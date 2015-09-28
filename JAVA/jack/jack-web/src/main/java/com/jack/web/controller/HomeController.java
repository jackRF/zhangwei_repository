package com.jack.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jack.core.permission.Action;
import com.jack.core.permission.Modul;
import com.jack.core.permission.Option;
import com.jack.core.permission.Permission;

@Controller
@RequestMapping("home")
public class HomeController {
	
	@Permission(modul=Modul.USER,action=@Action(id=1,name="用户列表",options={@Option(id=1,name="sfdds")}))
	@RequestMapping("/index.htm")
	public String index() {
		return "home/index";

	}
}
