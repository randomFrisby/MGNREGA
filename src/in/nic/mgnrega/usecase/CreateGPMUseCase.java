package in.nic.mgnrega.usecase;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import in.nic.mgnrega.colors.ConsoleColors;
import in.nic.mgnrega.dao.BDOImpl;
import in.nic.mgnrega.dao.BDOInterface;
import in.nic.mgnrega.exception.GPMException;
import in.nic.mgnrega.model.GPMember;

public class CreateGPMUseCase {
	
	
	
	
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
  
	
    public static boolean isValidPassword(String password) {
  
        // Regex to check valid password.
        String regex = "^(?=.*[0-9])"
                       + "(?=.*[a-z])(?=.*[A-Z])"
                       + "(?=.*[@#$%^&+=])"
                       + "(?=\\S+$).{8,20}$";
  
        // Compile the ReGex
        Pattern p = Pattern.compile(regex);
  
        // If the password is empty
        // return false
        if (password == null) {
            return false;
        }
  
        // Pattern class contains matcher() method
        // to find matching between given password
        // and regular expression.
        Matcher m = p.matcher(password);
  
        // Return if the password
        // matched the ReGex
        return m.matches();
    }

	public static void createGPM() {
		Scanner sc = new Scanner(System.in);
		
		BDOInterface bdoIntr = new BDOImpl();
		
		try {
			
			System.out.print(ConsoleColors.WHITE_BOLD_BRIGHT  + "Enter Member Name: " + ConsoleColors.RESET);
			String gname = sc.nextLine();
			System.out.print(ConsoleColors.WHITE_BOLD_BRIGHT  + "Enter Panchayat Name: " + ConsoleColors.RESET);
			String gpanchayat = sc.nextLine();
			System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT  + "Enter Email Address: " + ConsoleColors.RESET);
			System.out.println(ConsoleColors.BOXING +"A valid email address has four parts:\r\n"
					+ "-> Recipient name\r\n"
					+ "-> @ symbol\r\n"
					+ "-> Domain name\r\n"
					+ "-> Top-level domain" + ConsoleColors.RESET);
			boolean flag = false;
			String email = sc.nextLine();
			
			while(!flag) {
				
				flag = isValidEmail(email);
				
				if (flag) {
					break;
				} else {	
					System.out.println(ConsoleColors.RED_BACKGROUND + ConsoleColors.WHITE_BOLD_BRIGHT + "Invalid Email" + ConsoleColors.RESET);
					System.out.println(ConsoleColors.RED_ITALIC+"(Email must contain '@' symbol and ends with '.domainName')"+ ConsoleColors.RESET);

					System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT  + "Enter Email Address: " + ConsoleColors.RESET);
					email = sc.nextLine();
				}
				
			}
			
			System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT  + "Set Password: " + ConsoleColors.RESET);
			System.out.println(ConsoleColors.BOXING +"It must contain at least 8 characters and at most 20 characters.\r\n"
					+ "It must contain at least one digit.\r\n"
					+ "It must contain at least one upper case alphabet.\r\n"
					+ "It must contain at least one lower case alphabet.\r\n"
					+ "It must contain at least one special character which includes !@#$%&*()-+=^.\r\n"
					+ "It shouldn't contain any white space."+ ConsoleColors.RESET);
			flag = false;
			String password = sc.nextLine();
			
			while(!flag) {
				
				flag = isValidPassword(password);
				
				if (flag) {
					break;
				} else {	
					System.out.println(ConsoleColors.RED_BACKGROUND + ConsoleColors.WHITE_BOLD_BRIGHT + "Invalid Password Pattern" + ConsoleColors.RESET);
					System.out.println(ConsoleColors.RED_ITALIC+"(Password must contain 8 characters and should have atleast 1 Upper Case, 1 Small Case, 1 Number and 1 Special Character)"+ ConsoleColors.RESET);

					System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT  + "Set Password: " + ConsoleColors.RESET);
					password = sc.nextLine();
				}
				
			}
			
			GPMember gpm = new GPMember();
			gpm.setGname(gname);
			gpm.setGpanchayat(gpanchayat);
			gpm.setPhone(email);
			gpm.setPassword(password);
			

			
			String result;
			try {
				result = bdoIntr.createGPM(gpm);
				System.out.println(result);
			} catch (GPMException e) {
				System.out.println(ConsoleColors.RED_BOLD_BRIGHT  + e.getMessage() + ConsoleColors.RESET);
			}
			
		} catch (InputMismatchException e) {
			System.out.println(ConsoleColors.RED_BACKGROUND + ConsoleColors.WHITE_BOLD_BRIGHT 
					+ "Exception : Invalid Input Data Type" + ConsoleColors.RESET);
		}
		
	}
}
