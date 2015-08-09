package com.jack.wechat;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jack.wechat.message.handler.HandlerChain;
import com.jack.wechat.message.recieve.RecieveMessage;
import com.jack.wechat.message.send.SendMessage;
import com.jack.wechat.util.HttpUtils;

public class WechatSession {
	public static Log logger = LogFactory.getLog(WechatContextFactory.class);

	private String id;			//会话唯一身份	
	private long createTime;  	//会话创建时间
	private String openId;		//用户的openID
	private RecieveMessage recieveMessage;	//接收的消息
	private HttpServletResponse response;	
	private HandlerChain chain;				//操作链
	private int repeatCount;				//消息重复接收次数
	public static Log getLogger() {
		return logger;
	}
	public static void setLogger(Log logger) {
		WechatSession.logger = logger;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public RecieveMessage getRecieveMessage() {
		return recieveMessage;
	}
	public void setRecieveMessage(RecieveMessage recieveMessage) {
		this.recieveMessage = recieveMessage;
	}
	public HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	public HandlerChain getChain() {
		return chain;
	}
	public void setChain(HandlerChain chain) {
		this.chain = chain;
	}
	public int getRepeatCount() {
		return repeatCount;
	}
	public void setRepeatCount(int repeatCount) {
		this.repeatCount = repeatCount;
	}
	public void toChain(){		
		try {
			SendMessage sendMessage= chain.doHandler(this.recieveMessage);
			String responseXml="";
			if (sendMessage!=null) {
				responseXml = sendMessage.toXml();
			}	
			HttpUtils.write(response, responseXml);			
		} catch (Exception e) {			
			e.printStackTrace();
		}finally{
			SessionManager.getInstance().removeSession(this.id);
		}	
	}
}
