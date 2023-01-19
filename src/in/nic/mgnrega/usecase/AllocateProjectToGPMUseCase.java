package in.nic.mgnrega.usecase;

import java.util.InputMismatchException;
import java.util.Scanner;

import in.nic.mgnrega.colors.ConsoleColors;
import in.nic.mgnrega.dao.BDOImpl;
import in.nic.mgnrega.dao.BDOInterface;
import in.nic.mgnrega.exception.GPMException;
import in.nic.mgnrega.exception.ProjectException;

public class AllocateProjectToGPMUseCase {
	
	public static void allocateProjectToGPM() {
		
		Scanner sc = new Scanner(System.in);
		
		BDOInterface bdoIntr = new BDOImpl();
		
		try {
			
			System.out.print("Enter the Project ID: ");
			int pid = sc.nextInt();
			System.out.print("Enter GPM ID: ");
			int gpid = sc.nextInt();
			
			try {
				String result = bdoIntr.allocateProjectToGPM(pid, gpid);
				System.out.println(result);
			} catch (ProjectException | GPMException e) {
				System.out.println(e.getMessage());
			}
			
		} catch (InputMismatchException e) {
			System.out.println(ConsoleColors.RED_BACKGROUND + ConsoleColors.WHITE_BOLD_BRIGHT 
					+ "Exception : Invalid Input Data Type" + ConsoleColors.RESET);
		}
		
		

		

		
	}
}
