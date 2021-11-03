package presentacion.controlador.gerente;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import dto.ClienteDTO;
import modelo.Cliente;
import presentacion.controlador.Controlador;
import presentacion.controlador.ValidadorTeclado;
import presentacion.vista.gerente.VentanaGestionarClientes;

public class ControladorGestionarClientes {

	VentanaGestionarClientes ventanaGestionarClientes;
	
	Cliente cliente;
	List<ClienteDTO> todosLosClientes;
	
	List<ClienteDTO> clienteEnTabla;
	
	ControladorAltaCliente controladorAltaCliente;
	Controlador controlador;
	public ControladorGestionarClientes(Controlador controlador,Cliente cliente) {		
		this.controlador = controlador;
		this.cliente = cliente;
		
		this.todosLosClientes = new ArrayList<ClienteDTO>();
		this.clienteEnTabla = new ArrayList<ClienteDTO>();
		
		this.ventanaGestionarClientes = new VentanaGestionarClientes();
		
		this.ventanaGestionarClientes.getBtnAgregarCliente().addActionListener(a -> pasarAAgregarCliente(a));
		this.ventanaGestionarClientes.getBtnEditarCliente().addActionListener(a -> pasarAEditarCliente(a));
		this.ventanaGestionarClientes.getBtnAtras().addActionListener(a -> salir(a));
		
		
		// TextField
		this.ventanaGestionarClientes.getTxtFieldCodCliente().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				realizarBusqueda();
			}
		});

		this.ventanaGestionarClientes.getTxtFieldNombre().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				realizarBusqueda();
			}
		});

		this.ventanaGestionarClientes.getTxtFieldApellido().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				realizarBusqueda();
			}
		});

		this.ventanaGestionarClientes.getTxtFieldCUIL().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				realizarBusqueda();
			}
		});

	}

	public void setControladorAltaCliente(ControladorAltaCliente controladorAltaCliente) {
		this.controladorAltaCliente = controladorAltaCliente;
	}
	
	public void inicializar() {
		this.todosLosClientes = this.cliente.readAll();
		llenarTablaCompleta();	
		
		validarTeclado();
		
		
	}
	
	public void mostrarVentana() {
		this.ventanaGestionarClientes.mostrarVentana();
	}
	
	public void cerrarVentana() {
		this.ventanaGestionarClientes.cerrar();
	}
	
	public void salir(ActionEvent a) {
		this.ventanaGestionarClientes.cerrar();
		this.controlador.inicializar();
		this.controlador.mostrarVentanaMenuDeSistemas();
	}
	
	public void llenarTablaCompleta() {
		this.ventanaGestionarClientes.getModelCliente().setRowCount(0);//borrar datos de la tabla
		this.ventanaGestionarClientes.getModelCliente().setColumnCount(0);
		this.ventanaGestionarClientes.getModelCliente().setColumnIdentifiers(this.ventanaGestionarClientes.getNombreColumnas());
		this.clienteEnTabla.removeAll(this.clienteEnTabla);
		
		for (ClienteDTO c : this.todosLosClientes) {
			agregarATabla(c);
		}
		
	}

	
	public void realizarBusqueda() {
		String txtcodCliente = this.ventanaGestionarClientes.getTxtFieldCodCliente().getText();
		String txtNombre = this.ventanaGestionarClientes.getTxtFieldNombre().getText();
		String txtApellido = this.ventanaGestionarClientes.getTxtFieldApellido().getText();
		String txtCUIL = this.ventanaGestionarClientes.getTxtFieldCUIL().getText();
		List<ClienteDTO> clienteAproximados = this.cliente.getClienteAproximado("IdCliente", txtcodCliente, "Nombre",txtNombre, "Apellido", txtApellido, "CUIL", txtCUIL, null, null);
		llenarTablaAprox(clienteAproximados);
	}
	
	public void llenarTablaAprox(List<ClienteDTO> clienteEnTabla) {		
		this.ventanaGestionarClientes.getModelCliente().setRowCount(0);
		this.ventanaGestionarClientes.getModelCliente().setColumnCount(0);
		this.ventanaGestionarClientes.getModelCliente().setColumnIdentifiers(this.ventanaGestionarClientes.getNombreColumnas());
		this.clienteEnTabla.removeAll(this.clienteEnTabla);
		
		for (ClienteDTO c : clienteEnTabla) {
			agregarATabla(c);
		}
	}
	
	public void agregarATabla(ClienteDTO c) {
		int codCliente = c.getIdCliente();
		String nombre = c.getNombre();
		String apellido = c.getApellido();
		String CUIL = c.getCUIL();
		String correo = c.getCorreo();
		double limiteCredito = c.getLimiteCredito();
		double creditoDisponible = c.getCreditoDisponible();
		String estado = c.getEstado();
		if (!estado.equals("Inactivo")) {
			Object[] fila = { codCliente, nombre, apellido, CUIL, correo, limiteCredito, creditoDisponible,	estado };
			this.ventanaGestionarClientes.getModelCliente().addRow(fila);
			this.clienteEnTabla.add(c);
		}
	}
	
	
	public void pasarAAgregarCliente(ActionEvent a) {
//		int filaSeleccionada = this.ventanaGestionarClientes.getTablaClientes().getSelectedRow();
//		if(filaSeleccionada == -1) {
//        	JOptionPane.showMessageDialog(null, "Debe seleccionar un cliente", "Error", JOptionPane.OK_OPTION);
//        	return;
//		}
		this.ventanaGestionarClientes.cerrar();
		this.controladorAltaCliente.inicializar();
		this.controladorAltaCliente.mostrarVentana();
		
	}

	public void pasarAEditarCliente(ActionEvent a) {
		int filaSeleccionada = this.ventanaGestionarClientes.getTablaClientes().getSelectedRow();
		if(filaSeleccionada==-1) {
			JOptionPane.showMessageDialog(null, "Debe seleccionar un cliente", "Error", JOptionPane.OK_OPTION);
			return;
		}
		ClienteDTO clienteSeleccionado = this.clienteEnTabla.get(filaSeleccionada);
		
		this.ventanaGestionarClientes.cerrar();
		this.controladorAltaCliente.inicializar();
		this.controladorAltaCliente.setearDatosDeCliente(clienteSeleccionado);
		this.controladorAltaCliente.mostrarVentanaEditar();		
	}
	
	public void validarTeclado() {
		
		this.ventanaGestionarClientes.getTxtFieldCodCliente().addKeyListener((new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarSoloNumeros(e);
			}
		}));
		
		this.ventanaGestionarClientes.getTxtFieldNombre().addKeyListener((new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarLetrasYEspacios(e);
			}
		}));
		
		this.ventanaGestionarClientes.getTxtFieldApellido().addKeyListener((new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarLetrasYEspacios(e);
			}
		}));
		
		this.ventanaGestionarClientes.getTxtFieldCUIL().addKeyListener((new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarSoloNumeros(e);
			}
		}));
		
	}
}
