package in.nic.mgnrega.main;

import java.util.Scanner;

import in.nic.mgnrega.colors.ConsoleColors;
import in.nic.mgnrega.usecase.BDOLoginUseCase;
import in.nic.mgnrega.usecase.CreateProjectUseCase;

public class BDOMain {
	
	
	public static int login() {
		
		boolean response = BDOLoginUseCase.login();
		if (response == false) {
			System.out.println("\n" + ConsoleColors.RED_BACKGROUND +"Wrong Credentials" + ConsoleColors.RESET);
			return -1;
		}
		
		System.out.println("\n" + ConsoleColors.GREEN_BACKGROUND +"Login Successful!" + ConsoleColors.RESET);
		
		main();
		
		return 0;
	}
	
	public static void main() {
		
		Scanner sc = new Scanner(System.in);
		
		A: while (true) {
			System.out.println("\n" + ConsoleColors.BLACK_ITALIC + ConsoleColors.BANANA_YELLOW_BACKGROUND +  "-=-=-=-BDO Portal-=-=-=-" + ConsoleColors.RESET
					+ "\n1. Create Project"
					+ "\n2. View List Of Project"
					+ "\n3. Create GPM"
					+ "\n4. View all GPM"
					+ "\n5. Allocate Project to GPM"
					+ "\n6. See List of Employee working on that Project and thier wages"
					+ "\n0. Log out");
			
			System.out.print("\nChoose: ");
			String choice = sc.next();
			
			switch (choice) {
			case "1":
				CreateProjectUseCase.createProject();
				break;
			case "0":
				break A;
				
			default:
				System.out.println("Choose between 0-6");
				break;
			}
		}
		
		System.out.println("Going back to Main Section.");

	}
}
