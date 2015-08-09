package com.jack.web.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jack.direct.AbstractDirect;
import com.jack.direct.DirectRequest;


public class DirectServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AbstractDirect direct; 
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		final String[] liclass={"com.jack.web.direct.action.Product"};
		final String url=request.getRequestURI();
		if(direct==null){
			direct=new AbstractDirect(){
				@Override
				public Class<?>[] getActionClasses(){
					List<Class<?>> ls=new ArrayList<Class<?>>();
					for(String str:liclass){
						try {
							ls.add(Class.forName(str));
						} catch (ClassNotFoundException e) {
							
							e.printStackTrace();
						}
					}
					Class<?>[] css=new Class<?>[ls.size()]; 
					for(int i=0;i<ls.size();i++){
						css[i]=ls.get(i);
					}
					return  css;
				}
				@Override
				protected String getUrl() {				
					return url;
				}

				@Override
				public String initAPI(String choose) {
					StringBuilder sb = new StringBuilder();
					
					if(choose!=null){
						if(choose.equals("ext")){
							sb.append("Ext.ClassManager.createNamespaces('" + this.getProviderName() + "');");
							sb.append(this.getProviderName()+"=" + this.constructApi()+ ";");
							sb.append("Ext.direct.Manager.addProvider(" + this.getProviderName() + ");");
							return sb.toString();
						}
					}
					sb.append("$.code.createNamespaces('" + this.getProviderName() + "');");
					sb.append(this.getProviderName()+"=" + this.constructApi()+ ";");
					sb.append("$.Direct.addProvider(" + this.getProviderName() + ");");					
					return sb.toString();
				}

				@Override
				protected String getProviderName() {					
					return "jack.Remote.direct";
				}
				@Override
				protected String getNamespace(){
					return "jack";
				}
				@Override
				protected Object[] dataToArgs(Class<?>[] cs, Object data) {
					JSONArray jarray=(JSONArray)data;
					if (cs != null && cs.length > 0) {						
						return JSONArray.parseArray(jarray.toJSONString(), cs).toArray();
					}
					return null;
				}
				@Override
				public String direct(String json) {
					String data="";
					if (json.startsWith("[")) {				
						List<DirectRequest> liRequest =JSONArray.parseArray(json,DirectRequest.class);
						data=JSONObject.toJSONString(this.directMulti(liRequest));
					}else{
						DirectRequest dRequest =JSONObject.parseObject(json,DirectRequest.class);				
						data=JSONObject.toJSONString(this.directSingle(dRequest));
					}
					return data;
				}			
				
			};
		}
		String type="";
		String data = "";
		if(request.getMethod().equals("POST")){
			type = "application/json";
			BufferedReader reader= request.getReader();
			StringWriter strWriter =new StringWriter();
			do{
				strWriter.write(reader.readLine());
			}while(reader.read()!=-1);
			reader.close();
			strWriter.close();
			String json =strWriter.toString();
			data=direct.direct(json);
		}else{
			type = "text/javascript";
			data=direct.initAPI("$");			
		}
		response.setCharacterEncoding("utf-8");
		response.setContentType(type);
		PrintWriter pw=response.getWriter();
		pw.write(data);
		pw.flush();
		pw.close();
	}
}
