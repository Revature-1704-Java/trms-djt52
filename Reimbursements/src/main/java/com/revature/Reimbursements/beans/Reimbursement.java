package com.revature.Reimbursements.beans;

import java.sql.Date;
import java.sql.Time;

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
	
	
	public Reimbursement(int id, float cost, float ramount, String description, String reason,
			String location, int eid, Date date, Time time, int formatid,
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
		this.time = time;
		this.formatid = formatid;
		this.eventid = eventid;
		this.status = status;
		this.timemissed = timemissed;
		this.excreason = excreason;
		this.denial = denial;
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
	public void setTime(Time time) {
		this.time = time;
	}
	public int getFormatid() {
		return formatid;
	}
	public void setFormatid(int formatid) {
		this.formatid = formatid;
	}
	public int getEventid() {
		return eventid;
	}
	public void setEventid(int eventid) {
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
	
	

