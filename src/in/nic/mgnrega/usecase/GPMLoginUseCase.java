package in.nic.mgnrega.usecase;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

import in.nic.mgnrega.colors.ConsoleColors;
import in.nic.mgnrega.dao.GPMImpl;
import in.nic.mgnrega.dao.GPMInterface;
import in.nic.mgnrega.exception.GPMException;

public class GPMLoginUseCase {
	
	
	public static boolean isValidEmail(String email) 
    { 
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                            "[a-zA-Z0-9_+&*-]+)*@" + 
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                            "A-Z]{2,7}$"; 
                              
        Pattern pat = Pattern.compile(emailRegex); 
        if (email == null) 
            return false; 
        return pat.matcher(email).matches(); 
    } 
	
	
	public static boolean gpmLogin() {
		
		boolean response = false;
		
		Scanner sc = new Scanner(System.in);
		
		GPMInterface gpmIntr = new GPMImpl();
		
		
		try {
			
			System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT  + "\nEnter your crendentials" + ConsoleColors.RESET);
			System.out.print(ConsoleColors.WHITE_BOLD_BRIGHT  + "Enter Your Email Address: " + ConsoleColors.RESET);
			boolean flag = false;
			int chance = 3;

			String email = sc.nextLine();
			
			while(!flag && chance-- != 0) {	
				flag = isValidEmail(email);
				
				if (flag) {
					break;
				}
				if (chance == 0) {
					return response;
				}
					
				System.out.println(ConsoleColors.RED_BACKGROUND + ConsoleColors.WHITE_BOLD_BRIGHT + "\nEnter a valid email" + ConsoleColors.RESET);

				System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT  + "\nEnter Email Address: " + ConsoleColors.RESET);
				email = sc.nextLine();		
			}
			
			System.out.print(ConsoleColors.WHITE_BOLD_BRIGHT  + "Enter Your Password: " + ConsoleColors.RESET);
			String password = sc.nextLine();
			
			
			try {
				response = gpmIntr.loginGPM(email, password);
			} catch (GPMException e) {
				System.out.println(e.getMessage());
			}
			
			
		} catch (InputMismatchException e) {
			System.out.println(ConsoleColors.RED_BACKGROUND + ConsoleColors.WHITE_BOLD_BRIGHT 
					+ "\nException : Invalid Input Data Type" + ConsoleColors.RESET);
		}
		
		return response;
		
	}

}
