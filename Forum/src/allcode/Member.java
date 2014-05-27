package allcode;
import java.util.Date;
import java.util.Vector;
import java.util.HashMap;

import services.InnerMessage;
import services.report;
import services.Email;
import org.apache.commons.lang3.RandomStringUtils;

public class Member {

	private Date _regDate;

	private String _userName;
	private Password _password;
	private Email _email;
	private String _verificationCode;
	
	private Member promoter;
	private MemberType _type;
	private Vector <Member> _friends;
	private Vector <Password> _oldPasswords;
	private HashMap <Integer, Post> _posts;
	private HashMap <Integer, InnerMessage> _messages;
	private int _msgCounter;
	
	public Member(String userName, String password, String email) {

		_regDate = DateManagment.getDate();
		this._userName = userName;
		this._password = new Password(password);
		this._email = new Email(email);
		_friends = new Vector <Member>();
		_messages = new HashMap <Integer, InnerMessage>();
		_posts = new HashMap <Integer, Post> ();
		promoter = null;
		_msgCounter = 0;
		_verificationCode=null;
	}

	public Member(String userName, String password, String email, String question, String answer) {

		_regDate = DateManagment.getDate();
		this._userName = userName;
		this._password = new Password(password, question, answer);
		this._email = new Email(email);
		_friends = new Vector <Member>();
		_messages = new HashMap <Integer, InnerMessage>();
		_posts = new HashMap <Integer, Post> ();
		promoter = null;
		_msgCounter = 0;
		_verificationCode=null;
	}
	/**
	 * constructor for superAdmin membership
	 */
	@SuppressWarnings("unused")
	private Member(String userName) {
		this._userName = userName;
		_friends = new Vector<Member>();
		_messages = new HashMap<Integer, InnerMessage>();
		_posts = new HashMap <Integer, Post> ();
		_msgCounter = 0;
	}
	
	public static Member createSuperAdminMember(String userName, String pass, String email){
		String name=userName.concat("- administrator");
		return new Member(name, pass, email);
	}

	public boolean addFriend(Member member){
		_friends.add(member);
		return true;
	}

	public void notifyFriends(){

	}
	public void upgrade(String type){

	}
	public boolean isValidMemberForAdministrator(){
		return false;
	}

	public boolean isValidMemberForModerator(){
		return false;
	}

	public boolean isValidMemberForSuperAdministrator(){
		return false;
	}
	
	
	public report answerPasswordQuestion(String answer)
	{
		if (_password.get_passAnswer().equals(answer))
			return report.OK;
		else
			return report.WRONG_PASSWORD_ANSWER;
	}	
	
	public void message(String message) {
		InnerMessage newMessage = new InnerMessage("System message", message);
		_messages.put(_msgCounter, newMessage);
		_msgCounter++;
	}

	public void message(String sender,String message){
		InnerMessage newMessage = new InnerMessage(sender,message);
		_messages.put(_msgCounter, newMessage);
		_msgCounter++;

	}

	public report setNewPassword(String newPassword) {
		for (int i = 0; i < _oldPasswords.size(); i++)		//checking all former passwords
			if (_oldPasswords.get(i).get_pass().equals(newPassword))
				return report.PASSWORD_ALREADY_BEEN_USED;
		
		_oldPasswords.add(this._password);
		this._password.set_pass(newPassword);
		return report.OK;
	}
	
	/* 
	 * getters and setters
	 */

	public String get_userName() {
		return _userName;
	}
	public void set_userName(String userName) {
		this._userName = userName;
	}

	public Password get_password() {
		return _password;
	}

	public Email get_email() {
		return _email;
	}
	public void set_email(String email) {
		this._email = new Email(email);
	}
	public MemberType get_type() {
		return _type;
	}
	public void set_type(MemberType type) {
		this._type = type;
	}
	public Vector<Member> getFriends() {
		return _friends;
	}
	public void setFriends(Vector<Member> friends) {
		this._friends = friends;
	}
	
	public Date get_regDate() {
		return _regDate;
	}
	

	
	/* 
	 * END getters and setters
	 */

	public boolean equals(Object object){
		if(object instanceof Member && ((Member)object).get_userName() == this._userName){
			return true;
		} else {
			return false;
		}
	}
	public Member getPromoter() {
		return promoter;
	}
	public void setPromoter(Member promoter) {
		this.promoter = promoter;
	}
	public HashMap <Integer, Post> getPosts() {
		return _posts;
	}

	public void addPost(int index, Post newPost) {
		_posts.put(index, newPost);
		
	}

	public String getVerificationPassword(){
		return _verificationCode;
	}
	public void setVerification(String code){
		_verificationCode=code;
	}
	public boolean isValidVerificationCode(String code){
		return _verificationCode.equals(code);
	}
	public String setRandomVerification(){
		 _verificationCode= RandomStringUtils.randomAlphanumeric(8);
		 return _verificationCode;
	}
	
	

}
