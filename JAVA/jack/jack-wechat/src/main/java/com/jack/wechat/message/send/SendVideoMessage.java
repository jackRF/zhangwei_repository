package com.jack.wechat.message.send;

import com.jack.wechat.message.MessageType;
import com.jack.wechat.message.recieve.RecieveMessage;

public class SendVideoMessage extends SendMessage {
	private String mediaId;
	private String title;
	private String description;
	
	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public SendVideoMessage(RecieveMessage recieveMessage) {
		super(recieveMessage);
		this.msgType=MessageType.TYPE_VIDEO;
	}

	@Override
	public String toMessageBody() {		
		StringBuilder sb=new StringBuilder();
		sb.append("<Video>");
		sb.append("<MediaId><![CDATA["+this.mediaId+"]]></MediaId>");
		sb.append("<Title><![CDATA["+this.title+"]]></Title>");
		sb.append("<Description><![CDATA["+this.description+"]]></Description>");
		sb.append("</Video>");
		return sb.toString();
	}
}
