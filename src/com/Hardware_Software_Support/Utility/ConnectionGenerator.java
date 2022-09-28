package com.Hardware_Software_Support.Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionGenerator {
	
	/*
	 * This is a method to Generate the Connection Between the Application 
	 * And the MySQL DataBase.
	 * (To Use Please Provide MySQl Database Username , Password &
	 * DataBase Name which You have to Use.)
	 */
	
	public static Connection provideConnection() {

		Connection conn = null;
		String username = "root";
		String password = "Harshal@6342";
		String DataBase = "project";
		
		//Do Not Change Below Code.
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String url = "jdbc:mysql://localhost:3306/"+DataBase;

		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;

	}

}
