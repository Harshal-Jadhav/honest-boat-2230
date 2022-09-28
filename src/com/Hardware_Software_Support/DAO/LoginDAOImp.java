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

			PreparedStatement ps1 = con.prepareStatement("selecet * from ? where username = ?");

			ps1.setString(1, table);
			ps1.setString(2, username);

			ResultSet rs1 = ps1.executeQuery();

			if (rs1.next()) {
				PreparedStatement ps2 = con.prepareStatement("select * from ? where username = ? AND password = ?");

				ps2.setString(1, table);
				ps2.setString(2, username);
				ps2.setString(3, password);

				ResultSet rs2 = ps2.executeQuery();

				if (rs2.next()) {
					name = (rs2.getString("FirstName") + " " + rs2.getString("LastName"));
				} else {
					throw new CredentialsException("OOPS Wrong Password...! ");
				}

			}else {
				throw new CredentialsException("User Not Found..Please Check username or Register Yourself..!");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new CredentialsException(e.getMessage());
		}

		return name;
	}

}
