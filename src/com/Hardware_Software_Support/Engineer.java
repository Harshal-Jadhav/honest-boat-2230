package com.Hardware_Software_Support;

import java.util.Scanner;

import com.Hardware_Software_Support.UseCases.GetAllEngineerAssignedComplaints;
import com.Hardware_Software_Support.UseCases.GetAllEngineersFromList;
import com.Hardware_Software_Support.UseCases.LoginUser;

/**
 * This class Handles all the Menu Operations that an Engineer is allowed to do.
 * For Example- View the Engineers List, Add or Remove Engineer etc.
 * 
 * @author Harshal-Jadhav
 *
 */
public class Engineer {

	public void run() {

		LoginUser lg = new LoginUser();
		Engineer E = new Engineer();

		if (lg.loginIntoAccount("engineer")) {
			E.menu();
		} else {

			E.run();
		}
	}

	public void menu() {
		Scanner sc = new Scanner(System.in);

		boolean check = true;

		while (check) {
			System.out.println("\nPlease Enter Your Choice: ");
			System.out.println("=========================");
			System.out.println("1. View Problems Assigned to me.");
			System.out.println("2. Update the Status of the Problems.");
			System.out.println("3. View all Problems attended by me.");
			System.out.println("4. Change my Password.");
			System.out.println("5. LogOut\n");

			int choice = sc.nextInt();
			sc.nextLine();
			
			switch (choice) {
			case 1:
				GetAllEngineerAssignedComplaints getComplaints = new GetAllEngineerAssignedComplaints();
				getComplaints.run();
				break;

			default:
				break;
			}
		}
	}
}
