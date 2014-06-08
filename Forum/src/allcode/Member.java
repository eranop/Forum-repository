package allcode;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.HashMap;

import services.InnerMessage;
import services.report;
import services.Email;

import org.apache.commons.lang3.RandomStringUtils;











import org.hibernate.Session;

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
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity

@Table (name="Members")
public class Member implements Serializable{
	
	@Column(name="date_of_registration")
	private Date _regDate;

	
	@Id
	@GeneratedValue
	@Column(name="member_id")
	private int memberID;
	//@OneToOne
	
	@Column(name = "userName")
	private String _userName;
	
	
	@OneToOne
	@JoinColumn(name="forumName")
	private Forum _forum;
	
	@Embedded
	private Password _password;
	
	@Embedded 
	private Email _email;
	
	@Column (name="verification_code")
	private String _verificationCode;
	
	@OneToOne 
	@JoinColumn(name="promoter")
	private Member promoter;
	
	@Transient //not implemented
	private MemberType _type;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)  
	 @JoinTable(name="friends_of_member",
	 joinColumns={@JoinColumn(name="memberID")},inverseJoinColumns={@JoinColumn(name="freindID")})
	private List <Member> _friends;
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="old_passwords",joinColumns=@JoinColumn(name="memberID"))
	private List <Password> _oldPasswords;
	
	@ElementCollection(fetch=FetchType.EAGER)
	  @MapKeyColumn(name="_index")
	@Column(name="post")
	@CollectionTable(name="member_posts",joinColumns={@JoinColumn(name="member_index")})
	/*@OneToMany(mappedBy="_publisher")
	@MapKey(name="_index")*/
	private Map <Integer, Post> _posts;
	
	@ElementCollection(fetch=FetchType.EAGER)
	  @MapKeyColumn(name="message_index")
	@Column(name="messages")
	@CollectionTable(name="member_messages",joinColumns={@JoinColumn(name="member_index")})
	private Map <Integer, InnerMessage> _messages;
	
	@Column (name="message_counter")
	private int _msgCounter;
	
	public Member(String userName, String password, String email) {

		_regDate = DateManagment.getDate();
		this._userName = userName;
		this._password = new Password(password);
		this._email = new Email(email);
		_friends = new ArrayList <Member>();
		_messages = new HashMap <Integer, InnerMessage>();
		_posts = new HashMap <Integer, Post> ();
		promoter = null;
		_msgCounter = 0;
		_verificationCode=null;
	}

	public Member(String userName, String password, String email, String question, String answer,Forum forum) {

		_regDate = DateManagment.getDate();
		this._userName = userName;
		this._password = new Password(password, question, answer);
		this._email = new Email(email);
		_friends = new ArrayList <Member>();
		_messages = new HashMap <Integer, InnerMessage>();
		_posts = new HashMap <Integer, Post> ();
		promoter = null;
		_msgCounter = 0;
		_verificationCode=null;
		_forum=forum;
	}
	
	public Member(){
		
	}
	/**
	 * constructor for superAdmin membership
	 */
	@SuppressWarnings("unused")
	private Member(String userName) {
		this._userName = userName;
		_friends = new ArrayList<Member>();
		_messages = new HashMap<Integer, InnerMessage>();
		_posts = new HashMap <Integer, Post> ();
		_msgCounter = 0;
	}
	
	public static Member createSuperAdminMember(String userName, String pass, String email){
		String name=userName.concat("- administrator");
		Member mem= new Member(name, pass, email);
		Session ss=DataBaseInit.sf.openSession();  
		  ss.beginTransaction();  
		 //saving objects to session  
		  ss.saveOrUpdate(mem);  
		  ss.getTransaction().commit();  
		  ss.close(); 
		return mem;
	}

	public boolean addFriend(Member member){
		_friends.add(member);
		Session ss=DataBaseInit.sf.openSession();  
		  ss.beginTransaction();  
		 //saving objects to session  
		  ss.update(this);  
		  ss.getTransaction().commit();  
		  ss.close(); 
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
		Session ss=DataBaseInit.sf.openSession();  
		  ss.beginTransaction();  
		 //saving objects to session  
		  ss.saveOrUpdate(newMessage);
		  ss.update(this);
		  ss.getTransaction().commit();  
		  ss.close(); 
	}

	public void message(String sender,String message){
		InnerMessage newMessage = new InnerMessage(sender,message);
		_messages.put(_msgCounter, newMessage);
		_msgCounter++;
		Session ss=DataBaseInit.sf.openSession();  
		  ss.beginTransaction();  
		 //saving objects to session  
		  ss.saveOrUpdate(newMessage);
		  ss.update(this);
		  ss.getTransaction().commit();  
		  ss.close(); 

	}

	public report setNewPassword(String newPassword) {
		for (int i = 0; i < _oldPasswords.size(); i++)		//checking all former passwords
			if (_oldPasswords.get(i).get_pass().equals(newPassword))
				return report.PASSWORD_ALREADY_BEEN_USED;
		
		_oldPasswords.add(this._password);
		this._password.set_pass(newPassword);
		Session ss=DataBaseInit.sf.openSession();  
		  ss.beginTransaction();  
		 //saving objects to session  
		  ss.update(this);
		  ss.getTransaction().commit();  
		  ss.close(); 
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
	public List getFriends() {
		return  _friends;
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
	public Map <Integer, Post> getPosts() {
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
