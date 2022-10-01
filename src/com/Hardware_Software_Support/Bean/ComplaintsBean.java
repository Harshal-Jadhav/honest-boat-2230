package com.Hardware_Software_Support.Bean;

public class ComplaintsBean {

	private String Id;
	private String description;
	private String type;
	private int empId;
	private String empName;
	private String empDepartment;
	private String status;
	private int engId;
	private String engName;
	private String EngDepartment;

	public String getEmpDepartment() {
		return empDepartment;
	}

	public void setEmpDepartment(String empDepartment) {
		this.empDepartment = empDepartment;
	}

	public String getEngDepartment() {
		return EngDepartment;
	}

	public void setEngDepartment(String engDepartment) {
		EngDepartment = engDepartment;
	}

	// Constructor
	public ComplaintsBean() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * Getter And Setter Methods for above Variables.
	 */

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public int getEngId() {
		return engId;
	}

	public void setEngId(int engId) {
		this.engId = engId;
	}

	public String getEngName() {
		return engName;
	}

	public void setEngName(String engName) {
		this.engName = engName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {

		System.out.printf("| %-15s | %-50s | %10s | %03d | %20s | %15s | %15s | %04d | %15s | %15s |%n", 
				Id,description, type, empId, empName, empDepartment, status, engId, engName, EngDepartment);
		return "";
	}

}
