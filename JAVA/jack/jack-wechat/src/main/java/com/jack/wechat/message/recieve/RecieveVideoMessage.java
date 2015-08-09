package com.jack.wechat.message.recieve;

import org.dom4j.Element;

import com.jack.wechat.message.MessageType;

public class RecieveVideoMessage extends RecieveGeneralMessage {
	private String mediaId;
	private String thumbMediaId;
	
	public String getMediaId() {
		return mediaId;
	}
	public String getThumbMediaId() {
		return thumbMediaId;
	}
	public RecieveVideoMessage(String msgXml, Element root) {
		super(msgXml, root);
		this.handlerType=MessageType.HANDLER_TYPE_VIDEO;
	}
	@Override
	public void parseXml(Element root){
		super.parseXml(root);
		this.mediaId = root.elementText("MediaId");
		this.thumbMediaId = root.elementText("ThumbMediaId");
	}
}
