package com.jack.cts.service;

import org.springframework.ui.ModelMap;

import com.jack.cts.intf.ICTSBusinessConstant;
import com.jack.entity.User;
import com.jack.service.IBusinessService;

public interface ICTSBusinessService extends ICTSBusinessConstant, IBusinessService{
	String doListModelAndView(User user,Integer type,ModelMap model);
	String doDetailModelAndView(User user,Integer type,ModelMap model);
}
