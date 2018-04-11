package com.services;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

@Component
public class EmailService {
	private final String senderEmail = "thedummyemailusedfordev@gmail.com";
	private final String senderEmailPass = "dummy1234";
	private final String host = "smtp.gmail.com";
	private Properties properties;
	
	public EmailService(){
		properties = System.getProperties();
		properties.setProperty("mail.smtp.host", host);
		properties.setProperty("mail.smtp.port", "465");
		properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.setProperty("mail.smtp.socketFactory.fallback", "false");
		properties.setProperty("mail.smtp.socketFactory.port", "465");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.store.protocol", "pop3");
		properties.put("mail.transport.protocol", "smtp");
		properties.setProperty("mail.user", senderEmail);
		properties.setProperty("mail.password", senderEmailPass);
	}
	
	public boolean sendMail(String recipientEmail, String subject, String body){
		boolean success = true;
		
		try{
			//getting authenticated session
			Session session = Session.getDefaultInstance(properties, 
					new Authenticator(){
						protected PasswordAuthentication getPasswordAuthentication(){
							return new PasswordAuthentication(senderEmail, senderEmailPass);
						}
					});
			
			MimeMessage message = new MimeMessage(session);
			//sending details
			message.setFrom(new InternetAddress(senderEmail));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
			
			//message of the email
			message.setSubject(subject);
			message.setContent(body, "text/html");
			//date
			message.setSentDate(new Date());
			//send
			Transport.send(message);
		}catch(MessagingException e){
			success = false;
			e.printStackTrace();
		}
		
		return success;
	}
	
	public String generateLink(String contextPath){
		String link = contextPath + "/";
		
		return link;
	}
}
