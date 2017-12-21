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
	public static String getPassword(String username) {
		PreparedStatement ps = null;
		String pass = null;
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT EPASSWORD FROM EMPLOYEE WHERE EUSERNAME = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
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
				float amount = rs.getFloat("AMOUNT");
				String desc = rs.getString("RDESCRIPTION");
				String loc = rs.getString("LOC");
				int emp = rs.getInt("EID");
				Date date = rs.getDate("EDATE");
				Time time = rs.getTime("ETIME");
				int gfid = rs.getInt("FORMATID");
				int evid = rs.getInt("EVENTID");
				int sapproval = rs.getInt("SAPPROVAL");
				int dhapproval = rs.getInt("DHAPPROVAL");
				int bcapproval = rs.getInt("BCAPPROVAL");
				String status = rs.getString("STATUS");
				float timemissed = rs.getFloat("TIMEMISSED");
				
				r = new Reimbursement(id,amount,desc, loc, emp, date, time, gfid, evid, sapproval,
						dhapproval, bcapproval, status, timemissed);
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
	
	public void newRequest(float amount, String description,
							String location, int eid, Date date, Time time, int formatid,
							int eventid, int sapproval, int dhapproval, int bcapproval,
							String status, float timemissed) {
		CallableStatement cs = null;
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "{INSERT INTO REQUEST (EID, FDATE, FTIME, LOC, FDESCRIPTION, FCOST"
					+ "FORMATID, EVENTID, SAPPROVAL, DHAPPROVAL, BCAPPROVAL, STATUS, TIMEMISSED)"
					+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
			cs = conn.prepareCall(sql);
			cs.setInt(1, eid);
			cs.setDate(2, date);
			cs.setTime(3, time);
			cs.setString(4, location);
			cs.setString(5, description);
			cs.setFloat(6, amount);
			cs.setInt(7, formatid);
			cs.setInt(8, eventid);
			cs.setInt(9, sapproval);
			cs.setInt(10, dhapproval);
			cs.setInt(11, bcapproval);
			cs.setString(12,status);
			cs.setFloat(13, timemissed);
			
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
