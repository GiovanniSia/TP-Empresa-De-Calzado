package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JOptionPane;
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
		
		//Botones
		this.ventanaBusquedaCliente.getBtnAtras().addActionListener(a -> atras(a));
		this.ventanaBusquedaCliente.getBtnPasarAVenta().addActionListener(p -> pasarAVenta(p));
		
		//TextField
		this.ventanaBusquedaCliente.getTxtFieldCodCliente().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				clienteEnTabla = cliente.filtrarPorCodCliente(ventanaBusquedaCliente.getTxtFieldCodCliente().getText());
				llenarTabla(clienteEnTabla);
			}
		});

		this.ventanaBusquedaCliente.getTxtFieldNombre().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				clienteEnTabla = cliente.filtrarPorNombre(ventanaBusquedaCliente.getTxtFieldNombre().getText());
				llenarTabla(clienteEnTabla);
			}
		});

		this.ventanaBusquedaCliente.getTxtFieldApellido().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				clienteEnTabla = cliente.filtrarPorApellido(ventanaBusquedaCliente.getTxtFieldApellido().getText());
				llenarTabla(clienteEnTabla);
			}
		});

		this.ventanaBusquedaCliente.getTxtFieldDNI().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				clienteEnTabla = cliente.filtrarPorDNI(ventanaBusquedaCliente.getTxtFieldDNI().getText());
				llenarTabla(clienteEnTabla);
			}
		});
		
		this.mostrarVentana();
	}
	
	public void atras(ActionEvent a) {
		this.ventanaBusquedaCliente.cerrar();
	}

	public void pasarAVenta(ActionEvent p) {
		clienteSeleccionado();
	}

	public void clienteSeleccionado() {
		int filaSeleccionada = this.ventanaBusquedaCliente.getTablaClientes().getSelectedRow();
		if (filaSeleccionada == -1) {
			JOptionPane.showMessageDialog(null, "Seleccione un cliente");
			return;
		}

		String codCliente = this.ventanaBusquedaCliente.getTablaClientes().getValueAt(filaSeleccionada, 0).toString();
		String nombre = this.ventanaBusquedaCliente.getTablaClientes().getValueAt(filaSeleccionada, 1).toString();
		String apellido = this.ventanaBusquedaCliente.getTablaClientes().getValueAt(filaSeleccionada, 2).toString();
		String DNI = this.ventanaBusquedaCliente.getTablaClientes().getValueAt(filaSeleccionada, 3).toString();
		String correoElectronico = this.ventanaBusquedaCliente.getTablaClientes().getValueAt(filaSeleccionada, 4)
				.toString();
		String estado = this.ventanaBusquedaCliente.getTablaClientes().getValueAt(filaSeleccionada, 5).toString();
		System.out.println("Cliente Seleccionado: " + codCliente + ", " + nombre + ", " + apellido + ", " + DNI + ", "
				+ correoElectronico + ", " + estado);
	}

	public void mostrarVentana() {
		this.ventanaBusquedaCliente.show();
		this.mostrarClientesEnTabla();
	}

	public void mostrarClientesEnTabla() {
		this.clienteEnTabla = cliente.readAll();
		this.llenarTabla(clienteEnTabla);
	}

	public void llenarTabla(List<ClienteDTO> clienteEnTabla) {
		this.ventanaBusquedaCliente.getModelCliente().setRowCount(0);
		this.ventanaBusquedaCliente.getModelCliente().setColumnCount(0);
		this.ventanaBusquedaCliente.getModelCliente()
				.setColumnIdentifiers(this.ventanaBusquedaCliente.getNombreColumnas());
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
