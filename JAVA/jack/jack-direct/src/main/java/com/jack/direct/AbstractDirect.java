package com.jack.direct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




public abstract class AbstractDirect {
	
	
	private final static Map<String, DirectProvider> directProviderCache=new HashMap<String,DirectProvider>();
	private DirectProvider provider = null;
	public AbstractDirect(){		
		if (directProviderCache.containsKey(this.getProviderName())) {
			provider = directProviderCache.get(this.getProviderName());
		} else {
			provider = new DirectProvider();
			
			provider.setId(this.getId());			
			provider.setName(getProviderName());
			provider.setType(this.getType());
			provider.setUrl(this.getUrl());
			provider.setNamespace(this.getNamespace());			
			provider.setTimeout(this.getTimeout());
			provider.setMaxRetries(this.getMaxRetries());
			
			provider.configure(this.getActionClasses());			
			directProviderCache.put(this.getProviderName(), provider);			
			this.constructApi();
		}
	}
	public abstract String direct(String json);
	protected  List<DirectResponse> directMulti(List<DirectRequest> requests){	
		List<DirectResponse> listDR=new ArrayList<DirectResponse>();
		for(DirectRequest request:requests){
			listDR.add(directSingle(request));
		}
		return listDR;
	}
	protected  DirectResponse directSingle(DirectRequest request){		
		return processRequest(provider,request);
	}
	protected abstract String getUrl();	
	protected abstract String getProviderName();	
	protected abstract Class<?>[]  getActionClasses();	
	protected abstract  Object[] dataToArgs(Class<?>[] cs,Object data);	
	public  abstract String initAPI(String choose);
	
	public String constructApi(){		
		if(!provider.isConfigured()){
			StringBuilder sb=new StringBuilder();
			sb.append("{");
			sb.append("\"type\":\"");
			sb.append(provider.getType());
			sb.append("\",\"url\":\"");
			sb.append(provider.getUrl());
			sb.append("\"");
			if(!Utility.StringIsNullOrEmpty(provider.getNamespace())){
				sb.append(",\"namespace\":\"");
				sb.append(provider.getNamespace());
				sb.append("\"");
			}
			if (!Utility.StringIsNullOrEmpty(provider.getId())) {
				sb.append(",\"id\":\"");
				sb.append(provider.getId());
				sb.append("\"");
			}
			if(provider.getTimeout()!=null){
				sb.append(",\"timeout\":");
				sb.append(provider.getTimeout());				
			}
			if (provider.getMaxRetries()!= null) {				
				sb.append(",\"maxRetries\":");
				sb.append(provider.getMaxRetries());				
			}
			sb.append(",\"actions\":{");
			int aIndex=0;
			for(DirectAction action:provider.getActions().values()){
				if(aIndex>0){
					sb.append(",");
				}
				sb.append("\"");
				sb.append(action.getName());
				sb.append("\":[");
				int mIndex=0;
				for(DirectMethod method:action.getMethods().values()){					
					if(mIndex>0){
						sb.append(",");
					}
					sb.append("{\"name\":\"");
					sb.append(method.getName());					
					sb.append("\",\"len\":");
					sb.append(method.getLen());
					sb.append("}");
					mIndex++;
				}
				sb.append("]");
				aIndex++;
			}
			sb.append("}}");			
			provider.setApi(sb.toString());
			provider.setConfigured(true);
		}
		return provider.getApi();
	}

	private  DirectResponse processRequest(DirectProvider provider,
			DirectRequest request) {
		DirectResponse r = new DirectResponse(request);
		try {
			r.setResult(execute(provider,request));
		} catch (DirectException e) {
			r.setMessage(e.getMessage());
			r.setType("exception");
		}
		return r;
	}
	private  Object execute(DirectProvider provider,DirectRequest request) throws DirectException{
		DirectAction action = provider.getActions().get(request.getAction());
		if (action == null) {
			throw new DirectException("Unable to find action, " + request.getAction());
		}
		DirectMethod method = action.getMethods().get(request.getMethod());
		if (method == null) {
			throw new DirectException("Unable to find method, " + request.getMethod());
		}
		Class<?> c = action.getActionClass();
		Object[] args = dataToArgs(method.getMethod().getParameterTypes(),request.getData());		
		if((args==null&&method.getLen()==0)||(args!=null&&args.length==method.getLen())){
			try{
				return method.getMethod().invoke(c.newInstance(), args);
			}catch(Exception e){
				throw new DirectException("Error occurred while calling Direct method: " + e.getMessage());
			}
		}else{
			throw new DirectException("Parameters length does not match");
		}			
	}
	
	protected DirectProvider getProvider(){
		return this.provider;
	}	

	protected String getCharacterEncoding() {
		return "utf-8";
	}

	protected String getType() {
		return "remoting";
	}

	protected Integer getTimeout() {
		return null;
	}

	protected String getNamespace() {
		return null;
	}

	protected Integer getMaxRetries() {
		return null;
	}

	protected String getId() {
		return null;
	}	
}
