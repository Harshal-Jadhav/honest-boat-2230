package com.Hardware_Software_Support.UseCases;

import java.util.List;
import java.util.Scanner;

import com.Hardware_Software_Support.Bean.ComplaintsBean;
import com.Hardware_Software_Support.DAO.HodDAO;
import com.Hardware_Software_Support.DAO.HodDAOImp;
import com.Hardware_Software_Support.Exceptions.RecordsNotFoundException;

public class GetAllComplaintsFromList {

	public void run() {

		HodDAO h = new HodDAOImp();

		Scanner sc = new Scanner(System.in);

		System.out.println("Please Select Your Choice: ");
		System.out.println("1. View only unassigned Complaints.");
		System.out.println("2. View only assignned Complaints. ");
		System.out.println("3. View all Complaints.");

		int choice = sc.nextInt();
		sc.nextLine();

		switch (choice) {
		case 1:
			try {
				List<ComplaintsBean> list = h.viewUnassignedComplaints();

				list.stream().forEach(s -> System.out.print(s));
			} catch (RecordsNotFoundException e) {
				System.out.println(e.getMessage());
			}
			break;
		case 2:
			try {
				List<ComplaintsBean> list = h.viewAssignedComplaints();

				list.stream().forEach(s -> System.out.println(s));
			} catch (RecordsNotFoundException e) {
				System.out.println(e.getMessage());
			}
			break;
		case 3:
			try {
				List<ComplaintsBean> list = h.viewAllComplaints();

				list.stream().forEach(s -> System.out.print(s));
			} catch (RecordsNotFoundException e) {
				System.out.println(e.getMessage());
			}
			break;

		default:
			System.out.println("Invalid Choice....!");
			run();
			break;
		}

	}

	
}
