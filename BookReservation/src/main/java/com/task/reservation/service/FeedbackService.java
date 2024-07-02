package com.task.reservation.service;

import java.util.List;

import com.task.reservation.entity.Feedback;

public interface FeedbackService {
	
	Feedback addFeedback(Feedback feedback, String token);
	
//	--------------------------------------------------------------------------------------------------------------
	
    List<Feedback> viewAllFeedback(String token);

}
