package com.jack.web.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction  extends ActionSupport implements SessionAware,ServletRequestAware,ServletResponseAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected Map<String,Object> session;

	protected HttpServletRequest request;
	
	protected HttpServletResponse response;
	
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}

	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request=request;
	}

	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session=session;
	}

}
