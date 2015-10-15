package com.jack.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class MyServiceImpl  implements ApplicationContextAware{
	private String userName;
	public Long getUserId(){
		if(userName==null||userName.isEmpty()){
			userName="userName:"+System.currentTimeMillis()+"|sdfsdfsdfd";
		}
		System.out.println(userName);
		return applicationContext.getBean(SupportUserServiceImpl.class).getUserId();
	}
	private ApplicationContext applicationContext;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext=applicationContext;
	}
}
