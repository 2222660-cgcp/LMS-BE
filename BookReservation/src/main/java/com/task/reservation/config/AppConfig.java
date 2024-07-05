package com.task.reservation.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

//-------------------------------IBRAHIM BADSHAH--------------------------------

@Configuration
public class AppConfig {
   @Bean
   public RestTemplate restTemplate() {
       return new RestTemplate();
   }
}