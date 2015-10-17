package com.jack.web.app;

import com.jack.intf.business.IBusiness;

public class Application extends AbstractApplication<String,Integer,String> implements IActionType{

	@Override
	protected <R> R doBusiness(IBusiness<String, Integer, String> business, Integer actionType, String businessType,Object... params) {
		R result=null;
		if (ACTION_TYPE_MODELANDVIEW == actionType) {
			result = business.modelAndView(businessType, params);
		} else if (ACTION_TYPE_QUERY == actionType) {
			result = business.query(businessType, params);
		} else if (ACTION_TYPE_PROCESS == actionType) {
			result = business.process(businessType, params);
		} else if (ACTION_TYPE_EXPORT == actionType) {
			result = business.export(businessType, params);
		} else {
			throw new IllegalArgumentException("ActionType 配置错误");
		}
		return result;
	}

}
