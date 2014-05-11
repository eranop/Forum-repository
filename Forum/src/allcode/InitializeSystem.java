package allcode;

public class InitializeSystem {

	
	
	public ForumsManagement init(String superAdminName, String password, String email){

		
		ForumsManagement fs= new ForumsManagement();
		DataBaseInit.initialize();
		fs.setSuperAdmin(superAdminName, password, email);

		return fs;
	}	
}

