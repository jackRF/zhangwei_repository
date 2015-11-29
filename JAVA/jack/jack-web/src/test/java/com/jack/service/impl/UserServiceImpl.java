package com.jack.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jack.entity.User;
import com.jack.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	protected SimpleJdbcTemplate simpleJdbcTemplate;

	private String sqlScriptEncoding;
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.simpleJdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}
	@SuppressWarnings("deprecation")
	private int  insertUser(User user){
		String sql="insert into t_user(id,user_name,pwd,name)values(:id,:userName,:pwd,:name)";
		
		Map<String,Object> args=new HashMap<String,Object>();
		args.put("id", user.getId());
		args.put("userName", user.getUserName());
		args.put("pwd", user.getPwd());
		args.put("name", user.getName());
		return this.simpleJdbcTemplate.update(sql, args);
	}
	public int saveUser(User user){
		System.out.println(this.getClass().getName());
		insertUser(user);
		insertUser(user);
		return 0;
		
	}
	@Override
	public int updateUser(User user) {
//		saveUser(user);
		insertUser(user);
		insertUser(user);
		return 0;
	}
	@Override
	public void update(User user) {
		System.out.println(this.getClass().getName());
//		updateUser(user);
		
	}
	@Override
	public void save(User user) {
		saveUser(user);
		
	}
	
}
