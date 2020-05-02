package com.websystique.springmvc.service;

import java.security.SecureRandom;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.UserDao;
import com.websystique.springmvc.model.Login;
import com.websystique.springmvc.model.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

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