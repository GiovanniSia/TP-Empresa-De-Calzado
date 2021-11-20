package modelo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;

import dto.ClienteDTO;
import dto.FacturaDTO;
import dto.PedidosPendientesDTO;
import dto.ProveedorDTO;
import persistencia.dao.mysql.DAOSQLFactory;

public class EnviadorDeMails extends Thread{

	
	//se recibe un proveedor con todos sus pedidos
	public static void enviarMailAProveedor(ProveedorDTO proveedor, ArrayList<PedidosPendientesDTO> pedidosDeProv) {
		try {

			String mensaje = "";
			for (PedidosPendientesDTO p : pedidosDeProv) {
				mensaje = mensaje + "Nombre de Producto: " + p.getNombreMaestroProducto() + "\nCantidad: "
						+ new BigDecimal(p.getCantidad()).setScale(2, RoundingMode.HALF_UP) + "\nUnidad de medida: " + p.getUnidadMedida() + "\n";
			}

			Properties props = new Properties();
			props.setProperty("mail.smtp.host", "smtp.gmail.com");
			props.setProperty("mail.smtp.starttls.enable", "true");
			props.setProperty("mail.smtp.port", "587");
			props.setProperty("mail.smtp.auth", "true");
			props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");
//				props.put("mail.debug", "true");
			Session session = Session.getDefaultInstance(props);

			String correoRemitente = "zapateriaargento198@gmail.com";
			String contrasenia = "zapateriaArgento123ContraseniaIndestructible";
			String correoReceptor = proveedor.getCorreo();
			String asunto = "Pedido de Reposicion";

			MimeMessage message = new MimeMessage(session);

			message.setFrom(new InternetAddress(correoRemitente));

			message.addRecipient(Message.RecipientType.TO, new InternetAddress(correoReceptor));

			message.setSubject(asunto);
			message.setText(mensaje);

			Transport t = session.getTransport("smtp");
			t.connect(correoRemitente, contrasenia);
			t.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
			t.close();

			System.out.println("se envia mail a proveedor");
//			JOptionPane.showMessageDialog(null, "Correo enviado");

		} catch (AddressException e) {
//				Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
			JOptionPane.showMessageDialog(null, "Error, el primero xd");
			e.printStackTrace();
		} catch (MessagingException e) {
			JOptionPane.showMessageDialog(null, "Error, el segundo xd");
			e.printStackTrace();
		}
	}
	
	
	public static void enviarFacturaACliente(ClienteDTO cliente,String URLFactura) {
		if (cliente.getCorreo().equals("")) {
			// si es el cliente por default no se envia nada
			return;
		}
		try {
			Properties props = new Properties();
			props.setProperty("mail.smtp.host", "smtp.gmail.com");
			props.setProperty("mail.smtp.starttls.enable", "true");
			props.setProperty("mail.smtp.port", "587");
			props.setProperty("mail.smtp.auth", "true");
			props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");
//			props.put("mail.debug", "true");
			Session session = Session.getDefaultInstance(props);

			String correoRemitente = "zapateriaargento198@gmail.com";
			String contrasenia = "zapateriaArgento123ContraseniaIndestructible";
			String correoReceptor = cliente.getCorreo();
			String asunto = "Gracias por su compra";
			
			String mensaje="";
			if(esPrimerCompraDe(cliente)) {
				mensaje = "Hola, <b>" + cliente.getNombre() + "</b>"
						+ "<br>Gracias por su primer compra en <i>Zapateria Argento</i>!"
						+ "<br>Adjuntamos la <b>factura</b> correspondiente a ella. <i>Que tenga buen dia</i>!";
			}else {
				mensaje = "Hola, <b>" + cliente.getNombre() + "</b>! "
						+ "<br>Gracias por su compra en <i>Zapateria Argento</i>!"
						+ "<br>Adjuntamos la <b>factura</b> correspondiente a ella. <i>Que tenga buen dia</i>!";	
			}
			
			
			BodyPart texto = new MimeBodyPart();
			texto.setContent(mensaje, "text/html");

			BodyPart adjunto = new MimeBodyPart();
			adjunto.setDataHandler(new DataHandler(new FileDataSource(URLFactura)));
			adjunto.setFileName(URLFactura);

			MimeMultipart multiParte = new MimeMultipart();
			multiParte.addBodyPart(texto);
			multiParte.addBodyPart(adjunto);

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(correoRemitente));

			message.addRecipient(Message.RecipientType.TO, new InternetAddress(correoReceptor));
			message.setSubject(asunto);
			message.setContent(multiParte);

			Transport t = session.getTransport("smtp");
			t.connect(correoRemitente, contrasenia);
			t.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
			t.close();
			System.out.println("se envia factura a cliente");
//			JOptionPane.showMessageDialog(null, "Factura enviada");

		} catch (AddressException e) {
//			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
			JOptionPane.showMessageDialog(null, "Error, el primero xd");
			e.printStackTrace();
		} catch (MessagingException e) {
			JOptionPane.showMessageDialog(null, "Error, el segundo xd");
			e.printStackTrace();
		}

	}

	public static boolean esPrimerCompraDe(ClienteDTO cliente) {
		Factura facturaModelo = new Factura(new DAOSQLFactory());
		int cant=0;
		ArrayList<FacturaDTO> todasLasFacturas = (ArrayList<FacturaDTO>) facturaModelo.readAll();
		for(FacturaDTO f: todasLasFacturas) {
			if(f.getIdCliente()==cliente.getIdCliente()) {
				cant++;
			}
		}
		return cant <=1;
	}
	
	
	public static void enviarMailDeBienvenidaACliente(ClienteDTO cliente) {
		try {
			Properties props = new Properties();
			props.setProperty("mail.smtp.host", "smtp.gmail.com");
			props.setProperty("mail.smtp.starttls.enable", "true");
			props.setProperty("mail.smtp.port", "587");
			props.setProperty("mail.smtp.auth", "true");
			props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");
//		props.put("mail.debug", "true");
			Session session = Session.getDefaultInstance(props);

			String correoRemitente = "zapateriaargento198@gmail.com";
			String contrasenia = "zapateriaArgento123ContraseniaIndestructible";
			String correoReceptor = cliente.getCorreo();
			String asunto = "Correo de registro de cliente";
			
			String mensaje = "Hola, <b>" + cliente.getNombre() + "</b>!."
					+ "<br>Le damos la bienvenida a <i>ZapateriaArgento</i>!"
					+ "<br>Esperamos con ansias su primer compra!";			
			
			MimeMessage message = new MimeMessage(session);

			BodyPart texto = new MimeBodyPart();
			texto.setContent(mensaje, "text/html");

			MimeMultipart multiParte = new MimeMultipart();
			multiParte.addBodyPart(texto);			
			
			message.setFrom(new InternetAddress(correoRemitente));

			message.addRecipient(Message.RecipientType.TO, new InternetAddress(correoReceptor));

			message.setSubject(asunto);
			message.setText(mensaje);
			message.setContent(multiParte);

			Transport t = session.getTransport("smtp");
			t.connect(correoRemitente, contrasenia);
			t.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
			t.close();
			System.out.println("se envia mail de registro de cliente");
		} catch (AddressException e) {
//		Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
			JOptionPane.showMessageDialog(null, "Error, el primero xd");
			e.printStackTrace();
		} catch (MessagingException e) {
			JOptionPane.showMessageDialog(null, "Error, el segundo xd");
			e.printStackTrace();
		}
	}
	
	
	
}
