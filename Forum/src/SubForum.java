import java.util.Collection;
import java.util.Vector;
import java.util.HashMap;

public class SubForum {
	
	private int msgCounter;						//will count the messages and arrange thier indexes.
	private String name;						//Sub forum name
	private String description;					//Sub forum description
	private String modPolicy;					//Moderators policy
	private String userPolicy;					//Users policy
	private Forum forum;						//The main forum above this sub.
	private Vector <Member> moderators;			//List of moderatos (maybe should be set so the same user cannot be added twice)
	private HashMap <Integer, Post> allPosts;	//List of all posts
	private HashMap <Integer, Post> rootPosts;	//List of root posts
	private Vector <Complain> complains;		//list of complains (by members on moderators)
	
	@SuppressWarnings("unused")
	private Vector <Member> bannedUsers;		//List of banned member (who cannot enter the forum) NOT IMPLEMENTED YET.
	
	/**
	 * Creating a Sub Forum
	 * 
	 * @param name name of the sub-forum
	 * @param desc description of the sub-forum
	 * @param forum the forum above
	 */
	
	public SubForum (String name, String desc, Forum forum) 
	{
		this.msgCounter = 0;
		this.name = name;
		this.description = desc;
		this.forum = forum;
		this.moderators = new Vector <Member>();
		this.allPosts = new HashMap <Integer, Post>();
		this.rootPosts = new HashMap <Integer, Post>();
		this.complains = new Vector <Complain>();
		this.bannedUsers = new Vector <Member>();
	}
	
	/**
	 * 	/**
	 * Creating a Sub Forum
	 * 
	 * @param name name of the sub-forum
	 * @param desc description of the sub-forum
	 * @param forum the forum above
	 * @param moderator 1 moderator u wanna recruit
	 */
	
	public SubForum (String name, String desc, Forum forum, Member moderator) 
	{
		this.msgCounter = 0;
		this.name = name;
		this.description = desc;
		this.forum = forum;
		this.moderators = new Vector <Member>();
		this.allPosts = new HashMap <Integer, Post>();
		this.rootPosts = new HashMap <Integer, Post>();
		this.complains = new Vector <Complain>();
		if (isMember(moderator))
			moderators.add(moderator);
		this.bannedUsers = new Vector <Member>();
	}
	
	/**
	 * post a respond to an existing Post by its index
	 * 
	 * @param member composer of the respond
	 * @param orgPostIndx the post index u want to replay on
	 * @param title the title of the response
	 * @param content the content of the respone
	 * @return true upon success, false otherwise
	 */
	
	public boolean postRespond(Member member, int orgPostIndx, String title, String content)
	{
		Post originalPost = allPosts.get(orgPostIndx);									//getting the original post we want to respond to by index
		Post newPost = new Post(member, title, content, msgCounter++, originalPost);	//creating the new respond(Post).
		allPosts.put(newPost.getIndex(),  newPost);										//adding the post to allPosts.
		return originalPost.addResponse(newPost.getIndex(), newPost);					//adding it and return true if succeed.
	}

	/**
	 * Show all the root post titles that has been published in the current subForum.
	 */
	
	public void showRootPosts()
	{
		Collection<Post> cl = allPosts.values();
		Object tempposts[] = cl.toArray();
		for (int i = 0; i < tempposts.length; i++)
			if ( ((Post)tempposts[i]).getRoot() == null )
				System.out.println("Post " + ((Post)tempposts[i]).getIndex() + ". " + ((Post)tempposts[i]).getTitle() + ". By: " + ((Post)tempposts[i]).getPublisher());
	}
	
	/**
	 * Show all the post titles that has been published in the current subForum.
	 */
	
	public void showAllPosts()
	{
		Collection<Post> cl = allPosts.values();
		Object tempposts[] = cl.toArray();
		for (int i = 0; i < tempposts.length; i++)
			System.out.println("Post " + ((Post)tempposts[i]).getIndex() + ". " + ((Post)tempposts[i]).getTitle() + ". By: " + ((Post)tempposts[i]).getPublisher());
	}
	
	/**
	 * Show all the complains that has been published by members in the current subForum
	 */
	
	public void showComplains()
	{
		for (int i = 0; i < complains.size(); i++)
			complains.get(i).presentComplain();
	}
	
	/**
	 * getting an index of a post and reveal all sub posts including thier subs and so on.
	 * @param index the index of the original post we want unfold.
	 */
	
