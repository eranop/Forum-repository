package rmi;
import rmi.remoteInterface;
import services.Response;
import services.report;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteImpl extends UnicastRemoteObject implements remoteInterface{
	
	private allcode.UserConnection uc;
	private static final long serialVersionUID = 1L;

	protected RemoteImpl(allcode.UserConnection uc) throws RemoteException {
		super();
		this.uc=uc;
	}

	@Override
	public report login(String userName, String pass){
		return this.uc.login(userName, pass);
	}

	@Override
	public report exitForum() {
		return this.uc.exitForum();
	}

	@Override
	public report logout() {
		return this.uc.logout();
	}

	@Override
	public report registerToForum(String userName, String password,
			String email, String question, String answer) {
		return this.uc.registerToForum(userName, password, email,  answer);
	}

	@Override
	public report complain(String moderator, String content) {
		return this.uc.complain(moderator, content);
	}

	@Override
	public report addModerator(String moderatorName) {
		return this.uc.addModerator(moderatorName);
	}

	@Override
	public report removeModerator(String adminName, String moderatorName) {
		return this.uc.removeModerator(adminName,moderatorName);
	}

	@Override
	public report createSubforum(String name, String description) {
		return this.uc.createSubforum(name,description);
	}

	@Override
	public report createForum(String name, String description) {
		return this.uc.createForum(name,description);
	}

	@Override
	public report deleteSubForum(String subForumName) {
		return this.uc.deleteSubForum(subForumName);
	}

	@Override
	public report setFriends(String friendName) {
		return this.uc.setFriends(friendName);
	}

	@Override
	public Response writePost(String title, String content) {
		return this.uc.writePost(title,content);
	}

	@Override
	public Response writeResponsePost(String title, String content) {
		return this.uc.writeResponsePost(title,content);
	}

	@Override
	public report postEdit(String title, String content) {
		return this.uc.postEdit(title,content);
	}

	@Override
	public report deletePost() {
		return this.uc.deletePost();
	}

	@Override
	public Response getListOfPostsByMember(String mNickname) {
		return this.uc.getListOfPostsByMember(mNickname);
	}

	@Override
	public Response getPostNumInSubForum(String subForumName) {
		return this.uc.getPostNumInSubForum(subForumName);
	}

	@Override
	public Response getListOfModeratorsInSubForum(String subForumName) {
		return this.uc.getListOfModeratorsInSubForum(subForumName);
	}

	@Override
	public void reset() {
		this.uc.reset();
	}
	
}