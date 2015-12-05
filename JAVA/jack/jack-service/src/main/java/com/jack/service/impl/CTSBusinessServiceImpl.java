package com.jack.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.jack.annotation.Business;
import com.jack.annotation.NameSpace;
import com.jack.comp.abstr.AbstractBusinessService;
import com.jack.cts.intf.ICTSBusinessConstant;
import com.jack.entity.User;
import com.jack.service.IUserService;
@NameSpace(ICTSBusinessConstant.NS_CTS)
@Service
public class CTSBusinessServiceImpl extends AbstractBusinessService implements ICTSBusinessConstant{
	@Autowired
	private IUserService userSerice;
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
	@Business(BT_QUERY_LOANAPPLY_LIST)
	public Map<String,Object> doApproveQuery(Long loanApplyId){
		Map<String,Object> result=new HashMap<String,Object>();
		result.put("success", true);
		return result;
	}
	@Business(BT_PROCESS_USER_SAVE)
	public Map<String,Object> processApprove(Integer o,Object...ids){
		System.out.println("processApprove....");
		System.out.println("o:"+o);
		for(Object id:ids){
			System.out.println("id:"+id);
		}
		Map<String,Object> result=new HashMap<String,Object>();
		result.put("success", true);
		User user=new User();
		user.setName("2015120501");
		user.setPwd("sfsfssdf");
		user.setUserName("jack.com");
		userSerice.saveUser(user);
		System.out.println("saveUser success");
		result.put("user", user);
//		throw new RuntimeException("事物测试");
		return result;
	}
	
}
