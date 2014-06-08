package allcode;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Embeddable
public class ForumPolicy implements Serializable{
	
	
	@Column(name="deleteMessageModerator")
	private boolean deleteMessageModerator;		//if true mod can delete user message
	
	
	@Column(name="deleteMessagePublisher")
	private boolean deleteMessagePublisher;		//if true publisher can delete msg
	
	
	@Column(name="deleteMessageAdmin")
	private boolean deleteMessageAdmin;			//if true admin can delete message.
	
	
	@Column(name="moderatorDays")
	private int moderatorDays;					//the amount of days before a member can become mod		
	
	
	@Column(name="moderatorPosts")
	private int moderatorPosts;					//the amount of posts before a member can become mod
	
	
	
	@Column(name="deleteModeratorOnlyByRankingAdmin")
	private boolean deleteModeratorOnlyByRankingAdmin;		//if true only the ranking admin can delete this mod
	
	
	@Column(name="deleteLastModerator")
	private boolean deleteLastModerator;					//if true can delete last mod
	
	public ForumPolicy()
	{
		setDeleteMessageModerator(true);
		setDeleteMessagePublisher(true);
		setDeleteMessageAdmin(true);
		setModeratorDays(0);		
		setModeratorPosts(0);
		setDeleteModeratorOnlyByRankingAdmin(false);
		setDeleteLastModerator(true);
	}

	public boolean isDeleteMessageModerator() {
		return deleteMessageModerator;
	}

	public void setDeleteMessageModerator(boolean deleteMessageModerator) {
		this.deleteMessageModerator = deleteMessageModerator;
	}

	public boolean isDeleteMessagePublisher() {
		return deleteMessagePublisher;
	}

	public void setDeleteMessagePublisher(boolean deleteMessagePublisher) {
		this.deleteMessagePublisher = deleteMessagePublisher;
	}

	public boolean isDeleteMessageAdmin() {
		return deleteMessageAdmin;
	}

	public void setDeleteMessageAdmin(boolean deleteMessageAdmin) {
		this.deleteMessageAdmin = deleteMessageAdmin;
	}

	public int getModeratorDays() {
		return moderatorDays;
	}

	public void setModeratorDays(int moderatorDays) {
		this.moderatorDays = moderatorDays;
	}

	public int getModeratorPosts() {
		return moderatorPosts;
	}

	public void setModeratorPosts(int moderatorPosts) {
		this.moderatorPosts = moderatorPosts;
	}

	public boolean isDeleteModeratorOnlyByRankingAdmin() {
		return deleteModeratorOnlyByRankingAdmin;
	}

	public void setDeleteModeratorOnlyByRankingAdmin(
			boolean deleteModeratorOnlyByRankingAdmin) {
		this.deleteModeratorOnlyByRankingAdmin = deleteModeratorOnlyByRankingAdmin;
	}

	public boolean isDeleteLastModerator() {
		return deleteLastModerator;
	}

	public void setDeleteLastModerator(boolean deleteLastModerator) {
		this.deleteLastModerator = deleteLastModerator;
	}
	
	
	
	
		
		


}
