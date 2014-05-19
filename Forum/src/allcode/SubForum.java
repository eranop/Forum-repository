package allcode;
import java.util.Collection;
import java.util.Set;
import java.util.Vector;
import java.util.HashMap;

import services.Complain;
import services.report;

public class SubForum {
	
	private int _msgCounter;					//will count the messages and arrange thier indexes.
	private String _name;						//Sub forum name
	private String _description;				//Sub forum description
	private ForumPolicy _forumPolicy;			//Moderators policy
	private String _userPolicy;					//Users policy
	private Forum _forum;						//The main forum above this sub.
	private Vector <Member> _moderators;		//List of moderatos (maybe should be set so the same user cannot be added twice)
	private HashMap <Integer, Post> _allPosts;	//List of all posts
	private HashMap <Integer, Post> _rootPosts;	//List of root posts
	private Vector <Complain> _complains;		//list of complains (by members on moderators)
	
	@SuppressWarnings("unused")
	private Vector <Member> bannedUsers;		//List of banned member (who cannot enter the forum) NOT IMPLEMENTED YET.
	
	public SubForum (String name, String desc, Forum forum) 
	{
		this._msgCounter = 0;
		this._name = name;
		this._description = desc;
		this.set_forum(forum);
		this._forumPolicy = forum.get_forumPolicy();
		this._moderators = new Vector <Member>();
		this._allPosts = new HashMap <Integer, Post>();
		this._rootPosts = new HashMap <Integer, Post>();
		this._complains = new Vector <Complain>();
		this.bannedUsers = new Vector <Member>();
	}
	
	/**
	 * Creating a Sub Forum with moderator 
	 */
	public SubForum (String name, String desc, Forum forum, Member moderator) 
	{
		this._msgCounter = 0;
		this._name = name;
		this._description = desc;
		this.set_forum(forum);
		this._forumPolicy = forum.get_forumPolicy();
		this._moderators = new Vector <Member>();
		this._allPosts = new HashMap <Integer, Post>();
		this._rootPosts = new HashMap <Integer, Post>();
		this._complains = new Vector <Complain>();
		this._moderators.add(moderator);
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
		Post newPost = new Post(member, title, content, _msgCounter++);
		_rootPosts.put(newPost.getIndex(), newPost);
		_allPosts.put(newPost.getIndex(), newPost);
		//if (allPosts.get(newPost.getIndex()) != null)
			//return true;
		//return false;
		member.addPost(_msgCounter, newPost);
		return report.OK;
	}
	/**
	 *  return post by its index
	 */
	public Post getPostByIndex(int index){
		return _allPosts.get(index);
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
		Post originalPost = _allPosts.get(orgPostIndx);									//getting the original post we want to respond to by index
		if(originalPost==null){
			return report.NO_SUCH_POST;
		}
		Post newPost = new Post(member, title, content, _msgCounter++, originalPost);	//creating the new respond(Post).
		_allPosts.put(newPost.getIndex(),  newPost);										//adding the post to allPosts.
		originalPost.addResponse(newPost.getIndex(), newPost);					//adding it and return true if succeed.
		member.addPost(_msgCounter, newPost);
		return report.OK;
	}

