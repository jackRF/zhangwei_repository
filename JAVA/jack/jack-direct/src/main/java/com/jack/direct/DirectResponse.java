package com.jack.direct;



public class DirectResponse {
	private String type;	

	private int tid;
	
	private String action;
	
	private String method;
	
	private Object result;	
	
	private String message;
	
	
	public DirectResponse(DirectRequest request) {
		this.action=request.getAction();
		this.method=request.getMethod();
		this.tid=request.getTid();
		this.type=request.getType();
	}


	public void setType(String type) {
		this.type = type;
	}


	public void setResult(Object result) {
		this.result = result;
	}


	public void setMessage(String message) {
		this.message = message;
	}
	public String getType() {
		return type;
	}


	public int getTid() {
		return this.tid;
	}


	public String getAction() {
		return this.action;
	}


	public String getMethod() {
		return this.method;
	}


	public Object getResult() {
		return this.result;
	}


	public String getMessage() {
		return this.message;
	}

}
