package com.jack.wechat.message.send;

import java.util.Date;

import com.jack.wechat.message.recieve.RecieveMessage;

public abstract class SendMessage {
	protected String toUserName;
	protected String fromUserName;
	protected Date createTime;
	protected String msgType;
	public SendMessage(RecieveMessage recieveMessage) {
		this.toUserName = recieveMessage.getFromUserName();
		this.fromUserName = recieveMessage.getToUserName();
		this.createTime = new Date();
	}
	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public  String toXml(){
		StringBuilder sb = new StringBuilder();
		sb.append("<xml>");
		sb.append("<ToUserName><![CDATA["+this.toUserName+"]]></ToUserName>");
		sb.append("<FromUserName><![CDATA["+this.fromUserName+"]]></FromUserName>");
		sb.append("<CreateTime>"+(this.createTime.getTime() / 1000)+"</CreateTime>");
		sb.append("<MsgType><![CDATA["+this.msgType+"]]></MsgType>");
		sb.append(this.toMessageBody());
		sb.append("</xml>");
		return sb.toString();
	};
	protected abstract String toMessageBody();
}
