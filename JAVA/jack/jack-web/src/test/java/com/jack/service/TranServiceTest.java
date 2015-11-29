package com.jack.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.jack.entity.User;
import com.jack.service.impl.UserServiceImpl;
import com.jack.test.AbstractJunitTest;
public class TranServiceTest extends AbstractJunitTest{
	@Autowired
	private UserService userService;
//	private int  insertUser(User user){
//		String sql="insert into t_user(id,user_name,pwd,name)values(:id,:userName,:pwd,:name)";
//		
//		Map<String,Object> args=new HashMap<String,Object>();
//		args.put("id", user.getId());
//		args.put("userName", user.getUserName());
//		args.put("pwd", user.getPwd());
//		args.put("name", user.getName());
//		return this.simpleJdbcTemplate.update(sql, args);
//	}
//	@Test
	public void testA() throws Exception {
		System.out.println("sfsfs:{}");
		logger.info("sddfsdfs");
	}
//	@Test
	public void testc(){
		Transactional transactional=UserServiceImpl.class.getAnnotation(Transactional.class);
		if(transactional!=null){
			log("UserServiceImpl has Annotation Transactional.class");
		}else{
			log("UserServiceImpl has not Annotation Transactional.class");
		}
		String[] aa={"sdfs","4654"};
		Object[] oo={5,"dgds"};
		oo=(Object[])aa;
		log(userService.getClass().getName());
		userService.saveUser(null);
	}
//	@Test
	public void testB() throws Exception {
		User user=new User();
		user.setId(56L);
		user.setName("zzzz");
		user.setPwd("123456");
		user.setUserName("jack");
		log(userService.getClass().getName());
		userService.update(user);
//		userService.saveUser(user);
//		userService.saveUser(user);
//		userService.updateUser(user);
//		insertUser(user);
//		insertUser(user);
	}
	@Test
	public void testf(){
		User user=null;
		sfsdd(user,user.getName()) ;
	}
	public void sfsdd(User user,String name){
		return ;
	}
	private void  log(String msg){
		System.out.println(msg);
	}
}
