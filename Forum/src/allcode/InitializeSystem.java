package allcode;

public class InitializeSystem {

	public static SiteManager init(String superAdminName, String password, String email){

		SiteManager sm= new SiteManager(superAdminName, password, email);
		
		return sm;
	}	
}

