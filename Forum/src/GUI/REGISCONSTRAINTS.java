package GUI;

public class REGISCONSTRAINTS {
	public REGISCONSTRAINTS(){}
	
	public static boolean validSubmittionInRegister(String name, String password, String mail, String ans1, String ans2){
		boolean ans=false;
		System.out.println("canc: "+name+password+mail+ans1+ans2);
		System.out.println(name.length());
		if (name.length()>0 && password.length()>0 && mail.length()>0 && ans1.length()>0 && ans2.length()>0){
			ans = true;	
		}
		return ans;	
	}
	
	public static boolean matchPasswords(String pass1, String pass2){
		if (pass1==pass2){
			System.out.println("matched passwords");
		}
		return true;
	}

	
	public static boolean validRegistration(String name, String pass1, 
											String pass2, String mail, String ans1, String ans2){
		boolean ans=false;
		boolean temp1 = validSubmittionInRegister(name, pass1, mail, ans1, ans2);
		boolean temp2 = matchPasswords(pass1, pass2);
		if (temp1 && temp2){
			ans=true;
		}
		return ans;
	}
}