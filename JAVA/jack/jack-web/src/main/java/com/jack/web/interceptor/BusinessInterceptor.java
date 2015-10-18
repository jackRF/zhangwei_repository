package com.jack.web.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;

import com.jack.intf.business.IBusinessAction;
import com.jack.web.annotation.BusinessAction;
import com.jack.web.annotation.Namespace;
import com.jack.web.app.CCSApplication;

@Component
public class BusinessInterceptor extends AbstractBusinessInterceptor<String, Integer, String> {
	@Autowired
	private CCSApplication application;

	private String getDefaultNameSpace() {
		return CCSApplication.DEFAULT_NAMESPACE;
	}

	private boolean isSupportActionType(int actionType) {
		return ArrayUtils.contains(CCSApplication.SUPPORT_ACTION_TYPES, actionType);
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
		
		if (returnType == null||void.class.equals(returnType)||Void.class.equals(returnType)) {
			return CCSApplication.ACTION_TYPE_EXPORT;
		}
		if (!hasResponseBody) {
			if (String.class.equals(returnType)) {
				return CCSApplication.ACTION_TYPE_MODELANDVIEW;
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
			return queryFlag ? CCSApplication.ACTION_TYPE_QUERY : CCSApplication.ACTION_TYPE_PROCESS;
		}
		return 0;
	}

	@Override
	protected boolean isSupport(HttpServletRequest request, HttpServletResponse response,
			IBusinessAction<String, Integer, String> businessAction) {
		return application.isSupport(request, response, businessAction);
	}
}
