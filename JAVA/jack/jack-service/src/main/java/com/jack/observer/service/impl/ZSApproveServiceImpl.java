package com.jack.observer.service.impl;

import org.springframework.stereotype.Service;

import com.jack.comp.abstr.AbstractObserverService;
import com.jack.cts.observer.service.IApproveObserverService;
@Service
public class ZSApproveServiceImpl extends AbstractObserverService<Integer> implements IApproveObserverService {

	public ZSApproveServiceImpl() {
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
