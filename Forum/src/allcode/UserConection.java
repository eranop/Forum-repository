package allcode;


public class UserConection {
private static int IDs=0;
	private Forum _forum;
	private SubForum _subForum;
	private Member _member;
	private Post _post;
	private ForumsManagement _fs;
	private boolean _isSuperAdmin;
	private int _id;
	
public UserConection(ForumsManagement fs){
		_fs=fs;
		_forum=null;
		_subForum=null;
		_member=null;
		_post=null;
		_isSuperAdmin=false;
		_id=IDs++;
	}
	
/**
 * for each state there is "in" and "out" functions
 */
	public report logout(){
		if(_member==null){
			return report.NO_MEMBER;
		}
		_member=null;
		return report.OK;
	}
	public report login(String userName, String pass){
		if(_forum == null)
			return report.NO_FORUM;
		Member m= _forum.getMember(userName);
		if(m==null)
			return report.NO_SUCH_USER_NAME;
		if(m.get_password().equals(pass)){
			_member=m;
			return report.OK;
		}	
		return report.INVALID_PASSWORD;
	}	
	public report exitForum(){
		if(_forum==null)
			return report.NO_FORUM;
		_forum=null;
		_subForum=null;
		if(!_isSuperAdmin){ 
			//superAdmin stay connected as member even if he exit the forum
			_member=null;
		}
		return report.OK;
	}
	public report enterForum(String forumName){
		Forum f= _fs.getForum(forumName);
		if(f==null){
			return report.NO_SUCH_FORUM;
		}
		_forum=f;
		return report.OK;
	}
	public report exitSubforum(){
		if(_subForum==null)
			return report.NO_SUBFORUM;
		_subForum=null;
		return report.OK;
	}
	public report enterSubforum(String subforumName){
		if(_forum==null){
			return report.NO_FORUM;
		}
		SubForum sf= _forum.getSubForum(subforumName);
		if(sf==null){
			return report.NO_SUCH_SUBFORUM;
		}
		_subForum=sf;
		return report.OK;
	}
	public report enterPost(int id){
		if(_subForum==null){
			return report.NO_SUBFORUM;
		}
		Post p=_subForum.getPostByIndex(id);
		if(p==null){
			return report.NO_SUCH_POST;
		}
		_post=p;
		return report.OK;
	}
	public report exitPost(){
		if(_post==null){
			return report.NO_POST;
		}
		_post=null;
		return report.OK;
	}

	
	public report loginAsSuperAdmin(String userName, String pass){
		if(userName==null || pass==null){
			return report.NULL_ARGUMENTS;
		}
		if(_member!=null){
			return report.ALREADY_MEMBER_EXIST;
		}
		if(!_fs.isValidSuperAdmin(userName, pass)){
			return report.IS_NOT_SUPERADMIN;
		}
		_isSuperAdmin=true;
		_member=Member.createSuperAdminMember(userName);
		return report.OK;
	}
	public report logoutAsSuperAdmin(){
		if(!_isSuperAdmin){
			return report.IS_NOT_SUPERADMIN;
		}
		if(_member==null){
			return report.ALREADY_MEMBER_EXIST;
		}
		_isSuperAdmin=false;
		_member=null;
		return report.OK;
	}
	
/**
 * functions that "set" in domain layer
 * return only report, not objects
 */
	public report registerToForum(String userName,String password,String email){
		if(_forum==null){
			System.out.println("not in forum");
			return report.NO_FORUM;
		}
		return _forum.register(userName, password, email);
	}

	public report complain(String moderator,String content){
		if(_forum==null){
			System.out.println("not connected to forum!");
			return report.NO_FORUM;
		}
		if(_subForum==null){
			System.out.println("not connected to subforum!");
			return report.NO_SUBFORUM;
		}
		Member m=_forum.getMember(moderator);
		if(m==null)
			return report.NO_SUCH_USER_NAME;
		return _subForum.complain(_member, m, content);
	}


