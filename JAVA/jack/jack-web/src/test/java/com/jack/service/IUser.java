package com.jack.service;

import org.springframework.transaction.annotation.Transactional;

import com.jack.entity.User;
public interface IUser {
	@Transactional
	void update(User user);
	@Transactional
	void save(User user);
}
