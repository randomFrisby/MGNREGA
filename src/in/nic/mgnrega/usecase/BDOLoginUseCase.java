package in.nic.mgnrega.usecase;

import java.util.InputMismatchException;
import java.util.Scanner;

import in.nic.mgnrega.colors.ConsoleColors;
import in.nic.mgnrega.dao.BDOImpl;
import in.nic.mgnrega.dao.BDOInterface;

public class BDOLoginUseCase {

	public static boolean login() {
		
		boolean response = false;
		
		Scanner sc = new Scanner(System.in);
		
		BDOInterface bdoIntr = new BDOImpl();
		
		try {
			
			System.out.println("\n" + ConsoleColors.WHITE_BOLD_BRIGHT + "Enter your credentials" + ConsoleColors.RESET);
			System.out.print(ConsoleColors.WHITE_BOLD_BRIGHT + "Enter Username: " + ConsoleColors.RESET);
			String username = sc.nextLine();
			System.out.print(ConsoleColors.WHITE_BOLD_BRIGHT + "Enter password: " + ConsoleColors.RESET);
			String password = sc.nextLine();
			
			// sc.close();
				
			response = bdoIntr.loginBdo(username, password);
			
		} catch (InputMismatchException e) {
			System.out.println(ConsoleColors.RED_BACKGROUND + ConsoleColors.WHITE_BOLD_BRIGHT 
					+ "Exception : Invalid Input Data Type" + ConsoleColors.RESET);
		}
		
		
		return response;
	}
}
