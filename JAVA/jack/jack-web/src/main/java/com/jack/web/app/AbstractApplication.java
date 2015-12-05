/*
 * Copyright (C), 2014-2015, 达信财富投资管理（上海）有限公司
 * FileName: AbstractApplication.java
 * Author:   zhangwei
 * Date:     2015年10月16日 上午11:40:32
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.jack.web.app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;

import com.jack.entity.User;
/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *
 * @author zhangwei
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 * @param <S> 业务命名空间
 * @param <A> Action类型
 * @param <B> 具体业务类型
 */
public  abstract class AbstractApplication<BA> implements IApplication<BA>,ApplicationContextAware{
	
	protected Logger logger=LoggerFactory.getLogger(getClass());
	
	private ApplicationContext applicationContext;

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
	
	public User getSessionUser(HttpServletRequest request){
		return (User)request.getSession().getAttribute("user");
	}
	
	@Override
	public boolean isSupport(HttpServletRequest request,HttpServletResponse response,BA businessAction) {
		User user =getSessionUser(request);
		boolean isSuccess = true;
		int sc=403;
		if (isSupport(businessAction)) {// 检查系统是否实现此业务
			isSuccess =checkPermission(user, businessAction);// 检查用户权限
		} else {
			sc=404;
			isSuccess = false;
		}
		if (isSuccess) {
			initLocalBusiness(request, response,user,businessAction);
		}else{
			sendError(sc,response, user, businessAction);
		}
		return isSuccess;
	}	
	protected abstract <R> R isSupport(BA businessAction);
	/**
	 * 检查用户权限
	 * @param user 当前用户
	 * @param businessAction
	 * @return
	 */
	protected abstract boolean checkPermission(User user,BA businessAction);
	protected abstract void initLocalBusiness(HttpServletRequest request, HttpServletResponse response, User user,
			BA businessAction);
	/**
	 * 当出现错误时，发送错误信息或重定向到首页
	 * @statusCode 状态码 404，不存在的资源，403，没有权限
	 * @param response
	 * @param user 根据用户发送信息
	 * @param businessAction 根据业务发送信息
	 */
	protected abstract void sendError(int statusCode,HttpServletResponse response, User user, BA businessAction);
	
}
