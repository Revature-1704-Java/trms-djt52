package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.Reimbursements.DAO.ReimbursementDAO;
import com.revature.Reimbursements.beans.Employee;
import com.revature.Reimbursements.beans.Reimbursement;

//A servlet to add new requests to the database
public class NewFormServlet extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		StringBuilder buffer = new StringBuilder();
	    BufferedReader reader = request.getReader();
	    String line;
	    while ((line = reader.readLine()) != null) {
	        buffer.append(line);
	    }
	    String json = buffer.toString();
		ObjectMapper mapper = new ObjectMapper();
		Reimbursement rem = mapper.readValue(json, Reimbursement.class);
		if(rem != null) {
			rem.initialize();
			Logger log = Logger.getRootLogger();
			log.info("New Request: " + rem.getId());
			response.getWriter().append(rem.getReason());
		} else {
			response.getWriter().append("Other problem");
		}
	}
}
