package com.Hardware_Software_Support.UseCases;

import java.util.Scanner;

import com.Hardware_Software_Support.DAO.LoginDAO;
import com.Hardware_Software_Support.DAO.LoginDAOImp;
import com.Hardware_Software_Support.Exceptions.CredentialsException;

public class Login {

	public boolean loginIntoAccount(String table) {
		Scanner sc = new Scanner(System.in);

		boolean flag = false;

		System.out.print("Enter Your Username: ");
		String username = sc.nextLine();

		System.out.print("Enter Your Password: ");
		String password = sc.nextLine();
		sc.close();
		LoginDAO dao = new LoginDAOImp();

		try {
			
			String UserName = dao.login(username, password, table);

			flag = true;
			System.out.println("\nWelcome " + UserName);
			
		} catch (CredentialsException e) {
			System.out.println(e.getMessage());
		}

		return flag;
	}

}
