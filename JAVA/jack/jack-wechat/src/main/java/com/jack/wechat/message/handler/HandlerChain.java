package com.jack.wechat.message.handler;

import java.util.LinkedList;

import com.jack.wechat.message.recieve.RecieveMessage;
import com.jack.wechat.message.send.SendMessage;

public class HandlerChain {
	private final String handlerType;
	private final LinkedList<Handler> handlerQuene = new LinkedList<Handler>();
	public HandlerChain(String handlerType){
		this.handlerType=handlerType;
	}
	
	public String getHandlerType() {
		return handlerType;
	}

	public void insertFirst(Handler handler){
		handlerQuene.add(0, handler);
	}
	public void append(Handler handler){
		handlerQuene.add(handler);
	}
	public int getSize() {
		return handlerQuene.size();
	}
	public SendMessage doHandler(RecieveMessage recieveMessage) throws Exception {
		SendMessage sendMessage = null;
		for (Handler handler : handlerQuene) {
			handler.setSendMessage(sendMessage);
			handler.setRecieveMessage(recieveMessage);
			handler.process();
			sendMessage = handler.getSendMessage();
		}
		return sendMessage;
	}
}
