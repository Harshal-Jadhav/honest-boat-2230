package com.Hardware_Software_Support.UseCases;

import java.util.Scanner;

import com.Hardware_Software_Support.Hod;
import com.Hardware_Software_Support.DAO.HodDAO;
import com.Hardware_Software_Support.DAO.HodDAOImp;

public class Register {

	public void registerNewEngineer() {
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
		
		HodDAO h = new HodDAOImp();
		
		if(h.register(fname, lname, dept, username, password)) {
			System.out.println("\nEngineer Registration Sucess\n=========================\n");
		}else {
			System.out.println("Engineer Registration Failed.");
		}
		
		addAnother();
		
	}

	public void addAnother() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please Enter Your Choice");
		System.out.println("1. Add another Engineer.");
		System.out.println("2. Go Back.");
		
		int choice = sc.nextInt();
		sc.nextLine();
		
		switch (choice) {
		case 1:
			registerNewEngineer();
			break;
		case 2:
			Hod h = new Hod();
			h.menu();
		default:
			addAnother();
			break;
		}
		
	}
	
}
