package presentacion.controlador.gerente;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import dto.HistorialCambioClienteDTO;
import modelo.HistorialCambioCliente;
import presentacion.vista.gerente.VentanaHistorialDeCambiosCliente;

public class ControladorHistorialDeCambiosDeCliente {
	
	HistorialCambioCliente historialDeCambiosCliente;
	VentanaHistorialDeCambiosCliente ventanahistorialDeCambiosCliente; 
	
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
		this.ventanahistorialDeCambiosCliente = new VentanaHistorialDeCambiosCliente();
		this.listaHistorialCambioCliente = this.historialDeCambiosCliente.readAll();
		
		this.ventanahistorialDeCambiosCliente.getBtnVolverAModificarProducto().addActionListener(a -> salir());
		
		llenarTabla();
	}

	public void mostrarVentana() {
		this.ventanahistorialDeCambiosCliente.show();
	}
	
	public void cerrarVentana() {
		this.ventanahistorialDeCambiosCliente.cerrar();
	}
	
	public void llenarTabla() {
		this.ventanahistorialDeCambiosCliente.getModelhistorialCambioCliente().setRowCount(0);// borrar datos de la tabla
		this.ventanahistorialDeCambiosCliente.getModelhistorialCambioCliente().setColumnCount(0);
		this.ventanahistorialDeCambiosCliente.getModelhistorialCambioCliente().setColumnIdentifiers(this.ventanahistorialDeCambiosCliente.getNombreColumnas());
		
		for(HistorialCambioClienteDTO h : this.listaHistorialCambioCliente) {
			agregarATabla(h);
		}
		
	}

	public void agregarATabla(HistorialCambioClienteDTO h) {
		int id = h.getId();
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
		BigDecimal limiteCreditoAntiguo = new BigDecimal(limiteCreditoAntigu);
		
		double limiteCreditoNuev = h.getLimiteCreditoNuevo();
		BigDecimal limiteCreditoNuevo = new BigDecimal(limiteCreditoNuev);
		
		double creditoDisponibleAntigu = h.getCreditoDisponibleAntiguo();
		BigDecimal creditoDisponibleAntiguo = new BigDecimal(creditoDisponibleAntigu);
		
		
		double creditoDisponibleNuev = h.getCreditoDisponibleNuevo();
		BigDecimal creditoDisponibleNuevo = new BigDecimal(creditoDisponibleNuev);
		
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
		
		Object[] fila = {id,idEmpleado,fecha,nombreAntiguo,nombreNuevo,apellidoAntiguo,apellidoNuevo,CUILAntiguo,CUILNuevo,correoAntiguo,correoNuevo,limiteCreditoAntiguo,limiteCreditoNuevo,creditoDisponibleAntiguo,creditoDisponibleNuevo,tipoClienteAntiguo,tipoClienteNuevo,impuestoAFIPAntiguo,impuestoAFIPNuevo,estadoAntiguo,estadoNuevo,calleAntiguo,calleNuevo,alturaAntiguo,alturaNuevo,paisAntiguo,paisNuevo,provinciaAntiguo,provinciaNuevo,localidadAntiguo,localidadNuevo,codPostalAntiguo,codPostalNuevo};
		
		this.ventanahistorialDeCambiosCliente.getModelhistorialCambioCliente().addRow(fila);
	}

	public void salir() {
		this.ventanahistorialDeCambiosCliente.cerrar();
		this.controladorGestionarClientes.inicializar();
		this.controladorGestionarClientes.mostrarVentana();
	}
	
}
