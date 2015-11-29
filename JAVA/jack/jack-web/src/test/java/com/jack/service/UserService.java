package com.jack.service;

import org.springframework.transaction.annotation.Transactional;

import com.jack.entity.User;
@Transactional
public interface UserService extends IUser{
	
	int saveUser(User user);
	int updateUser(User user);
}
