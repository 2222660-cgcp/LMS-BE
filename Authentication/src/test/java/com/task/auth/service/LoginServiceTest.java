package com.task.auth.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import com.task.auth.exceptionhandling.UserNotFoundException;
import com.task.auth.model.LoginRequest;
import com.task.auth.model.LoginResponse;
import com.task.auth.model.Users;
import com.task.auth.repository.UserRepository;

@SpringBootTest
class LoginServiceTest {

	@Mock
	private JwtUtil jwtutil;
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private CustomerDetailsService customerDetailservice;
	
	@InjectMocks
	private LoginService loginService;
		
	private LoginRequest loginRequest;
	private Users user1;
	private Users user2;
	
	@BeforeEach
	void setUp() {
		user1 = new Users();
		user1.setUsername("user1");
		user1.setPassword("user1Password");
		user1.setEmail("user1Email");
		user1.setRole("user1Role");
		
		user2 = new Users();
		user2.setUsername("user2");
		user2.setPassword("user2Password");
		user2.setEmail("user2Email");
		user2.setRole("user2Role");
		
		loginRequest = new LoginRequest();
		loginRequest.setUsername("username");
		loginRequest.setPassword("password");
		
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void testLogin_Success() throws UserNotFoundException {
		
		UserDetails userDetails = mock(UserDetails.class);
		Users user = new Users();
		user.setUsername("username");
		user.setPassword("passowrd");
		user.setEmail("email@gmail.com");
		user.setRole("USER");
		
		when(customerDetailservice.loadUserByUsername("username")).thenReturn(userDetails);
		when(userDetails.getPassword()).thenReturn("password");
        when(userRepository.findById("username")).thenReturn(Optional.of(user));
		when(jwtutil.generateToken(userDetails)).thenReturn("token");
		
		LoginResponse loginResponse = loginService.userLogin(loginRequest);
		
		assertNotNull(loginResponse);
	}
}
