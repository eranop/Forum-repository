package connectionRMI;

import services.Response;
import services.report;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Vector;

import allcode.Forum;
import allcode.SubForum;

import java.util.List;

public class RemoteImpl extends UnicastRemoteObject implements RemoteInterface{

	private allcode.UserConnection uc;
	private static final long serialVersionUID = 1L;

	protected RemoteImpl(allcode.UserConnection uc) throws RemoteException {
		super();
		this.uc=uc;
	}

	@Override
	public report login(String userName, String pass) {
		return this.uc.login(userName, pass);
	}


	@Override
	public report logout() {
		return this.uc.logout();
	}

	@Override
	public report registerToForum(String userName, String password,
			String email, String question, String answer) {
		return this.uc.registerToForum(userName, password, email, question,  answer);
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
	public report removeModerator(String moderatorName) {
		return this.uc.removeModerator(moderatorName);
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
	public report postEdit(int index, String title, String content) {
		return this.uc.postEdit(index, title, content);
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
	public Vector<String> getModeratorsList(String subforum)
			throws RemoteException {
		return uc.getModeratorsList(subforum);
	}


	@Override
	public void reset() {
		this.uc.reset();
	}

	@Override
	public Vector<String> getForums(){
		 ArrayList<Forum> forums=(ArrayList<Forum>)this.uc.getForums().getMe();
		 Vector<String> forumsList=new Vector<String>();
		 for(Forum f: forums){
			 forumsList.add(f.get_forumName());
		 }
		 return forumsList;
	}

	@Override
	public Vector<String> getSubForums(String forum) throws RemoteException {
		uc.enterForum(forum);
                Vector<String> forumsList=(Vector<String>)uc.getSubForums().getMe();
		 uc.exitForum();
		 return forumsList;
	}

	@Override
	public report enterForum(String forumName) throws RemoteException {
		return uc.enterForum(forumName);
	}

	@Override
	public report exitForum() {
		return this.uc.exitForum();
	}

	@Override
	public Response enterSubforum(String subforumName) throws RemoteException {
		return uc.enterSubforum(subforumName);
	}

	@Override
	public report exitSubforum() throws RemoteException {
		return uc.exitSubforum();
	}

	@Override
	public Response enterPost(int id) throws RemoteException {
		return uc.enterPost(id);
	}

	@Override
	public report exitPost() throws RemoteException {
		return uc.exitPost();
	}

	@Override
	public Vector<String> getQuestions() throws RemoteException {
		return uc.getQuestions();
	}

	@Override
	public report isValidAnswer(String userName, String answer) throws RemoteException {
		return uc.isValidAnswer(userName, answer);
	}

	@Override
	public Response getPostByIndex(int index) throws RemoteException {
		return uc.getPostByIndex(index);
	}

	@Override
	public report addNewForum(String forumName, String description)
			throws RemoteException {
		return uc.addNewForum(forumName, description);
	}

	@Override
	public report deleteForum(String forumName) throws RemoteException {
		return uc.deleteForum(forumName);
	}

	@Override
	public report addAdminToForum(String name) throws RemoteException {
		return uc.addAdminToForum(name);
	}

	@Override
	public Response getNumberOfForums() throws RemoteException {
		return uc.getNumberOfForums();
	}

	@Override
	public report setSuperAdmin(String superadminName, String superadminPass,
			String email) throws RemoteException {
		return uc.setSuperAdmin(superadminName, superadminPass, email);
	}

	@Override
	public Vector<String> getMembersOfForum(String forum)
			throws RemoteException {
		return uc.getMembersOfForum(forum);
	}

	@Override
	public Vector<String> getAdministratorsVector(String forum)
			throws RemoteException {
		return uc.getAdministratorsVector(forum);
	}

	@Override
	public report deleteAdminByName(String member){
		return uc.deleteAdminByName(member);
	}
	
	public String getMyQuestion(){
		return uc.getMyQuestion();
	}

}