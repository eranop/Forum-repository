package services;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;





public class Email {
	private String _email;
	private final String FORUM_MAIL="sadna12c@gmail.com";
	private final String PASSWORD="oferhagever";
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

/**
 * 
 * 
		// Recipient's email ID needs to be mentioned.
		String to = "aviadelitzur@gmail.com";

		// Sender's email ID needs to be mentioned
		String from = "aviadelitzur@gmail.com";

		// Assuming you are sending email from localhost
		String host = "localhost";

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.setProperty("mail.smtp.host", host);

		// Get the default Session object.
		Session session = Session.getDefaultInstance(properties);

		try{
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO,
					new InternetAddress(to));

			// Set Subject: header field
			message.setSubject("This is the Subject Line!");

			// Now set the actual message
			message.setText("This is actual message");

			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");
		}catch (MessagingException mex) {
			mex.printStackTrace();
		}

 * 
 */

}
