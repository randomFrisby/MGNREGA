package in.nic.mgnrega.dao;

import in.nic.mgnrega.exception.EmployeeException;
import in.nic.mgnrega.exception.GPMException;
import in.nic.mgnrega.model.Employee;

public interface GPMInterface {

	// 1. Login into their account
	public boolean loginGPM(String email, String password) throws GPMException;
	
	// 2. Create Employee
	public String createEmployee(Employee emp) throws EmployeeException;
}
