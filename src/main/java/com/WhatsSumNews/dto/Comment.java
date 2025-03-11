package com.WhatsSumNews.dto;

import lombok.Data;

@Data
public class Comment {
	private int commentId;
	private int createAt;
	private String content;

}
