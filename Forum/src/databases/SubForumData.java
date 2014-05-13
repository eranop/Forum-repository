package databases;
import java.util.ArrayList;  
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
@Table(name="Sub_Forums")
public class SubForumData {

	@Id
	@Column(name="sub_forum_name")
	String _name;
	
	@Id
	@Column(name="forum_name")
	String _forum;
	
	@Column(name="sub_forum_description")
	String _description;
	
	@Column(name="user_policy")
	String _userPolicy;
	
	@OneToMany(cascade=CascadeType.ALL)  
	 @JoinTable(name="moderator_Of_SubForum",joinColumns={@JoinColumn(name="sub_forum_name")}
	 ,inverseJoinColumns={@JoinColumn(name="sub_forum_name")})  
	 Collection<ModeratorData> _moderators=new Vector<ModeratorData>();
	
	@OneToMany(cascade=CascadeType.ALL)  
	 @JoinTable(name="posts_Of_SubForum",joinColumns={@JoinColumn(name="sub_forum_name")}
	 ,inverseJoinColumns={@JoinColumn(name="sub_forum_name")})  
	 Collection<AllPostData> _allPosts=new Vector<AllPostData>();
	
	@OneToMany(cascade=CascadeType.ALL)  
	 @JoinTable(name="root_posts_Of_SubForum",joinColumns={@JoinColumn(name="sub_forum_name")}
	 ,inverseJoinColumns={@JoinColumn(name="sub_forum_name")})  
	 Collection<RootPostData> _RootPosts=new Vector<RootPostData>();

	public Collection<ModeratorData> get_moderators() {
		return _moderators;
	}

	public void set_moderators(Collection<ModeratorData> _moderators) {
		this._moderators = _moderators;
	}

	
	public Collection<AllPostData> get_allPosts() {
		return _allPosts;
	}

	public void set_allPosts(Collection<AllPostData> _allPosts) {
		this._allPosts = _allPosts;
	}

	public Collection<RootPostData> get_RootPosts() {
		return _RootPosts;
	}

	public void set_RootPosts(Collection<RootPostData> _RootPosts) {
		this._RootPosts = _RootPosts;
	}

	public SubForumData(String _name, String _forum, String _description,
			String _userPolicy) {
		super();
		this._name = _name;
		this._forum = _forum;
		this._description = _description;
		this._userPolicy = _userPolicy;
	}

	public String get_name() {
		return _name;
	}

	public void set_name(String _name) {
		this._name = _name;
	}

	public String get_forum() {
		return _forum;
	}

	public void set_forum(String _forum) {
		this._forum = _forum;
	}

	public String get_description() {
		return _description;
	}

	public void set_description(String _description) {
		this._description = _description;
	}

	public String get_userPolicy() {
		return _userPolicy;
	}

	public void set_userPolicy(String _userPolicy) {
		this._userPolicy = _userPolicy;
	}
	
	
}
