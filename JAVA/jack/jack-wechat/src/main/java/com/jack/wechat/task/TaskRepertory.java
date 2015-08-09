package com.jack.wechat.task;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Element;

import com.jack.wechat.WechatContextFactory;
import com.jack.wechat.task.work.Work;
import com.jack.wechat.util.WechatUtils;

public class TaskRepertory {

	public static Log logger = LogFactory.getLog(WechatContextFactory.class);

	/** 任务集合 **/
	private final Map<String, Task> tasks;
	/** 回调工作集合 **/
	private final Map<String, String> taskWorkNames;

	private static TaskRepertory repertory;

	private TaskRepertory() {
		tasks = new HashMap<String, Task>();
		taskWorkNames = new HashMap<String, String>();
	}
	public static TaskRepertory getInstance() {
		if (repertory == null){
			repertory = new TaskRepertory();
		}
		return repertory;
	}

	/**
	 *初始化，读取wechat.task.xml文件
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void init() throws Exception {
		Document doc = WechatUtils.getDocumentResource("wechat.task.xml");
		if (doc == null){
			return;
		}
		List<Element> taskEs = doc.getRootElement().elements("task");

		for (Element taskE : taskEs) {
			String name = taskE.elementText("name");
			String url = taskE.elementText("url");
			String method = taskE.elementText("method");
			String workClassName = taskE.elementText("work");
			String instance = taskE.elementText("instance");
			String className = taskE.elementText("class");

			Task task = null;
			try {
				if (className == null) {
					task = new CommonTask();
				} else {
					Class<?> taskClz = Class.forName(className);
					task = (Task) taskClz.newInstance();
				}
			} catch (Exception e) {
				logger.warn("task class not found : " + className);
			}

			task.setName(name);
			task.setUrl(url);
			task.setMethod(method == null ? "post" : method);
			task.setInstance(instance == null ? "multi" : instance);

			if (StringUtils.isNotEmpty(workClassName)) {
				try {
					Class.forName(workClassName);					
					tasks.put(name, task);
					taskWorkNames.put(name, workClassName);
				} catch (ClassNotFoundException e) {
					logger.warn("work class not found : " + workClassName);
				} catch (Exception e) {
					logger.warn("work class not be instance : " + workClassName);
				}

			} else {
				tasks.put(name, task);
			}
		}
	}

	/**
	 * 根据名称获得任务
	 * @param name
	 * @return
	 * @throws TaskException
	 */
	public Task getTaskByName(String name) throws TaskException {
		Task task = tasks.get(name);

		if (task != null) {
			if (!task.getInstance().equals("single")){
				task = task.clone();
			}
			String workClzName = taskWorkNames.get(name);
			if (workClzName != null) {
				try {
					Class<?> workClz = Class.forName(workClzName);
					Work work = (Work) workClz.newInstance();
					task.setWork(work);
				} catch (Exception e) {
					throw new TaskException("task's work class is error.", e);
				}
			}
		} else {
			throw new TaskException("task not found : " + name);
		}

		return task;
	}
	
}