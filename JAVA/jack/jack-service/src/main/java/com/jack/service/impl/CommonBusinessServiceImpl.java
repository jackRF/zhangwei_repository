package com.jack.service.impl;

import org.springframework.stereotype.Service;

import com.jack.comp.abstr.AbstractBusinessService;
import com.jack.intf.business.IBusinessAction;
import com.jack.service.IBusinessService;
@Service
public class CommonBusinessServiceImpl extends AbstractBusinessService implements IBusinessService {

	@Override
	public boolean isSupport(IBusinessAction<String, Integer, String> supportKey) {
		// TODO Auto-generated method stub
		return super.isSupport(supportKey, "common", "BT_QUERY_USER_LIST","BT_MV_USER_LIST");
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <R> R export(String businessType, Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
