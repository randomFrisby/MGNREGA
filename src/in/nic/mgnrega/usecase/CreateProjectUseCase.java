package in.nic.mgnrega.usecase;

import java.util.InputMismatchException;
import java.util.Scanner;

import in.nic.mgnrega.colors.ConsoleColors;
import in.nic.mgnrega.dao.BDOImpl;
import in.nic.mgnrega.dao.BDOInterface;
import in.nic.mgnrega.exception.ProjectException;
import in.nic.mgnrega.model.Project;

public class CreateProjectUseCase {
	
	public static void createProject() {
		Scanner sc = new Scanner(System.in);
		
		try {

			BDOInterface bdoIntr = new BDOImpl();
			
			System.out.print(ConsoleColors.WHITE_BOLD_BRIGHT  + "Enter Project name: " + ConsoleColors.RESET);
			String pname = sc.nextLine();
			System.out.print(ConsoleColors.WHITE_BOLD_BRIGHT  + "Enter Project Budget(in Lakhs): " + ConsoleColors.RESET);
			int budget = sc.nextInt();
			System.out.print(ConsoleColors.WHITE_BOLD_BRIGHT  + "Enter Project Duration(in Days): " + ConsoleColors.RESET);
			int duration = sc.nextInt();
			
			Project p = new Project();
			p.setPname(pname);
			p.setBudget(budget);
			p.setDuration(duration);
			
			String result;
			try {
				result = bdoIntr.createProject(p);
				System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT  + result + ConsoleColors.RESET);
			} catch (ProjectException e) {
				System.out.println(ConsoleColors.RED_BOLD_BRIGHT  + e.getMessage() + ConsoleColors.RESET);
			}
			
			
		} catch (InputMismatchException e) {
			System.out.println(ConsoleColors.RED_BACKGROUND + ConsoleColors.WHITE_BOLD_BRIGHT 
					+ "Exception : Invalid Input Data Type" + ConsoleColors.RESET);
		}
		
	}

}
