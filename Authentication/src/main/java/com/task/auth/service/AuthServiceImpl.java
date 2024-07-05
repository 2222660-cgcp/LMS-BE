package com.task.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.auth.model.PasswordUpdateRequest;
import com.task.auth.model.Users;
import com.task.auth.repository.UserRepository;

//-----------------------------------------Anagha.S.R----------------------------------------------------------------------------

@Service
public class AuthServiceImpl implements AuthService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Users signUp(Users userData) {
		return userRepository.save(userData);
	}

	@Override
	public List<Users> registeredUsers() {
		return userRepository.registeredUsers();
	}
	
	
//	------------------------------------Ibrahim Badshah---------------------------------------------------------------------------------
	
	@Override
    public Users getUserByUsername(String username,String Token) {
        return userRepository.findById(username).orElseThrow(() -> new RuntimeException("User not found"));
    }

	@Override
	public Users updateUser(String username, Users user, String token) {
		Users userObj = userRepository.findById(username).orElseThrow(() -> new RuntimeException("User not found"));
		userObj.setUsername(username);
		userObj.setRole(userObj.getRole());
		userObj.setEmail(user.getEmail());
		userObj.setPassword(userObj.getPassword());
		userObj.setFirstname(user.getFirstname());
		userObj.setLastname(user.getLastname());
		userObj.setPhone(user.getPhone());
		userObj.setAddress(user.getAddress());
		userRepository.save(userObj);
		return userObj;
	}

	@Override
    public void changePassword(String username, PasswordUpdateRequest passwordChangeRequest, String token) {
        Users existingUser = userRepository.findById(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        existingUser.setPassword(passwordChangeRequest.getNewPassword());
        userRepository.save(existingUser);
    }

	
}
