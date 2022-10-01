package com.Hardware_Software_Support.UseCases;

import java.util.Scanner;

import com.Hardware_Software_Support.Bean.EngineerBean;
import com.Hardware_Software_Support.DAO.EngineerDAO;
import com.Hardware_Software_Support.DAO.EngineerDAOImp;
import com.Hardware_Software_Support.Exceptions.InvalidInputException;

public class ChangePassEngineer {

	public void run(EngineerBean engObj) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Please Enter Your Old Password: ");
		String oldPass = sc.nextLine();
		System.out.println("Please Enter Your New Password: ");
		String newPass = sc.nextLine();

		EngineerDAO eg = new EngineerDAOImp();
		try {
			if (eg.changePassword(engObj.getEmpId(), oldPass, newPass)) {
				System.out.println("Password Updated Sucessfully.");
			}
		} catch (InvalidInputException e) {
			System.out.println(e.getMessage());
		}
	}

}
