package com.task.feedback.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.task.feedback.entity.Feedback;
import com.task.feedback.service.FeedbackService;

//-----------------------------IBRAHIM BADSHAH---------------------------

@CrossOrigin
@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;
    
    @PostMapping
    public ResponseEntity<Feedback> addComment(@RequestBody Feedback feedback, @RequestHeader("Authorization") final String token) {
    	Feedback savedComment = feedbackService.addFeedback(feedback, token);
        return ResponseEntity.ok(savedComment);
    }
    
//    ------------------------ANAGHA.S.R--------------------------------------------------------------------------------------
    
    @GetMapping
    public ResponseEntity<List<Feedback>> viewAllFeedback(@RequestHeader("Authorization") final String token) {
        List<Feedback> feedbacks = feedbackService.viewAllFeedback(token);
        return new ResponseEntity<List<Feedback>> (feedbacks, HttpStatus.OK);
    }
}