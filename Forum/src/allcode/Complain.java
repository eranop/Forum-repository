package allcode;

public class Complain {
	
	private Member member;
	private Member moderator;
	private String complain;

	public Complain(Member member, Member moderator, String complain) 
	{
		this.member = member;
		this.setModerator(moderator);
		this.setComplain(complain);
	}

	public Member getMember() {
		return member;
	}

	public Member getModerator() {
		return moderator;
	}

	public void setModerator(Member moderator) {
		this.moderator = moderator;
	}

	public String getComplain() {
		return complain;
	}

	public void setComplain(String complain) {
		this.complain = complain;
	}

	public void presentComplain() {
		System.out.println("Complaining Member: " + getMember());
		System.out.println("Complained Moderator: " + getModerator());
		System.out.println("");
		System.out.println("Complain: " + getComplain());
	}
}
