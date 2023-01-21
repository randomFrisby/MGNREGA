package in.nic.mgnrega.usecase;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import in.nic.mgnrega.colors.ConsoleColors;
import in.nic.mgnrega.dao.BDOImpl;
import in.nic.mgnrega.dao.BDOInterface;
import in.nic.mgnrega.exception.EmployeeException;
import in.nic.mgnrega.exception.ProjectException;
import in.nic.mgnrega.model.Employee;

public class EmployeeOnAProjectUseCase {

	public static void employeeOnAProject() {
		
		Scanner sc = new Scanner(System.in);
		
		BDOInterface bdoIntr = new BDOImpl();
		
		try {
			
			System.out.print("Enter Project ID to see the list of all employee in that project : ");
			int pid = sc.nextInt();
			
			List<Employee> emp =  bdoIntr.employeeOnAProject(pid);
			
			System.out.println("--" + ConsoleColors.PURPLE_BACKGROUND+ConsoleColors.YELLOW_BOLD_BRIGHT +"Table of all the Employee Present in the Project ID : " + pid + ConsoleColors.RESET+ "----");
			
			System.out.println("-----------------------------------------------");
			
			System.out.printf("%17s %10.5s %19s",ConsoleColors.WHITE_BOLD_BRIGHT + "EID","NAME","WAGE/DAY" + ConsoleColors.RESET);
			System.out.println();
			System.out.print("-----------------------------------------------");
			
			System.out.println();
			
			for(Employee e : emp) {
				
				System.out.format("%9.2s %13s %10s",e.getEid(),e.getEname(), e.getWage());
				System.out.println();
			}
			
			System.out.println("-----------------------------------------------");
			
			
			
			
		} catch (ProjectException | EmployeeException e) {
			System.out.println(ConsoleColors.RED_BACKGROUND + ConsoleColors.WHITE_BOLD_BRIGHT + e.getMessage() + ConsoleColors.RESET);
		} catch (InputMismatchException e) {
			System.out.println(ConsoleColors.RED_BACKGROUND + ConsoleColors.WHITE_BOLD_BRIGHT 
					+ "Exception : Invalid Input Data Type" + ConsoleColors.RESET);
		}
			
	}
}
