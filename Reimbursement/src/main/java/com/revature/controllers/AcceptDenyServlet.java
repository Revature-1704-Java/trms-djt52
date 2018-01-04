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

public class AcceptDenyServlet extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String change = request.getParameter("change");
	    String ridStr = request.getParameter("rid");
	    String eidStr = request.getParameter("eid");
	    String denyReason = request.getParameter("deny");
	    int rid = Integer.parseInt(ridStr);
	    int eid = Integer.parseInt(eidStr);
	    Reimbursement rem = ReimbursementDAO.getRequest(rid);
	    if(change.equals("Approve")) {
	    	
	    } else if(change.equals("Deny")) {
	    	rem.setStatus("Denied");
	    	rem.setDenial("denyReason");
	    	ReimbursementDAO.updateRequest(rem);
	    }
	}
}
