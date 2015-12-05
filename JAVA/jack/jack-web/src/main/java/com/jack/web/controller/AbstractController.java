package com.jack.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.jack.intf.business.IBusinessAction;
import com.jack.web.app.IApplication;

public abstract class AbstractController{
	
	protected Logger logger=LoggerFactory.getLogger(getClass());
	
	@Autowired
	protected IApplication<IBusinessAction<String,Integer,String>> application;
}
