package allcode;
import java.util.HashMap;
import java.util.Vector;


public class Forum {
	//Fields
	private String _forumName;
	private String _description;
	private ForumPolicy _forumPolicy;
	private Vector<Member> _members;
	private Vector<SubForum> _subForums;
	private Vector<Member> _administrators;

	//constructors
	public Forum(String forumName,String description){
		_forumName=forumName;
		_description=description;
		_forumPolicy = new ForumPolicy();
		_members=new Vector<Member>();
		_subForums=new Vector<SubForum>();
		_administrators=new Vector<Member>();
	}

	public Forum(String forumName,String description,Member admin){
		_forumName=forumName;
		_description=description;
		_forumPolicy = new ForumPolicy();
		_members=new Vector<Member>();
		_subForums=new Vector<SubForum>();
		_administrators=new Vector<Member>();
		insertNewMember(admin);
		_administrators.add(admin);

	}

	//functionality

	public report setFriends(Member user1,Member user2){
			user1.addFriend(user2);
			user2.addFriend(user1);
			return report.OK;	
	}
	public report register(String name,String pass,String email){
		//add fields
		//do delegation to member constructor
		//have to check if this user is already exists
		if(isMember(name)){
			System.out.println("member already exists");
			return report.ALREADY_MEMBER_EXIST;
		}
		else if(isEmail(email)){
			System.out.println("email adress already exists in forum");
			return report.ALREADY_EMAIL_EXIST;
		}
		else{
			Member newMember= new Member(name,pass,email);
			System.out.println("registered, an email will be sent");
			insertNewMember(newMember);

			//wait for confirmation email?
		}

		return report.OK;
	}


	//needs some work - when to return false? - when he is already admin?
	public report addAdminByName(String member){
		if(isMember(member))
			return report.NO_SUCH_USER_NAME;
		Member m=getMember(member);
		_administrators.add(m);
		return report.OK;
	}

	public report addAdmin(Member member) {
		if(isMember(member)){
			_administrators.add(member);
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
		return report.OK;
	}
	/**
	 * validates that the member exists and that his password is correct
	 * @return the member if validated and null otherwise
	 */
	private Member assureMember(String userName,String password) {
		for(int i=0;i<_members.size();i++){
			if(_members.elementAt(i).get_userName().equals(userName) &&
					_members.elementAt(i).get_password().equals(password))
				return _members.elementAt(i);
		}
		return null;
	}

	public Member getMember(String name){
		for(int i=0;i<_members.size();i++){
			if(_members.elementAt(i).get_userName().equals(name))
				return _members.elementAt(i);
		}
		return null;
	}

	/**
	 * check if the member exists
	 * @return true if members exists in the forum
	 */
	public boolean isMember(String userName) {
		for(int i=0;i<_members.size();i++){
			if(_members.elementAt(i).get_userName().equals(userName))
				return true;
		}
		return false;
	}

	public boolean isMember(Member m) {
		if( _members.indexOf(m) == -1)
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
			if(_administrators.elementAt(i).get_userName().equals(userName))
				return true;
		}
		return false;
	}
	public boolean isAdmin(Member m) {
		if(_administrators.indexOf(m)!=-1)
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
			if(_members.elementAt(i).get_email().equals(email))
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
			if(_subForums.elementAt(i).getName().equals(name))
				return true;
		}
		return false;
	}

	public SubForum getSubForum(String name){
		for(int i=0;i<_subForums.size();i++){
			if(_subForums.elementAt(i).getName().equals(name))
				return _subForums.elementAt(i);
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
				ans.append(_subForums.elementAt(i).getName());
				ans.append("\n");
			}
			return ans.toString();
		}

	}

	private void insertNewMember(Member newMember) {
		_members.add(newMember);
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
	
	
	public boolean canDeleteMessage(Member member, Post post, SubForum subforum) {
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

	public Integer getPostNumInSubForum(String subForumName) {
		// TODO Auto-generated method stub
		return null;
	}

	public HashMap <Integer, Post> getListOfPostsByMember(String mNickname) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	
	//getters and setters

	public String get_forumName() {
		return _forumName;
	}

	public void set_forumName(String _forumName) {
		this._forumName = _forumName;
	}

	public Vector<Member> get_members() {
		return _members;
	}

	public void set_members(Vector<Member> _members) {
		this._members = _members;
	}

	public Vector<SubForum> get_subForums() {
		return _subForums;
	}

	public void set_subForums(Vector<SubForum> _subForums) {
		this._subForums = _subForums;
	}

	public Vector<Member> get_administrators() {
		return _administrators;
	}

	public void set_administrators(Vector<Member> _administrators) {
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


