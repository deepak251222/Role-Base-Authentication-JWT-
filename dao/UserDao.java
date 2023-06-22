package com.spring.dao;

import org.springframework.data.repository.CrudRepository;

import com.spring.Entity.User;

public interface UserDao extends CrudRepository<User, String> {

}
