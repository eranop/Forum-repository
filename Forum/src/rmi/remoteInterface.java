package rmi;
import java.rmi.Remote;
import services.Response;
import services.report;

public interface remoteInterface extends Remote{
	public abstract report exitForum();

	public abstract report login(String userName, String pass);

	public abstract report logout();
	
	public abstract report registerToForum(String userName, String password,
			String email, String question, String answer);

	public abstract report complain(String moderator, String content);

	/**
	 * the user must be logged in forum
	 */

	public abstract report addModerator(String moderatorName);

	public abstract report removeModerator(String adminName,
			String moderatorName);

	public abstract report createSubforum(String name, String description);

	public abstract report createForum(String name, String description);

	public abstract report deleteSubForum(String subForumName);

	public abstract report setFriends(String friendName);

	public abstract Response writePost(String title, String content);

	public abstract Response writeResponsePost(String title, String content);

	public abstract report postEdit(String title, String content);

	/**
	 * must stand in the POLICY of the forum
	 */
	public abstract report deletePost();

	/**
	 * Report function for admin user.
	 * admin enters a membername and gets a list of all his posts.
	 * @param mNickname is member's nickname
	 * @return Response(report.OK, HashMap <Integer, Post>)
	 */
	public abstract Response getListOfPostsByMember(String mNickname);

	/**
	 * Report function for admin user.
	 * admin enters a sub forum name and gets the number of posts in a sub forum.
	 * @param subForumName is sub forum's name
	 * @return Response (report.OK, Integer)
	 */
	public abstract Response getPostNumInSubForum(String subForumName);

	/**
	 * Report function for admin user.
	 * admin enters a sub forum name and gets Vector of all his moderatos.
	 * information need to be parsed: List of mods. Who apointed them. When and to which subForum. thier posts.
	 * @param subForumName is sub forum's name
	 * @return Response (report.OK, Vector <Member>) which are moderators. inside theres access for all information needed)
	 */
	public abstract Response getListOfModeratorsInSubForum(String subForumName);

	public abstract void reset();
}