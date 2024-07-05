package com.task.feedback.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task.feedback.entity.Feedback;

 
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

}
