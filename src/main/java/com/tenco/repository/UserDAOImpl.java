package com.tenco.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tenco.models.User;

/**
 * 인터페이스 설계 생략
 */
public class UserDAOImpl {

	private static final String URL = "jdbc:mysql://localhost:3306/db_todo2?serverTimezone=Asia/Seoul"; 
	private static final String USER = "root"; 
	private static final String PASSWORD = "asd123"; 
	
	public UserDAOImpl() {
		
		// Runnable runnable; // 분리의 원칙은 지킴(run() 하나뿐임)
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private Connection getConnetion() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
	
	public List<User> getAllUsers(){
		
		List<User> list = new ArrayList<>();
		
		String sql = " SELECT * FROM users ";
		try (Connection conn = getConnetion();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery()){
			
			while(rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setEmail(rs.getString("email"));
				list.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
