package allcode;
import java.util.HashMap;


public class SiteManager {

	ForumsManagement _fm;
	HashMap<Integer,SiteConnection>  _connections;
	/**
	 * constructors- with or without super admin
	 * 
	 */
	public SiteManager(String superAdminName, String passward, String email) {
		_fm=new ForumsManagement(superAdminName,passward, email);
		_connections=new HashMap<Integer,SiteConnection>();
	}
	public SiteManager() {
		_fm=new ForumsManagement();
		_connections=new HashMap<Integer,SiteConnection>();
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
	public UserConnection openNewConnection(){
		UserConnection uc=new UserConnection(_fm);
		_connections.put(uc.getID(), uc);
		return uc;
	}

	public SuperAdminConnection openSuperAdminConnection(){
		SuperAdminConnection uc = new SuperAdminConnection(_fm);
		_connections.put(uc.getID(), uc);
		return uc;
	}

	public boolean closeConnection(int id){
		SiteConnection uc=_connections.get(id);
		if(uc==null){
			return false;
		}
		_connections.remove(id);
		return true;
	}

	public SiteConnection getConnectionByID(int id){
		return _connections.get(id);
	}

}
