package com.Hardware_Software_Support;

import java.util.Scanner;

import com.Hardware_Software_Support.UseCases.ChangePassEngineer;
import com.Hardware_Software_Support.UseCases.EngineerLogin;
import com.Hardware_Software_Support.UseCases.GetAllAttendedProblems;
import com.Hardware_Software_Support.UseCases.GetAllEngineerAssignedComplaints;
import com.Hardware_Software_Support.UseCases.UpdateStatusOfProblems;

/**
 * This class Handles all the Menu Operations that an Engineer is allowed to do.
 * For Example- View the Engineers List, Add or Remove Engineer etc.
 * 
 * @author Harshal-Jadhav
 *
 */
public class Engineer {

	public void run() {

		Engineer E = new Engineer();
		EngineerLogin eg = new EngineerLogin();
		if (eg.loginIntoAccount("engineer")) {
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
			case 2:
				UpdateStatusOfProblems up = new UpdateStatusOfProblems();
				up.run();
				break;
			case 3:
				GetAllAttendedProblems g = new GetAllAttendedProblems();
				g.run();
				break;
			case 4:
				ChangePassEngineer ch = new ChangePassEngineer();
				ch.run();
				break;

			default:
				break;
			}
		}
	}
}
