package modelo;

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

public class EnviadorDeMails {

	
	//se recibe un proveedor con todos sus pedidos
	public static void enviarMailAProveedor(ProveedorDTO proveedor, ArrayList<PedidosPendientesDTO> pedidosDeProv) {
		try {

			String mensaje = "";
			for (PedidosPendientesDTO p : pedidosDeProv) {
				mensaje = mensaje + "Nombre de Producto: " + p.getNombreMaestroProducto() + "\nCantidad: "
						+ p.getCantidad() + "\nUnidad de medida: " + p.getUnidadMedida() + "\n";
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

			JOptionPane.showMessageDialog(null, "Correo enviado");

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
				mensaje = "Que onda <b>" + cliente.getNombre() + "</b>, gracias por TU PRIMER compra en <i>Zapateria Argento</i>";
			}else {
				mensaje = "Que onda <b>" + cliente.getNombre() + "</b>, gracias por comprar xd";	
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
			String mensaje = "Bienvenido " + cliente.getNombre() + " a ZapateriaArgento, ansiamos por su futura compra";
			MimeMessage message = new MimeMessage(session);

			message.setFrom(new InternetAddress(correoRemitente));

			message.addRecipient(Message.RecipientType.TO, new InternetAddress(correoReceptor));

			message.setSubject(asunto);
			message.setText(mensaje);

			Transport t = session.getTransport("smtp");
			t.connect(correoRemitente, contrasenia);
			t.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
			t.close();
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
