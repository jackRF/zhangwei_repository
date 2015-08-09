package com.jack.direct;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


/**
 * ͨѶ��Ϣ
 * @author Administrator
 *
 */
public class DirectAction {

	private Map<String, DirectMethod> methods;
	private Class<?> c;
	private String name;
    
	public DirectAction(Class<?> c) {
		this.c = c;
		this.name = c.getSimpleName();
		this.methods = new HashMap<String, DirectMethod>();
		com.jack.direct.annotation.DirectAction directAction = getDirectActionAnnotation(c);
		if (directAction != null) {
			if (!"".equals(directAction.alias())) {
				this.name = directAction.alias();
			}
		}
		this.configure();
	}
	public String getName() {
		return name;
	}
	public Class<?> getActionClass(){
		return c;

	}
	
	public Map<String, DirectMethod> getMethods() {
		return this.methods;
	}
	

	private void configure() {
		Method[] methods = this.c.getMethods();
		for (Method m : methods) {
			if (DirectMethod.isDirectMethod(m)) {
				DirectMethod dm = new DirectMethod(m);
				this.methods.put(dm.getName(), dm);
			}
		}
	}

	private static com.jack.direct.annotation.DirectAction getDirectActionAnnotation(
			Class<?> c) {
		return Utility.findAnnotation(c.getAnnotations(),
						com.jack.direct.annotation.DirectAction.class);
	}

	public static boolean isDirectAction(Class<?> c) {
		return getDirectActionAnnotation(c) != null;
	}

}
