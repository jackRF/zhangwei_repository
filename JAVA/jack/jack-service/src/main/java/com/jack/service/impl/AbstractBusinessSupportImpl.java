package com.jack.service.impl;

import org.apache.commons.lang3.ArrayUtils;
import com.jack.intf.business.IBusinessAction;

public abstract class AbstractBusinessSupportImpl<S,A,B>{
	@SuppressWarnings("unchecked")
	public boolean isSupport(IBusinessAction<S,A,B> businessAction,S nameSpace,B...businessTypes){
		if(nameSpace.equals(businessAction.getNameSpace())){
			return ArrayUtils.contains(businessTypes, businessAction.getBusinessType());
		}
		return false;
	}
}
