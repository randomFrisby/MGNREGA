package in.nic.mgnrega.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	
	public static Connection provideConnection() {
		/*
		 * method will be called whenever we require 
		 * to establish connection to the database
		 */
		
		Connection conn= null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		
		String url = "jdbc:mysql://localhost:3306/mgnregadb";
		try {
			conn = DriverManager.getConnection(url, "root", "root");	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return conn;
	}

}
