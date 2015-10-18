package com.jack.service;

public interface IServiceConstant {
	final ThreadLocal<Integer> LOCAL_ACTION_TYPE=new ThreadLocal<Integer>();
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
