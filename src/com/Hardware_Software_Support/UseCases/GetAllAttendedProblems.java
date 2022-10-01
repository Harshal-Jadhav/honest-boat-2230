package com.Hardware_Software_Support.UseCases;

import java.util.List;
import java.util.Scanner;

import com.Hardware_Software_Support.Bean.ComplaintsBean;
import com.Hardware_Software_Support.Bean.EngineerBean;
import com.Hardware_Software_Support.DAO.EngineerDAO;
import com.Hardware_Software_Support.DAO.EngineerDAOImp;
import com.Hardware_Software_Support.Exceptions.RecordsNotFoundException;

public class GetAllAttendedProblems {

	public void run(EngineerBean engObj) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please Confirm Your password: ");
		String pass = sc.nextLine();
		
		if(!pass.equals(engObj.getPassword())) {
			System.out.println("Wrong Password...Please try Again.");
			return;
		}
		EngineerDAO eg = new EngineerDAOImp();
		try {
			List<ComplaintsBean> list = eg.viewAllAttendedProblems(engObj.getEmpId());

			list.stream().forEach(s -> System.out.println(s));
		} catch (RecordsNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

}
