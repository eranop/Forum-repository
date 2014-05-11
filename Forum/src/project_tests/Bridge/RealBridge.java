package project_tests.Bridge;
import java.util.HashMap;
import java.util.Vector;

import allcode.*;
public class RealBridge implements SiteInterface {
	private SiteManager _sm;

	@Override
	public void cleanAllData() {
		// TODO Auto-generated method stub

	}
	@Override
	public boolean init(String superAdminName, String passward, String email) {
		_sm=InitializeSystem.init(superAdminName, passward, email);
		return true;
	}
	@Override
	public int openNewConnection() {
		UserConection uc=_sm.openNewConnection();
		return uc.getID();
	}
	@Override
	public boolean addForumToSite(int id,String name, String description,
			String adminName, String adminPass, String adminMail) {
		UserConection uc=_sm.getConnectionByID(id);
		uc.reset();
		uc.loginAsSuperAdmin(adminName, adminPass);
		report r= uc.createForum(name, description);
		if(r.equals(report.OK))
			return true;
		return false;
	}
	@Override
	public boolean setAdministratorToForum(int id, String name, String forumName) {
		UserConection uc=_sm.getConnectionByID(id);
		uc.reset();
		uc.enterForum(forumName);
		
		
	}
	@Override
	public boolean setSuperAdminToSite(int id, String name, String pass, String email) {
		return fs.setSuperAdmin(name, pass, email);
	}
	@Override
	public boolean login(int id, String user, String pass, String forumName) {
		return fs.login(user, pass, forumName);
	}
	@Override
	public boolean logout(int id) {
		return fs.logout();
	}
	@Override
	public boolean addNewMemberToForum(int id, String forumName, String userName,
			String password, String email) {
		return fs.registerToForum(forumName, userName, password, email);
	}
	@Override
	public boolean sendComplainOnModerator(int id, String user, String moderator,
			String forumName, String subForumName, String content) {
		return fs.complain(user, moderator, forumName, subForumName, content);
	}
	@Override
	public boolean setTwoMembersFriends(int id, String forumName, String userName1,
			String userName2) {
		return fs.setFriends(forumName, userName1, userName2);
	}
	@Override
	public boolean writePostInSubForum(int id, String forumName, String subForumName,
			String username, String title, String content) {
		
		return fs.writePostInSubForum(forumName, subForumName, username, title, content);
	}
	@Override
	public boolean writeResponsePostInSubForum(int id, String forumName,
			String subForumName, String username, String title, String content,
			int postToResponseID) {
		return fs.writeResponsePostInSubForum(forumName, subForumName, username, title, content, postToResponseID);
		
	}
	@Override
	public boolean deletePostInSubForum(int id, String forumName, String subForumName,
			int postID, String moderator) {
		return fs.deletePostInSubForum(forumName, subForumName, postID, moderator);
		
	}
	@Override
	public boolean loginSuperAdmin(int id, String userName, String password) {
		return fs.loginSuperAdmin(userName, password);
	}
	@Override
	public boolean logoutSuperAdmin(int id) {
		return fs.logoutSuperAdmin();
	}
	@Override
	public boolean deleteSubForum(int id, String forumName, String subForumName,
			String adminName) {
		return fs.deleteSubForum(forumName, subForumName, adminName);
	}
	@Override
	public boolean addModerator(int id, String forumName, String subForumName,
			String adminName, String moderatorName) {
		return fs.addModerator(forumName, subForumName, adminName, moderatorName);
	}
	@Override
	public Vector<String> showSubforumsOfForum(int id, String forumName) {
		Vector <SubForum> sf=fs.showSubforumsOfForum(forumName);
		Vector<String> ans= new Vector<>();
		if(sf!=null){
			for(int i=0; i<sf.size() ; i++){
				ans.add(sf.get(i).getName());
			}
				return ans;
		}
		return null;
		
	}
	@Override
	public Vector<String> showPostInSubForum(int id, String forumName,
			String subForumName) {
		Vector<String> ans= new Vector<>();
		HashMap<Integer, Post> posts=fs.showPostInSubForum(int id, forumName, subForumName);
		if(posts!=null){
			for (int post: posts.keySet()) {
			   ans.add(posts.get(post).getTitle());
			}
			return ans;
		}
		return null;
	}
	@Override
	public boolean createSubForumInForumByAdmin(int id, String forumName,
			String subForumName, String description, String adminName) {
		return fs.createSubForumInForumByAdmin(forumName, subForumName, description, adminName);
	}

}
