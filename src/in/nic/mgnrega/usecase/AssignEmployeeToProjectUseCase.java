package in.nic.mgnrega.usecase;

import java.util.InputMismatchException;
import java.util.Scanner;

import in.nic.mgnrega.colors.ConsoleColors;
import in.nic.mgnrega.dao.GPMImpl;
import in.nic.mgnrega.dao.GPMInterface;
import in.nic.mgnrega.exception.EmployeeException;
import in.nic.mgnrega.exception.GPMException;
import in.nic.mgnrega.exception.ProjectException;

public class AssignEmployeeToProjectUseCase {

	public static void assignEmployeeToProject() {
		
		Scanner sc = new Scanner(System.in);
		
		GPMInterface gpmIntr = new GPMImpl();
		
		try {
			
			System.out.print("Enter Employee Id: ");
			int eid = sc.nextInt();
			System.out.print("Enter Project Id: ");
			int pid = sc.nextInt();
			
			try {
				String response = gpmIntr.assingnEmployeeToProject(eid, pid);
				System.out.println(ConsoleColors.GREEN + response + ConsoleColors.RESET);
				
			} catch (EmployeeException | GPMException | ProjectException e) {
				System.out.println(ConsoleColors.RED_BACKGROUND + ConsoleColors.WHITE_BOLD_BRIGHT + e.getMessage() + ConsoleColors.RESET);
			}
			
		} catch (InputMismatchException e) {
			System.out.println(ConsoleColors.RED_BACKGROUND + ConsoleColors.WHITE_BOLD_BRIGHT 
					+ "\nException : Invalid Input Data Type" + ConsoleColors.RESET);
		}
		
	}
}
