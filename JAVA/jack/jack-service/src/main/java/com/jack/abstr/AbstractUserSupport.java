package com.jack.abstr;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.jack.entity.User;
import com.jack.service.impl.SessionUserSupportServiceImpl;

public abstract class AbstractUserSupport implements ApplicationContextAware{
	private ApplicationContext applicationContext;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext=applicationContext;
	}
	public boolean isSupport(User user,String role){
		return applicationContext.getBean(SessionUserSupportServiceImpl.class).isSupport(user, role);
	}
}
