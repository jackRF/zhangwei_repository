package com.jack.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.jack.intf.business.ICCSBusinessAction;
import com.jack.web.annotation.BusinessAction;
import com.jack.web.annotation.Namespace;
import com.jack.web.app.CCSApplication;
@Component
public class BusinessInterceptor implements HandlerInterceptor {
	@Autowired
	private CCSApplication application;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(handler!=null&&handler instanceof HandlerMethod){
			HandlerMethod hm=(HandlerMethod)handler;
			final BusinessAction businessAction=hm.getMethodAnnotation(BusinessAction.class);
			if(businessAction!=null){
				Namespace namespace=hm.getMethodAnnotation(Namespace.class);
				if(namespace==null){
					namespace=hm.getBeanType().getAnnotation(Namespace.class);
				}
				int actionType=businessAction.actionType();
				if(actionType<CCSApplication.ACTION_TYPE_MODELANDVIEW||actionType>CCSApplication.ACTION_TYPE_EXPORT){
					actionType=calculateActionType(hm.getReturnType().getParameterType()
							,hm.getMethodAnnotation(ResponseBody.class)!=null
							,hm.getMethodAnnotation(RequestMapping.class));
				}
				final int at=actionType;
				final String ns=namespace!=null?namespace.value():"DEFAULT";
				return application.isSupport(new ICCSBusinessAction(){

					@Override
					public String getNameSpace() {
						return ns;
					}

					@Override
					public Integer getActionType() {
						return at;
					}

					@Override
					public String getBusinessType() {
						return businessAction.businessType();
					}
					
				});
			}
			
		}
		return true;
	}
	private int calculateActionType(Class<?> returnType,boolean hasResponseBody,RequestMapping requestMapping){
		if(returnType==null){
			return CCSApplication.ACTION_TYPE_EXPORT;
		}
		if(!hasResponseBody){
			if(String.class.equals(returnType)){
				return CCSApplication.ACTION_TYPE_MODELANDVIEW;
			}
		}else{
			String[] mappings=requestMapping.value();
			boolean queryFlag=false;
			for(String mapping:mappings){
				if(mapping!=null&&(mapping.contains("query")||mapping.contains("Query"))){
					queryFlag=true;
					break;
				}
			}
			return queryFlag?CCSApplication.ACTION_TYPE_QUERY:CCSApplication.ACTION_TYPE_PROCESS;
		}
		return 0;
	}
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}

}
