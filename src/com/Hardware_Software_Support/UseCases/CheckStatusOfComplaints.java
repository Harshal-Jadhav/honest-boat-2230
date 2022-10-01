package com.Hardware_Software_Support.UseCases;

import java.util.List;
import java.util.Scanner;

import com.Hardware_Software_Support.Bean.ComplaintsBean;
import com.Hardware_Software_Support.DAO.EmployeeDAO;
import com.Hardware_Software_Support.DAO.EmployeeDAOImp;
import com.Hardware_Software_Support.Exceptions.InvalidInputException;
import com.Hardware_Software_Support.Exceptions.RecordsNotFoundException;

public class CheckStatusOfComplaints {

	public void run() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Please Enter your Complaint Id: ");
		String compId = sc.nextLine();
		
		EmployeeDAO ed = new EmployeeDAOImp();

			try {
				ComplaintsBean c =  ed.checkComplaintStatus(compId);
				
				System.out.println(c);
			} catch (InvalidInputException e) {
				System.out.println(e.getMessage());
			} catch (RecordsNotFoundException e) {
				System.out.println(e.getMessage());
			}

	}
	
}
