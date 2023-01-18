package in.nic.mgnrega.dao;

import in.nic.mgnrega.exception.ProjectException;
import in.nic.mgnrega.model.Project;

public interface BDOInterface {
	
	// static and final by default
	public String username = "Gaurav";
	public String password = "123";

	public boolean loginBdo(String username, String password);
	
	public String createProject(Project p) throws ProjectException;
}
