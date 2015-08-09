package com.jack.wechat.message.recieve;

import org.dom4j.Element;

import com.jack.wechat.message.MessageType;

public class RecieveImageMessage extends RecieveGeneralMessage {
	private String picUrl;
	private String mediaId;
	
	public String getPicUrl() {
		return picUrl;
	}
	public String getMediaId() {
		return mediaId;
	}
	
	public RecieveImageMessage(String msgXml, Element root) {
		super(msgXml, root);	
		this.handlerType=MessageType.HANDLER_TYPE_IMAGE;
	}
	
	@Override
	public  void parseXml(Element root) {
		super.parseXml(root);
		this.picUrl =root.elementText("PicUrl");
		this.mediaId =root.elementText("MediaId");
	}
}
