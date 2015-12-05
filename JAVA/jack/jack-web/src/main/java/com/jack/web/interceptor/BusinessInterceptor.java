package com.jack.web.interceptor;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import com.jack.intf.business.IBusinessAction;
import com.jack.web.annotation.BusinessAction;
import com.jack.web.annotation.Namespace;
import com.jack.web.app.IApplication;

@Component
public class BusinessInterceptor extends AbstractBusinessInterceptor<IBusinessAction<String, Integer, String>> {
	
	private String getDefaultNameSpace() {
		return IApplication.NS_DEFAULT;
	}

	private boolean isSupportActionType(int actionType) {
		return ArrayUtils.contains(IApplication.SUPPORT_ACTION_TYPES, actionType);
	}

	@Override
	protected IBusinessAction<String, Integer, String> getBusinessAction(HandlerMethod hm) {
		BusinessAction businessAction = hm.getMethodAnnotation(BusinessAction.class);
		if (businessAction == null) {
			return null;
		}
		Namespace namespace = hm.getMethodAnnotation(Namespace.class);
		if (namespace == null) {
			namespace = hm.getBeanType().getAnnotation(Namespace.class);
		}
		final String ns = namespace != null ? namespace.value() : getDefaultNameSpace();
		final String bt = businessAction.businessType();
		int atTemp=businessAction.actionType();
		if(!isSupportActionType(businessAction.actionType())){
			if(bt.startsWith("MV_")){
				atTemp=IApplication.ACTION_TYPE_MODELANDVIEW;
			}else if(bt.startsWith("QUERY_")){
				atTemp=IApplication.ACTION_TYPE_QUERY;
			}else if(bt.startsWith("PROCESS_")){
				atTemp=IApplication.ACTION_TYPE_PROCESS;
			}else if(bt.startsWith("EXPORT_")){
				atTemp=IApplication.ACTION_TYPE_EXPORT;
			}else{
				atTemp=calculateActionType(hm.getReturnType().getParameterType(),
						hm.getMethodAnnotation(ResponseBody.class) != null,
						hm.getMethodAnnotation(RequestMapping.class));
			}
		}
		final int at=atTemp;
		return new IBusinessAction<String, Integer, String>() {

			@Override
			public String getNameSpace() {
				return ns;
			}

			@Override
			public Integer getActionType() {
				return at;
			}

			@Override
			public String getBusinessType() {
				return bt;
			}

		};

	}
	
	private int calculateActionType(Class<?> returnType, boolean hasResponseBody, RequestMapping requestMapping) {
		if (returnType == null||void.class.equals(returnType)||Void.class.equals(returnType)) {
			return IApplication.ACTION_TYPE_EXPORT;
		}else if (!hasResponseBody) {
			return IApplication.ACTION_TYPE_MODELANDVIEW;
		} else {
			String[] mappings = requestMapping.value();
			boolean queryFlag = false;
			for (String mapping : mappings) {
				if (mapping != null && (mapping.contains("query") || mapping.contains("Query"))) {
					queryFlag = true;
					break;
				}
			}
			return queryFlag ? IApplication.ACTION_TYPE_QUERY : IApplication.ACTION_TYPE_PROCESS;
		}
	}
	
}
