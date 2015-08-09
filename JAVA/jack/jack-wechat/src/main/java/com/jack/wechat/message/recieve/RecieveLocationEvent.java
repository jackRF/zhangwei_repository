package com.jack.wechat.message.recieve;

import org.dom4j.Element;

import com.jack.wechat.message.MessageType;

public class RecieveLocationEvent extends RecieveEvent{
	private Double latitude;
	private Double longitude;
	private Double precision;
	
	public Double getLatitude() {
		return latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public Double getPrecision() {
		return precision;
	}
	public RecieveLocationEvent(String msgXml, Element root) {
		super(msgXml, root);
		this.handlerType=MessageType.HANDLER_EVENT_LOCATION;
	}
	@Override
	public void parseXml(Element root) {
		this.latitude=Double.parseDouble(root.elementText("Latitude"));
		this.longitude=Double.parseDouble(root.elementText("Longitude"));
		this.precision=Double.parseDouble(root.elementText("Precision"));
	}
}