	public void unfoldPost(int index)
	{
		Post post = getPost(index);
		if (post != null)
			post.unfold();
		else
			System.out.println("Post number: " + index + " doesnt exist.");
	}
	
	
	/** 
	 * showing on the screen a post
	 * @param index is the number of Post
	 */
	public void readPost(int index)
	{
		Post post = getPost(index);
		if (post != null)
			post.showPost();
		else
			System.out.println("Post number: " + index + " doesnt exist.");
	}
	
	/**
	 * adding post by the following parameters to the posts vector.
	 * 
	 * @param member member who wrote the post 
	 * @param title title opf the of the post
	 * @param content content of the post
	 * @return return true apon success, false otherwise.
	 */
	
	public boolean addPost(Member member, String title, String content)
	{
		Post newPost = new Post(member, title, content, msgCounter++);
		rootPosts.put(newPost.getIndex(), newPost);
		allPosts.put(newPost.getIndex(), newPost);
		if (allPosts.get(newPost.getIndex()) != null)
			return true;
		return false;
	}
	
	/**
	 * deleting a post by the Post class
	 * 
	 * @param post post we want to delete
	 * @return true upon success, false otherwise
	 */
	
	public boolean deletePost(Post post)
	{
		if (post.getRoot() == null)
			rootPosts.remove(post);
		allPosts.remove(post.getIndex());
		if (allPosts.get(post.getIndex()) == null)
				return true;
		return false;
	}
	
	/**
	 * deleting a post by its index (u can see in showPosts())
	 * 
	 * @param index the index of the post that is going to be deleted.
	 * @return true upon success, failure otherwise.
	 */
	
	public boolean deletePost(int index)
	{
		if (rootPosts.get(index) != null && rootPosts.get(index).getRoot() == null)
			rootPosts.remove(index);
		if (allPosts.get(index) != null)
		{
			allPosts.remove(index);
			return true;
		}
		return false;
	}
	
	/**
	 * adding moderator to the subforum
	 * @param member moderator we want to add
	 * @return true upon success, failure otherwise (if member isnt a Member in the forum we'll get false).
	 */

	public boolean addModerator(Member member)
	{
		member.message("you've been added as modarator in bla bla\n");
		return moderators.add(member);
	}
	
	/**
	 * checking if a given member is moderator in the sub-forum
	 * @param member the member we want to check
	 * @return true if he is. false if he isnt.
	 */
	
	public boolean isModerator(Member member)
	{
		return moderators.contains(member);
	}
	
	/**
	 * removing a moderator from the sub-forum
	 * @param member the member we want to remove
	 * @return true upon success. false otherwise
	 */
	
	public boolean removeModerator(Member member)
	{
		if (isModerator(member))
		{
			member.message("Moderator premission has been removed from bla bla\n");
			return moderators.remove(member);
		}
		return false;
	}
	
	/**
	 * adding a complain about a moderator
	 * @param member the writer of the complain
	 * @param moderator the moderator we complain on
	 * @param complain the text of the conplain
	 * @return true upon success, false otherwise.
	 */
	
	public boolean complain(Member member, Member moderator, String complain)
	{
		if (!isModerator(moderator))		//case isnt moderator.
			return false;
		Complain newComplain = new Complain(member, moderator, complain);
		return complains.add(newComplain);
	}
	
	public boolean banUser(Member member)
	{
		return true;
	}
	
	public boolean unBanUser(Member member)
	{
		return true;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getModPolicy() {
		return modPolicy;
	}

	public void setModPolicy(String modPolicy) {
		this.modPolicy = modPolicy;
	}

	public String getUserPolicy() {
		return userPolicy;
	}

	public void setUserPolicy(String userPolicy) {
		this.userPolicy = userPolicy;
	}

	public Forum getForum() {
		return forum;
	}
	
	public HashMap <Integer, Post> getAllPosts() {
		return allPosts;
	}

	public HashMap <Integer, Post> getRootPosts() {
		return rootPosts;
	}
	
	public Vector <Complain> getComplains() {
		return complains;
	}
	
	public Vector <Member> getModeratos(){
		return moderators;
	}
	
	private Post getPost(int index)
	{
		return allPosts.get(index);
	}
	
	
	private boolean isMember(Member moderator)
	{
		return forum.isMember(moderator.get_userName());
	}


}
