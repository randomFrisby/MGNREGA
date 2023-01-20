package in.nic.mgnrega.dao;

import java.util.List;

import in.nic.mgnrega.exception.EmployeeException;
import in.nic.mgnrega.exception.GPMException;
import in.nic.mgnrega.exception.ProjectException;
import in.nic.mgnrega.model.Employee;
import in.nic.mgnrega.model.Project;

public interface GPMInterface {

	// 1. Login into their account
	public boolean loginGPM(String email, String password) throws GPMException;
	
	// 2. Create Employee
	public String createEmployee(Employee emp) throws EmployeeException;
	
	// 3. View all Employee under Logged In GPM
	public List<Employee> viewAllEmployee() throws EmployeeException;
	
	// 4. View All Project of Logged In GPM
	public List<Project> viewAllProject() throws ProjectException;
	
	// 5. Assign Employee To A Project
	public String assingnEmployeeToProject(int eid, int pid) throws EmployeeException, GPMException, ProjectException;
}
