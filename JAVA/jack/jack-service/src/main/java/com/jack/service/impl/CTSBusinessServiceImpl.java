package com.jack.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.jack.annotation.Business;
import com.jack.annotation.NameSpace;
import com.jack.comp.abstr.AbstractSmartBusinessService;
import com.jack.cts.intf.ICTSBusinessConstant;
import com.jack.entity.User;
@NameSpace(ICTSBusinessConstant.NS_CTS)
@Service
public class CTSBusinessServiceImpl extends AbstractSmartBusinessService implements ICTSBusinessConstant{
	@Business({BT_MV_LOANAPPLY_LIST,BT_MV_LOANAPPLY_DETAIL})
	public String doApproveModelAndView(User user, Integer type, ModelMap model) {
		String businessType=getBusinessType();
		if(BT_MV_LOANAPPLY_LIST.equals(businessType)){
			return "loanApply/list";
		}else if(BT_MV_LOANAPPLY_DETAIL.equals(businessType)){
			return "loanApply/detail";
		}
		throw new RuntimeException("businessType 错误 ");
	}
}