	public report postEdit(Member member, int orgPostIndx, String title, String content)
	{
		if(member==null || title==null || content==null)
			return report.NULL_ARGUMENTS;
		
		Post post = _allPosts.get(orgPostIndx);									//getting the original post we want to respond to by index
		if(post == null)
			return report.NO_SUCH_POST;
		if (!post.getMember().equals(member))
			return report.NOT_POST_OWNER;
		
		post.setTitle(title);
		post.setContent(content);
		return report.OK;
	}
	
	
	/**
	 * deleting a post from sub forum and all its responses if have any
	 * @return report.OK if succeed
	 * @return report.NO_POST if got null argument
	 * @return report.NO_SUCH_POST if there's not such post in the sub forum
	 */
	public report deletePost(Post post)
	{
		if (post == null)
			return report.NO_POST;
		Post p=_allPosts.remove(post.getIndex());
		if(p==null)
			return report.NO_SUCH_POST;
		
		//delete response posts (not recursive)
		Set<Integer> keys= p.getResponsesIndex();
		for(Integer i: keys){
			_allPosts.remove(i);
		}
		//
		if (post.getRoot() == null) // a root post
			_rootPosts.remove(post);
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
	 * adding moderator to the sub forum
	 * @param member moderator we want to add
	 */
	public report addModerator(Member member, Member promoter)
	{
		member.message("you've been added as modarator in " + this.getName());
		_moderators.add(member);
		member.setPromoter(promoter);
		return report.OK;
	}

	
	/**
	 * Show all the root post titles that has been published in the current subForum.
	 */
	public void showRootPosts()
	{
		Collection<Post> cl = _allPosts.values();
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
		Collection<Post> cl = _allPosts.values();
		Object tempposts[] = cl.toArray();
		for (int i = 0; i < tempposts.length; i++)
			System.out.println("Post " + ((Post)tempposts[i]).getIndex() + ". " + ((Post)tempposts[i]).getTitle() + ". By: " + ((Post)tempposts[i]).getPublisher());
	}
	
	/**
	 * Show all the complains that has been published by members in the current subForum
	 */
	
	public void showComplains()
	{
		for (int i = 0; i < _complains.size(); i++)
			_complains.get(i).presentComplain();
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
		return _moderators.contains(member);
	}
	
	/**
	 * removing a moderator from the sub-forum
	 * @param member the member we want to remove
	 * @return true upon success. false otherwise
	 */
	
	public report removeModerator(Member member)
	{
		if (isModerator(member))
			if (_moderators.remove(member))
			{
				member.message("Moderator premission has been removed from " + this.getName());
				return report.OK;
			}
		return report.IS_NOT_MODERATOR;
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
		if (!isModerator(moderator))		//case isn't moderator.
			return report.IS_NOT_MODERATOR;
		Complain newComplain = new Complain(member, moderator, complain);
		_complains.add(newComplain);
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
		return _name;
	}

	public void setName(String name) {
		this._name = name;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		this._description = description;
	}

	public String getUserPolicy() {
		return _userPolicy;
	}

	public void setUserPolicy(String userPolicy) {
		this._userPolicy = userPolicy;
	}
	public HashMap <Integer, Post> getAllPosts() {
		return _allPosts;
	}

	public HashMap <Integer, Post> getRootPosts() {
		return _rootPosts;
	}
	
	public Vector <Complain> getComplains() {
		return _complains;
	}
	
	public Vector <Member> getModerators(){
		return _moderators;
	}
	
	private Post getPost(int index)
	{
		return _allPosts.get(index);
	}

	public ForumPolicy getForumPolicy() {
		return _forumPolicy;
	}

	public void setForumPolicy(ForumPolicy forumPolicy) {
		this._forumPolicy = forumPolicy;
	}

	public Forum get_forum() {
		return _forum;
	}

	public void set_forum(Forum _forum) {
		this._forum = _forum;
	}
	
	/*
	private boolean isMember(Member moderator)
	{
		return forum.isMember(moderator.get_userName());
	}

	
	public void notifyNewMsgToMembers(Post post)
	{
		for (int i = 0; i < _forum.get_members().size(); i++)
			_forum.get_members().get(i).message(
					"A new post has been added in: " + this.getName() + 
					". Title: " + post.getTitle() + ". Posted by: " + post.getPublisher() + 
					". In: " + DateManagment.dateFormat.format(DateManagment.getDate()));
	}
	
	public void notifyResponders(Post post)
	{
		for (int i = 0; i < post.getResponds().size(); i++)
			post.getResponds().get(i).getMember().message(
					"a former post that you've replaied has changed in: " + this.getName() + 
					". New title is: " + post.getTitle() + ". Posted by: " + post.getPublisher() + 
					". Changed in " + DateManagment.dateFormat.format(DateManagment.getDate()));
	}
	
	*/

}
