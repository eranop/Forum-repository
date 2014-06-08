package allcode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import services.report;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.Session;
/**
 * use by super administrator actor
 * contains super administrator operations
 */


public class ForumsManagement {

	
	private Member _superAdmin;
	
	private List<Forum> _forums;

	public ForumsManagement() {
		Session ss=DataBaseInit.sf.openSession();  
		  ss.beginTransaction();  
		 //saving objects to session  
		  _forums=ss.createQuery("from allcode.Forum").list(); 
		  ss.getTransaction().commit();
		  ss.close(); 
		if(_forums==null)  
			_forums=new ArrayList<Forum>();
	}
	public ForumsManagement(String superAdminName, String password, String email) {
		Session ss=DataBaseInit.sf.openSession();  
		  ss.beginTransaction();  
		 //saving objects to session  
		  _forums=ss.createQuery("from allcode.Forum").list(); 
		  ss.getTransaction().commit();
		  ss.close(); 
		if(_forums==null)  
			_forums=new ArrayList<Forum>();
		_superAdmin= new Member(superAdminName, password, email);
	}


	/**
	 * 3 constructors:
	 *  - creating new member which  will be administrator
	 *  - forum without administrator
	 *  - creating forum with member and add it as administrator
	 */
	public report createForum(String name,String description,String adminName,String adminPass,String adminMail, String amdinQuestion, String adminAnswer){
		//description is not must
		if(name==null || adminName==null || adminPass==null || adminMail==null){
			return report.NULL_ARGUMENTS; 
		}
		if(isForumExist(name)){
			return report.ALREADY_FORUM_EXIST;
		}
		Forum forum=new Forum(name,description);
		report rep= forum.register(adminName, adminPass, adminMail, amdinQuestion, adminAnswer);
		if(rep.equals(report.OK)){
		_forums.add(forum);
		Session ss=DataBaseInit.sf.openSession();  
		  ss.beginTransaction();  
		 //saving objects to session  
		  ss.saveOrUpdate(forum);  
		  ss.getTransaction().commit();  
		  ss.close(); 
		return forum.addAdminByName(adminName);
		}
		return rep;
	}
	public report createForum(String name,String description){
		//description is not must
		if(name==null){
			return report.NULL_ARGUMENTS; 
		}
		if(isForumExist(name)){
			return report.ALREADY_FORUM_EXIST;
		}
		Forum forum=new Forum(name,description);
		//report rep=forum.register(adminName, adminPass, adminMail);
		_forums.add(forum);
		Session ss=DataBaseInit.sf.openSession();  
		  ss.beginTransaction();  
		 //saving objects to session  
		  ss.saveOrUpdate(forum);  
		  ss.getTransaction().commit();  
		  ss.close(); 
		return report.OK;
	}
	public report createForum(String name,String description, Member admin){
		//description is not must
		if(name==null){
			return report.NULL_ARGUMENTS; 
		}
		if(isForumExist(name)){
			return report.ALREADY_FORUM_EXIST;
		}
		Forum forum=new Forum(name,description);
		//report rep=forum.register(adminName, adminPass, adminMail);
		//TODO registration with email sending
		if(admin != null){
			forum.addAdmin(admin);
		}
		_forums.add(forum);
		Session ss=DataBaseInit.sf.openSession();  
		  ss.beginTransaction();  
		 //saving objects to session  
		  ss.saveOrUpdate(forum);  
		  ss.getTransaction().commit();  
		  ss.close(); 
		return report.OK;
	}
	public report deleteForum(String forumName){
		if(isForumExist(forumName)){
			Forum forum=getForum(forumName);
			_forums.remove(forum);
			Session ss=DataBaseInit.sf.openSession();  
			  ss.beginTransaction();  
			 //saving objects to session  
			  ss.delete(forum);  
			  ss.getTransaction().commit();  
			  ss.close(); 
			return report.OK;
		}
		return report.NO_SUCH_FORUM;
	}

	/**
	 * 
	 * @param name of member in forum
	 * @param forumName name of forum
	 * 
	 */

	public report setSuperAdmin(String name,String pass,String email){
		
		if(email==null || pass==null || name==null)
			return report.NULL_ARGUMENTS;
		//decide if we want to replace current super administrator
		//get member details (because he must be member in each forum)
		_superAdmin.set_email(email);
		_superAdmin.setNewPassword(pass);
		_superAdmin.set_userName(name);

		return report.OK;
	}
	//helper functions
	public Forum getForum(String forumName){
		for(int i=0;i<_forums.size();i++){
			if( _forums.get(i).get_forumName().equals(forumName)){
				return  _forums.get(i);
			}
		}
		return null;
	}
	public boolean isForumExist(String forumName){
		for(int i=0;i<_forums.size();i++){
			if( _forums.get(i).get_forumName().equals(forumName)){
				return true;
			}
		}
		return false;
	}
	public boolean isValidSuperAdmin(String userName, String pass){
		if(_superAdmin.get_userName().equals(userName) &&
				_superAdmin.get_password().equals(pass)){
			return true;
		}
		return false;
	}
	
	public List<Forum> getForums()
	{
		return  this._forums;
	}








}
