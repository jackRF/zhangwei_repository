package com.jack.cts.service;

import org.springframework.ui.ModelMap;

import com.jack.entity.User;
import com.jack.intf.business.IBusiness;
import com.jack.intf.business.ICTSBusiness;

public interface ICTSBusinessService extends ICTSBusiness, IBusiness<String,Integer,String>{
	String doListModelAndView(User user,Integer type,ModelMap model);
	String doDetailModelAndView(User user,Integer type,ModelMap model);
}
