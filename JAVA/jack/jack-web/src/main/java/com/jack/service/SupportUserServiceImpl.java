package com.jack.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("session")
public class SupportUserServiceImpl {
	private Long userId;
	public Long getUserId(){
		if(userId==null||userId<=0){
			userId=System.currentTimeMillis();
		}
		System.out.println("MyUtils userId:"+userId);
		return userId;
	}
}
