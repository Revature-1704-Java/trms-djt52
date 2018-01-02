package com.revature.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

import com.revature.Reimbursements.DAO.ReimbursementDAO;
import com.revature.Reimbursements.beans.Employee;


//@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
	    String password = request.getParameter("pass");
		Employee e = ReimbursementDAO.getEmployee(email);
		if(e != null) {
			if(password.equals(e.getPassword())) {
				response.getWriter().append(e.getName());
			} else {
				response.getWriter().append("Incorrect Password");
			}
		} else {
			response.getWriter().append("Invalid Email");
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
