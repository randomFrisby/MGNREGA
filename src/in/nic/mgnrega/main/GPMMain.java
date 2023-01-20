package in.nic.mgnrega.main;

import java.util.Scanner;

import in.nic.mgnrega.colors.ConsoleColors;
import in.nic.mgnrega.usecase.CreateEmployeeUseCase;
import in.nic.mgnrega.usecase.GPMLoginUseCase;

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
		
		main();
		
		return 0;
	}
	
	public static void main() {
		/* functions of a GPM are listed here */
		
		Scanner sc = new Scanner(System.in);
		
		A: while (true) {
			System.out.println("\n" + ConsoleColors.BLACK_BOLD + ConsoleColors.BLACK_ITALIC + ConsoleColors.ORANGE_BACKGROUND +  "-=-=-=- GPM PORTAL -=-=-=-" 
					+ ConsoleColors.RESET
					+ ConsoleColors.WHITE_BOLD_BRIGHT
					+ "\n1. Create Employee"
					+ "\n2. View Details of Employee"
					+ "\n3. Assign Employee To A Project"
					+ "\n4. View Total Number Of Days Employee Worked On a Project And Their Wages"
					+ "\n0. Log out"
					+ ConsoleColors.RESET);
			
			System.out.print("\nChoose: ");
			String choice = sc.next();
			
			switch (choice) {
			case "1":
				CreateEmployeeUseCase.createEmployee();
				break;
				
			case "2":
				break;
			
			case "3":
				break;
			
			case "4":
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
