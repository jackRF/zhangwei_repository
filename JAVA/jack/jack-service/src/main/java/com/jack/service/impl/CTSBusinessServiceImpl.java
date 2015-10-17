package com.jack.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.jack.cts.service.ICTSBusinessService;
import com.jack.entity.User;
import com.jack.intf.business.ICCSBusinessAction;

@Service
public class CTSBusinessServiceImpl extends SupportCCSBusinessActionServiceImpl implements ICTSBusinessService{

	@Override
	public <R> R modelAndView(String businessType, Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <R> R query(String businessType, Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <R> R process(String businessType, Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <R> R export(String businessType, Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String doListModelAndView(User user, Integer type, ModelMap model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String doDetailModelAndView(User user, Integer type, ModelMap model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isSupport(ICCSBusinessAction supportKey) {
		return super.isSupport(supportKey,NS_CTS, BT_MV_LOANAPPLY_LIST,BT_MV_LOANAPPLY_DETAIL);
	}

}
