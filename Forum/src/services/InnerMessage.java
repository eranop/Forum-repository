package services;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Messages")
public class InnerMessage implements Serializable{

	private static final long serialVersionUID = 1L;

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
	public InnerMessage(){
		
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
