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
import com.jack.intf.business.IBusinessAction;
import com.jack.service.IBusinessService;
@Component
public class JackApplication extends AbstractApplication<IBusinessAction<String, Integer, String>>{
	@Autowired
	private List<IBusinessService> businessServices;
	@Override
	public <R> R doBusiness(final Object... params){
		final IBusinessAction<String, Integer, String> businessAction=LOCAL_BUSINESS_ACTION.get();
		 return isSupport(new ICallBack<IBusinessService>() {
			@Override
			public <R2> R2 callBack(IBusinessService businessService) {
				return doBusiness(businessService,businessAction, params);
			}
		}, businessAction,null);
	}
	@Override
	public <CB,R> CB doBusiness(ICallBack<R> callBack, Object... params) {
		R result = doBusiness(params);
		return callBack.callBack(result);
	}
	private <R> R doBusiness(IBusinessService businessService, IBusinessAction<String, Integer, String> businessAction,
			Object... params) {
		Integer actionType=businessAction.getActionType();
		String businessType=businessAction.getBusinessType();
		R result=route(businessService,actionType, businessType, params);
		Map<String,Object> info=LOCAL_BUSINESS_INFO.get();
		if(!Boolean.TRUE.equals(IBusinessService.LAST_SUPPORT_RESULT.get())){
			reportError(403,businessAction,info);
		}else if(info.get("errorCode")!=null){
			reportError((Integer)info.get("errorCode"),businessAction,info);
		}
		return result;
	}
	private <R> R route(IBusinessService businessService, Integer actionType, String businessType,
			Object[] params) {
		if(ACTION_TYPE_MODELANDVIEW==actionType){
			return businessService.modelAndView(businessType, params);
		}else if(ACTION_TYPE_QUERY==actionType){
			return businessService.query(businessType, params);
		}else if(ACTION_TYPE_PROCESS==actionType){
			return businessService.process(businessType, params);
		}else if(ACTION_TYPE_EXPORT==actionType){
			return businessService.export(businessType, params);
		}
		return null;
	}
	
	@Override
	protected void initLocalBusiness(HttpServletRequest request, HttpServletResponse response, User user,
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
	protected boolean checkPermission(User user, IBusinessAction<String, Integer, String> businessAction) {
		// TODO Auto-generated method stub
		return true;//这里应该根据AM配置的用户的实际权限去检查，默认true，并不代表真的有权限操作，只是让它接着往下执行，在观察者模式地方会返回空或不处理
	}
	private void reportError(int i, IBusinessAction<String, Integer, String> businessAction, Map<String, Object> businessInfo) {
		
	}
	@Override
	protected void sendError(int statusCode, HttpServletResponse response, User user,
			IBusinessAction<String, Integer, String> businessAction) {
		try {
			response.sendError(statusCode);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	protected <R> R isSupport(IBusinessAction<String, Integer, String> businessAction) {
		return isSupport(null,businessAction,null);
	}
	@SuppressWarnings("unchecked")
	private <R> R isSupport(ICallBack<IBusinessService> callback,IBusinessAction<String, Integer, String> businessAction,R r) {
		boolean supportFlag = false;
		for (IBusinessService businessService : businessServices) {
			if (businessService.isSupport(businessAction)) {
				supportFlag = true;
				IBusinessService.LAST_SUPPORT_RESULT.set(true);
				if(callback!=null){
					r=callback.callBack(businessService);
				}else{
					r=(R) Boolean.TRUE;
				}
				break;
			}
		}
		if(!supportFlag){
			IBusinessService.LAST_SUPPORT_RESULT.set(false);
			if(callback==null){
				r=(R) Boolean.FALSE;
			}
		}
		return r;
	}
}
