package in.nic.mgnrega.dao;

import java.util.List;

import in.nic.mgnrega.exception.GPMException;
import in.nic.mgnrega.exception.ProjectException;
import in.nic.mgnrega.model.GPMember;
import in.nic.mgnrega.model.Project;

public interface BDOInterface {
	
	// static and final by default
	public String username = "bdo";
	public String password = "123";

	// 1. Login into their account
	public boolean loginBdo(String username, String password);
	
	// 2. Create a project
	public String createProject(Project p) throws ProjectException;
	
	// 3. View List Of Project
	public List<Project> viewAllProject() throws ProjectException;
	
	// 4. Create a new Gram Panchayat Member (GPM)
	public String createGPM(GPMember gpm) throws GPMException;
	
	// 5. View all the GPM
	public List<GPMember> viewAllGPM() throws GPMException;
	
	// 6. Allocate Project to GPM
	public String allocateProjectToGPM(int pid, int gpid) throws ProjectException, GPMException;
}
