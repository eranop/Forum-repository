package databases;
import java.util.Collection;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Column;  
import javax.persistence.Entity;  
import javax.persistence.Id;    
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Members")
public class MemberData {

	@Id
	@Column(name="user_name")
	String _username;
	
	@Id
	@Column(name="forum_name")
	String _forum;
	
	@Column(name="password")
	String _password;
	
	@Column(name="email")
	String _email;
	
	@Column(name="password_question")
	String _passQuestion;
	
	@Column(name="password_answer")
	String _passAns;
	
	@OneToMany(cascade=CascadeType.ALL)  
	 @JoinTable(name="root_posts_of_user",joinColumns={@JoinColumn(name="user_name")}
	 ,inverseJoinColumns={@JoinColumn(name="sub_forum")})  
	 Collection<RootPostData> _initialPosts=new Vector<RootPostData>();
	
	@OneToMany(cascade=CascadeType.ALL)  
	 @JoinTable(name="posts_Of_SubForum",joinColumns={@JoinColumn(name="user_name")}
	 ,inverseJoinColumns={@JoinColumn(name="sub_forum")})  
	 Collection<AllPostData> _allPosts=new Vector<AllPostData>();

	public Collection<RootPostData> get_initialPosts() {
		return _initialPosts;
	}

	public void set_initialPosts(Collection<RootPostData> _initialPosts) {
		this._initialPosts = _initialPosts;
	}

	public Collection<AllPostData> get_allPosts() {
		return _allPosts;
	}

	public void set_allPosts(Collection<AllPostData> _allPosts) {
		this._allPosts = _allPosts;
	}

	public MemberData(String _username, String _forum, String _password,
			String _email, String _passQuestion, String _passAns) {
		super();
		this._username = _username;
		this._forum = _forum;
		this._password = _password;
		this._email = _email;
		this._passQuestion = _passQuestion;
		this._passAns = _passAns;
	}

	public String get_username() {
		return _username;
	}

	public void set_username(String _username) {
		this._username = _username;
	}

	public String get_forum() {
		return _forum;
	}

	public void set_forum(String _forum) {
		this._forum = _forum;
	}

	public String get_password() {
		return _password;
	}

	public void set_password(String _password) {
		this._password = _password;
	}

	public String get_email() {
		return _email;
	}

	public void set_email(String _email) {
		this._email = _email;
	}

	public String get_passQuestion() {
		return _passQuestion;
	}

	public void set_passQuestion(String _passQuestion) {
		this._passQuestion = _passQuestion;
	}

	public String get_passAns() {
		return _passAns;
	}

	public void set_passAns(String _passAns) {
		this._passAns = _passAns;
	}
	
}
