package com.jack.wechat;

import com.jack.wechat.entity.WechatConfiguration;
import com.jack.wechat.entity.WechatMenu;
/**
 * 微信缓存类
 * @author Administrator
 *
 */
public class WechatCache {
	private WechatMenu menu;
	
	private WechatConfiguration config;
	
	private String appName;
	
	private boolean isClusterMaster;
	
	public WechatMenu getMenu() {
		return menu;
	}

	public void setMenu(WechatMenu menu) {
		this.menu = menu;
	}

	public WechatConfiguration getConfig() {
		return config;
	}

	public void setConfig(WechatConfiguration config) {
		this.config = config;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public boolean isClusterMaster() {
		return isClusterMaster;
	}

	public void setClusterMaster(boolean isClusterMaster) {
		this.isClusterMaster = isClusterMaster;
	}

	
}
