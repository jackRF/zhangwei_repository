package com.jack.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.jack.comp.abstr.AbstractBusinessSupport;
import com.jack.cts.observer.service.IApproveService;
import com.jack.cts.service.ICTSBusinessService;
import com.jack.entity.User;
import com.jack.intf.business.IBusinessAction;

@Service
public class CTSBusinessServiceImpl extends AbstractBusinessSupport<String,Integer,String> implements ICTSBusinessService{
	@Autowired
	private List<IApproveService> approveServices=new ArrayList<IApproveService>();
	@Override
	public <R> R modelAndView(String businessType, Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <R> R query(String businessType, Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <R> R process(String businessType, Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <R> R export(String businessType, Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String doListModelAndView(User user, Integer type, ModelMap model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String doDetailModelAndView(User user, Integer type, ModelMap model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isSupport(IBusinessAction<String, Integer, String> supportKey) {
		return super.isSupport(supportKey,NS_CTS, BT_MV_LOANAPPLY_LIST,BT_MV_LOANAPPLY_DETAIL);
	}

	@Override
	public <P, R> R emit(User user,Integer type,P param, R r) {
		boolean isSupport=false;
		for(IApproveService approveService:approveServices){
			if(approveService.isSupport(user)){
				Integer actionType=LOCAL_ACTION_TYPE.get();
				if(ACTION_TYPE_MODELANDVIEW==actionType){
					r=approveService.modelAndView(type, param);
				}else if(ACTION_TYPE_QUERY==actionType){
					r=approveService.query(actionType, param, r);
				}else if(ACTION_TYPE_PROCESS==actionType){
					r=approveService.process(actionType, param, r);
				}else if(ACTION_TYPE_PROCESS==actionType){
					r=approveService.process(actionType, param, r);
				}
				isSupport=true;
				break;
			}
		}
		LAST_SUPPORT_RESULT.set(isSupport);
		return r;
	}

}
