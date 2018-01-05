package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.Reimbursements.DAO.ReimbursementDAO;
import com.revature.Reimbursements.beans.Employee;
import com.revature.Reimbursements.beans.Reimbursement;

public class ManServlet extends HttpServlet{

		
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String param = request.getParameter("eid");
		int eid = Integer.parseInt(param);
		List<Reimbursement> list = new ArrayList<Reimbursement>();
		Employee e = ReimbursementDAO.getEmployeeById(eid);
		if(e.getDepartment() == 1) {
			list = ReimbursementDAO.getAllRequests();
		} else {
			ArrayList<Integer> departments = ReimbursementDAO.checkDepartmentHead(eid);
			if(departments.size() != 0) {
				for(Integer i: departments) {
					list.addAll(ReimbursementDAO.getDepartmentEmployeeRequests(i));
				}
			}
			List<Reimbursement> list2 = ReimbursementDAO.getSubordinateRequests(eid);
			boolean check = true;
			list.addAll(list2);
			
			for(Reimbursement r: list2) {
				check = true;
				for(Reimbursement r2:list) {
					if(r.getId() == r2.getId()) {
						check = false;
						System.out.println("Found repeat");
					}
				}
				if(check) {
					System.out.println("repeated")
					list2.add(r);
					
				}
			}
		}
		ObjectMapper mapper = new ObjectMapper();
		Reimbursement[] rlist = new Reimbursement[list.size()];
		rlist = list.toArray(rlist);
		String res = mapper.writeValueAsString(rlist);
		response.getWriter().append(res);		
				
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}