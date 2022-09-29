package com.Hardware_Software_Support;

import java.util.Scanner;

import com.Hardware_Software_Support.UseCases.AssignEngineerToComplaint;
import com.Hardware_Software_Support.UseCases.GetAllComplaintsFromList;
import com.Hardware_Software_Support.UseCases.GetAllEngineersFromList;
import com.Hardware_Software_Support.UseCases.LoginUser;
import com.Hardware_Software_Support.UseCases.RegisterNewEngineer;
import com.Hardware_Software_Support.UseCases.RemoveEngineerFromList;

/**
 * This class Handles all the Menu Operations that HOD (Admin) is allowed to do. For
 * Example- View the Engineers List, Add or Remove Engineer etc.
 * 
 * @author Harshal-Jadhav
 *
 */
public class Hod {

	public void run() {

		LoginUser lg = new LoginUser();
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
				RegisterNewEngineer r = new RegisterNewEngineer();
				r.run();
				break;
			case 2:
				GetAllEngineersFromList getEngineers = new GetAllEngineersFromList();
				getEngineers.run();
				break;
			case 3:
				RemoveEngineerFromList rm = new RemoveEngineerFromList();
				rm.run();
				break;
			case 4:
				GetAllComplaintsFromList getComplaints = new GetAllComplaintsFromList();
				getComplaints.run();
				break;
			case 5:
				AssignEngineerToComplaint as = new AssignEngineerToComplaint();
				as.run();
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
