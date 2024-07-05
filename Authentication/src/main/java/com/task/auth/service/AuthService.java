package com.task.auth.service;

import java.util.List;

import com.task.auth.model.PasswordUpdateRequest;
import com.task.auth.model.Users;

//---------------------------------Anagha.S.R-----------------------------------------------------------------------------

public interface AuthService {	
	List<Users> registeredUsers();
	Users signUp(Users userData);
	
//	------------------------------Ibrahim Badshah------------------------------------------------------------------------------
	
	Users getUserByUsername(String username,String Token);
	Users updateUser(String username,Users user, String token);
	void changePassword(String username, PasswordUpdateRequest passwordChangeRequest, String token);
	
}
