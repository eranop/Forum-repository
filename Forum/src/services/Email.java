package services;

import java.io.Serializable;
import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import javax.activation.*;



@Embeddable
public class Email implements Serializable{
	
	@Column(name="email")
	private String _email;
	
	@Transient
	private static final String FORUM_MAIL="sadna12c@gmail.com";
	
	@Transient
	private static final String PASSWORD="oferhagever";
	
	public Email(){
		
	}
	
	public Email(String email) {
		if(isValidEmail(email))
			_email=email;
		else
			_email=null;
	}

	public boolean isValidEmail(String email){
		return true;
	}



	public static void main(String [] args)
	{    	
		Email a= new Email("aviadelitzur@gmail.com");
		a.sendMessage("hi", "bye");
	}

	public void sendMessage(String title, String content){
	    Properties props = System.getProperties();
	    props.put("mail.smtp.starttls.enable", true); // added this line
	    props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.user", "username");
	    props.put("mail.smtp.password", "1");
	    props.put("mail.smtp.port", "587");
	    props.put("mail.smtp.auth", true);



	    Session session = Session.getInstance(props,null);
	    MimeMessage message = new MimeMessage(session);

	    System.out.println("Port: "+session.getProperty("mail.smtp.port"));

	    // Create the email addresses involved
	    try {
	        InternetAddress from = new InternetAddress(FORUM_MAIL);
	        message.setSubject(title);
	        message.setFrom(from);
	        message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(_email));

	        // Create a multi-part to combine the parts
	        Multipart multipart = new MimeMultipart("alternative");

	        // Create your text message part
	        BodyPart messageBodyPart = new MimeBodyPart();
	        messageBodyPart.setText("some text to send");

	        // Add the text part to the multipart
	        multipart.addBodyPart(messageBodyPart);

	        // Create the html part
	        messageBodyPart = new MimeBodyPart();
	        String htmlMessage = content;
	        messageBodyPart.setContent(htmlMessage, "text/html");


	        // Add html part to multi part
	        multipart.addBodyPart(messageBodyPart);

	        // Associate multi-part with message
	        message.setContent(multipart);

	        // Send message
	        Transport transport = session.getTransport("smtp");
	        transport.connect("smtp.gmail.com", FORUM_MAIL, PASSWORD);
	        System.out.println("Transport: "+transport.toString());
	        transport.sendMessage(message, message.getAllRecipients());


	    } catch (AddressException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    } catch (MessagingException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	}


}
