package com.Hardware_Software_Support.UseCases;

import java.util.Scanner;

import com.Hardware_Software_Support.Employee;
import com.Hardware_Software_Support.DAO.EmployeeDAO;
import com.Hardware_Software_Support.DAO.EmployeeDAOImp;
import com.Hardware_Software_Support.Exceptions.RecordsNotFoundException;

public class RegisterNewEmployee {

	public boolean run() {
		
		boolean flag = false;
		
		Scanner sc = new Scanner(System.in);
		System.out.println("\n******************************************");
		System.out.println("===========ADD NEW ENGINEER=============");
		System.out.println("******************************************\n");
		
		System.out.print("Enter The Engineer's First Name: ");
		String fname = sc.nextLine();
		
		System.out.print("Enter The Engineer's Last Name: ");
		String lname = sc.nextLine();
		
		System.out.print("Enter The Department of the Engineer (Hardware/Software): ");
		String dept = sc.nextLine();
		
		System.out.print("Create The Username of the Enginer (E-mail): ");
		String username = sc.nextLine();
		
		System.out.print("Create Password: ");
		String password = sc.nextLine();
		
		EmployeeDAO e = new EmployeeDAOImp();
		
		try {
			if(e.register(fname, lname, dept, username, password)) {
				flag = true;
				System.out.println("\nEngineer Registration Sucess\n=========================\n");
			}else {
				System.out.println("Engineer Registration Failed.");
			}
		} catch (RecordsNotFoundException a) {
			System.out.println(a.getMessage());
		}
		
		return flag;
		
	}

	
}
