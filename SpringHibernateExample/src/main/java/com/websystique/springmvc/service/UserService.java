package com.websystique.springmvc.service;

import java.sql.SQLException;

import com.websystique.springmvc.model.Login;
import com.websystique.springmvc.model.User;

public interface UserService {
	String register(User user) throws SQLException;

	boolean findAllEmployees(String uname, String pwd) throws SQLException;
	
	//public String getJWTToken(String username);
}
