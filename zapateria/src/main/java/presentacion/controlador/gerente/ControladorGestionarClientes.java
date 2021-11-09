package presentacion.controlador.gerente;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import dto.ClienteDTO;
import inicioSesion.empleadoProperties;
import modelo.Cliente;
import presentacion.controlador.Controlador;
import presentacion.controlador.ValidadorTeclado;
import presentacion.controlador.supervisor.ControladorGenerarPedidoAProveedorManualmente;
import presentacion.vista.gerente.VentanaGestionarClientes;

public class ControladorGestionarClientes {

	static String tipoEmpleado = "";

	public void obtenerDatosPropertiesEmpleado() {
		try {
			empleadoProperties empleadoProp = empleadoProperties.getInstance();
			tipoEmpleado = empleadoProp.getValue("TipoEmpleado");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	VentanaGestionarClientes ventanaGestionarClientes;
	
	Cliente cliente;
	List<ClienteDTO> todosLosClientes;
	
	List<ClienteDTO> clienteEnTabla;
	
	ControladorAltaCliente controladorAltaCliente;
	Controlador controlador;
	
	ControladorGenerarPedidoAProveedorManualmente controladorGenerarPedidoAProveedorManualmente;
	
	ControladorHistorialDeCambiosDeCliente controladorHistorialDeCambiosDeCliente;
	
	public ControladorGestionarClientes(Controlador controlador,Cliente cliente) {		
		this.controlador = controlador;
		this.cliente = cliente;
		
		this.todosLosClientes = new ArrayList<ClienteDTO>();
		this.clienteEnTabla = new ArrayList<ClienteDTO>();
				
	}

	public void setControladorAltaCliente(ControladorAltaCliente controladorAltaCliente) {
		this.controladorAltaCliente = controladorAltaCliente;
	}

	public void setControladorHistorialDeCambiosDeCliente(ControladorHistorialDeCambiosDeCliente controladorHistorialDeCambiosDeCliente) {
		this.controladorHistorialDeCambiosDeCliente = controladorHistorialDeCambiosDeCliente;
	}
	
	public void inicializar() {
		obtenerDatosPropertiesEmpleado();
		
		this.ventanaGestionarClientes = new VentanaGestionarClientes();
		this.todosLosClientes = this.cliente.readAll();
		
		if(tipoEmpleado.equals("Vendedor")) {
			this.ventanaGestionarClientes.getBtnAgregarCliente().addActionListener(a -> pasarAAgregarCliente(a));
			mostrarVentanaParaVendedor();
		}else if(tipoEmpleado.equals("Supervisor")){
			mostrarVentanaParaSupervisor();
		}else {
			this.ventanaGestionarClientes.getBtnAgregarCliente().addActionListener(a -> pasarAAgregarCliente(a));
			this.ventanaGestionarClientes.getBtnEditarCliente().addActionListener(a -> pasarAEditarCliente(a));
			this.ventanaGestionarClientes.getBtnHistorialDeCambios().addActionListener(a -> pasarAHistorialDeClientes());				
		}
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
		
		
		llenarTablaCompleta();	
		
		validarTeclado();
		
	}
	
	public void mostrarVentanaParaSupervisor() {
		this.ventanaGestionarClientes.mostrarVentanaParaSupervisor();
	}
	
	public void mostrarVentanaParaVendedor() {
		this.ventanaGestionarClientes.mostrarVentanaParaVendedor();
	}
	
	public void mostrarVentana() {
		this.ventanaGestionarClientes.mostrarVentana();
	}
	
	public void cerrarVentana() {
		this.ventanaGestionarClientes.cerrar();
	}
	
	public void salir(ActionEvent a) {
		this.ventanaGestionarClientes.cerrar();
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
		
		double limiteCredit = c.getLimiteCredito();
		BigDecimal limiteCredito = new BigDecimal(limiteCredit);
		
		double creditoDisponibl = c.getCreditoDisponible();
		BigDecimal creditoDisponible = new BigDecimal(creditoDisponibl);
		
		String tipoCliente = c.getTipoCliente();
		String afip = c.getImpuestoAFIP();
		String estado = c.getEstado();
		String calle = c.getCalle();
		String altura = c.getAltura();
		String pais = c.getPais();
		String prov = c.getProvincia();
		String local = c.getLocalidad();
		String codPostal = c.getCodPostal();
		
		Object[] fila = { codCliente, nombre, apellido, CUIL, correo, limiteCredito, creditoDisponible,tipoCliente,afip,estado,calle,altura,pais,prov,local,codPostal };
		this.ventanaGestionarClientes.getModelCliente().addRow(fila);
		this.clienteEnTabla.add(c);
		
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
	
	public void pasarAHistorialDeClientes() {
		this.ventanaGestionarClientes.cerrar();
		this.controladorHistorialDeCambiosDeCliente.inicializar();
		this.controladorHistorialDeCambiosDeCliente.mostrarVentana();
		
	}
	
}
