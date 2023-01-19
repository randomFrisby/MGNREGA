package in.nic.mgnrega.main;

import java.util.Scanner;

import in.nic.mgnrega.colors.ConsoleColors;

public class Main {
 
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		A: while (true) {	
			System.out.println("\n" + ConsoleColors.BLACK_BOLD_BRIGHT + ConsoleColors.BLACK_ITALIC + ConsoleColors.CYAN_BACKGROUND + "-=-=-=- MGNREGA 2005 -=-=-=-"
					+ ConsoleColors.RESET
					+ ConsoleColors.WHITE_BOLD_BRIGHT
					+ "\n1. Login as Block Development Officer(BDO)"
					+ "\n2. Login as Gram Panchayat Member(GPM)"
					+ "\n3. Info"
					+ "\n0. Exit" +  ConsoleColors.RESET);
			
			System.out.print("\n" + ConsoleColors.WHITE_BOLD_BRIGHT +  "Choose any option: " +  ConsoleColors.RESET);
			String choice = sc.nextLine().trim();
				
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
					
				case "3":
					// Information about MGNREGA
					Info.info();
					break;
				case "0": 
					System.out.print(ConsoleColors.WHITE_BOLD_BRIGHT +  "\nAre you sure you want to exit? (y/n): "+ ConsoleColors.RESET);
					String option = sc.next();
					if (option.equalsIgnoreCase("y")) {
						break A;
					} else {
						break;
					}
				default:
					System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT +  "\nChoose between 0-3" + ConsoleColors.RESET);
					break;
			}
		}
		
		System.out.println("\n" + ConsoleColors.BLACK_BOLD_BRIGHT + ConsoleColors.BLACK_ITALIC + ConsoleColors.CYAN_BACKGROUND + "Thank you!" + ConsoleColors.RESET);
		
		sc.close();
	}
}
