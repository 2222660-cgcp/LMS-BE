package com.task.feedback.service;

import java.util.List;

import com.task.feedback.entity.Feedback;

//------------------IBRAHIM BADSHAH-------------------------------------------------

public interface FeedbackService {
	
	Feedback addFeedback(Feedback feedback, String token);
	
//	-------------------ANAGHA.S.R-------------------------------------------------------------------------------------------
	
    List<Feedback> viewAllFeedback(String token);

}
