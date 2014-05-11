package project_tests.Bridge;

import java.util.Vector;

public interface SiteInterface {
	
	public boolean init(String superAdminName, String passward, String email);
	public int openNewConnection();
	public boolean addForumToSite(int id, String name,String description,
			String adminName,String adminPass,String adminMail);
	
	public boolean setAdministratorToForum(int id, String superadminName, String pass,
			String name,String forumName);
	public boolean setSuperAdminToSite(int id, String superadminName, String superadminPass, String name,String newPass, String email);
	
	
	public boolean login(int id, String user,String pass,String forumName);
	public boolean logout(int id);
	
	public boolean addNewMemberToForum(int id, String forumName,String userName,String password,String email);
	public boolean sendComplainOnModerator(int id, String user,String moderator,
			String forumName,String subForumName,String content);
	public boolean setTwoMembersFriends(int id, String forumName,String userName1,String userName2);
	public boolean writePostInSubForum(int id, String forumName,String subForumName,
			String username,String title,String content);
	public boolean writeResponsePostInSubForum(int id, String forumName,String subForumName,
			String username,String title,String content,int postToResponseID);
	public boolean deletePostInSubForum(int id, String forumName,
			String subForumName,int postID,String moderator);
	public boolean loginSuperAdmin(int id, String userName, String password);
	public boolean logoutSuperAdmin(int id);
	public boolean createSubForumInForumByAdmin(int id, String forumName, String subForumName,
			String description, String adminName);
	
	//public boolean exitForum(String forumName);
	public boolean deleteSubForum(int id, String forumName,String subForumName,String adminName);
	public boolean addModerator(int id, String forumName,String subForumName,
			String adminName,String moderatorName);
	
	public Vector<String> showSubforumsOfForum(String forumName);
	public Vector<String> showPostInSubForum(String forumName, String subForumName);
	
	public void cleanAllData();

}
