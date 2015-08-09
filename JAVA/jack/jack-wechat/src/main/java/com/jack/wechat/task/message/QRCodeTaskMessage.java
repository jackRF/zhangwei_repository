package com.jack.wechat.task.message;


import org.apache.commons.lang.StringUtils;
/**
 * 
 * @author zhangwei
 * @date 2015-3-25
 *
 */
public class QRCodeTaskMessage implements TaskMessage {
	private int expire_seconds;//当action_name=QR_LIMIT_STR_SCENE时 值为0
	private String action_name;
	private int scene_id;
	private String scene_str;
	/**
	 * 有效时间 >0 临时，=0 永久 
	 * @return
	 */
	public int getExpire_seconds() {
		return expire_seconds;
	}
	public int getScene_id() {
		return scene_id;
	}
	public String getScene_str() {
		return scene_str;
	}
	/**
	 * 临时二维码请求
	 * 场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000）
	 * @param scene_id
	 */
	public QRCodeTaskMessage(int scene_id){
		expire_seconds=1800;
		this.action_name="QR_SCENE";		
		this.scene_id=scene_id!=0?scene_id:888;
	}
	/**
	 * expire_seconds>0 临时 <0永久
	 * @param expire_seconds
	 */
	public void setExpire_seconds(int expire_seconds){
		if(expire_seconds>0){
			if(scene_id!=0){//可临时，可永久
				this.expire_seconds=expire_seconds;
				this.action_name="QR_SCENE";
			}
		}else{
			this.expire_seconds=0;
			this.action_name="QR_LIMIT_STR_SCENE";
		}
	}	
	/**
	 * 永久二维码请求
	 * 场景值ID（字符串形式的ID），字符串类型，长度限制为1到64，仅永久二维码支持此字段
	 * @param scene_str
	 */
	public QRCodeTaskMessage(String scene_str){		
		this.action_name="QR_LIMIT_STR_SCENE";	
		this.scene_str=StringUtils.isEmpty(scene_str)?"888":scene_str;
	}	
	
	
	
	public String toJson() {				
		if(expire_seconds>0){
			/**
			 * 临时
			 */
			return "{\"expire_seconds\":"+expire_seconds+",\"action_name\":\""+action_name+"\",\"action_info\":{\"scene\":{\"scene_id\": "+scene_id+"}}}";			
		}else{
			/**
			 * 永久
			 */
			if(this.scene_id>0){
				return "{\"action_name\":\"QR_LIMIT_STR_SCENE\",\"action_info\":{\"scene\":{\"scene_id\":"+scene_id+"}}}";
			}else{
				return "{\"action_name\":\"QR_LIMIT_STR_SCENE\",\"action_info\":{\"scene\": {\"scene_str\":\""+scene_str+"\"}}}";
			}
		}
	}
}
