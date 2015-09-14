package com.jack.core.util;


import org.junit.Test;

public class EncryptUtilsTest {
	@Test
	public void sha1() {
		System.out.println(EncryptUtils.SHA1("dfgdsgdfghrtyryjry4rmdgdg"));
		
		System.out.println(EncryptUtils.SHA("dfgdsgdfghrtyryjry4rmdgdg"));
		
		
	}
	@Test
	public void md5() {
		System.out.println("MD5:"+EncryptUtils.MD5("123456"));

	}
	@Test
	public void sdfsf() {
		int i=0xff;
		byte b=-7;
		System.out.println(b&i);
		System.out.println(Integer.toHexString(249));
	}
}
