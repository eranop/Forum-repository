package allcode;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import services.Email;
import services.Response;
import services.report;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.Session;


@Entity
@Table (name="Forums")
public class Forum implements Serializable{
	//Fields
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="forumName")
	private String _forumName;
	
	@Column(name="description")
	private String _description;
	
	@Embedded
	private ForumPolicy _forumPolicy;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)  
	 @JoinTable(name="members_in_forum",
	 joinColumns={@JoinColumn(name="forum")},inverseJoinColumns={@JoinColumn(name="member_id")})
	private List<Member> _members;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)  
	 @JoinTable(name="subForums_Of_Forum",
	 joinColumns={@JoinColumn(name="forum")},inverseJoinColumns={@JoinColumn(name="subForum_id")})
	private List<SubForum> _subForums;
	
	/*@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)  
	 @JoinTable(name="admins_in_forum",
	 joinColumns={@JoinColumn(name="forum")},inverseJoinColumns={@JoinColumn(name="admin_id")})*/
	@Transient
	private List<Member> _administrators;

	//constructors
	public Forum(String forumName,String description){
		_forumName=forumName;
		_description=description;
		_forumPolicy = new ForumPolicy();
		_members=new ArrayList<Member>();
		_subForums=new ArrayList<SubForum>();
		_administrators=new ArrayList<Member>();
	}

	public Forum(String forumName,String description,Member admin){
		_forumName=forumName;
		_description=description;
		_forumPolicy = new ForumPolicy();
		_members=new ArrayList<Member>();
		_subForums=new ArrayList<SubForum>();
		_administrators=new ArrayList<Member>();
		insertNewMember(admin);
		_administrators.add(admin);

	}
	
	public Forum(){
		_administrators=new ArrayList<Member>();
	}

	//functionality

	public report setFriends(Member user1,Member user2){
			user1.addFriend(user2);
			user2.addFriend(user1);
			return report.OK;	
	}
	public report register(String name, String pass, String email, String answer){
		//add fields
		//do delegation to member constructor
		//have to check if this user is already exists
		
		if (name == null || pass == null || email == null || answer == null)
		{
			System.out.println("Error in register: One of the fields is EMPTY!");
			return report.NULL_FIELD;
		}
		else if (name.equals("") || pass.equals("") || email.equals("") || answer.equals(""))
		{
			System.out.println("Error in register: One of the fields is EMPTY!");
			return report.EMPTY_FIELD;
		}
		else if(isMember(name)){
			System.out.println("member already exists");
			return report.ALREADY_MEMBER_EXIST;
		}
		else if(isEmail(email)){
			System.out.println("email adress already exists in forum");
			return report.ALREADY_EMAIL_EXIST;
		}
		else if(!nameStartWithLetter(name))
		{
			System.out.println("Invalid user name: User name must start with a letter.");
			return report.INVALID_USER_NAME;
		}
		else if(nameContainsTags(name))
		{
			System.out.println("Invalid user name: User name cannot contains tags.");
			return report.INVALID_USER_NAME;
		}
		else{
			if(Email.isValidEmail(email)){
			Member newMember= new Member(name, pass, email, answer,this);
			System.out.println("registered, an email will be sent");
			
			Session ss=DataBaseInit.sf.openSession();  
			  ss.beginTransaction();  
			 //saving objects to session  
			  ss.saveOrUpdate(newMember);  
			  ss.getTransaction().commit();
			  ss.close(); 
			  insertNewMember(newMember);
			//wait for confirmation email?
			}
			else{
				return report.INVALID_EMAIL_PATTERN;
			}
		}

		return report.OK;
	}
	
	private boolean nameContainsTags(String name) {
		String[] tags = {"!", "@", "#", "$", "%", "^", "&",  "*", "(", ")", "[", "]", ":", "~", "\\", "{", "}", "+", "|", "<", 
				">", ".", ";", "\"", "`"};
		for (String i : tags) 
			if (name.contains(i))
				return true;
		return false;
	}

	private boolean nameStartWithLetter(String name) 
	{
		char c = name.charAt(0);
		if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'))
			return true;
		return false;
	}


	//needs some work - when to return false? - when he is already admin?
	public report addAdminByName(String member){
		if(!isMember(member))
			return report.NO_SUCH_USER_NAME;
		Member m=getMember(member);
		
		_administrators.add(m);
		Session ss=DataBaseInit.sf.openSession();  
		  ss.beginTransaction();  
		 //saving objects to session  
		  ss.update(this);
		  ss.getTransaction().commit();  
		  ss.close(); 
		return report.OK;
	}

	public report addAdmin(Member member) {
		if(isMember(member)){
			_administrators.add(member);
			Session ss=DataBaseInit.sf.openSession();  
			  ss.beginTransaction();  
			 //saving objects to session  
			  ss.update(this);
			  ss.getTransaction().commit();  
			  ss.close(); 
			return report.OK;
		}
		return report.NO_SUCH_USER_NAME;
	}

	public report createSubForum(String name,String description){
		//add fields
		//delegation to Subforum constructor
		
		
		if(findSubforum(name)){
			System.out.println("sub forum already exists!");
			return report.ALREADY_SUBFORUM_EXIST;
		}
		
		SubForum sub = new SubForum(name, description, this);
		_subForums.add(sub);
		Session ss=DataBaseInit.sf.openSession();  
		  ss.beginTransaction();  
		 //saving objects to session  
		  ss.saveOrUpdate(sub);  
		  ss.update(this);
		  ss.getTransaction().commit();  
		  ss.close(); 
		return report.OK;
	}


	public String viewSubForums(){
		String ans=subForumString();
		//do i need to print??

		return ans;
	}

	//need to understand how to do - part of registration
	public boolean sendIdentificationByEmail(String email, Member member){


		return false;
	}
	public report deleteSubForum(String subForumName, Member admin) {
		if(!isAdmin(admin)){
			System.out.println("not admin!");
			return report.IS_NOT_ADMIN;
		}
		SubForum sf=getSubForum(subForumName);
		if(sf==null){
			System.out.println("no such sub-forum!");
			return report.NO_SUCH_SUBFORUM;
		}
		_subForums.remove(sf);
		Session ss=DataBaseInit.sf.openSession();  
		  ss.beginTransaction();  
		 //saving objects to session  
		  ss.update(sf);
		  ss.update(this);
		  ss.getTransaction().commit();  
		  ss.close(); 
		return report.OK;
	}
	/**
	 * validates that the member exists and that his password is correct
	 * @return the member if validated and null otherwise
	 */
	private Member assureMember(String userName,String password) {
		for(int i=0;i<_members.size();i++){
			if(  _members.get(i).get_userName().equals(userName) &&
					 _members.get(i).get_password().equals(password))
				return  _members.get(i);
		}
		return null;
	}

	public Member getMember(String name){
		for(int i=0;i<_members.size();i++){
			if( _members.get(i).get_userName().equals(name))
				return  _members.get(i);
		}
		return null;
	}

	/**
	 * check if the member exists
	 * @return true if members exists in the forum
	 */
	public boolean isMember(String userName) {
		for(int i=0;i<_members.size();i++){
			if( _members.get(i).get_userName().equals(userName))
				return true;
		}
		return false;
	}

	public boolean isMember(Member m) {
		if(  _members.indexOf(m) == -1)
			return false;
		return true;
	}


	/**
	 * check if the admin exists
	 * @param userName
	 * @return true if members exists in the forum
	 */
	public boolean isAdmin(String userName) {
		for(int i=0;i<_administrators.size();i++){
			if( _administrators.get(i).get_userName().equals(userName))
				return true;
		}
		return false;
	}
	public boolean isAdmin(Member m) {
		if( _administrators.indexOf(m)!=-1)
			return true;
		return false;
	}
	/**
	 * check if the email adress exists in the forum
	 * @param email
	 * @return true if exists
	 */
	private boolean isEmail(String email) {
		for(int i=0;i<_members.size();i++){
			if( _members.get(i).get_email().equals(email))
				return true;
		}
		return false;
	}

	/**
	 * check if the subforum exists in the forum
	 * @param name
	 * @return true if subforum exists
	 */
	private boolean findSubforum(String name) {
		for(int i=0;i<_subForums.size();i++){
			
			if( _subForums.get(i).getName().equals(name))
				
				return true;
		}
		return false;
	}

	public SubForum getSubForum(String name){
		for(int i=0;i<_subForums.size();i++){
			if( _subForums.get(i).getName().equals(name))
				return ( _subForums.get(i));
		}
		return null;
	}

	/**
	 * string representation of the sub forums
	 * @return
	 */
	private String subForumString() {
		if (_subForums.isEmpty())
			return "no sub-forums in this forum!";
		else{
			StringBuilder ans=new StringBuilder();
			for(int i=0;i<_subForums.size();i++){
				ans.append( _subForums.get(i).getName());
				ans.append("\n");
			}
			return ans.toString();
		}

	}

	private void insertNewMember(Member newMember) {
		_members.add(newMember);
		Session ss=DataBaseInit.sf.openSession();  
		  ss.beginTransaction();  
		 //saving objects to session  
		  ss.update(this);  
		  ss.getTransaction().commit();  
		  ss.close(); 
	}

	public void notifyNewMsgToMembers(Member member, String title, SubForum subforum)
	{
		for (int i = 0; i < this.get_members().size(); i++)
			this.get_members().get(i).message(
					"A new post has been added in: " + subforum.getName() + 
					". Title: " + title + ". Posted by: " + member.get_userName() + 
					". In: " + DateManagment.dateFormat.format(DateManagment.getDate()));
	}
	
	public void notifyResponders(Member member, String title, SubForum subforum, Post post)
	{
		for (int i = 0; i < post.getResponds().size(); i++)
			post.getResponds().get(i).getMember().message(
					"a former post that you've replaied has changed in: " + subforum.getName() + 
					". New title is: " + post.getTitle() + ". Posted by: " + post.getPublisher() + 
					". Changed in " + DateManagment.dateFormat.format(DateManagment.getDate()));
	}
	
	
	public boolean canDeletePost(Member member, Post post, SubForum subforum) {
		if ( (_forumPolicy.isDeleteMessagePublisher() && member.equals(post.getMember()))		//if member is owner and policy allows
				|| (_forumPolicy.isDeleteMessageAdmin() && this.isAdmin(member))				//if member is admin and policy allows
				|| (_forumPolicy.isDeleteMessageModerator() && subforum.isModerator(member)))	//if member is moder and policy allows
			return true;
		return false;
	}
	
	public boolean canAddModerator(Member member) {
		if ( (_forumPolicy.getModeratorDays() <= DateManagment.getDateDiffDays(member.get_regDate(), DateManagment.getDate()))	//if this date - registration date in days > policy days
			&& (_forumPolicy.getModeratorPosts() <= member.getPosts().size()) )	//if member published enuf posts.
			return true;
		return false;
	}
	
	public boolean canRemoveModerator(Member member, Member admin, SubForum subForum) {
		if ( ((_forumPolicy.isDeleteModeratorOnlyByRankingAdmin() && admin.equals(member.getPromoter()) )	//only promoter admin can delete mod and admin is the promoter
			||	(!_forumPolicy.isDeleteModeratorOnlyByRankingAdmin()))	//if all admins can delete the mod
			&& (_forumPolicy.isDeleteLastModerator()					//if can delete last mod
					|| (!_forumPolicy.isDeleteLastModerator() && subForum.getModerators().size() > 1)))	//if canNOT delete last mod but num of mods > 1
			return true;
		return false;
	}
	
	public Response getPostNumInSubForum(String subForumName) {
		if (this.findSubforum(subForumName))
			return new Response(report.OK, this.getSubForum(subForumName).getAllPosts().size());
		else
			return new Response(report.NO_SUCH_SUBFORUM);
	}

	public Response getListOfPostsByMember(String mNickname) {
		if (this.isMember(mNickname))
			return new Response(report.OK, this.getMember(mNickname).getPosts());
		else
			return new Response(report.NO_SUCH_USER_NAME);
	}

	public Response getListOfModeratorsInSubForum(String subForumName) {
		if (this.findSubforum(subForumName))
			return new Response(report.OK, this.getSubForum(subForumName).getModerators());
		else
			return new Response(report.NO_SUCH_SUBFORUM);
	}
	
	public report addMember(Member member)
	{
		if (_members.contains(member))
			return report.MEMBER_ALREADY_IN_FORUM;
		else
		{
			_members.add(member);
			Session ss=DataBaseInit.sf.openSession();  
			  ss.beginTransaction();  
			 //saving objects to session  
			  ss.update(this);  
			  ss.getTransaction().commit();  
			  ss.close(); 
			return report.OK;
		}
	}

	//getters and setters

	public String get_forumName() {
		return _forumName;
	}

	public void set_forumName(String _forumName) {
		this._forumName = _forumName;
	}

	public List<Member> get_members() {
		return  _members;
	}

	public void set_members(Vector<Member> _members) {
		this._members = _members;
	}

	public Vector<String> get_subForums() {
		Vector<String> subforumsList=new Vector<String>();
		for(SubForum f: _subForums){
			 subforumsList.add(f.getName());
		 }
		
		return  subforumsList;
	}

	public void set_subForums(ArrayList<SubForum> _subForums) {
		this._subForums = _subForums;
	}

	public List<Member> get_administrators() {
		return  _administrators;
	}

	public void set_administrators(ArrayList<Member> _administrators) {
		this._administrators = _administrators;
	}
	public String get_description() {
		return _description;
	}
	public void set_description(String _description) {
		this._description = _description;
	}

	public String toString(){
		return _forumName;
	}

	public ForumPolicy get_forumPolicy() {
		return _forumPolicy;
	}

	public void set_forumPolicy(ForumPolicy _forumPolicy) {
		this._forumPolicy = _forumPolicy;
	}







}
/*
public boolean postInSubForum(String subName,String user,String title,String content){
	SubForum subforum=getSubForum(subName);
	if(subforum==null){
		System.out.println("no such sub-forum");
		return false;
	}
	Member member=getMember(user);
	if(member==null){
		System.out.println("no such member!");
		return false;
	}
	subforum.addPost(member, title, content);
	return true;
}

public boolean postResponseInSubForum(String subForumName,String user,
		String title,String content,int postToResponseID){

	SubForum subforum=getSubForum(subForumName);
	if(subforum==null){
		System.out.println("no such sub-forum");
		return false;
	}
	Member member=getMember(user);
	if(member==null){
		System.out.println("no such member!");
		return false;
	}
	subforum.postRespond(member,postToResponseID,title,content);
	return true;

}

public boolean deleteFromSubForum(String subName,int postID){
	SubForum subforum=getSubForum(subName);
	if(subforum==null){
		System.out.println("no such sub-forum");
		return false;
	}
	return subforum.deletePost(postID);
}





public HashMap<Integer,Post> showPostInSubForum(String subForumName) {
	SubForum subForum=getSubForum(subForumName);
	if(subForum==null){
		System.out.println("no such subForum");
		return null;
	}
	return subForum.getRootPosts();
}

public boolean addModerator(String subForumName, String adminName,
		String moderatorName) {
	if(!isAdmin(adminName)){
		System.out.println("not admin!");
		return false;
	}
	SubForum subForum=getSubForum(subForumName);
	if(subForum==null){
		System.out.println("no such sub-forum!");
		return false;
	}
	Member moderator=getMember(moderatorName);
	if(moderator==null){
		System.out.println("moderator is not a member!");
		return false;
	}
	return subForum.addModerator(moderator);
	
}
*/


