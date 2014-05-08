import java.util.HashMap;
import java.util.Vector;

/**
 * 
 * use by super administrator actor
 * contains super administrator operations
 *
 */
public class ForumSystem {




	private SuperAdmin _superAdmin;
	private Vector<Forum> _forums;
	private UserState _state;

	public ForumSystem() {
		_forums=new Vector<Forum>();
		_superAdmin=new SuperAdmin();
		_state=new UserState();
	}
	
	
	/**
	 * creates forum and adds admin as member to the forum and admin of the forum
	 * @param name
	 * @param description
	 * @param adminName
	 * @param adminPass
	 * @param adminMail
	 * @return the new forum
	 */
	public Forum createForum(String name,String description,String adminName,String adminPass,String adminMail){
		//delegation to forum constructor
		if(_state.isSuperAdmin()){
			Forum forum=new Forum(name,description);
			forum.register(adminName, adminPass, adminMail);

			_forums.add(forum);
			addAdminToForum(adminName, name);

			return forum;
		}
		else{
			System.out.println("you are not allowed!");
			return null;
		}
	}

	/**
	 * 
	 * @param name of member in forum
	 * @param forumName name of forum
	 * 
	 */
	
	public boolean addAdminToForum(String name,String forumName){
		Forum forum=getForum(forumName);
		if(forum==null){
			System.out.println("no such forum");
			return false;
		}
		else{
			Member member=forum.getMember(name);
			if(member!=null){


				forum.addAdmin(member);
				return true;
			}
			else{
				System.out.println("no such member");
				return false;
			}
		}
	}

	public boolean setSuperAdmin(String name,String pass,String email){
		//decide if we want to replace current super administrator
		//get member details (because he must be member in each forum)

		_superAdmin.set_email(email);
		_superAdmin.set_password(pass);
		_superAdmin.set_userName(name);

		return true;
	}
	/*
	public boolean complain(String user,String moderator,String forumName,String subForumName,String content){
		if(!_state.isMemberLoggedInForum(user, forumName)){
			System.out.println("not connected to forum!");
			return false;
		}
		Forum forum=getForum(forumName);
		if(forum==null){
			System.out.println("no such forum");
			return false;
		}
		return forum.complainInSubForum(subForumName, user, moderator, content);
	}
	
	public boolean exitForum(String forumName){
		if(!_state.isInForum(forumName)){
			System.out.println("not in the forum!");
			return false;
		}
		_state.set_forum(null);
		return true;
		
	}

	public boolean deleteSubForum(String forumName,String subForumName,String adminName){
		if(!_state.isMemberLoggedInForum(adminName, forumName)){
			System.out.println("not connected to forum!");
			return false;
		}
		Forum forum=getForum(forumName);
		if(forum==null){
			System.out.println("no such forum");
			return false;
		}
		return forum.deleteSubForum(subForumName,adminName);
	}
	*/
	
	/**
	 * adds a moderator to a sub-forum if the moderator is a member
	 * @param forumName
	 * @param subForumName
	 * @param adminName
	 * @param moderatorName
	 * @return true if the moderator was added
	 */
	/*
	public boolean addModerator(String forumName,String subForumName,String adminName,String moderatorName){
		if(!_state.isMemberLoggedInForum(adminName, forumName)){
			System.out.println("not connected to forum!");
			return false;
		}
		Forum forum=getForum(forumName);
		if(forum==null){
			System.out.println("no such forum");
			return false;
		}
		return forum.addModerator(subForumName,adminName,moderatorName);
	}
	
	

	public boolean setFriends(String forumName,String userName1,String userName2){
		if(!_state.isMemberLoggedInForum(userName1, forumName)){
			System.out.println("not connected to forum!");
			return false;
		}
		Forum forum=getForum(forumName);
		if(forum==null){
			System.out.println("no such forum");
			return false;
		}
		return forum.setFriends(userName1, userName2);


	}

	public boolean login(String user,String pass,String forumName){
		if(!_state.isInForum(forumName)){
			System.out.println("not in the forum!");
			return false;
		}
		else if(_state.isMemberLoggedInForum(user, forumName)){
			System.out.println("already logged in!");
			return false;
		}
		else{
			Forum forum=getForum(forumName);
			if(forum!=null){
				Member temp=forum.login(user, pass);
				if(temp!=null){
					_state.set_member(temp);
					return true;
				}
			}
			else{
				System.out.println("no such forum!");
				return false;
			}
		}
		return false;

	}
*/
	public boolean writePostInSubForum(String forumName,String subForumName,
			String username,String title,String content){
		if(!_state.isMemberLoggedInForum(username, forumName)){
			System.out.println("not logged in to the forum!");
			return false;
		}
		Forum forum=getForum(forumName);
		if(forum==null){
			System.out.println("no such forum!");
			return false;
		}
		return forum.postInSubForum(subForumName, username, title, content);
	}

