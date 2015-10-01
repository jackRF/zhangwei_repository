package com.jack.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
	@RequestMapping("/my/index.htm")
	public String myindex() {
		return "home/index";

	}
	@RequestMapping(value="getUserData.json",method=RequestMethod.POST)
	@ResponseBody
	public Object getUserData(){
		return null;
	}
	@RequestMapping("userEdit.htm")
	public String userEdit(){
		System.out.println("userEdit()");
		return "home/userEdit";
	}
}
