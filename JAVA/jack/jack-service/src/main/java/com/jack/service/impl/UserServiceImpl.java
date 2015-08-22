package com.jack.service.impl;

import org.springframework.stereotype.Service;

import com.jack.entity.User;
import com.jack.service.IUserService;

/**
 * Hello world!
 *
 */
@Service("userService")
public class UserServiceImpl implements IUserService
{
	public User queryUser(Long id) {
		User u=new User();
		return u;
	}
}
