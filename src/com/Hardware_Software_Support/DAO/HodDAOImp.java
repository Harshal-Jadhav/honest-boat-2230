package com.Hardware_Software_Support.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Hardware_Software_Support.Bean.EngineerBean;
import com.Hardware_Software_Support.Exceptions.RecordsNotFoundException;
import com.Hardware_Software_Support.Utility.ConnectionGenerator;

public class HodDAOImp implements HodDAO {

	@Override
	public boolean register(String fname, String lname, String dept, String username, String password) {

		boolean flag = false;

		try (Connection con = ConnectionGenerator.provideConnection()) {

			PreparedStatement ps1 = con.prepareStatement(
					"insert into engineer (firstname,lastname,department,username,password) VALUES(?,?,?,?,?)");
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return flag;
	}

	@Override
	public List<EngineerBean> viewAllEngineers() throws RecordsNotFoundException {
		List<EngineerBean> list = new ArrayList<>();

		try (Connection con = ConnectionGenerator.provideConnection()) {

			PreparedStatement ps1 = con.prepareStatement("select * from engineer");

			ResultSet rs1 = ps1.executeQuery();
			boolean flag = true;
			while (rs1.next()) {
				flag = false;

				EngineerBean en = new EngineerBean();

				en.setEmpId(rs1.getInt("EmpId"));
				en.setName(rs1.getString("FirstName") + " " + rs1.getString("LastName"));
				en.setDepartment(rs1.getString("Department"));
				en.setUsername(rs1.getString("username"));
				en.setPassword(rs1.getString("password"));

				list.add(en);
			}

			if (flag) {
				throw new RecordsNotFoundException("Engineers Table is Empty");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RecordsNotFoundException(e.getMessage());
		}

		return list;

	}

}
