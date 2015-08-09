package com.jack.wechat.task.work;

public class WorkException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WorkException(String message) {
		super(message);
	}

	public WorkException(String message, Throwable cause) {
		super(message, cause);
	}
}
