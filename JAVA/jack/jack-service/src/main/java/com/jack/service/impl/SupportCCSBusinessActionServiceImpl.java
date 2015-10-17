package com.jack.service.impl;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Component;

import com.jack.intf.business.ICCSBusinessAction;
@Component
public class SupportCCSBusinessActionServiceImpl{
	public boolean isSupport(ICCSBusinessAction ccsBusinessAction,String nameSpace,String...businessTypes){
		if(nameSpace.equals(ccsBusinessAction.getNameSpace())){
			return ArrayUtils.contains(businessTypes, ccsBusinessAction.getBusinessType());
		}
		return false;
	}
}
