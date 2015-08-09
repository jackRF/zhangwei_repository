package com.jack.wechat.message.recieve;

import org.dom4j.Element;

import com.jack.wechat.message.MessageType;

public class RecieveQrsceneEvent  extends RecieveEvent{
	private String eventKey;
	private String ticket;
	
	public String getEventKey() {
		return eventKey;
	}
	public String getTicket() {
		return ticket;
	}
	public RecieveQrsceneEvent(String msgXml, Element root) {
		super(msgXml, root);
		this.handlerType=MessageType.HANDLER_EVENT_QRSCENE;
	}
	@Override
	public void parseXml(Element root) {
		this.eventKey=root.elementText("EventKey");
		this.ticket=root.elementText("Ticket");
	}
}
