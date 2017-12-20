package com.revature.Reimbursements.beans;

public class Employee {
	private int id;
	private String name;
	private String description;
	private String username;
	private String password;
	private int managerId;
	
	public Employee() {
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		if(id >= 0)
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		if(name.length() > 0)
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
	public int getManagerId() {
		return managerId;
	}
	public void setManagerId(int managerId) {
		if(managerId > 0)
		this.managerId = managerId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Employee(int id, String name, String description, String username,
			String password, int managerId) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.username = username;
		this.password = password;
		this.managerId = managerId;
	}

	
}
