package com.jack.service;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.annotation.Role;
import org.springframework.test.annotation.Rollback;

import com.jack.entity.User2;
import com.jack.test.AbstractTransactionalJunitTest;

public class UserServiceTest extends AbstractTransactionalJunitTest {
	private void insertUser(User2 user) {
		Map<String,Object> argsMap=new HashMap<String,Object>();
		argsMap.put("username", user.getUsername());
		argsMap.put("password", user.getPassword());
		argsMap.put("name", user.getName());
		String sql="insert into t_user(username,password,name)values(:username,:password,:name)";
		super.simpleJdbcTemplate.update(sql, argsMap);
	}
	@Rollback(false)
	@Test
	public void testa() {
		User2 user=new User2();
		user.setUsername("ss3");
		user.setPassword("2354762");
		user.setName("12");
		insertUser(user);

	}
}
