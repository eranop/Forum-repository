
public class UserState {
	

	

	private boolean _isSuperAdmin;
	
	private Member _member;
	private Forum _forum;
	
	
	
	public UserState(Member _member, Forum _forum, SubForum _subForum) {
		this._member = _member;
		this._forum = _forum;
	}
		
	public UserState(){
		this._forum=null;
		this._member=null;
	}
	
	
	public boolean isMemberLoggedInForum(String user,String forum){
		if(_forum!=null && _member!=null){
			if(_forum.get_forumName().equals(forum) &&
					_member.get_userName().equals(user) && _forum.isMember(user)){
				return true;
			}
				
		}
		return false;
	}
	
	public void superAdminModeOn(){
		_isSuperAdmin=true;
		
	}
	public void superAdminModeOff(){
		_isSuperAdmin=false;
	}
	
	public boolean isInForum(String forum){
		if(_forum==null || !(_forum.get_forumName().equals(forum))){
			return false;
		}
		return true;
	}
	
	public boolean logout() {
		if(_forum == null){
			System.out.println("not in forum!");
			return false;
		}
		if(_member==null){
			System.out.println("not logged in!");
			return false;
		}
		else{
			_member=null;
			return true;
		}
		
	}
	
	//getters and setters
	public Member get_member() {
		return _member;
	}

	public void set_member(Member _member) {
		this._member = _member;
	}
	

	public Forum get_forum() {
		return _forum;
	}

	public void set_forum(Forum _forum) {
		this._forum = _forum;
	}
	
	public boolean isSuperAdmin() {
		return _isSuperAdmin;
	}

	public void set_isSuperAdmin(boolean _isSuperAdmin) {
		this._isSuperAdmin = _isSuperAdmin;
	}


	
	
	
	
	
}
