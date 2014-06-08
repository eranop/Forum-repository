package allcode;

import java.util.HashMap;

import services.Response;
import services.report;


public class UserConnection extends SiteConnection {
	private Member _member;
	private services.Logger2 _log;

	public UserConnection(ForumsManagement fs){
		super(fs);
		_member=null;
		_log=null;
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
		if(m.get_password().get_pass().equals(pass)){
			_member=m;
			try{
			_log = new services.Logger2(_member.get_userName()+"-"+_forum.get_forumName());
			}
			catch (Exception exc){
				System.out.println("ERROR SETTING UP LOGGER");
			}
			return report.OK;
		}	
		return report.INVALID_PASSWORD;
	}	

	public report logout(){
		if(_member==null){
			return report.NO_MEMBER;
		}
		_member=null;
		_log=null;
		return report.OK;
	}


	/**
	 * functions that "set" in domain layer
	 * return only report, not objects
	 */
	public report registerToForum(String userName, String password, String email, String answer){
		if(_forum==null){
			System.out.println("not in forum");
			_log.writeToLog("registerToForum",report.NO_FORUM);
			return report.NO_FORUM;
		}
		report rep = _forum.register(userName, password, email, answer);
		if(rep != report.OK){
			_log.writeToLog("registerToForum",rep);
		}
		else _log.writeToLog("registerToForum");
		return rep;
	}

	public report complain(String moderator,String content){
		if(_forum==null){
			System.out.println("not connected to forum!");
			_log.writeToLog("complain",report.NO_FORUM);
			return report.NO_FORUM;
		}
		if(_subForum==null){
			System.out.println("not connected to subforum!");
			_log.writeToLog("complain",report.NO_SUBFORUM);
			return report.NO_SUBFORUM;
		}
		Member m=_forum.getMember(moderator);
		if(m==null){
			_log.writeToLog("complain",report.NO_SUCH_USER_NAME);
			return report.NO_SUCH_USER_NAME;
		}
		report rep =_subForum.complain(_member, m, content);
		if(rep != report.OK){
			_log.writeToLog("registerToForum",rep);
		}
		else _log.writeToLog("registerToForum");
		return rep;
	}


	/**
	 * the user must be logged in forum
	 */

	public report addModerator(String moderatorName){
		if(_subForum == null){
			System.out.println("not connected to subforum!");
			_log.writeToLog("complain",report.NO_SUBFORUM);
			return report.NO_SUBFORUM;
		}
		if(_member == null){
			_log.writeToLog("complain",report.NOT_LOGGED);
			return report.NOT_LOGGED;
		}
		if(_forum.isAdmin(_member)){
			Member member =_forum.getMember(moderatorName);
			if(member == null){
				_log.writeToLog("complain",report.NO_SUCH_USER_NAME);
				return report.NO_SUCH_USER_NAME;
			}
			if (_forum.canAddModerator(member)){
				System.out.println("add moderator " + member.get_userName());
				report rep = _subForum.addModerator(member, _member);//member = the member we want to add. _member = the admin
				if(rep != report.OK){
					_log.writeToLog("addModerator",rep);
				}
				else _log.writeToLog("addModerator");
				return rep;		
			}
			else{
				_log.writeToLog("addModerator",report.DENIED_BY_POLICY);
				return report.DENIED_BY_POLICY;
			}
		}
		else{
			_log.writeToLog("addModerator",report.IS_NOT_ADMIN);
			return report.IS_NOT_ADMIN;
		}
	}

	public report removeModerator(String adminName,String moderatorName){
		if(_subForum == null){
			System.out.println("not connected to forum!");
			_log.writeToLog("complain",report.NO_SUBFORUM);
			return report.NO_SUBFORUM;
		}
		if(_member == null){
			_log.writeToLog("complain",report.NOT_LOGGED);
			return report.NOT_LOGGED;
		}
		if(_forum.isAdmin(_member)){
			Member member =_forum.getMember(moderatorName);
			if(member == null){
				_log.writeToLog("complain",report.NO_SUCH_USER_NAME);
				return report.NO_SUCH_USER_NAME;
			}
			if (_forum.canRemoveModerator(member, _member, _subForum)){
				report rep =  _subForum.removeModerator(member);
				if(rep != report.OK){
					_log.writeToLog("removeModerator",rep);
				}
				else _log.writeToLog("removeModerator");
				return rep;
			}
			else{
				_log.writeToLog("removeModerator",report.DENIED_BY_POLICY);
				return report.DENIED_BY_POLICY;
			}
		}
		else{
			_log.writeToLog("removeModerator",report.IS_NOT_ADMIN);
			return report.IS_NOT_ADMIN;
		}
	}


	public report createSubforum(String name,String description){
		if(_forum==null){
			_log.writeToLog("createSubforum",report.NO_FORUM);
			return report.NO_FORUM;
		}
		report rep =_forum.createSubForum(name, description);
		if(rep != report.OK){
			_log.writeToLog("createSubforum",rep);
		}
		else _log.writeToLog("createSubforum");
		return rep;
	}

