package com.Hardware_Software_Support;

import java.util.Scanner;

import com.Hardware_Software_Support.Bean.EmployeeBean;
import com.Hardware_Software_Support.UseCases.CheckStatusOfComplaints;
import com.Hardware_Software_Support.UseCases.EmployeeLogin;
import com.Hardware_Software_Support.UseCases.GetAllComplaintsOfEmployee;
import com.Hardware_Software_Support.UseCases.RaiseNewComplaint;
import com.Hardware_Software_Support.UseCases.RegisterNewEmployee;

public class Employee {
	
	static EmployeeBean en ;

	public void run() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("                        WELCOME TO EMPLOYEE PORTAL                     ");
		System.out.println("----------------------------------------------------------------------------------");
		

		System.out.println("Please Select Your Choice: ");
		System.out.println("***********************");
		System.out.println("1. Register Into System.");
		System.out.println("2. Login Into Account.");
		System.out.println("3. Go Back.");

		int choice = sc.nextInt();
		sc.nextLine();

		switch (choice) {
		case 1:
			register();
			break;
		case 2:
			login();
			break;
		case 3:
			APP.main(null);
		default:
			System.out.println("Invalid Choice....");
			run();
			break;
		}
	}

	public void register() {
		RegisterNewEmployee rem = new RegisterNewEmployee();
		rem.run();
		run();
	}

	public void login() {
		EmployeeLogin el = new EmployeeLogin();
		en = el.loginIntoAccount();
		if (en!=null) {
			menu();
		} else {
			run();
		}
	}

	public void menu() {

		boolean check = true;

		while (check) {

			Scanner sc = new Scanner(System.in);

			System.out.println("\nPlease Enter Your Choice:-");
			System.out.println("***********************");

			System.out.println("1. Raise a New Complaint: ");
			System.out.println("2. See The Status of a Complaint:");
			System.out.println("3. See All My Complaints.");
			System.out.println("4. LogOut");

			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				RaiseNewComplaint rs = new RaiseNewComplaint();
				rs.run(en);
				break;
			case 2:
				CheckStatusOfComplaints ch = new CheckStatusOfComplaints();
				ch.run(en);
				break;
			case 3:
				GetAllComplaintsOfEmployee ge = new GetAllComplaintsOfEmployee();
				ge.run(en);
				break;
			case 4:
				check = false;
				APP.main(null);
				break;
			default:
				break;
			}

		}
	}

}
