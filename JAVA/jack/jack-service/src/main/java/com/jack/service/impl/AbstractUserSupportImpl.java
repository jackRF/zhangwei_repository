package com.jack.service.impl;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.jack.entity.User;

public abstract class AbstractUserSupportImpl implements ApplicationContextAware{
	private ApplicationContext applicationContext;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext=applicationContext;
	}
	public boolean isSupport(User user,String role){
		return applicationContext.getBean(UserSupportServiceImpl.class).isSupport(user, role);
	}
}
