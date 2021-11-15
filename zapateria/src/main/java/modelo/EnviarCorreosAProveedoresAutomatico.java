package modelo;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import static java.nio.file.StandardWatchEventKinds.*;

import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import dto.PedidosPendientesDTO;
import dto.ProveedorDTO;
import modelo.compraVirtual.ProcesarCompraVirtual;
import persistencia.dao.mysql.DAOSQLFactory;

public class EnviarCorreosAProveedoresAutomatico extends Thread{
	
	public void run() {
		ConfiguracionBD config = ConfiguracionBD.getInstance();
		try {
			EnviarCorreosAProveedoresAutomatico.verificarEnvioDeMailsAutomatico(config);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void verificarEnvioDeMailsAutomatico(ConfiguracionBD config) throws ParseException, IOException {

		boolean yaFueSeteado = false;

		String diaDeEnvio = "";
		String horaProperties = "";
		try {
			diaDeEnvio = config.getValue("DiaDeEnvio");
			horaProperties = config.getValue("HoraDeEnvio");
		} catch (IOException e) {
			e.printStackTrace();
		}
		Timer timer = new Timer();

		Date fe;
		ProcesarCompraVirtual procesoDeCompraVirtual = new ProcesarCompraVirtual();
		procesoDeCompraVirtual.RutinaProcesarCompra(config);
		while (true) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			String fecha = dtf.format(LocalDateTime.now());

			SimpleDateFormat inFormat = new SimpleDateFormat("dd-MM-yyyy");
			Date date = inFormat.parse(fecha);
			SimpleDateFormat outFormat = new SimpleDateFormat("EEEE");
			String d = outFormat.format(date);

			String cadenaNormalize = Normalizer.normalize(d, Normalizer.Form.NFD);
			String dia = cadenaNormalize.replaceAll("[^\\p{ASCII}]", "");

//			DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm:ss");
//			String horaActual = tf.format(LocalDateTime.now());

			if (dia.equals(diaDeEnvio) && !yaFueSeteado) {
				try {
					yaFueSeteado = true;
					fe = establecerFechaParaElTimer(dia, horaProperties);
					timer.cancel();
					timer = new Timer();
					TimerTask tarea = new TimerTask() {

						@Override
						public void run() {
							// Enviar mails
							enviarMails();
						}
					};

					timer.schedule(tarea, fe);

				} catch (Exception e) {
					e.printStackTrace();
				}

			} else {
				if (!dia.equals(diaDeEnvio)) {
					timer.cancel();
					yaFueSeteado = false;
				}

			}

			try {
				if (seCambioElProperties()) {
					diaDeEnvio = config.getValue("DiaDeEnvio");
					horaProperties = config.getValue("HoraDeEnvio");
					procesoDeCompraVirtual.cambioConfig(config);
					yaFueSeteado = false;
				}
			} catch (InterruptedException e) {
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
		
		
		ArrayList<PedidosPendientesDTO> pedidosDeProv = new ArrayList<PedidosPendientesDTO>(); 
		for(ProveedorDTO prov: todosLosProveedores) {
			todosLosPedidos = pedidosPendientes.readAll();
			for(PedidosPendientesDTO pedido: todosLosPedidos) {
				if(prov.getId() == pedido.getIdProveedor() && pedido.getEstado().equals("En espera")) {
					pedidosDeProv.add(pedido);
//					System.out.println("se añade el pedido para el prov: "+prov.getId());
				}	
			}
			if(pedidosDeProv.size()!=0) {
				imprimirMail(pedidosDeProv);
				EnviadorDeMails.enviarMailAProveedor(prov,pedidosDeProv);
				marcarPedidoComoEnviado(pedidosDeProv);
//				System.out.println("se envia el mail!!");
			}
			
			pedidosDeProv.removeAll(pedidosDeProv);
		}
	}
	
	private static void marcarPedidoComoEnviado(ArrayList<PedidosPendientesDTO> pedidos) {
		PedidosPendientes pedidosPendietes = new PedidosPendientes(new DAOSQLFactory());
		for(PedidosPendientesDTO pedido: pedidos) {
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
			String fecha = dtf.format(LocalDateTime.now());
			
		    DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm:ss");
		    String hora = tf.format(LocalDateTime.now());
			
		    pedido.setEstado("Enviado");
		    pedido.setHoraEnvioMail(hora);
		    pedido.setFechaEnvioMail(fecha);
		    
		    boolean update = pedidosPendietes.update(pedido,pedido.getId());
		    
//			boolean update = pedidosPendietes.cambiarEstado(pedido.getId(), "Enviado");
		    
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
	
	
	public static boolean seCambioElProperties() throws InterruptedException {
		  try (WatchService ws = FileSystems.getDefault().newWatchService()) {
	            // carpeta que deseamos monitorear
//			  	Path aux = Paths.get("config.properties");
			  	
	            Path dirToWatch = Paths.get("config");
//			  	Path dirToWatch = aux.toAbsolutePath();
	            // eventos que deseamos que nos envien notificaciones
	            dirToWatch.register(ws, ENTRY_CREATE, ENTRY_MODIFY, ENTRY_DELETE);
	            
	            // obtener el key
                WatchKey key = ws.take();
                
                // procesar los eventos ocurridos
                for (WatchEvent<?> event : key.pollEvents()) {
                    Kind<?> eventKind = event.kind();
                    if (eventKind == OVERFLOW) {
                        System.out.println("Event overflow occurred");
                        return true;
                    }
                    if (eventKind == ENTRY_MODIFY) {
                        System.out.println("Se modifico el coso");
                        return true;
                    }
                    
                    
                    // obtener informacion del evento ocurrido
                    @SuppressWarnings("unchecked")
					WatchEvent<Path> currEvent = (WatchEvent<Path>) event;
                    Path dirEntry = currEvent.context();

                    System.out.println(eventKind + " occurred on " + dirEntry);
                    return false;
                }
                
                // resetear el key
                boolean isKeyValid = key.reset();
                if (!isKeyValid) {
                    return false;
                }
                
   
            
            return false;
		  } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
		 return false;
	}
	
	
	public static boolean huboCambiosEnFichero() {
        try (WatchService ws = FileSystems.getDefault().newWatchService()) {
            // carpeta que deseamos monitorear
            Path dirToWatch = Paths.get("config");

            // eventos que deseamos que nos envien notificaciones
            dirToWatch.register(ws, ENTRY_CREATE, ENTRY_MODIFY, ENTRY_DELETE);
            System.out.println("Watching " + dirToWatch + " for events.");
            
            while (true) {
                // obtener el key
                WatchKey key = ws.take();
                
                // procesar los eventos ocurridos
                for (WatchEvent<?> event : key.pollEvents()) {
                    Kind<?> eventKind = event.kind();
                    if (eventKind == OVERFLOW) {
                        System.out.println("Event overflow occurred");
                        return true;
                    }
                    if (eventKind == ENTRY_MODIFY) {
                        System.out.println("Se modifico el coso");
                        return true;
                    }
                    
                    
                    // obtener informacion del evento ocurrido
                    WatchEvent<Path> currEvent = (WatchEvent<Path>) event;
                    Path dirEntry = currEvent.context();

                    System.out.println(eventKind + " occurred on " + dirEntry);
                    return true;
                }
                
                // resetear el key
                boolean isKeyValid = key.reset();
                if (!isKeyValid) {
                    break;
                }
                
            }
            
            return false;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }
	
	
}
