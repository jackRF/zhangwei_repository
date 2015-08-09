package com.jack.wechat;

import java.net.MalformedURLException;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.jack.wechat.entity.WechatConfiguration;


public class WechatContextFactory {
	private static WechatContextFactory wechatContextFactory;
	private static ApplicationContext wechatContext;
	public static WechatContextFactory getInstance() {
		if(wechatContextFactory == null)
			wechatContextFactory  = new WechatContextFactory();
		return wechatContextFactory;
	}
	public void init(ServletContext servletContext) {
		wechatContext = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		Carp.CLASSPATH =  Carp.class.getClassLoader().getResource("/").getPath();
		Carp.contextPath=servletContext.getContextPath();
		try {
			Carp.WEBROOT=servletContext.getResource("/").getPath();
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Carp.getInstance().init(new WechatConfiguration());
	}
	public ApplicationContext getContext(){
		return wechatContext;
	}
}
