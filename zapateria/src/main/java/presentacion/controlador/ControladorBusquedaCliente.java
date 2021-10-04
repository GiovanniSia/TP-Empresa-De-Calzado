package presentacion.controlador;


import java.awt.event.ActionEvent;
import java.util.List;

import dto.ClienteDTO;
import modelo.Cliente;
import persistencia.conexion.Conexion;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.vista.VentanaBusquedaCliente;

public class ControladorBusquedaCliente {
	private VentanaBusquedaCliente ventanaBusquedaCliente;
	private Cliente cliente;
	private List<ClienteDTO> clienteEnTabla;

	public ControladorBusquedaCliente(VentanaBusquedaCliente ventanaBusquedaCliente, Cliente cliente) {
		this.ventanaBusquedaCliente = ventanaBusquedaCliente;
		this.cliente = cliente;
	}
	
	public void inicializar() {
		this.cliente = new Cliente(new DAOSQLFactory());
		this.clienteEnTabla = this.cliente.readAll();
		this.mostrarVentana();
		
		//this.ventanaBusquedaCliente.mostrarVentana();
		this.ventanaBusquedaCliente = new VentanaBusquedaCliente();
		this.ventanaBusquedaCliente.getBtnAtras().addActionListener(a -> atras(a));
		this.ventanaBusquedaCliente.getBtnPasarAVenta().addActionListener(p ->pasarAVenta(p));
	}
	
	public void atras(ActionEvent a) {
		this.ventanaBusquedaCliente.cerrar();
	}
	
	public void pasarAVenta(ActionEvent p) {
		this.ventanaBusquedaCliente.cerrar();
	}
	
	public void mostrarVentana() {
		llenarTabla();
		this.ventanaBusquedaCliente.show();
	}

	public void llenarTabla() {
		this.ventanaBusquedaCliente.getModelCliente().setRowCount(0);
		this.ventanaBusquedaCliente.getModelCliente().setColumnCount(0);
		this.ventanaBusquedaCliente.getModelCliente().setColumnIdentifiers(this.ventanaBusquedaCliente.getNombreColumnas());
		for (ClienteDTO c : this.clienteEnTabla) {
			int codCliente = c.getIdCliente();
			String nombre = c.getNombre();
			String apellido = c.getApellido();
			String DNI = c.getDNI();
			String correo = c.getCorreo();
			String estado = c.getEstado();
			
			Object[] fila = { codCliente, nombre, apellido, DNI, correo, estado };
			this.ventanaBusquedaCliente.getModelCliente().addRow(fila);
		}
	}
	
	
	
}
