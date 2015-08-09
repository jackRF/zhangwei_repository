package com.jack.wechat.entity;

public class WechatConfiguration {
	/**
	 * AppID(应用ID)
	 */
	private String appId;
	
	/**
	 * AppSecret(应用密钥)
	 */
	private String appSecret;
	
	/**
	 * 描述
	 */
	private String description;
	
	/**
	 * 网站域名
	 */
	private String domain;
	
	/**
	 * A task of getting an access token, not a recevie token.
	 * 填写接口配置信息时,需要的Token.
	 */
	private String token;
	
	private boolean isFirst;
	
	private String accessToken;
	
	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isFirst() {
		return isFirst;
	}

	public void setFirst(boolean isFirst) {
		this.isFirst = isFirst;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
}
