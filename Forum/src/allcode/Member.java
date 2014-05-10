package allcode;
import java.util.Date;
import java.util.Vector;
import java.util.HashMap;


public class Member {

	private Date _regDate;
	private Date _passwordDate;
	
	private String _passQuestion;
	private String _passAnswer;
	
	private String _userName;
	private String _password;
	private String _email;
	
	private MemberType _type;
	private Vector <Member> _friends;
	private Vector <String> _oldPasswords;
	private HashMap <Integer, Message> _messages;
	private int _msgCounter;

	public Member(String userName, String password, String email) {

		_regDate = DateManagment.getDate();
		_passwordDate = DateManagment.getDate();
		this._userName = userName;
		this._password = password;
		this._email = email;
		_friends = new Vector<Member>();
		_messages = new HashMap<Integer,Message>();
		_msgCounter = 0;
	}
	/**
	 * constructor for superAdmin membership
	 */
	private Member(String userName) {
		this._userName = userName;
		_friends = new Vector<Member>();
		_messages = new HashMap<Integer,Message>();
		_msgCounter = 0;
	}
	
	public static Member createSuperAdminMember(String userName){
		String name=userName.concat("- administrator");
		return new Member(name);
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
		if (this._passAnswer.equals(answer))
			return report.OK;
		else
			return report.WRONG_PASSWORD_ANSWER;
	}	
	
	public void message(String message) {
		Message newMessage=new Message("System message",message);
		_messages.put(_msgCounter, newMessage);
		_msgCounter++;
	}

	public void message(String sender,String message){
		Message newMessage=new Message(sender,message);
		_messages.put(_msgCounter, newMessage);
		_msgCounter++;

	}

	public report setNewPassword(String newPassword) {
		for (int i = 0; i < _oldPasswords.size(); i++)		//checking all former passwords
			if (_oldPasswords.get(i).equals(newPassword))
				return report.PASSWORD_ALREADY_BEEN_USED;
		
		_oldPasswords.add(this._password);
		this._password = newPassword;
		this._passwordDate = DateManagment.getDate();
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

	public String get_password() {
		return _password;
	}

	public String get_email() {
		return _email;
	}
	public void set_email(String _email) {
		this._email = _email;
	}
	public MemberType get_type() {
		return _type;
	}
	public void set_type(MemberType _type) {
		this._type = _type;
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
	
	public Date get_passwordDate() {
		return _passwordDate;
	}

	public int daysSinceLastPassword() {
		return DateManagment.getDateDiffDays(_passwordDate, DateManagment.getDate());
	}
	public String get_passQuestion() {
		return _passQuestion;
	}
	public void set_passQuestion(String passQuestion) {
		this._passQuestion = passQuestion;
	}
	public String get_passAnswer() {
		return _passAnswer;
	}
	public void set_passAnswer(String passAnswer) {
		this._passAnswer = passAnswer;
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




}
