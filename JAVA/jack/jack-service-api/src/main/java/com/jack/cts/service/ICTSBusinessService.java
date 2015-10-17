package com.jack.cts.service;

import org.springframework.ui.ModelMap;

import com.jack.cts.intf.ICTSBusinessConstant;
import com.jack.entity.User;
import com.jack.intf.business.IBusiness;

public interface ICTSBusinessService extends ICTSBusinessConstant, IBusiness<String,Integer,String>{
	String doListModelAndView(User user,Integer type,ModelMap model);
	String doDetailModelAndView(User user,Integer type,ModelMap model);
}
