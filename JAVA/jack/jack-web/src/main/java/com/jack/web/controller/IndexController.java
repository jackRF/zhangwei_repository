package com.jack.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
	
	@RequestMapping(value="/index3.htm",method=RequestMethod.GET)
	public String index() {
		System.out.println("sfdsfs");
		return "index";
	}
}
