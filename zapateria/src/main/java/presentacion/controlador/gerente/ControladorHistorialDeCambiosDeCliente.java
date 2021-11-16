package presentacion.controlador.gerente;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dto.HistorialCambioClienteDTO;
import modelo.HistorialCambioCliente;
import presentacion.vista.gerente.VentanaHistorialDeCambiosCliente;

public class ControladorHistorialDeCambiosDeCliente {
	
	HistorialCambioCliente historialDeCambiosCliente;
	VentanaHistorialDeCambiosCliente ventanaHistorialDeCambiosCliente; 
	
	List<HistorialCambioClienteDTO> listaHistorialCambioCliente;
	
	ControladorGestionarClientes controladorGestionarClientes;
	
	public ControladorHistorialDeCambiosDeCliente(HistorialCambioCliente historialDeCambiosCliente) {
		this.historialDeCambiosCliente = historialDeCambiosCliente;
		this.listaHistorialCambioCliente = new ArrayList<HistorialCambioClienteDTO>();
	}
	
	public void setControladorGestionarClientes(ControladorGestionarClientes controladorGestionarClientes) {
		this.controladorGestionarClientes = controladorGestionarClientes;
	}
	
	public void inicializar() {
		this.ventanaHistorialDeCambiosCliente = new VentanaHistorialDeCambiosCliente();
		this.listaHistorialCambioCliente = this.historialDeCambiosCliente.readAll();
		
		this.ventanaHistorialDeCambiosCliente.getBtnVolverAModificarProducto().addActionListener(a -> salir());
		
		
		
		this.ventanaHistorialDeCambiosCliente.getTextFieldIdCliente().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				realizarBusqueda();
			}
		});
		this.ventanaHistorialDeCambiosCliente.getTextFieldIdCliente().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				realizarBusqueda();
			}
		});
		this.ventanaHistorialDeCambiosCliente.getTextNombreCliente().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				realizarBusqueda();
			}
		});
		this.ventanaHistorialDeCambiosCliente.getTextCUIL().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				realizarBusqueda();
			}
		});
		
		this.ventanaHistorialDeCambiosCliente.getDateChooserFechaMod().addPropertyChangeListener(
				new PropertyChangeListener() {

					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						System.out.println("valor del datochooser: "+ventanaHistorialDeCambiosCliente.getDateChooserFechaMod().getDate());
						realizarBusqueda();
					}
			});
		
		this.ventanaHistorialDeCambiosCliente.getBtnBorrarFiltroFecha().addActionListener(a -> borrarFiltroFecha());
		
		llenarTabla();
	}

	public void mostrarVentana() {
		this.ventanaHistorialDeCambiosCliente.show();
	}
	
	public void cerrarVentana() {
		this.ventanaHistorialDeCambiosCliente.cerrar();
	}
	
	public void llenarTabla() {
		this.ventanaHistorialDeCambiosCliente.getModelhistorialCambioCliente().setRowCount(0);// borrar datos de la tabla
		this.ventanaHistorialDeCambiosCliente.getModelhistorialCambioCliente().setColumnCount(0);
		this.ventanaHistorialDeCambiosCliente.getModelhistorialCambioCliente().setColumnIdentifiers(this.ventanaHistorialDeCambiosCliente.getNombreColumnas());
		
		for(HistorialCambioClienteDTO h : this.listaHistorialCambioCliente) {
			agregarATabla(h);
		}
		
	}

	public void agregarATabla(HistorialCambioClienteDTO h) {
		int idCliente = h.getIdCliente();
		int idEmpleado = h.getIdEmpleado();
		 
		String fecha = h.getFecha();
		
		String nombreAntiguo = h.getNombreAntiguo();
		String nombreNuevo = h.getNombreNuevo();
		
		String apellidoAntiguo= h.getApellidoAntiguo();
		String apellidoNuevo = h.getApellidoNuevo();
		
		String CUILAntiguo = h.getCUILAntiguo();
		String CUILNuevo = h.getCUILNuevo();
		
		String correoAntiguo = h.getCorreoAntiguo();
		String correoNuevo = h.getCorreoNuevo();
		
		double limiteCreditoAntigu = h.getLimiteCreditoAntiguo();
		BigDecimal limiteCreditoAntiguo = new BigDecimal(limiteCreditoAntigu).setScale(2, RoundingMode.HALF_UP);;
		
		double limiteCreditoNuev = h.getLimiteCreditoNuevo();
		BigDecimal limiteCreditoNuevo = new BigDecimal(limiteCreditoNuev).setScale(2, RoundingMode.HALF_UP);;
		
		double creditoDisponibleAntigu = h.getCreditoDisponibleAntiguo();
		BigDecimal creditoDisponibleAntiguo = new BigDecimal(creditoDisponibleAntigu).setScale(2, RoundingMode.HALF_UP);;
		
		
		double creditoDisponibleNuev = h.getCreditoDisponibleNuevo();
		BigDecimal creditoDisponibleNuevo = new BigDecimal(creditoDisponibleNuev).setScale(2, RoundingMode.HALF_UP);;
		
		String tipoClienteAntiguo = h.getTipoClienteAntiguo();
		String tipoClienteNuevo = h.getTipoClienteNuevo();
		
		String impuestoAFIPAntiguo = h.getImpuestoAFIPAntiguo();
		String impuestoAFIPNuevo = h.getImpuestoAFIPNuevo();
		
		String estadoAntiguo = h.getEstadoAntiguo();
		String estadoNuevo = h.getEstadoNuevo();
		
		String calleAntiguo = h.getCalleAntiguo();
		String calleNuevo = h.getCalleNuevo();
		
		String alturaAntiguo = h.getAlturaAntiguo();
		String alturaNuevo = h.getAlturaNuevo();
		
		String paisAntiguo = h.getPaisAntiguo();
		String paisNuevo = h.getPaisNuevo();
		
		String provinciaAntiguo = h.getProvinciaAntiguo();
		String provinciaNuevo = h.getProvinciaNuevo();
		
		String localidadAntiguo = h.getLocalidadAntiguo();
		String localidadNuevo = h.getLocalidadNuevo();
		
		String codPostalAntiguo = h.getCodPostalAntiguo();
		String codPostalNuevo = h.getCodPostalNuevo();
		
		Object[] fila = {idCliente,idEmpleado,fecha,nombreAntiguo,nombreNuevo,apellidoAntiguo,apellidoNuevo,CUILAntiguo,CUILNuevo,correoAntiguo,correoNuevo,limiteCreditoAntiguo,limiteCreditoNuevo,creditoDisponibleAntiguo,creditoDisponibleNuevo,tipoClienteAntiguo,tipoClienteNuevo,impuestoAFIPAntiguo,impuestoAFIPNuevo,estadoAntiguo,estadoNuevo,calleAntiguo,calleNuevo,alturaAntiguo,alturaNuevo,paisAntiguo,paisNuevo,provinciaAntiguo,provinciaNuevo,localidadAntiguo,localidadNuevo,codPostalAntiguo,codPostalNuevo};
		
		this.ventanaHistorialDeCambiosCliente.getModelhistorialCambioCliente().addRow(fila);
	}

	public void salir() {
		this.ventanaHistorialDeCambiosCliente.cerrar();
		this.controladorGestionarClientes.inicializar();
		this.controladorGestionarClientes.mostrarVentana();
	}
	
	public void realizarBusqueda() {
		String idEmpleado = this.ventanaHistorialDeCambiosCliente.getTxtCodEmpleado().getText();
		String idCliente = this.ventanaHistorialDeCambiosCliente.getTextFieldIdCliente().getText();
		String nombreCliente = this.ventanaHistorialDeCambiosCliente.getTextNombreCliente().getText();
		String CUILCliente = this.ventanaHistorialDeCambiosCliente.getTextCUIL().getText();

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date fech = this.ventanaHistorialDeCambiosCliente.getDateChooserFechaMod().getDate();
		
		String fecha = null;
        if (fech != null) {
            fecha = dateFormat.format(fech);
        }
        
        ArrayList<HistorialCambioClienteDTO> listaFiltrada = (ArrayList<HistorialCambioClienteDTO>) this.historialDeCambiosCliente.obtenerListaFiltrada("IdEmpleado", idEmpleado, "IdCliente", idCliente, "NombreNuevo", nombreCliente, "CUILNuevo", CUILCliente, "Fecha", fecha);
        
        llenarTablaFiltrada(listaFiltrada);
        
	}
	
	public void llenarTablaFiltrada(ArrayList<HistorialCambioClienteDTO> lista) {
		this.ventanaHistorialDeCambiosCliente.getModelhistorialCambioCliente().setRowCount(0);// borrar datos de la tabla
		this.ventanaHistorialDeCambiosCliente.getModelhistorialCambioCliente().setColumnCount(0);
		this.ventanaHistorialDeCambiosCliente.getModelhistorialCambioCliente().setColumnIdentifiers(this.ventanaHistorialDeCambiosCliente.getNombreColumnas());
		
		for(HistorialCambioClienteDTO h : lista) {
			agregarATabla(h);
		}
	}

	public void borrarFiltroFecha() {
		this.ventanaHistorialDeCambiosCliente.getDateChooserFechaMod().setDate(null);
	}
	
}
