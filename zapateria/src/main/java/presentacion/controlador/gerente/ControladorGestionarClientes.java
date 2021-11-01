package presentacion.controlador.gerente;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JOptionPane;

import dto.ClienteDTO;
import modelo.Cliente;
import presentacion.controlador.Controlador;
import presentacion.vista.gerente.VentanaGestionarClientes;

public class ControladorGestionarClientes {

	VentanaGestionarClientes ventanaGestionarClientes;
	
	Cliente cliente;
	List<ClienteDTO> todosLosClientes;
	
	ControladorAltaCliente controladorAltaCliente;
	Controlador controlador;
	public ControladorGestionarClientes(Controlador controlador,Cliente cliente) {		
		this.controlador = controlador;
		this.cliente = cliente;
		
		this.ventanaGestionarClientes = new VentanaGestionarClientes();
		
		this.ventanaGestionarClientes.getBtnAgregarCliente().addActionListener(a -> pasarAAgregarCliente(a));
		this.ventanaGestionarClientes.getBtnAtras().addActionListener(a -> salir(a));
	}

	public void setControladorAltaCliente(ControladorAltaCliente controladorAltaCliente) {
		this.controladorAltaCliente = controladorAltaCliente;
	}
	
	public void inicializar() {
		this.todosLosClientes = this.cliente.readAll();
		llenarTabla();	
		
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
	
	public void llenarTabla() {
		this.ventanaGestionarClientes.getModelCliente().setRowCount(0);//borrar datos de la tabla
		this.ventanaGestionarClientes.getModelCliente().setColumnCount(0);
		this.ventanaGestionarClientes.getModelCliente().setColumnIdentifiers(this.ventanaGestionarClientes.getNombreColumnas());
		
		for (ClienteDTO c : this.todosLosClientes) {
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
			}
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

	
}
