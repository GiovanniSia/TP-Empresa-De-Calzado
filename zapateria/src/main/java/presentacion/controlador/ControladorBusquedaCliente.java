package presentacion.controlador;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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

		//Lambdas para filtrar presionando ENTER
//		this.ventanaBusquedaCliente.getTxtFieldCodCliente().addActionListener(f -> filtrarPorCodCliente(f));
//		this.ventanaBusquedaCliente.getTxtFieldNombre().addActionListener(f -> filtrarPorNombre(f));
//		this.ventanaBusquedaCliente.getTxtFieldApellido().addActionListener(f-> filtrarPorApellido(f));
//		this.ventanaBusquedaCliente.getTxtFieldDNI().addActionListener(f-> filtrarPorDNI(f));
		
		this.ventanaBusquedaCliente.getTxtFieldCodCliente().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				clienteEnTabla=cliente.filtrarPorCodCliente(ventanaBusquedaCliente.getTxtFieldCodCliente().getText()); 	
				llenarTabla(clienteEnTabla);
			}
		});;
		this.ventanaBusquedaCliente.getTxtFieldNombre().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				clienteEnTabla=cliente.filtrarPorNombre(ventanaBusquedaCliente.getTxtFieldNombre().getText()); 	
				llenarTabla(clienteEnTabla);
			}
		});;
		
		this.ventanaBusquedaCliente.getTxtFieldApellido().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				clienteEnTabla=cliente.filtrarPorApellido(ventanaBusquedaCliente.getTxtFieldApellido().getText()); 	
				llenarTabla(clienteEnTabla);
			}
		});;
		this.ventanaBusquedaCliente.getTxtFieldDNI().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				clienteEnTabla=cliente.filtrarPorDNI(ventanaBusquedaCliente.getTxtFieldDNI().getText()); 	
				llenarTabla(clienteEnTabla);
			}
		});;
		this.mostrarVentana();
		this.mostrarClientesEnTabla();
	}
	
	//Filtrar presionando ENTER
//	public void filtrarPorCodCliente(ActionEvent f) {
//		clienteEnTabla=cliente.filtrarPorCodCliente(ventanaBusquedaCliente.getTxtFieldCodCliente().getText()); 	
//		llenarTabla(clienteEnTabla);
//	}
//	
//	public void filtrarPorNombre(ActionEvent f) {
//		clienteEnTabla=cliente.filtrarPorNombre(ventanaBusquedaCliente.getTxtFieldNombre().getText()); 	
//		llenarTabla(clienteEnTabla);
//	}
//	
//	public void filtrarPorApellido(ActionEvent f) {
//		clienteEnTabla=cliente.filtrarPorApellido(ventanaBusquedaCliente.getTxtFieldApellido().getText()); 	
//		llenarTabla(clienteEnTabla);
//	}
//	
//	public void filtrarPorDNI(ActionEvent f) {
//		clienteEnTabla=cliente.filtrarPorDNI(ventanaBusquedaCliente.getTxtFieldDNI().getText()); 	
//		llenarTabla(clienteEnTabla);
//	}
	
	public void atras(ActionEvent a) {
		this.ventanaBusquedaCliente.cerrar();
	}
	
	//Aqui ya se selecciona un cliente, agregar advertencia si no selecciono algun cliente
	public void pasarAVenta(ActionEvent p) {
		
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
