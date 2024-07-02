package com.task.reservation.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.reservation.entity.Feedback;
import com.task.reservation.repository.FeedbackRepository;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Override
	public Feedback addFeedback(Feedback feedback, String token) {
		 return feedbackRepository.save(feedback);
	}
 
//    --------------------------------------------------------------------------------------------------------------
    
	@Override
	public List<Feedback> viewAllFeedback(String token) {
		 return feedbackRepository.findAll();
	}        
}
 