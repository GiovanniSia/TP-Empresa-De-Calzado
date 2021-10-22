package presentacion.controlador;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

import dto.PedidosPendientesDTO;
import dto.ProveedorDTO;
import modelo.PedidosPendientes;
import modelo.Proveedor;
import persistencia.dao.mysql.DAOSQLFactory;

public class EnviarCorreosAProveedoresAutomatico {

	
	
	@SuppressWarnings("deprecation")
	public static void verificarEnvioDeMailsAutomatico() throws ParseException {
		
		String diaDeEnvio = "viernes";
//		int horaDeEnvio = 20; 
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy"); 
		String fecha = dtf.format(LocalDateTime.now());
		
		SimpleDateFormat inFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = inFormat.parse(fecha);
		SimpleDateFormat outFormat = new SimpleDateFormat("EEEE");
		String dia = outFormat.format(date); 
		//goal devuelve el dia actual
		
//		System.out.println("El dia actual: "+dia+" - El dia que que digo yo: "+ diaDeEnvio);
		if(dia.equals(diaDeEnvio)) {
	
			Timer timer = new Timer();
			
			TimerTask tarea = new TimerTask() {
	
				@Override
				public void run() {
					//Enviar mails
					enviarMails();

					System.out.println("se ejecuta el timer");
					
				}
			};
			//121 = 2021, 9 = mes (10), 21 dia, 20 hora, 45 minutos, 00 segundos
			//                 año,m,d ,hr,m ,seg
			Date fe = new Date(121,9,22,13,04,00);//fecha de hoy 21/10/21
			
			timer.schedule(tarea, fe);
		
		}	
	}
	
	private static void enviarMails() {
		Proveedor proveedor = new Proveedor(new DAOSQLFactory());
		List<ProveedorDTO> todosLosProveedores = new ArrayList<ProveedorDTO>();
		todosLosProveedores = proveedor.readAll();
		
		List<PedidosPendientesDTO> todosLosPedidos = new ArrayList<PedidosPendientesDTO>();
		PedidosPendientes pedidosPendientes = new PedidosPendientes(new DAOSQLFactory());
		todosLosPedidos = pedidosPendientes.readAll();
		
		ArrayList<PedidosPendientesDTO> pedidosDeProv = new ArrayList<PedidosPendientesDTO>(); 
		for(ProveedorDTO prov: todosLosProveedores) {
			for(PedidosPendientesDTO pedido: todosLosPedidos) {
//				System.out.println("Prov: "+prov.getNombre()+" - "+prov.getId()+"\nPedido: "+pedido.getNombreMaestroProducto()+" - "+pedido.getIdProveedor());
				if(prov.getId() == pedido.getIdProveedor()) {
					pedidosDeProv.add(pedido);
//					
				}	
			}
//			enviarMail(prov,pedidosDeProv);
			if(pedidosDeProv.size()!=0) {
//				System.out.println("se envia el mail");
//				imprimirMail(pedidosDeProv);
				enviarMail(prov,pedidosDeProv);
			}
			
			pedidosDeProv.removeAll(pedidosDeProv);
		}
	}
	
//	private static void imprimirMail(ArrayList<PedidosPendientesDTO> pedidosDeProv) {	
//		String mensaje="";
//		for(PedidosPendientesDTO p: pedidosDeProv) {
//			mensaje = p.getNombreMaestroProducto() + " - "+p.getCantidad()+ "\n";
//		}
//		System.out.println("el msj: \n"+mensaje);
//	}
	
	private static void enviarMail(ProveedorDTO proveedor,ArrayList<PedidosPendientesDTO> pedidosDeProv) {
		try {
			
			String mensaje="";
			for(PedidosPendientesDTO p: pedidosDeProv) {
				mensaje = mensaje + p.getNombreMaestroProducto() + " - "+p.getCantidad()+ "\n";
			}
			
			Properties props = new Properties();
			props.setProperty("mail.smtp.host", "smtp.gmail.com");
			props.setProperty("mail.smtp.starttls.enable", "true");
			props.setProperty("mail.smtp.port", "587");
			props.setProperty("mail.smtp.auth", "true");
			props.setProperty("mail.smtp.ssl.trust", "*");
//			props.put("mail.debug", "true");
			Session session = Session.getDefaultInstance(props);

			String correoRemitente = "subelza150@gmail.com";
			String contrasenia = "";
			String correoReceptor = "sebastianx3600@gmail.com";
			String asunto = "Prueba";
			
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
//			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
			JOptionPane.showMessageDialog(null, "Error, el primero xd");
			e.printStackTrace();
		} catch (MessagingException e) {
			JOptionPane.showMessageDialog(null, "Error, el segundo xd");
			e.printStackTrace();
		}
	}
}
