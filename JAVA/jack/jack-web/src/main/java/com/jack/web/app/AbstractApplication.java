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

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;

import com.jack.entity.User;
import com.jack.intf.business.IBusiness;
import com.jack.intf.business.IBusinessAction;

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
	private boolean isSupport(IBusinessAction<S,A,B> businessAction) {
		boolean supportFlag = false;
		for (IBusiness<S,A,B> business : businesses) {
			if (business.isSupport(businessAction)) {
				supportFlag = true;
				LOCAL_BUSINESS_ACTION.set(businessAction);
				LOCAL_BUSINESS.set(business);
				break;
			}
		}
		IBusiness.LAST_SUPPORT_RESULT.set(supportFlag);
		return supportFlag;
	}
	public boolean isSupport(HttpServletRequest request,HttpServletResponse response,IBusinessAction<S,A,B> businessAction) {
		User user =getSessionUser(request);
		boolean isSuccess = true;
		int sc=403;
		if (isSupport(businessAction)) {// 检查系统是否实现此业务
			isSuccess =checkPermission(user, businessAction);// 检查用户权限
		} else {
			sc=404;
			isSuccess = false;
		}
		if (!isSuccess) {
			initLocalBusiness(request, response,user,businessAction);
		}else{
			sendError(sc,response, user, businessAction);
		}
		return isSuccess;
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
	public User getSessionUser(HttpServletRequest request){
		return (User)request.getSession().getAttribute("user");
	}
	/**
	 * 当出现错误时，发送错误信息或重定向到首页
	 * @statusCode 状态码 404，不存在的资源，403，没有权限
	 * @param response
	 * @param user 根据用户发送信息
	 * @param businessAction 根据业务发送信息
	 */
	public abstract void sendError(int statusCode,HttpServletResponse response, User user, IBusinessAction<S, A, B> businessAction);

	public abstract void initLocalBusiness(HttpServletRequest request, HttpServletResponse response, User user,
			IBusinessAction<S, A, B> businessAction);
	/**
	 * 检查用户权限
	 * @param user 当前用户
	 * @param businessAction
	 * @return
	 */
	public abstract boolean checkPermission(User user,IBusinessAction<S, A, B> businessAction);
}
