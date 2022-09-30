package com.Hardware_Software_Support.DAO;

import java.util.List;

import com.Hardware_Software_Support.Bean.ComplaintsBean;
import com.Hardware_Software_Support.Bean.EngineerBean;
import com.Hardware_Software_Support.Exceptions.CredentialsException;
import com.Hardware_Software_Support.Exceptions.InvalidInputException;
import com.Hardware_Software_Support.Exceptions.RecordsNotFoundException;

public interface HodDAO {

	public boolean register(String fname, String lname, String dept, String username, String password) throws RecordsNotFoundException;
	
	public List<EngineerBean> viewAllEngineers() throws RecordsNotFoundException;
	
	public boolean removeFromList(int id) throws RecordsNotFoundException;
	
	public List<ComplaintsBean> viewUnassignedComplaints() throws RecordsNotFoundException;
	
	public List<ComplaintsBean> viewAssignedComplaints() throws RecordsNotFoundException;
	
	public List<ComplaintsBean> viewAllComplaints() throws RecordsNotFoundException;
	
	public boolean assignEngineer(String compId, int EngId) throws InvalidInputException;
	
	public String login(String username, String password, String table) throws CredentialsException;
}
