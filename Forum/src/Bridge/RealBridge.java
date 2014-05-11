package Bridge;
import java.util.HashMap;
import java.util.Vector;

import forumSiteSystem.*;
public class RealBridge implements SiteInterface {
	private ForumSystem fs;
	@Override
	public void cleanAllData() {
		// TODO Auto-generated method stub

	}
	@Override
	public boolean init(String superAdminName, String passward, String email) {
		fs=InitializeSystem.init(superAdminName, passward, email);
		return true;
	}
	@Override
	public boolean addForumToSite(String name, String description,
			String adminName, String adminPass, String adminMail) {
		Forum f= fs.createForum(name, description, adminName, adminPass, adminMail);
		if(f!=null)
			return true;
		return false;
	}
	@Override
	public boolean setAdministratorToForum(String name, String forumName) {
		return fs.addAdminToForum(name, forumName);
	}
	@Override
	public boolean setSuperAdminToSite(String name, String pass, String email) {
		return fs.setSuperAdmin(name, pass, email);
	}
	@Override
	public boolean login(String user, String pass, String forumName) {
		return fs.login(user, pass, forumName);
	}
	@Override
	public boolean logout() {
		return fs.logout();
	}
	@Override
	public boolean addNewMemberToForum(String forumName, String userName,
			String password, String email) {
		return fs.registerToForum(forumName, userName, password, email);
	}
	@Override
	public boolean sendComplainOnModerator(String user, String moderator,
			String forumName, String subForumName, String content) {
		return fs.complain(user, moderator, forumName, subForumName, content);
	}
	@Override
	public boolean setTwoMembersFriends(String forumName, String userName1,
			String userName2) {
		return fs.setFriends(forumName, userName1, userName2);
	}
	@Override
	public boolean writePostInSubForum(String forumName, String subForumName,
			String username, String title, String content) {
		
		return fs.writePostInSubForum(forumName, subForumName, username, title, content);
	}
	@Override
	public boolean writeResponsePostInSubForum(String forumName,
			String subForumName, String username, String title, String content,
			int postToResponseID) {
		return fs.writeResponsePostInSubForum(forumName, subForumName, username, title, content, postToResponseID);
		
	}
	@Override
	public boolean deletePostInSubForum(String forumName, String subForumName,
			int postID, String moderator) {
		return fs.deletePostInSubForum(forumName, subForumName, postID, moderator);
		
	}
	@Override
	public boolean loginSuperAdmin(String userName, String password) {
		return fs.loginSuperAdmin(userName, password);
	}
	@Override
	public boolean logoutSuperAdmin() {
		return fs.logoutSuperAdmin();
	}
	@Override
	public boolean exitForum(String forumName) {
		return fs.exitForum(forumName);
	}
	@Override
	public boolean deleteSubForum(String forumName, String subForumName,
			String adminName) {
		return fs.deleteSubForum(forumName, subForumName, adminName);
	}
	@Override
	public boolean addModerator(String forumName, String subForumName,
			String adminName, String moderatorName) {
		return fs.addModerator(forumName, subForumName, adminName, moderatorName);
	}
	@Override
	public Vector<String> showSubforumsOfForum(String forumName) {
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
	public Vector<String> showPostInSubForum(String forumName,
			String subForumName) {
		Vector<String> ans= new Vector<>();
		HashMap<Integer, Post> posts=fs.showPostInSubForum(forumName, subForumName);
		if(posts!=null){
			for (int post: posts.keySet()) {
			   ans.add(posts.get(post).getTitle());
			}
			return ans;
		}
		return null;
	}
	@Override
	public boolean createSubForumInForumByAdmin(String forumName,
			String subForumName, String description, String adminName) {
		return fs.createSubForumInForumByAdmin(forumName, subForumName, description, adminName);
	}

}
