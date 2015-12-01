package com.jack.web.app;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jack.entity.User;
import com.jack.intf.business.IBusiness;
import com.jack.intf.business.IBusinessAction;
import com.jack.service.IBusinessService;
@Component
public class CCSApplication extends AbstractApplication<String, Integer, String>implements IApplicationConstant {
	@Autowired
	private List<IBusinessService> businessServices;
	@Override
	public <R> R doBusiness(final Object... params){
		
		 final IBusinessAction<String, Integer, String> businessAction=LOCAL_BUSINESS_ACTION.get();
		 return isSupport(new ICallBack<IBusiness<String,Integer,String>>() {
			@Override
			public <B> B callBack(IBusiness<String, Integer, String> business) {
				return doBusiness(business,businessAction, params);
			}
		}, businessAction,null);
	}
	private <R> R doBusiness(IBusiness<String, Integer, String> business, IBusinessAction<String, Integer, String> businessAction,
			Object... params) {
		Integer actionType=businessAction.getActionType();
		String businessType=businessAction.getBusinessType();
		R result=route(business,actionType, businessType, params);
		Map<String,Object> info=LOCAL_BUSINESS_INFO.get();
		if(!Boolean.TRUE.equals(IBusiness.LAST_SUPPORT_RESULT.get())){
			reportError(403,businessAction,info);
		}else if(info.get("errorCode")!=null){
			reportError((Integer)info.get("errorCode"),businessAction,info);
		}
		return result;
	}
	
	private <R> R route(IBusiness<String, Integer, String> business, Integer actionType, String businessType,
			Object[] params) {
		IBusinessService businessService=(IBusinessService)business;
		R r=null;
		if(ACTION_TYPE_MODELANDVIEW==actionType){
			r=businessService.modelAndView(businessType, params);
		}else if(ACTION_TYPE_QUERY==actionType){
			r=businessService.query(businessType, params);
		}else if(ACTION_TYPE_PROCESS==actionType){
			r=businessService.process(businessType, params);
		}else if(ACTION_TYPE_EXPORT==actionType){
			r=businessService.export(businessType, params);
		}
		return r;
	}
	private void reportError(int i, IBusinessAction<String, Integer, String> businessAction, Map<String, Object> businessInfo) {
		
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
		LOCAL_BUSINESS_INFO.set(businessInfo);
		LOCAL_BUSINESS_ACTION.set(businessAction);
	}

	@Override
	public boolean checkPermission(User user, IBusinessAction<String, Integer, String> businessAction) {
		// TODO Auto-generated method stub
		return true;//这里应该根据AM配置的用户的实际权限去检查，默认true，并不代表真的有权限操作，只是让它接着往下执行，在观察者模式地方会返回空或不处理
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected <R> R isSupport(ICallBack<IBusiness<String, Integer, String>> callback,IBusinessAction<String, Integer, String> businessAction,R r) {
		boolean supportFlag = false;
		for (IBusiness<String, Integer, String> business : businessServices) {
			if (business.isSupport(businessAction)) {
				supportFlag = true;
				IBusiness.LAST_SUPPORT_RESULT.set(true);
				if(callback!=null){
					r=callback.callBack(business);
				}else{
					r=(R) Boolean.TRUE;
				}
				break;
			}
		}
		if(!supportFlag){
			IBusiness.LAST_SUPPORT_RESULT.set(false);
			if(callback==null){
				r=(R) Boolean.FALSE;
			}
		}
		return r;
	}
}
