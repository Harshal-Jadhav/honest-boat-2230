package com.Hardware_Software_Support.UseCases;

import java.util.Scanner;

import com.Hardware_Software_Support.DAO.HodDAO;
import com.Hardware_Software_Support.DAO.HodDAOImp;
import com.Hardware_Software_Support.Exceptions.RecordsNotFoundException;

public class RemoveEngineerFromList {

	public void run() {
		Scanner sc = new Scanner(System.in);

		System.out.print("Please Provide an ID of Employee you want to remove: ");
		int id = sc.nextInt();
		sc.nextLine();

		HodDAO h = new HodDAOImp();
		System.out.println("Are you sure to remove Engineer with Id " + id + " from the list.(Y/N)");
		String choice = sc.nextLine();
		if (choice.equalsIgnoreCase("n")) {
			return;
		} else {
			try {
				h.removeFromList(id);
				System.out.println("Engineer Removed From List...");
			} catch (RecordsNotFoundException e) {
				System.out.println(e.getMessage());
			}
		}
	}

}
