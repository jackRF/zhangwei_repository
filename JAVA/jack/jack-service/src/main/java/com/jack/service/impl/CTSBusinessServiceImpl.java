package com.jack.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.jack.comp.abstr.AbstractBusinessService;
import com.jack.cts.intf.ICTSBusinessConstant;
import com.jack.cts.publisher.service.IApprovePublisherService;
import com.jack.entity.User;
import com.jack.intf.business.IBusinessAction;

@Service
public class CTSBusinessServiceImpl extends AbstractBusinessService implements ICTSBusinessConstant{
	@Autowired
	private IApprovePublisherService approvePublisherService;
	@Override
	public boolean isSupport(IBusinessAction<String, Integer, String> supportKey) {
		return super.isSupport(supportKey,NS_CTS, BT_MV_LOANAPPLY_LIST,BT_MV_LOANAPPLY_DETAIL);
	}
	
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
		return null;
	}

	@Override
	public <R> R export(String businessType, Object... params) {
		return null;
	}

	public String doListModelAndView(User user, Integer type, ModelMap model) {
		return null;
	}

	public String doDetailModelAndView(User user, Integer type, ModelMap model) {
		return null;
	}

	
}
