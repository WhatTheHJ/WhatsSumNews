package com.WhatsSumNews.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.WhatsSumNews.Service.NYTService;
import com.WhatsSumNews.dto.NYTResponse;
import com.WhatsSumNews.dto.NYTResponse.Article;

@RestController
@RequestMapping("/nyt")
@CrossOrigin(origins = "http://localhost:5173") //cors오류방지, 리액트서버의 접근허용
public class NYTController {
    private final NYTService nytService;

    public NYTController(NYTService nytService) {
        this.nytService = nytService;
    }

    @GetMapping("/articles/{period}")
    public ResponseEntity<List<Article>> getArticles(@PathVariable("period") int period) {
       
        List<Article> articles = nytService.fetchArticles(period);
//        for (Article article : articles) {
//            System.out.println("Article: " + article);  // Article 객체 출력
//        }
        for (Article article : articles) {
            article.setPeriod(period);  // period 값을 각 기사에 설정
        }
        
        return new ResponseEntity<>(articles, HttpStatus.OK);
//        return ResponseEntity.ok(articles);
    }
}

