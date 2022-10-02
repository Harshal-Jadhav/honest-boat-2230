package com.Hardware_Software_Support.UseCases;

import java.util.Scanner;

import com.Hardware_Software_Support.Bean.EngineerBean;
import com.Hardware_Software_Support.DAO.EngineerDAO;
import com.Hardware_Software_Support.DAO.EngineerDAOImp;
import com.Hardware_Software_Support.Exceptions.CredentialsException;

public class EngineerLogin {

	public EngineerBean loginIntoAccount() {
		Scanner sc = new Scanner(System.in);

		boolean flag = false;
		EngineerBean en = null;
		
		System.out.println("\nPlease Provide Your Login Credentials:-");
		System.out.println("**********************************");
		System.out.print("Enter Your Username: ");
		String username = sc.nextLine();

		System.out.print("Enter Your Password: ");
		String password = sc.nextLine();
		EngineerDAO dao = new EngineerDAOImp();

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
