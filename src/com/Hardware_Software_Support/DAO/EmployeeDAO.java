package com.Hardware_Software_Support.DAO;

import com.Hardware_Software_Support.Exceptions.CredentialsException;
import com.Hardware_Software_Support.Exceptions.RecordsNotFoundException;

public interface EmployeeDAO {

	public boolean register(String fname, String lname, String dept, String username, String password) throws RecordsNotFoundException;
	
	public String login(String username, String password) throws CredentialsException;
	
}
