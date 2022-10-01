package com.Hardware_Software_Support.UseCases;

import java.util.List;
import java.util.Scanner;

import com.Hardware_Software_Support.Bean.ComplaintsBean;
import com.Hardware_Software_Support.DAO.EmployeeDAO;
import com.Hardware_Software_Support.DAO.EmployeeDAOImp;
import com.Hardware_Software_Support.Exceptions.RecordsNotFoundException;

public class GetAllComplaintsOfEmployee {

	public void run() {
		Scanner sc = new Scanner(System.in);
		EmployeeDAO e = new EmployeeDAOImp();
		System.out.println("Please Confirm Your Employee Id: ");
		int empId = sc.nextInt();
		try {
			List<ComplaintsBean> list = e.getAllComplaintsraised(empId);

			list.stream().forEach(s -> System.out.println(s));
		} catch (RecordsNotFoundException ea) {
			System.out.println(ea.getMessage());
		}

	}

}
