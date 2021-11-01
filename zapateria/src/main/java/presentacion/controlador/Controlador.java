package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;
import dto.SucursalDTO;
import inicioSesion.sucursalProperties;
import modelo.Caja;
import modelo.Carrito;
import modelo.Cliente;
import modelo.ConfiguracionBD;
import modelo.DetalleCarrito;
import modelo.DetalleFactura;
import modelo.Egresos;
import modelo.Empleado;
import modelo.Factura;
import modelo.Ingresos;
import modelo.MaestroProducto;
import modelo.MedioPago;
import modelo.PedidosPendientes;
import modelo.ProductoDeProveedor;
import modelo.Proveedor;
import modelo.Stock;
import modelo.Sucursal;
import modelo.generarOrdenesFabricacion;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.controlador.Cajero.ControladorCierreCaja;
import presentacion.controlador.Cajero.ControladorEgresosCaja;
import presentacion.controlador.Cajero.ControladorIngresosCaja;
import presentacion.controlador.Cajero.ControladorRealizarVenta;
import presentacion.controlador.Cajero.ControladorVisualizarCarritos;
import presentacion.controlador.ModificarProducto.ControladorHistorialCambioMProducto;
import presentacion.controlador.ModificarProducto.ControladorModificarMProducto;
import presentacion.controlador.compraVirtual.ControladorVisualizarComprasVirtuales;
import presentacion.controlador.fabrica.ReControladorOperario;
import presentacion.controlador.generarOrdenesManufactura.ControladorGenerarOrdenesManufactura;
import presentacion.controlador.gerente.ControladorAltaCliente;
import presentacion.controlador.reporteRanking.ControladorReporteRankingVentaXSucursal;
import presentacion.controlador.supervisor.ControladorAltaProducto;
import presentacion.controlador.supervisor.ControladorAsignarProductoAProveedor;
import presentacion.controlador.supervisor.ControladorConsultarProveedor;
import presentacion.controlador.supervisor.ControladorVerPedidosAProveedor;
import presentacion.vista.VentanaMenu;
import presentacion.vista.VentanaMenuSistemaDeVentas;
import presentacion.vista.VentanaTareasAutomatizadas;

public class Controlador implements ActionListener {

