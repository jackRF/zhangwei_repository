package com.jack.wechat.message.handler;



import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.jack.wechat.WechatContextFactory;
import com.jack.wechat.message.MessageType;
import com.jack.wechat.message.recieve.RecieveEvent;
import com.jack.wechat.message.recieve.RecieveImageMessage;
import com.jack.wechat.message.recieve.RecieveLinkMessage;
import com.jack.wechat.message.recieve.RecieveLocationEvent;
import com.jack.wechat.message.recieve.RecieveLocationMessage;
import com.jack.wechat.message.recieve.RecieveMenuEvent;
import com.jack.wechat.message.recieve.RecieveMessage;
import com.jack.wechat.message.recieve.RecieveQrsceneEvent;
import com.jack.wechat.message.recieve.RecieveTxtMessage;
import com.jack.wechat.message.recieve.RecieveVideoMessage;
import com.jack.wechat.message.recieve.RecieveVoiceMessage;


public class RouterHandler {
	public static Log logger = LogFactory.getLog(WechatContextFactory.class);
	/**
	 * 获得消息的操作链
	 * @param message
	 * @return
	 */
	public static HandlerChain parseHandler(RecieveMessage message) {
		String handlerType = message.getHandlerType();
		HandlerChain chain = HandlerManager.getInstance().getChain(handlerType);
		return chain;
	}

	/**
	 * 转换消息为ReciveMessage
	 * @param recieveMsg
	 * @return
	 */
	public static RecieveMessage generateMsg(String recieveMsg) {
		RecieveMessage message = null;
		try {
			Document doc = DocumentHelper.parseText(recieveMsg);
			Element root = doc.getRootElement();
			String type = root.elementText("MsgType");

			if (StringUtils.equals(type, MessageType.TYPE_TEXT)) {
				message = new RecieveTxtMessage(recieveMsg, root);
			} else if (StringUtils.equals(type, MessageType.TYPE_IMAGE)) {
				message = new RecieveImageMessage(recieveMsg, root);
			} else if (StringUtils.equals(type, MessageType.TYPE_VOICE)) {
				message = new RecieveVoiceMessage(recieveMsg, root);
			} else if (StringUtils.equals(type, MessageType.TYPE_VIDEO)) {
				message = new RecieveVideoMessage(recieveMsg, root);
			} else if (StringUtils.equals(type, MessageType.TYPE_LOCATION)) {
				message = new RecieveLocationMessage(recieveMsg, root);
			} else if (StringUtils.equals(type, MessageType.TYPE_LINK)) {
				message = new RecieveLinkMessage(recieveMsg, root);
			} else if (StringUtils.equals(type, MessageType.TYPE_EVENT)) {
				String event = root.elementText("Event");
				if (StringUtils.equals(event, MessageType.EVENT_SUBSCRIBE)) {
					if (root.elementText("Ticket") == null){
						message = new RecieveEvent(recieveMsg, root);
					}
					else{
						message = new RecieveQrsceneEvent(recieveMsg, root);
					}
					
				} else if(StringUtils.equals(event, MessageType.EVENT_UNSUBSCRIBE)){
					message = new RecieveEvent(recieveMsg, root);
				} else if (StringUtils.equals(event, MessageType.EVENT_SCAN)) {
					message = new RecieveQrsceneEvent(recieveMsg, root);
				} else if (StringUtils.equals(event, MessageType.EVENT_LOCATION)) {
					message = new RecieveLocationEvent(recieveMsg, root);				
				} else if (StringUtils.equals(event, MessageType.EVENT_CLICK)) {
					message = new RecieveMenuEvent(recieveMsg, root);
				} else if (StringUtils.equals(event, MessageType.EVENT_VIEW)) {
					message = new RecieveMenuEvent(recieveMsg, root);					
				}				
			}

		} catch (Exception e) {
			throw new HandlerException("RecieveMessage parse error.", e);
		}

		return message;
	}
}
