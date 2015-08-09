package com.jack.wechat.task.work;

import com.jack.wechat.task.Task;
import com.jack.wechat.task.result.TaskResult;

public abstract class Work {
	protected Task task;
	protected TaskResult result;	
	
	public void setTask(Task task) {
		this.task = task;
	}
	public TaskResult getResult() {
		return result;
	}
	public void setResult(String result) {
		 this.result=new TaskResult(result){
			@Override
			protected void parseResult() {
			}
		 };
	}
	public abstract void toDo() throws WorkException;

	public abstract void failedToDo() throws WorkException;
}
