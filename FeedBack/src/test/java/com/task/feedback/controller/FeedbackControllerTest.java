package com.task.feedback.controller;
 
import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.feedback.entity.Feedback;
import com.task.feedback.service.FeedbackService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
 
import java.util.Arrays;
 
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;
 

//--------------------------------IBRAHIM BADSHAH----------------------------------------------------

@WebMvcTest(FeedbackController.class)
public class FeedbackControllerTest {
 
    @Autowired
    private MockMvc mockMvc;
 
    @MockBean
    private FeedbackService feedbackService;
 
    @Autowired
    private ObjectMapper objectMapper;
 
    @Test
    public void testAddComment() throws Exception {
        Feedback feedback = new Feedback();
        feedback.setComment("Great service!");
 
        Feedback savedFeedback = new Feedback();
        savedFeedback.setComment("Great service!");
 
        Mockito.when(feedbackService.addFeedback(ArgumentMatchers.any(Feedback.class), ArgumentMatchers.anyString()))
                .thenReturn(savedFeedback);
 
        mockMvc.perform(post("/feedback")
                .header("Authorization", "Bearer token")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(feedback)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.comment", is("Great service!")));
    }
    
//    ------------------------------ANAGHA.S.R------------------------------------------------------------------------------
 
    @Test
    public void testViewAllFeedback() throws Exception {
        Feedback feedback1 = new Feedback();
        feedback1.setComment("Great service!");
 
        Feedback feedback2 = new Feedback();
        feedback2.setComment("Could be better.");
 
        Mockito.when(feedbackService.viewAllFeedback(ArgumentMatchers.anyString()))
                .thenReturn(Arrays.asList(feedback1, feedback2));
 
        mockMvc.perform(get("/feedback")
                .header("Authorization", "Bearer token")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].comment", is("Great service!")))
                .andExpect(jsonPath("$[1].comment", is("Could be better.")));
    }
}
