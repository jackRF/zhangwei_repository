package com.jack.wechat.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jack.wechat.Carp;
import com.jack.wechat.SessionManager;
import com.jack.wechat.WechatCache;
import com.jack.wechat.WechatContextFactory;
import com.jack.wechat.util.HttpUtils;
import com.jack.wechat.util.WechatUtils;

public class WechatServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static Log logger = LogFactory.getLog(WechatContextFactory.class);
	private static String token;
	@Override
	public void init()throws ServletException{
		WechatContextFactory.getInstance().init(getServletContext());
		WechatCache wechatCache = Carp.getInstance().getCache();
		token=wechatCache.getConfig().getToken();		
	}
	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response)throws ServletException,IOException{
		String signature = request.getParameter("signature");//微信加密签名
		String timestamp = request.getParameter("timestamp");// 时间戳
		String nonce = request.getParameter("nonce");// 随机数
		String echostr = request.getParameter("echostr");// 随机字符串
		boolean flag=WechatUtils.checkAuthentication(signature, timestamp, nonce, echostr, token);
		if(flag){
			if(Carp.getInstance().getCache().getConfig().isFirst()){
				HttpUtils.write(response, echostr);
			}else{
				String requestXML=HttpUtils.read(request, "UTF-8");
				SessionManager.getInstance().buildSession(requestXML, response);
			}
		}else{
			logger.warn("host:" + request.getRemoteHost()
					+ " request content cant't checked.");
		}
	}
	
}
