package in.nic.mgnrega.usecase;

import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import in.nic.mgnrega.colors.ConsoleColors;
import in.nic.mgnrega.dao.GPMImpl;
import in.nic.mgnrega.dao.GPMInterface;
import in.nic.mgnrega.exception.EmployeeException;
import in.nic.mgnrega.model.Employee;

public class CreateEmployeeUseCase {

	public static void createEmployee() {
		
		Scanner sc = new Scanner(System.in);
		
		GPMInterface gpmIntr = new GPMImpl();
		
		try {
			
			System.out.print("Enter Employee Name: ");
			String ename = sc.nextLine();
			System.out.print("Enter Employee Address: ");
			String address = sc.nextLine();
			
			Employee emp = new Employee();
			emp.setEgpid(GPMImpl.loggedInGPMId);
			emp.setEname(ename);
			emp.setAddress(address);
			
			System.out.print("Enter Employee Joined Date: ");
			String joined_date = sc.nextLine();
			
			Date date = Date.valueOf(joined_date);
			emp.setDate_joined(date);
			
		    System.out.print("Enter Employee's Wage/Day: ");
		    int wage = sc.nextInt();
		    
			emp.setWage(wage);
		    
			
			try {
				String response = gpmIntr.createEmployee(emp);
				System.out.println("\n" + response);
			} catch (EmployeeException e) {
				System.out.println(e.getMessage());
			}
			
		} catch (InputMismatchException | IllegalArgumentException e) {
			System.out.println(ConsoleColors.RED_BACKGROUND + ConsoleColors.WHITE_BOLD_BRIGHT 
					+ "\nException : Invalid Input Data Type" + ConsoleColors.RESET);
		}
		

	}
}