	public report createForum(String name,String description){
		//return _fs.createForum(name, description, _member); 
		report rep = _fs.createForum(name, description);
		if(rep != report.OK){
			_log.writeToLog("createForum",rep);
		}
		else _log.writeToLog("createForum");
		return rep;
	}
	
	public report deleteSubForum(String subForumName){
		if(_forum==null){
			System.out.println("not connected to forum!");
			_log.writeToLog("deleteSubForum",report.NO_FORUM);
			return report.NO_FORUM;
		}
		report rep =_forum.deleteSubForum(subForumName, _member);
		if(rep != report.OK){
			_log.writeToLog("deleteSubForum",rep);
		}
		else _log.writeToLog("deleteSubForum");
		return rep;
	}




	public report setFriends(String friendName){
		if(_forum==null){
			System.out.println("not connected to forum!");
			_log.writeToLog("setFriends",report.NO_FORUM);
			return report.NO_FORUM;
		}
		if(_member==null){
			System.out.println("user not logged!");
			_log.writeToLog("setFriends",report.NOT_LOGGED);
			return report.NOT_LOGGED;
		}
		Member friend=_forum.getMember(friendName);
		if(friend==null){
			_log.writeToLog("setFriends",report.NO_SUCH_USER_NAME);
			return report.NO_SUCH_USER_NAME;
		}
		report rep =_forum.setFriends(_member, friend);
		if(rep != report.OK){
			_log.writeToLog("setFriends",rep);
		}
		else _log.writeToLog("setFriends");
		return rep;
	}

	public Response writePost(String title,String content){
		if(_forum==null){
			System.out.println("not connected to forum!");
			_log.writeToLog("writePost",report.NO_FORUM);
			return new Response(report.NO_FORUM);
		}
		if(_subForum==null){
			System.out.println("not connected to subforum!");
			_log.writeToLog("writePost",report.NO_SUBFORUM);
			return new Response(report.NO_SUBFORUM);
		}
		if(_member==null){
			System.out.println("user not logged!");
			_log.writeToLog("writePost",report.NOT_LOGGED);
			return new Response(report.NOT_LOGGED);
		}
		Response post = _subForum.addPost(_member, title, content);
		if  (post.getReport() == report.OK){
			_log.writeToLog("writePost");
			_forum.notifyNewMsgToMembers(_member, title, _subForum);
		}
		else{
			_log.writeToLog("writePost",post.getReport());
		}
		return post;
	}

	public Response writeResponsePost(String title, String content){
		if(_forum==null){
			System.out.println("not connected to forum!");
			_log.writeToLog("writeResponsePost",report.NO_FORUM);
			return new Response(report.NO_FORUM);
		}
		if(_subForum==null){
			System.out.println("not connected to subforum!");
			_log.writeToLog("writeResponsePost",report.NO_SUBFORUM);
			return new Response(report.NO_SUBFORUM);
		}
		if(_member==null){
			System.out.println("user not logged!");
			_log.writeToLog("writeResponsePost",report.NOT_LOGGED);
			return  new Response(report.NOT_LOGGED);
		}
		if(_post==null){
			System.out.println("no post to response to");
			_log.writeToLog("writeResponsePost",report.NO_POST);
			return new Response(report.NO_POST);
		}
		Response post= _subForum.postRespond(_member, _post.getIndex(), title, content);
		if  (post.getReport() == report.OK){
			_log.writeToLog("writeResponsePost");
			_forum.notifyNewMsgToMembers(_member, title, _subForum);
		}
		else{
			_log.writeToLog("writeResponsePost",post.getReport());
		}
		return post;
	}


	public report postEdit(String title, String content) {
		if(_forum == null){
			System.out.println("not connected to forum!");
			_log.writeToLog("postEdit",report.NO_FORUM);
			return report.NO_FORUM;
		}
		if(_subForum == null){
			System.out.println("not connected to subforum!");
			_log.writeToLog("postEdit",report.NO_SUBFORUM);
			return report.NO_SUBFORUM;
		}
		if(_member == null){
			System.out.println("user not logged!");
			_log.writeToLog("postEdit",report.NOT_LOGGED);
			return report.NOT_LOGGED;
		}
		if(_post == null){
			System.out.println("no post to response to");
			_log.writeToLog("postEdit",report.NO_POST);
			return report.NO_POST;
		}
		report rep = _subForum.postEdit(_member, _post.getIndex(), title, content);
		if (rep == report.OK){
			_log.writeToLog("postEdit");
			_forum.notifyResponders(_member, title, _subForum, _post);
			return report.OK;
		}
		else{
			_log.writeToLog("postEdit",rep);
			return rep;
		}
	}


