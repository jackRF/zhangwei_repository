package com.jack.wechat.message.recieve;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Element;

import com.jack.wechat.WechatContextFactory;

public abstract class RecieveMessage {
	public static Log logger = LogFactory.getLog(WechatContextFactory.class);
	private String msgXml;
	protected String handlerType;
	private String toUserName;
	private String fromUserName;
	private Date createTime;
	private String msgType;
	public RecieveMessage(String msgXml, Element root) {
		this.msgXml=msgXml;
		parsePubXml(root);
		parseXml(root);
	}
	public String getMsgXml() {
		return msgXml;
	}	
	public String getToUserName() {
		return toUserName;
	}
	
	public String getFromUserName() {
		return fromUserName;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public String getMsgType() {
		return msgType;
	}
	
	public String getHandlerType() {
		return handlerType;
	}
	
	private void parsePubXml(Element root) {
		this.toUserName = root.elementText("ToUserName");
		this.fromUserName = root.elementText("FromUserName");		
		this.createTime = new Date((Long.parseLong(root.elementText("CreateTime")) * 1000));
		this.msgType = root.elementText("MsgType");
	}
	public abstract void parseXml(Element root);
}
