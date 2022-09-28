package com.Hardware_Software_Support;


import com.Hardware_Software_Support.UseCases.Login;

/**
 * This  class Handles all the Operations that HOD (Admin) is allowed to do.
 * For Example- View the Engineers List, Add or Remove Engineer etc.
 * 
 * @author Harshal-Jadhav
 *
 */
public class HOD {

	public void run() {
		Login lg = new Login();
		if(lg.loginIntoAccount("hod")) {
			
		}
	}
	
	
}
