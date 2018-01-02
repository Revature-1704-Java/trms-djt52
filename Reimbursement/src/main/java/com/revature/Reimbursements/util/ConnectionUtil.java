package com.revature.Reimbursements.util;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
	public static Connection getConnection() throws SQLException, IOException {
		Properties prop = new Properties();
		//InputStream in = new FileInputStream("connection.properties");
		//prop.load(in);
		/*
		String url = prop.getProperty("url");
		String user = prop.getProperty("user");
		String password = prop.getProperty("password");
		*/
		try {
		    Class.forName("oracle.jdbc.driver.OracleDriver");
		} 
		catch (ClassNotFoundException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		} 
		String url= "jdbc:oracle:thin:@mydbinstance.cpwvj99ccxdv.us-east-2.rds.amazonaws.com:1521:ORCL";
		String user = "proj1";
		String password = "proj1";
		return DriverManager.getConnection(url, user, password);
	}
}
