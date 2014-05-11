package Bridge;

import java.util.Vector;

public class ProxyBridge implements SiteInterface {

	public SiteInterface real;

	public ProxyBridge() {
		this.real=null;
	}


	@Override
	public boolean addForumToSite(String name, String description,
			String adminName, String adminPass, String adminMail) {
		if(this.real!=null)
			return real.addForumToSite(name, description, adminName, adminPass, adminMail);

		return false;
	}

	@Override
	public boolean setAdministratorToForum(String name, String forumName) {
		if(this.real!=null)
			return real.setAdministratorToForum(name, forumName);
		return false;
	}

	@Override
	public boolean setSuperAdminToSite(String name, String pass, String email) {
		if(this.real!=null)
			return real.setSuperAdminToSite(name, pass, email);
		return false;
	}

	@Override
	public boolean login(String user, String pass, String forumName) {
		if(this.real!=null)
			return real.login(user, pass, forumName);
		return false;
	}

	@Override
	public boolean logout() {
		if(this.real!=null)
			return real.logout();
		return false;
	}

	@Override
	public boolean addNewMemberToForum(String forumName, String userName,
			String password, String email) {
		if(this.real!=null)
			return real.addNewMemberToForum(forumName, userName, password, email);
		return false;
	}


	@Override
	public boolean sendComplainOnModerator(String user, String moderator,
			String forumName, String subForumName, String content) {
		if(this.real!=null)
			return real.sendComplainOnModerator(user, moderator, forumName, subForumName, content);
		return false;
	}

	@Override
	public boolean setTwoMembersFriends(String forumName, String userName1,
			String userName2) {
		if(this.real!=null)
			return real.setTwoMembersFriends(forumName, userName1, userName2);	

		return false;
	}

	@Override
	public boolean writePostInSubForum(String forumName, String subForumName,
			String username, String title, String content) {
		if(this.real!=null)
			return real.writePostInSubForum(forumName, subForumName, username, title, content);
		return false;
	}

	@Override
	public boolean writeResponsePostInSubForum(String forumName,
			String subForumName, String username, String title, String content,
			int postToResponseID) {
		if(this.real!=null)
			return real.writeResponsePostInSubForum(forumName, subForumName, username, title, content, postToResponseID);
		return false;
	}

	@Override
	public boolean deletePostInSubForum(String forumName, String subForumName,
			int postID, String moderator) {
		if(this.real!=null)
			return real.deletePostInSubForum(forumName, subForumName, postID, moderator);
		return false;
	}

	@Override
	public boolean loginSuperAdmin(String userName, String password) {
		if(this.real!=null)
			return real.loginSuperAdmin(userName, password);
		return false;
	}

	@Override
	public boolean logoutSuperAdmin() {
		if(this.real!=null)
			return real.logoutSuperAdmin();
		return false;
	}

	@Override
	public void cleanAllData() {
		// TODO Auto-generated method stub

	}


	@Override
	public boolean exitForum(String forumName) {
		if(this.real!=null)
			return real.exitForum(forumName);
		return false;
	}


	@Override
	public boolean deleteSubForum(String forumName, String subForumName,
			String adminName) {
		if(this.real!=null)
			return real.deleteSubForum(forumName, subForumName, adminName);
		return false;
	}


	@Override
	public boolean addModerator(String forumName, String subForumName,
			String adminName, String moderatorName) {
		if(this.real!=null)
			return real.addModerator(forumName, subForumName, adminName, moderatorName);
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
	public boolean createSubForumInForumByAdmin(String forumName,
			String subForumName, String description, String adminName) {
		if(this.real!=null)
			return real.createSubForumInForumByAdmin(forumName, subForumName, description, adminName);
		return false;
	}


}
