package project_tests.Bridge;

public class ProxyBridge implements SiteInterface {

	public SiteInterface real;

	public ProxyBridge() {
		this.real=null;
	}

	@Override
	public boolean init(String superAdminName, String passward, String email) {
		if(real!=null)
			return real.init(superAdminName, passward, email);
		return false;
	}

	@Override
	public int openNewConnection() {
		if(real!=null)
			return real.openNewConnection();
		return -1;
	}

	@Override
	public int openSuperAdminConnection() {
		if(real!=null)
			return real.openSuperAdminConnection();
		return -1;
	}



	@Override
	public boolean switchConnection(int connectionID){
		if(real!=null)
			return real.switchConnection(connectionID);
		return false;
	}
	@Override
	public boolean closeConnection(int connectionID){
		if(real!=null)
			return real.closeConnection(connectionID);
		return false;
	}
	@Override
	public boolean closeCurrentConnection(){
		if(real!=null)
			return real.closeCurrentConnection();
		return false;
	}

	@Override
	public boolean login(String user, String pass) {
		if(real!=null)
			return real.login(user, pass);
		return false;
	}

	@Override
	public boolean logout() {
		if(real!=null)
			return real.logout();
		return false;
	}

	@Override
	public boolean enterForum(String forum) {
		if(real!=null)
			return real.enterForum(forum);
		return false;
	}

	@Override
	public boolean exitForum() {
		if(real!=null)
			return real.exitForum();
		return false;
	}

	@Override
	public boolean enterSubforum(String subforumNAme) {
		if(real!=null)
			return real.enterSubforum(subforumNAme);
		return false;
	}

	@Override
	public boolean exitSubforum() {
		if(real!=null)
			return real.exitSubforum();
		return false;
	}

	@Override
	public boolean enterPost(int postID) {
		if(real!=null)
			return real.enterPost(postID);
		return false;
	}

	@Override
	public boolean exitPost() {
		if(real!=null)
			return real.exitPost();
		return false;
	}
	@Override
	public boolean addForum(String name, String description) {
		if(real!=null)
			return real.addForum(name, description);
		return false;
	}

	@Override
	public boolean deleteForum(String forumName) {
		if(real!=null)
			return real.deleteForum(forumName);
		return false;
	}

	@Override
	public boolean addSubforumToForum(String subForumName,
			String description, String adminName) {
		if(real!=null)
			return real.addSubforumToForum(subForumName, description, adminName);
		return false;
	}

	@Override
	public boolean deleteSubForum(String subForumName) {
		if(real!=null)
			return real.deleteSubForum(subForumName);
		return false;
	}

	@Override
	public boolean registerToForum(String userName, String password, String email, String answer) {
		if(real!=null)
			return real.registerToForum(userName, password, email, answer);
		return false;
	}

	@Override
	public boolean setSuperAdminToSite(String superadminName,
			String superadminPass, String email) {
		if(real!=null)
			return real.setSuperAdminToSite(superadminName, superadminPass, email);
		return false;
	}

	@Override
	public boolean addAdministratorToForum(String name) {
		if(real!=null)
			return real.addAdministratorToForum(name);
		return false;
	}

	@Override
	public boolean addModeratorTosubforum(String name) {
		if(real!=null)
			return real.addModeratorTosubforum(name);
		return false;
	}

	@Override
	public int writePostInSubForum(String title, String content) {
		if(real!=null)
			return real.writePostInSubForum(title, content);
		return -1;
	}

	@Override
	public int writeResponsePostInSubForum(String title,
			String content) {
		if(real!=null)
			return writeResponsePostInSubForum(title, content);
		return -1;
	}

	@Override
	public boolean deletePostInSubForum(int postID) {
		if(real!=null)
			return real.deletePostInSubForum(postID);
		return false;
	}

	@Override
	public void cleanAllData() {
		// TODO Auto-generated method stub

	}

	

	@Override
	public boolean isRegularConnection(int connectionID) {
		if(real!=null)
			return real.isRegularConnection(connectionID);
		return false;
	}


	@Override
	public int getCurrentConnectionID() {
		if(real!=null)
			return real.getCurrentConnectionID();
		return -1;
	}
}
