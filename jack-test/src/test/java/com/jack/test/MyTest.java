package com.jack.test;

import org.junit.Test;

public class MyTest {
	@Test
	public void testa(){
		System.out.println("hello word");
	}
	@Test
	public void testb(){
		int i=queryData(1);
		System.out.println("queryData(1) return:i|"+i);
		String s=queryData(2);
		System.out.println("queryData(1) return:s|"+s);
	}
	@SuppressWarnings("unchecked")
	private <R> R queryData(Integer type){
		if(type==1){
			return (R)Integer.valueOf(9);
		}else if(type==2){
			return (R)"sfsfs";
		}
		return null;
		
	}
}
