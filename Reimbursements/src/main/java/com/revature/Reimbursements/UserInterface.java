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
	
	//Login method can add incorrect username function later
	public static String login(String username) {
		String att = ReimbursementDAO.getPassword(username);
		if(att.equals(null)) {
			return att;
		} else {
			return att;
		}
	}
	
	//Return Reimbursements, need to add return type after learning servlets
	
	public void getRequests(String username) {
		List<Reimbursement> requests = ReimbursementDAO.getEmployeeRequests(username);
		
	}
	
	// Form submission
	public void newRequest() {
		
	}
	
	// View requests to be approved
	public void managerView(String username) {
		Employee e = ReimbursementDAO.getEmployee(username);
		if(e.getDepartment().equals("BenCo")) {
			
		} else if(ReimbursementDAO.checkDepartmentHead(e.getId()).size() != 0) {
			
		} else {
			List<Reimbursement> toBeApproved = ReimbursementDAO.getSubbordinateRequests(e.getId());
			for(Reimbursement r : toBeApproved) {
			}
		}
	}
	/*
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
	*/
}
