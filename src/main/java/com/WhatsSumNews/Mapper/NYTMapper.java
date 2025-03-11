package com.WhatsSumNews.Mapper;


import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.WhatsSumNews.dto.NYTResponse.Article;

@Mapper
public interface NYTMapper {
	
	@Select("SELECT * FROM nyt_articles WHERE period = #{period} AND fetched_date = #{fetchedDate}")
    List<Article> findByPeriodAndDate(@Param("period") int period, @Param("fetchedDate") LocalDate fetchedDate);
	
	@Select("SELECT * FROM nyt_articles WHERE createdAt = CURDATE()")
    List<Article> findByCreatedAtToday();
	
	@Insert("""
		    INSERT INTO nyt_articles 
		    (title, id, abstract_text, url, published_date, section, 
		    subsection, adx_keywords, image_url, period, fetched_date) 

		    VALUES 
		    (#{title}, #{id}, #{abstract_text}, #{url}, #{Published_date}, #{section}, 
		    #{subsection}, #{Adx_keywords}, #{image_Url}, #{period}, #{fetchedDate})
		""")
		void insertArticle(Article article);


}
