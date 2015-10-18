package com.jack.web.app;

import com.jack.cts.intf.ICTSBusinessConstant;
import com.jack.service.IServiceConstant;

public interface IApplicationConstant extends IServiceConstant,ICTSBusinessConstant{
	String BT_VALIDATE_FIELD="BT_VALIDATE_FIELD";
	String BT_QUERY_USER_LIST="BT_QUERY_USER_LIST";
	String BT_EXPORTUSER="BT_EXPORTUSER";
	String BT_MODELANDVIEW_USERLIST="BT_MODELANDVIEW_USERLIST";
	/**
	 * 默认业务命名空间
	 */
	String DEFAULT_NAMESPACE="DEFAULT";
	/**
	 * Application 支持的操作的类型(ActionType),Business拦截器中用到
	 */
	int[] SUPPORT_ACTION_TYPES={ACTION_TYPE_MODELANDVIEW,ACTION_TYPE_QUERY,ACTION_TYPE_PROCESS,ACTION_TYPE_EXPORT};
}
