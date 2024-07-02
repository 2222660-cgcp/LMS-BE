package com.task.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task.reservation.entity.Feedback;

 
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

}
