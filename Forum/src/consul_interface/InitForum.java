package consul_interface;

import java.util.Scanner;

import allcode.ForumsManagement;

public class InitForum {
	
	public UserInterface init(){
		try{
			Scanner sc = new Scanner(System.in);
			System.out.println("enter super administrator ditails:");
			System.out.print("enter name: ");
			String superAdminName=sc.next();
			System.out.print("enter passward: ");
			String passward=sc.next();
			System.out.print("enter email: ");
			String email= sc.next();
			
			ForumsManagement fs= new ForumsManagement();
			fs.setSuperAdmin(superAdminName, passward, email);
			UserInterface GUI=new UserInterface(fs);
			
			return GUI;
			
		}
		catch(Exception e){
			return null;
		}
	}
}
