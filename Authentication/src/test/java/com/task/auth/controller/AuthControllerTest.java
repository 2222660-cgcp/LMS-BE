package com.task.auth.controller;

import org.junit.jupiter.api.Test;

import com.task.auth.model.Users;
import com.task.auth.exceptionhandling.UserNotFoundException;
import com.task.auth.model.LoginRequest;
import com.task.auth.model.LoginResponse;
import com.task.auth.model.PasswordUpdateRequest;
import com.task.auth.service.AuthService;
import com.task.auth.service.JwtUtil;
import com.task.auth.service.LoginService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
 
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

//----------------------------------Anagha.S.R-------------------------------------------------------------------
 
@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {
 
    @Autowired
    private MockMvc mockMvc;
 
    @MockBean
    private AuthService authService;
    
    @MockBean
    private LoginService loginService;
    
    @MockBean
    private JwtUtil jwtUtil;
 
    @Autowired
    private ObjectMapper objectMapper;
    
    String username = "testuser";
    String token = "token";
    
    private List<Users> userList;
    private Users user;
    private Users user1;
    private PasswordUpdateRequest passwordUpdateRequest;
    
    private LoginRequest validLoginRequest;
    private LoginRequest invalidUsernameRequest;
    private LoginRequest incorrectCredentialsRequest;
 
    @BeforeEach
    void setUp() {
        user = new Users();
        user.setUsername("john");
        user.setEmail("test@example.com");
        user.setPhone("123456789");
        user.setFirstname("John");
        user.setLastname("Doe");
        user.setAddress("MainSt");
        user.setRole("USER");
        
        user1 = new Users();
        user1.setUsername("john2");
        user1.setEmail("test2@example.com");
        user1.setPhone("123456789");
        user1.setFirstname("John2");
        user1.setLastname("Doe2");
        user1.setAddress("MainSt2");
        user1.setRole("USER");
        
        validLoginRequest = new LoginRequest();
        validLoginRequest.setUsername("validUser");
        validLoginRequest.setPassword("validPassword");
 
        invalidUsernameRequest = new LoginRequest();
        invalidUsernameRequest.setUsername("invalidUser");
        invalidUsernameRequest.setPassword("somePassword");
 
        incorrectCredentialsRequest = new LoginRequest();
        incorrectCredentialsRequest.setUsername("validUser");
        incorrectCredentialsRequest.setPassword("invalidPassword");
        
        userList = Arrays.asList(user,user1);
 
        passwordUpdateRequest = new PasswordUpdateRequest();
        passwordUpdateRequest.setNewPassword("newpassword");
        
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    public void testValidLogin() throws Exception {
        LoginResponse loginResponse = new LoginResponse();
 
        Mockito.when(loginService.userLogin(Mockito.any(LoginRequest.class)))
               .thenReturn(loginResponse);
 
        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validLoginRequest)))
                .andExpect(status().isAccepted())
                .andExpect(content().json(objectMapper.writeValueAsString(loginResponse)))
                .andDo(print());
    }
    
    @Test
    public void testInvalidUsername() throws Exception {
        Mockito.when(loginService.userLogin(Mockito.any(LoginRequest.class)))
               .thenThrow(new UsernameNotFoundException("Invalid username!"));
 
        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidUsernameRequest)))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string("Invalid username!"))
                .andDo(print());
    }
    
    @Test
    public void testIncorrectCredentials() throws Exception {
        Mockito.when(loginService.userLogin(Mockito.any(LoginRequest.class)))
               .thenThrow(new UserNotFoundException("Incorrect credentials! Try again"));
 
        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(incorrectCredentialsRequest)))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string("Incorrect credentials! Try again"))
                .andDo(print());
    }
    
    @Test
    void signupTest() throws Exception {
    	when(authService.signUp(user)).thenReturn(user);
    	
    	mockMvc.perform(post("/signUp")
				 .contentType(MediaType.APPLICATION_JSON)
				 .content(objectMapper.writeValueAsString(user)))
        .andExpect(status().isCreated());
    	
    	verify(authService, times(1)).signUp(any(Users.class));

    }
 
    
    @Test
    void registeredUsersTest() throws Exception {
    	
    	when(authService.registeredUsers()).thenReturn(userList);
    	
    	mockMvc.perform(get("/view-users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].email").value("test@example.com"))
        .andExpect(jsonPath("$[0].phone").value("123456789"))
        .andExpect(jsonPath("$[0].address").value("MainSt"))
        .andExpect(jsonPath("$[0].firstname").value("John"))
        .andExpect(jsonPath("$[0].lastname").value("Doe"));
    }
    
//    --------------------Ibrahim Badshah---------------------------------------------------------------------------------
 
    @Test
    void updateUserTest() throws Exception {
 
        when(authService.updateUser(eq(username), any(Users.class), eq(token))).thenReturn(user);
 
        mockMvc.perform(put("/update-user/{username}", username)
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("test@example.com"))
                .andExpect(jsonPath("$.phone").value("123456789"))
                .andExpect(jsonPath("$.address").value("MainSt"))
                .andExpect(jsonPath("$.firstname").value("John"))
                .andExpect(jsonPath("$.lastname").value("Doe"));
        verify(authService, times(1)).updateUser(eq(username), any(Users.class), eq(token));
    }
 
    @Test
    void changePasswordTest() throws Exception {
 
        mockMvc.perform(MockMvcRequestBuilders.put("/change-password/{username}", username)
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(passwordUpdateRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Password updated successfully"));
    }
    
    @Test
    public void testGetLoggedInUser() throws Exception {
        String token = "token";
        String username = "validUser";
        Users user = new Users();
 
        Mockito.when(jwtUtil.validateToken(token)).thenReturn(true);
        Mockito.when(authService.getUserByUsername(username, token)).thenReturn(user);
 
        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authentication.getName()).thenReturn(username);
        SecurityContextHolder.getContext().setAuthentication(authentication);
 
        mockMvc.perform(get("/view")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
