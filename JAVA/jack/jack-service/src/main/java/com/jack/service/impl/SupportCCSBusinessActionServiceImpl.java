package com.jack.service.impl;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Component;

import com.jack.intf.business.IBusinessAction;
@Component
public class SupportCCSBusinessActionServiceImpl{
	public boolean isSupport(IBusinessAction<String, Integer, String> businessAction,String nameSpace,String...businessTypes){
		if(nameSpace.equals(businessAction.getNameSpace())){
			return ArrayUtils.contains(businessTypes, businessAction.getBusinessType());
		}
		return false;
	}
}
