package com.revature.Reimbursements;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.Reimbursements.DAO.ReimbursementDAO;
import com.revature.Reimbursements.beans.Employee;
import com.revature.Reimbursements.beans.Reimbursement;

public class UserInterface {
	
//	private ReimbursementDAO dao;
	
	public static void main(String[] args){
		UserInterface ui = new UserInterface();
		System.out.println("Ctrl + C to quit");
	}
	
	public UserInterface() {
		System.out.println("Welcome to the employee reimbursement program");
		Scanner sc = new Scanner(System.in);
		String username = "";
		System.out.println("Please enter your username:");
		username = sc.nextLine();
		ReimbursementDAO dao = new ReimbursementDAO();
		Employee emp = dao.getEmployee(username);
		if(emp == null) {
			System.out.println("Incorrect username");
			UserInterface ui = new UserInterface();
		}
		else {
			System.out.println("Please enter your password:");
			String password = sc.nextLine();
			if(password.equals(emp.getPassword())) {
				main(dao,emp);
			} else {
				System.out.println("Incorrect password");
				UserInterface ui = new UserInterface();
			}
		}
	}
		
	public void main(ReimbursementDAO dao, Employee emp) {
		Scanner sc = new Scanner(System.in);
		String command = "";
		boolean exit = false;
		while(!exit) {
			System.out.println("Would you like to check your requests, make a new request, or enter manager mode?");
			command = sc.nextLine();
			if(command.equals("check") || command.equals("Check") || command.equals("c")) {
				check(emp,dao);
			} else if(command.equals("request") || command.equals("Request") || command.equals("r")) {
				request(emp,dao);
			} else if(command.equals("Manager") || command.equals("manager") || command.equals("m")) {
				manager(emp,dao);
			} else if(command.equals("q") || command.equals("exit") || command.equals("Exit") ||
				command.equals("Quit") || command.equals("quit")) {
				exit = true;
			} else {
				System.out.println("Please insert a valid command.");
			}
		}
		sc.close();
	}
	public void check(Employee e, ReimbursementDAO d) {
		List<Reimbursement> requests = d.getEmployeeRequests(e.getId());
		System.out.println(requests.size());
		for(Reimbursement r: requests) {
			System.out.println("Amount: " + r.getAmount() + ", " + r.getDescription());
		}
	}
	
	public void request(Employee e, ReimbursementDAO d) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please insert the amount you are requesting:");
		int amount = Integer.parseInt(sc.nextLine());
		if(amount < 0) {
			System.out.println("Please insert a non negative value.");
			request(e,d);
		} else {
			System.out.println("Please insert the reason for the request:");
			String des = sc.nextLine();
			d.newRequest(e.getId(), amount, des);
		}
	}
	
	public void manager(Employee e, ReimbursementDAO d) {
		List<Employee> emps = new ArrayList<Employee>();
		List<Reimbursement> requests = new ArrayList<Reimbursement>();
		emps = d.getAllEmps();
		for(Employee emp: emps) {
			if(emp.getManagerId() == e.getId()) {
				requests = d.getEmployeeRequests(emp.getId());
				System.out.println(emp.getName() + " :");
				for(Reimbursement r : requests) {
					System.out.println("Amount: " + r.getAmount() + ", " + r.getDescription());
				}
			}
		}
	}
}
