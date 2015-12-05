package com.jack.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jack.web.annotation.BusinessAction;
import com.jack.web.annotation.Namespace;
import com.jack.web.app.CCSApplication;

@Namespace(CCSApplication.NS_CTS)
@Controller
@RequestMapping("creditAudit")
public class CreditAuditController {
	@Autowired
	private CCSApplication application;
	@BusinessAction(businessType=CCSApplication.BT_QUERY_LOANAPPLY_LIST)
	@ResponseBody
	@RequestMapping("queryInfo.htm")
	public Map<String,Object> queryInfo(Long loanApplyId){
		return application.doBusiness(loanApplyId);
	}
}