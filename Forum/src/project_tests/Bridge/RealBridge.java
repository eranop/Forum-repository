package project_tests.Bridge;
import allcode.*;
public class RealBridge implements SiteInterface {
	private SiteManager _sm;
	private int _currentConnection;
	@Override
	public boolean init(String superAdminName, String password, String email) {
		_sm=InitializeSystem.init(superAdminName, password, email);
		_currentConnection=-1;
		return true;
	}

	@Override
	public int openNewConnection() {
		UserConection uc=_sm.openNewConnection();
		if(uc==null){
			System.out.println("openning connection failed");
			return -1;
		}
		//_currentConnection=uc.getID();
		System.out.println("openning connection "+ uc.getID());
		return uc.getID();
	}


	@Override
	public boolean switchConnection(int connectionID) {
		UserConection uc=_sm.getConnectionByID(connectionID);
		if(uc==null)
			return false;
		_currentConnection=connectionID;
		return true;
	}

	@Override
	public boolean closeConnection(int connectionID) {
		if(_sm.closeConnection(connectionID)){  
			if(_currentConnection==connectionID){
				_currentConnection=-1;
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean closeCurrentConnection() {
		_currentConnection=-1;
		return _sm.closeConnection(_currentConnection);
	}


	@Override
	public boolean login(String user, String pass) {
		UserConection uc=_sm.getConnectionByID(_currentConnection);
		if(uc==null)
			return false;
		report r=uc.login(user, pass);
		if(r.equals(report.OK))
			return true;
		return false;
	}

	@Override
	public boolean logout() {
		UserConection uc=_sm.getConnectionByID(_currentConnection);
		if(uc==null)
			return false;
		uc.logout();
		return false;
	}

	@Override
	public boolean enterForum(String forum) {
		UserConection uc=_sm.getConnectionByID(_currentConnection);
		if(uc==null)
			return false;
		uc.enterForum(forum);
		return false;
	}

	@Override
	public boolean exitForum() {
		UserConection uc=_sm.getConnectionByID(_currentConnection);
		if(uc==null)
			return false;
		report r= uc.exitForum();
		if(r.equals(report.OK))
			return true;
		return false;
	}

	@Override
	public boolean enterSubforum(String subforumName) {
		UserConection uc=_sm.getConnectionByID(_currentConnection);
		if(uc==null)
			return false;
		report r=uc.enterSubforum(subforumName);
		if(r.equals(report.OK))
			return true;
		return false;
	}

	@Override
	public boolean exitSubforum() {
		UserConection uc=_sm.getConnectionByID(_currentConnection);
		if(uc==null)
			return false;
		report r=uc.exitSubforum();
		if(r.equals(report.OK))
			return true;
		return false;
	}

	@Override
	public boolean enterPost(int postID) {
		UserConection uc=_sm.getConnectionByID(_currentConnection);
		if(uc==null)
			return false;
		report r=uc.enterPost(postID);
		if(r.equals(report.OK))
			return true;
		return false;
	}

	@Override
	public boolean exitPost() {
		UserConection uc=_sm.getConnectionByID(_currentConnection);
		if(uc==null)
			return false;
		report r=uc.exitPost();
		if(r.equals(report.OK))
			return true;
		return false;
	}

	@Override
	public boolean loginSuperAdmin(String userName, String password) {
		UserConection uc=_sm.getConnectionByID(_currentConnection);
		if(uc==null)
			return false;
		report r=uc.loginAsSuperAdmin(userName, password);
		if(r.equals(report.OK))
			return true;
		return false;
	}

	@Override
	public boolean logoutSuperAdmin() {
		UserConection uc=_sm.getConnectionByID(_currentConnection);
		if(uc==null)
			return false;
		report r=uc.logoutAsSuperAdmin();
		if(r.equals(report.OK))
			return true;
		return false;
	}

	@Override
	public boolean addForum(String name, String description) {
		UserConection uc=_sm.getConnectionByID(_currentConnection);
		if(uc==null)
			return false;
		report r=uc.createForum(name, description);
		if(r.equals(report.OK))
			return true;
		return false;
	}

	@Override
	public boolean deleteForum(String forumName) {
		UserConection uc=_sm.getConnectionByID(_currentConnection);
		if(uc==null)
			return false;
		report r=uc.deleteForum(forumName);
		if(r.equals(report.OK))
			return true;
		return false;
	}

	@Override
	public boolean addSubforumToForum(String subForumName,
			String description, String adminName) {
		UserConection uc=_sm.getConnectionByID(_currentConnection);
		if(uc==null)
			return false;
		report r=uc.createSubforum(subForumName, description);
		if(r.equals(report.OK))
			return true;
		return false;
	}

	@Override
	public boolean deleteSubForum(String subForumName) {
		UserConection uc=_sm.getConnectionByID(_currentConnection);
		if(uc==null)
			return false;
		report r=uc.deleteSubForum(subForumName);
		if(r.equals(report.OK))
			return true;
		return false;
	}

	@Override
	public boolean registerToForum(String userName, String password,
			String email) {
		UserConection uc=_sm.getConnectionByID(_currentConnection);
		if(uc==null)
			return false;
		report r=uc.registerToForum(userName, password, email);
		if(r.equals(report.OK))
			return true;
		return false;
	}

	@Override
	public boolean setSuperAdminToSite(String superadminName,
			String superadminPass, String email) {
		UserConection uc=_sm.getConnectionByID(_currentConnection);
		if(uc==null)
			return false;
		report r=uc.setSuperAdmin(superadminName, superadminPass, email);
		if(r.equals(report.OK))
			return true;
		return false;
	}

	@Override
	public boolean addAdministratorToForum(String name) {
		UserConection uc=_sm.getConnectionByID(_currentConnection);
		if(uc==null)
			return false;
		report r=uc.addAdminToForum(name);
		if(r.equals(report.OK))
			return true;
		return false;
	}

	@Override
	public boolean addModeratorTosubforum(String name) {
		UserConection uc=_sm.getConnectionByID(_currentConnection);
		if(uc==null)
			return false;
		report r=uc.addModerator(name);
		if(r.equals(report.OK))
			return true;
		return false;
	}

	@Override
	public boolean writePostInSubForum(String title, String content) {
		UserConection uc=_sm.getConnectionByID(_currentConnection);
		if(uc==null)
			return false;
		report r=uc.writePost(title, content);
		if(r.equals(report.OK))
			return true;
		return false;
	}

	@Override
	public boolean writeResponsePostInSubForum(String title,
			String content) {
		UserConection uc=_sm.getConnectionByID(_currentConnection);
		if(uc==null)
			return false;
		report r=uc.writeResponsePost(title, content);
		if(r.equals(report.OK))
			return true;
		return false;
	}

	@Override
	public boolean deletePostInSubForum(int postID) {
		UserConection uc=_sm.getConnectionByID(_currentConnection);
		if(uc==null)
			return false;
		report r=uc.deletePost();
		if(r.equals(report.OK))
			return true;
		return false;
		//TODO
	}

	@Override
	public void cleanAllData() {
	}


}
