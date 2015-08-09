package com.jack.wechat.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;

import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.jack.wechat.WechatContextFactory;

public class HttpUtils {
	public static void main(String[] args) {
		try {
			InputStream in=doHttpsGetImage("https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQER8ToAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL2tVZ2FjQ0xsT1MxQWpYRTNzbUM5AAIEzZowVQMEAAAAAA==");
			FileUtil.copyFile(in, "/sdfsfs1133.jpg");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	public static Log logger = LogFactory.getLog(WechatContextFactory.class);
	public static String doHttpsPost(String url, String param) throws Exception {		
		return postApache(url, param);
	}
	public static String doHttpsGet(String url) throws Exception {
		HttpEntity entity=doHttps(url);
		if(entity!=null){
			return EntityUtils.toString(entity, "utf-8");
		}
		return null;
	}
	public static InputStream doHttpsGetImage(String url){
		HttpEntity entity=doHttps(url);
		if(entity!=null){
			try {
				return entity.getContent();				
			} catch (IOException e) {				
				e.printStackTrace();
			}
		}
		return null;
	}
	public static HttpEntity doHttps(String url){
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(url);		
		try{
			HttpResponse res = client.execute(get);
			if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				return  res.getEntity();						
			}else{
				logger.info("apache 返回状态:"+res.getStatusLine().getStatusCode());
			}
		}catch (Exception e){
			logger.info("apache:"+e);
			throw new RuntimeException(e);
		}
		return null;
	}
	
  
  
	/**
	 * 
	 * @param url
	 * @param param
	 */
	public static String postApache(String url, String param){
		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		String rep = "";
		try{
			StringEntity s = new StringEntity(param,"utf-8");
			s.setContentEncoding("UTF-8");
			s.setContentType("application/json");
			post.setEntity(s);

			HttpResponse res = client.execute(post);
			if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				HttpEntity entity = res.getEntity();
				rep = EntityUtils.toString(entity, "utf-8");				
			}else{
				logger.info("apache 返回状态:"+res.getStatusLine().getStatusCode());
			}
		}catch (Exception e){
			logger.info("apache:"+e);
			throw new RuntimeException(e);
		}
		return rep;
	}
	public static void write(HttpServletResponse response, String message)
			throws IOException {
		PrintWriter writer = null;
		try {
			response.setHeader("Charset", "UTF-8");
			response.setCharacterEncoding("UTF-8");
			writer = response.getWriter();
			writer.write(message);
		} finally {
			if (writer != null){
				writer.close();
			}
		}
	}
	public static String read(HttpServletRequest request, String encoding)
            throws IOException {
		StringWriter sw = new StringWriter();
        copy(request.getInputStream(), sw, encoding);
        return sw.toString();
    }

	public static void copy(InputStream input, Writer output, String encoding)
			throws IOException {
		if (encoding == null) {
			copy(input, output);
		} else {
			InputStreamReader in = new InputStreamReader(input, encoding);
			copy(in, output);
		}
	}

	public static void copy(InputStream input, Writer output)
			throws IOException {
		InputStreamReader in = new InputStreamReader(input);
		copy(in, output);
	}

	public static int copy(Reader input, Writer output) throws IOException {
		long count = copyLarge(input, output);
		if (count > Integer.MAX_VALUE) {
			return -1;
		}
		return (int) count;
	}

	public static long copyLarge(Reader input, Writer output)
			throws IOException {
		char[] buffer = new char[1024 << 2];
		long count = 0;
		int n = 0;
		while (-1 != (n = input.read(buffer))) {
			output.write(buffer, 0, n);
			count += n;
		}
		return count;
	}
}
