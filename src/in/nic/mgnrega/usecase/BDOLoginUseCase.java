package in.nic.mgnrega.usecase;

import java.util.Scanner;

import in.nic.mgnrega.dao.BDOImpl;
import in.nic.mgnrega.dao.BDOInterface;

public class BDOLoginUseCase {

	public static boolean login() {
		
		boolean response = false;
		
		Scanner sc = new Scanner(System.in);
		
		BDOInterface bdoIntr = new BDOImpl();
		
		System.out.println("\nEnter your credentials");
		System.out.print("Enter Username: ");
		String username = sc.next();
		System.out.print("Enter password: ");
		String password = sc.next();
		
		// sc.close();
		
		
		response = bdoIntr.loginBdo(username, password);
		
		
		return response;
	}
}
