package com.revature.Reimbursements.beans;

import java.sql.Date;
import java.sql.Time;

import com.revature.Reimbursements.DAO.ReimbursementDAO;

public class Reimbursement {
	
	private int id;
	private float cost, ramount;
	private String description, location, reason;
	private int eid;
	private Date date;
	private Time time;
	private String formatid, eventid;
	private String status, excreason, denial;
	private float timemissed;
	
	public Reimbursement() {
	}
	
	public Reimbursement(int id, float cost, float ramount, String description, String reason,
			String location, int eid, Date date, Time time, String formatid,
			String eventid, String status, float timemissed, String excreason,
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
		this.time = time;
		this.formatid = formatid;
		this.eventid = eventid;
		this.status = status;
		this.timemissed = timemissed;
		this.excreason = excreason;
		this.denial = denial;
	}
	
	public Reimbursement(int eid, String date, Time time, String description, String reason,
			String location, float cost, float timemissed, String gradeformat,
			String eventformat) {
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
	public String getFormatid() {
		return formatid;
	}
	public void setFormatid(String formatid) {
		this.formatid = formatid;
	}
	public String getEventid() {
		return eventid;
	}
	public void setEventid(String eventid) {
		this.eventid = eventid;
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
	
	

