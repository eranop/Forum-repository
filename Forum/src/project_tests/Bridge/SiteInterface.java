package project_tests.Bridge;

public interface SiteInterface {
	/*
	 * all tests are state based- every connection save its state.
	 * doesn't need to pass arguments which passed before.
	 */
	// initialize functions
	public boolean init(String superAdminName, String passward, String email);
	public int openNewConnection();
	public int openSuperAdminConnection();
	
	public boolean isRegularConnection(int connectionID);
	public boolean switchConnection(int connectionID);
	public boolean closeConnection(int connectionID);
	public boolean closeCurrentConnection();
	
	//state functions
	public boolean login(String user,String pass);
	public boolean logout();
	public boolean enterForum(String forum);
	public boolean exitForum();
	public boolean enterSubforum(String subforumNAme);
	public boolean exitSubforum();
	public boolean enterPost(int postID);
	public boolean exitPost();
	//public boolean loginSuperAdmin(String userName, String password);
	//public boolean logoutSuperAdmin();
	
	//base functions
	public boolean addForum(String name,String description);
	public boolean deleteForum(String forumName);
	
	public boolean addSubforumToForum(String subForumName, String description, String adminName);
	public boolean deleteSubForum(String subForumName);
	
	public boolean registerToForum(String userName,String password,String email);
	//public boolean deleteMemberFromForum(int id, String userName);
	
	//the new super administrator is not exist- create new one
	public boolean setSuperAdminToSite(String superadminName, String superadminPass,  String email);
	//the new admin\ modearator already exist 
	public boolean addAdministratorToForum(String name);
	public boolean addModeratorTosubforum(String name);
	/*
	 * public boolean removeAdministrator(int id, String name);
	 * public boolean removeModerator(int id, String name);
	 * 
	 */
	
	//post functions
	public boolean writePostInSubForum(String title,String content);
	public boolean writeResponsePostInSubForum(String title,String content);
	public boolean deletePostInSubForum(int postID);
	
	/*
	public boolean sendComplainOnModerator(int id, String user,String moderator,
			String forumName,String subForumName,String content);
	public boolean setTwoMembersFriends(int id, String forumName,String userName1,String userName2);
	public boolean addModerator(int id, String forumName,String subForumName,
			String adminName,String moderatorName);
	
	public Vector<String> showSubforumsOfForum(String forumName);
	public Vector<String> showPostInSubForum(String forumName, String subForumName);
*/	
	public void cleanAllData();
	int getCurrentConnectionID();

}
