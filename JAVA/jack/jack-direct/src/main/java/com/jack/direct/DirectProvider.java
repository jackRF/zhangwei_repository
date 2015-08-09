package com.jack.direct;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;



public class DirectProvider {

	private String id;
	private String name;
	private String type;
	private String url;
	private String namespace;
	private Integer timeout;
	private Integer maxRetries;	
	private String api;
	private boolean isConfigured;

	private Map<String, DirectAction> actions;

	public DirectProvider() {
		actions = new HashMap<String, DirectAction>();
	}	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public Integer getTimeout() {
		return timeout;
	}

	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}

	public Integer getMaxRetries() {
		return maxRetries;
	}

	public void setMaxRetries(Integer maxRetries) {
		this.maxRetries = maxRetries;
	}

	public String getApi() {
		return api;
	}

	public void setApi(String api) {
		this.api = api;
	}

	public boolean isConfigured() {
		return isConfigured;
	}

	public void setConfigured(boolean isConfigured) {
		this.isConfigured = isConfigured;
	}
	
	public Map<String, DirectAction> getActions() {
		return actions;
	}

	public void configure(Class<?>... cs){
		Set<Class<?>> set = new HashSet<Class<?>>();
		for (Class<?> c : cs) {
			if (DirectAction.isDirectAction(c)) {
				set.add(c);
			}
		}
		configure(set);
	}

	private void configure(Set<Class<?>> set){
		for (Class<?> c : set) {
			DirectAction da = new DirectAction(c);
			this.actions.put(da.getName(), da);
		}
	}
	

	public void clear() {
		this.isConfigured = false;
		this.actions.clear();
	}
}
