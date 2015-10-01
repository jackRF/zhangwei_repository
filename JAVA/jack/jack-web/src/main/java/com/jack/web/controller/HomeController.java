package com.jack.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("home")
public class HomeController {
	@RequestMapping("/index.htm")
	public String index() {
		return "home/index";

	}
	@RequestMapping("/my/index.htm")
	public String myindex() {
		return "home/index";

	}
	@RequestMapping(value="getUserData.json",method=RequestMethod.POST)
	@ResponseBody
	public Object getUserData(){
		return null;
	}
}
