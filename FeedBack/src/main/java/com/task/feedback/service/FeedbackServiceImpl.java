package com.task.feedback.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.feedback.entity.Feedback;
import com.task.feedback.repository.FeedbackRepository;

//--------------------------------IBRAHIM BADSHAH----------------------------------------------------

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Override
	public Feedback addFeedback(Feedback feedback, String token) {
		 return feedbackRepository.save(feedback);
	}
 
//    ------------------------------ANAGHA.S.R--------------------------------------------------------------------------------
    
	@Override
	public List<Feedback> viewAllFeedback(String token) {
		 return feedbackRepository.findAll();
	}        
}
 