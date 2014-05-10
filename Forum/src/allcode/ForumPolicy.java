package allcode;

public class ForumPolicy {
	
	private boolean deleteMessageModerator;		//if true mod can delete user message
	private boolean deleteMessagePublisher;		//if true publisher can delete msg
	private boolean deleteMessageAdmin;			//if true admin can delete message.
	
	private int moderatorDays;					//the amount of days before a member can become mod		
	private int moderatorPosts;					//the amount of posts before a member can become mod
	
	private boolean deleteModeratorOnlyByRankingAdmin;		//if true only the ranking admin can delete this mod
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
