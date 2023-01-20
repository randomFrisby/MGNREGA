package in.nic.mgnrega.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.nic.mgnrega.colors.ConsoleColors;
import in.nic.mgnrega.exception.EmployeeException;
import in.nic.mgnrega.exception.GPMException;
import in.nic.mgnrega.model.Employee;
import in.nic.mgnrega.util.DBUtil;

public class GPMImpl implements GPMInterface {
	
	public static int loggedInGPMId;

	@Override
	public boolean loginGPM(String email, String password) throws GPMException {

		boolean response = false;
		
		try(Connection conn = DBUtil.providConnection()){
			
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
		
		try(Connection conn= DBUtil.providConnection()) {
			
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

	
	
	
}
