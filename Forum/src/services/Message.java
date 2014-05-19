package services;

public class Message {

	private String composer;
	private String content;
	
	public Message (String composer, String content)
	{
		this.composer = composer;
		this.content = content;
	}

	public String getComposer() 
	{
		return composer;
	}

	public String getContent()
	{
		return content;
	}
	
	

	
}
