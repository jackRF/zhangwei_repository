package com.jack.wechat.task;

import java.util.Stack;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



import com.jack.wechat.Carp;
import com.jack.wechat.WechatContextFactory;



/**
 * 客服消息任务管理器
 * @author CaryXu
 *
 */
public class TaskManager {

	public static Log logger = LogFactory.getLog(WechatContextFactory.class);
	
	/** 是否已得到访问token **/
	private Boolean isGetToken;
	private static TaskManager taskManager;
	/** 异步发送客服消息任务的线程池 **/
	private final ExecutorService pool;
	/** 等待发送消息的集合 **/
	private final Stack<Task> pauseTasks;

	private TaskManager() {
		pool = Executors.newFixedThreadPool(30);
		pauseTasks = new Stack<Task>();
		isGetToken = false;
	}

	public static TaskManager getInstance() {
		if (taskManager == null)
			taskManager = new TaskManager();

		return taskManager;
	}
	
	/**
	 * 添加消息任务，异步执行
	 * @param task 客服消息任务
	 */
	public void addTask(Task task){
		addTask(task,false);
	}

	/**
	 * 添加消息任务，可控制是否同步执行
	 * @param task 客服消息任务
	 * @param isSynchronized 同步标记，如果为false，将会异步执行
	 */
	public void addTask(Task task, boolean isSynchronized) {
		synchronized (isGetToken) {
			if (task.getName().equals("token")) {
				if (isGetToken == false) {
					isGetToken = true;
					executeTask(task, isSynchronized);
				}
			} else {
				if (isGetToken || Carp.getInstance().getCache().getConfig().getAccessToken() == null) {
					pauseTasks.add(task);
				} else {
					executeTask(task, isSynchronized);
				}
			}
		}
	}

	private void executeTask(Task task, boolean isSynchronized) {
		if (isSynchronized)
			task.run();
		else
			pool.submit(task);
	}

	/**
	 * 设定access_token
	 * @param status
	 */
	public void setTokenStatus(boolean status) {
		synchronized (isGetToken) {
			isGetToken = status;
			logger.info("modify token status ： "+status);
			if (isGetToken == false && pauseTasks.size() > 0) {
				logger.info("commit task");
				while(!pauseTasks.isEmpty()){
					Task task = pauseTasks.pop();
					pool.submit(task);
				}
			}
		}
	}
	
	public void destroy(){
		pool.shutdown();
	}
}
