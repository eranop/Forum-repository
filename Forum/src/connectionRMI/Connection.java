package connectionRMI;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import allcode.Forum;
import allcode.ForumsManagement;
import allcode.SiteManager;
import allcode.SubForum;
import allcode.UserConnection;
import services.Response;
import services.report;
/**
 *
 * @author aviad elitzur
 */
public class Connection extends UnicastRemoteObject implements ConnectionInterface{
    
	private UserConnection uc;
	private static final long serialVersionUID = 1L;

	public Connection(SiteManager sm) throws RemoteException {
		super();
		this.uc= sm.openNewConnection();
	}

	@Override
	public report login(String userName, String pass) throws RemoteException{
		return this.uc.login(userName, pass);
	}

	@Override
	public report exitForum() throws RemoteException{
		return this.uc.exitForum();
	}

	@Override
	public report logout() throws RemoteException{
		return this.uc.logout();
	}

	@Override
	public report registerToForum(String userName, String password,
			String email, String question, String answer) throws RemoteException{
		return this.uc.registerToForum(userName, password, email,  answer);
	}

	@Override
	public report complain(String moderator, String content) throws RemoteException{
		return this.uc.complain(moderator, content);
	}

	@Override
	public report addModerator(String moderatorName) throws RemoteException{
		return this.uc.addModerator(moderatorName);
	}

	@Override
	public report removeModerator(String adminName, String moderatorName) throws RemoteException{
		return this.uc.removeModerator(adminName,moderatorName);
	}

	@Override
	public report createSubforum(String name, String description) throws RemoteException{
		return this.uc.createSubforum(name,description);
	}

	@Override
	public report createForum(String name, String description) throws RemoteException{
		return this.uc.createForum(name,description);
	}

	@Override
	public report deleteSubForum(String subForumName) throws RemoteException{
		return this.uc.deleteSubForum(subForumName);
	}

	@Override
	public report setFriends(String friendName) throws RemoteException{
		return this.uc.setFriends(friendName);
	}

	@Override
	public Response writePost(String title, String content) throws RemoteException{
		return this.uc.writePost(title,content);
	}

	@Override
	public Response writeResponsePost(String title, String content) throws RemoteException{
		return this.uc.writeResponsePost(title,content);
	}

	@Override
	public report postEdit(String title, String content) throws RemoteException{
		return this.uc.postEdit(title,content);
	}

	@Override
	public report deletePost() throws RemoteException{
		return this.uc.deletePost();
	}

	@Override
	public Response getListOfPostsByMember(String mNickname) throws RemoteException{
		return this.uc.getListOfPostsByMember(mNickname);
	}

	@Override
	public Response getPostNumInSubForum(String subForumName) throws RemoteException{
		return this.uc.getPostNumInSubForum(subForumName);
	}

	@Override
	public Response getListOfModeratorsInSubForum(String subForumName) throws RemoteException{
		return this.uc.getListOfModeratorsInSubForum(subForumName);
	}

	@Override
	public void reset() throws RemoteException{
		this.uc.reset();
	}

	@Override
	public Vector<String> getForums() throws RemoteException{
		Vector<Forum> forums = (Vector<Forum>)uc.getForums().getMe();
		Vector<String> forumsList= new Vector<String>() ; 
		for(int i=0; i<forums.size(); i++){
			forumsList.add(forums.get(i).get_forumName());
		}
		return forumsList;
	}

	@Override
	public Vector<String> getSubForums() throws RemoteException{
		Vector<SubForum> subforums = (Vector<SubForum>)uc.getSubForums().getMe();
		Vector<String> subforumsList= new Vector<String>() ; 
		for(int i=0; i<subforums.size(); i++){
			subforumsList.add(subforums.get(i).getName());
		}
		return subforumsList;
	}
    
    
}
