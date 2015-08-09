package com.jack.direct;

import java.lang.reflect.Method;



public class DirectMethod {
	private Method method;	
	private String name;
	private int len;

	public DirectMethod(Method method) {
		this.method = method;
		this.name = method.getName();		
		len = method.getParameterTypes().length;
		com.jack.direct.annotation.DirectMethod methodAnnotation = getDirectMethodAnnotation(method);
		if (methodAnnotation != null) {
			if (!"".equals(methodAnnotation.alias())) {
				this.name = methodAnnotation.alias();
			}			
		}
	}

	public Method getMethod() {
		return method;
	}

	public String getName() {
		return name;
	}
	
	public int getLen() {
		return len;
	}

	

	private static com.jack.direct.annotation.DirectMethod getDirectMethodAnnotation(
			Method m) {
		return Utility.findAnnotation(m.getAnnotations(),
						com.jack.direct.annotation.DirectMethod.class);

	}

	public static boolean isDirectMethod(Method m) {
		return getDirectMethodAnnotation(m) != null;
	}
}
