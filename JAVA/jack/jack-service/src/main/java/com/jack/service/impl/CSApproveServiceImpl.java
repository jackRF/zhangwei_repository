package com.jack.service.impl;

import com.jack.comp.abstr.AbstractServiceObserver;
import com.jack.cts.observer.service.IApproveService;

public class CSApproveServiceImpl extends AbstractServiceObserver<Integer> implements IApproveService{

	public CSApproveServiceImpl() {
		super("CS");
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
