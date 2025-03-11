//package com.WhatsSumNews.Controller;
//
//import java.util.List;
//
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.WhatsSumNews.Service.CommentService;
//import com.WhatsSumNews.dto.Comment;
//
//@RestController
//@RequestMapping("/api/comments")
//public class CommentController {
//	
//	    private final CommentService commentService;
//
//	    public CommentController(CommentService commentService) {
//	        this.commentService = commentService;
//	    }
//
//	    @GetMapping
//	    public List<Comment> getAllComments() {
//	        return commentService.getAllComments();
//	    }
//
//	    @PostMapping
//	    public String addComment(@RequestParam String content) {
//	        commentService.addComment(content);
//	        System.out.println(content);
//	        return "Comment added successfully";
//	    }
//
//	    @DeleteMapping("/{id}")
//	    public String deleteComment(@PathVariable int id) {
//	        commentService.deleteComment(id);
//	        return "Comment deleted successfully";
//	    }
//
//	}
