package allcode;
import java.util.Vector;


public class SiteManager {

	ForumsManagement _fm;
	Vector<UserConection>  _connections;
	/**
	 * constructors- with or without super admin
	 * 
	 */
	public SiteManager(String superAdminName, String passward, String email) {
		_fm=new ForumsManagement();
		_fm.setSuperAdmin(superAdminName,passward, email);
		_connections=new Vector<UserConection>();
	}
	public SiteManager() {
		_fm=new ForumsManagement();
		_connections=new Vector<UserConection>();
	}
	public boolean setSuperAdmin(String name,String pass,String email){
		if(name==null || pass==null || email==null){
			return false;
		}
		_fm.setSuperAdmin(name, pass, email);
		return true;
	}
	
	/**
	 * 
	 * functions of siteManager
	 */
	public UserConection openNewConnection(){
		UserConection uc=new UserConection(_fm);
		_connections.add(uc);
		return uc;
	}
	
	public boolean closeConnection(int id){
		for(UserConection c : _connections){
			if(c.getID()==id){
				_connections.remove(c);
				return true;
			}
		}
		return false;
	}

}
