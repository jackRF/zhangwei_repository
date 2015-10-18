package com.jack.web.app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.jack.entity.User;
import com.jack.intf.business.IBusiness;
import com.jack.intf.business.IBusinessAction;
import com.jack.service.IBusinessService;

public class CCSApplication extends AbstractApplication<String, Integer, String>implements IApplicationConstant {
	@Autowired
	private List<IBusinessService> businessServices=new ArrayList<IBusinessService>();
	@Override
	public <R> R doBusiness(Object... params){
		IBusinessAction<String, Integer, String> businessAction=LOCAL_BUSINESS_ACTION.get();
		boolean supportFlag=false;
		R r=null;
		for (IBusiness<String, Integer, String> business : businessServices) {
			if (business.isSupport(businessAction)) {
				supportFlag=true;
				IBusiness.LAST_SUPPORT_RESULT.set(true);
				r=doBusiness(business,businessAction, params);
				break;
			}
		}
		if(!supportFlag){
			IBusiness.LAST_SUPPORT_RESULT.set(false);
		}
		return r;
	}
	@SuppressWarnings("unchecked")
	@Override
	protected <R> R doBusiness(IBusiness<String, Integer, String> business, IBusinessAction<String, Integer, String> businessAction,
			Object... params) {
		Integer actionType=businessAction.getActionType();
		String businessType=businessAction.getBusinessType();
		R result=business.route(actionType, businessType, params);
		if(!Boolean.TRUE.equals(IBusiness.LAST_SUPPORT_RESULT.get())){
			Map<String,Object> info=IBusiness.LOCAL_BUSINESS_INFO.get();
			sendError(403,(HttpServletResponse)info.get("response"),(User)info.get("user"),(IBusinessAction<String, Integer, String>)info.get("businessAction"));
		}
		return result;
	}

	@Override
	public void sendError(int statusCode, HttpServletResponse response, User user,
			IBusinessAction<String, Integer, String> businessAction) {
		try {
			response.sendError(statusCode);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void initLocalBusiness(HttpServletRequest request, HttpServletResponse response, User user,
			IBusinessAction<String, Integer, String> businessAction) {
		Map<String,Object> businessInfo=new HashMap<String,Object>();
		businessInfo.put("request", request);
		businessInfo.put("response", response);
		businessInfo.put("user", user);
		businessInfo.put("businessAction", businessAction);
		IBusiness.LOCAL_BUSINESS_INFO.set(businessInfo);
	}

	@Override
	public boolean checkPermission(User user, IBusinessAction<String, Integer, String> businessAction) {
		// TODO Auto-generated method stub
		return true;//这里应该根据AM配置的用户的实际权限去检查，默认true，并不代表真的有权限操作，只是让它接着往下执行，在观察者模式地方会返回空或不处理
	}
	
	@Override
	protected boolean isSupport(IBusinessAction<String, Integer, String> businessAction) {
		boolean supportFlag = false;
		for (IBusiness<String, Integer, String> business : businessServices) {
			if (business.isSupport(businessAction)) {
				supportFlag = true;
				IBusiness.LAST_SUPPORT_RESULT.set(true);
				LOCAL_BUSINESS_ACTION.set(businessAction);
				break;
			}
		}
		if(!supportFlag){
			IBusiness.LAST_SUPPORT_RESULT.set(false);
		}
		return supportFlag;
	}
}
