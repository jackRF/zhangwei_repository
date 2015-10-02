package com.jack.web.listener;

import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
@Component  
public class CRefreshListener implements ApplicationListener<ContextRefreshedEvent> {

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		ApplicationContext context = (ApplicationContext) event.getSource();
		
		Map<String,Object> beans=event.getApplicationContext().getBeansWithAnnotation(Controller.class);
		for(String key:beans.keySet()){
			Class<?> handlerType = context.getType(key);
			System.out.println(handlerType.getName());
			final Class<?> userType = ClassUtils.getUserClass(handlerType);
			System.out.println(userType.getName());
			System.out.println(key);
			System.out.println(beans.get(key).getClass().getName());
			beans.get(key);
		}
		System.out.println("ContextRefreshedEvent...");
	}
}
