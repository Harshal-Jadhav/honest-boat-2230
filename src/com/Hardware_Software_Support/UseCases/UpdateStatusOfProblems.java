package com.Hardware_Software_Support.UseCases;

import java.util.Scanner;

import com.Hardware_Software_Support.Bean.EngineerBean;
import com.Hardware_Software_Support.DAO.EngineerDAO;
import com.Hardware_Software_Support.DAO.EngineerDAOImp;
import com.Hardware_Software_Support.Exceptions.InvalidInputException;
import com.Hardware_Software_Support.Exceptions.RecordsNotFoundException;

public class UpdateStatusOfProblems {

	public void run(EngineerBean engObj) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Complaint Id: ");
		String compId = sc.nextLine();
		System.out.println("Please Enter the status to Update (max 20 words): ");
		String status = sc.nextLine();
		System.out.println("Please Confirm your Password: ");
		String pass = sc.nextLine();
		
		if(!pass.equals(engObj.getPassword())) {
			System.out.println("Wrong Password...Please try Again.");
			return;
		}
		
		EngineerDAO eg = new EngineerDAOImp();
		try {
			if(eg.updateStatus(compId, status, engObj.getEmpId())) {
				System.out.println("Status Updated Sucessfully....");
			}else {
				System.out.println("jshdfklgjdf");
			}
		} catch (RecordsNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (InvalidInputException e) {
			System.out.println(e.getMessage());
		}
		
		
	}
	
}
