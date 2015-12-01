package com.jack.service;

import java.util.Map;

import com.jack.intf.business.IBusinessAction;

public interface IBusinessServiceConstant {
	/**
	 * 业务信息
	 */
	ThreadLocal<Map<String,Object>> LOCAL_BUSINESS_INFO=new ThreadLocal<Map<String,Object>>();
	ThreadLocal<IBusinessAction<String,Integer,String>> LOCAL_BUSINESS_ACTION=new ThreadLocal<IBusinessAction<String,Integer,String>>();
	
	String NS_DEFAULT="DEFAULT";
	/**
	 * Action类型-准备model获取试图
	 */
	int ACTION_TYPE_MODELANDVIEW=1;
	/**
	 * Action类型-查询
	 */
	int ACTION_TYPE_QUERY=2;
	/**
	 * Action类型-业务处理，涉及到增删改和事物操作 ,对应的service方法要有事物控制
	 */
	int ACTION_TYPE_PROCESS=3;
	/**
	 * Action类型-导出
	 */
	int ACTION_TYPE_EXPORT=4;
	/**
     * 重定向到首页
     */
    String REDIRECT_INDEX="redirect:/index.htm";
    /**
     * 重定向到无权限页
     */
    String REDIRECT_403="redirect:/403.htm";
    /**
     * 重定向到无资源页
     */
    String REDIRECT_404="redirect:/404.htm";
    /**
     * 重定向到服务器错误页
     */
    String REDIRECT_500="redirect:/500.htm";
}
