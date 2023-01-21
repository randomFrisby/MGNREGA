package in.nic.mgnrega.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.nic.mgnrega.colors.ConsoleColors;
import in.nic.mgnrega.exception.EmployeeException;
import in.nic.mgnrega.exception.GPMException;
import in.nic.mgnrega.exception.ProjectException;
import in.nic.mgnrega.model.Employee;
import in.nic.mgnrega.model.EmployeeWageOperationDTO;
import in.nic.mgnrega.model.Project;
import in.nic.mgnrega.util.DBUtil;

public class GPMImpl implements GPMInterface {
	
	public static int loggedInGPMId;

	@Override
	public boolean loginGPM(String email, String password) throws GPMException {

		boolean response = false;
		
		try(Connection conn = DBUtil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("select * from gpmember where binary email=? and binary password=?");
			ps.setString(1, email);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				response = true;
				loggedInGPMId = rs.getInt("gpid");
			} else {
				throw new GPMException("\n" + ConsoleColors.WHITE_BOLD_BRIGHT + ConsoleColors.RED_BACKGROUND +"NO RECORD FOUND" + ConsoleColors.RESET);
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		
		return response;
	}

	
	
	
	@Override
	public String createEmployee(Employee emp) throws EmployeeException {
		String response = "Unable to create a new Employee";
		
		try(Connection conn= DBUtil.provideConnection()) {
			
			PreparedStatement ps = conn.prepareStatement("insert into employee (egpid, ename, address, date_joined, wage) "
					+ "values (?, ?, ?, ?, ?)");
			ps.setInt(1, emp.getEgpid());
			ps.setString(2, emp.getEname());
			ps.setString(3, emp.getAddress());
			ps.setDate(4, emp.getDate_joined());
			ps.setInt(5, emp.getWage());
			
			int x= ps.executeUpdate();
			if (x > 0) {
				response = ConsoleColors.GREEN + "New Employee Created Successfully!" + ConsoleColors.RESET;
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return response;
	}



	
	@Override
	public List<Employee> viewAllEmployee() throws EmployeeException {
		
		List<Employee> employees = new ArrayList<>();
		
		try(Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps = conn.prepareStatement("select * from employee where egpid=?");
			ps.setInt(1, loggedInGPMId);
			
			ResultSet rs = ps.executeQuery();
			boolean flag = false;
			
			while (rs.next()) {
				flag = true;
				Employee emp = new Employee();
				emp.setEid(rs.getInt("eid"));
				emp.setEgpid(rs.getInt("egpid"));
				emp.setEpid(rs.getInt("epid"));
				emp.setEname(rs.getString("ename"));
				emp.setAddress(rs.getString("address"));
				emp.setDate_joined(rs.getDate("date_joined"));
				emp.setWage(rs.getInt("wage"));
				
				employees.add(emp);
			}
			
			if (!flag) {
				throw new EmployeeException("\nNO EMPLOYEE WORKING IN THIS GRAM WITH GPM ID: " + loggedInGPMId);
			}
			
			
		} catch (SQLException e) {
			System.out.println(ConsoleColors.RED_BACKGROUND + ConsoleColors.WHITE_BOLD_BRIGHT + e.getMessage() + ConsoleColors.RESET);
		}
		
		return employees;
	}
	
	
	
	@Override
	public List<Project> viewAllProject() throws ProjectException {
		
		List<Project> projects = new ArrayList<>();
		
		try(Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps = conn.prepareStatement("select * from project where pgpid=?");
			ps.setInt(1, loggedInGPMId);
			
			ResultSet rs = ps.executeQuery();
			boolean flag = false;
			
			while (rs.next()) {
				flag = true;

				Project p = new Project();
				p.setPid(rs.getInt("pid"));
				p.setPgpid(rs.getInt("pgpid"));
				p.setPname(rs.getString("pname"));
				p.setBudget(rs.getInt("budget"));
				p.setDuration(rs.getInt("duration"));
				
				projects.add(p);
			}
			
			if (!flag) {
				throw new ProjectException("\nNO PROJECT IN THIS GRAM WITH GPM ID: " + loggedInGPMId);
			}
			
			
		} catch (SQLException e) {
			System.out.println(ConsoleColors.RED_BACKGROUND + ConsoleColors.WHITE_BOLD_BRIGHT + e.getMessage() + ConsoleColors.RESET);
		}
		
		return projects;
	}



	
	@Override
	public String assingnEmployeeToProject(int eid, int pid) throws EmployeeException, GPMException, ProjectException {
		String response = "Unable to assign Employee to a Project";
		
		try(Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps = conn.prepareStatement("select * from employee where eid=? and egpid=?");
			ps.setInt(1, eid);
			ps.setInt(2, loggedInGPMId);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				
				PreparedStatement ps1 = conn.prepareStatement("select * from project where pid=? and pgpid=?");
				ps1.setInt(1, pid);
				ps1.setInt(2, loggedInGPMId);
				
				ResultSet rs1 = ps1.executeQuery();
				
				if (rs1.next()) {
					
					PreparedStatement ps2 = conn.prepareStatement("update employee set epid=? where eid=?");
					ps2.setInt(1, pid);
					ps2.setInt(2, eid);
					
					int x= ps2.executeUpdate();
					
					if(x > 0) {
						response = "Project with ID : " + pid + " assigned to an Employee with ID : " + eid;
					}
					
					
				} else {
					throw new ProjectException("Either Project with ID " + pid + " doesn't exist or assingned to another Gram Panchayat Member by BDO");
				}
				
			} else {
				throw new EmployeeException("Either Employee with ID " + eid + " doesn't exist or already working under a different Gram Panchayat Member");
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		
		return response;
	}

	
	public List<EmployeeWageOperationDTO> employeeDaysAndWage() throws EmployeeException {
		
		List<EmployeeWageOperationDTO> dayandWageList = new ArrayList<>();
		
		
		try (Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps = conn.prepareStatement("select e.eid, e.ename, p.pid, p.pname, e.date_joined, datediff(curdate(), date_joined) "
					+ "as days_worked, e.wage, datediff(curdate(), date_joined) * e.wage as total_amount "
					+ "from employee e INNER JOIN project p "
					+ "ON e.epid = p.pid "
					+ "group by e.eid");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				int eid = rs.getInt("eid");
				String ename = rs.getString("ename");
				int pid = rs.getInt("pid");
				String pname = rs.getString("pname");
				Date date = rs.getDate("date_joined");
				int days = rs.getInt("days_worked");
				int wage = rs.getInt("wage");
				int total = rs.getInt("total_amount");
				
				EmployeeWageOperationDTO empWageTotal = new EmployeeWageOperationDTO(eid, ename, pid, pname, date, days, wage, total);
				dayandWageList.add(empWageTotal);	
				
			}
			
			
			
		} catch (SQLException e) {
			throw new EmployeeException(e.getMessage());
		}
		
		
		if(dayandWageList.size() == 0) {
			throw new EmployeeException("Exception : No employee Found in DataBase");
		}
			
		return dayandWageList;
		
	}
	
	
}
