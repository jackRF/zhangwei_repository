
package com.jack.wechat;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jack.wechat.message.MessageType;
import com.jack.wechat.message.handler.HandlerChain;
import com.jack.wechat.message.handler.RouterHandler;
import com.jack.wechat.message.recieve.RecieveGeneralMessage;
import com.jack.wechat.message.recieve.RecieveMessage;




/**
 *会话管理，在消息未被处理完成之前，会话保存在此处
 * @author CaryXu
 *
 */
public class SessionManager {

	public static Logger logger = LoggerFactory.getLogger(WechatContextFactory.class);

	private static SessionManager manager;

	private final Map<String, WechatSession> wechatSessions;

	private SessionManager() {
		wechatSessions = new HashMap<String, WechatSession>();
	}

	/**
	 * 获得会话实例
	 * @return
	 */
	public static SessionManager getInstance() {
		if (manager == null)
			manager = new SessionManager();

		return manager;
	}

	/**
	 *  创建一个用户会话，并调用操作链进行处理
	 * @param recieveMessage
	 * @param response
	 */
	public void buildSession(String recieveMessage, HttpServletResponse response) {
		RecieveMessage  message= RouterHandler.generateMsg(recieveMessage);
		String sessionId="";
		if(message.getMsgType().equals(MessageType.TYPE_EVENT)){
			sessionId= message.getToUserName()+ message.getCreateTime().getTime();
		}else{
			sessionId=((RecieveGeneralMessage)message).getMsgId().toString();
		}		
		WechatSession session=wechatSessions.get(sessionId);
		if (session == null) {			
			HandlerChain chain = RouterHandler.parseHandler(message);
			
			session = new WechatSession();
			session.setId(sessionId);
			session.setChain(chain);
			session.setCreateTime(new Date().getTime());
			session.setRecieveMessage(message);
			session.setOpenId(message.getFromUserName());
			session.setRepeatCount(1);
			session.setResponse(response);

			wechatSessions.put(sessionId, session);

			session.toChain();
		} else{
			session.setRepeatCount(session.getRepeatCount() + 1);
		}

	}

	/**
	 *  移除一个会话
	 * @param sessionId
	 */
	public void removeSession(String sessionId) {
		wechatSessions.remove(sessionId);
	}
}
