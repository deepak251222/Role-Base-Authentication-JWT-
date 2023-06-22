package com.spring.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.Entity.Role;
import com.spring.Entity.User;
import com.spring.dao.RoleDao;
import com.spring.dao.UserDao;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public User registerNewUser(User user)
	{
		Role role=roleDao.findById("user").get();
		Set<Role> roles=new HashSet<>();
		roles.add(role);
		user.setRoles(roles);
		user.setUserPwd(getPasswordEncoder(user.getUserPwd()));
		return  userDao.save(user);
	}
	// for not Creating and revoming data into databasse
   public void initRolesAndUser()
   {
	   Role adminRole=new Role();
	   adminRole.setRoleName("admin");
	   adminRole.setRoleDesc("admin role");
	   
	   roleDao.save(adminRole);
	   
	   Role userRole=new Role();
	   userRole.setRoleName("user");
	   userRole.setRoleDesc("Default role for new record");
	   
	   roleDao.save(userRole);
	   
	   User adminUser= new User();
	   adminUser.setFname("admin");
	   adminUser.setLname("admin");
	   adminUser.setUsername("admin123");
	   adminUser.setUserPwd(getPasswordEncoder("admin@pass"));
	   Set<Role> adminRoles=new HashSet<>();
	   adminRoles.add(adminRole);
	   adminUser.setRoles(adminRoles);
	   userDao.save(adminUser);
	   
//	   User user= new User();
//	   user.setFname("deepak");
//	   user.setLname("kumar");
//	   user.setUsername("deepak123");
//	   user.setUserPwd(getPasswordEncoder("deepak@pass"));
//	   Set<Role> userRoles=new HashSet<>();
//	   userRoles.add(userRole);
//	   user.setRoles(userRoles);//for setting data into user_role
//	   userDao.save(user);
   }
   public String getPasswordEncoder(String password)
   {
	   return passwordEncoder.encode(password);
   }
}
