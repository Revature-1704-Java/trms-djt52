package com.revature.Reimbursements.beans;

public class Employee {
	
	private int id;
	private String name;
	private String username;
	private String password;
	private String department;
	private int managerId;
	
	public Employee(int id, String name, String username, String password,
		String department, int managerId) {
	super();
	this.id = id;
	this.name = name;
	this.username = username;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public int getManagerId() {
		return managerId;
	}
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
}
	
	