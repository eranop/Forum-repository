package allcode;

public class InitializeSystem {

	
	
	public static SiteManager init(String superAdminName, String password, String email){

		SiteManager fs= new SiteManager(superAdminName, password, email);
		
		return fs;
	}	
}

