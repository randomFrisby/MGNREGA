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
import in.nic.mgnrega.model.GPMember;
import in.nic.mgnrega.model.Project;
import in.nic.mgnrega.util.DBUtil;

public class BDOImpl implements BDOInterface {

	@Override
	public boolean loginBdo(String username, String password) {
		boolean response = false;
		
		if (username.equals(BDOInterface.username) && password.equals(BDOInterface.password)) {
			response = true;
		}
		
		
		return response;
	}

	
	
	@Override
	public String createProject(Project p) throws ProjectException{
		String response = "\n" + ConsoleColors.RED_BACKGROUND + "Unable to create project";
		
		try(Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps = conn.prepareStatement("insert into project (pname, budget, duration) values (?, ?, ?)");
			ps.setString(1, p.getPname());
			ps.setInt(2, p.getBudget());
			ps.setInt(3,  p.getDuration());
			
			int x= ps.executeUpdate();
			if (x > 0) {
				response = "\n" + ConsoleColors.GREEN_BACKGROUND + "Project created successfully" + ConsoleColors.RESET;
			} else {
				throw new ProjectException("Error while creating a new project. Try again");
			}
			
		} catch (SQLException e) {
			throw new ProjectException(e.getMessage());
		}
		
		
		
		return response;
	}



	@Override
	public List<Project> viewAllProject() throws ProjectException {
		List<Project> projects = new ArrayList<>();
		
		try(Connection conn = DBUtil.provideConnection()) {
		
			PreparedStatement ps = conn.prepareStatement("select * from project");
			
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
			
			if (flag == false) {
				throw new ProjectException("No Project available");
			}
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return projects;
	}


	
	@Override
	public String createGPM(GPMember gpm) throws GPMException {
		String response = "Unable to create a GPM";
		
		try(Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps = conn.prepareStatement("insert into gpmember (gname, gpanchayat, email, password) "
					+ "values (?, ?, ?, ?)");
			ps.setString(1, gpm.getGname());
			ps.setString(2, gpm.getGpanchayat());
			ps.setString(3, gpm.getEmail());
			ps.setString(4, gpm.getPassword());
			
			int x= ps.executeUpdate();
			
			if (x > 0) {
				response = "\n" + ConsoleColors.GREEN + "GPM created successfully" + ConsoleColors.RESET;
			} else {
				throw new GPMException("Problem occured while creating GPM. Try again.");
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return response;
	}




	@Override
	public List<GPMember> viewAllGPM() throws GPMException {

		List<GPMember> members = new ArrayList<>();
		
		try(Connection conn = DBUtil.provideConnection()) {
		
			PreparedStatement ps = conn.prepareStatement("select * from gpmember");
			
			ResultSet rs = ps.executeQuery();
			boolean flag = false;
			
			while (rs.next()) {
				
				flag = true;
				GPMember gpm = new GPMember();
				gpm.setGpid(rs.getInt("gpid"));
				gpm.setGname(rs.getString("gname"));
				gpm.setGpanchayat(rs.getString("gpanchayat"));
				gpm.setPhone(rs.getString("email"));
				gpm.setPassword(rs.getString("password"));
				
				members.add(gpm);
				
				
			}
			
			if (flag == false) {
				throw new GPMException("No Members available");
			}
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return members;
	}



	@Override
	public String allocateProjectToGPM(int pid, int gpid)  throws ProjectException, GPMException {

		String response = "Unable to allocate project";
		
		try(Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps = conn.prepareStatement("select * from project where pid=?");
			ps.setInt(1, pid);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				
				PreparedStatement ps1 = conn.prepareStatement("select * from gpmember where gpid=?");
				ps1.setInt(1, gpid);
				
				ResultSet rs1 = ps1.executeQuery();
				if (rs1.next()) {
					
					PreparedStatement ps2 = conn.prepareStatement("update project set pgpid=? where pid=?");
					ps2.setInt(1, gpid);
					ps2.setInt(2, pid);
					
					int x = ps2.executeUpdate();
					if (x > 0) {
						response = "Project ID " + pid + " allocated to Gram Panchayat Member with ID " + gpid;
					}
					
				} else {
					throw new GPMException("Member with id " + gpid + " doesn't exist!");
				}
				
			} else {
				throw new ProjectException("Project with id " + pid + " doesn't exist!");
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		
		return response;
	}

	
	public List<Employee> employeeOnAProject(int pid) throws ProjectException, EmployeeException {
		
		List<Employee> empList = new ArrayList<>();
		
		
		try (Connection conn = DBUtil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("select * from project where pid =?");
			ps.setInt(1, pid);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				PreparedStatement ps2 = conn.prepareStatement("select * from employee e INNER JOIN project p "
						+ "on e.epid = p.pid WHERE p.pid = ?");
				ps2.setInt(1, pid);
				
				ResultSet rs2 = ps2.executeQuery();
				
				while(rs2.next()) {

					int eid = rs2.getInt("eid");
					int egpid = rs2.getInt("egpid");
					int epid = rs2.getInt("epid");
					String name = rs2.getString("ename");
					String address = rs2.getString("address");
					Date date = rs2.getDate("date_joined");
					int wage = rs2.getInt("wage");
					
					
					Employee emp = new Employee(eid, egpid, epid, name, address, date, wage);
					empList.add(emp);
		
				}
				
			} else {
				throw new ProjectException("No Project found with PID ID : " + pid);
			}
		
		} catch (SQLException e) {
			System.out.println(e.getMessage());;
		}
		
		if(empList.size() == 0) {
			throw new EmployeeException("Exception : No Employee Found in the given PID : " + pid);
		}
			
		return empList;
	}
	
}