	private int idSucursal = 0;
	public void obtenerDatosPropertiesSucursal() {
		try {
			sucursalProperties sucursalProp = sucursalProperties.getInstance();
			idSucursal = Integer.parseInt(sucursalProp.getValue("IdSucursal"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	SucursalDTO sucursalObj;

	MaestroProducto maestroProducto;
	Stock stock;
	Sucursal sucursal;
	Cliente cliente;

	MedioPago medioPago;
	Caja caja;

	Ingresos ingresos;
	Egresos egresos;
	Empleado empleado;

	Carrito carrito;
	DetalleCarrito detalleCarrito;

	Factura factura;
	DetalleFactura detalleFactura;

	Proveedor proveedor;
	ProductoDeProveedor productoDeProveedor;

	PedidosPendientes pedidosPendientes;
	// Controladores
	ControladorBusquedaCliente controladorBusquedaCliente;
	ControladorBusquedaProductos controladorBusquedaProducto;

	ControladorHistorialCambioCotizacion controladorHistorialCambioCotizacion;
	ControladorHistorialCambioMProducto controladorHistorialCambioMProducto;

	ControladorModificarCotizacion controladorModificarCotizacion;
	ControladorModificarMProducto controladorModificarMProducto;

	ControladorEgresosCaja controladorEgresosCaja;

	ReControladorOperario reControladorOperario;

	ControladorGenerarOrdenesManufactura controladorGenerarOrdenesManufactura;

	// Controlador cajero
	ControladorCierreCaja controladorCierreCaja;
	ControladorIngresosCaja controladorIngresosCaja;
	ControladorVisualizarCarritos controladorVisualizarCarritos;
	ControladorRealizarVenta controladorRealizarVenta;

	// Controlador gerente
	ControladorAltaCliente controladorAltaCliente;

	// Controlador supervisor
	ControladorAltaProducto controladorAltaProducto;
	ControladorAsignarProductoAProveedor controladorAsignarProductoAProveedor;
	ControladorConsultarProveedor controladorConsultarProveedor;

	// Ver pedidos pendientes
	ControladorVerPedidosAProveedor controladorVerPedidosAProveedor;

	ControladorVisualizarComprasVirtuales controladorVisualizarComprasVirtuales;
	ControladorReporteRankingVentaXSucursal controladorReporteRankingVentaXSucursal;

	VentanaMenu ventanaMenu;
	VentanaMenuSistemaDeVentas ventanaMenuSistemaDeVentas;

	VentanaTareasAutomatizadas ventanaTareasAutomatizadas;
	ControladorTareasAutomatizadas controladorTareasAutomatizadas;

	// Coso para el properties
	private ConfiguracionBD config = ConfiguracionBD.getInstance();

	public Controlador() {
		obtenerDatosPropertiesSucursal();

		this.cliente = new Cliente(new DAOSQLFactory());
		this.maestroProducto = new MaestroProducto(new DAOSQLFactory());
		this.stock = new Stock(new DAOSQLFactory());
		this.sucursal = new Sucursal(new DAOSQLFactory());

		this.medioPago = new MedioPago(new DAOSQLFactory());
		this.caja = new Caja(new DAOSQLFactory());

		this.carrito = new Carrito(new DAOSQLFactory());
		this.detalleCarrito = new DetalleCarrito(new DAOSQLFactory());

		this.ingresos = new Ingresos(new DAOSQLFactory());
		this.egresos = new Egresos(new DAOSQLFactory());
		this.empleado = new Empleado(new DAOSQLFactory());

		this.factura = new Factura(new DAOSQLFactory());
		this.detalleFactura = new DetalleFactura(new DAOSQLFactory());

		this.proveedor = new Proveedor(new DAOSQLFactory());
		this.productoDeProveedor = new ProductoDeProveedor(new DAOSQLFactory());
		
		this.sucursalObj = this.sucursal.select(this.idSucursal);

		this.pedidosPendientes = new PedidosPendientes(new DAOSQLFactory());
	}

	public void inicializar() {
		System.out.println("Sucursal "+idSucursal);
		this.ventanaMenu = new VentanaMenu();
		this.ventanaMenuSistemaDeVentas = new VentanaMenuSistemaDeVentas();

		this.ventanaTareasAutomatizadas = new VentanaTareasAutomatizadas();

//		this.ventanaBusquedaCliente= new VentanaBusquedaCliente();
//		this.vistaBusquedaProducto = new VentanaBusquedaProductos();

		this.reControladorOperario = new ReControladorOperario(this, this.sucursalObj);

		// Config
		this.controladorTareasAutomatizadas = new ControladorTareasAutomatizadas(this.ventanaTareasAutomatizadas,
				config);

		// armar Venta
		this.controladorBusquedaCliente = new ControladorBusquedaCliente(this, cliente);
		this.controladorBusquedaProducto = new ControladorBusquedaProductos(this.maestroProducto, this.stock,
				this.sucursal, this.carrito, this.detalleCarrito);
		this.controladorBusquedaCliente.setControladorBusquedaProducto(this.controladorBusquedaProducto);
		this.controladorBusquedaProducto.setControladorBusquedaCliente(this.controladorBusquedaCliente);

		// Registrar Venta
		this.controladorVisualizarCarritos = new ControladorVisualizarCarritos(this, carrito, detalleCarrito, cliente,
				maestroProducto, stock);
		this.controladorRealizarVenta = new ControladorRealizarVenta(this.medioPago, this.cliente, this.empleado,
				this.carrito, this.detalleCarrito, this.maestroProducto, this.factura, this.detalleFactura,
				this.ingresos);
		this.controladorVisualizarCarritos.setControladorRealizarVenta(this.controladorRealizarVenta);
		this.controladorRealizarVenta.setControladorVisualizarCarritos(this.controladorVisualizarCarritos);

		// Supervisor
		this.controladorAltaProducto = new ControladorAltaProducto(this, this.maestroProducto, this.proveedor,
				this.productoDeProveedor);
		this.controladorAsignarProductoAProveedor = new ControladorAsignarProductoAProveedor(this.maestroProducto,
				this.proveedor, this.productoDeProveedor);
		this.controladorConsultarProveedor = new ControladorConsultarProveedor(this, this.proveedor,
				this.productoDeProveedor);

		this.controladorConsultarProveedor
				.setControladorAsignarProductoAProveedor(this.controladorAsignarProductoAProveedor);
		this.controladorConsultarProveedor.setControladorAltaProducto(this.controladorAltaProducto);

		this.controladorAltaProducto.setControladorConsultarProveedor(this.controladorConsultarProveedor);
		this.controladorAsignarProductoAProveedor.setControladorConsultarProveedor(this.controladorConsultarProveedor);

		this.controladorVisualizarComprasVirtuales = new ControladorVisualizarComprasVirtuales(this);
		this.controladorReporteRankingVentaXSucursal = new ControladorReporteRankingVentaXSucursal(this);

		// cotizacion
		this.controladorModificarCotizacion = new ControladorModificarCotizacion(this, medioPago);

		// mod precio unitario
		this.controladorModificarMProducto = new ControladorModificarMProducto(this, this.maestroProducto);

		// Ingreso Caja
		this.controladorIngresosCaja = new ControladorIngresosCaja(this, caja);

		// Egreso Caja
		this.controladorEgresosCaja = new ControladorEgresosCaja(this, egresos, pedidosPendientes);

		// Cierre Caja
		this.controladorCierreCaja = new ControladorCierreCaja(this, caja, ingresos, egresos, empleado);

		// Generar ordenes de manufac
		this.controladorGenerarOrdenesManufactura = new ControladorGenerarOrdenesManufactura(this);

		// Alta cliente
		this.controladorAltaCliente = new ControladorAltaCliente(this, this.cliente);

		// Ver pedidos a prov
		this.controladorVerPedidosAProveedor = new ControladorVerPedidosAProveedor(this, pedidosPendientes, stock);

		// Menu principal
		this.ventanaMenu.getBtnOperatoriaDeFabrica().addActionListener(a -> iniciarSistemaOperatoriaFabrica(a));
		this.ventanaMenu.getBtnSistemaDeVentas().addActionListener(a -> iniciarSistemaDeVentas(a));

		// VentanaMenu de sistemas
		this.ventanaMenuSistemaDeVentas.getBtnRegresar().addActionListener(a -> regresarMenuPrincipal(a));
		this.ventanaMenuSistemaDeVentas.getBtnConfig().addActionListener(a -> pasarAConfig(a));
		// armar Venta
		this.ventanaMenuSistemaDeVentas.getBtnArmarVenta().addActionListener(a -> pasarAArmarVenta(a));
		this.ventanaMenuSistemaDeVentas.getBtnCobrarVenta().addActionListener(a -> pasarACobrarVenta(a));

		// cotizacion
		this.ventanaMenuSistemaDeVentas.getBtnCotizaciones().addActionListener(a -> pasarACotizaciones(a));

		// mod precioUnitario
		this.ventanaMenuSistemaDeVentas.getBtnModPrecioUnitario()
				.addActionListener(a -> pasarAModificarPrecioUnitario(a));

		// Ingreso caja
		this.ventanaMenuSistemaDeVentas.getBtnIngresoCaja().addActionListener(a -> pasarAIngresoDeCaja(a));

		// Egreso
		this.ventanaMenuSistemaDeVentas.getBtnEgresoCaja().addActionListener(a -> pasarAEgresosCaja(a));

		// Cierre de caja
		this.ventanaMenuSistemaDeVentas.getBtnCierreCaja().addActionListener(a -> pasarACierreDeCaja(a));

		// Generar ordenes de manufac
		this.ventanaMenuSistemaDeVentas.getBtnGenerarOrdenDe().addActionListener(a -> pasarAGenerarOrdenManufac(a));

		// Alta cliente
		this.ventanaMenuSistemaDeVentas.getBtnRegistrarUnCliente().addActionListener(a -> pasarARegistrarUnCliente(a));

		// Alta producto
		this.ventanaMenuSistemaDeVentas.getBtnIngresarProductoNuevo()
				.addActionListener(a -> pasarADarDeAltaProducto(a));
		this.ventanaMenuSistemaDeVentas.getBtnVerProveedores().addActionListener(a -> pasarAConsultarProveedores(a));
		// Consultar proveedores y asignarle un producto

		// Ver pedidos a prov
		this.ventanaMenuSistemaDeVentas.getBtnVerPedidosA().addActionListener(a -> pasarAVerPedidosAProveedor(a));

		this.ventanaMenuSistemaDeVentas.getBtnVerComprasVirtuales()
				.addActionListener(a -> pasarAVerComprasVirtuales(a));
		this.ventanaMenuSistemaDeVentas.getBtnVerReporteRanking().addActionListener(a -> pasarAVerRanking(a));
//		try {
//			EnviarCorreosAProveedoresAutomatico.verificarEnvioDeMailsAutomatico(config);
//		}catch (IOException | ParseException e) {
//			e.printStackTrace();
//		}
		escribirNombreSucursal();
		generarOrdenesFabricacion.actualizarTodosLosTrabajosListosParaLosEnvios();
	}

	public void escribirNombreSucursal() {
		List<SucursalDTO> sucursales = this.sucursal.readAll();
		for (SucursalDTO s : sucursales) {
			if (s.getIdSucursal() == this.idSucursal) {
				this.ventanaMenuSistemaDeVentas.getLblNombreSucursal().setText(s.getNombre());
			}
		}
	}

	public void mostrarVentanaMenu() {
		this.ventanaMenu.show();
	}

	public void mostrarVentanaMenuDeSistemas() {
		this.ventanaMenuSistemaDeVentas.show();
	}

	public void regresarMenuPrincipal(ActionEvent a) {
		this.ventanaMenuSistemaDeVentas.cerrar();
		this.ventanaMenu.show();
		this.ventanaTareasAutomatizadas.cerrar();
	}

	public void pasarAConfig(ActionEvent a) {

		if (this.controladorTareasAutomatizadas.ventanaYaFueInicializada()) {
			return;
		}
		this.ventanaTareasAutomatizadas.show();
		this.controladorTareasAutomatizadas.inicializar();
	}

	public void iniciarSistemaOperatoriaFabrica(ActionEvent a) {
		this.ventanaMenu.cerrar();
		this.reControladorOperario.inicializar();
		this.reControladorOperario.mostrarVentana();

	}

	public void iniciarSistemaDeVentas(ActionEvent a) {
		this.ventanaMenu.cerrar();
		this.ventanaMenuSistemaDeVentas.show();
	}

	public void pasarAArmarVenta(ActionEvent a) {
		if (!this.controladorIngresosCaja.estaCajaAbierta()) {
			JOptionPane.showMessageDialog(null, "La caja no esta abierta");
			return;
		}
		this.ventanaMenuSistemaDeVentas.cerrar();
		controladorBusquedaCliente.inicializar();
		this.controladorBusquedaCliente.mostrarVentana();
		this.ventanaTareasAutomatizadas.cerrar();
	}

	public void pasarACobrarVenta(ActionEvent a) {
		if (!this.controladorIngresosCaja.estaCajaAbierta()) {
			JOptionPane.showMessageDialog(null, "La caja no esta abierta");
			return;
		}

		this.ventanaMenuSistemaDeVentas.cerrar();
		this.controladorVisualizarCarritos.inicializar();
		this.controladorVisualizarCarritos.mostrarVentana();
		this.ventanaTareasAutomatizadas.cerrar();
	}

	// Cotizacion
	public void pasarACotizaciones(ActionEvent a) {
		this.ventanaMenuSistemaDeVentas.cerrar();
		this.controladorModificarCotizacion.inicializar();
		this.controladorModificarCotizacion.mostrarVentana();
		this.ventanaTareasAutomatizadas.cerrar();
	}

	// Mod precio unitario
	public void pasarAModificarPrecioUnitario(ActionEvent a) {
		this.ventanaMenuSistemaDeVentas.cerrar();
		this.controladorModificarMProducto.inicializar();
		this.controladorModificarMProducto.mostrarVentana();
		this.ventanaTareasAutomatizadas.cerrar();
	}

	// Ingreso de caja
	public void pasarAIngresoDeCaja(ActionEvent a) {
		if (this.controladorIngresosCaja.cajaYaFueCerrada()) {
			JOptionPane.showMessageDialog(null, "La caja ya fue cerrada");
			return;
		}

		this.ventanaMenuSistemaDeVentas.cerrar();
		this.controladorIngresosCaja.inicializar();
		this.controladorIngresosCaja.mostrarVentana();
		this.ventanaTareasAutomatizadas.cerrar();
	}

	// Egreso
	public void pasarAEgresosCaja(ActionEvent a) {
		if (!this.controladorIngresosCaja.estaCajaAbierta()) {
			JOptionPane.showMessageDialog(null, "La caja no esta abierta");
			return;
		}

		this.ventanaMenuSistemaDeVentas.cerrar();
		this.controladorEgresosCaja.inicializar();
		this.controladorEgresosCaja.mostrarVentana();
		this.ventanaTareasAutomatizadas.cerrar();
	}

	// Cierre de caja
	public void pasarACierreDeCaja(ActionEvent a) {
		if (!this.controladorIngresosCaja.estaCajaAbierta()) {
			JOptionPane.showMessageDialog(null, "La caja no esta abierta");
			return;
		}

		if (this.controladorIngresosCaja.cajaYaFueCerrada()) {
			JOptionPane.showMessageDialog(null, "La caja ya fue cerrada");
			return;
		}

		this.ventanaMenuSistemaDeVentas.cerrar();
		this.controladorCierreCaja.inicializar();
		this.controladorCierreCaja.mostrarVentana();
		this.ventanaTareasAutomatizadas.cerrar();
	}

	// Generar ord de manuf
	public void pasarAGenerarOrdenManufac(ActionEvent a) {
		this.ventanaMenuSistemaDeVentas.cerrar();
		this.controladorGenerarOrdenesManufactura.inicializar();
		this.controladorGenerarOrdenesManufactura.mostrarVentana();
		this.ventanaTareasAutomatizadas.cerrar();
	}

	public void pasarARegistrarUnCliente(ActionEvent a) {
		this.ventanaMenuSistemaDeVentas.cerrar();
		this.controladorAltaCliente.inicializar();
		this.controladorAltaCliente.mostrarVentana();
		this.ventanaTareasAutomatizadas.cerrar();
	}

	// Supervisor
	public void pasarADarDeAltaProducto(ActionEvent a) {
		this.ventanaMenuSistemaDeVentas.cerrar();
		this.controladorAltaProducto.inicializar();
		this.controladorAltaProducto.mostrarVentana();
		this.ventanaTareasAutomatizadas.cerrar();
	}

	public void pasarAConsultarProveedores(ActionEvent a) {
		this.ventanaMenuSistemaDeVentas.cerrar();
		this.controladorConsultarProveedor.inicializar();
		this.controladorConsultarProveedor.mostrarVentana();
		this.ventanaTareasAutomatizadas.cerrar();
	}

	// Ver pedidos a proveedor
	public void pasarAVerPedidosAProveedor(ActionEvent a) {
		this.ventanaMenuSistemaDeVentas.cerrar();
		this.controladorVerPedidosAProveedor.inicializar();
		this.controladorVerPedidosAProveedor.mostrarVentana();
		this.ventanaTareasAutomatizadas.cerrar();
	}

	public void pasarAVerComprasVirtuales(ActionEvent a) {
		this.ventanaMenuSistemaDeVentas.cerrar();
		this.controladorVisualizarComprasVirtuales.inicializar();
	}

	private void pasarAVerRanking(ActionEvent a) {
		this.ventanaMenuSistemaDeVentas.cerrar();
		this.controladorReporteRankingVentaXSucursal.inicializar();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

}