	public boolean writeResponsePostInSubForum(String forumName,String subForumName,
			String username,String title,String content,int postToResponseID){
		Forum forum=getForum(forumName);
		if(forum!=null){
			if(_state.isMemberLoggedInForum(username, forumName)){
				return forum.postResponseInSubForum(subForumName, username, title, content, postToResponseID);
			}
			else
				System.out.println("not connected to forum!");
		}
		else
			System.out.println("no such forum!");
		return false;
	}
	/*
	public boolean deletePostInSubForum(String forumName,String subForumName,int postID,String moderator){
		Forum forum=getForum(forumName);
		if(forum!=null){
			if(_state.isMemberLoggedInForum(moderator, forumName)){
				return forum.deleteFromSubForum(subForumName, postID);
			}
			else
				System.out.println("not connected to forum!");
		}
		else
			System.out.println("no such forum!");
		return false;
	}
*/


	public boolean loginSuperAdmin(String userName, String password){
		if(_state.isSuperAdmin()==true){
			System.out.println("already logged in!");
			return false;
		}
		else if(_superAdmin.get_userName().equals(userName) && _superAdmin.get_password().equals(password)){
			_state.superAdminModeOn();
			return true;
		}
		else{
			System.out.println("wrong user or password");
			return false;
		}
	}

	public boolean logoutSuperAdmin(){
		if(_state.isSuperAdmin()){
			_state.superAdminModeOff();
			return true;
		}
		return false;
	}

	
	/**
	 * enters a forum and shows a list of its sub-forums
	 * @param forumName
	 * @return a vector of the subforums
	 */
	public Vector<SubForum> showSubforumsOfForum(String forumName) {
		Forum forum=getForum(forumName);
		if(forum==null){
			System.out.println("no such forum");
			return null;
		}
		_state.set_forum(forum);
		return forum.get_subForums();
	}
	
	public HashMap<Integer,Post> showPostInSubForum(String forumName, String subForumName) {
		if(!_state.isInForum(forumName)){
			System.out.println("not connected to forum");
			return null;
		}
		Forum forum=getForum(forumName);
		if(forum==null){
			System.out.println("no such forum");
			return null;
		}
		return forum.showPostInSubForum(subForumName);
		
	}

	public boolean createSubForumInForumByAdmin(String forumName, String subForumName,
			String description, String adminName) {
		if(!_state.isMemberLoggedInForum(adminName, forumName)){
			System.out.println("not connected to forum");
			return false;
		}
		Forum forum=getForum(forumName);
		if(forum==null){
			System.out.println("no such forum");
			return false;
		}
		return forum.createSubForum(subForumName, description,adminName);
		
	}
	
	//helper functions
	public Forum getForum(String forumName){
		for(int i=0;i<_forums.size();i++){
			if(_forums.elementAt(i).get_forumName().equals(forumName)){
				return _forums.elementAt(i);
			}
		}
		return null;
	}

	

	




}
