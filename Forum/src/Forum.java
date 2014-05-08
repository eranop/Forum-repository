import java.util.HashMap;
import java.util.Vector;


public class Forum {


	//Fields

	private String _forumName;
	private String _description;
	private Vector<Member> _members;
	private Vector<SubForum> _subForums;
	private Vector<Member> _administrators;


	//constructors

	public Forum(String forumName,String description){
		_forumName=forumName;
		_description=description;
		_members=new Vector<Member>();
		_subForums=new Vector<SubForum>();
		_administrators=new Vector<Member>();

	}


	public Forum(String forumName,String description,Member admin){
		_forumName=forumName;
		_description=description;
		_members=new Vector<Member>();
		_subForums=new Vector<SubForum>();
		_administrators=new Vector<Member>();
		insertNewMember(admin);
		_administrators.add(admin);

	}

	//functionality


	public boolean setFriends(String user1,String user2){
		if(!isMember(user1) || !isMember(user2)){
			System.out.println("member doesnt exist!");
			return false;
		}
		else{
			Member member1=getMember(user1);
			Member member2=getMember(user2);
			member1.addFriend(member2);
			member2.addFriend(member1);
			return true;
		}
	}


	public Member login(String userName, String password){


		Member ans= assureMember(userName,password);
		if(ans!=null){
			System.out.println("successful login!");

			return ans;
		}
		System.out.println("wrong username");

		return null;
	}




	public boolean complainInSubForum(String subForumName,String user,
			String moderator, String content){
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
		Member moderatorMember=getMember(moderator);
		if(moderatorMember==null){
			System.out.println("no such member!");
			return false;
		}
		subforum.complain(member, moderatorMember, content);
		return true;
	}

	public report register(String name,String pass,String email){
		//add fields
		//do delegation to member constructor
		//have to check if this user is already exists
		if(isMember(name)){
			System.out.println("member already exists");
			return report.ALL_READY_MEMBER_EXIST;
		}
		else if(isEmail(email)){
			System.out.println("email adress already exists in forum");
			return report.ALL_READY_EMAIL_EXIST;
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
	public boolean setAdmin(Member member){

		if(!isMember(member.get_userName()))
			insertNewMember(member);
		if(!isAdmin(member.get_userName())){
			_administrators.add(member);
			return true;
		}
		return false;
	}



	public boolean createSubForum(String name,String description,String admin){
		//add fields
		//delegation to Subforum constructor
		if(findSubforum(name)){
			System.out.println("sub forum already exists!");
			return false;
		}
		if(!isAdmin(admin)){
			System.out.println("you are not an asministrator!");
			return false;
		}
		SubForum sub=new SubForum(name, description,this);
		_subForums.add(sub);

		return true;

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


	public boolean deleteSubForum(String subForumName, String adminName) {
		if(!isAdmin(adminName)){
			System.out.println("not admin!");
			return false;
		}
		if(getSubForum(subForumName)==null){
			System.out.println("no such sub-forum!");
			return false;
		}
		for(int i=0;i<_subForums.size();i++){
			if(_subForums.elementAt(i).getName().equals(subForumName)){
				_subForums.remove(i);
				return true;
			}
		}
		return false;
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


	//helper functions

	/**
	 * validates that the member exists and that his password is correct
	 * @param userName
	 * @param password
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

	public boolean addAdmin(Member member) {
		if(isMember(member)){
			_administrators.add(member);
			return true;
		}
		return false;
	}

	/**
	 * check if the member exists
	 * @param userName
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


	








}
