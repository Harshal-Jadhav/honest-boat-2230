package com.Hardware_Software_Support.UseCases;

import java.util.Scanner;

import com.Hardware_Software_Support.DAO.EngineerDAO;
import com.Hardware_Software_Support.DAO.EngineerDAOImp;
import com.Hardware_Software_Support.Exceptions.CredentialsException;

public class EngineerLogin {

	public boolean loginIntoAccount() {
		Scanner sc = new Scanner(System.in);

		boolean flag = false;

		System.out.print("Enter Your Username: ");
		String username = sc.nextLine();

		System.out.print("Enter Your Password: ");
		String password = sc.nextLine();
//		sc.close();
		EngineerDAO dao = new EngineerDAOImp();

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
