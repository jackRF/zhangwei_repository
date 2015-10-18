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
}
