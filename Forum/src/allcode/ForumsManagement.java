package allcode;
import java.util.HashMap;
import java.util.Vector;

/**
 * use by super administrator actor
 * contains super administrator operations
 */
public class ForumsManagement {

	private Member _superAdmin;
	private Vector<Forum> _forums;

	public ForumsManagement() {
		_forums=new Vector<Forum>();
	}
	public ForumsManagement(String superAdminName, String password, String email) {
		_forums=new Vector<Forum>();
		_superAdmin= new Member(superAdminName, password, email);
	}


	/**
	 * 3 constructors:
	 *  - creating new member which  will be administrator
	 *  - forum without administrator
	 *  - creating forum with member and add it as administrator
	 */
	public report createForum(String name,String description,String adminName,String adminPass,String adminMail){
		//description is not must
		if(name==null || adminName==null || adminPass==null || adminMail==null){
			return report.NULL_ARGUMENTS; 
		}
		if(isForumExist(name)){
			return report.ALREADY_FORUM_EXIST;
		}
		Forum forum=new Forum(name,description);
		report rep=forum.register(adminName, adminPass, adminMail);
		if(rep.equals(report.OK)){
		_forums.add(forum);
		return forum.addAdminByName(adminName);
		}
		return rep;
	}
	public report createForum(String name,String description){
		//description is not must
		if(name==null){
			return report.NULL_ARGUMENTS; 
		}
		if(isForumExist(name)){
			return report.ALREADY_FORUM_EXIST;
		}
		Forum forum=new Forum(name,description);
		//report rep=forum.register(adminName, adminPass, adminMail);
		_forums.add(forum);
		
		return report.OK;
	}
	public report createForum(String name,String description, Member admin){
		//description is not must
		if(name==null){
			return report.NULL_ARGUMENTS; 
		}
		if(isForumExist(name)){
			return report.ALREADY_FORUM_EXIST;
		}
		Forum forum=new Forum(name,description);
		//report rep=forum.register(adminName, adminPass, adminMail);
		//TODO registration with email sending
		if(admin != null){
			forum.addAdmin(admin);
		}
		_forums.add(forum);
		
		return report.OK;
	}
	public report deleteForum(String forumName){
		if(isForumExist(forumName)){
			_forums.remove(getForum(forumName));
			return report.OK;
		}
		return report.NO_SUCH_FORUM;
	}

	/**
	 * 
	 * @param name of member in forum
	 * @param forumName name of forum
	 * 
	 */

	public report setSuperAdmin(String name,String pass,String email){
		
		if(email==null || pass==null || name==null)
			return report.NULL_ARGUMENTS;
		//decide if we want to replace current super administrator
		//get member details (because he must be member in each forum)
		_superAdmin.set_email(email);
		_superAdmin.setNewPassword(pass);
		_superAdmin.set_userName(name);

		return report.OK;
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
	public boolean isForumExist(String forumName){
		for(int i=0;i<_forums.size();i++){
			if(_forums.elementAt(i).get_forumName().equals(forumName)){
				return true;
			}
		}
		return false;
	}
	public boolean isValidSuperAdmin(String userName, String pass){
		if(_superAdmin.get_userName().equals(userName) &&
				_superAdmin.get_password().equals(pass)){
			return true;
		}
		return false;
	}








}
