package com.Hardware_Software_Support;

import java.util.Scanner;

import com.Hardware_Software_Support.UseCases.EmployeeLogin;
import com.Hardware_Software_Support.UseCases.RaiseNewComplaint;
import com.Hardware_Software_Support.UseCases.RegisterNewEmployee;

public class Employee {

	public void run() {
		Scanner sc = new Scanner(System.in);

		System.out.println("Please Select Your Choice: ");
		System.out.println("==========================\n");
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

		default:
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
		if (el.loginIntoAccount()) {
			menu();
		} else {
			run();
		}
	}

	public void menu() {

		Scanner sc = new Scanner(System.in);

		System.out.println("Please Enter Your Choice:-");
		System.out.println("=====================/n");

		System.out.println("1. Raise a New Complaint: ");
		System.out.println("2. See The Status of a Complaint:");
		System.out.println("3. See All My Complaints.");

		int choice = sc.nextInt();
		switch (choice) {
		case 1:
			RaiseNewComplaint rs = new RaiseNewComplaint();
			rs.run();
			break;

		default:
			break;
		}

	}

}
