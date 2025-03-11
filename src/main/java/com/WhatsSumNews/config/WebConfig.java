package com.WhatsSumNews.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
    	 // 기본 경로("/")도 index.html로 연결
        registry.addViewController("/").setViewName("forward:/index.html");
        // nyt 경로는 제외하고 클라이언트 사이드 라우팅 적용
        registry.addViewController("/{spring:^(?!nyt$).*}$").setViewName("forward:/index.html");
//        registry.addViewController("/**/{spring:^(?!nyt$).*}$").setViewName("forward:/index.html");
        registry.addViewController("/{spring:^(?!nyt$).*}/**{spring:?!(\\.js|\\.css|\\.ico|\\.json)$}")
                .setViewName("forward:/index.html");
    }
}