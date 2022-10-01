package com.Hardware_Software_Support.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.Hardware_Software_Support.Bean.ComplaintsBean;
import com.Hardware_Software_Support.Bean.EmployeeBean;
import com.Hardware_Software_Support.Exceptions.CredentialsException;
import com.Hardware_Software_Support.Exceptions.InvalidInputException;
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
	public EmployeeBean login(String username, String password) throws CredentialsException {
		EmployeeBean en = new EmployeeBean();

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
					en.setEmpId(rs2.getInt("EmpId"));
					en.setName(rs2.getString("FirstName") + " " + rs2.getString("LastName"));
					en.setDepartment(rs2.getString("department"));
					en.setUsername(rs2.getString("username"));
					en.setPassword(rs2.getString("password"));
					
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

		return en;
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
				System.out.println("Your Complaint Id is " + ComId);
				flag = true;
			}

		} catch (SQLException e) {
			throw new RecordsNotFoundException(e.getMessage());
		}
		return flag;
	}

	@Override
	public ComplaintsBean checkComplaintStatus(String CompId, int empId) throws InvalidInputException, RecordsNotFoundException {
		ComplaintsBean c = null;

		try (Connection con = ConnectionGenerator.provideConnection()) {

			PreparedStatement ps1 = con.prepareStatement(
					"select c.id,c.description,c.type,c.EmpId,em.firstname,em.lastname,em.department,c.EngId,en.EngFirstname,en.EngLastname,en.EngDepartment,c.status from complaints c , employee em, engineer en where c.EmpId=em.EmpId AND c.EngId=en.EngId AND c.id=? AND c.EmpId=?");

			PreparedStatement ps2 = con.prepareStatement("select * from complaints where id = ?");

			ps1.setString(1, CompId);
			ps1.setInt(2, empId);
			ps2.setString(1, CompId);

			if (ps2.executeQuery().next()) {
				ResultSet rs1 = ps1.executeQuery();

				if (rs1.next()) {
					c = new ComplaintsBean();
					c.setId(rs1.getString("Id"));
					c.setDescription(rs1.getString("description"));
					c.setType(rs1.getString("type"));
					c.setEmpId(rs1.getInt("EmpId"));
					c.setEmpName(rs1.getString("firstname") + " " + rs1.getString("lastname"));
					c.setEmpDepartment(rs1.getString("department"));

					if (rs1.getInt("EngId") > 0) {
						c.setEngId(rs1.getInt("EngId"));
						c.setEngName(rs1.getString("EngFirstname") + " " + rs1.getString("EngLastname"));
						c.setEngDepartment(rs1.getString("EngDepartment"));
					} else {
						c.setEngId(rs1.getInt("EngId"));
						c.setEngName("Not Assigned");
						c.setEngDepartment("Not Availabel");
					}

					c.setStatus(rs1.getString("status"));

				} else {
					throw new InvalidInputException(
							"Complaint Yet to be assigned to an Engineer...We Request Your patience.");
				}

			} else {
				throw new RecordsNotFoundException("No records Found for complaint id " + CompId);
			}

		} catch (SQLException e) {
			throw new InvalidInputException(e.getMessage());
		}

		return c;

	}

	@Override
	public List<ComplaintsBean> getAllComplaintsraised(int EmpId) throws RecordsNotFoundException {
		List<ComplaintsBean> list = new ArrayList<>();

		try (Connection con = ConnectionGenerator.provideConnection()) {

			PreparedStatement ps1 = con.prepareStatement(
					"select c.id,c.description,c.type,c.EmpId,em.firstname,em.lastname,em.department,c.EngId,en.EngFirstname,en.EngLastname,en.EngDepartment,c.status from complaints c , employee em, engineer en where c.EmpId=em.EmpId AND c.EngId=en.EngId AND c.EmpId=?");

			ps1.setInt(1, EmpId);
			ResultSet rs1 = ps1.executeQuery();

			boolean flag = true;

			while (rs1.next()) {
				flag = false;

				ComplaintsBean c = new ComplaintsBean();
				c.setId(rs1.getString("Id"));
				c.setDescription(rs1.getString("description"));
				c.setType(rs1.getString("type"));
				c.setEmpId(rs1.getInt("EmpId"));
				c.setEmpName(rs1.getString("firstname") + " " + rs1.getString("lastname"));
				c.setEmpDepartment(rs1.getString("department"));
				c.setEngId(rs1.getInt("EngId"));
				c.setEngName(rs1.getString("EngFirstname") + " " + rs1.getString("EngLastname"));
				c.setEngDepartment(rs1.getString("EngDepartment"));
				c.setStatus(rs1.getString("status"));

				list.add(c);

			}

			if (flag) {
				throw new RecordsNotFoundException("No Records Found for the Unassigned Complaints...");
			}

		} catch (SQLException e) {
			throw new RecordsNotFoundException(e.getMessage());
		}
		return list;
	}

}
