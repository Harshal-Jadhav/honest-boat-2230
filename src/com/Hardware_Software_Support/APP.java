package com.Hardware_Software_Support;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This is an Application Built for the Online Hardware and Software Support 
 * The tech Stacks used are. Core JAVA, Collection Interface, MySQL And JDBC.
 *  
 * @author Harshal-Jadhav
 *@version 1.0
 *
 */
public class APP {

	public static void main(String[] args) {
		LocalDateTime d = LocalDateTime.now();
		String ComId = d.format(DateTimeFormatter.ofPattern("MMddHHmmss"));
		
		System.out.println(ComId);
	}
	
}
