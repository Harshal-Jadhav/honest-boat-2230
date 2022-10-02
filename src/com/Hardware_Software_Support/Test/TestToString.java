package com.Hardware_Software_Support.Test;

import com.Hardware_Software_Support.Bean.ComplaintsBean;

public class TestToString {

	public static void main(String[] args) {
		
		ComplaintsBean c = new ComplaintsBean();
		c.setId("1001256485");
		c.setDescription("Outlook not working asking for  permissions");
		c.setType("Hardware");
		c.setEmpId(101);
		c.setEmpName("Harshal Jadhav");
		c.setEmpDepartment("Marketing");
		c.setEngId(1001);
		c.setEngDepartment("Null");
		c.setEngName("Harshal Jadhav");
		System.out.println(c);
	}
	
	
	
	
}
