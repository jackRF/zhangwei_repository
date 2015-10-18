package com.jack.service;

import com.jack.entity.User;
import com.jack.intf.business.IBusiness;
import com.jack.intf.observer.IEmitter;

public interface IBusinessService extends IServiceConstant,IBusiness<String,Integer,String>,IEmitter<User,Integer>{
	
	
}
