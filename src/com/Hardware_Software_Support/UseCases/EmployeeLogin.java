package com.Hardware_Software_Support.UseCases;

import java.util.Scanner;

import com.Hardware_Software_Support.DAO.EmployeeDAO;
import com.Hardware_Software_Support.DAO.EmployeeDAOImp;
import com.Hardware_Software_Support.Exceptions.CredentialsException;

public class EmployeeLogin {

	public boolean loginIntoAccount() {
		Scanner sc = new Scanner(System.in);

		boolean flag = false;

		System.out.print("Enter Your Username: ");
		String username = sc.nextLine();

		System.out.print("Enter Your Password: ");
		String password = sc.nextLine();
//		sc.close();
		EmployeeDAO dao = new EmployeeDAOImp();

		try {
			
			String UserName = dao.login(username, password);

			flag = true;
			System.out.println("\nWelcome " + UserName);
			System.out.println("=============================");
			
		} catch (CredentialsException e) {
			System.out.println(e.getMessage());
		}

		return flag;
	}

}
