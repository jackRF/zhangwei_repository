package com.jack.wechat.message.recieve;

import org.dom4j.Element;

import com.jack.wechat.message.MessageType;

public class RecieveLocationMessage  extends RecieveGeneralMessage{
	private Double locationX;
	private Double locationY;
	private Integer scale;
	private String label;
	
	public Double getLocationX() {
		return locationX;
	}
	public Double getLocationY() {
		return locationY;
	}
	public Integer getScale() {
		return scale;
	}
	public String getLabel() {
		return label;
	}
	public RecieveLocationMessage(String msgXml, Element root) {
		super(msgXml, root);
		this.handlerType=MessageType.HANDLER_TYPE_LOCATION;
	}
	@Override
	public  void parseXml(Element root) {
		super.parseXml(root);
		this.locationX=Double.parseDouble(root.elementText("Location_X"));
		this.locationY=Double.parseDouble(root.elementText("Location_Y"));
		this.scale=Integer.parseInt(root.elementText("Scale"));
		this.label=root.elementText("Label");
	}

}
