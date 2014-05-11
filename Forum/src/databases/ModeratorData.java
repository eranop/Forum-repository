package databases;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Moderators")
public class ModeratorData {

	
	@Id
	@Column(name="user_name")
	String _username;
	
	@Id
	@Column(name="forum_name")
	String _forum;
	
	@Id
	@Column(name="sub_forum_name")
	String _subForum;

	public ModeratorData(String _username, String _forum, String _subForum) {
		super();
		this._username = _username;
		this._forum = _forum;
		this._subForum = _subForum;
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

	public String get_subForum() {
		return _subForum;
	}

	public void set_subForum(String _subForum) {
		this._subForum = _subForum;
	}
	

}
