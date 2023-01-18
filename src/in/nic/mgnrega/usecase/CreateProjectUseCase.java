package in.nic.mgnrega.usecase;

import java.util.Scanner;

import in.nic.mgnrega.dao.BDOImpl;
import in.nic.mgnrega.dao.BDOInterface;
import in.nic.mgnrega.exception.ProjectException;
import in.nic.mgnrega.model.Project;

public class CreateProjectUseCase {
	
	public static void createProject() {
		Scanner sc = new Scanner(System.in);
		
		BDOInterface bdoIntr = new BDOImpl();
		
		System.out.println("Enter Project name: ");
		String pname = sc.nextLine();
		System.out.println("Enter Project Budget(in Lakhs): ");
		int budget = sc.nextInt();
		System.out.println("Enter Project Duration(in Days): ");
		int duration = sc.nextInt();
		
		Project p = new Project();
		p.setPname(pname);;
		p.setBudget(budget);
		p.setDuration(duration);
		
		String result;
		try {
			result = bdoIntr.createProject(p);
			System.out.println(result);
		} catch (ProjectException e) {
			System.out.println(e.getMessage());
		}
		
	}

}
