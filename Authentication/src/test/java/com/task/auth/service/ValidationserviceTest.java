package com.task.auth.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.task.auth.model.AuthenticationResponse;
import com.task.auth.model.LoginRequest;
import com.task.auth.model.Users;
import com.task.auth.repository.UserRepository;

@SpringBootTest
class ValidationserviceTest {

	@Mock
	private JwtUtil jwtutil;
	
	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	private Validationservice validationService;
	
		
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
	
//	@Test
//	void testValidate_Success() {
//		String token = "Bearer token";
//		String extractedUsername = "user1";
//		
//		when(jwtutil.validateToken(token)).thenReturn(true);
//		when(jwtutil.extractUsername(token)).thenReturn(extractedUsername);
//		
//		AuthenticationResponse authenticationResponse = validationService.validate(token);
//		
//		assertTrue(authenticationResponse.isValid());
//		assertEquals(extractedUsername, authenticationResponse.getUsername());
//				
//	}

}
