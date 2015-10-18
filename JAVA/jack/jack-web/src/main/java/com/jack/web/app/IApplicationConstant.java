package com.jack.web.app;

import com.jack.service.IServiceConstant;

public interface IApplicationConstant extends IServiceConstant{
	/**
	 * 默认业务命名空间
	 */
	String DEFAULT_NAMESPACE="DEFAULT";
	/**
	 * Application 支持的操作的类型(ActionType),Business拦截器中用到
	 */
	int[] SUPPORT_ACTION_TYPES={ACTION_TYPE_MODELANDVIEW,ACTION_TYPE_QUERY,ACTION_TYPE_PROCESS,ACTION_TYPE_EXPORT};
//	/**
//	 * Action类型-准备model获取试图
//	 */
//	int ACTION_TYPE_MODELANDVIEW=1;
//	/**
//	 * Action类型-查询
//	 */
//	int ACTION_TYPE_QUERY=2;
//	/**
//	 * Action类型-业务处理，涉及到增删改和事物操作 ,对应的service方法要有事物控制
//	 */
//	int ACTION_TYPE_PROCESS=3;
//	/**
//	 * Action类型-导出
//	 */
//	int ACTION_TYPE_EXPORT=4;
}
