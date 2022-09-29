package com.Hardware_Software_Support.UseCases;

import java.util.List;

import com.Hardware_Software_Support.Bean.EngineerBean;
import com.Hardware_Software_Support.DAO.HodDAO;
import com.Hardware_Software_Support.DAO.HodDAOImp;
import com.Hardware_Software_Support.Exceptions.RecordsNotFoundException;

public class GetAllEngineersFromList {
	
	public void run() {

		HodDAO h = new HodDAOImp();

		try {
			List<EngineerBean> list = h.viewAllEngineers();

			list.stream().forEach(e -> System.out.println(e));

		} catch (RecordsNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

	}

	
}
