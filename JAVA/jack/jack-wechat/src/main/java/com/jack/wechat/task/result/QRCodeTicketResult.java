package com.jack.wechat.task.result;



public class QRCodeTicketResult extends TaskResult {
	private String ticket;
	private Integer expire_seconds;
	private String url;
	public QRCodeTicketResult(String json) {
		super(json);		
	}
	
	public String getTicket() {
		return ticket;
	}

	public Integer getExpire_seconds() {
		return expire_seconds;
	}

	public String getUrl() {
		return url;
	}
	
	@Override
	protected void parseResult() {		
		this.ticket=super.getString("ticket");
		this.expire_seconds=super.getInt("expire_seconds");
		this.url=super.getString("url");
	}
}
