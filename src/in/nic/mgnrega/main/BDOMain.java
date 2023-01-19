package in.nic.mgnrega.main;

import java.util.Scanner;

import in.nic.mgnrega.colors.ConsoleColors;
import in.nic.mgnrega.exception.ProjectException;
import in.nic.mgnrega.usecase.BDOLoginUseCase;
import in.nic.mgnrega.usecase.CreateGPMUseCase;
import in.nic.mgnrega.usecase.CreateProjectUseCase;
import in.nic.mgnrega.usecase.ViewAllGPm;
import in.nic.mgnrega.usecase.ViewAllProjectUseCase;

public class BDOMain {
	
	
	public static int login() {
		/*
		 * authenticate BDO by taking input, take that input to validate
		 */
		
		boolean response = BDOLoginUseCase.login();
		if (response == false) {
			System.out.println("\n" + ConsoleColors.WHITE_BOLD_BRIGHT + ConsoleColors.RED_BACKGROUND +"WRONG CREDENTIALS" + ConsoleColors.RESET);
			return -1;
		}
		
		System.out.println("\n" + ConsoleColors.WHITE_BOLD_BRIGHT  + ConsoleColors.GREEN_BACKGROUND +"LOGIN SUCCESSFUL!" + ConsoleColors.RESET);
		
		main();
		
		return 0;
	}
	
	public static void main() {
		
		/* functions of BDO are listed here */
		
		Scanner sc = new Scanner(System.in);
		
		A: while (true) {
			System.out.println("\n" + ConsoleColors.BLACK_BOLD + ConsoleColors.BLACK_ITALIC + ConsoleColors.BANANA_YELLOW_BACKGROUND +  "-=-=-=- BDO PORTAL -=-=-=-" 
					+ ConsoleColors.RESET
					+ ConsoleColors.WHITE_BOLD_BRIGHT
					+ "\n1. Create Project"
					+ "\n2. View List Of Project"
					+ "\n3. Create GPM"
					+ "\n4. View all GPM"
					+ "\n5. Allocate Project to GPM"
					+ "\n6. See List of Employee working on that Project and thier wages"
					+ "\n0. Log out"
					+ ConsoleColors.RESET);
			
			System.out.print("\nChoose: ");
			String choice = sc.next();
			
			switch (choice) {
			case "1":
				CreateProjectUseCase.createProject();
				break;
				
			case "2":
				ViewAllProjectUseCase.viewAllProject();
				break;
			
			case "3":
				CreateGPMUseCase.createGPM();
				break;
			
			case "4":
				ViewAllGPm.viewAllGPM();
				break;
				
			case "0":
				System.out.println("\n" + ConsoleColors.BANANA_YELLOW_BACKGROUND + ConsoleColors.BLACK_BOLD + "LOGGED OUT!" + ConsoleColors.RESET);	
				break A;
				
			default:
				System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Choose between 0-6" + ConsoleColors.RESET);
				break;
			}
		}
		
		System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Going back to Main Section." + ConsoleColors.RESET);

	}
}
