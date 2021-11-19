package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import dto.ClienteDTO;
import modelo.Cliente;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.vista.VentanaBusquedaCliente;
import presentacion.vista.VentanaInformacionBusquedaCliente;

public class ControladorBusquedaCliente {
	private VentanaBusquedaCliente ventanaBusquedaCliente;
	private Cliente cliente;
	private List<ClienteDTO> clienteEnTabla;

	ControladorBusquedaProductos controladorBusquedaProductos;

	Controlador controlador;

	private ClienteDTO clienteSeleccionado;

	private VentanaInformacionBusquedaCliente ventanaInformacionBusquedaCliente;

	public ControladorBusquedaCliente(Controlador controlador, Cliente cliente) {
		this.controlador = controlador;
//		this.ventanaMenuSistemaDeVentas = new VentanaMenuSistemaDeVentas(); 
		this.ventanaBusquedaCliente = new VentanaBusquedaCliente();
		this.cliente = cliente;
		ventanaInformacionBusquedaCliente = new VentanaInformacionBusquedaCliente();
	}

	public void setControladorBusquedaProducto(ControladorBusquedaProductos c) {
		this.controladorBusquedaProductos = c;
	}

	public void inicializar() {

		clienteEnTabla = new ArrayList<ClienteDTO>();

		this.cliente = new Cliente(new DAOSQLFactory());

		this.ventanaBusquedaCliente = new VentanaBusquedaCliente();

		this.ventanaBusquedaCliente.getBtnInformacionElegirCliente()
				.addActionListener(p -> mostrarVentanaInformacion(p));
		// Botones
		this.ventanaBusquedaCliente.getBtnAtras().addActionListener(a -> atras(a));
		this.ventanaBusquedaCliente.getBtnPasarAVenta().addActionListener(p -> pasarAVenta(p));

		// TextField
		this.ventanaBusquedaCliente.getTxtFieldCodCliente().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				realizarBusqueda();
			}
		});

		this.ventanaBusquedaCliente.getTxtFieldNombre().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				realizarBusqueda();
			}
		});

		this.ventanaBusquedaCliente.getTxtFieldApellido().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				realizarBusqueda();
			}
		});

		this.ventanaBusquedaCliente.getTxtFieldCUIL().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				realizarBusqueda();
			}
		});

	}

	public void realizarBusqueda() {
		this.ventanaBusquedaCliente.getModelCliente().setRowCount(0);// borrar datos de la tabla
		this.ventanaBusquedaCliente.getModelCliente().setColumnCount(0);
		this.ventanaBusquedaCliente.getModelCliente()
				.setColumnIdentifiers(this.ventanaBusquedaCliente.getNombreColumnas());

		String txtcodCliente = this.ventanaBusquedaCliente.getTxtFieldCodCliente().getText();
		String txtNombre = this.ventanaBusquedaCliente.getTxtFieldNombre().getText();
		String txtApellido = this.ventanaBusquedaCliente.getTxtFieldApellido().getText();
		String txtCUIL = this.ventanaBusquedaCliente.getTxtFieldCUIL().getText();

		this.clienteEnTabla.removeAll(clienteEnTabla);

		List<ClienteDTO> clienteAproximados = this.cliente.getClienteAproximado("IdCliente", txtcodCliente, "Nombre",
				txtNombre, "Apellido", txtApellido, "CUIL", txtCUIL, null, null);
		llenarTabla(clienteAproximados);
	}

	public void mostrarVentanaInformacion(ActionEvent b) {
		ventanaInformacionBusquedaCliente.show();
	}

	public void atras(ActionEvent a) {
		this.ventanaBusquedaCliente.cerrar();
		this.ventanaInformacionBusquedaCliente.cerrar();
		this.controlador.mostrarVentanaMenuDeSistemas();
	}

	public void pasarAVenta(ActionEvent p) {

		if (clienteSeleccionado() && esClienteInactivo()) {
			this.ventanaBusquedaCliente.cerrar();
			this.ventanaInformacionBusquedaCliente.cerrar();
			this.controladorBusquedaProductos.establecerClienteElegido(this.clienteSeleccionado);
			controladorBusquedaProductos.inicializar();
		}
	}

	public boolean clienteSeleccionado() {
		int filaSeleccionada = this.ventanaBusquedaCliente.getTablaClientes().getSelectedRow();
		if (filaSeleccionada == -1) {
			JOptionPane.showMessageDialog(null, "Seleccione un cliente");
			return false;
		}

		List<ClienteDTO> clientes = cliente.readAll();

		String codCliente = this.ventanaBusquedaCliente.getTablaClientes().getValueAt(filaSeleccionada, 0).toString();
		for (ClienteDTO c : clientes) {
			// Obtengo el objeto cilente con todos sus valores
			if (codCliente.equals("" + c.getIdCliente() + "")) {
				this.clienteSeleccionado = c;
				return true;
			}
		}
		return false;
	}

	public boolean esClienteInactivo() {
		int filaSeleccionada = this.ventanaBusquedaCliente.getTablaClientes().getSelectedRow();

		System.out.println(this.clienteEnTabla.get(filaSeleccionada).getNombre());

		String estado = this.clienteEnTabla.get(filaSeleccionada).getEstado();

		if (estado.equals("Inactivo")) {
			JOptionPane.showMessageDialog(null, "El cliente seleccionado se encuentra inactivo");
			return false;
		}
		return true;
	}

	public void mostrarVentana() {
		this.ventanaBusquedaCliente.show();
		this.mostrarClientesEnTabla();
	}

	public void mostrarClientesEnTabla() {
		this.realizarBusqueda();
	}

	public void llenarTabla(List<ClienteDTO> listaFiltrada) {
		this.ventanaBusquedaCliente.getModelCliente().setRowCount(0);
		this.ventanaBusquedaCliente.getModelCliente().setColumnCount(0);
		this.ventanaBusquedaCliente.getModelCliente()
				.setColumnIdentifiers(this.ventanaBusquedaCliente.getNombreColumnas());
		for (ClienteDTO c : listaFiltrada) {
			int codCliente = c.getIdCliente();
			String nombre = c.getNombre();
			String apellido = c.getApellido();
			String CUIL = c.getCUIL();
			String correo = c.getCorreo();
			double limiteCredit = c.getLimiteCredito();
			BigDecimal limiteCredito = new BigDecimal(limiteCredit).setScale(2, RoundingMode.HALF_UP);
			;

			double creditoDisponibl = c.getCreditoDisponible();
			BigDecimal creditoDisponible = new BigDecimal(creditoDisponibl).setScale(2, RoundingMode.HALF_UP);
			;

			String estado = c.getEstado();
			if (!estado.equals("Inactivo")) {
				Object[] fila = { codCliente, nombre, apellido, CUIL, correo, limiteCredito, creditoDisponible,
						estado };
				this.ventanaBusquedaCliente.getModelCliente().addRow(fila);
				this.clienteEnTabla.add(c);
			}

		}
	}

	public ClienteDTO getClienteSeleccionado() {
		return this.clienteSeleccionado;
	}

}
