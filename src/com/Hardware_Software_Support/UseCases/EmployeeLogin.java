package com.Hardware_Software_Support.UseCases;

import java.util.Scanner;

import com.Hardware_Software_Support.Bean.EmployeeBean;
import com.Hardware_Software_Support.DAO.EmployeeDAO;
import com.Hardware_Software_Support.DAO.EmployeeDAOImp;
import com.Hardware_Software_Support.Exceptions.CredentialsException;

public class EmployeeLogin {

	public EmployeeBean loginIntoAccount() {
		Scanner sc = new Scanner(System.in);

		boolean flag = false;
		EmployeeBean en=null;
		
		System.out.println("\nPlease Provide Your Login Credentials:-");
		System.out.println("**********************************");

		System.out.print("Enter Your Username: ");
		String username = sc.nextLine();

		System.out.print("Enter Your Password: ");
		String password = sc.nextLine();

		EmployeeDAO dao = new EmployeeDAOImp();

		try {
			
			en = dao.login(username, password);

			flag = true;
			System.out.println("\nWelcome " + en.getName());
			System.out.println("-------------------------------------");
			
		} catch (CredentialsException e) {
			System.out.println(e.getMessage());
		}

		if(flag) {
			return en;
		}else {
			return null;
		}
	}

}
