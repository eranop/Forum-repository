package allcode;

public class InitializeSystem {

	
	
	public static SiteManager init(String superAdminName, String password, String email){

<<<<<<< HEAD
		
		ForumsManagement fs= new ForumsManagement();
		DataBaseInit.initialize();
		fs.setSuperAdmin(superAdminName, password, email);

=======
		SiteManager fs= new SiteManager(superAdminName, password, email);
		
>>>>>>> refs/heads/version2.1
		return fs;
	}	
}

