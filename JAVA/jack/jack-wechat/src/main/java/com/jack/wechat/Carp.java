package com.jack.wechat;

import com.jack.wechat.entity.WechatConfiguration;

public class Carp {
	private static Carp carp;
	/**
	 * classpath 路径
	 */
	public static String CLASSPATH;
	/**
	 * 应用根目录
	 */
	public static String WEBROOT;
	/**
	 * 
	 */
	public static String contextPath;
	private  WechatCache cache;
	public static Carp getInstance() {
		if (carp == null) {
			carp = new Carp();
		}
		return carp;
	}
	public void init(WechatConfiguration config){
		cache=new WechatCache();
		cache.setConfig(config);
		cache.setAppName(contextPath);		
	}
	public WechatCache getCache() {
		return cache;
	}	
}
