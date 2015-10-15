package com.jack.web.controller;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jack.service.MyServiceImpl;
import com.jack.service.SupportUserServiceImpl;

@Controller
@RequestMapping("common")
public class CommonController implements ApplicationContextAware {

	// @Autowired
	// private MyUtils myUtils;
//	@Autowired
//	private MyServiceImpl myService;
//	@Autowired
//	private SupportUserServiceImpl supportUserService;

	@RequestMapping(value = "validateField.json")
	@ResponseBody
	public boolean validateField(String field, String value) {
		 
		MyServiceImpl supportUserService=applicationContext.getBean(MyServiceImpl.class);
		supportUserService.getUserId();
//		myService.getUserId();
//		supportUserService.getUserId();
		
		System.out.println("field:" + field);
		System.out.println("value:" + value);
		if ("username".equals(field)) {
			return "dgd".equals(value);
		}
		return false;
	}
	private ApplicationContext applicationContext;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext=applicationContext;

	}
}
