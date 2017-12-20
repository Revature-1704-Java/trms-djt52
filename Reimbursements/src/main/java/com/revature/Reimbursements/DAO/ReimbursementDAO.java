package com.revature.Reimbursements.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.Reimbursements.beans.Employee;
import com.revature.Reimbursements.beans.Reimbursement;
import com.revature.Reimbursements.util.ConnectionUtil;

public class ReimbursementDAO {
	
	public List<Reimbursement> getEmployeeRequests(int eId) {
		
		PreparedStatement ps = null;
		Reimbursement r = null;
		List<Reimbursement> requests = new ArrayList<>();
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM REIMBURSEMENT WHERE EID = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, eId);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("RID");
				float amount = rs.getFloat("AMOUNT");
				String desc = rs.getString("RDESCRIPTION");
				int weight = rs.getInt("EID");
				
				r = new Reimbursement(id,amount,desc,weight);
				requests.add(r);
			}
			rs.close();
			ps.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return requests;
	}
	
	public Employee getEmployee(String username) {
		PreparedStatement ps = null;
		Employee e = null;
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM EMPLOYEE WHERE EUSERNAME = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				int id = rs.getInt("EID");
				String name = rs.getString("ENAME");
				String position = rs.getString("EPOSITION");
				String user = rs.getString("EUSERNAME");
				String pass = rs.getString("EPASSWORD");
				int manid = rs.getInt("MANAGERID");
				
				e = new Employee(id,name,position,user,pass,manid);
			}
			rs.close();
			ps.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Exception");
		}
		return e;
	}
	
	public void newRequest(int eid, int amount, String des) {
		CallableStatement cs = null;
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "{CALL REIMBURSEREQUEST(?, ?, ?)}";
			cs = conn.prepareCall(sql);
			cs.setInt(1, eid);
			cs.setInt(2, amount);
			cs.setString(3, des);
			
			Boolean result = cs.execute();
			if (!result)
				System.out.println("Request Added");
			else
				System.out.println("Failed");
			
			cs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public List<Employee> getAllEmps() {
		PreparedStatement ps = null;
		Employee e = null;
		List<Employee> emps = new ArrayList<>();
		
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM EMPLOYEE";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("EID");
				String name = rs.getString("ENAME");
				String position = rs.getString("EPOSITION");
				String user = rs.getString("EUSERNAME");
				String pass = rs.getString("EPASSWORD");
				int manid = rs.getInt("MANAGERID");
				e = new Employee(id,name,position,user,pass,manid);
				emps.add(e);
			}
			rs.close();
			ps.close();
		} catch (Exception ex) {
			ex.getMessage();
		}
		
		return emps;
	}
	
}
