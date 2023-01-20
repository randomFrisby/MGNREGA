package in.nic.mgnrega.usecase;

import java.util.List;

import in.nic.mgnrega.colors.ConsoleColors;
import in.nic.mgnrega.dao.GPMImpl;
import in.nic.mgnrega.dao.GPMInterface;
import in.nic.mgnrega.exception.EmployeeException;
import in.nic.mgnrega.model.Employee;

public class ViewAllEmployeeUseCase {

	public static void viewAllEmployee() {
		
		GPMInterface gpmIntr = new GPMImpl();
		
		try {
			List<Employee> employees = gpmIntr.viewAllEmployee();
			
			System.out.println("---------------------" + ConsoleColors.PURPLE_BACKGROUND+ConsoleColors.YELLOW_BOLD_BRIGHT +"Table of all the Employee Present" + ConsoleColors.RESET+ "---------------------------");
			
			System.out.println("---------------------------------------------------------------------------------");
			
			System.out.printf("%7s %7.5s %8.5s %10s %15s %17s %16s",ConsoleColors.WHITE_BOLD_BRIGHT + "EID", "EGPMID","EPID", "NAME", "ADDRESS", "DATE JOINED", "WAGE/DAY" + ConsoleColors.RESET);
			System.out.println();
			System.out.print("---------------------------------------------------------------------------------");
			
			System.out.println();
			for(Employee emp : employees) {
				
				System.out.format("%2s %6s %8.5s %13s %14s %16s %10s", emp.getEid(), emp.getEgpid(), emp.getEpid(), emp.getEname(), emp.getAddress(), emp.getDate_joined(), emp.getWage());
				System.out.println();
			}
			
			System.out.println("---------------------------------------------------------------------------------");
			
			
		} catch (EmployeeException e) {
			System.out.println(ConsoleColors.RED_BACKGROUND + ConsoleColors.WHITE_BOLD_BRIGHT + e.getMessage() + ConsoleColors.RESET);
		}
		
	}
}
