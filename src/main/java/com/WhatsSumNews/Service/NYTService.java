package com.WhatsSumNews.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.WhatsSumNews.Mapper.NYTMapper;
import com.WhatsSumNews.dto.NYTResponse;
import com.WhatsSumNews.dto.NYTResponse.Article;

@Service
public class NYTService {

    @Autowired
    private NYTMapper nytMapper;

    private final RestTemplate restTemplate = new RestTemplate();
    private final String API_KEY = "MDiSrsX025OcPIH8xBwJepxrjkNL46A6"; // 본인의 NYT API 키 입력
    private final String BASE_URL = "https://api.nytimes.com/svc/mostpopular/v2/viewed/%d.json?api-key=%s"; // %d를 사용하여 period를 처리

    
    
    public List<Article> fetchArticles(int period) {

    	LocalDate today = LocalDate.now();
        // 로그 추가 - DB에서 먼저 데이터 조회
    	 System.out.println("Checking if data exists for period: " + period + " on " + today);

        // 1. DB에서 먼저 데이터 조회
        List<Article> articles = nytMapper.findByPeriodAndDate(period, today);
        if (!articles.isEmpty()) {
            //System.out.println("Found articles in DB for period " + period);
           System.out.println("Fetched article data: " + articles);
            return articles;
        }

        // 2. DB에 데이터가 없으면 API 호출
        String url = String.format(BASE_URL, period, API_KEY); // period 값을 URL에 제대로 넣도록 수정
        System.out.println("Requesting URL: " + url);  // URL 확인용 로그

        // ResponseEntity 사용하여 응답받기
        try {
            ResponseEntity<NYTResponse> response = restTemplate.getForEntity(url, NYTResponse.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                System.out.println("Successfully fetched articles from NYT API");

                // API 응답에서 articles를 가져와 DB에 저장
                List<NYTResponse.Article> nytArticles = response.getBody().getResults();
                List<Article> savedArticles = new ArrayList<>();

                // 3. API 결과를 DB에 저장
                if (nytArticles != null && !nytArticles.isEmpty()) {
                    for (NYTResponse.Article result : nytArticles) {
                        Article article = new Article();
                        article.setTitle(result.getTitle());
                        article.setId(result.getId());
                        article.setAbstract_text(result.getAbstract_text());
                        article.setUrl(result.getUrl());
                        article.setPublished_date(result.getPublished_date());
                        article.setSection(result.getSection());
                        article.setSubsection(result.getSubsection());
                        article.setAdx_keywords(result.getAdx_keywords());
                        article.setPeriod(period); 
                        article.setImage_Url(result.getImage_Url());
                        article.setFetchedDate(today);  // ✅ 오늘 날짜 추가
                        
                        //article.setImageUrl(result.getImageUrl());

                        nytMapper.insertArticle(article);  // DB에 저장
                        //savedArticles.add(article);
                    }
                }

                return nytArticles; // DB에 저장한 데이터를 반환
            } else {
                System.out.println("Failed to fetch articles, HTTP Status: " + response.getStatusCode());
                throw new RuntimeException("Failed to fetch articles");
            }
        } catch (Exception e) {
            System.out.println("Error occurred while calling the NYT API: " + e.getMessage());
            throw new RuntimeException("Failed to fetch articles", e);
        }
    }}
    
