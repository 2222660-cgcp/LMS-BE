package com.task.auth.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.task.auth.model.Users;
import com.task.auth.repository.UserRepository;

//------------------------------------ANAGHA.S.R---------------------------------------------------------------

@SpringBootTest
class CustomerDetailsServiceTest {
	
	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	private CustomerDetailsService customerDetailservice;
			
	private Users user1;
	
	@BeforeEach
	void setUp() {
		user1 = new Users();
		user1.setUsername("user1");
		user1.setPassword("user1Password");
		user1.setEmail("user1Email");
		user1.setRole("user1Role");
		
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testLoadUserByUsername() {
		String username = "username";
		when(userRepository.findById(username)).thenReturn(Optional.of(user1));
		UserDetails result = customerDetailservice.loadUserByUsername(username);
		Collection<? extends GrantedAuthority> roles = result.getAuthorities();
		
		assertNotNull(result);
		assertEquals(roles, result.getAuthorities());
		
		verify(userRepository, times(1)).findById(username);
	}
}
