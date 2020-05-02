package com.websystique.springmvc.dao;

import java.sql.SQLException;

import com.websystique.springmvc.model.Login;
import com.websystique.springmvc.model.User;

public interface UserDao {
	String register(User user) throws SQLException;

	boolean findAllEmployees(String uname, String pwd) throws SQLException;
}
