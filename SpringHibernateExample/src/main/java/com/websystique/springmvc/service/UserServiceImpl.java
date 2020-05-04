package com.websystique.springmvc.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.UserDao;
import com.websystique.springmvc.model.User;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	public UserDao userDao;

	@Transactional
	public String register(User user) throws SQLException {
		return userDao.register(user);
	}

	@Transactional
	public boolean findAllEmployees(String uname, String pwd) throws SQLException {
		return userDao.findAllEmployees(uname, pwd);

	}

}