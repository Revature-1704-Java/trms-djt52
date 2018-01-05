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
	
	public static List<Reimbursement> getEmployeeRequests(int eid) {
		
		PreparedStatement ps = null;
		Reimbursement r = null;
		List<Reimbursement> requests = new ArrayList<>();
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM REQUEST WHERE EID = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, eid);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("RID");
				float cost = rs.getFloat("FCOST");
				float ramount = rs.getFloat("RAMOUNT");
				String desc = rs.getString("FDESCRIPTION");
				String loc = rs.getString("LOC");
				String reas = rs.getString("REASON");
				int emp = rs.getInt("EID");
				Date date = rs.getDate("FDATE");
				//Time time = rs.getTime("ETIME");
				int gfid = rs.getInt("FORMATID");
				int evid = rs.getInt("EVENTID");
				String status = rs.getString("STATUS");
				float timemissed = rs.getFloat("TIMEMISSED");
				String excreason = rs.getString("EXCREASON");
				String denial = rs.getString("DENIAL");
				
				r = new Reimbursement(id,cost,ramount,desc, reas, loc, emp, date, /*time,*/ gfid, evid, status, timemissed, excreason, denial);
				requests.add(r);
			}
			rs.close();
			ps.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return requests;
	}
	
public static Reimbursement getRequest(int rid) {
		
		PreparedStatement ps = null;
		Reimbursement r = null;
		Reimbursement request = new Reimbursement();
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM REQUEST WHERE RID = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, rid);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				int id = rs.getInt("RID");
				float cost = rs.getFloat("FCOST");
				float ramount = rs.getFloat("RAMOUNT");
				String desc = rs.getString("FDESCRIPTION");
				String loc = rs.getString("LOC");
				String reas = rs.getString("REASON");
				int emp = rs.getInt("EID");
				Date date = rs.getDate("FDATE");
				//Time time = rs.getTime("ETIME");
				int gfid = rs.getInt("FORMATID");
				int evid = rs.getInt("EVENTID");
				String status = rs.getString("STATUS");
				float timemissed = rs.getFloat("TIMEMISSED");
				String excreason = rs.getString("EXCREASON");
				String denial = rs.getString("DENIAL");
				
				request = new Reimbursement(id,cost,ramount,desc, reas, loc, emp, date, /*time,*/ gfid, evid, status, timemissed, excreason, denial);
			}
			rs.close();
			ps.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return request;
	}

	public static Employee getEmployee(String email) {
		PreparedStatement ps = null;
		Employee e = null;
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM EMPLOYEE WHERE EMAIL = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				int id = rs.getInt("EID");
				String name = rs.getString("ENAME");
				int department = rs.getInt("EDEPARTMENT");
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
	
	public static Employee getEmployeeById(int eid) {
		PreparedStatement ps = null;
		Employee e = null;
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM EMPLOYEE WHERE EID = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, eid);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				int id = rs.getInt("EID");
				String name = rs.getString("ENAME");
				int department = rs.getInt("EDEPARTMENT");
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
	
	//Needs work
	public static void updateStatus(Reimbursement r) {
		PreparedStatement ps = null;
		Employee e = null;
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "UPDATE REQUEST SET STATUS = ? WHERE RID = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, r.getStatus());
			ps.setInt(2, r.getId());
			ps.execute();
			ps.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void newRequest(Reimbursement r) {
		CallableStatement cs = null;
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO REQUEST (EID, FDATE, LOC, FDESCRIPTION, REASON, "
					+ "FCOST, RAMOUNT, FORMATID, EVENTID, STATUS, TIMEMISSED, EXCREASON, DENIAL)"
					+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
			cs = conn.prepareCall(sql);
			cs.setInt(1, r.getEid());
			cs.setDate(2, r.getDate());
			//cs.setTime(3, r.getTime());
			cs.setString(3, r.getLocation());
			cs.setString(4, r.getDescription());
			cs.setString(5, r.getReason());
			cs.setFloat(6, r.getCost());
			cs.setFloat(7, r.getRamount());
			cs.setInt(8, r.getFormatid());
			cs.setInt(9, r.getEventid());
			cs.setString(10,r.getStatus());
			cs.setFloat(11, r.getTimemissed());
			cs.setString(12,r.getExcreason());
			cs.setString(13,r.getDenial());
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
			String sql = "UPDATE REQUEST SET RAMOUNT = ?, STATUS = ?, EXCREASON = ?, DENIAL = ?"
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
	
	public static int getEvent(int evId) {
		PreparedStatement ps = null;
		int percent = 0;
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT COVERAGE FROM EVENTTYPE WHERE EVENTID = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, evId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				percent = rs.getInt("COVERAGE");
			}
			rs.close();
			ps.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Exception");
		}
		return percent;
	}
	
	public static void setEmpAmount(int id,float amount) {
		CallableStatement cs = null;
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "UPDATE EMPLOYEE SET TOTALAMOUNT = ? WHERE EID = ?";
					

			cs = conn.prepareCall(sql);
			cs.setFloat(1, amount);
			cs.setInt(2, id);
			cs.execute();
			cs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	public static float getEmpAmount(int eid) {
		PreparedStatement ps = null;
		float amount = 0;
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT TOTALAMOUNT FROM EMPLOYEE WHERE EID = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, eid);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				amount = rs.getFloat("TOTALAMOUNT");
			}
			rs.close();
			ps.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Exception");
		}
		return amount;
	}
	
	public static int getEventId(String evname) {
		PreparedStatement ps = null;
		int id = 0;
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT EVENTID FROM EVENTTYPE WHERE EDESCRIPTION = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, evname);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				id = rs.getInt("EVENTID");
			}
			rs.close();
			ps.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Exception");
		}
		return id;
	}
	
	public static int getFormatId(String fname) {
		PreparedStatement ps = null;
		int id = 0;
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT FORMATID FROM GRADEFORMAT WHERE FDESCRIPTION = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, fname);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				id = rs.getInt("FORMATID");
			}
			rs.close();
			ps.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Exception");
		}
		return id;
	}
	
	// Checks if the given employee is a department head
	public static ArrayList<Integer> checkDepartmentHead(int eid) {
		PreparedStatement ps = null;
		ArrayList<Integer> departments = new ArrayList<Integer>();
		
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT DEPARTMENTID FROM DEPARTMENT WHERE HEAD = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1,eid);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				int e = rs.getInt("DEPARTMENTID");
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
			String sql = "SELECT * FROM EMPLOYEE, REQUEST WHERE REPORTSTO = ? AND EMPLOYEE.EID = REQUEST.EID";
			ps = conn.prepareStatement(sql);
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				int rid = rs.getInt("RID");
				float cost = rs.getFloat("FCOST");
				float ramount = rs.getFloat("RAMOUNT");
				String desc = rs.getString("FDESCRIPTION");
				String loc = rs.getString("LOC");
				String reas = rs.getString("REASON");
				int emp = rs.getInt("EID");
				Date date = rs.getDate("FDATE");
				//Time time = rs.getTime("ETIME");
				int gfid = rs.getInt("FORMATID");
				int evid = rs.getInt("EVENTID");
				String status = rs.getString("STATUS");
				float timemissed = rs.getFloat("TIMEMISSED");
				String excreason = rs.getString("EXCREASON");
				String denial = rs.getString("DENIAL");
				
				res.add(new Reimbursement(rid,cost,ramount,desc, reas, loc, emp, date, /*time,*/ gfid, evid, status, timemissed, excreason, denial));
			}
			rs.close();
			ps.close();
		} catch (Exception ex) {
			ex.getMessage();
		}
		
		return res;
		
	}
	
	
	
	public static List<Reimbursement> getDepartmentEmployeeRequests(int i) {
		PreparedStatement ps = null;
		List<Reimbursement> res = new ArrayList<>();
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM EMPLOYEE, REQUEST WHERE EDEPARTMENT = ? AND EMPLOYEE.EID = REQUEST.EID";
			ps = conn.prepareStatement(sql);
			ps.setInt(1,i);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				int rid = rs.getInt("RID");
				float cost = rs.getFloat("FCOST");
				float ramount = rs.getFloat("RAMOUNT");
				String desc = rs.getString("FDESCRIPTION");
				String loc = rs.getString("LOC");
				String reas = rs.getString("REASON");
				int emp = rs.getInt("EID");
				Date date = rs.getDate("FDATE");
				//Time time = rs.getTime("ETIME");
				int gfid = rs.getInt("FORMATID");
				int evid = rs.getInt("EVENTID");
				String status = rs.getString("STATUS");
				float timemissed = rs.getFloat("TIMEMISSED");
				String excreason = rs.getString("EXCREASON");
				String denial = rs.getString("DENIAL");
				
				res.add(new Reimbursement(rid,cost,ramount,desc, reas, loc, emp, date, /*time,*/ gfid, evid, status, timemissed, excreason, denial));
			}
			rs.close();
			ps.close();
		} catch (Exception ex) {
			ex.getMessage();
		}
		return res;
		
	}
	
	public static List<Reimbursement> getAllRequests() {
		PreparedStatement ps = null;
		List<Reimbursement> res = new ArrayList<>();
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM REQUEST";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				int rid = rs.getInt("RID");
				float cost = rs.getFloat("FCOST");
				float ramount = rs.getFloat("RAMOUNT");
				String desc = rs.getString("FDESCRIPTION");
				String loc = rs.getString("LOC");
				String reas = rs.getString("REASON");
				int emp = rs.getInt("EID");
				Date date = rs.getDate("FDATE");
				//Time time = rs.getTime("ETIME");
				int gfid = rs.getInt("FORMATID");
				int evid = rs.getInt("EVENTID");
				String status = rs.getString("STATUS");
				float timemissed = rs.getFloat("TIMEMISSED");
				String excreason = rs.getString("EXCREASON");
				String denial = rs.getString("DENIAL");
				
				res.add(new Reimbursement(rid,cost,ramount,desc, reas, loc, emp, date, /*time,*/ gfid, evid, status, timemissed, excreason, denial));
			}
			rs.close();
			ps.close();
		} catch (Exception ex) {
			ex.getMessage();
		}
		return res;
		
	}
	
	
}
