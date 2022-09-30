package com.Hardware_Software_Support.UseCases;

import java.util.Scanner;

import com.Hardware_Software_Support.DAO.HodDAO;
import com.Hardware_Software_Support.DAO.HodDAOImp;
import com.Hardware_Software_Support.Exceptions.CredentialsException;

public class HodLogin {

	public boolean run(String table) {
		Scanner sc = new Scanner(System.in);

		boolean flag = false;

		System.out.print("Enter Your Username: ");
		String username = sc.nextLine();

		System.out.print("Enter Your Password: ");
		String password = sc.nextLine();
//		sc.close();
		HodDAO dao = new HodDAOImp();

		try {
			
			String UserName = dao.login(username, password, table);

			flag = true;
			System.out.println("\nWelcome " + UserName);
			System.out.println("=============================");
			
		} catch (CredentialsException e) {
			System.out.println(e.getMessage());
		}

		return flag;
	}

}
