package com.jack.wechat.message.send;

import com.jack.wechat.message.MessageType;
import com.jack.wechat.message.recieve.RecieveMessage;

public class SendImageMessage extends SendMessage {
	private String mediaId;
	public SendImageMessage(RecieveMessage recieveMessage) {
		super(recieveMessage);
		this.msgType=MessageType.TYPE_IMAGE;
	}
	
	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	@Override
	public String toMessageBody() {
		return "<Image><MediaId><![CDATA["+this.mediaId+"]]></MediaId></Image>";
	}
}
