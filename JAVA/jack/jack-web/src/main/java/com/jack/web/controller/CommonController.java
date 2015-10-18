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
import com.jack.web.annotation.BusinessAction;
import com.jack.web.annotation.Namespace;
import com.jack.web.app.CCSApplication;

@Controller
@Namespace("common")
@RequestMapping("common")
public class CommonController implements ApplicationContextAware {

	// @Autowired
	// private MyUtils myUtils;
//	@Autowired
//	private MyServiceImpl myService;
//	@Autowired
//	private SupportUserServiceImpl supportUserService;
	@BusinessAction(businessType=CCSApplication.BT_VALIDATE_FIELD)
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
	@BusinessAction(businessType=CCSApplication.BT_QUERY_USER_LIST)
	@RequestMapping(value = "queryUserList.json")
	@ResponseBody
	public boolean queryUser(){
		return false;
	}
	@BusinessAction(businessType=CCSApplication.BT_EXPORTUSER)
	@RequestMapping(value = "exportUser.json")
	public void exportUser(){
	}
	@BusinessAction(businessType=CCSApplication.BT_MODELANDVIEW_USERLIST)
	@RequestMapping(value = "userList.json")
	public String userList(){
		return "user_list";
	}
	private ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext=applicationContext;

	}
}
