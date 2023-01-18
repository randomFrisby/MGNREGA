package in.nic.mgnrega.main;

import java.util.Scanner;

import in.nic.mgnrega.colors.ConsoleColors;

public class Main {
 
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		A: while (true) {	
			System.out.println("\n" + ConsoleColors.BLACK_ITALIC + ConsoleColors.CYAN_BACKGROUND + "-=-=-=- MGNREGA 2005 -=-=-=-"
					+ ConsoleColors.RESET
					+ ConsoleColors.WHITE_BOLD_BRIGHT
					+ "\n1. Login as Block Development Officer(BDO)"
					+ "\n2. Login as Gram Panchayat Member(GPM)"
					+ "\n0. Exit" +  ConsoleColors.RESET);
			
			System.out.print("\nChoose any option: ");
			String choice = sc.next();	
				
			switch (choice) {
				case "1":
					// BDO applications
					int chance = 3;
					int x= -1;
					while (chance-- != 0 && x == -1) {
						x= BDOMain.login();
						if (x == -1)
							System.out.println("\n" + chance + " chances remaining");
					}
					break;
					
					
					
					
					
					
				case "2":
					// GPM applications
					break;
				case "0": 
					System.out.print("\nAre you sure you want to exit? (y/n): ");
					String option = sc.next();
					if (option.equalsIgnoreCase("y")) {
						break A;
					} else {
						break;
					}
				default:
					System.out.println("\nChoose between 0-2");
					break;
			}
		}
		
		System.out.println("\nThank you!");
		
		sc.close();
	}
}
