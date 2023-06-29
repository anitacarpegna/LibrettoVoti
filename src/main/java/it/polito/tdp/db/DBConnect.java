package it.polito.tdp.db;

import java.sql.*;

public class DBConnect {
	public static Connection getConnection() throws SQLException {
		String jdbcURL = "jdbc:mariadb://localhost/librettovoti?user=root&password=root";
		Connection conn = DriverManager.getConnection(jdbcURL);
		return conn;
	}
}
