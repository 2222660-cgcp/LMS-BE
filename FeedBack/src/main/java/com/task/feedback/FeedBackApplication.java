package com.task.feedback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages= {"com.task.feedback"})
public class FeedBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeedBackApplication.class, args);
	}

}
