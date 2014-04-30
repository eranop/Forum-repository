
public class InitializeSystem {

	
	
	public ForumSystem init(String superAdminName, String password, String email){

		ForumSystem fs= new ForumSystem();
		fs.setSuperAdmin(superAdminName, password, email);

		return fs;
	}	
}

