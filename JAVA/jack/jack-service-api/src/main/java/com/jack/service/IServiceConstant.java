package com.jack.service;

import com.jack.intf.business.IBusinessAction;

public interface IServiceConstant {
	ThreadLocal<IBusinessAction<String,Integer,String>> LOCAL_BUSINESS_ACTION=new ThreadLocal<IBusinessAction<String,Integer,String>>();
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
}