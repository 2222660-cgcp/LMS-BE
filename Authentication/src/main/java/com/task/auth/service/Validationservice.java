package com.task.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.task.auth.model.AuthenticationResponse;
import com.task.auth.repository.UserRepository;

@Component
public class Validationservice { 

	@Autowired
	private JwtUtil jwtutil;

	@Autowired
	private UserRepository userRepo;

	public AuthenticationResponse validate(String token) { 
	    AuthenticationResponse authenticationResponse = new AuthenticationResponse();

	    if (token.startsWith("Bearer ")) { 
	        token = token.substring(7);  
	    }
	    if (jwtutil.validateToken(token)) { 
	        String extractedUsername = jwtutil.extractUsername(token);
	       
	        authenticationResponse.setValid(true);
	        authenticationResponse.setUsername(userRepo.findById(extractedUsername).get().getUsername());			
	    } else {	
	        authenticationResponse.setValid(false);
	    }
	    return authenticationResponse; 
	}
}