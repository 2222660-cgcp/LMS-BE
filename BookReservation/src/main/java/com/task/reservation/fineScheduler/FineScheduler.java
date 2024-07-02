package com.task.reservation.fineScheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.task.reservation.service.ReservationService;

 
@Component
public class FineScheduler {
 
    @Autowired
    private ReservationService reservationService;
 
    @Scheduled(cron = "0 0 * * * ?") 
    public void applyFines() {
    	reservationService.updateFines();
    }
}
