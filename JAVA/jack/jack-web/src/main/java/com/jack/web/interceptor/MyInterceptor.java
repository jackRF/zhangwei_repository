package com.jack.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
@Component
public class MyInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
//		System.out.println("preHandle...");
//		if(handler!=null){
//			HandlerMethod han=(HandlerMethod)handler;
//			System.out.println(han.getBeanType().getName());
//			System.out.println(han.getMethod().getName());
//			System.out.println("handler Class:"+handler.getClass().getName());
//			if(han.getMethodAnnotation(ResponseBody.class)!=null){
////				response.setStatus(403);
//				response.sendError(403);
//				return false;
//			}
//		}else{
//			System.out.println("handler is null");
//		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("postHandle...");
		
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("afterCompletion...");
		
	}

}
