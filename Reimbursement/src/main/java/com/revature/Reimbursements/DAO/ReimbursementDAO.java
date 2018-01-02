package com.revature.Reimbursements.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import com.revature.Reimbursements.beans.Employee;
import com.revature.Reimbursements.beans.Reimbursement;
import com.revature.Reimbursements.util.ConnectionUtil;

public class ReimbursementDAO {
	
	//DAO function to return password for login in front end
	public static String getPassword(String email) {
		PreparedStatement ps = null;
		String pass = null;
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT EPASSWORD FROM EMPLOYEE WHERE EUSERNAME = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				pass = rs.getString("EPASSWORD");
			}
			rs.close();
			ps.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Exception");
		}
		return pass;
	}
	
	public static List<Reimbursement> getEmployeeRequests(String username) {
		
		PreparedStatement ps = null;
		Reimbursement r = null;
		List<Reimbursement> requests = new ArrayList<>();
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM REIMBURSEMENT WHERE EUSERNAME = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("RID");
				int cost = rs.getInt("FCOST");
				float ramount = rs.getFloat("RAMOUNT");
				String desc = rs.getString("RDESCRIPTION");
				String loc = rs.getString("LOC");
				String reas = rs.getString("REASON");
				int emp = rs.getInt("EID");
				Date date = rs.getDate("EDATE");
				Time time = rs.getTime("ETIME");
				int gfid = rs.getInt("FORMATID");
				int evid = rs.getInt("EVENTID");
				String status = rs.getString("STATUS");
				float timemissed = rs.getFloat("TIMEMISSED");
				String excreason = rs.getString("EXCREASON");
				String denial = rs.getString("DENIAL");
				
				r = new Reimbursement(id,cost,ramount,desc, reas, loc, emp, date, time, gfid, evid, status, timemissed, excreason, denial);
				requests.add(r);
			}
			rs.close();
			ps.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return requests;
	}
	
	public static Employee getEmployee(String username) {
		PreparedStatement ps = null;
		Employee e = null;
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM EMPLOYEE WHERE EMAIL = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				int id = rs.getInt("EID");
				String name = rs.getString("ENAME");
				String department = rs.getString("EDEPARTMENT");
				String user = rs.getString("EMAIL");
				String pass = rs.getString("EPASSWORD");
				int manid = rs.getInt("REPORTSTO");
				
				e = new Employee(id,name,user,pass,department,manid);
			}
			rs.close();
			ps.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Exception");
		}
		return e;
	}
	
	public void newRequest(Reimbursement r) {
		CallableStatement cs = null;
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "{INSERT INTO REQUEST (EID, FDATE, FTIME, LOC, FDESCRIPTION, REASON, "
					+ "FCOST, RAMOUNT, FORMATID, EVENTID, STATUS, TIMEMISSED, EXCREASON, DENIAL)"
					+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
			cs = conn.prepareCall(sql);
			cs.setInt(1, r.getId());
			cs.setDate(2, r.getDate());
			cs.setTime(3, r.getTime());
			cs.setString(4, r.getLocation());
			cs.setString(5, r.getDescription());
			cs.setString(6, r.getReason());
			cs.setFloat(7, r.getCost());
			cs.setFloat(8, r.getRamount());
			cs.setInt(9, r.getFormatid());
			cs.setInt(10, r.getEventid());
			cs.setString(11,r.getStatus());
			cs.setFloat(12, r.getTimemissed());
			cs.setString(13,r.getExcreason());
			cs.setString(14,r.getDenial());
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
	
	public static void updateRequest(Reimbursement r) {
		CallableStatement cs = null;
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "{UPDATE REQUEST SET RAMOUNT = ?, STATUS = ?,EXCREASON = ?, DENIAL = ?)"
					+ "WHERE RID = ?";

			cs = conn.prepareCall(sql);
			cs.setFloat(1, r.getRamount());
			cs.setString(2,r.getStatus());
			cs.setString(3,r.getExcreason());
			cs.setString(4,r.getDenial());
			cs.setInt(5, r.getId());
			Boolean result = cs.execute();
			if (!result)
				System.out.println("Request Updated");
			else
				System.out.println("Failed");
			
			cs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	// Checks if the given employee is a department head
	public static ArrayList<String> checkDepartmentHead(int eid) {
		PreparedStatement ps = null;
		ArrayList<String> departments = new ArrayList<String>();
		
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT DNAME FROM DEPARTMENT WHERE HEAD = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1,eid);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				String e = rs.getString("DNAME");
				departments.add(e);
			}
			rs.close();
			ps.close();
		} catch (Exception ex) {
			ex.getMessage();
		}
		
		return departments;
	}
	
	// Returns all of the subordinate requests of a manager
	//needs work
	//
	//
	public static List<Reimbursement> getSubordinateRequests(int id) {
		PreparedStatement ps = null;
		List<Reimbursement> res = new ArrayList<>();
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM EMPLOYEE,REIMBURSEMENT WHERE REPORTSTO = ? AND EMPLOYEE.EID = REQUEST.EID";
			ps = conn.prepareStatement(sql);
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				String e = rs.getString("DNAME");
			//	.add(e);
			}
			rs.close();
			ps.close();
		} catch (Exception ex) {
			ex.getMessage();
		}
		
		return res;
		
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
