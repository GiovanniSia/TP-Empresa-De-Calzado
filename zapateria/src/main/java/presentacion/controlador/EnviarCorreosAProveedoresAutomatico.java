package presentacion.controlador;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import modelo.ConfiguracionBD;
import modelo.PedidosPendientes;
import modelo.Proveedor;
import persistencia.dao.mysql.DAOSQLFactory;

public class EnviarCorreosAProveedoresAutomatico {

	
	
	public static void verificarEnvioDeMailsAutomatico(ConfiguracionBD config) throws ParseException {
		
		String diaDeEnvio="";
		String horaProperties="";
		try {
			diaDeEnvio = config.getValue("DiaDeEnvio");
			horaProperties = config.getValue("HoraDeEnvio");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy"); 
		String fecha = dtf.format(LocalDateTime.now());
		
		SimpleDateFormat inFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = inFormat.parse(fecha);
		SimpleDateFormat outFormat = new SimpleDateFormat("EEEE");
		String dia = outFormat.format(date); 
		//goal devuelve el dia actual
		
	    DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm:ss");
	    String horaActual = tf.format(LocalDateTime.now());
		
		
		System.out.println("El dia y hora segun el properties: "+diaDeEnvio +" - "+ horaProperties+"\nEl dia y hora actual: "+dia+" - "+horaActual);

	
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
			//                 a�o,m,d ,hr,m ,seg
		
		if(dia.equals(diaDeEnvio)) {
			//SI HOY ES EL DIA EN QUE SE ENVIA, ENTONCES SE PREPARA EL TIMER PARA QUE SE ENVIE
			Date fe;
			try {
				fe = establecerFechaParaElTimer(dia,horaProperties);
				timer.schedule(tarea, fe);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		
			
	}
	
	@SuppressWarnings("deprecation")
	private static Date establecerFechaParaElTimer(String NombreDia,String hora) {
		Calendar fecha = new GregorianCalendar();
		Date d = new Date();
		int anio = d.getYear();
		int mes = d.getMonth();
		int diaActual = fecha.get(Calendar.DAY_OF_MONTH);
		
		int h = Integer.parseInt(hora.substring(0, 2));
		int min = Integer.parseInt(hora.substring(3, 5));
		int seg = Integer.parseInt(hora.substring(6, 8));
		 System.out.println("fecha: "+anio+"/"+mes+"/"+diaActual+" "+h+":"+min+":"+seg);
		return new Date(anio,mes,diaActual,h,min,seg);//fecha de hoy 21/10/21
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
				if(prov.getId() == pedido.getIdProveedor() && pedido.getEstado().equals("En espera")) {
					pedidosDeProv.add(pedido);
//					
				}	
			}
//			enviarMail(prov,pedidosDeProv);
			if(pedidosDeProv.size()!=0) {
//				System.out.println("se envia el mail");
				imprimirMail(pedidosDeProv);
//				enviarMail(prov,pedidosDeProv);
				marcarPedidoComoEnviado(pedidosDeProv);
				System.out.println("se envia el mail!!");
			}
			
			pedidosDeProv.removeAll(pedidosDeProv);
		}
	}
	
	private static void marcarPedidoComoEnviado(ArrayList<PedidosPendientesDTO> pedidos) {
		PedidosPendientes pedidosPendietes = new PedidosPendientes(new DAOSQLFactory());
		for(PedidosPendientesDTO pedido: pedidos) {
			boolean update = pedidosPendietes.cambiarEstado(pedido.getId(), "Enviado");
			if(!update) {
				System.out.println("ha ocurrido un error al marcar al pedido: "+pedido.getNombreMaestroProducto()+" - "+pedido.getCantidad()+" como enviado");
			}
		}
		
	}

	private static void imprimirMail(ArrayList<PedidosPendientesDTO> pedidosDeProv) {	
		String mensaje="";
		for(PedidosPendientesDTO p: pedidosDeProv) {
			mensaje = p.getNombreMaestroProducto() + " - "+p.getCantidad()+ "\n";
		}
		System.out.println("el msj: \n"+mensaje);
	}
	
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
