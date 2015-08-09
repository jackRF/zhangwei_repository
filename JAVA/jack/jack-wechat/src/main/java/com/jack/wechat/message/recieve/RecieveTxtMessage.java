package com.jack.wechat.message.recieve;

import org.dom4j.Element;

import com.jack.wechat.message.MessageType;

public class RecieveTxtMessage extends RecieveGeneralMessage {
	private String content;
	public String getContent() {
		return content;
	}

	public RecieveTxtMessage(String msgXml, Element root) {
		super(msgXml, root);
		this.handlerType=MessageType.HANDLER_TYPE_TEXT;
	}

	@Override
	public void parseXml(Element root) {
		super.parseXml(root);
		this.content = root.elementText("Content");
	}

}
