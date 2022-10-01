package com.Hardware_Software_Support.DAO;

import java.util.List;

import com.Hardware_Software_Support.Bean.ComplaintsBean;
import com.Hardware_Software_Support.Bean.EmployeeBean;
import com.Hardware_Software_Support.Exceptions.CredentialsException;
import com.Hardware_Software_Support.Exceptions.InvalidInputException;
import com.Hardware_Software_Support.Exceptions.RecordsNotFoundException;

public interface EmployeeDAO {

	public boolean register(String fname, String lname, String dept, String username, String password) throws RecordsNotFoundException;
	
	public EmployeeBean login(String username, String password) throws CredentialsException;
	
	public boolean raiseComplaint(int EmpId, String description, String type) throws RecordsNotFoundException;

	public ComplaintsBean checkComplaintStatus(String CompId, int empid) throws InvalidInputException, RecordsNotFoundException;
	
	public List<ComplaintsBean> getAllComplaintsraised(int EmpId) throws RecordsNotFoundException;
}
