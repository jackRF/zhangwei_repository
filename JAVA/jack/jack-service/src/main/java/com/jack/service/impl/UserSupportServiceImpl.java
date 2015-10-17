package com.jack.service.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jack.entity.User;

@Service
@Scope("session")
public class UserSupportServiceImpl extends AbstractUserSupportImpl{
	
	private User user;
	
	@Override
	public boolean isSupport(User user,String role){
		if(this.user==null){
			this.user=user;
			//roles
			//return hasRole role
		}
		return false;
	}
}
