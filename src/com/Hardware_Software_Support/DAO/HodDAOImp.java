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
import com.mysql.cj.protocol.Resultset;

public class HodDAOImp implements HodDAO {

	@Override
	public boolean register(String fname, String lname, String dept, String username, String password)
			throws RecordsNotFoundException {

		boolean flag = false;

		try (Connection con = ConnectionGenerator.provideConnection()) {

			PreparedStatement ps1 = con.prepareStatement(
					"insert into engineer (EngFirstname,EngLastname,EngDepartment,username,password) VALUES(?,?,?,?,?)");
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
	public List<EngineerBean> viewAllEngineers() throws RecordsNotFoundException {
		List<EngineerBean> list = new ArrayList<>();

		try (Connection con = ConnectionGenerator.provideConnection()) {

			PreparedStatement ps1 = con.prepareStatement("select * from engineer");

			ResultSet rs1 = ps1.executeQuery();
			boolean flag = true;
			while (rs1.next()) {
				flag = false;

				EngineerBean en = new EngineerBean();

				en.setEmpId(rs1.getInt("EngId"));
				en.setName(rs1.getString("EngFirstName") + " " + rs1.getString("EngLastName"));
				en.setDepartment(rs1.getString("EngDepartment"));
				en.setUsername(rs1.getString("username"));
				en.setPassword(rs1.getString("password"));

				list.add(en);
			}

			if (flag) {
				throw new RecordsNotFoundException("No Records Found...!");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RecordsNotFoundException(e.getMessage());
		}

		return list;

	}

	@Override
	public boolean removeFromList(int id) throws RecordsNotFoundException {

		boolean flag = false;

		try (Connection con = ConnectionGenerator.provideConnection()) {
			
			PreparedStatement p1 = con.prepareStatement("select * from complaints where EngId=?");
			ResultSet r1 = p1.executeQuery();
			
			if(r1.next()) {
				PreparedStatement ps = con.prepareStatement("delete from complaints where EngId=?");
				ps.setInt(1, id);
				
				ps.executeUpdate();
			}
			
			PreparedStatement ps1 = con.prepareStatement("delete from engineer where EngId=?");

			ps1.setInt(1, id);

			int x = ps1.executeUpdate();

			if (x > 0) {
				flag = true;
			} else {
				throw new RecordsNotFoundException("No Record Found matching Supplied Engineer ID. ");
			}

		} catch (SQLException e) {
			throw new RecordsNotFoundException(e.getMessage());
		}

		return flag;
	}

	@Override
	public List<ComplaintsBean> viewUnassignedComplaints() throws RecordsNotFoundException {
		List<ComplaintsBean> list = new ArrayList<>();

		try (Connection con = ConnectionGenerator.provideConnection()) {

			PreparedStatement ps1 = con.prepareStatement(
					"select c.id,c.description,c.type,c.EmpId,em.firstname,em.lastname,em.department,c.EngId,c.status from complaints c,employee em where c.EmpId = em.EmpId AND c.status=?");

			ps1.setString(1, "unassigned");

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
				c.setEngName("Not Available");
				c.setEngDepartment("Not Availabel");
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

	@Override
	public List<ComplaintsBean> viewAssignedComplaints() throws RecordsNotFoundException {
		List<ComplaintsBean> list = new ArrayList<>();

		try (Connection con = ConnectionGenerator.provideConnection()) {

			PreparedStatement ps1 = con.prepareStatement(
					"select c.id,c.description,c.type,c.EmpId,em.firstname,em.lastname,em.department,c.EngId,en.EngFirstname,en.EngLastname,en.EngDepartment,c.status from complaints c , employee em, engineer en where c.EmpId=em.EmpId AND c.EngId=en.EngId AND c.status = ?");

			ps1.setString(1, "assigned");
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

	@Override
	public List<ComplaintsBean> viewAllComplaints() throws RecordsNotFoundException {
		List<ComplaintsBean> list = new ArrayList<>();

		try (Connection con = ConnectionGenerator.provideConnection()) {

			PreparedStatement ps1 = con.prepareStatement(
					"select c.id,c.description,c.type,c.EmpId,em.firstname,em.lastname,em.department,c.EngId,en.EngFirstname,en.EngLastname,en.EngDepartment,c.status from complaints c , employee em, engineer en where c.EmpId=em.EmpId AND c.EngId=en.EngId");

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

	@Override
	public boolean assignEngineer(String compId, int EngId) throws InvalidInputException {

		boolean flag = false;

		try (Connection con = ConnectionGenerator.provideConnection()) {

			PreparedStatement ps1 = con.prepareStatement("select * from complaints where status=? AND id=?");
			ps1.setString(1, "unassigned");
			ps1.setString(2, compId);

			PreparedStatement ps2 = con.prepareStatement("select * from engineer where EngId=?");
			ps2.setInt(1, EngId);

			ResultSet x = ps1.executeQuery();
			ResultSet y = ps2.executeQuery();

			if (x.next()) {
				if (y.next()) {
					PreparedStatement ps3 = con.prepareStatement("Update Complaints set EngId = ? where id=?");
					PreparedStatement ps4 = con.prepareStatement("Update Complaints set status=? where id=?");

					ps3.setInt(1, EngId);
					ps3.setString(2, compId);

					ps4.setString(1, "assigned");
					ps4.setString(2, compId);

					ps3.executeUpdate();
					ps4.executeUpdate();
					flag = true;
				} else {
					throw new InvalidInputException("No Engineer Found With given Id " + EngId);
				}
			} else {
				throw new InvalidInputException("No Unassigned Complaint Found with complaint Id " + compId);
			}

		} catch (SQLException e) {
			throw new InvalidInputException(e.getMessage());
		}
		return flag;
	}

	@Override
	public String login(String username, String password) throws CredentialsException {

		String name = null;
		
		try (Connection con = ConnectionGenerator.provideConnection()) {
			
			PreparedStatement ps1 = con.prepareStatement("select * from hod where username = ?");

			ps1.setString(1, username);
			
			ResultSet rs1 = ps1.executeQuery();

			if (rs1.next()) {
				PreparedStatement ps2 = con.prepareStatement("select * from hod where username = ? AND password = ?");

				ps2.setString(1, username);
				ps2.setString(2, password);

				ResultSet rs2 = ps2.executeQuery();

//				if (rs2.next() && table=="engineer") {
//					name = rs2.getString("EngFirstName")+" "+rs2.getString("EngLastName");
//				}
				if (rs2.next()) {
						name = (rs2.getString("FirstName"));
				}else {
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
