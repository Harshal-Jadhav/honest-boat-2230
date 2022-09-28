package com.Hardware_Software_Support.Test;

import java.sql.Connection;

import com.Hardware_Software_Support.Utility.ConnectionGenerator;

public class TestConnection {
	
	//This method Checks the the connection.
	public static void main(String[] args) {

		Connection conn = ConnectionGenerator.provideConnection();

		System.out.println(conn);

	}
	
}
