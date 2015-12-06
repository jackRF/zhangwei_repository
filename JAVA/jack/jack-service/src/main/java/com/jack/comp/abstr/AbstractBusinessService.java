package com.jack.comp.abstr;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import com.jack.annotation.Business;
import com.jack.annotation.NameSpace;
import com.jack.intf.business.IBusinessAction;
import com.jack.service.IBusinessService;

public abstract class AbstractBusinessService implements IBusinessService{
	private String nameSpace;
	private Map<String,Method> handlerMethodMap=new HashMap<String,Method>();
	public AbstractBusinessService(){
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
					String[] values=business.value();
					for(String value:values){
						handlerMethodMap.put(value, method);
					}
					
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
	public final <R> R modelAndView(String businessType, Object... params) {
		return invoke(businessType,params);
	}

	@Override
	public final <R> R query(String businessType, Object... params) {
		return invoke(businessType,params);
	}
	@Transactional
	@Override
	public final <R> R process(String businessType, Object... params) {
		return invoke(businessType,params);
	}

	@Override
	public final <R> R export(String businessType, Object... params) {
		return invoke(businessType,params);
	}	
	@SuppressWarnings("unchecked")
	private <R> R invoke(String businessType, Object...params){
		try {
			Method handlerMethod=handlerMethodMap.get(businessType);
			ReflectionUtils.makeAccessible(handlerMethod);
			Class<?>[] parameterTypes=handlerMethod.getParameterTypes();
			int ptln=parameterTypes.length;
			if(ptln>0){
				Class<?> parameterClass=parameterTypes[ptln-1];
				if(parameterClass.isArray()&&Object.class.equals(parameterClass.getComponentType())){
					if(params.length>=ptln){
						Object[] temp=params;
						params=Arrays.copyOfRange(temp, 0, ptln);
						params[ptln-1]=Arrays.copyOfRange(temp, ptln-1, temp.length);
					}else if(params.length==ptln-1){
						params=Arrays.copyOf(params, ptln);
						params[ptln-1]=new Object[0];
					}
				}
			}
			return (R) handlerMethod.invoke(this, params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	protected String getBusinessType(){
		return LOCAL_BUSINESS_ACTION.get().getBusinessType();
	}
}
