package allcode;
import java.util.HashMap;


public class SiteManager {

	ForumsManagement _fm;
	HashMap<Integer,UserConection>  _connections;
	/**
	 * constructors- with or without super admin
	 * 
	 */
	public SiteManager(String superAdminName, String passward, String email) {
		_fm=new ForumsManagement();
		_fm.setSuperAdmin(superAdminName,passward, email);
		_connections=new HashMap<Integer,UserConection>();
	}
	public SiteManager() {
		_fm=new ForumsManagement();
		_connections=new HashMap<Integer,UserConection>();
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
		_connections.put(uc.getID(), uc);
		return uc;
	}

	public boolean closeConnection(int id){
		UserConection uc=_connections.get(id);
		if(uc==null){
			return false;
		}
		_connections.remove(id);
		return true;
	}

	public UserConection getConnectionByID(int id){
		return _connections.get(id);
	}

}
