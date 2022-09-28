package com.Hardware_Software_Support.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Hardware_Software_Support.Exceptions.CredentialsException;
import com.Hardware_Software_Support.Utility.ConnectionGenerator;

public class LoginDAOImp implements LoginDAO {

	@Override
	public String login(String username, String password, String table) throws CredentialsException {

		String name = null;
		
		try (Connection con = ConnectionGenerator.provideConnection()) {
			
			PreparedStatement ps1 = con.prepareStatement("select * from "+table+" where username = ?");

			ps1.setString(1, username);
			
			ResultSet rs1 = ps1.executeQuery();

			if (rs1.next()) {
				PreparedStatement ps2 = con.prepareStatement("select * from "+table+ " where username = ? AND password = ?");

				ps2.setString(1, username);
				ps2.setString(2, password);

				ResultSet rs2 = ps2.executeQuery();

				if (rs2.next()) {
					name = (rs2.getString("FirstName") + " " + rs2.getString("LastName"));
				} else {
					throw new CredentialsException("\nOOPS Wrong Password...! Try Again\n======================================\n");
				}

			}else {
				throw new CredentialsException("\nUser Not Found..Please Check username or Register Yourself..!\n========================================================\n");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new CredentialsException(e.getMessage());
		}

		return name;
	}

}
