package com.jack.wechat.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang.StringUtils;

/**
 * 杂项
 * @author Administrator
 *
 */
public class SundryUtils {
	/**
	 * 获取一个随机的文件名
	 * @return
	 */
	public static  String getRandomFileName(){
		Random r=new Random();
		int rint=r.nextInt(1000);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(new Date())+StringUtils.leftPad(rint+"", 3);
	}
}
