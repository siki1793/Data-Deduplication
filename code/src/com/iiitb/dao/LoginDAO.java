package com.iiitb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDAO {

	Database db = new Database();
	Connection con = null;
	PreparedStatement ps = null;

	// ---- login with parameter user & password ---- //

	public boolean login(String user, String password) {

		try {
			con = db.getConnection();
			ps = con.prepareStatement("select user_name, user_password from user_master_table where user_name= ? and user_password= ? ");
			ps.setString(1, user);
			ps.setString(2, password);
			System.out.println("User=" + user);
			ResultSet rs = ps.executeQuery();
			System.out.println("resultset==" + rs);
			if (rs.next()) // found
			{
				System.out.println("User found----" + rs.getString("user_name"));
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error in connection() -->" + e.getMessage());
			return false;
		}
	}

	
}
