package presentacion.controlador.Cajero;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import dto.CarritoDTO;
import dto.ClienteDTO;
import dto.DetalleCarritoDTO;
import dto.DetalleFacturaDTO;
import dto.EmpleadoDTO;
import dto.FacturaDTO;
import dto.IngresosDTO;
import dto.MaestroProductoDTO;
import dto.MedioPagoDTO;
import dto.PrimeraDeudaClienteDTO;
import dto.SucursalDTO;
import inicioSesion.empleadoProperties;
import inicioSesion.sucursalProperties;
import modelo.Carrito;
import modelo.Cliente;
import modelo.DetalleCarrito;
import modelo.DetalleFactura;
import modelo.Empleado;
import modelo.Factura;
import modelo.Ingresos;
import modelo.MaestroProducto;
import modelo.MedioPago;
import modelo.PedidosPendientes;
import modelo.PrimeraDeudaCliente;
import modelo.Sucursal;
import modelo.generarOrdenesFabricacion;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.controlador.ValidadorTeclado;
import presentacion.reportes.ReporteFactura;
import presentacion.vista.Cajero.VentanaRealizarVenta;

public class ControladorRealizarVenta {

	// hardcodeado
	int idEmpleado = 0;
	int idSucursal = 0;

	public void obtenerDatosPropertiesSucursalEmpleado() {
		try {
			sucursalProperties sucursalProp = sucursalProperties.getInstance();
			idSucursal = Integer.parseInt(sucursalProp.getValue("IdSucursal"));

			empleadoProperties empleadoProp = empleadoProperties.getInstance();
			idEmpleado = Integer.parseInt(empleadoProp.getValue("IdEmpleado"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public double totalPagado = 0;
	public double totalAPagar;
	public double totalAPagarAux;
	public double descuento = 0;
	public double totalAPagarSinDescuento;

	boolean seAplicoDesc = false;

	double cantidadUsadaCC;

	VentanaRealizarVenta ventanaRealizarVenta;

	CarritoDTO carritoACobrar;
	List<DetalleCarritoDTO> detalleCarritoACobrar;
	ClienteDTO clienteCarrito;

	MedioPago medioPago;
	Cliente cliente;
	Empleado cajero;

	Carrito carrito;
	DetalleCarrito detalleCarrito;
	MaestroProducto maestroProducto;
	Factura factura;
	DetalleFactura detalleFactura;
	Ingresos ingresos;

	Sucursal sucursal;

	List<MedioPagoDTO> listamediosDePago;

	List<IngresosDTO> listaDeIngresosARegistrar;// representa tambien el pago que este en la tabla (por indice)

	List<MaestroProductoDTO> todosLosProductos;

	ControladorVisualizarCarritos controladorVisualizarCarritos;

	FacturaDTO facturaGenerada;

	public ControladorRealizarVenta(MedioPago medioPago, Cliente cliente, Empleado cajero, Carrito carrito,
			DetalleCarrito detalleCarrito, MaestroProducto maestroProducto, Factura factura,
			DetalleFactura detalleFactura, Ingresos ingresos) {
		obtenerDatosPropertiesSucursalEmpleado();
		this.medioPago = medioPago;
		this.cliente = cliente;
		this.cajero = cajero;
		this.carrito = carrito;
		this.detalleCarrito = detalleCarrito;
		this.maestroProducto = maestroProducto;
		this.factura = factura;
		this.detalleFactura = detalleFactura;
		this.ingresos = ingresos;

		this.sucursal = new Sucursal(new DAOSQLFactory());

		this.listamediosDePago = new ArrayList<MedioPagoDTO>();
		this.listaDeIngresosARegistrar = new ArrayList<IngresosDTO>();
		this.todosLosProductos = new ArrayList<MaestroProductoDTO>();

		this.ventanaRealizarVenta = new VentanaRealizarVenta();
	}

	public void setControladorVisualizarCarritos(ControladorVisualizarCarritos controladorVisualizarCarritos) {
		this.controladorVisualizarCarritos = controladorVisualizarCarritos;
	}

	public void establecerCarritoACobrar(CarritoDTO carrito, List<DetalleCarritoDTO> detalles, ClienteDTO cliente) {
		this.carritoACobrar = carrito;
		this.detalleCarritoACobrar = detalles;
		this.clienteCarrito = cliente;
	}

	public void inicializar() {
//		vaciarDatosPrevios();

		this.todosLosProductos = this.maestroProducto.readAll();
		this.listamediosDePago = this.medioPago.readAll();

		this.ventanaRealizarVenta.getBtnAgregarMedioPago().addActionListener(a -> agregarMedioDePago(a));
		this.ventanaRealizarVenta.getBtnQuitarMedioPago().addActionListener(a -> quitarMedioPago(a));
		this.ventanaRealizarVenta.getBtnFinalizarVenta().addActionListener(a -> registrarPago(a));
		this.ventanaRealizarVenta.getBtnCancelarVenta().addActionListener(a -> cancelarPago(a));
		this.ventanaRealizarVenta.getTextDescuento().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				actualizarTablaMedioPago();
			}
		});

		validarTeclado();
		llenarCbMedioPago();

	}

	public void llenarDatosDeCarrito() {

		this.totalAPagarSinDescuento = this.carritoACobrar.getTotal();
		this.totalAPagar = carritoACobrar.getTotal();
		BigDecimal tpgr = new BigDecimal(this.totalAPagar).setScale(2, RoundingMode.HALF_UP);
		this.ventanaRealizarVenta.getLblTotalAPagarValor().setText("" + tpgr);
	}

	public void mostrarVentana() {
		this.ventanaRealizarVenta.show();
	}

	public void cerrarVentana() {
		this.ventanaRealizarVenta.cerrar();
	}

	public void llenarCbMedioPago() {
		this.ventanaRealizarVenta.getComboBoxMetodoPago().removeAllItems();
		this.ventanaRealizarVenta.getComboBoxMetodoPago().addItem("Sin seleccionar");
		for (MedioPagoDTO m : this.listamediosDePago) {
			if (!m.getIdMoneda().equals("PV")) {
				this.ventanaRealizarVenta.getComboBoxMetodoPago().addItem(m.getDescripcion());
			}

		}
	}

	public void agregarMedioDePago(ActionEvent a) {
		if (!todosLosCamposSonValidos()) {
			JOptionPane.showMessageDialog(null, "El campo de monto no es correcto");
			return;
		}

		String nroOperacion = this.ventanaRealizarVenta.getTextNumOperacion().getText();

		if (ventanaRealizarVenta.getTextCantidad().getText().equals("")) {
			JOptionPane.showMessageDialog(null, "No ha agregado ningun valor");
			return;
		}

		String metodoPagoCb = (String) this.ventanaRealizarVenta.getComboBoxMetodoPago().getSelectedItem();

		if (metodoPagoCb.equals("Sin seleccionar")) {
			JOptionPane.showMessageDialog(null, "No ha agregado ningun medio de pago");
			return;
		}

		MedioPagoDTO medioPago = obtenerMedioPago(metodoPagoCb);

		double cantidad = (double) Double.parseDouble(ventanaRealizarVenta.getTextCantidad().getText());

		if (cantidad == 0) {
			JOptionPane.showMessageDialog(null, "No se puede agregar esta cantidad");
			return;
		}

		if (!esUnPagoConTarjeta(medioPago) && !nroOperacion.equals("")) {
			JOptionPane.showMessageDialog(null, "No se puede agregar un nro de operacion");
			return;
		}

		if ((cantidad * medioPago.getTasaConversion()) > this.totalAPagar) {
			JOptionPane.showMessageDialog(null, "Esta cantidad supera el total a pagar!");
			return;
		}

		if (nroOperacion.equals("") && esUnPagoConTarjeta(medioPago)) {
			JOptionPane.showMessageDialog(null, "Debe agregar el n�mero de operaci�n para agregar el medio de pago!");
			return;
		}

		// cuando se registra un pago se guarda un ingreso para registrar cuando se
		// tenga la factura

		if (medioPago.getDescripcion().equals("Cuenta Corriente")) {
			if (this.clienteCarrito.getEstado().equals("Moroso")) {
				JOptionPane.showMessageDialog(null, "El cliente es Moroso, no puede usar la cuenta corriente!");
				return;
			}
			if (!poseeSaldoSuficiente(this.clienteCarrito, cantidad)) {
				JOptionPane.showMessageDialog(null, "El cliente no posee saldo suficiente!");
				return;
			}
		}

		// verificamos si el pago ya esta en la tabla
		if (!esUnPagoConTarjeta(medioPago) && pagoYaFueRegistrado(medioPago)) {
			for (IngresosDTO i : this.listaDeIngresosARegistrar) {
				if (i.getMedioPago().equals(medioPago.getIdMoneda())) {
					double cantNuevo = i.getCantidad() + cantidad;
					double totalNuevo = i.getTotal() + (cantidad * medioPago.getTasaConversion());
					i.setCantidad(cantNuevo);
					i.setTotal(totalNuevo);

					actualizarTablaMedioPago();
					this.ventanaRealizarVenta.getTextCantidad().setText("");
					this.ventanaRealizarVenta.getTextNumOperacion().setText("");

					return;
				}
			}
		}

		int idCliente = this.carritoACobrar.getIdCliente();

		String tipoFactura = determinarCategoriaFactura(this.clienteCarrito);
		double valorConversion = medioPago.getTasaConversion();
		double totalArg = cantidad * valorConversion;
		String mp = medioPago.getIdMoneda();

		// EL NRO DE FACTURA SE DEBERA SETEAR AL MOMENTO DE REALIZAR EL COBRO, RECORRER
		// TODOS LOS INGRESOS Y SETEARLE A CADA UNO EL NRO DE FACTURA GENERADO
//		System.out.println("medio de pago agregado: "+mp);
		IngresosDTO ingreso = new IngresosDTO(0, idSucursal, "", "", "VT", idCliente, tipoFactura, "0", mp, cantidad,
				valorConversion, nroOperacion, totalArg);
		this.listaDeIngresosARegistrar.add(ingreso);
		// {"M�todo","Moneda","Nom. Tarjeta","Cantidad","Cant. (en AR$)"};

		actualizarTablaMedioPago();
		this.ventanaRealizarVenta.getTextCantidad().setText("");
		this.ventanaRealizarVenta.getTextNumOperacion().setText("");
	}

	public MedioPagoDTO obtenerMedioPago(String nombre) {
		for (MedioPagoDTO m : this.listamediosDePago) {
			if (m.getDescripcion().equals(nombre)) {
				return m;
			}
		}
		return null;
	}

	public boolean esUnPagoConTarjeta(MedioPagoDTO medioPago) {
		return medioPago.getDescripcion().charAt(0) == 'T' || medioPago.getDescripcion().equals("Mercado Pago");
	}

	public boolean pagoYaFueRegistrado(MedioPagoDTO medioPago) {
		for (IngresosDTO i : this.listaDeIngresosARegistrar) {
			if (i.getMedioPago().equals(medioPago.getIdMoneda())) {
				return true;
			}
		}
		return false;
	}

	public void actualizarTablaMedioPago() {
		this.ventanaRealizarVenta.getModelTablaMedioPago().setRowCount(0);// borrar datos de la tabla
		this.ventanaRealizarVenta.getModelTablaMedioPago().setColumnCount(0);
		this.ventanaRealizarVenta.getModelTablaMedioPago()
				.setColumnIdentifiers(this.ventanaRealizarVenta.getNombreColumnasMedioPago());

		this.cantidadUsadaCC = 0;
		this.totalPagado = 0;
		this.totalAPagar = this.carritoACobrar.getTotal();
		this.totalAPagarAux = this.carritoACobrar.getTotal();

		for (IngresosDTO i : this.listaDeIngresosARegistrar) {
			for (MedioPagoDTO medioPago : this.listamediosDePago) {
				if (i.getMedioPago().equals(medioPago.getIdMoneda())) {
					String metodoPago = medioPago.getDescripcion();
					double valorConversion = medioPago.getTasaConversion();
					String moneda = valorConversion == 1 ? "AR$" : medioPago.getIdMoneda() + "$";
//					String nombreTarjeta = medioPago.getIdMoneda().charAt(0)=='T' ? medioPago.getDescripcion() : "-";
					String numOperacionAux = i.getOperacion();
					String numOperacion = numOperacionAux.equals("") ? "-" : numOperacionAux;

					double cantida = i.getCantidad();
					BigDecimal cantidad = new BigDecimal(cantida).setScale(2, RoundingMode.HALF_UP);
					double totalAr = cantida * valorConversion;
					BigDecimal totalArg = new BigDecimal(totalAr).setScale(2, RoundingMode.HALF_UP);

					Object[] fila = { metodoPago, moneda, numOperacion, cantidad, totalArg };
					this.ventanaRealizarVenta.getModelTablaMedioPago().addRow(fila);
					actualizarTotalAPagar(i, medioPago);
				}

			}
		}
		descontarDescuento();

//		descontarDescuento();

		this.totalAPagar -= this.descuento;

		BigDecimal totalPgd = new BigDecimal(this.totalPagado).setScale(2, RoundingMode.HALF_UP);
		BigDecimal totalPgr = new BigDecimal(this.totalAPagar).setScale(2, RoundingMode.HALF_UP);

		this.ventanaRealizarVenta.getLblPrecioVentaValor().setText("" + totalPgd);
		this.ventanaRealizarVenta.getLblTotalAPagarValor().setText("" + totalPgr);

	}

	public void actualizarTotalAPagar(IngresosDTO ingreso, MedioPagoDTO medioPago) {
		double pagadoArg = ingreso.getCantidad() * medioPago.getTasaConversion();

		if (medioPago.getIdMoneda().equals("CC")) {
			this.cantidadUsadaCC += pagadoArg;
		}

		this.totalPagado += pagadoArg;
		this.totalAPagar -= pagadoArg;
		this.totalAPagarAux = this.totalAPagar;
//		this.totalAPagar = this.totalAPagar-this.descuento;
	}

	public void descontarDescuento() {
		if (!todosLosCamposSonValidos()) {
			return;
		}

		// si el campo es vacio se reincia a 0
		if (this.ventanaRealizarVenta.getTextDescuento().getText().equals("")) {
			this.totalAPagarAux = this.totalAPagar;
			this.descuento = 0;

			BigDecimal desc = new BigDecimal(this.descuento).setScale(2, RoundingMode.HALF_UP);
			BigDecimal pgraux = new BigDecimal(this.totalAPagarAux).setScale(2, RoundingMode.HALF_UP);

			this.ventanaRealizarVenta.getLblDescuentoDescontado().setText("" + desc);
			this.ventanaRealizarVenta.getLblTotalAPagarValor().setText("" + pgraux);
			return;
		}
		if (this.ventanaRealizarVenta.getTextDescuento().getText() != null) {
			this.descuento = Double.parseDouble(this.ventanaRealizarVenta.getTextDescuento().getText());
			BigDecimal desc = new BigDecimal(this.descuento).setScale(2, RoundingMode.HALF_UP);

			this.ventanaRealizarVenta.getLblDescuentoDescontado().setText("" + desc);
			// si el desc escrito es mayor al total a pagar, se reinicia
			if (descuento > this.totalAPagar) {
				JOptionPane.showMessageDialog(null, "Esta cantidad de descuento supera el total a pagar!");

				this.totalAPagarAux = this.totalAPagar;
				this.descuento = 0;

				BigDecimal desc1 = new BigDecimal(this.descuento).setScale(2, RoundingMode.HALF_UP);
				BigDecimal pgraux = new BigDecimal(this.totalAPagarAux).setScale(2, RoundingMode.HALF_UP);

				this.ventanaRealizarVenta.getLblDescuentoDescontado().setText("" + desc1);
				this.ventanaRealizarVenta.getLblTotalAPagarValor().setText("" + pgraux);
				this.ventanaRealizarVenta.getTextDescuento().setText("");

				return;
			}

			this.totalAPagarAux = this.totalAPagar - this.descuento;
			BigDecimal pgraux = new BigDecimal(this.totalAPagarAux).setScale(2, RoundingMode.HALF_UP);
			this.ventanaRealizarVenta.getLblTotalAPagarValor().setText("" + pgraux);
		}
	}

	public boolean poseeSaldoSuficiente(ClienteDTO cliente, double cantidad) {
		double cantidadAgregada = 0;
		for (IngresosDTO i : this.listaDeIngresosARegistrar) {
			if (i.getMedioPago().equals("CC")) {
				cantidadAgregada += i.getCantidad();
			}
		}
		cantidadAgregada = cantidadAgregada + cantidad;
		return cliente.getCreditoDisponible() >= cantidadAgregada;
	}

	public void registrarPago(ActionEvent a) {
		if (this.listaDeIngresosARegistrar.size() == 0) {
			JOptionPane.showMessageDialog(null, "No se puede completar el pago. No hay ingresos que registrar");
			return;
		}
		if ((this.totalPagado + this.descuento) >= this.carritoACobrar.getTotal()) {

			for (DetalleCarritoDTO det : detalleCarritoACobrar) {

				for (MaestroProductoDTO mp : this.todosLosProductos) {
					if (mp.getIdMaestroProducto() == det.getIdProducto()) {
						generarOrdenesFabricacion.verificarYGenerarOrden(idSucursal, mp);

						// verificar generar pedido
						if (generarOrdenesFabricacion.faltaStockDeUnProductoEnUnaSucursal(idSucursal, mp)
								&& mp.getFabricado().equals("N")) {
							PedidosPendientes.generarPedidoAutomatico(idSucursal, mp);
						}

					}
				}
			}

			generarFactura();
			borrarCarritoConDetalle();

			if (!clienteCarrito.getEstado().equals("Moroso")) {
				verificarClienteFuturoMoroso();
			}

			// Consultamos si quiere ver la factura
			// si selecciona que si devuelve un 0, no un 1, y la x un -1
			ReporteFactura reporte = new ReporteFactura(this.facturaGenerada.getNroFacturaCompleta(), idSucursal,this.clienteCarrito);
			int resp = JOptionPane.showConfirmDialog(null, "Pago efectuado con exito. \nDesea ver la factura?",
					"Ver Factura", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (resp == 0) {
				
				reporte.mostrar();
			}
			limpiarVariables();		
			this.ventanaRealizarVenta.cerrar();
			vaciarDatosPrevios();
			this.controladorVisualizarCarritos.actualizarVentana();

		} else {
			JOptionPane.showMessageDialog(null, "Todav�a no se han pagado todos los productos!");
		}
	}

	public void verificarClienteFuturoMoroso() {
		if (esFuturoMoroso()) {
			if (!existeEnTablaPrimeraDeudaCliente()) {
				PrimeraDeudaCliente primeraDeudaCliente = new PrimeraDeudaCliente(new DAOSQLFactory());
				int idCliente = clienteCarrito.getIdCliente();
				String fechaDeuda = obtenerFechaDeHoy();
				PrimeraDeudaClienteDTO clienteMoroso = new PrimeraDeudaClienteDTO(0, idCliente, fechaDeuda);
				primeraDeudaCliente.insert(clienteMoroso);
			}
		}
	}

	public boolean existeEnTablaPrimeraDeudaCliente() {
		PrimeraDeudaCliente primeraDeudaCliente = new PrimeraDeudaCliente(new DAOSQLFactory());
		List<PrimeraDeudaClienteDTO> listaPrimeraDeudaCliente;
		listaPrimeraDeudaCliente = primeraDeudaCliente.readAll();
		for (PrimeraDeudaClienteDTO p : listaPrimeraDeudaCliente) {
			if (p.getIdCliente() == clienteCarrito.getIdCliente()) {
				return true;
			}
		}
		return false;
	}

	public boolean esFuturoMoroso() {
		if (clienteCarrito.getCreditoDisponible() < clienteCarrito.getLimiteCredito()) {
			return true;
		}
		return false;
	}

	public String obtenerFechaDeHoy() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		String fecha = dtf.format(LocalDateTime.now());
		return fecha;
	}

	public void limpiarVariables() {
		this.carritoACobrar = null;
		this.detalleCarritoACobrar = null;
		this.clienteCarrito = null;
		this.totalAPagar = 0;
		this.totalAPagarAux = 0;
		this.totalAPagarSinDescuento = 0;
		this.totalPagado = 0;
	}

	public void cancelarPago(ActionEvent a) {

		vaciarDatosPrevios();

		this.ventanaRealizarVenta.cerrar();

	}

	public void quitarMedioPago(ActionEvent a) {
		int filaSeleccionada = this.ventanaRealizarVenta.getTableMedioPago().getSelectedRow();
		if (filaSeleccionada == -1) {
			JOptionPane.showMessageDialog(null, "No ha seleccionado ningun medio de pago");
			return;
		}
		IngresosDTO ingreso = this.listaDeIngresosARegistrar.get(filaSeleccionada);
		if (ingreso.getMedioPago().equals("CTE")) {
			this.cantidadUsadaCC = 0;
		}

		this.listaDeIngresosARegistrar.remove(filaSeleccionada);
		actualizarTablaMedioPago();
	}

//	tipo factura Ri: Responsable inscripto A / M:Monotributista B / CF: Consumidor Final B / E: Exento E
//	NroFacturaCompleta: Primero va el tipo de la facturaSeguido del nro de la sucursal (4 - 5 digtos)Por ultimo 8 DIGITOS SECUENCIALES que conforma la id de la factura
	public void generarFactura() {

		int id = 0;
		double montoPendiente = this.cantidadUsadaCC;

		ClienteDTO client = this.cliente.selectCliente(this.carritoACobrar.getIdCliente());

		int idCliente = client.getIdCliente();
		String nombreCliente = client.getApellido() + " " + client.getNombre();

		int idCajero = idEmpleado;
		int idVendedor = this.carritoACobrar.getIdVendedor();
		EmpleadoDTO cajero = this.cajero.selectEmpleado(idCajero);
		String nombreCajero = cajero.getApellido() + " " + cajero.getNombre();
		EmpleadoDTO vendedor = this.cajero.selectEmpleado(idVendedor);

		String nombreVendedor = vendedor.getApellido() + " " + vendedor.getNombre();

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String fecha = dtf.format(LocalDateTime.now());

		String tipoFactura = determinarCategoriaFactura(client);

		String nroFacturaCompleto = generarNroSucursal() + tipoFactura + generarNroFacturaSecuencial(tipoFactura);
		int idSucursal = this.carritoACobrar.getIdSucursal();
		double descuento = this.descuento;

//		double totalBruto = this.carritoACobrar.getTotal(); 
//		double totalFactura = calcularTotal(client);//Habria que chequearlo mejor por el impuesto afip

		double totalBruto = calcularTotalBruto(client);
		double totalFactura = this.carritoACobrar.getTotal() - this.descuento;

		String tipoVenta = client.getTipoCliente();// mayorista/minorista

		String calle = client.getCalle();
		String altura = client.getAltura();
		String pais = client.getPais();
		String provincia = client.getProvincia();
		String localidad = client.getLocalidad();
		String codPostal = client.getCodPostal();
		String CUIL = client.getCUIL();
		String correo = client.getCorreo();

		String impuestoAFIP = obtenerNombreCategoria(client);
		double IVA = calcularIVA(client) ? ((21 * totalFactura) / 100) : 0.0;
		totalBruto = calcularIVA(client) ? totalFactura - ((21 * totalFactura) / 100) : totalFactura;
		this.facturaGenerada = new FacturaDTO(id, montoPendiente, idCliente, nombreCliente, idCajero, nombreCajero,
				idVendedor, nombreVendedor, fecha, tipoFactura, nroFacturaCompleto, idSucursal, descuento, totalBruto,
				totalFactura, tipoVenta, calle, altura, pais, provincia, localidad, codPostal, CUIL, correo,
				impuestoAFIP, IVA);

		// registramos la factura en la bd
		boolean insertFactura = this.factura.insert(this.facturaGenerada);
		if (insertFactura == false) {
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error");
			return;
		}
		ArrayList<FacturaDTO> todasLasFacturas = (ArrayList<FacturaDTO>) this.factura.readAll();
		this.facturaGenerada.setIdFactura(todasLasFacturas.get(todasLasFacturas.size() - 1).getIdFactura());
		registrarDetallesFactura(this.facturaGenerada);
		registrarIngreso(this.facturaGenerada);
	}

	public void registrarDetallesFactura(FacturaDTO factura) {
		ClienteDTO client = this.cliente.selectCliente(this.carritoACobrar.getIdCliente());

		for (DetalleCarritoDTO detalleCarrito : this.detalleCarritoACobrar) {
			int id = 0;
			int idProd = detalleCarrito.getIdProducto();
			int cant = (int) detalleCarrito.getCantidad();
			MaestroProductoDTO producto = this.maestroProducto.selectMaestroProducto(idProd);
			String descr = producto.getDescripcion();
			double precioCosto = producto.getPrecioCosto();

			double precioVenta = detalleCarrito.getPrecio() / detalleCarrito.getCantidad();
			double precioVentaConIVA = calcularIVA(client) ? (precioVenta - (21 * precioVenta) / 100) : precioVenta;

			double monto = precioVenta * cant;
			double montoConIVA = calcularIVA(client) ? (monto - ((21 * monto) / 100)) : monto;

			int idFactura = factura.getIdFactura();
			String unidadMedida = "" + producto.getUnidadMedida();// CHEQUEAR

			DetalleFacturaDTO detalleFactur = new DetalleFacturaDTO(id, idProd, cant, descr, precioCosto,
					precioVentaConIVA, montoConIVA, idFactura, unidadMedida);
			boolean insertDetalleFactura = this.detalleFactura.insert(detalleFactur);// se guarda en la bd
			if (!insertDetalleFactura) {
				JOptionPane.showMessageDialog(null, "Ha ocurrido un error en uno de los ingresos de factura");
			}
		}
	}

	public void registrarIngreso(FacturaDTO factura) {
		DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		String fecha = f.format(LocalDateTime.now());
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
		String hora = dtf.format(LocalDateTime.now());

		for (IngresosDTO ingreso : this.listaDeIngresosARegistrar) {
			ingreso.establecerDatosFaltantes(factura.getNroFacturaCompleta(), fecha, hora);
			this.ingresos.insert(ingreso);
			if (ingreso.getMedioPago().equals("CC")) {
				double nuevaCant = clienteCarrito.getCreditoDisponible() - ingreso.getCantidad();
				clienteCarrito.setCreditoDisponible(nuevaCant);

				boolean update = this.cliente.update(clienteCarrito.getIdCliente(), clienteCarrito);
				if (!update) {
					JOptionPane.showMessageDialog(null,
							"Ha ocurrido un error al actualizar el credito disponible del cliente");
				} else {
//					System.out.println("se actualiza el credito disponible del cliente: " + clienteCarrito.getNombre()
//							+ ", a: " + nuevaCant);
				}
			}

		}
		this.listaDeIngresosARegistrar.removeAll(this.listaDeIngresosARegistrar);
	}

//	public String generarNroSucursal() {
//		String nroSucursal = "" + this.carritoACobrar.getIdSucursal();
//		String nroSucFactura = "" + nroSucursal;
//		while (nroSucFactura.length() < 5) {
//			nroSucFactura = "0" + nroSucFactura;
//		}
//		return nroSucFactura;
//	}

	public String generarNroSucursal() {
		SucursalDTO sucursal = this.sucursal.select(idSucursal);
		return sucursal.getNroSucursal();
	}

	public String generarNroFacturaSecuencial(String tipoFactura) {
		/*
		 * ArrayList<FacturaDTO> todasLasFacturas = (ArrayList<FacturaDTO>)
		 * this.factura.readAll(); if (todasLasFacturas.size() == 0) { return "1"; }
		 * FacturaDTO ultFactura = todasLasFacturas.get(todasLasFacturas.size() - 1);
		 * String nroCompletoUlt = ultFactura.getNroFacturaCompleta();
		 * 
		 * String ultSec = ""; // damos por hecho que 1 dig sera para el tipo de
		 * factura, y 5 para el nro de // sucursal for (int i = 6; i <
		 * nroCompletoUlt.length(); i++) { ultSec = ultSec + nroCompletoUlt.charAt(i);//
		 * obtenemos los ult 8 dig secuenciales } int viejoSuma =
		 * Integer.parseInt(ultSec); int nuevoSec = (viejoSuma + 1); return "" +
		 * nuevoSec;
		 */
		String nroFacturaSec = "";
		FacturaDTO ultFactura = obtenerUltFacturaParaSerie(tipoFactura);

		if (ultFactura != null) {
			// si ya existe entonces hay que buscar el utimo y sumarle +1

			int longitudFactura = ultFactura.getNroFacturaCompleta().length();
			for (int i = 8; i >= 1; i--) {
				nroFacturaSec = nroFacturaSec + ultFactura.getNroFacturaCompleta().charAt(longitudFactura - i);
			}
			int suma = Integer.parseInt(nroFacturaSec) + 1;
			nroFacturaSec = suma + "";
			if (nroFacturaSec.length() < 8) {
				while (nroFacturaSec.length() < 8) {
					nroFacturaSec = "0" + nroFacturaSec;
				}
				return nroFacturaSec;

			} else {
				return nroFacturaSec;
			}

		} else {
			// si no existe hay que crear uno nuevo. Ej 00000001
			for (int i = 1; i <= 7; i++) {
				nroFacturaSec = nroFacturaSec + 0;
			}
			return nroFacturaSec + "1";
		}
	}

	public FacturaDTO obtenerUltFacturaParaSerie(String tipoFactura) {
		ArrayList<FacturaDTO> todasLasFacturas = (ArrayList<FacturaDTO>) this.factura.readAll();
		FacturaDTO ultFactura = null;
		for (FacturaDTO f : todasLasFacturas) {
			if (f.getIdSucursal() == this.idSucursal) {
				for (int i = 0; i < f.getNroFacturaCompleta().length(); i++) {
					// si en algun caracter de la factura existe un A-B o E entonces es true
					if (f.getNroFacturaCompleta().charAt(i) == tipoFactura.charAt(0)) {
						ultFactura = f;
					}
				}
			}

		}
		return ultFactura;
	}

	public boolean calcularIVA(ClienteDTO cliente) {
		if (cliente.getImpuestoAFIP().equals("RI") || cliente.getImpuestoAFIP().equals("M")) {
			return true;
		}
		return false;
	}

	public double calcularTotalBruto(ClienteDTO cliente) {
		double total = this.carritoACobrar.getTotal();
		if (calcularIVA(cliente)) {
			total = total - ((21 / 100) * total);
		}
		return total;
	}

	public String obtenerNombreCategoria(ClienteDTO cliente) {
		String tipo = cliente.getImpuestoAFIP();
		if (tipo.equals("RI")) {
			return "Responsable Inscripto";
		}
		if (tipo.equals("M")) {
			return "Monotributista";
		}
		if (tipo.equals("CF")) {
			return "Consumidor Final";
		}
		if (tipo.equals("E")) {
			return "Exento";
		}
		return "Categoria no encontrada en el sistema";
	}

	public String determinarCategoriaFactura(ClienteDTO cliente) {
		if (cliente.getImpuestoAFIP().equals("RI") || cliente.getImpuestoAFIP().equals("M")) {
			return "A";
		}
		if (cliente.getImpuestoAFIP().equals("E") || cliente.getImpuestoAFIP().equals("CF")) {
			return "B";
		}
		if (cliente.getPais() != "Argentina") {// si la persona no vive en arg es excento
			return "E";
		}
		return "";
	}

	public void borrarCarritoConDetalle() {
		for (DetalleCarritoDTO detalleCarrito : this.detalleCarritoACobrar) {
			if (this.carritoACobrar.getIdCarrito() == detalleCarrito.getIdCarrito()) {
				this.detalleCarrito.delete(detalleCarrito);
			}
		}
		this.carrito.delete(this.carritoACobrar);
	}

	public boolean todosLosCamposSonValidos() {
		String monto = this.ventanaRealizarVenta.getTextCantidad().getText();
		boolean m = monto.matches("^[0-9]+(\\.[0-9]{1,2})?$");

		if (!monto.equals("") && !m) {
//        	JOptionPane.showMessageDialog(null, "El campo monto no es correcto");
			return false;
		}

		String desc = this.ventanaRealizarVenta.getTextDescuento().getText();
		boolean d = desc.matches("^[0-9]+(\\.[0-9]{1,2})?$");
		if (!desc.equals("") && !d) {
//        	JOptionPane.showMessageDialog(null, "El campo descuento no es correcto");
			return false;
		}
		return true;

	}

	public void validarTeclado() {

		this.ventanaRealizarVenta.getTextNumOperacion().addKeyListener((new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarSoloNumeros(e);
			}
		}));
		this.ventanaRealizarVenta.getTextCantidad().addKeyListener((new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarSoloNumerosYpuntos(e);
			}
		}));
		this.ventanaRealizarVenta.getTextDescuento().addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarSoloNumerosYpuntos(e);
			}
		});
	}

	public boolean ventanaYaFueInicializada() {
		return this.ventanaRealizarVenta.getFrame().isShowing();
	}

	public void vaciarDatosPrevios() {
		this.ventanaRealizarVenta.getModelTablaMedioPago().setRowCount(0);// borrar datos de la tabla
		this.ventanaRealizarVenta.getModelTablaMedioPago().setColumnCount(0);
		this.ventanaRealizarVenta.getModelTablaMedioPago()
				.setColumnIdentifiers(this.ventanaRealizarVenta.getNombreColumnasMedioPago());

		this.listaDeIngresosARegistrar.removeAll(this.listaDeIngresosARegistrar);// se borran los futuros ingresos, es
																					// decir los medios de pagos
		this.totalAPagar = 0;
		BigDecimal tpgr = new BigDecimal(this.totalAPagar).setScale(2, RoundingMode.HALF_UP);
		this.ventanaRealizarVenta.getLblTotalAPagarValor().setText("" + tpgr);

		this.cantidadUsadaCC = 0;
		this.totalPagado = 0;
		this.totalAPagar = 0;
		this.totalAPagarAux = 0;

		this.ventanaRealizarVenta.getTextNumOperacion().setText("");
		this.ventanaRealizarVenta.getTextCantidad().setText("");
		this.ventanaRealizarVenta.getLblPrecioVentaValor().setText("" + 0);
		this.ventanaRealizarVenta.getLblTotalAPagarValor().setText("" + 0);
		this.ventanaRealizarVenta.getTextDescuento().setText("");
	}
}
