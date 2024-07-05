package com.task.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.task.auth.model.Users;
import com.task.auth.repository.UserRepository;

//----------------------------------ANAGHA.S.R---------------------------

@Service 
public class CustomerDetailsService implements UserDetailsService { 

	@Autowired
	private UserRepository userRepo; 

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { 
	
		Users user = null;
		 
		user = userRepo.findById(username).orElse(null);
			
		if (user != null) {
			List<GrantedAuthority> grantedAuthorities = AuthorityUtils 
					.commaSeparatedStringToAuthorityList("ROLE_" + user.getRole());
					 
			return new User(user.getUsername(), user.getPassword(), grantedAuthorities); 
		} else {
			throw new UsernameNotFoundException("Invalid username! Try again"); 
		}
	}
}