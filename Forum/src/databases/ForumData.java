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
@Table(name="Forums")
public class ForumData {
	@Id  
	 @Column(name="Forum_Name")  
	 String _forumName;  
	   
	 @Column(name="Forum_Description")  
	 String _description;  
	 
	 @OneToMany(cascade=CascadeType.ALL)  
	 @JoinTable(name="SubForums_Of_Forum",joinColumns={@JoinColumn(name="Forum_Name")}
	 ,inverseJoinColumns={@JoinColumn(name="forum_name")})  
	 Collection<SubForumData> _subForums=new Vector<SubForumData>();
	 
	 @OneToMany(cascade=CascadeType.ALL)  
	 @JoinTable(name="Members_Of_Forum",joinColumns={@JoinColumn(name="Forum_Name")}
	 ,inverseJoinColumns={@JoinColumn(name="forum_name")})  
	 Collection<MemberData> _members=new Vector<MemberData>();
	 
	 @OneToMany(cascade=CascadeType.ALL)  
	 @JoinTable(name="Admins_Of_Forum",joinColumns={@JoinColumn(name="Forum_Name")}
	 ,inverseJoinColumns={@JoinColumn(name="forum_name")})  
	 Collection<AdminsData> _admins=new Vector<AdminsData>();
	  
	 public Collection<AdminsData> get_admins() {
		return _admins;
	}

	public void set_admins(Collection<AdminsData> _admins) {
		this._admins = _admins;
	}

	public Collection<MemberData> get_members() {
		return _members;
	}

	public void set_members(Collection<MemberData> _members) {
		this._members = _members;
	}

	public ForumData()  
	 {  
	    
	 }

	public Collection<SubForumData> getSubForums() {
		return _subForums;
	}

	public void setSubForums(Collection<SubForumData> subForums) {
		this._subForums = subForums;
	}

	public ForumData(String forumName, String forumDescription) {
		this._forumName = forumName;
		this._description = forumDescription;
	}

	public String getForumName() {
		return _forumName;
	}

	public void setForumName(String forumName) {
		this._forumName = forumName;
	}

	public String getForumDescription() {
		return _description;
	}

	public void setForumDescription(String forumDescription) {
		this._description = forumDescription;
	}  
	
	

}
