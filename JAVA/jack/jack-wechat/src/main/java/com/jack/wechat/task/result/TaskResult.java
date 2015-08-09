package com.jack.wechat.task.result;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;


public abstract class TaskResult {
	private Date recieveTime;
	private String json;
	private int errorCode;
	private String errMsg;
	private JSONObject result;
	public TaskResult(String json){
		this.recieveTime=new Date();
		this.json=json;
		this.result=JSONObject.parseObject(json);
		Object errcode=result.get("errcode");
		if(errcode!=null){
			this.errorCode=Integer.parseInt(errcode.toString());
		}
		Object errmsg=result.get("errmsg");
		if(errmsg!=null){
			this.errMsg=errmsg.toString();
		}
		if(errorCode==0){
			this.parseResult();
		}
	}	
	public Date getRecieveTime() {
		return recieveTime;
	}
	public String getJson() {
		return json;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public JSONObject getResult() {
		return result;
	}
	protected String getString(String key){
		Object o=result.get(key);
		if(o!=null){
			return o.toString();
		}
		return null;
	}
	
	protected Integer getInt(String key){
		Object o=result.get(key);
		if(o!=null){
			return Integer.parseInt(o.toString());
		}
		return 0;
	}
	protected Long getLong(String key){
		Object o=result.get(key);
		if(o!=null){
			return Long.parseLong(o.toString());
		}
		return 0L;
	}
	protected Double getDouble(String key){
		Object o=result.get(key);
		if(o!=null){
			return Double.parseDouble(o.toString());
		}
		return 0D;
	}
	
	protected abstract void parseResult();
}
