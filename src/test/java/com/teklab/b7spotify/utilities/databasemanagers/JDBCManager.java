package com.teklab.b7spotify.utilities.databasemanagers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.teklab.b7spotify.utilities.reportManagers.Log4jManager;



public class JDBCManager {
     //SQLite -- JDBC Manager
	//                             jdbc:mysql://191.67.78.19:3360/StudentDB.db
	//URL Syntax if using mysql:"jdbc:mysql://IPaddress:portnumber/db_name"
	//public static String DB_URL = "jdbc:mysql:C"\\Users\\Pro User\\Desktop\\Teklab Class\\Selenium Class\\Selenium Notes\\
	public static String DB_URL = "jdbc:sqlite:studentDB.db";
	//Constant for Database User name 
	public static String DB_USER = "root";
	//Constant for Database password
	public static String DB_PASSWORD="root";
	//Connection object 
	static Connection con = null;
	
	
	private static Statement stmt;
	
	private static void setUp()throws Exception{
		try {
			//if using mySQL connection.Use Class.forName("com.mysql.jbdc.Driver")
			Class.forName("org.sqlite.JDBC");
			Log4jManager.info("Connecting to Database...");
			
			//get connection to db
			//con = DriverManager.getConnection(dbUrl,DB_USER,DB_password)
			con = DriverManager.getConnection(DB_URL);
			if(con !=null) {
				Log4jManager.info("connected to the database");
			}else {
				Log4jManager.error("failed to connect to the database");
			}
			//statement object to send the sql statement to the database
			stmt = con.createStatement();
		}catch(SQLException e) {
			Log4jManager.error("SQLException,please check database connection information");
			
		}
	}
	public static ArrayList<String> getDataList(String query,int startCol,int endCol )throws Exception{
		    setUp();
		    
		    ArrayList<String> alist = new ArrayList<String>();
		    
		    try {
		    	//get the contents of user info table from db:
		    	ResultSet res = stmt.executeQuery(query);
		    	//res.next()returns true if there is any next record else return false
		    	while (res.next()) {
		    		for(int i = startCol; i <= endCol; i++) {
		    			alist.add(res.getString(i).toString());
		    		}
		    	}
		    	Log4jManager.debug("--retrieved data cell from database:" + alist);
		    }catch(SQLException e) {
		    	e.printStackTrace();
		    }
		    tearDown();
		    return alist;
	}
	public static String  getData(String query, String columnLabel) throws Exception{
		setUp();
		String data = null;
		try {
			//get the contents of user info table from db
			ResultSet res = stmt.executeQuery(query);
			
			while(res.next()) {
				data = res.getString(columnLabel).toString();
			}
			Log4jManager.debug("retrieved data cell from database:" + data);
		}catch(SQLException e) {
			Log4jManager.error("---failede to retrieve data cell----");
		}
		tearDown();
		return data;
	
	}
	private static void tearDown()throws Exception{
		// close db connection
		if(con !=null) {
			try {
				Log4jManager.info("closing database connection ");
				con.close();
			}catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
}
