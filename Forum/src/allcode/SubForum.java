package allcode;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.HashMap;

import services.Complain;
import services.Response;
import services.report;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.MapKeyClass;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.Session;

@Entity

@Table(name="subForum")
public class SubForum implements Serializable{
	
	@Id
	@GeneratedValue
	@Column(name="subForum_id")
	private int subForumID;
	
	
	@Column (name="message_counter")
	private int _msgCounter;					//will count the messages and arrange thier indexes.
	
	
	@Column(name="subForum_name")
	private String _name;						//Sub forum name
	
	@Column(name="description")
	private String _description;				//Sub forum description
	
	@Embedded
	private ForumPolicy _forumPolicy;			//Forum policy
	
	@Column (name="users_policy")
	private String _userPolicy;					//Users policy
	
	
	@OneToOne
	@JoinColumn(name="father_forum")
	private Forum _forum;						//The main forum above this sub.
	
	@OneToMany(fetch=FetchType.EAGER)
	@JoinTable 
	(
			name="subForum_moderators",
			joinColumns={ @JoinColumn(name="subForum_id")},
			inverseJoinColumns = { @JoinColumn(name="member_id")}
			
	)
	private List <Member> _moderators;		//List of moderatos (maybe should be set so the same user cannot be added twice)
	
	@ElementCollection(fetch=FetchType.EAGER)
	  @MapKeyColumn(name="_index")
	@Column(name="post")
	@CollectionTable(name="all_posts",joinColumns={@JoinColumn(name="subForum_index")})
	
	private Map <Integer, Post> _allPosts;	//List of all posts
	
	@ElementCollection(fetch=FetchType.EAGER)
	  @MapKeyColumn(name="_index")
	@Column(name="post")
	@CollectionTable(name="root_posts",joinColumns={@JoinColumn(name="subForum_index")})
	private Map <Integer, Post> _rootPosts;	//List of root posts
	
	@Transient //to implement
	private List <Complain> _complains;		//list of complains (by members on moderators)
	
	@SuppressWarnings("unused")
	@Transient
	private List <Member> bannedUsers;		//List of banned member (who cannot enter the forum) NOT IMPLEMENTED YET.
	
	public SubForum (String name, String desc, Forum forum) 
	{
		this._msgCounter = 0;
		this._name = name;
		this._description = desc;
		this.set_forum(forum);
		this._forumPolicy = forum.get_forumPolicy();
		this._moderators = new ArrayList <Member>();
		this._allPosts = new HashMap <Integer, Post>();
		this._rootPosts = new HashMap <Integer, Post>();
		this._complains = new ArrayList <Complain>();
		this.bannedUsers = new ArrayList <Member>();
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
		this._moderators = new ArrayList <Member>();
		this._allPosts = new HashMap <Integer, Post>();
		this._rootPosts = new HashMap <Integer, Post>();
		this._complains = new ArrayList <Complain>();
		this._moderators.add(moderator);
		this.bannedUsers = new ArrayList <Member>();
	}
	
	public SubForum(){
		
	}
	
	/**
	 * adding post by the following parameters to the posts vector.
	 */
	public Response addPost(Member member, String title, String content)
	{
		if(member==null || title==null || content==null){
			return new Response(report.NULL_ARGUMENTS);
		}
		if (title.equals("") || content.equals(""))
				return new Response(report.EMPTY_FIELD);
		Post newPost = new Post(member, title, content, _msgCounter++);
		_rootPosts.put(newPost.getIndex(), newPost);
		_allPosts.put(newPost.getIndex(), newPost);
		//if (allPosts.get(newPost.getIndex()) != null)
			//return true;
		//return false;
		
		Session ss=DataBaseInit.sf.openSession();  
		  ss.beginTransaction();  
		 //saving objects to session  
		ss.saveOrUpdate(newPost);
		member.addPost(_msgCounter, newPost);
		  ss.update(this);
		  ss.update(member);
		  ss.getTransaction().commit();  
		  ss.close(); 
		return new Response(report.OK, newPost);
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
	public Response postRespond(Member member, int orgPostIndx, String title, String content)
	{
		if(member==null || title==null || content==null){
			return new Response(report.NULL_ARGUMENTS);
		}
		Post originalPost = _allPosts.get(orgPostIndx);									//getting the original post we want to respond to by index
		if(originalPost==null){
			return new Response(report.NO_SUCH_POST);
		}
		Post newPost = new Post(member, title, content, _msgCounter++, originalPost);	//creating the new respond(Post).
		_allPosts.put(newPost.getIndex(),  newPost);										//adding the post to allPosts.
		originalPost.addResponse(newPost.getIndex(), newPost);					//adding it and return true if succeed.
		member.addPost(_msgCounter, newPost);
		Session ss=DataBaseInit.sf.openSession();  
		  ss.beginTransaction();  
		 //saving objects to session  
		  ss.saveOrUpdate(newPost);
		  ss.update(this);
		  ss.update(originalPost);
		  ss.update(member);
		  ss.getTransaction().commit();  
		  ss.close(); 
		return new Response(report.OK, newPost);
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
		Session ss=DataBaseInit.sf.openSession();  
		  ss.beginTransaction();  
		 //saving objects to session  
		  ss.saveOrUpdate(post);  
		  ss.getTransaction().commit();  
		  ss.close(); 
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
			_rootPosts.remove(post.getIndex());
		Session ss=DataBaseInit.sf.openSession();  
		  ss.beginTransaction();  
		 //saving objects to session  
		  ss.saveOrUpdate(p);  
		  ss.getTransaction().commit();  
		  ss.close(); 
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
<<<<<<< HEAD
		member.message("you've been added as modarator in " + this.getName());
		_moderators.add(member);
		member.setPromoter(promoter);
		Session ss=DataBaseInit.sf.openSession();  
		  ss.beginTransaction();  
		 //saving objects to session  
		  ss.saveOrUpdate(this);  
		  ss.getTransaction().commit();  
		  ss.close(); 
		return report.OK;
=======
		if(_forum.isAdmin(promoter)){
			member.message("you've been added as modarator in " + this.getName());
			_moderators.add(member);
			member.setPromoter(promoter);
			return report.OK;
		}
		else return report.NOT_ALLOWED;
>>>>>>> refs/heads/version3.6
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
				Session ss=DataBaseInit.sf.openSession();  
				  ss.beginTransaction();  
				 //saving objects to session  
				  ss.saveOrUpdate(this);  
				  ss.getTransaction().commit();  
				  ss.close(); 
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
		Session ss=DataBaseInit.sf.openSession();  
		  ss.beginTransaction();  
		 //saving objects to session  
		  ss.saveOrUpdate(newComplain);  
		  ss.update(this);
		  ss.getTransaction().commit();  
		  ss.close(); 
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
	public Map <Integer, Post> getAllPosts() {
		return _allPosts;
	}

	public Map <Integer, Post> getRootPosts() {
		return _rootPosts;
	}
	
	public List <Complain> getComplains() {
		return _complains;
	}
	
	public List <Member> getModerators(){
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
