package allcode;
import java.util.Collection;
import java.util.Set;
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
	 * Creating a Sub Forum with moderator 
	 */
	public SubForum (String name, String desc, Member moderator) 
	{
		this.msgCounter = 0;
		this.name = name;
		this.description = desc;
		//this.forum = forum;
		
		this.moderators = new Vector <Member>();
		this.allPosts = new HashMap <Integer, Post>();
		this.rootPosts = new HashMap <Integer, Post>();
		this.complains = new Vector <Complain>();
		this.moderators.add(moderator);
		this.bannedUsers = new Vector <Member>();
	}
	
	/**
	 * adding post by the following parameters to the posts vector.
	 */
	public report addPost(Member member, String title, String content)
	{
		if(member==null || title==null || content==null){
			return report.NULL_ARGUMENTS;
		}
		Post newPost = new Post(member, title, content, msgCounter++);
		rootPosts.put(newPost.getIndex(), newPost);
		allPosts.put(newPost.getIndex(), newPost);
		//if (allPosts.get(newPost.getIndex()) != null)
			//return true;
		//return false;
		return report.OK;
	}
	/**
	 *  return post by its index
	 */
	public Post getPostByIndex(int index){
		return allPosts.get(index);
	}
	/**
	 * post a respond to an existing Post by its index
	 * 
	 * @param member composer of the respond
	 * @param orgPostIndx the post index u want to replay on
	 */
	public report postRespond(Member member, int orgPostIndx, String title, String content)
	{
		if(member==null || title==null || content==null){
			return report.NULL_ARGUMENTS;
		}
		Post originalPost = allPosts.get(orgPostIndx);									//getting the original post we want to respond to by index
		if(originalPost==null){
			return report.NO_SUCH_POST;
		}
		Post newPost = new Post(member, title, content, msgCounter++, originalPost);	//creating the new respond(Post).
		allPosts.put(newPost.getIndex(),  newPost);										//adding the post to allPosts.
		originalPost.addResponse(newPost.getIndex(), newPost);					//adding it and return true if succeed.
		return report.OK;
	}

	/**
	 * deleting a post from subforum
	 */
	public report deletePost(Post post)
	{
		if (post == null)
			return report.NO_POST;
		Post p=allPosts.remove(post.getIndex());
		if(p==null)
			return report.NO_SUCH_POST;
		
		//delete response posts (not recursive)
		Set<Integer> keys= p.getResponsesIndex();
		for(Integer i: keys){
			allPosts.remove(i);
		}
		//
		if (post.getRoot() == null) // a root post
			rootPosts.remove(post);
		return report.OK;
	}
	
	/**
	 * deleting a post by its index 
	 */
	public report deletePost(int index)
	{
		Post p=getPostByIndex(index);
		return deletePost(p);
	}

	
	/**
	 * adding moderator to the subforum
	 * @param member moderator we want to add
	 */
	public report addModerator(Member member)
	{
		member.message("you've been added as modarator in bla bla\n");
		moderators.add(member);
		return report.OK;
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
	
	public report complain(Member member, Member moderator, String complain)
	{
		if (!isModerator(moderator))		//case isnt moderator.
			return report.IS_NOT_MODERATOR;
		Complain newComplain = new Complain(member, moderator, complain);
		complains.add(newComplain);
		return report.OK;
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
	
	/*
	private boolean isMember(Member moderator)
	{
		return forum.isMember(moderator.get_userName());
	}
*/

}
