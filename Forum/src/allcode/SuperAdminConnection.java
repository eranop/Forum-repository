package allcode;

public class SuperAdminConnection extends SiteConnection{

	public SuperAdminConnection(ForumsManagement fs) {
		super(fs);
	}

	public report addNewForum(String forumName, String description){
		return _fs.createForum(forumName, description);
	}
	
	public report deleteForum(String forumName) {
		return _fs.deleteForum(forumName);
	}
	
	public report addAdminToForum(String name){
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

	public report setSuperAdmin(String superadminName, String superadminPass,
			String email) {
		
		return _fs.setSuperAdmin(superadminName, superadminPass, email);	
	}

}
