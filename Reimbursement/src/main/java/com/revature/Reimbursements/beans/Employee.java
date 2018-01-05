package com.revature.Reimbursements.beans;

public class Employee {
	
	private int id;
	private String name;
	private String email;
	private String password;
	private int department;
	private int managerId;
	
	public Employee(int id, String name, String username, String password,
		int department, int managerId) {
	super();
	this.id = id;
	this.name = name;
	this.email = email;
	this.password = password;
	this.department = department;
	this.managerId = managerId;
}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String username) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getDepartment() {
		return department;
	}
	public void setDepartment(int department) {
		this.department = department;
	}
	public int getManagerId() {
		return managerId;
	}
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
}
	
	