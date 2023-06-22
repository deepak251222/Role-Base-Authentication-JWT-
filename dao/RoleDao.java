package com.spring.dao;

import org.springframework.data.repository.CrudRepository;

import com.spring.Entity.Role;

public interface RoleDao extends CrudRepository<Role, String>{

}
