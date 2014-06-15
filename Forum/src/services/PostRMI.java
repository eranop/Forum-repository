package services;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import allcode.Member;
import allcode.Post;

public class PostRMI implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String _publisher;
	private String _title;
	private String _content;
	private int _index;

	
	
	public String get_publisher() {
		return _publisher;
	}



	public void set_publisher(String _publisher) {
		this._publisher = _publisher;
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



	public int get_index() {
		return _index;
	}



	public void set_index(int _index) {
		this._index = _index;
	}



	public PostRMI(String title, String content, String publisher, int index) {
		_title=title;
		_content=content;
		_publisher=publisher;
		_index=index;
	}
	
	

}
