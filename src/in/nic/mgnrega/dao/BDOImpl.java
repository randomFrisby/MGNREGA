package in.nic.mgnrega.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import in.nic.mgnrega.colors.ConsoleColors;
import in.nic.mgnrega.exception.ProjectException;
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
		
		try(Connection conn = DBUtil.providConnection()) {
			
			PreparedStatement ps = conn.prepareStatement("insert into project (pname, budget, duration) "
					+ "values (?, ?, ?)");
			ps.setString(1, p.getPname());
			ps.setInt(2, p.getBudget());
			ps.setInt(3,  p.getDuration());
			
			int x= ps.executeUpdate();
			if (x > 0) {
				response = "\n" + ConsoleColors.GREEN_BACKGROUND + "Project created successfully";
			} else {
				throw new ProjectException("Error while creating a new project. Try again");
			}
			
		} catch (SQLException e) {
			throw new ProjectException(e.getMessage());
		}
		
		
		
		return response;
	}

}
