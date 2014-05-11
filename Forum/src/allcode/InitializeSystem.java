package allcode;

public class InitializeSystem {

	
	
	public static SiteManager init(String superAdminName, String password, String email){

		
		
		DataBaseInit.initialize();
		


		SiteManager fs= new SiteManager(superAdminName, password, email);


		return fs;
	}	
}

