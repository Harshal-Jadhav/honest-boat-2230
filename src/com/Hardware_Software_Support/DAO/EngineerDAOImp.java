package com.Hardware_Software_Support.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Hardware_Software_Support.Bean.ComplaintsBean;
import com.Hardware_Software_Support.Exceptions.RecordsNotFoundException;
import com.Hardware_Software_Support.Utility.ConnectionGenerator;

public class EngineerDAOImp implements EngineerDAO {

	@Override
	public List<ComplaintsBean> viewAssignedProblems(int EngId) throws RecordsNotFoundException {
		List<ComplaintsBean> list = new ArrayList<>();
		
		try(Connection con=ConnectionGenerator.provideConnection()){
			
			PreparedStatement ps1 = con.prepareStatement("select c.id,c.description,c.type,c.EmpId,em.firstname,em.lastname,em.department,c.EngId,en.EngFirstname,en.EngLastname,en.EngDepartment,c.status from complaints c , employee em, engineer en where c.EmpId=em.EmpId AND c.EngId=en.EngId AND status=? And c.EngId=?");
			
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

}
