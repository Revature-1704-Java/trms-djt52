package com.revature.Reimbursements;

import java.util.List;
import java.util.ArrayList;

import com.revature.Reimbursements.DAO.ReimbursementDAO;
import com.revature.Reimbursements.beans.Employee;
import com.revature.Reimbursements.beans.Reimbursement;

import static org.junit.Assert.*;

import org.junit.Test;

public class AppTest 
{
	@Test
	public final void amountTest() {
		Reimbursement r = new Reimbursement(1,0,"test",1);
		r.setAmount(10);
		assertEquals(10,(int)r.getAmount());
		r.setAmount(-12);
		assertEquals(10,(int)r.getAmount());
	}
	
	@Test
	public final void eidTest() {
		Reimbursement r = new Reimbursement(1,0,"test",1);
		r.setEid(10);
		assertEquals(10,r.getEid());
		r.setEid(-12);
		assertEquals(10,r.getEid());
	}
	@Test
	public final void ridTest() {
		Reimbursement r = new Reimbursement(1,0,"test",1);
		r.setId(10);
		assertEquals(10,r.getId());
		r.setId(-12);
		assertEquals(10,r.getId());
	}
	@Test
	public final void idTest() {
		Employee e = new Employee(1,"Bob","Sales","bob","password",1);
		e.setId(2);
		assertEquals(2,e.getId());
		e.setId(-4);
		assertEquals(2,e.getId());
	}
	@Test
	public final void midTest() {
		Employee e = new Employee(1,"Bob","Sales","bob","password",1);
		e.setManagerId(2);
		assertEquals(2,e.getManagerId());
		e.setManagerId(-4);
		assertEquals(2,e.getManagerId());
	}
	@Test
	public final void nameTest() {
		Employee e = new Employee(1,"Bob","Sales","bob","password",1);
		e.setName("Roy");
		assertEquals("Roy",e.getName());
		e.setName("");
		assertEquals("Roy",e.getName());
	}
	@Test
	public final void loginTest() {
		String username = "test";
		ReimbursementDAO dao = new ReimbursementDAO();
		Employee e = dao.getEmployee(username);
		assertEquals(5,e.getId());
	}
	@Test
	public final void getEmployeesTest() {
		List<Employee> list = new ArrayList<Employee>();
		ReimbursementDAO dao = new ReimbursementDAO();
		list = dao.getAllEmps();
		assertEquals("test",list.get(4).getName());
	}
	@Test
	public final void getRequestsTest() {
		ReimbursementDAO dao = new ReimbursementDAO();
		List<Reimbursement> r = dao.getEmployeeRequests(5);
		assertEquals("test",r.get(0).getDescription());
	}
}
