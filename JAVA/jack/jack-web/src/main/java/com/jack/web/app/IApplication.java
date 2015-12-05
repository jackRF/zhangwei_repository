package com.jack.web.app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jack.cts.intf.ICTSBusinessConstant;
import com.jack.service.IBusinessServiceConstant;

public interface IApplication<BA> extends IBusinessServiceConstant,ICTSBusinessConstant{
	/**
	 * Application 支持的操作的类型(ActionType),Business拦截器中用到
	 */
	int[] SUPPORT_ACTION_TYPES={ACTION_TYPE_MODELANDVIEW,ACTION_TYPE_QUERY,ACTION_TYPE_PROCESS,ACTION_TYPE_EXPORT};

	boolean isSupport(HttpServletRequest request, HttpServletResponse response,
			BA businessAction);

	<R> R doBusiness(Object...params);

	<CB,R> CB doBusiness(ICallBack<R> callBack, Object...params);
}
