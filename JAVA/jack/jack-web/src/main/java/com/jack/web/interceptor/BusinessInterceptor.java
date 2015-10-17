package com.jack.web.interceptor;


import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;

import com.jack.intf.business.IBusinessAction;
import com.jack.web.annotation.BusinessAction;
import com.jack.web.annotation.Namespace;
import com.jack.web.app.AbstractApplication;
import com.jack.web.app.Application;

@Component
public class BusinessInterceptor extends AbstractBusinessInterceptor<String, Integer, String> {
	@Autowired
	private Application application;

	private String getDefaultNameSpace() {
		return Application.DEFAULT_NAMESPACE;
	}

	private boolean isSupportActionType(int actionType) {
		return ArrayUtils.contains(Application.SUPPORT_ACTION_TYPES, actionType);
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
		final int at = isSupportActionType(businessAction.actionType()) ? businessAction.actionType()
				: calculateActionType(hm.getReturnType().getParameterType(),
						hm.getMethodAnnotation(ResponseBody.class) != null,
						hm.getMethodAnnotation(RequestMapping.class));
		final String bt = businessAction.businessType();

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
		if (returnType == null) {
			return Application.ACTION_TYPE_EXPORT;
		}
		if (!hasResponseBody) {
			if (String.class.equals(returnType)) {
				return Application.ACTION_TYPE_MODELANDVIEW;
			}
		} else {
			String[] mappings = requestMapping.value();
			boolean queryFlag = false;
			for (String mapping : mappings) {
				if (mapping != null && (mapping.contains("query") || mapping.contains("Query"))) {
					queryFlag = true;
					break;
				}
			}
			return queryFlag ? Application.ACTION_TYPE_QUERY : Application.ACTION_TYPE_PROCESS;
		}
		return 0;
	}
	@Override
	protected AbstractApplication<String, Integer, String> getApplication() {
		return application;
	}
}
