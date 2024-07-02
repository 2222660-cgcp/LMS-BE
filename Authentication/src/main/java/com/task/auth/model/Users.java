package com.task.auth.model;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Table;
 
@Entity
@Table(name = "users")
public class Users {
	@Id
	@Column(name = "username", length = 20)
	private String username;
	@Override
	public String toString() {
		return "Users [username=" + username + ", password=" + password + ",firstname=" + firstname + ",lastname=" + lastname + ", email=" + email + ",phone=" + phone+ ", address=" + address +", role=" + role + "]";
	}
	@Column(name = "firstname")
	private String firstname;
	@Column(name = "lastname")
	private String lastname;
 
	@Column(name = "password")
	private String password;
	@Column(name = "email")
	private String email;
	  @Column(name = "phone")
	    private String phone;
	    @Column(name = "address")
	    private String address;

 
	@Column(name = "role")
	private String role;
	public String getEmail() {
		return email;
	}
 
	public String getFirstname() {
		return firstname;
	}
 
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
 
	public String getLastname() {
		return lastname;
	}
 
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
 
	public void setEmail(String email) {
		this.email = email;
	}
 
	
	public String getUsername() {
		return username;
	}
 
	public void setUsername(String username) {
		this.username = username;
	}
 
	public String getPassword() {
		return password;
	}
 
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
 
	public String getRole() {
		return role;
	}
 
	public void setRole(String role) {
		this.role = role;
	}
 
	public Users() {
		super();
	}
 
	public Users(String username,String firstname,String lastname, String password, String email,String phone, String address, String role) {
		super();
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.email = email;
		this.phone = phone;
        this.address = address;
		this.role = role;
	}

}

//@Entity
//@Table(name = "users")
//public class Users {
//	
//	@Id
//	@Column(name = "username", length = 20)
//	private String username;
//	
//	@Column(name = "firstname")
//	private String firstname;
//	
//	@Column(name = "lastname")
//	private String lastname;
//	
//	@Column(name = "password")
//	private String password;
//	
//	@Column(name = "email")
//	private String email;
//	
//	@Column(name = "phone")
//    private String phone;
//	
//    @Column(name = "address")
//    private String address;
//	
//	@Column(name = "role")
//	private String role;
//	
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	
//	public String getUsername() {
//		return username;
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//	public String getRole() {
//		return role;
//	}
//
//	public void setRole(String role) {
//		this.role = role;
//	}
//	
//	public String getFirstname() {
//		return firstname;
//	}
//
//	public void setFirstname(String firstname) {
//		this.firstname = firstname;
//	}
//
//	public String getLastname() {
//		return lastname;
//	}
//
//	public void setLastname(String lastname) {
//		this.lastname = lastname;
//	}
//
//	public String getPhone() {
//		return phone;
//	}
//
//	public void setPhone(String phone) {
//		this.phone = phone;
//	}
//
//	public String getAddress() {
//		return address;
//	}
//
//	public void setAddress(String address) {
//		this.address = address;
//	}
//
//	public Users() {
//		super();
//	}
//
//	public Users(String username, String firstname, String lastname, String password, String email, String phone,
//			String address, String role) {
//		super();
//		this.username = username;
//		this.firstname = firstname;
//		this.lastname = lastname;
//		this.password = password;
//		this.email = email;
//		this.phone = phone;
//		this.address = address;
//		this.role = role;
//	}
//
//	@Override
//	public String toString() {
//		return "Users [username=" + username + ", firstname=" + firstname + ", lastname=" + lastname + ", password="
//				+ password + ", email=" + email + ", phone=" + phone + ", address=" + address + ", role=" + role + "]";
//	}
//	
//}
