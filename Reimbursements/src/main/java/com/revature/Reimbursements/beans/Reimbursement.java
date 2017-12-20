package com.revature.Reimbursements.beans;

public class Reimbursement {
	
	private int id;
	private float amount;
	private String description;
	private int eid;
	
	
	public Reimbursement(int id, float amount, String description, int eid) {
		super();
		this.id = id;
		this.amount = amount;
		this.description = description;
		this.eid = eid;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		if(id >= 0)
		this.id = id;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		if(amount >= 0)
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		if(eid >= 0)
		this.eid = eid;
	}
	
}
