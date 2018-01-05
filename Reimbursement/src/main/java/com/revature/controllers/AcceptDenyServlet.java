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

public class AcceptDenyServlet extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String apps = request.getParameter("apps");
	    String deny = request.getParameter("deny");
	    int eid = Integer.parseInt(request.getParameter("eid"));
	    ArrayList<Integer> appnumbers = new ArrayList<Integer>();
	    ArrayList<Integer> denynumbers = new ArrayList<Integer>();
	    if(apps != null) {
		    for(int i = 0;i < apps.length();i++) {
		    	StringBuilder builder = new StringBuilder();
		    	if(apps.charAt(i) == ',') {
		    		appnumbers.add(Integer.parseInt(builder.toString()));
		    	
		    	}else {
		    		builder.append(apps.charAt(i));
		    	}
		    	if( i + 1 == apps.length()) {
		    		appnumbers.add(Integer.parseInt(builder.toString()));
		    	}
		    }
	    }
	    if(deny != null) {
		    for(int i = 0;i < deny.length();i++) {
		    	StringBuilder builder = new StringBuilder();
		    	if(deny.charAt(i) == ',')  {
		    		denynumbers.add(Integer.parseInt(builder.toString()));
		    	} else {
		    		builder.append(deny.charAt(i));
		    	}
		    	if(i + 1 == deny.length()) {
		    		denynumbers.add(Integer.parseInt(builder.toString()));
		    	}
		    }
	    }
	    for(int a: appnumbers) {
	    	Reimbursement rem = ReimbursementDAO.getRequest(a);
	    	rem.updateStatus(eid,true);
	    }
	    for(int d: denynumbers) {
	    	Reimbursement rem = ReimbursementDAO.getRequest(d);
	    	rem.updateStatus(eid,false);
	    }
	    
	    
	}
}
