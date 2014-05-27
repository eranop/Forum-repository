package GUI;

public class CONNECTION {
	
	public CONNECTION(){}
		
	public static String[] getForums(){
		String[] stub_forumslist={"FORUM 1","FORUM 2","FORUM 3","FORUM 4"};
		return stub_forumslist;
	}
	
	public static String[] getSubsOfForum(String Forum){
		String[] stub_subforumslist={"SUBFORUM 1","SUBFORUM 2","SUBFORUM 3","SUBFORUM 4"};
		return stub_subforumslist;
	}
	
	public static boolean checkUserValidForForum(String uname,String pass,String forumname){
		return true;
	}
	
	public static String getQuestion1ForUser(String username){
		return "question1";
	}
	
	public static String getQuestion2ForUser(String username){
		return "question2";
	}
	
	public static boolean isMemberAtForum(String name, String Forum){
		return true;
	}
	
	public static boolean sendNewPasswordToUser(String name){
		return true;
	};
	
	
	}
	
	
	

