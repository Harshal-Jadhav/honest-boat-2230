package com.Hardware_Software_Support.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Hardware_Software_Support.Bean.ComplaintsBean;
import com.Hardware_Software_Support.Bean.EngineerBean;
import com.Hardware_Software_Support.Exceptions.CredentialsException;
import com.Hardware_Software_Support.Exceptions.InvalidInputException;
import com.Hardware_Software_Support.Exceptions.RecordsNotFoundException;
import com.Hardware_Software_Support.Utility.ConnectionGenerator;

public class EngineerDAOImp implements EngineerDAO {

	@Override
	public List<ComplaintsBean> viewAssignedProblems(int EngId) throws RecordsNotFoundException {
		List<ComplaintsBean> list = new ArrayList<>();

		try (Connection con = ConnectionGenerator.provideConnection()) {

			PreparedStatement ps1 = con.prepareStatement(
					"select c.id,c.description,c.type,c.EmpId,em.firstname,em.lastname,em.department,c.EngId,en.EngFirstname,en.EngLastname,en.EngDepartment,c.status from complaints c , employee em, engineer en where c.EmpId=em.EmpId AND c.EngId=en.EngId AND status=? And c.EngId=?");

			ps1.setString(1, "assigned");
			ps1.setInt(2, EngId);

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
				throw new RecordsNotFoundException("No New assigned Complaints...");
			}

		} catch (SQLException e) {
			throw new RecordsNotFoundException(e.getMessage());
		}
		return list;
	}

	public EngineerBean login(String username, String password) throws CredentialsException {

		EngineerBean en = new EngineerBean();

		try (Connection con = ConnectionGenerator.provideConnection()) {

			PreparedStatement ps1 = con.prepareStatement("select * from engineer where username = ?");

			ps1.setString(1, username);

			ResultSet rs1 = ps1.executeQuery();

			if (rs1.next()) {
				PreparedStatement ps2 = con
						.prepareStatement("select * from engineer where username = ? AND password = ?");

				ps2.setString(1, username);
				ps2.setString(2, password);

				ResultSet rs2 = ps2.executeQuery();

				if (rs2.next()) {
					en.setEmpId(rs2.getInt("EngId"));
					en.setName(rs2.getString("EngFirstName") + " " + rs2.getString("EngLastName"));
					en.setDepartment(rs2.getNString("EngDepartment"));
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

	public boolean updateStatus(String compId, String Status, int engId)
			throws RecordsNotFoundException, InvalidInputException {

		boolean flag = false;

		try (Connection con = ConnectionGenerator.provideConnection()) {

			PreparedStatement ps1 = con.prepareStatement("select * from complaints where id = ?");
			PreparedStatement ps2 = con.prepareStatement("select * from engineer where  EngId=?");
			PreparedStatement ps3 = con.prepareStatement("select * from complaints where id=? AND EngId=?");

			ps1.setString(1, compId);
			ps2.setInt(1, engId);
			ps3.setString(1, compId);
			ps3.setInt(2, engId);

			ResultSet x = ps1.executeQuery();

			if (x.next()) {
				ResultSet y = ps2.executeQuery();
				if (y.next()) {
					ResultSet z = ps3.executeQuery();
					if (z.next()) {

						PreparedStatement ps4 = con
								.prepareStatement("update complaints set status = ? where id = ? AND EngId=?");
						ps4.setString(1, Status);
						ps4.setString(2, compId);
						ps4.setInt(3, engId);

						int a = ps4.executeUpdate();
						if (a > 0) {
							flag = true;
						}
					} else {
						throw new RecordsNotFoundException("No Complaints Found for this Engineer Id......");
					}
				} else {
					throw new InvalidInputException("Invalid Engineer ID...!");
				}
			} else {
				throw new InvalidInputException("Invalild Complaint ID...!");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RecordsNotFoundException(e.getMessage());
		}

		return flag;
	}

	@Override
	public List<ComplaintsBean> viewAllAttendedProblems(int EngId) throws RecordsNotFoundException {
		List<ComplaintsBean> list = new ArrayList<>();

		try (Connection con = ConnectionGenerator.provideConnection()) {

			PreparedStatement ps1 = con.prepareStatement(
					"select c.id,c.description,c.type,c.EmpId,em.firstname,em.lastname,em.department,c.EngId,en.EngFirstname,en.EngLastname,en.EngDepartment,c.status from complaints c , employee em, engineer en where c.EmpId=em.EmpId AND c.EngId=en.EngId AND NOT status=? And c.EngId=?");

			ps1.setString(1, "assigned");
			ps1.setInt(2, EngId);

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
				throw new RecordsNotFoundException("No New assigned Complaints...");
			}

		} catch (SQLException e) {
			throw new RecordsNotFoundException(e.getMessage());
		}
		return list;
	}

	@Override
	public boolean changePassword(int EngId, String oldpass, String newpass) throws InvalidInputException {
		boolean flag = false;

		try (Connection con = ConnectionGenerator.provideConnection()) {

			PreparedStatement ps1 = con.prepareStatement("select * from engineer where EngId = ?");
			PreparedStatement ps2 = con.prepareStatement("select * from engineer where EngId = ? AND password = ?");
			PreparedStatement ps3 = con.prepareStatement("update engineer set password = ? where EngId=?");

			ps1.setInt(1, EngId);
			ps2.setInt(1, EngId);
			ps2.setString(2, oldpass);
			ps3.setString(1, newpass);
			ps3.setInt(2, EngId);

			if (ps1.executeQuery().next()) {
				if (ps2.executeQuery().next()) {
					int x = ps3.executeUpdate();
					if (x > 0) {
						flag = true;
					}
				} else {
					throw new InvalidInputException("Incorrect Old PassWord..!");
				}
			} else {
				throw new InvalidInputException("Incorrect EngId...!");
			}

		} catch (SQLException e) {
			throw new InvalidInputException(e.getMessage());
		}

		return flag;
	}

}