	/**
	 * the user must be logged in forum
	 */
	public report deleteSubForum(String subForumName){
		if(_forum==null){
			System.out.println("not connected to forum!");
			return report.NO_FORUM;
		}
		if(_member==null){
			System.out.println("user not logged!");
			return report.NOT_LOGGED;
		}
		return _forum.deleteSubForum(subForumName, _member);
	}

	public report addModerator(String adminName,String moderatorName){
		if(_subForum==null){
			System.out.println("not connected to forum!");
			return report.NO_SUBFORUM;
		}
		if(_member==null){
			return report.NOT_LOGGED;
		}
		if(_forum.isAdmin(_member)){
			Member m=_forum.getMember(moderatorName);
			if(m==null){
				return report.NO_SUCH_USER_NAME;
			}
			return _subForum.addModerator(m);
		}
		else{
			return report.IS_NOT_ADMIN;
		}
	}



	public report setFriends(String friendName){
		if(_forum==null){
			System.out.println("not connected to forum!");
			return report.NO_FORUM;
		}
		if(_member==null){
			System.out.println("user not logged!");
			return report.NOT_LOGGED;
		}
		Member friend=_forum.getMember(friendName);
		if(friend==null){
			return report.NO_SUCH_USER_NAME;
		}
		return _forum.setFriends(_member, friend);
	}

	public report writePost(String title,String content){
		if(_forum==null){
			System.out.println("not connected to forum!");
			return report.NO_FORUM;
		}
		if(_subForum==null){
			System.out.println("not connected to subforum!");
			return report.NO_SUBFORUM;
		}
		if(_member==null){
			System.out.println("user not logged!");
			return report.NOT_LOGGED;
		}
		if  (_subForum.addPost(_member, title, content) == report.OK)
			_forum.notifyNewMsgToMembers(_member, title, _subForum);
	}

	public report writeResponsePost(String title,String content){
		if(_forum==null){
			System.out.println("not connected to forum!");
			return report.NO_FORUM;
		}
		if(_subForum==null){
			System.out.println("not connected to subforum!");
			return report.NO_SUBFORUM;
		}
		if(_member==null){
			System.out.println("user not logged!");
			return report.NOT_LOGGED;
		}
		if(_post==null){
			System.out.println("no post to response to");
			return report.NO_POST;
		}
		return _subForum.postRespond(_member, _post.getIndex(), title, content);

	}
	/**
	 * must be in post as moderator or owner of the post
	 */
	public report deletePost(){
		if(_forum==null){
			System.out.println("not connected to forum!");
			return report.NO_FORUM;
		}
		if(_subForum==null){
			System.out.println("not connected to subforum!");
			return report.NO_SUBFORUM;
		}
		if(_member==null){
			System.out.println("user not logged!");
			return report.NOT_LOGGED;
		}
		if(_post==null){
			System.out.println("no post to delete");
			return report.NO_POST;
		}
		if(_member.get_userName().equals(_post.getPublisher()) //publisher delete his post
			|| _subForum.isModerator(_member)	){ //moderator delete post 
			return _subForum.deletePost(_post);
		}
		return report.NOT_ALLOWED;
			
	}

	public report addAdminToForum(String name){

		//check that the super admin do that

		if(_forum==null){
			System.out.println("no such forum");
			return report.NO_FORUM;
		}
		else{
			Member member=_forum.getMember(name);
			if(member!=null){
				_forum.addAdmin(member);
				return report.OK;
			}
			else{
				System.out.println("no such member");
				return report.NO_MEMBER;
			}
		}
	}
	
	public report createSubforum(String name,String description){
		if(_forum==null){
			return report.NO_FORUM;
		}
		if(_member==null){
			return report.NOT_LOGGED;
		}
		if(!_forum.isAdmin(_member)){
			return report.NOT_ALLOWED;
		}
		
		return _forum.createSubForum(name, description);
	}


/**
 * functions that transform objects of domain layer 
 * to the GUI representation
 */
	
	/*
	public Post getPost(int id){
		
	}
	*/

	
	
	/**
	 * getters
	 */
	public int getID(){
		return _id;
	}
	
}