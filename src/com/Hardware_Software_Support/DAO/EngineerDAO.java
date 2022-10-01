package com.Hardware_Software_Support.DAO;

import java.util.List;

import com.Hardware_Software_Support.Bean.ComplaintsBean;
import com.Hardware_Software_Support.Bean.EngineerBean;
import com.Hardware_Software_Support.Exceptions.CredentialsException;
import com.Hardware_Software_Support.Exceptions.InvalidInputException;
import com.Hardware_Software_Support.Exceptions.RecordsNotFoundException;

public interface EngineerDAO {

	public List<ComplaintsBean> viewAssignedProblems(int EngId) throws RecordsNotFoundException;
	
	public EngineerBean login(String username, String password) throws CredentialsException;
	
	public boolean updateStatus(String compId, String Status, int engId) throws RecordsNotFoundException, InvalidInputException;

	public List<ComplaintsBean> viewAllAttendedProblems(int EngId) throws RecordsNotFoundException;
	
	public boolean changePassword(int EngId,String oldpass,String newpass) throws InvalidInputException;

}
