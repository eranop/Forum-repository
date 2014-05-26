package services;

public class InnerMessage {

	private String composer;
	private String content;
	
	public InnerMessage (String composer, String content)
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
