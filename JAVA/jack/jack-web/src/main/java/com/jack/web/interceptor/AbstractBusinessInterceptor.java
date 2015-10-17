package com.jack.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.jack.entity.User;
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
				User user = getSessionUser(request);
				boolean isSuccess = true;
				int sc=403;
				if (getApplication().isSupport(businessAction)) {// 检查系统是否实现此业务
					isSuccess = checkPermission(user, businessAction);// 检查用户权限
				} else {
					sc=404;
					isSuccess = false;
				}
				if (!isSuccess) {
					sendError(sc,response, user, businessAction);
				}
				return isSuccess;
			}
		}
		return true;
	}
	private User getSessionUser(HttpServletRequest request){
		return (User)request.getSession().getAttribute("user");
	}
	
	protected abstract AbstractApplication<S, A, B> getApplication();
	protected abstract IBusinessAction<S, A, B> getBusinessAction(HandlerMethod hm);
	/**
	 * 检查用户权限
	 * @param user 当前用户
	 * @param businessAction
	 * @return
	 */
	protected abstract boolean checkPermission(User user,IBusinessAction<S, A, B> businessAction);
	/**
	 * 当出现错误时，发送错误信息或重定向到首页
	 * @statusCode 状态码 404，不存在的资源，403，没有权限
	 * @param response
	 * @param user 根据用户发送信息
	 * @param businessAction 根据业务发送信息
	 */
	protected abstract void sendError(int statusCode,HttpServletResponse response, User user, IBusinessAction<S, A, B> businessAction);
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
