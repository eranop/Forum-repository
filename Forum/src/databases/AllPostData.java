package databases;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="All_Posts")
public class AllPostData {
	
	@Id
	@Column(name="forum_name")
	String _forum;
	
	@Id
	@Column(name="sub_forum")
	String _subForum;
	
	@Id
	@Column(name="index")
	Integer index;
	
	@Column(name="title")
	String _title;
	
	@Column(name="content")
	String _content;
	
	@Column(name="father_ID")
	Integer _fatherID;

	public AllPostData(String _forum, String _subForum, Integer index,
			String _title, String _content, Integer _fatherID) {
		super();
		this._forum = _forum;
		this._subForum = _subForum;
		this.index = index;
		this._title = _title;
		this._content = _content;
		this._fatherID = _fatherID;
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

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String get_title() {
		return _title;
	}

	public void set_title(String _title) {
		this._title = _title;
	}

	public String get_content() {
		return _content;
	}

	public void set_content(String _content) {
		this._content = _content;
	}

	public Integer get_fatherID() {
		return _fatherID;
	}

	public void set_fatherID(Integer _fatherID) {
		this._fatherID = _fatherID;
	}
	
	

}
