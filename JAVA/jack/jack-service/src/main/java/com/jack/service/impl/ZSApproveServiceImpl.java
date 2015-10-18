package com.jack.service.impl;

import com.jack.comp.abstr.AbstractUserSupport;
import com.jack.cts.observer.service.IApproveService;
import com.jack.entity.User;

public class ZSApproveServiceImpl extends AbstractUserSupport implements IApproveService {

	@Override
	public <R,M> R modelAndView(Integer type, M model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <P, R> R query(Integer type, P param, R r) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <P, R> R process(Integer type, P param, R r) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <P, R> R export(Integer type, P param, R r) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isSupport(User supportKey) {
		return super.isSupport(supportKey, "CS");
	}
}
