package project_tests.Bridge;
import org.hamcrest.core.IsInstanceOf;

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
		UserConnection uc=_sm.openNewConnection();
		if(uc==null){
			System.out.println("openning connection failed");
			return -1;
		}
		//_currentConnection=uc.getID();
		System.out.println("openning connection "+ uc.getID());
		return uc.getID();
	}

	@Override
	public int openSuperAdminConnection() {
		SuperAdminConnection uc=_sm.openSuperAdminConnection();
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
		SiteConnection uc=_sm.getConnectionByID(connectionID);
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
		SiteConnection uc=_sm.getConnectionByID(_currentConnection);
		if(uc==null || !( uc instanceof UserConnection))
			return false;
		report r=((UserConnection) uc).login(user, pass);
		if(r.equals(report.OK))
			return true;
		return false;
	}

	@Override
	public boolean logout() {
		SiteConnection uc=_sm.getConnectionByID(_currentConnection);
		if(uc==null || !( uc instanceof UserConnection))
			return false;
		((UserConnection)uc).logout();
		return false;
	}

	@Override
	public boolean enterForum(String forum) {
		SiteConnection uc=_sm.getConnectionByID(_currentConnection);
		if(uc==null)
			return false;
		uc.enterForum(forum);
		return true;
	}

	@Override
	public boolean exitForum() {
		SiteConnection uc=_sm.getConnectionByID(_currentConnection);
		if(uc==null)
			return false;
		report r= uc.exitForum();
		if(r.equals(report.OK))
			return true;
		return false;
	}

	@Override
	public boolean enterSubforum(String subforumName) {
		SiteConnection uc=_sm.getConnectionByID(_currentConnection);
		if(uc==null)
			return false;
		report r=uc.enterSubforum(subforumName);
		if(r.equals(report.OK))
			return true;
		return false;
	}

	@Override
	public boolean exitSubforum() {
		SiteConnection uc=_sm.getConnectionByID(_currentConnection);
		if(uc==null)
			return false;
		report r=uc.exitSubforum();
		if(r.equals(report.OK))
			return true;
		return false;
	}

	@Override
	public boolean enterPost(int postID) {
		SiteConnection uc=_sm.getConnectionByID(_currentConnection);
		if(uc==null)
			return false;
		report r=uc.enterPost(postID);
		if(r.equals(report.OK))
			return true;
		return false;
	}

	@Override
	public boolean exitPost() {
		SiteConnection uc=_sm.getConnectionByID(_currentConnection);
		if(uc==null)
			return false;
		report r=uc.exitPost();
		if(r.equals(report.OK))
			return true;
		return false;
	}


	@Override
	public boolean addForum(String name, String description) {
		SiteConnection uc=_sm.getConnectionByID(_currentConnection);
		if(uc==null || !( uc instanceof SuperAdminConnection))
			return false;
		report r=((SuperAdminConnection)uc).addNewForum(name, description);
		if(r.equals(report.OK))
			return true;
		return false;
	}

	@Override
	public boolean deleteForum(String forumName) {
		SiteConnection sc=_sm.getConnectionByID(_currentConnection);
		if(sc==null || !(sc instanceof SuperAdminConnection))
			return false;
		report r=((SuperAdminConnection) sc).deleteForum(forumName);
		if(r.equals(report.OK))
			return true;
		return false;
	}

	@Override
	public boolean addSubforumToForum(String subForumName,
			String description, String adminName) {
		SiteConnection uc=_sm.getConnectionByID(_currentConnection);
		if(uc==null || !( uc instanceof UserConnection))
			return false;
		report r=((UserConnection)uc).createSubforum(subForumName, description);
		if(r.equals(report.OK))
			return true;
		return false;
	}

	@Override
	public boolean deleteSubForum(String subForumName) {
		SiteConnection uc=_sm.getConnectionByID(_currentConnection);
		if(uc==null || !( uc instanceof UserConnection))
			return false;
		report r=((UserConnection)uc).deleteSubForum(subForumName);
		if(r.equals(report.OK))
			return true;
		return false;
	}

	@Override
	public boolean registerToForum(String userName, String password,
			String email) {
		SiteConnection uc=_sm.getConnectionByID(_currentConnection);
		if(uc==null || !( uc instanceof UserConnection))
			return false;
		report r=((UserConnection)uc).registerToForum(userName, password, email);
		if(r.equals(report.OK))
			return true;
		return false;
	}

	@Override
	public boolean setSuperAdminToSite(String superadminName,
			String superadminPass, String email) {
		SiteConnection uc=_sm.getConnectionByID(_currentConnection);
		if(uc==null || !( uc instanceof SuperAdminConnection))
			return false;
		report r=((SuperAdminConnection)uc).setSuperAdmin(superadminName, superadminPass, email);
		if(r.equals(report.OK))
			return true;
		return false;
	}

	@Override
	public boolean addAdministratorToForum(String name) {
		SiteConnection uc=_sm.getConnectionByID(_currentConnection);
		if(uc==null || !( uc instanceof SuperAdminConnection))
			return false;
		report r=((SuperAdminConnection)uc).addAdminToForum(name);
		if(r.equals(report.OK))
			return true;
		return false;
	}

	@Override
	public boolean addModeratorTosubforum(String name) {
		SiteConnection uc=_sm.getConnectionByID(_currentConnection);
		if(uc==null || !( uc instanceof UserConnection))
			return false;
		report r=((UserConnection)uc).addModerator(name);
		if(r.equals(report.OK))
			return true;
		return false;
	}

	@Override
	public int writePostInSubForum(String title, String content) {
		SiteConnection uc=_sm.getConnectionByID(_currentConnection);
		if(uc==null || !( uc instanceof UserConnection))
			return -1;
		report r=((UserConnection)uc).writePost(title, content);
		if(r.equals(report.OK))
			return true;
		return false;
	}

	@Override
	public int writeResponsePostInSubForum(String title,
			String content) {
		SiteConnection uc=_sm.getConnectionByID(_currentConnection);
		if(uc==null || !( uc instanceof UserConnection))
			return false;
		report r=((UserConnection)uc).writeResponsePost(title, content);
		if(r.equals(report.OK))
			return true;
		return false;
	}

	@Override
	public boolean deletePostInSubForum(int postID) {
		SiteConnection uc=_sm.getConnectionByID(_currentConnection);
		if(uc==null || !( uc instanceof UserConnection))
			return false;
		report r=((UserConnection)uc).deletePost();
		if(r.equals(report.OK))
			return true;
		return false;
		//TODO
	}

	@Override
	public void cleanAllData() {
	}

	@Override
	public int getCurrentConnectionID() {
		return _currentConnection;
	}

	@Override
	public boolean isRegularConnection(int connectionID) {
		SiteConnection sc= _sm.getConnectionByID(connectionID);
		return (sc instanceof UserConnection);
	}

}
