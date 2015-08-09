package com.jack.wechat.task;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jack.wechat.Carp;
import com.jack.wechat.WechatContextFactory;
import com.jack.wechat.task.message.TaskMessage;
import com.jack.wechat.task.work.Work;
import com.jack.wechat.task.work.WorkException;
import com.jack.wechat.util.HttpUtils;

public abstract class Task implements Runnable{
	public static Log logger = LogFactory.getLog(WechatContextFactory.class);

	public static final int SUCCESS_CODE = 0;

	public static final String METHOD_POST = "post";
	public static final String MEHTOD_GET = "get";

	protected String url;
	protected TaskMessage message;
	protected Work work;
	protected String name;
	protected String method;
	protected String instance;
	protected int repeatCount;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public TaskMessage getMessage() {
		return message;
	}

	public void setMessage(TaskMessage message) {
		this.message = message;
	}

	public Work getWork() {
		return work;
	}

	public void setWork(Work work) {
		this.work = work;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getInstance() {
		return instance;
	}

	public void setInstance(String instance) {
		this.instance = instance;
	}

	public int getRepeatCount() {
		return repeatCount;
	}

	public void setRepeatCount(int repeatCount) {
		this.repeatCount = repeatCount;
	}
	/**
	 * 任务的克隆方法，用来复制一个新任务
	 */
	@Override
	public Task clone() {
		Task newTask = null;
		try {
			Class<?> clz = Class.forName(this.getClass().getName());
			newTask = (Task) clz.newInstance();
			newTask.setName(this.name);
			newTask.setUrl(this.url);
			newTask.setWork(this.work);
			newTask.setMethod(this.method);
			newTask.setInstance(this.instance);
			newTask.setMessage(this.message);
		} catch (Exception e) {
			throw new TaskException("clone task error.", e);
		}
		return newTask;
	}
	public void send() throws WorkException {		
		repeatCount++;
		TaskManager.getInstance().addTask(this, true);
	}

	public void send(boolean isSynchronized) throws WorkException {
		repeatCount++;
		TaskManager.getInstance().addTask(this, isSynchronized);
	}
	public abstract void generateUrl();
	public void run() {		
		String responseStr = null;
		try {			
			changeToken();
			changeSecret();
			changeAppid();
			generateUrl();
			logger.debug("url : " + url);					
			if (method.equals(METHOD_POST)){
				String sendMessage = "";
				if (message != null){
					sendMessage = message.toJson();
					logger.debug("sendMessage : " + sendMessage);
				}
				responseStr = HttpUtils.doHttpsPost(url, sendMessage);
			}
			else{
				responseStr = HttpUtils.doHttpsGet(url);
			}			
			logger.debug("json result : " + responseStr);
			callbackWork(responseStr);
		} catch (Exception e) {				
			throw new WorkException("url error : " + url, e);
		}
	}
	/**
	 * 任务完成后的回调工作
	 * @param result
	 */
	protected void callbackWork(String result) {
		if (work != null) {
			work.setResult(result);
			work.setTask(this);
			if (work.getResult().getErrorCode()!= SUCCESS_CODE) {
				work.failedToDo();
			} else{
				work.toDo();
			}
		}
	}
	
	/**
	 *  替换所需的access_token
	 */
	public void changeToken() {
		if (StringUtils.contains(url, "ACCESS_TOKEN")) {
			url = StringUtils.replace(url, "ACCESS_TOKEN", Carp.getInstance().getCache().getConfig().getAccessToken());
		}
	}
	
	/**
	 *  替换所需的appid
	 */
	public void changeAppid() {
		if (StringUtils.contains(url, "APP_ID")) {
			url = StringUtils.replace(url, "APP_ID", Carp.getInstance().getCache().getConfig().getAppId());
		} else {
			String oldAppid = StringUtils.substringAfter(url, "appid=");
			if (StringUtils.contains(oldAppid, "&")) {
				oldAppid = StringUtils.substringBetween(url, "appid=",
						"&");
			}
			url = StringUtils.replace(url, oldAppid, Carp.getInstance().getCache().getConfig().getAppId());
		}
	}
	/**
	 *  替换所需的secret
	 */
	public void changeSecret() {
		if (StringUtils.contains(url, "APP_SECRET")) {
			url = StringUtils.replace(url, "APP_SECRET", Carp.getInstance().getCache().getConfig().getAppSecret());
		} else {
			String oldSecret = StringUtils.substringAfter(url, "secret=");
			if (StringUtils.contains(oldSecret, "&")) {
				oldSecret = StringUtils.substringBetween(url, "secret=",
						"&");
			}
			url = StringUtils.replace(url, oldSecret, Carp.getInstance().getCache().getConfig().getAppSecret());
		}
	}
}
