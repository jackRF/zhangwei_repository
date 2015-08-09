package com.jack.wechat.message.send;

import com.jack.wechat.message.MessageType;
import com.jack.wechat.message.recieve.RecieveMessage;

public class SendTxtMessage extends SendMessage {
	private String content;
	public SendTxtMessage(RecieveMessage recieveMessage) {
		super(recieveMessage);
		this.msgType=MessageType.TYPE_TEXT;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toMessageBody() {
		return "<Content><![CDATA["+this.content+"]]></Content>";		
	}
}
