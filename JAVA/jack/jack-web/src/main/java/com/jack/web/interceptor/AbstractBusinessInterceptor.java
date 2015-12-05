package com.jack.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.jack.web.app.IApplication;

public abstract class AbstractBusinessInterceptor<BA> implements HandlerInterceptor {
	@Autowired
	private IApplication<BA> application;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (handler != null && handler instanceof HandlerMethod) {
			HandlerMethod hm = (HandlerMethod) handler;
			BA businessAction = getBusinessAction(hm);
			if (businessAction != null) {
				return application.isSupport(request,response,businessAction);
			}
		}
		return true;
	}
	protected abstract BA getBusinessAction(HandlerMethod hm);
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
