package com.jack.wechat.util;

import java.io.File;
import java.io.InputStream;
import java.util.Arrays;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;

import com.jack.wechat.Carp;



public class WechatUtils {
	/**
	 * 检查消息验证
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @param echostr
	 * @param token
	 * @return
	 */
	public static boolean checkAuthentication(String signature,
			String timestamp, String nonce, String echostr, String token) {
		
		boolean result = false;
		// 将获取到的参数放入数组
		String[] arrTemp = { token, timestamp, nonce };
		// 按微信提供的方法，对数据内容进行排序
		Arrays.sort(arrTemp);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arrTemp.length; i++) {
			sb.append(arrTemp[i]);
		}
		// 对排序后的字符串进行SHA-1加密
		String pwd = "";
		try {
			pwd = EncryptUtils.SHA1(sb.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
		result = pwd.equals(signature);
		return result;
	}
	public static Document getDocumentResource(String sourceName)
			throws Exception {
		String repertoryPath = Carp.CLASSPATH + File.separator + sourceName;
		SAXReader reader = new SAXReader();
		Document doc = null;
		File repertoryFile = new File(repertoryPath);
		if (repertoryFile.exists() && repertoryFile.isFile()) {
			doc = reader.read(repertoryFile);
		} else {
			InputStream in = Carp.class.getClassLoader().getResourceAsStream("/" + sourceName);
			doc = reader.read(in);
			in.close();
		}
		return doc;
	}
}
