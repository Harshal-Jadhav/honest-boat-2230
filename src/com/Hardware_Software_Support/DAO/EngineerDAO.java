package com.Hardware_Software_Support.DAO;

import java.util.List;

import com.Hardware_Software_Support.Bean.ComplaintsBean;
import com.Hardware_Software_Support.Exceptions.RecordsNotFoundException;

public interface EngineerDAO {

	public List<ComplaintsBean> viewAssignedProblems(int EngId) throws RecordsNotFoundException;
	
}
