package com.Hardware_Software_Support.DAO;

import java.util.List;

import com.Hardware_Software_Support.Bean.EngineerBean;
import com.Hardware_Software_Support.Exceptions.RecordsNotFoundException;

public interface HodDAO {

	public boolean register(String fname, String lname, String dept, String username, String password);
	
	public List<EngineerBean> viewAllEngineers() throws RecordsNotFoundException;
}
