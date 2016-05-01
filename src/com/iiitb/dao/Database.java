package com.iiitb.dao;

import java.sql.Connection;
import java.io.Serializable;
import java.sql.DriverManager;

import java.sql.SQLException;


public class Database implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public Connection getConnection() { 
		Connection conn=null;
        try
		{
			Class.forName("com.mysql.jdbc.Driver");
			try
			{
				conn=DriverManager.getConnection("jdbc:mysql://localhost/datamodelling","root", "");
				System.out.println("Created DB Connection....");
				return conn;
			}
			catch (SQLException se)
			{
				System.out.println("SqlException: " + se.getMessage());
				se.printStackTrace(System.out);
				return conn;
				
			}
		}
		catch (ClassNotFoundException e)
		{
			System.out.println("ClassNotFound: " + e.getMessage());
			return conn;
		}
        
        
    } 
  
    public  void close(Connection conn) { 
        try { 
            conn.close(); 
        } 
        catch (Exception ex) { 
        } 
    }

        
}