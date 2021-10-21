package main;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

import presentacion.controlador.Controlador;


public class Main {
	
	//OTRA FORMA	
	public void enviarMail2() {
		try {
			
			Properties props = new Properties();
			props.setProperty("mail.smtp.host", "smtp.gmail.com");
			props.setProperty("mail.smtp.starttls.enable", "true");
			props.setProperty("mail.smtp.port", "587");
			props.setProperty("mail.smtp.auth", "true");
			props.setProperty("mail.smtp.ssl.trust", "*");
//			props.put("mail.debug", "true");
			Session session = Session.getDefaultInstance(props);
			
			/*
			 * prop.put("mail.smtp.ssl.trust", "*");
			 * mail.smtp.starttls.enable=false
			 *  mail.smtp.starttls.enable
			Properties properties = new Properties();
			properties.put("mail.smtp.host", mailAccount.getMailHost());
			properties.put("mail.smtp.port", mailAccount.getPort());
			properties.put("mail.smtp.auth", mailAccount.isAuth());
			properties.put("mail.smtp.starttls.enable",mailAccount.isStartTls());
			*/
			
			String correoRemitente = "subelza150@gmail.com";
			String contrasenia = "";
			String correoReceptor = "sebastianx3600@gmail.com";
			String asunto = "Prueba";
			String mensaje = "Hola seba xd";
			MimeMessage message = new MimeMessage(session);

			message.setFrom(new InternetAddress(correoRemitente));
			
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(correoReceptor));
			
			message.setSubject(asunto);
			message.setText(mensaje);
			
			Transport t = session.getTransport("smtp");
			t.connect(correoRemitente, contrasenia);
			t.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
			t.close();
			
			JOptionPane.showMessageDialog(null, "Correo enviado");
			
			
		} catch (AddressException e) {
			// TODO Auto-generated catch block
//			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
			JOptionPane.showMessageDialog(null, "Error, el primero xd");
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Error, el segundo xd");
			e.printStackTrace();
		}
		
	}
	
	
	
	public static void main(String[] args) {
//		Main m = new Main();
//		m.enviarMail2();
		Controlador controlador = new Controlador();
		controlador.inicializar();
		controlador.mostrarVentanaMenu();
		
	}
}
