package com.revature.Reimbursements.beans;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import com.revature.Reimbursements.DAO.ReimbursementDAO;

public class Reimbursement {
	
	private int id;
	private float cost, ramount;
	private String description, location, reason;
	private int eid;
	private Date date;
	private Time time;
	private int formatid, eventid;
	private String status, excreason, denial;
	private float timemissed;
	
	public Reimbursement() {
	}
	
	public Reimbursement(int id, float cost, float ramount, String description, String reason,
			String location, int eid, Date date,/* Time time, */int formatid,
			int eventid, String status, float timemissed, String excreason,
			String denial) {
		super();
		this.id = id;
		this.ramount = ramount;
		this.cost = cost;
		this.description = description;
		this.reason = reason;
		this.location = location;
		this.eid = eid;
		this.date = date;
		//this.time = time;
		this.formatid = formatid;
		this.eventid = eventid;
		this.status = status;
		this.timemissed = timemissed;
		this.excreason = excreason;
		this.denial = denial;
	}
	
	public Reimbursement(int eid, String date, /*Time time,*/ String description, String reason,
			String location, float cost, float timemissed, int gradeformat,
			int eventformat) {
		//super();
		
		//this.cost = cost;
		this.description = description;
		//this.reason = reason;
		//this.location = location;
		//this.eid = eid;
		//this.date = date;
		//this.time = time;
		/*
		gradeFormat gf = ReimbursementDAO.getFormat(gradeformat);
		eventFormat ef = ReimbursementDAO.getEvent(eventformat);
		*/
		
		//this.timemissed = timemissed;
		
	}
	public void initialize() {
		
		int eventPercent = ReimbursementDAO.getEvent(eventid);
		float fullAmount = ((float)eventPercent / 100) * cost;
		float amountAvailable = 1000 - ReimbursementDAO.getEmpAmount(eid);
		if(amountAvailable > 0) {
			if(amountAvailable > fullAmount) {
				ramount = fullAmount;
			} else {
				ramount = amountAvailable;
			}
			ReimbursementDAO.setEmpAmount(eid,amountAvailable - ramount);
		} else ramount = 0;
		this.status = "Needs Supervisor, Head, and Benco Approval";
		if((Float)timemissed == null){
			timemissed = 0;
		}
		excreason = "";
		denial = "";
		ReimbursementDAO.newRequest(this);
		
	}
	public void updateStatus(int manid,boolean b) {
		Employee manager = ReimbursementDAO.getEmployeeById(manid);
		Employee emp = ReimbursementDAO.getEmployeeById(this.eid);
		if(b == false) {
			this.status = "Denied";
		} else {
			if(manager.getDepartment() == 1) {
				if(this.status.equals("Needs Supervisor, Head, and Benco Approval")) {
					this.status = "Needs Supervisor and Head Approval";
				} else if(this.status.equals("Needs Supervisor and Benco Approval")) {
					this.status = "Needs Supervisor Approval";
				} else if(this.status.equals("Needs Head and Benco Approval")) {
					this.status = "Needs Head Approval";
				} else if(this.status.equals("Needs Benco Approval")) {
					this.status = "Approved";
				}
			}
			ArrayList<Integer> departments = ReimbursementDAO.checkDepartmentHead(manid);
			if(departments.contains(emp.getDepartment())) {
				if(this.status.equals("Needs Supervisor, Head, and Benco Approval") ||
						this.status.equals("Needs Supervisor and Benco Approval") || this.status.equals("Needs Head and Benco Approval")) {
					this.status = "Needs Benco Approval";
				} else if(this.status.equals("Needs Supervisor and Head Approval") ||
					this.status.equals("Needs Head Approval") || this.status.equals("Needs Supervisor Approval")) {
					this.status = "Approved";
				}
			} else if(emp.getManagerId() == manid) {
				if(this.status.equals("Needs Supervisor, Head, and Benco Approval")) {
					this.status = "Needs Head and Benco Approval";
				} else if(this.status.equals("Needs Supervisor and Benco Approval")) {
					this.status = "Needs Benco Approval";
				}
			}
		}
		ReimbursementDAO.updateStatus(this);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getRamount() {
		return ramount;
	}
	public void setRamount(float amount) {
		this.ramount = amount;
	}
	public float getCost() {
		return cost;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Time getTime() {
		return time;
	}
	public void setTime(String time) {
		//this.time = java.sql.Time.valueOf(time);
	}
	public int getFormatid() {
		return formatid;
	}
	public void setFormatid(String formatid) {
		this.formatid = ReimbursementDAO.getFormatId(formatid);
	}
	public int getEventid() {
		return eventid;
	}
	public void setEventid(String eventid) {
		this.eventid = ReimbursementDAO.getEventId(eventid);
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public float getTimemissed() {
		return timemissed;
	}
	public void setTimemissed(float timemissed) {
		this.timemissed = timemissed;
	}
	public String getExcreason() {
		return excreason;
	}
	public void setExcreason(String excreason) {
		this.excreason = excreason;
	}
	public String getDenial() {
		return denial;
	}
	public void setDenial(String denial) {
		this.denial = denial;
	}
	
}
	
	

