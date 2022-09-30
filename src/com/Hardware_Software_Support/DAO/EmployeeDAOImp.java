package com.Hardware_Software_Support.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.Hardware_Software_Support.Exceptions.CredentialsException;
import com.Hardware_Software_Support.Exceptions.RecordsNotFoundException;
import com.Hardware_Software_Support.Utility.ConnectionGenerator;

public class EmployeeDAOImp implements EmployeeDAO {

	@Override
	public boolean register(String fname, String lname, String dept, String username, String password)
			throws RecordsNotFoundException {
		boolean flag = false;

		try (Connection con = ConnectionGenerator.provideConnection()) {

			PreparedStatement ps1 = con.prepareStatement(
					"insert into employee (firstname,lastname,department,username,password) VALUES(?,?,?,?,?)");
			ps1.setString(1, fname);
			ps1.setString(2, lname);
			ps1.setString(3, dept);
			ps1.setString(4, username);
			ps1.setString(5, password);

			int x = ps1.executeUpdate();

			if (x > 0) {
				flag = true;
			}

		} catch (SQLException e) {
			throw new RecordsNotFoundException(e.getMessage());
		}

		return flag;
	}

	@Override
	public String login(String username, String password) throws CredentialsException {
		String name = null;

		try (Connection con = ConnectionGenerator.provideConnection()) {

			PreparedStatement ps1 = con.prepareStatement("select * from employee where username = ?");

			ps1.setString(1, username);

			ResultSet rs1 = ps1.executeQuery();

			if (rs1.next()) {
				PreparedStatement ps2 = con
						.prepareStatement("select * from employee where username = ? AND password = ?");

				ps2.setString(1, username);
				ps2.setString(2, password);

				ResultSet rs2 = ps2.executeQuery();

				if (rs2.next()) {
					name = (rs2.getString("FirstName") + " " + rs2.getString("LastName"));
				} else {
					throw new CredentialsException(
							"\nOOPS Wrong Password...! Try Again\n======================================\n");
				}

			} else {
				throw new CredentialsException(
						"\nUser Not Found..Please Check username or Register Yourself..!\n========================================================\n");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new CredentialsException(e.getMessage());
		}

		return name;
	}

	@Override
	public boolean raiseComplaint(int EmpId, String description, String type) throws RecordsNotFoundException {
		boolean flag = false;

		try (Connection con = ConnectionGenerator.provideConnection()) {

			LocalDateTime d = LocalDateTime.now();
			String ComId = d.format(DateTimeFormatter.ofPattern("MMddHHmmss"));
			
			PreparedStatement ps1 = con
					.prepareStatement("insert into complaints (id,description,type,EmpId) values (?,?,?,?)");
			ps1.setString(1, ComId);
			ps1.setString(2, description);
			ps1.setString(3, type);
			ps1.setInt(4, EmpId);

			int x = ps1.executeUpdate();

			if (x > 0) {
				System.out.println("Your Complaint Id is "+ ComId);
				flag = true;
			}

		} catch (SQLException e) {
			throw new RecordsNotFoundException(e.getMessage());
		}
		return flag;
	}

}
