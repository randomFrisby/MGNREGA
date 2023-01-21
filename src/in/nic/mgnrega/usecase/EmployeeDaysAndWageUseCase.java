package in.nic.mgnrega.usecase;

import java.util.List;

import in.nic.mgnrega.colors.ConsoleColors;
import in.nic.mgnrega.dao.GPMImpl;
import in.nic.mgnrega.dao.GPMInterface;
import in.nic.mgnrega.exception.EmployeeException;
import in.nic.mgnrega.model.EmployeeWageOperationDTO;

public class EmployeeDaysAndWageUseCase {

	public static void employeedaysAndWage() {
		
		
		GPMInterface gpmIntr = new GPMImpl();
		
		try {
			
			List<EmployeeWageOperationDTO> empWage =  gpmIntr.employeeDaysAndWage();
				
			System.out.println("---------------------------------" + ConsoleColors.PURPLE_BACKGROUND+ConsoleColors.YELLOW_BOLD_BRIGHT +"Table of all the Employee, Wage, days, Total Amount" + ConsoleColors.RESET+ "-------------------------------------");
			
			System.out.println("-------------------------------------------------------------------------------------------------------------------------");
			
			System.out.printf("%7s %10s %11s %17s %22s %17s %13s %21s",ConsoleColors.WHITE_BOLD_BRIGHT + "EID", "ENAME", "PID", "PNAME", "DATE_JOINED", "DAYS WORKED", "WAGE/DAY", "TOTAL_AMOUNT" + ConsoleColors.RESET);
			System.out.println();
			System.out.print("-------------------------------------------------------------------------------------------------------------------------");
			
			System.out.println();
			for(EmployeeWageOperationDTO e : empWage) {
				
				System.out.format("%2s %12s %9s %22s %17s %14s %14s %18s",e.getEid(),e.getName(), e.getProjectID(),e.getProjectName(),e.getDate_joined(),e.getNoOfDays(),e.getWage(),e.getTotalAmount());
				System.out.println();
			}
			
			System.out.println("------------------------------------------------------------------------------------------------------------------------");
			
			
			
			
			
		} catch (EmployeeException e) {
			System.out.println(ConsoleColors.RED_BACKGROUND + ConsoleColors.WHITE_BOLD_BRIGHT 
					+ e.getMessage() + ConsoleColors.RESET);
		}
		
			
	}
}
