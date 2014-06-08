package allcode;

import services.Logger2;

public class InitializeSystem {

	public static SiteManager init(String superAdminName, String password, String email){
		
		DataBaseInit.initialize();

		SiteManager sm= new SiteManager(superAdminName, password, email);
		Logger2.initLogSystem();

		

		Logger2.initLogUser();

		return sm;
	}	
}

