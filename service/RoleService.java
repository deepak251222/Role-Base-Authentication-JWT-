package com.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.Entity.Role;
import com.spring.dao.RoleDao;

@Service
public class RoleService {

	@Autowired
	private RoleDao roleDao;
	
	public Role createRole(Role role) {
	   return roleDao.save(role);
		
	}
}