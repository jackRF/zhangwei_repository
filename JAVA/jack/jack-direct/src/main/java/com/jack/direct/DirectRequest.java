package com.jack.direct;


public class DirectRequest {
	 
	private String action;
	private String method;	
	private Object data;
	private String type;	
	private int tid;
	
	
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public int getTid() {
		return this.tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}		
}
