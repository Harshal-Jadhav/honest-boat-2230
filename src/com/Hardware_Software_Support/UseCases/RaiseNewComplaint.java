package com.Hardware_Software_Support.UseCases;

import java.util.Scanner;

import com.Hardware_Software_Support.Bean.EmployeeBean;
import com.Hardware_Software_Support.DAO.EmployeeDAO;
import com.Hardware_Software_Support.DAO.EmployeeDAOImp;
import com.Hardware_Software_Support.Exceptions.RecordsNotFoundException;

public class RaiseNewComplaint {

	public void run(EmployeeBean en) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Your Complaint description (max 250 words)");
		String description = sc.nextLine();
		System.out.println("Enter Complaint Type (Hardware/Software): ");
		String type = sc.nextLine();

		EmployeeDAO em = new EmployeeDAOImp();
		
		try {
			if(em.raiseComplaint(en.getEmpId(), description, type)) {
				System.out.println("Complaint Raised Sucessfull...!");
			}
		} catch (RecordsNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
}
