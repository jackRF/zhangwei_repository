package com.jack.web.app;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;

import com.jack.intf.business.IBusiness;
import com.jack.intf.business.IBusinessAction;
import com.jack.intf.business.IBusinessCallBack;

public  abstract class AbstractApplication<S,A,B> implements ApplicationContextAware{

	protected final ThreadLocal<IBusinessAction<S,A,B>> LOCAL_BUSINESS_ACTION = new ThreadLocal<IBusinessAction<S,A,B>>();
	private final ThreadLocal<IBusiness<S,A,B>> LOCAL_BUSINESS = new ThreadLocal<IBusiness<S,A,B>>();

	private ApplicationContext applicationContext;
	@Autowired
	private List<IBusiness<S,A,B>> businesses = new ArrayList<IBusiness<S,A,B>>();

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public <T> T getBean(Class<T> requiredType) {
		return applicationContext.getBean(requiredType);
	}

	@SuppressWarnings("unchecked")
	public <T> T getBean(String name) {
		return (T) applicationContext.getBean(name);
	}

	public void publishEvent(ApplicationEvent event) {
		applicationContext.publishEvent(event);
	}

	public boolean isSupport(IBusinessAction<S,A,B> businessAction) {
		boolean supportFlag = false;
		IBusiness<S,A,B> localBusiness = null;
		for (IBusiness<S,A,B> business : businesses) {
			if (business.isSupport(businessAction)) {
				supportFlag = true;
				localBusiness = business;
				LOCAL_BUSINESS_ACTION.set(businessAction);
				LOCAL_BUSINESS.set(localBusiness);
				break;
			}
		}
		return supportFlag;
	}

	public <R> R doBusiness(Object... params) {
		IBusinessAction<S,A,B> businessAction = LOCAL_BUSINESS_ACTION.get();
		IBusiness<S,A,B> business= LOCAL_BUSINESS.get();
		if (businessAction == null || business == null) {
			return null;
		}
		A actionType = businessAction.getActionType();
		B businessType = businessAction.getBusinessType();
		return doBusiness(business, actionType, businessType,params);
	}
	protected abstract <R> R doBusiness(IBusiness<S,A,B> business,A actionType,B businessType,Object... params);

	public <CB,R> CB doBusiness(IBusinessCallBack<R> callBack, Object... params) {
		R result = doBusiness(params);
		return callBack.callBack(result);
	}

}
