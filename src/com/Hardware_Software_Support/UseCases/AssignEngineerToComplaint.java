package com.Hardware_Software_Support.UseCases;

import java.util.Scanner;

import com.Hardware_Software_Support.DAO.HodDAO;
import com.Hardware_Software_Support.DAO.HodDAOImp;
import com.Hardware_Software_Support.Exceptions.InvalidInputException;

public class AssignEngineerToComplaint {

	public void run() {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Please Enter the Complaint ID: ");
		String id = sc.nextLine();
		System.out.println("Enter Id of the Engineer to assign");
		int EngId = sc.nextInt();
		sc.nextLine();
		
		HodDAO h = new HodDAOImp();
		try {
			if(h.assignEngineer(id, EngId)) {
				System.out.println("Engineer Assigned to the complaint with id: "+id);
			}
		} catch (InvalidInputException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
}
