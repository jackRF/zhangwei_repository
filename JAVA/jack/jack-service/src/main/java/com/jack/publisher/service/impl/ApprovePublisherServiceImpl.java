package com.jack.publisher.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.jack.comp.abstr.AbstractPublisherService;
import com.jack.cts.observer.service.IApproveObserverService;
import com.jack.cts.publisher.service.IApprovePublisherService;
import com.jack.entity.User;

public class ApprovePublisherServiceImpl extends AbstractPublisherService<User,Integer,IApproveObserverService> implements IApprovePublisherService {
	@Autowired
	private List<IApproveObserverService> approveObserverServices;
	@Override
	protected List<IApproveObserverService> getObservers() {
		return approveObserverServices;
	}

}
