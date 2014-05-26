package allcode;

import services.Response;
import services.report;

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
	
	/**
	 * report function for Admin.
	 * gettin the number of forums in the system.
	 * @return Response(report.ok, int) 
	 */
	public Response getNumberOfForums()
	{
		if(_forum==null){
			System.out.println("not connected to forum!");
			return new Response(report.NO_FORUM);
		}
		return new Response(report.OK, _fs.getForums().size());
	}

	public report setSuperAdmin(String superadminName, String superadminPass,
			String email) {
		
		return _fs.setSuperAdmin(superadminName, superadminPass, email);	
	}

}
