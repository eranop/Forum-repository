package GUI;

public class FORGOTTENPASSWORDCONSTRAINTS{
	
	public FORGOTTENPASSWORDCONSTRAINTS(){};
		
	public static boolean isValid(String user){
		boolean valid = false;
		if(user.length()>0){
			valid=true;
		}
		return valid;
	}
	

}