	/**
	 * must stand in the POLICY of the forum
	 */
	public report deletePost(){
		if(_forum==null){
			System.out.println("not connected to forum!");
			_log.writeToLog("deletePost",report.NO_FORUM);
			return report.NO_FORUM;
		}
		if(_subForum==null){
			System.out.println("not connected to subforum!");
			_log.writeToLog("deletePost",report.NO_SUBFORUM);
			return report.NO_SUBFORUM;
		}
		if(_member==null){
			System.out.println("user not logged!");
			_log.writeToLog("deletePost",report.NOT_LOGGED);
			return report.NOT_LOGGED;
		}
		if(_post==null){
			System.out.println("no post to delete");
			_log.writeToLog("deletePost",report.NO_POST);
			return report.NO_POST;
		}
		if (_forum.canDeletePost(_member, _post, _subForum)){
			report rep = _subForum.deletePost(_post);
			if(rep!=report.OK){
				_log.writeToLog("deletePost",rep);
			}
			else _log.writeToLog("deletePost");
			return rep;
		}
		_log.writeToLog("deletePost",report.NOT_ALLOWED);
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
			_log.writeToLog("getListOfPostsByMember",report.NO_FORUM);
			return new Response(report.NO_FORUM);
		}
		if(_member==null){
			System.out.println("user not logged!");
			_log.writeToLog("getListOfPostsByMember",report.NOT_LOGGED);
			return new Response(report.NOT_LOGGED);
		}
		if (!_forum.isAdmin(_member)){
			System.out.println("no admin premission!");
			_log.writeToLog("getListOfPostsByMember",report.IS_NOT_ADMIN);
			return new Response(report.IS_NOT_ADMIN);
		}
		Response res = _forum.getListOfPostsByMember(mNickname);
		if(res.getReport()!=report.OK){
			_log.writeToLog("getListOfPostsByMember",res.getReport());
		}
		else{
			_log.writeToLog("getListOfPostsByMember");
		}
		return res;
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
			_log.writeToLog("getPostNumInSubForum",report.NO_FORUM);
			return new Response(report.NO_FORUM);
		}
		if(_member==null){
			System.out.println("user not logged!");
			_log.writeToLog("getPostNumInSubForum",report.NOT_LOGGED);
			return new Response(report.NOT_LOGGED);
		}
		if (!_forum.isAdmin(_member)){
			System.out.println("no admin premission!");
			_log.writeToLog("getPostNumInSubForum",report.IS_NOT_ADMIN);
			return new Response(report.IS_NOT_ADMIN);
		}
		Response res = _forum.getPostNumInSubForum(subForumName); 
		if(res.getReport()!=report.OK){
			_log.writeToLog("getPostNumInSubForum",res.getReport());
		}
		else{
			_log.writeToLog("getPostNumInSubForum");
		}
		return res;
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
			_log.writeToLog("getListOfModeratorsInSubForum",report.NO_FORUM);
			return new Response(report.NO_FORUM);
		}
		if(_member==null){
			System.out.println("user not logged!");
			_log.writeToLog("getListOfModeratorsInSubForum",report.NOT_LOGGED);
			return new Response(report.NOT_LOGGED);
		}
		if (!_forum.isAdmin(_member)){
			System.out.println("no admin premission!");
			_log.writeToLog("getListOfModeratorsInSubForum",report.IS_NOT_ADMIN);
			return new Response(report.IS_NOT_ADMIN);
		}
		Response res = _forum.getListOfModeratorsInSubForum(subForumName);
		if(res.getReport()!=report.OK){
			_log.writeToLog("getPostNumInSubForum",res.getReport());
		}
		else{
			_log.writeToLog("getPostNumInSubForum");
		}
		return res;
	}
	
	/**
	 * get list of all forums
	 * @return reponse(ok, forums)
	 */
	
	public Response getForums()
	{
		_log.writeToLog("getForums");
		return new Response(report.OK, _fs.getForums());
	}
	
	/**
	 * get list of subforums in a forum
	 * @return response(ok, subForums)
	 */
	
	public Response getSubForums()
	{
		if (_forum == null){
			_log.writeToLog("getSubForums", report.NO_FORUM);
			return new Response(report.NO_FORUM);
		}
		_log.writeToLog("getSubForums");
		return new Response(report.OK, _forum.get_subForums());
	}
	
	/**
	 * get list of all ROOT posts in a sub forum (post responds are in the post object
	 * @return response(ok, posts)
	 */
	
	public Response getPosts()
	{
		if (_forum == null){
			_log.writeToLog("getPosts", report.NO_FORUM);
			return new Response(report.NO_FORUM);
		}
		if (_subForum == null){
			_log.writeToLog("getPosts", report.NO_SUBFORUM);
			return new Response(report.NO_SUBFORUM);
		}
		_log.writeToLog("getPosts");
		return new Response(report.OK, _subForum.getRootPosts());
	}

	@Override
	public void reset() {
		super.reset();
		_member=null;
		_log.writeToLog("reset");
	}

}
