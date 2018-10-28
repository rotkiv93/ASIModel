package es.udc.lbd.asi.restexample;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import es.udc.lbd.asi.restexample.model.domain.Movie;
import es.udc.lbd.asi.restexample.model.domain.MovieUser;
import es.udc.lbd.asi.restexample.model.repository.MovieDAO;
import es.udc.lbd.asi.restexample.model.repository.MovieUserDAO;

import java.util.Properties;
import java.util.Set;

import javax.mail.Address;
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
	
	@Autowired 
	private MovieDAO movieDAO;
	
	//cron puesto para que se ejecute cada dia a las 10 AM
	@Scheduled(cron = "* * 10 * * *")
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void scheduleFixedDelayTask() {
		
		LocalDate fecha = LocalDate.now();
	
		Set<String> emailsANotificar = new HashSet<>();
		
		//Se buscan todas las peliculas a estrenar hoy
		List<Movie> peliculas = (List<Movie>) movieDAO.findAllInDate(fecha);
		
		//se busca a todos los usuarios que hayan marcado esas peliculas como pendientes
		for (Movie m: peliculas){
			List<MovieUser> usuariosNotif = movieUserDAO.findAllMovieUsersWithMoviePending(m);
			for (MovieUser mu: usuariosNotif){
				emailsANotificar.add(mu.getUsuario().getEmail());
			}
		}
		
		String correos = String.join(",", emailsANotificar);
		
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
						return new PasswordAuthentication("FICaffinity","ficaffinity2018");
					}
				});

			
			try {

				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("ficaffinity@gmail.com"));
				
				message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(correos));
				message.setSubject("Parece que hoy es un buen dia!");
				message.setText("Parece que por fin se ha estrenado una pelicula que querías ver!," +
						"\n\n Ve corriendo a tu cine más cercano para disfrutarla!");

				//descomentar esta linea para que envie los correos
				//Transport.send(message);
			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
	}
}
		

	

