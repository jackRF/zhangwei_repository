package com.jack.web.controller;

import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jack.service.MyServiceImpl;
import com.jack.web.annotation.Namespace;

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
	@RequestMapping(value = "userList.htm")
	public String userList(){
		System.out.println("userList");
		return "index";
	}
	
	@RequestMapping(value = "queryUserList.json")
	@ResponseBody
	public boolean queryUser(@RequestBody List<Long> userIds){
		System.out.println("4756878677888");
		if(!CollectionUtils.isEmpty(userIds)){
			System.out.println("dsfgdsuseridsss");
			System.out.println(userIds);
//			for(User user:users){
//				System.out.println(JSON.toJSONString(user));
//			}
		}else{
			System.out.println("users is empty");
		}
		System.out.println("queryUser");
		return false;
	}
	@RequestMapping(value = "exportUser.json")
	public void exportUser(){
	}
	
	private ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext=applicationContext;

	}
}
