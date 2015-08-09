package com.jack.wechat.message.handler;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Element;


import com.jack.wechat.WechatContextFactory;
import com.jack.wechat.message.MessageType;
import com.jack.wechat.util.WechatUtils;

/**
*  操作管理器
* @author CaryXu
*
*/
public class HandlerManager {

public static Log logger = LogFactory.getLog(WechatContextFactory.class);

/**
 *  String：事件名称 List<String>:handler类名
 */
private Map<String, List<String>> chains;

private static HandlerManager manager;

private HandlerManager() {
	init();
}

@SuppressWarnings("unchecked")
private void init() {
	chains = new HashMap<String, List<String>>();
	chains.put(MessageType.HANDLER_TYPE_TEXT, new ArrayList<String>());
	chains.put(MessageType.HANDLER_TYPE_IMAGE, new ArrayList<String>());
	chains.put(MessageType.HANDLER_TYPE_VOICE, new ArrayList<String>());
	chains.put(MessageType.HANDLER_TYPE_VIDEO, new ArrayList<String>());
	chains.put(MessageType.HANDLER_TYPE_LOCATION, new ArrayList<String>());
	chains.put(MessageType.HANDLER_TYPE_LINK, new ArrayList<String>());
	chains.put(MessageType.HANDLER_EVENT_GENERAL,new ArrayList<String>());
	chains.put(MessageType.HANDLER_EVENT_QRSCENE,new ArrayList<String>());
	chains.put(MessageType.HANDLER_EVENT_LOCATION,new ArrayList<String>());
	chains.put(MessageType.HANDLER_EVENT_MENU,new ArrayList<String>());	
	
	Document doc = null;
	try {
		doc = WechatUtils.getDocumentResource("wechat.handler.xml");
	} catch (Exception e) {
		logger.error("wechat.handler.xml parser error");
		e.printStackTrace();
		return;
	}
	Element root = doc.getRootElement();	
	List<Element> handlerEs = root.elements();
	for (Element handlerE : handlerEs) {
		String handlerName = handlerE.elementText("name");		
		List<Element> handlerClassEs = handlerE.elements("handlerClass");
		if (handlerClassEs == null || handlerClassEs.size() == 0){
			continue;
		}
		List<String> handlerClassNames = chains.get(handlerName);		
		if (handlerClassNames == null){
			continue;
		}
		for (Element handlerClassE : handlerClassEs) {
			String clzName = handlerClassE.getText();
			try {
				Class.forName(clzName);
				handlerClassNames.add(clzName);
			} catch (ClassNotFoundException e) {
				logger.error(clzName + " class not fount exception");
				e.printStackTrace();
			}
		}
	}
}

public static HandlerManager getInstance() {
	if (manager == null)
		manager = new HandlerManager();

	return manager;
}

public HandlerChain getChain(String handlerType) {
		List<String> classNames = chains.get(handlerType);	
		if (classNames == null){
			return null;
		}
		HandlerChain chain = new HandlerChain(handlerType);
		try {
			for (String className : classNames) {
				Class<?> clz =Class.forName(className);
				Handler handler = (Handler) clz.newInstance();
				handler.setChain(chain);
				chain.append(handler);
			}
			return chain;
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			e.printStackTrace();
		}
		return null;
	}
}

