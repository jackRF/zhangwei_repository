package com.jack.wechat.message.recieve;

import org.dom4j.Element;

import com.jack.wechat.message.MessageType;

public class RecieveEvent extends  RecieveMessage{
	private String event;
	
	public String getEvent() {
		return event;
	}

	public RecieveEvent(String msgXml, Element root) {
		super(msgXml, root);
		this.handlerType=MessageType.HANDLER_EVENT_GENERAL;
	}
	@Override
	public void parseXml(Element root) {
		this.event=root.elementText("Event");
	}
}
