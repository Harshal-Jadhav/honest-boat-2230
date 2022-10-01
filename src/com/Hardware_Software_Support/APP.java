package com.Hardware_Software_Support;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * This is an Application Built for the Online Hardware and Software Support The
 * tech Stacks used are. Core JAVA, Collection Interface, MySQL And JDBC.
 * 
 * @author Harshal-Jadhav
 * @version 1.0
 *
 */
public class APP {

	public static void main(String[] args) {
		System.out.println("*******************************************************\n");
		System.out.println("*-----------------------WELCOME TO HELPING HANDS------------------*\n");
		System.out.println("*******************************************************\n");

		run();

	}

	public static void run() {
		System.out.println("Please Select Your User Type: ");
		System.out.println("1. Head Of Department.");
		System.out.println("2. Support Engineer.");
		System.out.println("3. Employee");

		Scanner sc = new Scanner(System.in);

		int choice = sc.nextInt();

		switch (choice) {
		case 1:
			Hod h = new Hod();
			h.run();
			break;
		case 2:
			Engineer en = new Engineer();
			en.run();
			break;
		case 3:
			Employee em = new Employee();
			em.run();
			break;

		default:
			System.out.println("Invalid Choice...!");
			break;
		}
	}
}
