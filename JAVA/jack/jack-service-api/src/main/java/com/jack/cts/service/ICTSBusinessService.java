package com.jack.cts.service;

import org.springframework.ui.ModelMap;

import com.jack.entity.User;
import com.jack.intf.business.ICTSBusiness;
import com.jack.service.ICCSBusinessService;

public interface ICTSBusinessService extends ICTSBusiness, ICCSBusinessService{
	String doListModelAndView(User user,Integer type,ModelMap model);
	String doDetailModelAndView(User user,Integer type,ModelMap model);
}
