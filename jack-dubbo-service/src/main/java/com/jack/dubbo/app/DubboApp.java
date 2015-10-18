package com.jack.dubbo.app;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DubboApp{
	
	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "spring-conf/spring-service.xml" });
		context.start();
		System.out.println("Press any key to exit.");
		System.in.read();
	}
}
