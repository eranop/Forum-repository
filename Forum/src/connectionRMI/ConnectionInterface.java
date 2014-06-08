/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connectionRMI;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

import services.Response;
import services.report;

/**
 *
 * @author aviad elitzur
 */
public interface ConnectionInterface  extends Remote{
	public abstract report exitForum() throws RemoteException;

	public abstract report login(String userName, String pass) throws RemoteException;

	public abstract report logout() throws RemoteException;
	
	public abstract report registerToForum(String userName, String password,
			String email, String question, String answer) throws RemoteException;

	public abstract report complain(String moderator, String content) throws RemoteException;

	/**
	 * the user must be logged in forum
	 */

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

	public abstract Response getListOfPostsByMember(String mNickname) throws RemoteException;

	public abstract Response getPostNumInSubForum(String subForumName) throws RemoteException;

	public abstract Response getListOfModeratorsInSubForum(String subForumName) throws RemoteException;

	public abstract Vector<String> getForums() throws RemoteException;
	
	public abstract Vector<String> getSubForums() throws RemoteException;
	
	public abstract void reset() throws RemoteException;

}
