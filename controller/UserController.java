package com.spring.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.Entity.User;
import com.spring.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//calling init method
	//for Creating user auto 
	@PostConstruct
	public void initRolesUsers()
	{
		userService.initRolesAndUser();
	}
	
	@PostMapping({"/newuser"})
	public User registerNewUser(@RequestBody User user) {
		
		return userService.registerNewUser(user);
	}
	//for admin accessible
	
	@GetMapping({"/forAdmin"})
	@PreAuthorize("hasRole('admin')")
    public String forAdmin()
    {
    	return "This url accessible to admin";
    }
	@GetMapping({"/forUser"})
	@PreAuthorize("hasRole('user')")
    public String forUser()
    {
    	return "This url accessible to user";
    }
	
	

}
