package com.jack.wechat.message.send;

import com.jack.wechat.message.MessageType;
import com.jack.wechat.message.recieve.RecieveMessage;

public class SendMusicMessage extends SendMessage {
	private String title;
	private String description;
	private String musicURL;
	private String hQMusicUrl;
	private String thumbMediaId;
	
	public SendMusicMessage(RecieveMessage recieveMessage) {
		super(recieveMessage);
		this.msgType=MessageType.TYPE_MUSIC;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMusicURL() {
		return musicURL;
	}

	public void setMusicURL(String musicURL) {
		this.musicURL = musicURL;
	}

	public String gethQMusicUrl() {
		return hQMusicUrl;
	}

	public void sethQMusicUrl(String hQMusicUrl) {
		this.hQMusicUrl = hQMusicUrl;
	}

	public String getThumbMediaId() {
		return thumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}

	@Override
	protected String toMessageBody() {
		StringBuilder sb=new StringBuilder();
		sb.append("<Music>");
		sb.append("<Title><![CDATA["+this.title+"]]></Title>");
		sb.append("<Description><![CDATA["+this.description+"]]></Description>");
		sb.append("<MusicUrl><![CDATA["+this.musicURL+"]]></MusicUrl>");
		sb.append("<HQMusicUrl><![CDATA["+this.hQMusicUrl+"]]></HQMusicUrl>");
		sb.append("<ThumbMediaId><![CDATA["+this.thumbMediaId+"]]></ThumbMediaId>");
		sb.append("</Music>");	
		return sb.toString();
	}

}
