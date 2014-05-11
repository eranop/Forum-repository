package Bridge;

import java.util.Vector;

public interface SiteInterface {
	public boolean init(String superAdminName, String passward, String email);
	public boolean addForumToSite(String name,String description,
			String adminName,String adminPass,String adminMail);
	
	public boolean setAdministratorToForum(String name,String forumName);
	public boolean setSuperAdminToSite(String name,String pass,String email);
	
	
	public boolean login(String user,String pass,String forumName);
	public boolean logout();
	
	public boolean addNewMemberToForum(String forumName,String userName,String password,String email);
	public boolean sendComplainOnModerator(String user,String moderator,
			String forumName,String subForumName,String content);
	public boolean setTwoMembersFriends(String forumName,String userName1,String userName2);
	public boolean writePostInSubForum(String forumName,String subForumName,
			String username,String title,String content);
	public boolean writeResponsePostInSubForum(String forumName,String subForumName,
			String username,String title,String content,int postToResponseID);
	public boolean deletePostInSubForum(String forumName,
			String subForumName,int postID,String moderator);
	public boolean loginSuperAdmin(String userName, String password);
	public boolean logoutSuperAdmin();
	public boolean createSubForumInForumByAdmin(String forumName, String subForumName,
			String description, String adminName);
	
	public boolean exitForum(String forumName);
	public boolean deleteSubForum(String forumName,String subForumName,String adminName);
	public boolean addModerator(String forumName,String subForumName,
			String adminName,String moderatorName);
	
	public Vector<String> showSubforumsOfForum(String forumName);
	public Vector<String> showPostInSubForum(String forumName, String subForumName);
	
	public void cleanAllData();

}
