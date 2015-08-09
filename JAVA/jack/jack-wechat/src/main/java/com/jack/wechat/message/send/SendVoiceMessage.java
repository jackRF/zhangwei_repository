package com.jack.wechat.message.send;

import com.jack.wechat.message.MessageType;
import com.jack.wechat.message.recieve.RecieveMessage;

public class SendVoiceMessage extends SendMessage {
	private String mediaId;
	public SendVoiceMessage(RecieveMessage recieveMessage) {
		super(recieveMessage);
		this.msgType=MessageType.TYPE_VOICE;
	}
	
	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	@Override
	public String toMessageBody() {	
		return "<Voice><MediaId><![CDATA["+this.mediaId+"]]></MediaId></Voice>";
	}
}
