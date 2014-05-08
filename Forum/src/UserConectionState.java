

public class UserConectionState {
	
	Forum _forum;
	SubForum _subForum;
	Member _member;
	
	public UserConectionState(){
		_forum=null;
		_subForum=null;
		_member=null;
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

	/**
	 * 
	 * set super admin???
	 */

	public report logout(){
		if(_member==null){
			return report.NO_MEMBER;
		}
		_member=null;
		return report.OK;
	}

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
		Memebr m=_forum.getMember(moderator);
		if(m==null)
			return report.NO_MODERATOR;
		return _subForum.complain(_member, m, content);
	}//heara


	
	
	
	
	
	
	private void setForum(Forum f){
		_forum=f;
	}
	private void setSubForum(SubForum s){
		_subForum=s;
	}
	private void setMember(Member m){
		_member=m;
	}
	
	
	
	
	
	
}
