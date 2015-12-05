package com.jack.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Service;

import com.jack.entity.User;
import com.jack.service.IUserService;
@Service
public class UserServiceImpl implements IUserService  {
	protected SimpleJdbcTemplate simpleJdbcTemplate;
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.simpleJdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}
	@SuppressWarnings("deprecation")
	private int  insertUser(User user){
		String sql="insert into t_user(user_name,pwd,name)values(:userName,:pwd,:name)";
		
		Map<String,Object> args=new HashMap<String,Object>();
		args.put("id", user.getId());
		args.put("userName", user.getUserName());
		args.put("pwd", user.getPwd());
		args.put("name", user.getName());
		return this.simpleJdbcTemplate.update(sql, args);
	}
	@Override
	public void saveUser(User user) {
		insertUser(user);
	}

}
