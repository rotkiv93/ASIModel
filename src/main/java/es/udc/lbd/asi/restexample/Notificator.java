package es.udc.lbd.asi.restexample;

import java.time.LocalDate;
import java.util.Properties;

import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import es.udc.lbd.asi.restexample.model.repository.MovieUserDAO;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



@Component
public class Notificator {
	
	@Autowired
	private MovieUserDAO movieUserDAO;
	
	
	@Scheduled(fixedDelay = 100)
	public void scheduleFixedDelayTask() {
		
		LocalDate fecha_hoy = LocalDate.now();
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
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
			message.setFrom(new InternetAddress("from@no-spam.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse("to@no-spam.com"));
			message.setSubject("Testing Subject");
			message.setText("Dear Mail Crawler," +
					"\n\n No spam to my email, please!");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
		
		
		
		//List<MovieUser> mov = movieUserDAO.findAll();
		//for(MovieUser m: mov){
			//m.getPelicula().getFecha_estreno();
		//}
	    //System.out.println(
	      //"Fixed delay task - " + fecha_hoy);
	}
}
