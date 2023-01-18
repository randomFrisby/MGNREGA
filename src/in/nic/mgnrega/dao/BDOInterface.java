package in.nic.mgnrega.dao;

import java.util.List;

import in.nic.mgnrega.exception.ProjectException;
import in.nic.mgnrega.model.Project;

public interface BDOInterface {
	
	// static and final by default
	public String username = "bdo";
	public String password = "123";

	public boolean loginBdo(String username, String password);
	
	public String createProject(Project p) throws ProjectException;
	
	public List<Project> viewAllProject() throws ProjectException;
}
