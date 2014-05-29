package allcode;

import java.util.HashMap;

import services.Response;
import services.report;


public class UserConnection extends SiteConnection {
	private Member _member;

	public UserConnection(ForumsManagement fs){
		super(fs);
		_member=null;		
	}

	/**
	 * for each state there is "in" and "out" functions
	 */
	@Override
	public report exitForum(){
		report r=super.exitForum();
		if(!r.equals(report.OK))
			return r; 
		_member=null;
		return report.OK;
	}

	public report login(String userName, String pass){
		if(_forum == null)
			return report.NO_FORUM;
		if(_member!=null)
			return report.ALREADY_MEMBER_EXIST;
		Member m= _forum.getMember(userName);
		if(m==null)
			return report.NO_SUCH_USER_NAME;
		//TODO set canLogin method in forum
		if(m.get_password().equals(pass)){
			_member=m;
			return report.OK;
		}	
		return report.INVALID_PASSWORD;
	}	

	public report logout(){
		if(_member==null){
			return report.NO_MEMBER;
		}
		_member=null;
		return report.OK;
	}


	/**
	 * functions that "set" in domain layer
	 * return only report, not objects
	 */
	public report registerToForum(String userName, String password, String email, String answer){
		if(_forum==null){
			System.out.println("not in forum");
			return report.NO_FORUM;
		}
		return _forum.register(userName, password, email, answer);
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

	public report addModerator(String moderatorName){
		if(_subForum == null){
			System.out.println("not connected to subforum!");
			return report.NO_SUBFORUM;
		}
		if(_member == null){
			return report.NOT_LOGGED;
		}
		if(_forum.isAdmin(_member)){
			Member member =_forum.getMember(moderatorName);
			if(member == null){
				return report.NO_SUCH_USER_NAME;
			}
			if (_forum.canAddModerator(member)){
				System.out.println("add moderator " + member.get_userName());
				return _subForum.addModerator(member, _member);		//member = the member we want to add. _member = the admin
			}
			else
				return report.DENIED_BY_POLICY;
		}
		else{
			return report.IS_NOT_ADMIN;
		}
	}

	public report removeModerator(String adminName,String moderatorName){
		if(_subForum == null){
			System.out.println("not connected to forum!");
			return report.NO_SUBFORUM;
		}
		if(_member == null){
			return report.NOT_LOGGED;
		}
		if(_forum.isAdmin(_member)){
			Member member =_forum.getMember(moderatorName);
			if(member == null){
				return report.NO_SUCH_USER_NAME;
			}
			if (_forum.canRemoveModerator(member, _member, _subForum))
				return _subForum.removeModerator(member);
			else
				return report.DENIED_BY_POLICY;
		}
		else{
			return report.IS_NOT_ADMIN;
		}
	}


	public report createSubforum(String name,String description){
		if(_forum==null){
			return report.NO_FORUM;
		}
		return _forum.createSubForum(name, description);
	}

	public report createForum(String name,String description){
		//return _fs.createForum(name, description, _member); 
		return _fs.createForum(name, description);
	}
	public report deleteSubForum(String subForumName){
		if(_forum==null){
			System.out.println("not connected to forum!");
			return report.NO_FORUM;
		}
		return _forum.deleteSubForum(subForumName, _member);
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

	public Response writePost(String title,String content){
		if(_forum==null){
			System.out.println("not connected to forum!");
			return new Response(report.NO_FORUM);
		}
		if(_subForum==null){
			System.out.println("not connected to subforum!");
			return new Response(report.NO_SUBFORUM);
		}
		if(_member==null){
			System.out.println("user not logged!");
			return new Response(report.NOT_LOGGED);
		}
		Response post=_subForum.addPost(_member, title, content);
		if  (post.getReport() == report.OK)
			_forum.notifyNewMsgToMembers(_member, title, _subForum);
		return post;
	}

	public Response writeResponsePost(String title, String content){
		if(_forum==null){
			System.out.println("not connected to forum!");
			return new Response(report.NO_FORUM);
		}
		if(_subForum==null){
			System.out.println("not connected to subforum!");
			return new Response(report.NO_SUBFORUM);
		}
		if(_member==null){
			System.out.println("user not logged!");
			return  new Response(report.NOT_LOGGED);
		}
		if(_post==null){
			System.out.println("no post to response to");
			return new Response(report.NO_POST);
		}
		Response post= _subForum.postRespond(_member, _post.getIndex(), title, content);
		if  (post.getReport() == report.OK)
			_forum.notifyNewMsgToMembers(_member, title, _subForum);
		return post;
	}


	public report postEdit(String title, String content) {
		if(_forum == null){
			System.out.println("not connected to forum!");
			return report.NO_FORUM;
		}
		if(_subForum == null){
			System.out.println("not connected to subforum!");
			return report.NO_SUBFORUM;
		}
		if(_member == null){
			System.out.println("user not logged!");
			return report.NOT_LOGGED;
		}
		if(_post == null){
			System.out.println("no post to response to");
			return report.NO_POST;
		}

		if (_subForum.postEdit(_member, _post.getIndex(), title, content) == report.OK)
			_forum.notifyResponders(_member, title, _subForum, _post);
		return report.OK;
	}


	/**
	 * must stand in the POLICY of the forum
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
		if (_forum.canDeletePost(_member, _post, _subForum))
			return _subForum.deletePost(_post);

		return report.NOT_ALLOWED;
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
	 * Report function for admin user.
	 * admin enters a membername and gets a list of all his posts.
	 * @param mNickname is member's nickname
	 * @return Response(report.OK, HashMap <Integer, Post>)
	 */
	public Response getListOfPostsByMember(String mNickname) 
	{ 
		if(_forum==null){
			System.out.println("not connected to forum!");
			return new Response(report.NO_FORUM);
		}
		if(_member==null){
			System.out.println("user not logged!");
			return new Response(report.NOT_LOGGED);
		}
		if (!_forum.isAdmin(_member)){
			System.out.println("no admin premission!");
			return new Response(report.IS_NOT_ADMIN);
		}
		return _forum.getListOfPostsByMember(mNickname);
	} 

	/**
	 * Report function for admin user.
	 * admin enters a sub forum name and gets the number of posts in a sub forum.
	 * @param subForumName is sub forum's name
	 * @return Response (report.OK, Integer)
	 */
	public Response getPostNumInSubForum(String subForumName) 
	{ 
		if(_forum==null){
			System.out.println("not connected to forum!");
			return new Response(report.NO_FORUM);
		}
		if(_member==null){
			System.out.println("user not logged!");
			return new Response(report.NOT_LOGGED);
		}
		if (!_forum.isAdmin(_member)){
			System.out.println("no admin premission!");
			return new Response(report.IS_NOT_ADMIN);
		}
		return _forum.getPostNumInSubForum(subForumName); 
	} 			
	
	/**
	 * Report function for admin user.
	 * admin enters a sub forum name and gets Vector of all his moderatos.
	 * information need to be parsed: List of mods. Who apointed them. When and to which subForum. thier posts.
	 * @param subForumName is sub forum's name
	 * @return Response (report.OK, Vector <Member>) which are moderators. inside theres access for all information needed)
	 */
	public Response getListOfModeratorsInSubForum(String subForumName)
	{
		if(_forum==null){
			System.out.println("not connected to forum!");
			return new Response(report.NO_FORUM);
		}
		if(_member==null){
			System.out.println("user not logged!");
			return new Response(report.NOT_LOGGED);
		}
		if (!_forum.isAdmin(_member)){
			System.out.println("no admin premission!");
			return new Response(report.IS_NOT_ADMIN);
		}
		return _forum.getListOfModeratorsInSubForum(subForumName);
	}

	@Override
	public void reset(){
		super.reset();
		_member=null;
	}

}
