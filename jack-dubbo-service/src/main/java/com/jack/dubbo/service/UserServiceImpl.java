package com.jack.dubbo.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.jack.entity.User;
import com.jack.service.IUserService;

/**
 * Hello world!
 *
 */
@Service
public class UserServiceImpl implements IUserService
{
	public User queryUser(Long id) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		System.out.println(sdf.format(new Date()));
		System.out.println("queryUser...");
		User u=new User();
		u.setId(207L);
		u.setName("张伟");
		u.setUserName("zhangwei");
		return u;
	}
}
