package databases;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Admins")
public class AdminsData {

	@Id
	@Column(name="user_name")
	String _username;
	
	@Id
	@Column(name="forum_name")
	String _forum;

	public AdminsData(String _username, String _forum) {
		super();
		this._username = _username;
		this._forum = _forum;
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
	
	
}
