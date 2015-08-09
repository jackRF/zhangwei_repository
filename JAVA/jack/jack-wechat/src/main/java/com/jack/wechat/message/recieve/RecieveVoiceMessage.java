package com.jack.wechat.message.recieve;

import org.dom4j.Element;

import com.jack.wechat.message.MessageType;

public class RecieveVoiceMessage extends RecieveGeneralMessage {
	private String mediaId;
	private String format;
	private String recognition;
	public String getMediaId() {
		return mediaId;
	}

	public String getFormat() {
		return format;
	}

	public String getRecognition() {
		return recognition;
	}

	public RecieveVoiceMessage(String msgXml, Element root) {
		super(msgXml, root);
		this.handlerType=MessageType.HANDLER_TYPE_VOICE;
	}
	
	@Override
	public  void parseXml(Element root) {
		super.parseXml(root);
		this.mediaId = root.elementText("MediaId");
		this.format = root.elementText("Format");
		this.recognition = root.elementText("Recognition");
	}
}
