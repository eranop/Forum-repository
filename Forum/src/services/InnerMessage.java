package services;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Messages")
public class InnerMessage {

	@Id
	@GeneratedValue
	@Column(name="message_id")
	private int messageID;
	
	@Column(name="composer")
	private String composer;
	
	@Column(name="content")
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
