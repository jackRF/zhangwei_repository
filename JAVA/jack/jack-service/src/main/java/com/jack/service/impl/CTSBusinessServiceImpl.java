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
import com.jack.intf.observer.IPublish;

@Service
public class CTSBusinessServiceImpl extends AbstractBusinessSupport<String,Integer,String> implements ICTSBusinessService,IPublish<User, Integer>{
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
	public <R> R route(Integer actionType, String businessType, Object... params) {
		R r=null;
		if(ACTION_TYPE_MODELANDVIEW==actionType){
			r=modelAndView(businessType, params);
		}else if(ACTION_TYPE_QUERY==actionType){
			r=query(businessType, params);
		}else if(ACTION_TYPE_PROCESS==actionType){
			r=process(businessType, params);
		}else if(ACTION_TYPE_EXPORT==actionType){
			r=export(businessType, params);
		}
		return r;
	}

	@Override
	public <R, P> R publish(User supportKey, Integer type, P param, R r) {
		for(IApproveService approveService:approveServices){
			if(approveService.isSupport(supportKey)){
				r=approveService.emit(type, param, r);
			}
		}
		return r;
	}

}
