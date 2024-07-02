package com.task.auth.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.task.auth.exceptionhandling.UserNotFoundException;
import com.task.auth.model.LoginRequest;
import com.task.auth.model.LoginResponse;
import com.task.auth.model.PasswordUpdateRequest;
import com.task.auth.model.Users;
import com.task.auth.repository.UserRepository;
import com.task.auth.service.AuthService;
import com.task.auth.service.JwtUtil;
import com.task.auth.service.LoginService;

@RestController   
@CrossOrigin()  
public class AuthController {

	@Autowired  
	private UserRepository userRepository;

	@Autowired
	private LoginService loginService;
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("/signUp") 
	public ResponseEntity<?> signup(@RequestBody Users userData) {
		Users createduser = null; 
		Optional<Users> user = userRepository.findById(userData.getUsername());
		if(user.isPresent()) {
			return new ResponseEntity<String>("Username already exists! Please choose a different username", 
					HttpStatus.NOT_ACCEPTABLE); 
		} else {
			try {
				createduser = authService.signUp(userData);
			} catch (Exception e) {
				return new ResponseEntity<String>("Not created! Try again", HttpStatus.NOT_ACCEPTABLE); 
			}
			return new ResponseEntity<>(createduser, HttpStatus.CREATED);
		}
	}
	
	@PostMapping("/login") 
	public ResponseEntity<?> login(@RequestBody LoginRequest credentials)
			throws UsernameNotFoundException, UserNotFoundException { 	
		try {
			LoginResponse loginResponse = loginService.userLogin(credentials); 
			return new ResponseEntity<>(loginResponse, HttpStatus.ACCEPTED); 
		} catch(UserNotFoundException e) {
			return new ResponseEntity<String>("Incorrect credentials! Try again", HttpStatus.UNAUTHORIZED); 
		} catch(UsernameNotFoundException e) {
			return new ResponseEntity<String>("Invalid username!", HttpStatus.UNAUTHORIZED); 
		}			
	}	
	
	@GetMapping("/view-users")
	public ResponseEntity<List<Users>> registeredUsers() {
		List<Users> users = authService.registeredUsers();
		return new ResponseEntity<List<Users>> (users, HttpStatus.OK);
	}
	
//	------------------------------------------------------------------------------------------------------------------
	
	@GetMapping("/validateToken")
    public ResponseEntity<String> validateToken(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        if (jwtUtil.validateToken(token)) {
        	String username = jwtUtil.getUsernameFromToken(token);
            return ResponseEntity.ok(username);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is not valid");
        }
    }
	
	@GetMapping("/view")
    public Users getLoggedInUser(@RequestHeader("Authorization") final String Token) {
        String loggedInUsername = getCurrentUsername();
        return authService.getUserByUsername(loggedInUsername,Token);
    }
	
	 private String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
	
	@PutMapping("/update-user/{username}")
	public Users updateUser(@PathVariable String username, @RequestBody Users user, @RequestHeader("Authorization") final String token) {
		return authService.updateUser(username, user, token);
	}
	
	@PutMapping("/change-password/{username}")
    public ResponseEntity<String> changePassword(@PathVariable String username, @RequestBody PasswordUpdateRequest passwordChangeRequest, @RequestHeader("Authorization") final String token) {
        authService.changePassword(username, passwordChangeRequest, token);
        return ResponseEntity.ok("Password updated successfully");
    }
}
