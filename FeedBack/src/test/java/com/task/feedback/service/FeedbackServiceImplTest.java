package com.task.feedback.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
 
import java.util.Arrays;
import java.util.List;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
 
import com.task.feedback.entity.Feedback;
import com.task.feedback.repository.FeedbackRepository;

//--------------------------------IBRAHIM BADSHAH----------------------------------------------------

public class FeedbackServiceImplTest {
 
    @Mock
    private FeedbackRepository feedbackRepository;
 
    @InjectMocks
    private FeedbackServiceImpl feedbackService;
 
    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
 
    @Test
    public void testAddFeedback() {
        Feedback feedback = new Feedback();
        feedback.setId(1L);
        feedback.setComment("Test feedback message");
 
        when(feedbackRepository.save(feedback)).thenReturn(feedback);
        Feedback savedFeedback = feedbackService.addFeedback(feedback, "dummyToken");
        verify(feedbackRepository, times(1)).save(feedback);
        assertEquals("Test feedback message", savedFeedback.getComment());
    }
 

  //--------------------------------ANAGHA.S.R---------------------------------------------------
    
    @Test
    public void testViewAllFeedback() {
        Feedback feedback1 = new Feedback();
        feedback1.setId(1L);
        feedback1.setComment("Feedback 1");
 
        Feedback feedback2 = new Feedback();
        feedback2.setId(2L);
        feedback2.setComment("Feedback 2");
 
        List<Feedback> feedbackList = Arrays.asList(feedback1, feedback2);
        when(feedbackRepository.findAll()).thenReturn(feedbackList);
        List<Feedback> retrievedFeedbackList = feedbackService.viewAllFeedback("dummyToken");
        verify(feedbackRepository, times(1)).findAll();
 
        assertEquals(2, retrievedFeedbackList.size());
        assertEquals("Feedback 1", retrievedFeedbackList.get(0).getComment());
        assertEquals("Feedback 2", retrievedFeedbackList.get(1).getComment());
    }
}
