package es.udc.lbd.asi.restexample;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import es.udc.lbd.asi.restexample.config.DatabaseLoader;
import es.udc.lbd.asi.restexample.model.domain.Movie;
import es.udc.lbd.asi.restexample.model.domain.MovieUser;
import es.udc.lbd.asi.restexample.model.domain.NotifEnum;
import es.udc.lbd.asi.restexample.model.repository.MovieDAO;
import es.udc.lbd.asi.restexample.model.repository.MovieUserDAO;

import java.util.Properties;
import java.util.Set;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Component
public class Notificator {

	private final Logger logger = LoggerFactory.getLogger(Notificator.class);
	
	@Autowired
	private MovieUserDAO movieUserDAO;

	@Autowired
	private MovieDAO movieDAO;

	// cron puesto para que se ejecute cada dia a las 10 AM
	//@Scheduled(cron = "*/20 * * * * *")

	//@Scheduled(cron = "* * 10 * * *")
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void scheduleFixedDelayTask() {
		
		LocalDate fecha = LocalDate.now();
		Set<String> emailsANotificar = new HashSet<>();

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("FICaffinity", "ficaffinity2018");
			}
		});

		// Se buscan todas las peliculas a estrenar hoy
		List<Movie> peliculas = (List<Movie>) movieDAO.findAllInDate(fecha);

		// se busca a todos los usuarios que hayan marcado esas peliculas como
		// pendientes
		for (Movie m : peliculas) {
			emailsANotificar.clear();
			List<MovieUser> usuariosNotif = movieUserDAO.findAllMovieUsersWithMoviePending(m);
			for (MovieUser mu : usuariosNotif) {
				// solo se añade si el usuario quiere notificaciones por email
				if (mu.getUsuario().getNotificacion() == NotifEnum.Email){
					emailsANotificar.add(mu.getUsuario().getEmail());
				} else{
					logger.info("Enviar SMS al usuario " + mu.getUsuario().toString() + " por la pelicula " + mu.getPelicula().toString());
				}
				
			}

			if (emailsANotificar.size() > 0) {
				String correos = String.join(",", emailsANotificar);
	
				try {
	
					Message message = new MimeMessage(session);
					message.setFrom(new InternetAddress("ficaffinity@gmail.com"));
	
					message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(correos));
					message.setSubject("Parece que hoy es un buen dia!");
					String contenido = "<div style = \"margin: 0% 1.5%\">\r\n" + 
							"	<div style=\"font-size:120%;text-align:center;\">\r\n" + 
							"    	<h1> Obtén ya tus entradas para <b style=\"color:#28B463;\"> "+ m.getTitulo() + "!</b></h1>\r\n" + 
							"       <p>No te olvides de visitarnos una vez la hayas visto para puntuarla :)</p>\r\n" + 
							"    </div>\r\n" + 
							"	<div style = \"text-align: center;\"> \r\n" + 
							"    	<p style = \"display: inline-block;background-color: #28B463; padding:1.5% 2%\"> \r\n" + 
							"        	 <a style= \"color:white; text-decoration: none;\" href=\"http://localhost:1234/#/movies/"+ m.getId() +"\">VOTALA AQUI!</a>\r\n" + 
							"        </p>\r\n" + 
							"    <div>\r\n" + 
							"    <p style= \"text-align:justify;\"> <b style= \"color:#28B463\">Y por si ya te habías olvidado de que iba, aquí te va la sinopsis:</b><br/>"+ m.getSinopsis() +"</p>\r\n" + 
							"    \r\n" + 
							"</div>";
					message.setContent(contenido, "text/html; charset=utf-8");
					// descomentar esta linea para que envie los correos
					//Transport.send(message);
				} catch (MessagingException e) {
					throw new RuntimeException(e);
				}
			}

		}
	}
}
