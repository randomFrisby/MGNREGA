package in.nic.mgnrega.main;

import java.util.Scanner;

import in.nic.mgnrega.colors.ConsoleColors;
import in.nic.mgnrega.dao.GPMImpl;
import in.nic.mgnrega.usecase.AssignEmployeeToProjectUseCase;
import in.nic.mgnrega.usecase.CreateEmployeeUseCase;
import in.nic.mgnrega.usecase.EmployeeDaysAndWageUseCase;
import in.nic.mgnrega.usecase.GPMLoginUseCase;
import in.nic.mgnrega.usecase.ViewAllEmployeeUseCase;
import in.nic.mgnrega.usecase.ViewAllProjectUnderGPMUseCase;

public class GPMMain {

	public static int login() {
		/*
		 * authenticate GPM by taking input, take that input to validate
		 */
		
		boolean response = GPMLoginUseCase.gpmLogin();
		if (response == false) {
			System.out.println("\n" + ConsoleColors.WHITE_BOLD_BRIGHT + ConsoleColors.RED_BACKGROUND +"WRONG CREDENTIALS" + ConsoleColors.RESET);
			return -1;
		}
		
		System.out.println("\n" + ConsoleColors.WHITE_BOLD_BRIGHT  + ConsoleColors.GREEN_BACKGROUND +"LOGIN SUCCESSFUL!" + ConsoleColors.RESET);
		
		main(GPMImpl.loggedInGPMId);
		
		return 0;
	}
	
	public static void main(int gpmId) {
		/* functions of a GPM are listed here */
		
		Scanner sc = new Scanner(System.in);
		
		A: while (true) {
			System.out.println("\n" + ConsoleColors.BLACK_BOLD + ConsoleColors.BLACK_ITALIC + ConsoleColors.ORANGE_BACKGROUND +  "-=-=-=- GPM PORTAL -=-=-=-" 
					+ ConsoleColors.RESET
					+ ConsoleColors.ORANGE
					+ "\nGPM ID: " + gpmId
					+ ConsoleColors.RESET
					+ ConsoleColors.WHITE_BOLD_BRIGHT
					+ "\n1. Create Employee"
					+ "\n2. All The Employees Working In The Gram"
					+ "\n3. Projects Active In the Gram"
					+ "\n4. Assign Employee To A Project"
					+ "\n5. View Total Number Of Days Employee Worked On a Project And Their Wages"
					+ "\n0. Log out"
					+ ConsoleColors.RESET);
			
			System.out.print("\nChoose: ");
			String choice = sc.next();
			
			switch (choice) {
			case "1":
				CreateEmployeeUseCase.createEmployee();
				break;
				
			case "2":
				ViewAllEmployeeUseCase.viewAllEmployee();
				break;
			
			case "3":
				ViewAllProjectUnderGPMUseCase.viewAllProjectUnderGPM();
				break;
				
			case "4":
				AssignEmployeeToProjectUseCase.assignEmployeeToProject();
				break;
			
			case "5":
				EmployeeDaysAndWageUseCase.employeedaysAndWage();
				break;
				
			case "0":
				System.out.println("\n" + ConsoleColors.BLACK_BOLD + ConsoleColors.BLACK_ITALIC + ConsoleColors.ORANGE_BACKGROUND + "LOGGED OUT!" + ConsoleColors.RESET);	
				break A;
				
			default:
				System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Choose between 0-6" + ConsoleColors.RESET);
				break;
			}
		}
		
		System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Going back to Main Section." + ConsoleColors.RESET);

	}
		
		
}
