package com.task.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.task.auth.model.Users;

@Repository           
public interface UserRepository extends JpaRepository<Users, String> {
	
	@Query("select u from Users u where u.role = 'USER'")
	List<Users> registeredUsers();

}
