package com.Hardware_Software_Support.UseCases;

import java.util.List;
import java.util.Scanner;

import com.Hardware_Software_Support.Bean.ComplaintsBean;
import com.Hardware_Software_Support.Bean.EngineerBean;
import com.Hardware_Software_Support.DAO.HodDAO;
import com.Hardware_Software_Support.DAO.HodDAOImp;
import com.Hardware_Software_Support.Exceptions.RecordsNotFoundException;

public class Show {

	public void printAllEngineers() {
		
		HodDAO h = new HodDAOImp();
		
		try {
			List<EngineerBean> list =  h.viewAllEngineers();
			
			list.stream().forEach(e->System.out.println(e));
			
		} catch (RecordsNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		
		
	}
	
	public void printAllComplaints() {
		
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
				
				list.stream().forEach(s->System.out.println(s));
			} catch (RecordsNotFoundException e) {
				System.out.println(e.getMessage());
			}
			break;

		default:
			break;
		}
		
		
	}
}
