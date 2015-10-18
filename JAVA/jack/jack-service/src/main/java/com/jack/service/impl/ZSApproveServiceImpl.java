package com.jack.service.impl;

import org.springframework.stereotype.Service;

import com.jack.comp.abstr.AbstractServiceObserver;
import com.jack.cts.observer.service.IApproveService;
@Service
public class ZSApproveServiceImpl extends AbstractServiceObserver<Integer> implements IApproveService {

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
