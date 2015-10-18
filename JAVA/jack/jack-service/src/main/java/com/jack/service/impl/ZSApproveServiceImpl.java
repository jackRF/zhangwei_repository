package com.jack.service.impl;

import com.jack.comp.abstr.AbstractObserver;
import com.jack.cts.observer.service.IApproveService;

public class ZSApproveServiceImpl extends AbstractObserver<Integer> implements IApproveService {

	protected ZSApproveServiceImpl() {
		super("ZS");
	}

	@Override
	public <R,P> R modelAndView(Integer type, P model,R r) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <R,P> R query(Integer type, P param, R r) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <R,P> R process(Integer type, P param, R r) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <R,P> R export(Integer type, P param, R r) {
		// TODO Auto-generated method stub
		return null;
	}
}
