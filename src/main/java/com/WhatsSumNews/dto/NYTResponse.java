package com.WhatsSumNews.dto;


import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class NYTResponse {
    private List<Article> results;
    public NYTResponse() {
		super();
	}


	@Data
	@NoArgsConstructor
    public static class Article {
        private String title;
        private BigInteger id;
        
        @JsonProperty("abstract")
        private String abstract_text;
        
        private String url;
        private String Published_date;
        private String section;
        private String subsection;
        private String Adx_keywords;
        private int period;
        private LocalDate fetchedDate;
        
        @JsonProperty("image_url") 
        @JsonDeserialize(using = ImageUrlDeserializer.class)
		private String image_Url;

        @JsonCreator
        public Article(
                @JsonProperty("title") String title,
                @JsonProperty("id") BigInteger id,
                //@JsonProperty("abstractText") String abstractText,
                @JsonProperty("url") String url,
                @JsonProperty("Published_date") String Published_date,
                @JsonProperty("section") String section,
                @JsonProperty("subsection") String subsection,
                @JsonProperty("Adx_keywords") String Adx_keywords,
                @JsonProperty("period") int period,
                //@JsonProperty("image_Url") String imageUrl,
                @JsonProperty("media") List<Media> media
        ) {
            this.title = title;
            this.id = id;
            this.abstract_text = abstract_text;
            this.url = url;
            this.Published_date = Published_date;
            this.section = section;
            this.subsection = subsection;
            this.Adx_keywords = Adx_keywords;
            this.period= period;
            this.image_Url=image_Url;
            //System.out.println("AbstractText: " + abstractText);  // 이 부분을 통해 값 확인

            // ✅ media-metadata의 세 번째 URL만 가져오기
            if (media != null && !media.isEmpty()) {
                List<MediaMetadata> mediaMetadata = media.get(0).getMediaMetadata();
                if (mediaMetadata != null && mediaMetadata.size() > 2) {  // 최소 3개 이상인지 확인
                    this.image_Url  = mediaMetadata.get(2).getUrl();  // 세 번째 URL 저장
                }
            }
            //this.imageUrl=imageUrl;
            
        }

    @Data
    public static class Media {
        @JsonProperty("media-metadata")  // JSON의 "media-metadata" 필드 매핑(하이픈은 바로쓸수없음)
        private List<MediaMetadata> mediaMetadata;  // "media-metadata" 필드 추가
    }

    @Data
    public static class MediaMetadata {
    	@JsonProperty("url")
        private String url;  // 이미지 URL
    }
}
}
