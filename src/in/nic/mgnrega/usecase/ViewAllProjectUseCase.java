package in.nic.mgnrega.usecase;

import java.util.List;

import in.nic.mgnrega.colors.ConsoleColors;
import in.nic.mgnrega.dao.BDOImpl;
import in.nic.mgnrega.dao.BDOInterface;
import in.nic.mgnrega.exception.ProjectException;
import in.nic.mgnrega.model.Project;

public class ViewAllProjectUseCase {

	public static void viewAllProject() {
		
		BDOInterface bdoIntr = new BDOImpl();
		
		try {
			List<Project> projects = bdoIntr.viewAllProject();
			
			System.out.println("---------------" + ConsoleColors.BROWN_BACKGROUND + ConsoleColors.YELLOW_BOLD_BRIGHT 
					+ "Table of all the Projects Present" 
					+ ConsoleColors.RESET+ "---------------------");
			
			System.out.println("---------------------------------------------------------------------");
			
			System.out.printf("%7s %7.5s %16s %15s %17s",ConsoleColors.WHITE_BOLD 
					+ "PID", "GPMID", "NAME", "BUDGET", "DURATION" 
					+ ConsoleColors.RESET);
			System.out.println();
			System.out.print("---------------------------------------------------------------------");
			
			System.out.println();
			for(Project p: projects) {
				
				System.out.format("%2s %6s %22s %12s %11s", p.getPid(), p.getPgpid(), p.getPname(), 
						p.getBudget() + " lakhs", p.getDuration() + " days");
				System.out.println();
			}
			
			System.out.println("---------------------------------------------------------------------");
			
			
			
		} catch(ProjectException e) {
			System.out.println(e.getMessage());
		}
	}
}
