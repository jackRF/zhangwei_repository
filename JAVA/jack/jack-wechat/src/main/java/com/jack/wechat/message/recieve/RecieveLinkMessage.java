package com.jack.wechat.message.recieve;

import org.dom4j.Element;

import com.jack.wechat.message.MessageType;

public class RecieveLinkMessage extends RecieveGeneralMessage {
	
	private String title;
	private String description;
	private String url;
	
	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getUrl() {
		return url;
	}

	public RecieveLinkMessage(String msgXml, Element root) {
		super(msgXml, root);
		this.handlerType=MessageType.HANDLER_TYPE_LINK;
	}
	@Override
	public  void parseXml(Element root) {
		super.parseXml(root);
		this.title=root.elementText("Title");
		this.description=root.elementText("Description");
		this.url=root.elementText("Url");
	}
}
