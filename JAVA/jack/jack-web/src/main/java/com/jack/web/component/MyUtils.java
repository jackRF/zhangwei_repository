package com.jack.web.component;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(WebApplicationContext.SCOPE_SESSION)
public class MyUtils {
	private Long userId;
	public Long getUserId(){
		if(userId==null||userId<=0){
			userId=System.currentTimeMillis();
		}
		System.out.println("MyUtils userId:"+userId);
		return userId;
	}
}
