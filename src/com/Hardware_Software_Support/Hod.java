package com.Hardware_Software_Support;

import java.util.Scanner;

import com.Hardware_Software_Support.UseCases.Login;
import com.Hardware_Software_Support.UseCases.Register;

/**
 * This class Handles all the Menu Operations that HOD (Admin) is allowed to do. For
 * Example- View the Engineers List, Add or Remove Engineer etc.
 * 
 * @author Harshal-Jadhav
 *
 */
public class Hod {

	public void run() {

		Login lg = new Login();
		Hod h = new Hod();

		if (lg.loginIntoAccount("hod")) {
			h.menu();
		} else {

			h.run();
		}
	}

	public void menu() {

		Scanner sc = new Scanner(System.in);

		boolean check = true;
		
		while(check) {
			System.out.println("\nPlease Enter Your Choice: ");
			System.out.println("=========================");
			System.out.println("1. Add New Engineer.");
			System.out.println("2. View all Registered Engineers.");
			System.out.println("3. Remove an Engineer from the list.");
			System.out.println("4. See all the Complaints Raised.");
			System.out.println("5. Assign an Engineer to a Problem.");
			System.out.println("6. LogOut\n");

			int choice = sc.nextInt();
			sc.nextLine();

			switch (choice) {
			case 1:
				Register r = new Register();
				r.registerNewEngineer();
				break;
			case 2:
				System.out.println("view eng");
				break;
			case 3:
				System.out.println("Remove Eng");
				break;
			case 4:
				System.out.println("view Comp");
				break;
			case 5:
				System.out.println("assign eng");
				break;
			case 6:
				System.out.println("logout");
				check = false;
				break;

			default:
				System.out.println("Invalid choice");
				break;
			}
		}

	}
}
