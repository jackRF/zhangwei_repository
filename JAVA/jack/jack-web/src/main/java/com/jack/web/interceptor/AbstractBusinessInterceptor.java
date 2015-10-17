package com.jack.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.jack.intf.business.IBusinessAction;
import com.jack.web.app.AbstractApplication;


public abstract class AbstractBusinessInterceptor<S, A, B> implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (handler != null && handler instanceof HandlerMethod) {
			HandlerMethod hm = (HandlerMethod) handler;
			IBusinessAction<S, A, B> businessAction = getBusinessAction(hm);
			if (businessAction != null) {
				return getApplication().isSupport(request,response,businessAction);
			}
		}
		return true;
	}
	
	
	protected abstract AbstractApplication<S, A, B> getApplication();
	protected abstract IBusinessAction<S, A, B> getBusinessAction(HandlerMethod hm);
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
