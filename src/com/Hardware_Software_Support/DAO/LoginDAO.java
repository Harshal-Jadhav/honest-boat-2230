package com.Hardware_Software_Support.DAO;

import com.Hardware_Software_Support.Exceptions.CredentialsException;

public interface LoginDAO {

	public String login(String username, String password, String table) throws CredentialsException;
	
}
