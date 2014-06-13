package connectionRMI;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Vector;

import allcode.Forum;
import allcode.Password;
import allcode.Post;
import allcode.SubForum;
import services.Response;
import services.report;

public interface RemoteInterface extends Remote{
		public abstract report login(String userName, String pass) throws RemoteException;

	public abstract report logout()throws RemoteException;

	public abstract report registerToForum(String userName, String password,
			String email, String question, String answer) throws RemoteException;

	public abstract report complain(String moderator, String content) throws RemoteException;

	public abstract report addModerator(String moderatorName) throws RemoteException;

	public abstract report removeModerator(String adminName,
			String moderatorName) throws RemoteException;

	public abstract report createSubforum(String name, String description) throws RemoteException;

	public abstract report createForum(String name, String description) throws RemoteException;

	public abstract report deleteSubForum(String subForumName) throws RemoteException;

	public abstract report setFriends(String friendName) throws RemoteException;

	public abstract Response writePost(String title, String content) throws RemoteException;

	public abstract Response writeResponsePost(String title, String content) throws RemoteException;

	public abstract report postEdit(String title, String content) throws RemoteException;

	public abstract report deletePost() throws RemoteException;
	
	public abstract Vector<String> getForums() throws RemoteException;

	public abstract Vector<String> getSubForums(String forum) throws RemoteException;

	public abstract Response getListOfPostsByMember(String mNickname) throws RemoteException;

	public abstract Response getPostNumInSubForum(String subForumName) throws RemoteException;

	public abstract Response getListOfModeratorsInSubForum(String subForumName) throws RemoteException;

	public abstract void reset() throws RemoteException;

	public abstract Vector<String> getQuestions() throws RemoteException;
	
	public abstract report isValidAnswer(String userName, String answer) throws RemoteException;
	
	public abstract report enterForum(String forumName) throws RemoteException;
	public abstract report exitForum() throws RemoteException;
	public abstract Response enterSubforum(String subforumName) throws RemoteException;
	public abstract report exitSubforum() throws RemoteException;
	public abstract report enterPost(int id) throws RemoteException;
	public abstract report exitPost() throws RemoteException;



}