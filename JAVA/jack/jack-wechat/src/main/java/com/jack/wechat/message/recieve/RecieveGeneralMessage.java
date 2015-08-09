package com.jack.wechat.message.recieve;

import org.dom4j.Element;

public abstract class RecieveGeneralMessage  extends RecieveMessage{
	private Long msgId;
	public Long getMsgId() {
		return msgId;
	}

	public RecieveGeneralMessage(String msgXml, Element root) {
		super(msgXml, root);		
	}

	@Override
	public  void parseXml(Element root) {
		this.msgId =Long.parseLong(root.elementText("MsgId"));		
	}
	
}
