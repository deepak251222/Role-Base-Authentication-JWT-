package com.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.Entity.Role;
import com.spring.service.RoleService;

@RestController
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@PostMapping("/createNewRole")//@PostMapping({"/createNewRole"})
	public Role createNewRole(@RequestBody Role role)
	{
		return roleService.createRole(role);
	}

}
