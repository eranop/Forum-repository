package GUI;

public class LOGINCONSTRAINTS {
	public LOGINCONSTRAINTS(){};
	
	public static boolean loginValidation(String name, String pass){
		boolean ans = false;
		if (name.length()>0 && pass.length()>0){
			ans=true;
		}
		return ans;
	}

}
