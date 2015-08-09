package com.jack.wechat.message.recieve;

import org.dom4j.Element;

import com.jack.wechat.message.MessageType;

public class RecieveMenuEvent extends  RecieveEvent{
	private String eventKey;
	
	public String getEventKey() {
		return eventKey;
	}
	public RecieveMenuEvent(String msgXml, Element root) {
		super(msgXml, root);
		this.handlerType=MessageType.HANDLER_EVENT_MENU;
	}
	@Override
	public void parseXml(Element root) {
		this.eventKey=root.elementText("EventKey");
	}
}
