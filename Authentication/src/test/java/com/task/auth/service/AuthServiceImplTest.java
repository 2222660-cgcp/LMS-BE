package com.task.auth.service;

import static org.junit.jupiter.api.Assertions.*; 
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.task.auth.model.LoginRequest;
import com.task.auth.model.Users;
import com.task.auth.repository.UserRepository;

@SpringBootTest
class AuthServiceImplTest {
	
	@Mock
	private JwtUtil jwtutil;
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private CustomerDetailsService customerDetailservice;
	
	@InjectMocks
	private AuthServiceImpl authServiceImpl;
		
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
	void testViewRegisteredUsers_Success() {
		List<Users> users = Arrays.asList(user1, user2);
		when(userRepository.registeredUsers()).thenReturn(users);
		List<Users> result = authServiceImpl.registeredUsers();
		
		assertNotNull(result);
		assertEquals(2, result.size());
		assertEquals("user1", result.get(0).getUsername());
		assertEquals("user2", result.get(1).getUsername());
		
		verify(userRepository, times(1)).registeredUsers();
	}

	@Test
	void testGetUserByUsername() {
		when(userRepository.findById(loginRequest.getUsername())).thenReturn(Optional.of(user1));
		Optional<Users> result = userRepository.findById(loginRequest.getUsername());
		
		assertNotNull(result);
		assertEquals("user1", result.get().getUsername());
		assertEquals("user1Password", result.get().getPassword());
		assertEquals("user1Email", result.get().getEmail());
		assertEquals("user1Role", result.get().getRole());
		
		verify(userRepository, times(1)).findById(loginRequest.getUsername());
	}
	
	@Test
	void testSignup_Success() {
		when(userRepository.save(user1)).thenReturn(user1);
		Users result = userRepository.save(user1);
		
		assertNotNull(result);
		assertEquals("user1", result.getUsername());
		assertEquals("user1Password", result.getPassword());
		assertEquals("user1Email", result.getEmail());
		assertEquals("user1Role", result.getRole());
		
		verify(userRepository).save(user1);
	}		
}
