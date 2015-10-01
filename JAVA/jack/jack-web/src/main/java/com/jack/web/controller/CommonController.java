package com.jack.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("common")
public class CommonController {
	
	@RequestMapping(value="validateField.json")
	@ResponseBody
	public boolean validateField(String field,String value){
		System.out.println("field:"+field);
		System.out.println("value:"+value);
		if("username".equals(field)){
			return "dgd".equals(value);
		}
		return false;
	}
}
