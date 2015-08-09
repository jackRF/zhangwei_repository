package com.jack.wechat.message.handler;

import com.jack.wechat.message.recieve.RecieveMessage;
import com.jack.wechat.message.send.SendMessage;

public  abstract class Handler {
	/** 需处理的消息 **/
	protected RecieveMessage recieveMessage;

	/** 返回的信息 **/
	protected SendMessage sendMessage;



	/** 操作类所在的处理链 **/
	protected HandlerChain chain;
	
	public RecieveMessage getRecieveMessage() {
		return recieveMessage;
	}
	public void setRecieveMessage(RecieveMessage recieveMessage) {
		this.recieveMessage = recieveMessage;
	}
	public SendMessage getSendMessage() {
		return sendMessage;
	}
	public void setSendMessage(SendMessage sendMessage) {
		this.sendMessage = sendMessage;
	}
	
	public HandlerChain getChain() {
		return chain;
	}
	public void setChain(HandlerChain chain) {
		this.chain = chain;
	}
	public abstract void process() throws Exception;
}
