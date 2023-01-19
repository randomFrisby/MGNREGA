package in.nic.mgnrega.usecase;

import java.util.List;

import in.nic.mgnrega.colors.ConsoleColors;
import in.nic.mgnrega.dao.BDOImpl;
import in.nic.mgnrega.dao.BDOInterface;
import in.nic.mgnrega.exception.GPMException;
import in.nic.mgnrega.model.GPMember;

public class ViewAllGPm {

	public static void viewAllGPM() {
		
		BDOInterface bdoIntr = new BDOImpl();
		
		try {
			List<GPMember> members = bdoIntr.viewAllGPM();
			
			
			
			
			System.out.println("\n------------" + ConsoleColors.PURPLE_BACKGROUND + ConsoleColors.YELLOW_BOLD_BRIGHT +"Table of all Gram Panchayat Member Present" + ConsoleColors.RESET+ "---------------");
			
			System.out.println("---------------------------------------------------------------------");
			
			System.out.printf("%7s %12s %16s %12.5s %19s", ConsoleColors.WHITE_BOLD_BRIGHT + "GPID", "NAME", "PANCHAYAT", "EMAIL", "PASSWORD" + ConsoleColors.RESET);
			System.out.println();
			System.out.print("---------------------------------------------------------------------");
			
			System.out.println();
			
			for(GPMember g : members) {
				System.out.format("%2s %15s %14s %15s %14s", g.getGpid(), g.getGname(), g.getGpanchayat(), g.getEmail(), g.getPassword());
				System.out.println();
			}
			
			System.out.println("---------------------------------------------------------------------");
			
			
			
			
		} catch (GPMException e) {
			System.out.println(e.getMessage());
		}
		
		
	}
}
