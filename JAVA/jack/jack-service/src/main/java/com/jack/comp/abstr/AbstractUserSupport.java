package com.jack.comp.abstr;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.jack.comp.SessionUserSupport;
import com.jack.entity.User;

public abstract class AbstractUserSupport implements ApplicationContextAware{
	private ApplicationContext applicationContext;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext=applicationContext;
	}
	public boolean isSupport(User user,String role){
		return applicationContext.getBean(SessionUserSupport.class).isSupport(user, role);
	}
}
