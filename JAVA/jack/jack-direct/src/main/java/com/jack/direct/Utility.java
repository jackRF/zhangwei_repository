package com.jack.direct;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class Utility {
	public static boolean StringIsNullOrEmpty(String str){
		if(str==null||str==""){
			return true;
		}
		return false;
	}
	public static boolean HasAnnotation(Class<?> c,
			Class<? extends Annotation> c1) {
		return HasAnnotation(c.getAnnotations(),c1);
	}

	public static boolean HasAnnotation(Method method,
			Class<? extends Annotation> c) {
		return HasAnnotation(method.getAnnotations(), c);
	}
	private static boolean HasAnnotation(Annotation[] annotations,
			Class<? extends Annotation> c) {		
		return findAnnotation(annotations,c)!=null;
	}
	@SuppressWarnings("unchecked")
	public static <T extends Annotation > T findAnnotation(Annotation[]annotations,Class<T> c){
		if (annotations != null && annotations.length > 0) {
			for (Annotation annotation : annotations) {
				if (c.isInstance(annotation)) {
					return (T) annotation;
				}
			}
		}
		return null;
	}	
}
