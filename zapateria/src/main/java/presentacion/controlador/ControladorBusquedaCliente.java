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
import presentacion.vista.vistaBusquedaProductos;


public class ControladorBusquedaCliente {	
	private VentanaBusquedaCliente ventanaBusquedaCliente;
	private Cliente cliente;
	private List<ClienteDTO> clienteEnTabla;
	
	ControladorBusquedaProductos controladorBusquedaProductos;
	
	private ClienteDTO clienteSeleccionado;
	
	public ControladorBusquedaCliente(Cliente cliente) {
		
		this.ventanaBusquedaCliente = new VentanaBusquedaCliente();
		this.cliente = cliente;
	}
	
	public void setControladorBusquedaProducto(ControladorBusquedaProductos c) {
		this.controladorBusquedaProductos=c;
	}
	
	
	
	
	public void inicializar() {
		System.out.println("se ejecuta el contorlador de cliente");
		
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
		
//		this.mostrarVentana();
	}
	
	public void atras(ActionEvent a) {
		this.ventanaBusquedaCliente.cerrar();
	}

	public void pasarAVenta(ActionEvent p) {
		if(clienteSeleccionado()) {
			this.ventanaBusquedaCliente.cerrar();
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
			//Obtengo el objeto cilente con todos sus valores
			if (codCliente.equals(""+c.getIdCliente()+"")) {
//				int IdCliente = c.getIdCliente();
//				String Nombre = c.getNombre();
//				String Apellido = c.getApellido();
//				String DNI = c.getDNI();
//				String CorreoElectronico = c.getCorreo();
//				int LimiteCredito = c.getLimiteCredito();
//				int CreditoDisponible = c.getCreditoDisponible();
//				String TipoCliente = c.getTipoCliente();
//				String ImpuestoAFIP = c.getImpuestoAFIP();
//				String Estado = c.getEstado();
//				String Calle = c.getCalle();
//				String Altura = c.getAltura();
//				String Pais = c.getPais();
//				String Provincia = c.getProvincia(); 
//				String Localidad = c.getLocalidad();
//				String CodPostal = c.getCodPostal();
//				System.out.println("Cliente Seleccionado: "+ IdCliente+", "+Nombre+", "+Apellido+", "+DNI+", "+Calle+", "+TipoCliente+", etc.");
				this.clienteSeleccionado = c;
				return true;
			}
		}
		return false;
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

	public ClienteDTO getClienteSeleccionado() {
		return this.clienteSeleccionado;
	}
	
	
	
	
//	public static void main(String[] args) {
//		new ControladorBusquedaCliente();
//	}
	
}
