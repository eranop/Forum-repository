
public class SuperAdmin {
	
	
	private String _userName;
	
	private String _password;
	
	private String _email;
	
	public SuperAdmin(){
		_userName=null;
		_password=null;
		_email=null;
	}

	public SuperAdmin(String name, String pass, String email) {
		_userName=name;
		_password=pass;
		_email=email;
	}

	//getter and setters
	public String get_userName() {
		return _userName;
	}

	public void set_userName(String _userName) {
		this._userName = _userName;
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


}
