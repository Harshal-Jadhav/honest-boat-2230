package com.Hardware_Software_Support.UseCases;

import java.util.List;
import java.util.Scanner;

import com.Hardware_Software_Support.Bean.ComplaintsBean;
import com.Hardware_Software_Support.DAO.EngineerDAO;
import com.Hardware_Software_Support.DAO.EngineerDAOImp;
import com.Hardware_Software_Support.Exceptions.RecordsNotFoundException;

public class GetAllAttendedProblems {

	public void run() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please Confirm Your Engineer Id: ");
		int id = sc.nextInt();
		EngineerDAO eg = new EngineerDAOImp();
		try {
			List<ComplaintsBean> list = eg.viewAllAttendedProblems(id);

			list.stream().forEach(s -> System.out.println(s));
		} catch (RecordsNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

}
