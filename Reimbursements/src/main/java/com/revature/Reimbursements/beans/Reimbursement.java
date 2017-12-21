package com.revature.Reimbursements.beans;

import java.sql.Date;
import java.sql.Time;

public class Reimbursement {
	
	private int id;
	private float amount;
	private String description, location;
	private int eid;
	private Date date;
	private Time time;
	private int formatid, eventid;
	private int sapproval, dhapproval, bcapproval;
	private String status;
	private float timemissed;
	
	
	public Reimbursement(int id, float amount, String description,
			String location, int eid, Date date, Time time, int formatid,
			int eventid, int sapproval, int dhapproval, int bcapproval,
			String status, float timemissed) {
		super();
		this.id = id;
		this.amount = amount;
		this.description = description;
		this.location = location;
		this.eid = eid;
		this.date = date;
		this.time = time;
		this.formatid = formatid;
		this.eventid = eventid;
		this.sapproval = sapproval;
		this.dhapproval = dhapproval;
		this.bcapproval = bcapproval;
		this.status = status;
		this.timemissed = timemissed;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public int getSapproval() {
		return sapproval;
	}
	public void setSapproval(int sapproval) {
		this.sapproval = sapproval;
	}
	public int getDhapproval() {
		return dhapproval;
	}
	public void setDhapproval(int dhapproval) {
		this.dhapproval = dhapproval;
	}
	public int getBcapproval() {
		return bcapproval;
	}
	public void setBcapproval(int bcapproval) {
		this.bcapproval = bcapproval;
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
}
	
	

