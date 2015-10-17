package com.jack.web.app;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

import com.jack.intf.business.IBusinessCallBack;
import com.jack.intf.business.ICCSBusinessAction;
import com.jack.service.ICCSBusinessService;
@Component
public class CCSApplication implements ICCSActionType {
	@Autowired
	private List<ICCSBusinessService> ccsBusinessServices=new ArrayList<ICCSBusinessService>();
	private final ThreadLocal<ICCSBusinessAction> LOCAL_CCS_BUSINESS_ACTION=new ThreadLocal<ICCSBusinessAction>();
	private final ThreadLocal<ICCSBusinessService> LOCAL_CCS_BUSINESS_SERVICE=new ThreadLocal<ICCSBusinessService>();
	public boolean isSupport(ICCSBusinessAction ccsBusinessAction){
		boolean supportFlag=false;
		ICCSBusinessService localCCSBusinessService=null;
		for(ICCSBusinessService ccsBusinessService:ccsBusinessServices){
			if(ccsBusinessService.isSupport(ccsBusinessAction)){
				supportFlag=true;
				localCCSBusinessService=ccsBusinessService;
				LOCAL_CCS_BUSINESS_ACTION.set(ccsBusinessAction);
				LOCAL_CCS_BUSINESS_SERVICE.set(localCCSBusinessService);
				break;
			}
		}
		return supportFlag;
		
	}
	private  ApplicationContext applicationContext;
	public <T> T getBean(Class<T> requiredType){
        return applicationContext.getBean(requiredType);
    }
    @SuppressWarnings("unchecked")
    public <T> T getBean(String name){
        return (T) applicationContext.getBean(name);
    }
    public void publishEvent(ApplicationEvent event){
        applicationContext.publishEvent(event);
    }
	public <R> R doBusiness(Object...params){
    	ICCSBusinessAction ccsBusinessAction=LOCAL_CCS_BUSINESS_ACTION.get();
    	ICCSBusinessService localCCSBusinessService=LOCAL_CCS_BUSINESS_SERVICE.get();
    	int actionType=ccsBusinessAction.getActionType();
    	String businessType=ccsBusinessAction.getBusinessType();
    	
    	R result=null;
    	 if(MODELANDVIEW==actionType){
             result=localCCSBusinessService.modelAndView(businessType,params);
         }else if(QUERY==actionType){
             result=localCCSBusinessService.query(businessType,params);
         }else if(PROCESS==actionType){
             result=localCCSBusinessService.process(businessType,params);
         }else if(EXPORT==actionType){
             result=localCCSBusinessService.export(businessType,params);
         }else {
             throw new IllegalArgumentException("ActionType 配置错误");
         }
    	 return result;
    }
	public <B,R> B doBusiness(IBusinessCallBack<R> callBack,Object...params){
    	R result=doBusiness(params);
        return callBack.callBack(result);
    }
}
