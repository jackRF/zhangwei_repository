package com.jack.web.direct.action;

import com.jack.direct.annotation.DirectAction;
import com.jack.direct.annotation.DirectMethod;

@DirectAction
public class Product {
	@DirectMethod
	public String hello(String name){
		return "hello "+name;
	}
	@DirectMethod
	public int aplusb(int[] ab){
		return ab[0]+ab[1];
	}
}
