package com.websystique.springmvc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.model.Login;
import com.websystique.springmvc.model.User;

@Repository("userDao")

public class UserDaoImpl extends AbstractDao<Integer, Login> implements UserDao {
	PreparedStatement stmt;
	ResultSet resultSet;

	List<Integer> allCustomer = new ArrayList<Integer>();
	/*
	 * @Autowired DataSource datasource;
	 * 
	 * @Autowired JdbcTemplate jdbcTemplate;
	 */
	/*
	 * public User register(User user) { String sql =
	 * "insert into users_details values(?,?,?,?,?,?,?)"; jdbcTemplate.update(sql,
	 * new Object[] { user.getUsername(), user.getPassword(), user.getFirstname(),
	 * user.getLastname(), user.getEmail(), user.getAddress(), user.getPhone() });
	 * 
	 * return user; }
	 */
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	public String register(User user) throws SQLException {
		String msg = "";
		Connection con = null;
		if (user.getUsername()!=null && user.getPassword()!=null && user.getPhone()!=0) {
			try {
				if ((con == null) || (con.isClosed())) {
					con = getConnection();
				}
				String sql = "insert into customer_details values(?,?,?)";
				stmt = con.prepareStatement(sql);
				stmt.setString(1, user.getUsername());
				stmt.setString(2, user.getPassword());
				stmt.setLong(3, user.getPhone());
				stmt.executeUpdate();
				msg = "Registration successful";
			} catch (Exception e) {

				e.printStackTrace();
			} finally {
				System.out.println("connection closed");
				con.close();
			}
		} else {
			System.out.println("Values missing");
		}
		return msg;
	}

	@SuppressWarnings({ "unchecked", "unused" })

	public boolean findAllEmployees(String uname, String pwd) throws SQLException {
		boolean isValidUser = false;

		Connection con = null;
		System.out.println("uname from form--" + uname);
		try {
			if ((con == null) || (con.isClosed())) {
				con = getConnection();
			}
			String sql = "select * from customer_details where Username = '" + uname + "' ";
			System.out.println("---" + sql);
			stmt = con.prepareStatement(sql);
			System.out.println("after statement");
			// stmt.setString(1, uname);
			resultSet = stmt.executeQuery();
			System.out.println("after resukltset execute" + resultSet.toString());
			if (resultSet == null) {
				System.out.println("No records found in DB");
			} else if (resultSet != null)
				while (resultSet.next()) {
					String username = resultSet.getString("Username");
					String password = resultSet.getString("Password");
					Long phone = resultSet.getLong("Phone");
					System.out.println("username from user" + uname);
					System.out.println("username from db" + username);
					System.out.println("usnername is++" + username.equals(uname));
					System.out.println("password is++" + password);
					System.out.println("phone number is++" + phone);

					if (password.equals(pwd)) {
						System.out.println("Cred correct");
						isValidUser = true;
					} else {
						System.out.println(
								"An error occurred while fetching the user details from the database or wrong cred");

						isValidUser = false;

					}

				}
			resultSet.close();
			// System.out.println("list having customer details searched" + allCustomer);
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			System.out.println("connection closed");
			con.close();
		}

		return isValidUser;
	}

	/*
	 * public User validateUser(Login login) { String sql =
	 * "select * from users_details where username='" + login.getUsername() +
	 * "' and password='" + login.getPassword() + "'"; List<User> users =
	 * jdbcTemplate.query(sql, new UserMapper()); return users.size() > 0 ?
	 * users.get(0) : null; }
	 */

	public Connection getConnection() throws SQLException {
		System.out.println("inside DB class");
		try {

			Class.forName("com.mysql.jdbc.Driver");

		} catch (ClassNotFoundException e) {

			e.printStackTrace();

		}

		System.out.println("Oracle JDBC Driver Registered!");

		Connection connection = null;

		try {

			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/SpringREST",
					"root", "hussain");
			System.out.println("database connected!");
		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();

		}

		if (connection != null) {

		} else {
			System.out.println("Failed to make connection!");
		}

		return connection;
	}
}
