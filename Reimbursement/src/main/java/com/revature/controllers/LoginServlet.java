package com.revature.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

import com.revature.Reimbursements.DAO.ReimbursementDAO;
import com.revature.Reimbursements.beans.Employee;


@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("email");
	    String password = request.getParameter("pass");
		//Employee e = ReimbursementDAO.getEmployee(username);
	    if(username.equals("erty") && password.equals("ertoo")) {
	    	System.out.println("Got it");
	    }
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
