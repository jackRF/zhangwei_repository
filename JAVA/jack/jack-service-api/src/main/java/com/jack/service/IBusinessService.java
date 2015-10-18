package com.jack.service;

import com.jack.intf.business.IBusiness;
import com.jack.intf.business.IBusinessAction;

public interface IBusinessService extends IServiceConstant,IBusiness<String,Integer,String>{
	ThreadLocal<IBusinessAction<String,Integer,String>> LOCAL_BUSINESS_ACTION=new ThreadLocal<IBusinessAction<String,Integer,String>>();
}
