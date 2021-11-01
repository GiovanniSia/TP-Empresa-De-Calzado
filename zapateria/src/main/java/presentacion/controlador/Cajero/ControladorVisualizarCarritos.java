package presentacion.controlador.Cajero;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dto.CarritoDTO;
import dto.ClienteDTO;
import dto.DetalleCarritoDTO;
import dto.MaestroProductoDTO;
import dto.StockDTO;
import inicioSesion.sucursalProperties;
import modelo.Carrito;
import modelo.Cliente;
import modelo.DetalleCarrito;
import modelo.MaestroProducto;
import modelo.Stock;
import presentacion.controlador.Controlador;
import presentacion.controlador.ValidadorTeclado;
import presentacion.vista.Cajero.VentanaVisualizarCarritos;

public class ControladorVisualizarCarritos {

	private int idSucursal = 0;
	public void obtenerDatosPropertiesSucursal() {
		try {
			sucursalProperties sucursalProp = sucursalProperties.getInstance();
			idSucursal = Integer.parseInt(sucursalProp.getValue("IdSucursal"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	Carrito carrito;
	DetalleCarrito detalleCarrito;
	Cliente cliente;
	MaestroProducto maestroProducto;

	Stock stock;

	List<CarritoDTO> listaCarritos;
	List<DetalleCarritoDTO> listaDetalleCarrito;
	List<ClienteDTO> listaClientes;

	List<MaestroProductoDTO> todosLosProductos;

	List<CarritoDTO> carritosEnTabla;
	List<DetalleCarritoDTO> detalleCarritoEnTabla;

	VentanaVisualizarCarritos ventanaVisualizarCarritos;
	ControladorRealizarVenta controladorRealizarVenta;

	Controlador controlador;

	public ControladorVisualizarCarritos(Controlador controlador, Carrito carrito, DetalleCarrito detalleCarrito,
			Cliente cliente, MaestroProducto maestroProducto, Stock stock) {
		obtenerDatosPropertiesSucursal();
		this.carrito = carrito;
		this.detalleCarrito = detalleCarrito;
		this.cliente = cliente;
		this.maestroProducto = maestroProducto;
		this.stock = stock;

		this.listaCarritos = new ArrayList<CarritoDTO>();
		this.listaDetalleCarrito = new ArrayList<DetalleCarritoDTO>();
		this.listaClientes = new ArrayList<ClienteDTO>();
		this.carritosEnTabla = new ArrayList<CarritoDTO>();
		this.detalleCarritoEnTabla = new ArrayList<DetalleCarritoDTO>();

		this.todosLosProductos = new ArrayList<MaestroProductoDTO>();
		this.ventanaVisualizarCarritos = new VentanaVisualizarCarritos();

		this.controlador = controlador;
	}

	public void setControladorRealizarVenta(ControladorRealizarVenta controladorRealizarVenta) {
		this.controladorRealizarVenta = controladorRealizarVenta;
	}

	public void inicializar() {
		this.controladorRealizarVenta.inicializar();

		this.listaCarritos = this.carrito.readAll();
		this.listaDetalleCarrito = this.detalleCarrito.readAll();
		this.listaClientes = this.cliente.readAll();

		this.todosLosProductos = this.maestroProducto.readAll();

		this.ventanaVisualizarCarritos.getBtnElegirCarrito().addActionListener(a -> pasarVentana(a));
		this.ventanaVisualizarCarritos.getBtnRegresar().addActionListener(a -> salir(a));
		this.ventanaVisualizarCarritos.getBtnBorrarCarrito().addActionListener(a -> borrarCarrito(a));

		this.ventanaVisualizarCarritos.getTableCarritos().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ListSelectionModel rowSM = this.ventanaVisualizarCarritos.getTableCarritos().getSelectionModel();

		rowSM.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				mostrarDetalle();
			}

		});

		this.ventanaVisualizarCarritos.getTextCUIL().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				realizarBusqueda();
			}
		});
		this.ventanaVisualizarCarritos.getTextNombre().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				realizarBusqueda();
			}
		});
		this.ventanaVisualizarCarritos.getTextApellido().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				realizarBusqueda();
			}
		});

		validarTeclado();
		llenarTablaCompleta();

	}

	public void actualizarVentana() {
		this.listaCarritos = this.carrito.readAll();
		this.listaDetalleCarrito = this.detalleCarrito.readAll();
		realizarBusqueda();
		mostrarDetalle();
		this.ventanaVisualizarCarritos.getTableCarritos().updateUI();
		this.ventanaVisualizarCarritos.getTableDetalle().updateUI();
	}

	public void mostrarVentana() {
		this.ventanaVisualizarCarritos.show();
	}

	public void cerrarVentana() {
		this.ventanaVisualizarCarritos.cerrar();
	}

	public void realizarBusqueda() {
		String nombre = this.ventanaVisualizarCarritos.getTextNombre().getText();
		String apellido = this.ventanaVisualizarCarritos.getTextApellido().getText();
		String CUIL = this.ventanaVisualizarCarritos.getTextCUIL().getText();

		ArrayList<ClienteDTO> clientesConCarrito = (ArrayList<ClienteDTO>) this.cliente.getClienteAproximado("Nombre",
				nombre, "Apellido", apellido, "CUIL", CUIL, null, null, null, null);
		escribirTablaFiltrada(clientesConCarrito);
	}

	public void escribirTablaFiltrada(ArrayList<ClienteDTO> clientesConCarrito) {
		this.ventanaVisualizarCarritos.getModelTablaCarritos().setRowCount(0);// borrar datos de la tabla
		this.ventanaVisualizarCarritos.getModelTablaCarritos().setColumnCount(0);
		this.ventanaVisualizarCarritos.getModelTablaCarritos()
				.setColumnIdentifiers(this.ventanaVisualizarCarritos.getNombreColumnasCarritos());
		this.carritosEnTabla.removeAll(this.carritosEnTabla);

		for (CarritoDTO carrito : this.listaCarritos) {
			for (ClienteDTO cliente : clientesConCarrito) {
				if (carrito.getIdCliente() == cliente.getIdCliente() && carrito.getIdSucursal() == this.idSucursal) {
					agregarATabla(cliente, carrito);
				}
			}
		}
	}

	public void llenarTablaCompleta() {
		this.ventanaVisualizarCarritos.getModelTablaCarritos().setRowCount(0);// borrar datos de la tabla
		this.ventanaVisualizarCarritos.getModelTablaCarritos().setColumnCount(0);
		this.ventanaVisualizarCarritos.getModelTablaCarritos()
				.setColumnIdentifiers(this.ventanaVisualizarCarritos.getNombreColumnasCarritos());

		for (CarritoDTO carrito : this.listaCarritos) {
			if (carrito.getIdSucursal() == this.idSucursal && !yaFueAgregado(carrito)) {

				ClienteDTO cliente = this.cliente.selectCliente(carrito.getIdCliente());
				agregarATabla(cliente, carrito);
			}
		}
	}

	public boolean yaFueAgregado(CarritoDTO carrito) {
		for (CarritoDTO c : this.carritosEnTabla) {
			if (carrito.getIdCarrito() == c.getIdCarrito()) {
				return true;
			}
		}
		return false;
	}

	public void agregarATabla(ClienteDTO cliente, CarritoDTO carrito) {
		String CUIL = cliente.getCUIL();
		String nombreCliente = cliente.getNombre() + " " + cliente.getApellido();
		String hora = carrito.getHora();
		String TipoCliente = cliente.getTipoCliente();

		String estado = cliente.getEstado();

		double precioTota = carrito.getTotal();
		BigDecimal precioTotal = new BigDecimal(precioTota);

		Object[] fila = { CUIL, nombreCliente, hora, TipoCliente, estado, precioTotal };
		this.ventanaVisualizarCarritos.getModelTablaCarritos().addRow(fila);

		this.carritosEnTabla.add(carrito);
	}

	public void mostrarDetalle() {
		this.ventanaVisualizarCarritos.getModelTablaDetalle().setRowCount(0);// borrar datos de la tabla
		this.ventanaVisualizarCarritos.getModelTablaDetalle().setColumnCount(0);
		this.ventanaVisualizarCarritos.getModelTablaDetalle()
				.setColumnIdentifiers(this.ventanaVisualizarCarritos.getNombreColumnasDetalle());
		this.detalleCarritoEnTabla.removeAll(this.detalleCarritoEnTabla);

		int filaSeleccionada = this.ventanaVisualizarCarritos.getTableCarritos().getSelectedRow();
		System.out.println("fila seleccionada: " + filaSeleccionada);
		if (filaSeleccionada == -1) {
//			JOptionPane.showMessageDialog(null, "wtf esto no deberia aparecer xd");
			return;
		}
		CarritoDTO carritoSeleccionado = this.carritosEnTabla.get(filaSeleccionada);

		for (DetalleCarritoDTO detalleCar : this.listaDetalleCarrito) {
			if (detalleCar.getIdCarrito() == carritoSeleccionado.getIdCarrito()
					&& carritoSeleccionado.getIdSucursal() == this.idSucursal) {
				MaestroProductoDTO prod = getProducto(detalleCar.getIdProducto());
				String nombreProd = prod.getDescripcion();
				int cant = detalleCar.getCantidad();
				double p = detalleCar.getPrecio();
				BigDecimal precio = new BigDecimal(p);

				Object[] fila = { nombreProd, cant, precio };
				this.ventanaVisualizarCarritos.getModelTablaDetalle().addRow(fila);
				this.detalleCarritoEnTabla.add(detalleCar);
			}
		}

	}

	public DetalleCarritoDTO getDetalle(CarritoDTO carrito) {
		for (DetalleCarritoDTO detalle : this.listaDetalleCarrito) {
			if (detalle.getIdCarrito() == carrito.getIdCarrito()) {
				return detalle;
			}
		}
		return null;
	}

	public MaestroProductoDTO getProducto(int idProducto) {
		for (MaestroProductoDTO m : this.todosLosProductos) {
			if (m.getIdMaestroProducto() == idProducto) {
				return m;
			}
		}
		return null;
	}

	public void pasarVentana(ActionEvent a) {

		int filaSeleccionada = this.ventanaVisualizarCarritos.getTableCarritos().getSelectedRow();

		if (filaSeleccionada == -1 || this.detalleCarritoEnTabla.size() == 0) {
			JOptionPane.showMessageDialog(null, "No ha seleccionado ningún carrito!");
			return;

		}
		int resp = JOptionPane.showConfirmDialog(null, "Pasar a cobrar el carrito?", "Cobrar Carrito",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (resp == 1) {
			return;
		}

		if (this.carritosEnTabla.size() == 0) {
			JOptionPane.showMessageDialog(null, "No hay ningún carrito para cobrar!");
			return;

		}

		if (this.controladorRealizarVenta.ventanaYaFueInicializada()) {
			JOptionPane.showMessageDialog(null, "Ya se esta cobrando una venta");
			return;
		}

		// si selecciona que si devuelve un 0, no un 1, y la x un -1
		if (resp == 0) {
			CarritoDTO carrito = this.carritosEnTabla.get(filaSeleccionada);
			ClienteDTO client = this.cliente.selectCliente(carrito.getIdCliente());

//			this.ventanaVisualizarCarritos.cerrar();
			this.controladorRealizarVenta.establecerCarritoACobrar(carrito, this.detalleCarritoEnTabla, client);
//			this.controladorRealizarVenta.inicializar();
			this.controladorRealizarVenta.llenarDatosDeCarrito();
			this.controladorRealizarVenta.mostrarVentana();

		}

	}

	public void validarTeclado() {
		this.ventanaVisualizarCarritos.getTextNombre().addKeyListener((new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarLetrasYEspacios(e);
			}
		}));
		this.ventanaVisualizarCarritos.getTextApellido().addKeyListener((new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarLetrasYEspacios(e);
			}
		}));
		this.ventanaVisualizarCarritos.getTextCUIL().addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarSoloNumeros(e);
			}
		});
	}

	public void salir(ActionEvent a) {
		this.ventanaVisualizarCarritos.cerrar();
		this.controlador.inicializar();
		this.controlador.mostrarVentanaMenuDeSistemas();
		this.controladorRealizarVenta.cerrarVentana();
	}

	public void borrarCarrito(ActionEvent a) {
		// si selecciona que si devuelve un 0, no un 1, y la x un -1
		int resp = JOptionPane.showConfirmDialog(null, "Esta seguro que desea borrar el carrito?", "Borrar Carrito",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (resp == 1) {
			return;
		}

		int filaSeleccionada = this.ventanaVisualizarCarritos.getTableCarritos().getSelectedRow();
		if (filaSeleccionada == -1) {
			JOptionPane.showMessageDialog(null, "No ha seleccionado ningun producto para borrar");
			return;
		}
		CarritoDTO carritoSel = this.carritosEnTabla.get(filaSeleccionada);
		ArrayList<DetalleCarritoDTO> detalleCarritosABorrar = new ArrayList<DetalleCarritoDTO>();
		for (DetalleCarritoDTO detalle : this.listaDetalleCarrito) {
			if (detalle.getIdCarrito() == carritoSel.getIdCarrito()) {
				detalleCarritosABorrar.add(detalle);
				boolean b = this.detalleCarrito.delete(detalle);// Borramos detalle de la bd
				if (b == false) {
					JOptionPane.showMessageDialog(null,
							"Ha ocurrido un error borrando uno de los detalles del carrito en la BD");
				}
			}
		}

		reintegrarStock(carritoSel, detalleCarritosABorrar);

		this.listaDetalleCarrito.removeAll(detalleCarritosABorrar);
		this.listaCarritos.remove(carritoSel);
		boolean c = this.carrito.delete(carritoSel);
		if (c == false) {
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error borrando el carrito en la BD");
		} else {
			JOptionPane.showMessageDialog(null, "Carrito borrado satisfactoriamente");
		}

		actualizarTablas();
	}

	public void actualizarTablas() {
		this.listaCarritos = this.carrito.readAll();
		this.listaDetalleCarrito = this.detalleCarrito.readAll();
		this.listaClientes = this.cliente.readAll();

		this.todosLosProductos = this.maestroProducto.readAll();

		this.ventanaVisualizarCarritos.getModelTablaDetalle().setRowCount(0);// borrar datos de la tabla
		this.ventanaVisualizarCarritos.getModelTablaDetalle().setColumnCount(0);
		this.ventanaVisualizarCarritos.getModelTablaDetalle()
				.setColumnIdentifiers(this.ventanaVisualizarCarritos.getNombreColumnasDetalle());
		this.detalleCarritoEnTabla.removeAll(this.detalleCarritoEnTabla);

		realizarBusqueda();

	}

	public void reintegrarStock(CarritoDTO carrito, ArrayList<DetalleCarritoDTO> detalleCarrito) {
		List<StockDTO> todosLosStock = this.stock.readAll();
		for (DetalleCarritoDTO d : detalleCarrito) {
			for (StockDTO s : todosLosStock) {
				if (d.getIdCarrito() == carrito.getIdCarrito() && s.getIdStock() == d.getIdStock()
						&& s.getIdProducto() == d.getIdProducto()) {
					this.stock.actualizarStock(d.getIdStock(), d.getCantidad());
				}
			}

		}
	}

}
