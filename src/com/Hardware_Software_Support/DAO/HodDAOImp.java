package com.Hardware_Software_Support.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Hardware_Software_Support.Bean.ComplaintsBean;
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

				en.setEmpId(rs1.getInt("EngId"));
				en.setName(rs1.getString("FirstName") + " " + rs1.getString("LastName"));
				en.setDepartment(rs1.getString("Department"));
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
		
		try(Connection con = ConnectionGenerator.provideConnection()){
			
			PreparedStatement ps1 = con.prepareStatement("select c.id,c.description,c.type,c.EmpId,em.firstname,em.lastname,c.EngId,c.status from complaints c,employee em where c.EmpId = em.EmpId;");
			
			ResultSet rs1 = ps1.executeQuery();
			
			boolean flag = true;
			
			while(rs1.next()) {
				flag = false;
				
				ComplaintsBean c = new ComplaintsBean();
				c.setId(rs1.getString("Id"));
				c.setDescription(rs1.getString("description"));
				c.setType(rs1.getString("type"));
				c.setEmpId(rs1.getInt("EmpId"));
				c.setEmpName(rs1.getString("firstname") + " " + rs1.getString("lastname"));
				c.setEngId(rs1.getInt("EngId"));
				c.setEngName("Not Available");
				c.setStatus(rs1.getString("status"));
				
				list.add(c);
				
			}
			
			if(flag) {
				throw new RecordsNotFoundException("No Records Found for the Unassigned Complaints...");
			}
			
		} catch (SQLException e) {
			throw new RecordsNotFoundException(e.getMessage());
		}
		return list;
	}
	
	
}
