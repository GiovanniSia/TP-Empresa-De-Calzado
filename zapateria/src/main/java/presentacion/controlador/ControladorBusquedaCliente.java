package presentacion.controlador;


import java.awt.event.ActionEvent;
import java.util.List;

import dto.ClienteDTO;
import modelo.Cliente;
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
	
		this.ventanaBusquedaCliente = new VentanaBusquedaCliente();
		
		this.ventanaBusquedaCliente.getBtnAtras().addActionListener(a -> atras(a));
		this.ventanaBusquedaCliente.getBtnPasarAVenta().addActionListener(p ->pasarAVenta(p));
		this.ventanaBusquedaCliente.getTxtFieldCodCliente().addActionListener(f -> filtrarPorCodCliente(f));
		this.ventanaBusquedaCliente.getTxtFieldNombre().addActionListener(f -> filtrarPorNombre(f));
		this.ventanaBusquedaCliente.getTxtFieldApellido().addActionListener(f-> filtrarPorApellido(f));
		this.ventanaBusquedaCliente.getTxtFieldDNI().addActionListener(f-> filtrarPorDNI(f));
		
		this.mostrarVentana();
		this.mostrarClientesEnTabla();
	}
	
	public void filtrarPorCodCliente(ActionEvent f) {
		clienteEnTabla=cliente.filtrarPorCodCliente(ventanaBusquedaCliente.getTxtFieldCodCliente().getText()); 	
		llenarTabla(clienteEnTabla);
	}
	
	public void filtrarPorNombre(ActionEvent f) {
		clienteEnTabla=cliente.filtrarPorNombre(ventanaBusquedaCliente.getTxtFieldNombre().getText()); 	
		llenarTabla(clienteEnTabla);
	}
	
	public void filtrarPorApellido(ActionEvent f) {
		clienteEnTabla=cliente.filtrarPorApellido(ventanaBusquedaCliente.getTxtFieldApellido().getText()); 	
		llenarTabla(clienteEnTabla);
	}
	
	public void filtrarPorDNI(ActionEvent f) {
		clienteEnTabla=cliente.filtrarPorDNI(ventanaBusquedaCliente.getTxtFieldDNI().getText()); 	
		llenarTabla(clienteEnTabla);
	}
	
	public void atras(ActionEvent a) {
		this.ventanaBusquedaCliente.cerrar();
	}
	
	public void pasarAVenta(ActionEvent p) {
		this.ventanaBusquedaCliente.cerrar();
	}
	
	public void mostrarVentana() {
		this.ventanaBusquedaCliente.show();
	}
	
	public void mostrarClientesEnTabla() {
		this.clienteEnTabla = cliente.readAll();
		this.llenarTabla(clienteEnTabla);
	}

	public void llenarTabla(List<ClienteDTO> clienteEnTabla) {
		this.ventanaBusquedaCliente.getModelCliente().setRowCount(0);
		this.ventanaBusquedaCliente.getModelCliente().setColumnCount(0);
		this.ventanaBusquedaCliente.getModelCliente().setColumnIdentifiers(this.ventanaBusquedaCliente.getNombreColumnas());
		for (ClienteDTO c : clienteEnTabla) {
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
