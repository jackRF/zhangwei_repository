package com.jack.comp.abstr;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import com.jack.annotation.Business;
import com.jack.annotation.NameSpace;
import com.jack.intf.business.IBusinessAction;
import com.jack.service.IBusinessService;

public abstract class AbstractSmartBusinessService implements IBusinessService{
	private String nameSpace;
	private Map<String,Method> handlerMethodMap=new HashMap<String,Method>();
	public AbstractSmartBusinessService(){
		Class<?> c=getClass();
		NameSpace ns=c.getAnnotation(NameSpace.class);
		if(ns!=null){
			nameSpace=ns.value();
		}else{
			nameSpace=NS_DEFAULT;
		}
		Method[] methods=c.getDeclaredMethods();
		if(methods!=null&&methods.length>0){
			for(Method method:methods){
				Business business=method.getAnnotation(Business.class);
				if(business!=null){
					handlerMethodMap.put(business.value(), method);
				}
			}
		}
	}
	@Override
	public boolean isSupport(IBusinessAction<String, Integer, String> supportKey) {
		if(supportKey.getNameSpace().equals(nameSpace)){
			return handlerMethodMap.get(supportKey.getBusinessType())!=null;
		}
		return false;
	}
	@Override
	public <R> R modelAndView(String businessType, Object... params) {
		return invoke(handlerMethodMap.get(businessType),params);
	}

	@Override
	public <R> R query(String businessType, Object... params) {
		return invoke(handlerMethodMap.get(businessType),params);
	}
	@Transactional
	@Override
	public <R> R process(String businessType, Object... params) {
		return invoke(handlerMethodMap.get(businessType),params);
	}

	@Override
	public <R> R export(String businessType, Object... params) {
		return invoke(handlerMethodMap.get(businessType),params);
	}
	@SuppressWarnings("unchecked")
	private <R> R invoke(Method handlerMethod, Object...params){
		try {
			ReflectionUtils.makeAccessible(handlerMethod);
			return (R) handlerMethod.invoke(this, params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
}