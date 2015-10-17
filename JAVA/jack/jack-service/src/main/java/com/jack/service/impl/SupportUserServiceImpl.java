package com.jack.service.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jack.entity.User;

@Service
@Scope("session")
public class SupportUserServiceImpl {
	
	private User user;
	
	public boolean isSupport(User user,String role){
		if(this.user==null){
			this.user=user;
			//roles
			//return hasRole role
		}
		return false;
	}
}
