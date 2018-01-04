package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
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
		List<Reimbursement> list = ReimbursementDAO.getSubordinateRequests(eid);
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(list.size());
		Reimbursement[] rlist = new Reimbursement[list.size()];
		rlist = list.toArray(rlist);
		String res = mapper.writeValueAsString(rlist);
		response.getWriter().append(res);		
				
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}