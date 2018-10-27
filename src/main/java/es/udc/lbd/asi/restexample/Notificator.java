package es.udc.lbd.asi.restexample;

import java.time.LocalDate;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



//@Component
public class Notificator {
		
	/*@Autowired 
	private MovieUserDAO movieUserDAO;
	
	@Scheduled(fixedDelay = 10000)
	public void scheduleFixedDelayTask() {
		
		
		LocalDate fecha_hoy = LocalDate.now();
		
		List<MovieUser> mov = movieUserDAO.findAll();
		for(MovieUser m: mov){
			m.getPelicula().getFecha_estreno();
		}*/
		
		
	/*
		Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");

			Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication("username","password");
					}
				});

			try {

				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("nuestrocorreo@gmail.com"));
				message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse("correosdeusuarios@gmail.com"));
				message.setSubject("Testing Subject");
				message.setText("Dear Mail Crawler," +
						"\n\n No spam to my email, please!");

				Transport.send(message);

				System.out.println("Done");

			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
	}*/
}
		

	

