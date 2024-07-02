package com.task.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.task.auth.exceptionhandling.UserNotFoundException;
import com.task.auth.model.LoginRequest;
import com.task.auth.model.LoginResponse;
import com.task.auth.model.Users;
import com.task.auth.repository.UserRepository;

@Component 
public class LoginService {

	@Autowired
	private JwtUtil jwtutil;
	
	@Autowired
	private CustomerDetailsService customerDetailservice;

	@Autowired
	private UserRepository userRepo;
	
	public LoginResponse userLogin(LoginRequest appuser) throws UserNotFoundException { 
	
		final UserDetails userdetails = customerDetailservice.loadUserByUsername(appuser.getUsername());
		
		String token = "";
		Users user = null;
		user = userRepo.findById(appuser.getUsername()).orElse(null); 

		String userRole = userRepo.findById(appuser.getUsername()).get().getRole(); 
		String email = userRepo.findById(user.getUsername()).get().getEmail();
		
		if (userdetails.getPassword().equals(appuser.getPassword()) && userRole.equals(user.getRole()) ) {  
			token = jwtutil.generateToken(userdetails);
			return new LoginResponse(token,userRole,email);
		} else {
			throw new UserNotFoundException("Incorrect credentials! Try again");	
		}
	}
}
