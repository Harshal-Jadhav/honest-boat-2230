package com.Hardware_Software_Support.UseCases;

import java.util.List;
import java.util.Scanner;

import com.Hardware_Software_Support.Bean.ComplaintsBean;
import com.Hardware_Software_Support.DAO.EngineerDAO;
import com.Hardware_Software_Support.DAO.EngineerDAOImp;
import com.Hardware_Software_Support.Exceptions.RecordsNotFoundException;

public class GetAllEngineerAssignedComplaints {

	public void run() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Please Submit Your Engineer Id for Confirmation: ");
		int id = sc.nextInt();
		sc.nextLine();
		
		EngineerDAO e = new EngineerDAOImp();
		try {
			
			List<ComplaintsBean> list = e.viewAssignedProblems(id);

			list.stream().forEach(s -> System.out.println(s));
		} catch (RecordsNotFoundException e1) {
			System.out.println(e1.getMessage());
		}
		
	}
	
	
}
