//package com.WhatsSumNews.Service;
//
//import java.util.List;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.WhatsSumNews.Mapper.CommentMapper;
//import com.WhatsSumNews.dto.Comment;
//
//@Service
//public class CommentService {
//	
//     private final CommentMapper commentMapper;
//	   
//     @Autowired
//     public CommentService(CommentMapper commentMapper) {
//         this.commentMapper = commentMapper;
//     }
//     
//	    public void addComment(String comment) {
//	    	commentMapper.insertComment(comment);
//
//	    }
//
//		public void deleteComment(int id) {
//			commentMapper.deleteComment(id);
//	    }
//
//	   
//		public List<Comment> getAllComments() {
//			// TODO Auto-generated method stub
//			return commentMapper.findAll();
//		}
//	}
