package project_tests.Bridge;

import java.util.Vector;

public class ProxyBridge implements SiteInterface {

	public SiteInterface real;

	public ProxyBridge() {
		this.real=null;
	}


	@Override
	public boolean addForumToSite(int id, String name, String description,
			String adminName, String adminPass, String adminMail) {
		if(this.real!=null)
			return real.addForumToSite(id, name, description, adminName, adminPass, adminMail);

		return false;
	}

	@Override
	public boolean setAdministratorToForum(int id, String superadminName, String pass, 
			String name, String forumName) {
		if(this.real!=null)
			return real.setAdministratorToForum(id, superadminName, pass, name, forumName);
		return false;
	}

	@Override
	public boolean setSuperAdminToSite(int id, String superadminName, String superadminPass, String name,String newPass, String email) {
		if(this.real!=null)
			return real.setSuperAdminToSite(id, superadminName, superadminPass, name, newPass, email);
		return false;
	}

	@Override
	public boolean login(int id, String user, String pass, String forumName) {
		if(this.real!=null)
			return real.login(id, user, pass, forumName);
		return false;
	}

	@Override
	public boolean logout(int id) {
		if(this.real!=null)
			return real.logout(id);
		return false;
	}

	@Override
	public boolean addNewMemberToForum(int id, String forumName, String userName,
			String password, String email) {
		if(this.real!=null)
			return real.addNewMemberToForum(id, forumName, userName, password, email);
		return false;
	}


	@Override
	public boolean sendComplainOnModerator(int id, String user, String moderator,
			String forumName, String subForumName, String content) {
		if(this.real!=null)
			return real.sendComplainOnModerator(id, user, moderator, forumName, subForumName, content);
		return false;
	}

	@Override
	public boolean setTwoMembersFriends(int id, String forumName, String userName1,
			String userName2) {
		if(this.real!=null)
			return real.setTwoMembersFriends(id, forumName, userName1, userName2);	

		return false;
	}

	@Override
	public boolean writePostInSubForum(int id, String forumName, String subForumName,
			String username, String title, String content) {
		if(this.real!=null)
			return real.writePostInSubForum(id, forumName, subForumName, username, title, content);
		return false;
	}

	@Override
	public boolean writeResponsePostInSubForum(int id, String forumName,
			String subForumName, String username, String title, String content,
			int postToResponseID) {
		if(this.real!=null)
			return real.writeResponsePostInSubForum(id, forumName, subForumName, username, title, content, postToResponseID);
		return false;
	}

	@Override
	public boolean deletePostInSubForum(int id, String forumName, String subForumName,
			int postID, String moderator) {
		if(this.real!=null)
			return real.deletePostInSubForum(id, forumName, subForumName, postID, moderator);
		return false;
	}

	@Override
	public boolean loginSuperAdmin(int id, String userName, String password) {
		if(this.real!=null)
			return real.loginSuperAdmin(id, userName, password);
		return false;
	}

	@Override
	public boolean logoutSuperAdmin(int id) {
		if(this.real!=null)
			return real.logoutSuperAdmin(id);
		return false;
	}

	@Override
	public void cleanAllData() {
	}


	
	@Override
	public boolean deleteSubForum(int id, String forumName, String subForumName,
			String adminName) {
		if(this.real!=null)
			return real.deleteSubForum(id, forumName, subForumName, adminName);
		return false;
	}


	@Override
	public boolean addModerator(int id, String forumName, String subForumName,
			String adminName, String moderatorName) {
		if(this.real!=null)
			return real.addModerator(id, forumName, subForumName, adminName, moderatorName);
		return false;
	}


	@Override
	public boolean init(String superAdminName, String passward, String email) {
		if(this.real!=null)
			return real.init(superAdminName, passward, email);
		return false;
	}


	@Override
	public Vector<String> showSubforumsOfForum(String forumName) {
		if(this.real!=null)
			return real.showSubforumsOfForum(forumName);
		return null;
	}


	@Override
	public Vector<String> showPostInSubForum(String forumName,
			String subForumName) {
		if(this.real!=null)
			return real.showPostInSubForum(forumName, subForumName);
		return null;
	}


	@Override
	public boolean createSubForumInForumByAdmin(int id, String forumName,
			String subForumName, String description, String adminName) {
		if(this.real!=null)
			return real.createSubForumInForumByAdmin(id, forumName, subForumName, description, adminName);
		return false;
	}


	@Override
	public int openNewConnection() {
		if(this.real!=null)
			return real.openNewConnection();
		return -1;	
	}
}
