package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.JOptionPane;
import dto.SucursalDTO;
import inicioSesion.empleadoProperties;
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
import modelo.HistorialCambioCliente;
import modelo.Ingresos;
import modelo.Localidad;
import modelo.MaestroProducto;
import modelo.MedioPago;
import modelo.Pais;
import modelo.PedidosPendientes;
import modelo.ProductoDeProveedor;
import modelo.Proveedor;
import modelo.Provincia;
import modelo.Stock;
import modelo.Sucursal;
import modelo.generarOrdenesFabricacion;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.controlador.Cajero.ControladorCierreCaja;
import presentacion.controlador.Cajero.ControladorEgresosCaja;
import presentacion.controlador.Cajero.ControladorIngresosCaja;
import presentacion.controlador.Cajero.ControladorRealizarVenta;
import presentacion.controlador.Cajero.ControladorVisualizarCarritos;
import presentacion.controlador.Login.ControladorLogin;
import presentacion.controlador.ModificarProducto.ControladorModificarMProducto;
import presentacion.controlador.compraVirtual.ControladorVisualizarComprasVirtuales;
import presentacion.controlador.fabrica.ControladorHistorialPasos;
import presentacion.controlador.fabrica.ReControladorOperario;
import presentacion.controlador.fabrica.receta.ControladorVerPasos;
import presentacion.controlador.generarOrdenesManufactura.ControladorGenerarOrdenesManufactura;
import presentacion.controlador.gerente.ControladorAltaCliente;
import presentacion.controlador.gerente.ControladorGestionarClientes;
import presentacion.controlador.gerente.ControladorGestionarSucursales;
import presentacion.controlador.gerente.ControladorHistorialDeCambiosDeCliente;
import presentacion.controlador.gestionarEmpleados.ControladorGestionarEmpleados;
import presentacion.controlador.reporteRanking.ControladorReporteRankingVentaXSucursal;
import presentacion.controlador.supervisor.ControladorAltaProducto;
import presentacion.controlador.supervisor.ControladorAltaProveedor;
import presentacion.controlador.supervisor.ControladorAsignarProductoAProveedor;
import presentacion.controlador.supervisor.ControladorGestionarProveedores;
import presentacion.controlador.supervisor.ControladorGenerarPedidoAProveedorManualmente;
import presentacion.controlador.supervisor.ControladorVerPedidosAProveedor;
import presentacion.controlador.supervisor.ControladorGestionarProductos;
import presentacion.vista.Login.VentanaAdministrador;
import presentacion.vista.Login.VentanaCajero;
import presentacion.vista.Login.VentanaOperarioFabrica;
import presentacion.vista.Login.VentanaSupervisor;
import presentacion.vista.Login.VentanaSupervisorFabrica;
import presentacion.vista.Login.VentanaVendedor;
import presentacion.vista.dashboardGerente.VentanaDashboardGerente;

public class Controlador {

	private int idSucursal = 0;
	private String tipoEmpleado = "";

	private SucursalDTO sucursalObj;
	private MaestroProducto maestroProducto;
	private Stock stock;
	private Sucursal sucursal;
	private Cliente cliente;
	private MedioPago medioPago;
	private Caja caja;
	private Ingresos ingresos;
	private Egresos egresos;
	private Empleado empleado;
	private Carrito carrito;
	private DetalleCarrito detalleCarrito;
	private Factura factura;
	private DetalleFactura detalleFactura;
	private Proveedor proveedor;
	private ProductoDeProveedor productoDeProveedor;
	private PedidosPendientes pedidosPendientes;

	private Pais pais;
	private Provincia provincia;
	private Localidad localidad;

	HistorialCambioCliente historialCambioCliente;

	// Controladores

	private ControladorGestionarSucursales controladorGestionarSucursales;

	private ControladorBusquedaCliente controladorBusquedaCliente;
	private ControladorBusquedaProductos controladorBusquedaProducto;

	private ControladorModificarCotizacion controladorModificarCotizacion;
	private ControladorModificarMProducto controladorModificarMProducto;

	private ControladorEgresosCaja controladorEgresosCaja;

	private ReControladorOperario reControladorOperario;

	private ControladorGenerarOrdenesManufactura controladorGenerarOrdenesManufactura;

	// Controlador cajero
	private ControladorCierreCaja controladorCierreCaja;
	private ControladorIngresosCaja controladorIngresosCaja;
	private ControladorVisualizarCarritos controladorVisualizarCarritos;
	private ControladorRealizarVenta controladorRealizarVenta;

	// Controlador gerente
	private ControladorGestionarClientes controladorGestionarClientes;
	private ControladorAltaCliente controladorAltaCliente;
	private ControladorHistorialDeCambiosDeCliente controladorHistorialDeCambiosDeCliente;

	// Controlador supervisor
	private ControladorGestionarProductos controladorGestionarProductos;
	private ControladorAltaProducto controladorAltaProducto;
	private ControladorAsignarProductoAProveedor controladorAsignarProductoAProveedor;
	private ControladorGestionarProveedores controladorGestionarProveedores;
	private ControladorAltaProveedor controladorAltaProveedor;

	ControladorGenerarPedidoAProveedorManualmente controladorGenerarPedidoAProveedorManualmente;

	// Ver pedidos pendientes
	private ControladorVerPedidosAProveedor controladorVerPedidosAProveedor;

	private ControladorVisualizarComprasVirtuales controladorVisualizarComprasVirtuales;
	private ControladorReporteRankingVentaXSucursal controladorReporteRankingVentaXSucursal;

	private ControladorTareasAutomatizadas controladorTareasAutomatizadas;

	private ControladorLogin controladorLogin;

	private VentanaAdministrador ventanaAdministrador;
	private VentanaCajero ventanaCajero;
//	private VentanaGerente ventanaGerente;

	private VentanaDashboardGerente ventanaDashboardGerente;

	private VentanaOperarioFabrica ventanaOperarioFabrica;
	private VentanaSupervisor ventanaSupervisor;
	private VentanaSupervisorFabrica ventanaSupervisorFabrica;
	private VentanaVendedor ventanaVendedor;
	private ControladorGestionarEmpleados controladorGestionarEmpleado;

//	private ControladorGestionarProveedores controladorGestionarProveedores;

	// gestionar recetas y pasos
	private ControladorVerPasos controladorVerPasos;

	// Configuracion (properties)
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

		this.pedidosPendientes = new PedidosPendientes(new DAOSQLFactory());

		this.pais = new Pais(new DAOSQLFactory());
		this.provincia = new Provincia(new DAOSQLFactory());
		this.localidad = new Localidad(new DAOSQLFactory());

		this.historialCambioCliente = new HistorialCambioCliente(new DAOSQLFactory());

		this.sucursalObj = this.sucursal.select(this.idSucursal);
	}

	public void inicializar() {
		if (tipoEmpleado.equals("Administrativo")) {
			this.ventanaAdministrador = new VentanaAdministrador();
			inicializarControladoresAdministrativos();
			escucharBotonesVentanaAdministrativo();
		}
		if (tipoEmpleado.equals("Cajero")) {
			this.ventanaCajero = new VentanaCajero();
			inicializarControladoresCajero();
			escucharBotonesVentanaCajero();
		}
		if (tipoEmpleado.equals("Vendedor")) {
			this.ventanaVendedor = new VentanaVendedor();
			inicializarControladoresVendedor();
			escucharBotonesVentanaVendedor();
		}
		if (tipoEmpleado.equals("Operario de Fabrica")) {
			this.ventanaOperarioFabrica = new VentanaOperarioFabrica();
			inicializarControladoresOperarioDeFabrica();
			escucharBotonesVentanaOperatoriaDeFabrica();
		}
		if (tipoEmpleado.equals("Supervisor de Fabrica")) {
			this.ventanaSupervisorFabrica = new VentanaSupervisorFabrica();
			inicializarControladoresSupervisorDeFabrica();
			escucharBotonesVentanaSupervisorFabrica();
		}
		if (tipoEmpleado.equals("Supervisor")) {
			this.ventanaSupervisor = new VentanaSupervisor();
			inicializarControladoresSupervisor();
			escucharBotonesVentanaSupervisor();
		}
		if (tipoEmpleado.equals("Gerente")) {
			this.ventanaDashboardGerente = new VentanaDashboardGerente();
			inicializarControladoresGerente();
			escucharBotonesVentanaGerente();
		}

		generarOrdenesFabricacion.actualizarTodosLosTrabajosListosParaLosEnvios();
	}

	public void inicializarControladoresAdministrativos() {
		// CONTROLADOR GESTIONAR PROVEEDORES
		this.controladorAltaProveedor = new ControladorAltaProveedor(proveedor);
		this.controladorAsignarProductoAProveedor = new ControladorAsignarProductoAProveedor(this.maestroProducto,
				this.proveedor, this.productoDeProveedor);
		this.controladorGestionarProveedores = new ControladorGestionarProveedores(this, this.proveedor,
				this.productoDeProveedor);

		////////////////////////////////////////////////////////////////////////////
		// SETGESTIONAR PROVEEDORES

		this.controladorGestionarProveedores.setControladorAltaProducto(this.controladorAltaProducto);
		this.controladorGestionarProveedores
				.setControladorAsignarProductoAProveedor(this.controladorAsignarProductoAProveedor);
		this.controladorGestionarProveedores.setControladorAltaProveedor(this.controladorAltaProveedor);

		this.controladorAsignarProductoAProveedor
				.setControladorConsultarProveedor(this.controladorGestionarProveedores);

		this.controladorAltaProveedor.setControladorGestionarProveedores(controladorGestionarProveedores);

		// Config
		this.controladorTareasAutomatizadas = new ControladorTareasAutomatizadas(this, config);

		// Ver compras virtuales
		this.controladorVisualizarComprasVirtuales = new ControladorVisualizarComprasVirtuales(this);

		// Ver pedidos a prov
		this.controladorVerPedidosAProveedor = new ControladorVerPedidosAProveedor(this, pedidosPendientes, stock,
				maestroProducto);

		// Gestionar Empleado
		this.controladorGestionarEmpleado = new ControladorGestionarEmpleados(this);

		// Gestionar Clientes
		this.controladorGestionarClientes = new ControladorGestionarClientes(this, this.cliente);
		this.controladorAltaCliente = new ControladorAltaCliente(this.cliente, this.pais, this.provincia,
				this.localidad, historialCambioCliente);
		this.controladorHistorialDeCambiosDeCliente = new ControladorHistorialDeCambiosDeCliente(
				historialCambioCliente);
		this.controladorVerPedidosAProveedor = new ControladorVerPedidosAProveedor(this, pedidosPendientes, stock,
				maestroProducto);
		this.controladorAltaCliente = new ControladorAltaCliente(this.cliente, this.pais, this.provincia,
				this.localidad, historialCambioCliente);

		// mod precio unitario
		this.controladorModificarMProducto = new ControladorModificarMProducto(this, this.maestroProducto);

		// GestionarProductos
		this.controladorGestionarProductos = new ControladorGestionarProductos(this, this.maestroProducto, this.stock);
		this.controladorAltaProducto = new ControladorAltaProducto(this.maestroProducto, this.proveedor,
				this.productoDeProveedor);
//		this.controladorGestionarProveedores = new ControladorGestionarProveedores(this, this.proveedor,
//				this.productoDeProveedor);
		this.controladorGenerarPedidoAProveedorManualmente = new ControladorGenerarPedidoAProveedorManualmente(
				proveedor, stock, pedidosPendientes, productoDeProveedor);

		// Set Gestionar Productos
		this.controladorGestionarProveedores.setControladorAltaProducto(this.controladorAltaProducto);
		this.controladorAltaProducto.setControladorConsultarProveedor(this.controladorGestionarProveedores);
		this.controladorAltaProducto.setControladorGestionarProductos(this.controladorGestionarProductos);
		this.controladorGestionarProductos.setControladorAltaProducto(this.controladorAltaProducto);
		this.controladorGestionarProductos
				.setControladorGenerarPedidoAProveedorManualmente(controladorGenerarPedidoAProveedorManualmente);
		this.controladorGenerarPedidoAProveedorManualmente
				.setControladorGestionarProductos(controladorGestionarProductos);

		// Set Gestionar Clientes
		this.controladorAltaCliente.setControladorGestionarClientes(this.controladorGestionarClientes);
		this.controladorGestionarClientes.setControladorAltaCliente(this.controladorAltaCliente);
		this.controladorGestionarClientes
				.setControladorHistorialDeCambiosDeCliente(controladorHistorialDeCambiosDeCliente);
		this.controladorHistorialDeCambiosDeCliente.setControladorGestionarClientes(controladorGestionarClientes);

	}

	public void inicializarControladoresCajero() {

		this.controladorIngresosCaja = new ControladorIngresosCaja(this, caja);

		this.controladorEgresosCaja = new ControladorEgresosCaja(this, egresos, pedidosPendientes);

		// Registrar Venta
		this.controladorCierreCaja = new ControladorCierreCaja(this, caja, ingresos, egresos, empleado);
		this.controladorVisualizarCarritos = new ControladorVisualizarCarritos(this, carrito, detalleCarrito, cliente,
				maestroProducto, stock);
		this.controladorRealizarVenta = new ControladorRealizarVenta(this.medioPago, this.cliente, this.empleado,
				this.carrito, this.detalleCarrito, this.maestroProducto, this.factura, this.detalleFactura,
				this.ingresos);
		this.controladorVisualizarCarritos.setControladorRealizarVenta(this.controladorRealizarVenta);
		this.controladorRealizarVenta.setControladorVisualizarCarritos(this.controladorVisualizarCarritos);

	}

	public void inicializarControladoresVendedor() {

		// armar Venta
		this.controladorIngresosCaja = new ControladorIngresosCaja(this, caja);
		this.controladorBusquedaCliente = new ControladorBusquedaCliente(this, cliente);
		this.controladorBusquedaProducto = new ControladorBusquedaProductos(this.maestroProducto, this.stock,
				this.sucursal, this.carrito, this.detalleCarrito);
		this.controladorBusquedaCliente.setControladorBusquedaProducto(this.controladorBusquedaProducto);
		this.controladorBusquedaProducto.setControladorBusquedaCliente(this.controladorBusquedaCliente);

		// Gestionar Clientes
		this.controladorGestionarClientes = new ControladorGestionarClientes(this, this.cliente);
		this.controladorAltaCliente = new ControladorAltaCliente(this.cliente, this.pais, this.provincia,
				this.localidad, historialCambioCliente);
		this.controladorHistorialDeCambiosDeCliente = new ControladorHistorialDeCambiosDeCliente(
				historialCambioCliente);
		this.controladorVerPedidosAProveedor = new ControladorVerPedidosAProveedor(this, pedidosPendientes, stock,
				maestroProducto);
		this.controladorAltaCliente = new ControladorAltaCliente(this.cliente, this.pais, this.provincia,
				this.localidad, historialCambioCliente);

		// GestionarProductos
		this.controladorGestionarProductos = new ControladorGestionarProductos(this, this.maestroProducto, this.stock);
		this.controladorAltaProducto = new ControladorAltaProducto(this.maestroProducto, this.proveedor,
				this.productoDeProveedor);
		this.controladorGestionarProveedores = new ControladorGestionarProveedores(this, this.proveedor,
				this.productoDeProveedor);
		this.controladorGenerarPedidoAProveedorManualmente = new ControladorGenerarPedidoAProveedorManualmente(
				proveedor, stock, pedidosPendientes, productoDeProveedor);

		this.controladorGestionarProveedores = new ControladorGestionarProveedores(this, proveedor,
				productoDeProveedor);

		// Set Gestionar Productos
		this.controladorGestionarProveedores.setControladorAltaProducto(this.controladorAltaProducto);
		this.controladorAltaProducto.setControladorConsultarProveedor(this.controladorGestionarProveedores);
		this.controladorAltaProducto.setControladorGestionarProductos(this.controladorGestionarProductos);
		this.controladorGestionarProductos.setControladorAltaProducto(this.controladorAltaProducto);
		this.controladorGestionarProductos
				.setControladorGenerarPedidoAProveedorManualmente(controladorGenerarPedidoAProveedorManualmente);
		this.controladorGenerarPedidoAProveedorManualmente
				.setControladorGestionarProductos(controladorGestionarProductos);

		// Set Gestionar Clientes
		this.controladorAltaCliente.setControladorGestionarClientes(this.controladorGestionarClientes);
		this.controladorGestionarClientes.setControladorAltaCliente(this.controladorAltaCliente);
		this.controladorGestionarClientes
				.setControladorHistorialDeCambiosDeCliente(controladorHistorialDeCambiosDeCliente);
		this.controladorHistorialDeCambiosDeCliente.setControladorGestionarClientes(controladorGestionarClientes);

	}

	public void inicializarControladoresOperarioDeFabrica() {
		this.reControladorOperario = new ReControladorOperario(this, this.sucursalObj);
	}

	public void inicializarControladoresSupervisorDeFabrica() {
		this.reControladorOperario = new ReControladorOperario(this, this.sucursalObj);
		// Gestionar recetas y pasos
		this.controladorVerPasos = new ControladorVerPasos();
		this.controladorVerPasos.setControlador(this);

	}

	public void inicializarControladoresSupervisor() {
		this.controladorGestionarSucursales = new ControladorGestionarSucursales(this, sucursal);

		// armar Venta
		this.controladorIngresosCaja = new ControladorIngresosCaja(this, caja);

		this.controladorBusquedaCliente = new ControladorBusquedaCliente(this, cliente);
		this.controladorBusquedaProducto = new ControladorBusquedaProductos(this.maestroProducto, this.stock,
				this.sucursal, this.carrito, this.detalleCarrito);
		this.controladorBusquedaCliente.setControladorBusquedaProducto(this.controladorBusquedaProducto);
		this.controladorBusquedaProducto.setControladorBusquedaCliente(this.controladorBusquedaCliente);

		// Config
		this.controladorTareasAutomatizadas = new ControladorTareasAutomatizadas(this, config);

		// cotizacion
		this.controladorModificarCotizacion = new ControladorModificarCotizacion(this, medioPago);

		// mod precio unitario
		this.controladorModificarMProducto = new ControladorModificarMProducto(this, this.maestroProducto);

		// Generar ordenes de manufac
		this.controladorGenerarOrdenesManufactura = new ControladorGenerarOrdenesManufactura(this);

		// Gestionar Clientes
		this.controladorGestionarClientes = new ControladorGestionarClientes(this, this.cliente);
		this.controladorAltaCliente = new ControladorAltaCliente(this.cliente, this.pais, this.provincia,
				this.localidad, historialCambioCliente);
		this.controladorHistorialDeCambiosDeCliente = new ControladorHistorialDeCambiosDeCliente(
				historialCambioCliente);
		this.controladorVerPedidosAProveedor = new ControladorVerPedidosAProveedor(this, pedidosPendientes, stock,
				maestroProducto);
		this.controladorAltaCliente = new ControladorAltaCliente(this.cliente, this.pais, this.provincia,
				this.localidad, historialCambioCliente);

		this.controladorVisualizarComprasVirtuales = new ControladorVisualizarComprasVirtuales(this);
		this.controladorReporteRankingVentaXSucursal = new ControladorReporteRankingVentaXSucursal(this);

		this.reControladorOperario = new ReControladorOperario(this, this.sucursalObj);

		// Ver pedidos a prov
		this.controladorVerPedidosAProveedor = new ControladorVerPedidosAProveedor(this, pedidosPendientes, stock,
				maestroProducto);
		// Supervisor
		// GestionarProductos
		this.controladorGestionarProductos = new ControladorGestionarProductos(this, this.maestroProducto, this.stock);
		this.controladorAltaProducto = new ControladorAltaProducto(this.maestroProducto, this.proveedor,
				this.productoDeProveedor);

		this.controladorGenerarPedidoAProveedorManualmente = new ControladorGenerarPedidoAProveedorManualmente(
				proveedor, stock, pedidosPendientes, productoDeProveedor);

		////////////////////////////////////////////////////////////////////////////
		// CONTROLADOR GESTIONAR PROVEEDORES
		this.controladorAltaProveedor = new ControladorAltaProveedor(proveedor);
		this.controladorAsignarProductoAProveedor = new ControladorAsignarProductoAProveedor(this.maestroProducto,
				this.proveedor, this.productoDeProveedor);
		this.controladorGestionarProveedores = new ControladorGestionarProveedores(this, this.proveedor,
				this.productoDeProveedor);

		////////////////////////////////////////////////////////////////////////////
		// SETGESTIONAR PROVEEDORES

		this.controladorGestionarProveedores.setControladorAltaProducto(this.controladorAltaProducto);
		this.controladorGestionarProveedores
				.setControladorAsignarProductoAProveedor(this.controladorAsignarProductoAProveedor);
		this.controladorGestionarProveedores.setControladorAltaProveedor(this.controladorAltaProveedor);

		this.controladorAsignarProductoAProveedor
				.setControladorConsultarProveedor(this.controladorGestionarProveedores);

		this.controladorAltaProveedor.setControladorGestionarProveedores(controladorGestionarProveedores);

		////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		this.controladorAltaCliente.setControladorGestionarClientes(this.controladorGestionarClientes);
		this.controladorGestionarClientes.setControladorAltaCliente(this.controladorAltaCliente);
		this.controladorGestionarClientes
				.setControladorHistorialDeCambiosDeCliente(controladorHistorialDeCambiosDeCliente);
		this.controladorHistorialDeCambiosDeCliente.setControladorGestionarClientes(controladorGestionarClientes);

		// Alta cliente

		this.controladorHistorialDeCambiosDeCliente.setControladorGestionarClientes(controladorGestionarClientes);

		// Ver pedidos a prov
		this.controladorVerPedidosAProveedor = new ControladorVerPedidosAProveedor(this, pedidosPendientes, stock,
				maestroProducto);
		// Supervisor
		// GestionarProductos
		this.controladorGestionarProductos = new ControladorGestionarProductos(this, this.maestroProducto, this.stock);
		this.controladorAltaProducto = new ControladorAltaProducto(this.maestroProducto, this.proveedor,
				this.productoDeProveedor);
//		this.controladorGestionarProveedores = new ControladorGestionarProveedores(this, this.proveedor,this.productoDeProveedor);

		this.controladorGenerarPedidoAProveedorManualmente = new ControladorGenerarPedidoAProveedorManualmente(
				proveedor, stock, pedidosPendientes, productoDeProveedor);

		this.controladorGestionarProveedores.setControladorAltaProducto(this.controladorAltaProducto);
		this.controladorAltaProducto.setControladorConsultarProveedor(this.controladorGestionarProveedores);
		this.controladorAltaProducto.setControladorGestionarProductos(this.controladorGestionarProductos);
		this.controladorGestionarProductos.setControladorAltaProducto(this.controladorAltaProducto);
		this.controladorGestionarProductos
				.setControladorGenerarPedidoAProveedorManualmente(controladorGenerarPedidoAProveedorManualmente);
		this.controladorGenerarPedidoAProveedorManualmente
				.setControladorGestionarProductos(controladorGestionarProductos);

		this.reControladorOperario = new ReControladorOperario(this, this.sucursalObj);

		// ver COmpras virutales
		this.controladorVisualizarComprasVirtuales = new ControladorVisualizarComprasVirtuales(this);
		this.controladorReporteRankingVentaXSucursal = new ControladorReporteRankingVentaXSucursal(this);

	}

	public void inicializarControladoresGerente() {
		// CONTROLADOR GESTIONAR PROVEEDORES
		this.controladorAltaProveedor = new ControladorAltaProveedor(proveedor);
		this.controladorAsignarProductoAProveedor = new ControladorAsignarProductoAProveedor(this.maestroProducto,
				this.proveedor, this.productoDeProveedor);
		this.controladorGestionarProveedores = new ControladorGestionarProveedores(this, this.proveedor,
				this.productoDeProveedor);

		////////////////////////////////////////////////////////////////////////////
		// SETGESTIONAR PROVEEDORES

		this.controladorGestionarProveedores.setControladorAltaProducto(this.controladorAltaProducto);
		this.controladorGestionarProveedores
				.setControladorAsignarProductoAProveedor(this.controladorAsignarProductoAProveedor);
		this.controladorGestionarProveedores.setControladorAltaProveedor(this.controladorAltaProveedor);

		this.controladorAsignarProductoAProveedor
				.setControladorConsultarProveedor(this.controladorGestionarProveedores);

		this.controladorAltaProveedor.setControladorGestionarProveedores(controladorGestionarProveedores);

		// Gestjionar SUcursal
		this.controladorGestionarSucursales = new ControladorGestionarSucursales(this, sucursal);

		// GestionarProductos
		this.controladorGestionarProductos = new ControladorGestionarProductos(this, this.maestroProducto, this.stock);
		this.controladorAltaProducto = new ControladorAltaProducto(this.maestroProducto, this.proveedor,
				this.productoDeProveedor);
		this.controladorGenerarPedidoAProveedorManualmente = new ControladorGenerarPedidoAProveedorManualmente(
				proveedor, stock, pedidosPendientes, productoDeProveedor);

		// Set Gestionar Productos
		this.controladorGestionarProveedores.setControladorAltaProducto(this.controladorAltaProducto);
		this.controladorAltaProducto.setControladorConsultarProveedor(this.controladorGestionarProveedores);
		this.controladorAltaProducto.setControladorGestionarProductos(this.controladorGestionarProductos);
		this.controladorGestionarProductos.setControladorAltaProducto(this.controladorAltaProducto);
		this.controladorGestionarProductos
				.setControladorGenerarPedidoAProveedorManualmente(controladorGenerarPedidoAProveedorManualmente);
		this.controladorGenerarPedidoAProveedorManualmente
				.setControladorGestionarProductos(controladorGestionarProductos);

		// mod precio unitario
		this.controladorModificarMProducto = new ControladorModificarMProducto(this, this.maestroProducto);

		// Gestionar Clientes
		this.controladorGestionarClientes = new ControladorGestionarClientes(this, this.cliente);
		this.controladorAltaCliente = new ControladorAltaCliente(this.cliente, this.pais, this.provincia,
				this.localidad, historialCambioCliente);
		this.controladorHistorialDeCambiosDeCliente = new ControladorHistorialDeCambiosDeCliente(
				historialCambioCliente);
		this.controladorVerPedidosAProveedor = new ControladorVerPedidosAProveedor(this, pedidosPendientes, stock,
				maestroProducto);
		this.controladorAltaCliente = new ControladorAltaCliente(this.cliente, this.pais, this.provincia,
				this.localidad, historialCambioCliente);
		// Set Gestionar Clientes
		this.controladorAltaCliente.setControladorGestionarClientes(this.controladorGestionarClientes);
		this.controladorGestionarClientes.setControladorAltaCliente(this.controladorAltaCliente);
		this.controladorGestionarClientes
				.setControladorHistorialDeCambiosDeCliente(controladorHistorialDeCambiosDeCliente);
		this.controladorHistorialDeCambiosDeCliente.setControladorGestionarClientes(controladorGestionarClientes);

		// Gestionar Empleado
		this.controladorGestionarEmpleado = new ControladorGestionarEmpleados(this);
		// Reporte ranking
		this.controladorReporteRankingVentaXSucursal = new ControladorReporteRankingVentaXSucursal(this);
		// Config
		this.controladorTareasAutomatizadas = new ControladorTareasAutomatizadas(this, config);
		// Ver pedidos a prov
		this.controladorVerPedidosAProveedor = new ControladorVerPedidosAProveedor(this, pedidosPendientes, stock,
				maestroProducto);
		// cotizacion
		this.controladorModificarCotizacion = new ControladorModificarCotizacion(this, medioPago);

		// Historial fabricacion
		this.reControladorOperario = new ReControladorOperario(this, this.sucursalObj);

		// Ingreso de Caja
		this.controladorIngresosCaja = new ControladorIngresosCaja(this, caja);
		// Egreso de Caja
		this.controladorEgresosCaja = new ControladorEgresosCaja(this, egresos, pedidosPendientes);

		// Registrar Venta
		// Cierre de Caja
		this.controladorCierreCaja = new ControladorCierreCaja(this, caja, ingresos, egresos, empleado);
		this.controladorVisualizarCarritos = new ControladorVisualizarCarritos(this, carrito, detalleCarrito, cliente,
				maestroProducto, stock);
		this.controladorRealizarVenta = new ControladorRealizarVenta(this.medioPago, this.cliente, this.empleado,
				this.carrito, this.detalleCarrito, this.maestroProducto, this.factura, this.detalleFactura,
				this.ingresos);
		this.controladorVisualizarCarritos.setControladorRealizarVenta(this.controladorRealizarVenta);
		this.controladorRealizarVenta.setControladorVisualizarCarritos(this.controladorVisualizarCarritos);

		// Gestionar recetas y pasos
		this.controladorVerPasos = new ControladorVerPasos();
		this.controladorVerPasos.setControlador(this);

	}

	public void escucharBotonesVentanaAdministrativo() {
		this.ventanaAdministrador.getBtnModificacionMasivaDePrecios()
				.addActionListener(a -> pasarAModificarPrecioUnitario(a));
		this.ventanaAdministrador.getBtnGestionarClientes().addActionListener(a -> pasarAGestionarClientes(a));
		this.ventanaAdministrador.getBtnVerProductos().addActionListener(a -> pasarADarDeAltaProducto(a));
		this.ventanaAdministrador.getBtnGestionarEmpleados().addActionListener(a -> pasarAGestionarEmpleado(a));
		this.ventanaAdministrador.getBtnPedidosAProveedores().addActionListener(a -> pasarAVerPedidosAProveedor(a));
		this.ventanaAdministrador.getBtnVerComprasVirtuales().addActionListener(a -> pasarAVerComprasVirtuales(a));
		this.ventanaAdministrador.getBtnTareasAutomaticas().addActionListener(a -> pasarAConfig(a));
		this.ventanaAdministrador.getBtnGestionarProveedores().addActionListener(a -> pasarAConsultarProveedores(a));
		this.ventanaAdministrador.getBtnCerrarSesion().addActionListener(a -> cerrarSesion(a));
	}

	public void escucharBotonesVentanaCajero() {
		this.ventanaCajero.getBtnCobrarVenta().addActionListener(a -> pasarACobrarVenta(a));
		this.ventanaCajero.getBtnIngresoDeCaja().addActionListener(a -> pasarAIngresoDeCaja(a));
		this.ventanaCajero.getBtnEgresoDeCaja().addActionListener(a -> pasarAEgresosCaja(a));
		this.ventanaCajero.getBtnCierreDeCaja().addActionListener(a -> pasarACierreDeCaja(a));

		this.ventanaCajero.getBtnCerrarSesion().addActionListener(a -> cerrarSesion(a));
	}

	public void escucharBotonesVentanaVendedor() {
		this.ventanaVendedor.getBtnArmarVenta().addActionListener(a -> pasarAArmarVenta(a));
		this.ventanaVendedor.getBtnVerAgregarClientes().addActionListener(a -> pasarAGestionarClientes(a));
		this.ventanaVendedor.getBtnVerProductos().addActionListener(a -> pasarADarDeAltaProducto(a));
		this.ventanaVendedor.getBtnCerrarSesion().addActionListener(a -> cerrarSesion(a));
	}

	public void escucharBotonesVentanaOperatoriaDeFabrica() {
		this.ventanaOperarioFabrica.getBtnOperatoriaDeFabrica()
				.addActionListener(a -> iniciarSistemaOperatoriaFabrica(a));
		this.ventanaOperarioFabrica.getBtnCerrarSesion().addActionListener(a -> cerrarSesion(a));
	}

	public void escucharBotonesVentanaSupervisor() {
		this.ventanaSupervisor.getBtnArmarVenta().addActionListener(a -> pasarAArmarVenta(a));
		this.ventanaSupervisor.getBtnConfig().addActionListener(a -> pasarAConfig(a));
		this.ventanaSupervisor.getBtnCotizaciones().addActionListener(a -> pasarACotizaciones(a));
		this.ventanaSupervisor.getBtnModPrecioUnitario().addActionListener(a -> pasarAModificarPrecioUnitario(a));
		this.ventanaSupervisor.getBtnGenerarOrdenDe().addActionListener(a -> pasarAGenerarOrdenManufac(a));
		this.ventanaSupervisor.getBtnGestionarClientes().addActionListener(a -> pasarAGestionarClientes(a));
		this.ventanaSupervisor.getBtnGestionarProductos().addActionListener(a -> pasarADarDeAltaProducto(a));
		this.ventanaSupervisor.getBtnVerProveedores().addActionListener(a -> pasarAConsultarProveedores(a));
		this.ventanaSupervisor.getBtnVerPedidosA().addActionListener(a -> pasarAVerPedidosAProveedor(a));
		this.ventanaSupervisor.getBtnVerComprasVirtuales().addActionListener(a -> pasarAVerComprasVirtuales(a));
		this.ventanaSupervisor.getBtnVerReporteRanking().addActionListener(a -> pasarAVerRanking(a));
		this.ventanaSupervisor.getBtnCerrarSesion().addActionListener(a -> cerrarSesion(a));

		this.ventanaSupervisor.getBtnGestionarSucursales().addActionListener(a -> pasarAGestionarSucursales(a));
	}

	public void escucharBotonesVentanaGerente() {
		this.ventanaDashboardGerente.getBtnGestionarProveedores().addActionListener(a -> pasarAConsultarProveedores(a));
		this.ventanaDashboardGerente.getBtnHistorialFabrica()
				.addActionListener(a -> iniciarSistemaOperatoriaFabrica(a));
		this.ventanaDashboardGerente.getBtnGestionarProductos().addActionListener(a -> pasarADarDeAltaProducto(a));
		this.ventanaDashboardGerente.getBtnModPrecioMasivo().addActionListener(a -> pasarAModificarPrecioUnitario(a));
		this.ventanaDashboardGerente.getBtnGestionarClientes().addActionListener(a -> pasarAGestionarClientes(a));
		this.ventanaDashboardGerente.getBtnGestionarEmpleados().addActionListener(a -> pasarAGestionarEmpleado(a));
		this.ventanaDashboardGerente.getBtnVerReporteRanking().addActionListener(a -> pasarAVerRanking(a));
		this.ventanaDashboardGerente.getBtnTareasAutomaticas().addActionListener(a -> pasarAConfig(a));
		this.ventanaDashboardGerente.getBtnVerPedidosA().addActionListener(a -> pasarAVerPedidosAProveedor(a));
		this.ventanaDashboardGerente.getBtnCotizaciones().addActionListener(a -> pasarACotizaciones(a));

		this.ventanaDashboardGerente.getBtnCobrarVenta().addActionListener(a -> pasarACobrarVenta(a));
		this.ventanaDashboardGerente.getBtnIngresoDeCaja().addActionListener(a -> pasarAIngresoDeCaja(a));
		this.ventanaDashboardGerente.getBtnEgresoDeCaja().addActionListener(a -> pasarAEgresosCaja(a));
		this.ventanaDashboardGerente.getBtnCierreDeCaja().addActionListener(a -> pasarACierreDeCaja(a));

		this.ventanaDashboardGerente.getBtnGestionarRecetasYPasos()
				.addActionListener(a -> pasarAGestionarRecetasYPasos(a));

		this.ventanaDashboardGerente.getBtnGestionarSucursales().addActionListener(a -> pasarAGestionarSucursales(a));
		this.ventanaDashboardGerente.getBtnCerrarSesion().addActionListener(a -> cerrarSesion(a));

	}

	public void escucharBotonesVentanaSupervisorFabrica() {
		this.ventanaSupervisorFabrica.getBtnOperatoriaDeFabrica()
				.addActionListener(a -> iniciarSistemaOperatoriaFabrica(a));
		this.ventanaSupervisorFabrica.getBtnGestionarRecetasYPasos()
				.addActionListener(a -> pasarAGestionarRecetasYPasos(a));
		this.ventanaSupervisorFabrica.getBtnCerrarSesion().addActionListener(a -> cerrarSesion(a));
	}

	public void obtenerDatosPropertiesSucursal() {
		try {
			sucursalProperties sucursalProp = sucursalProperties.getInstance();
			idSucursal = Integer.parseInt(sucursalProp.getValue("IdSucursal"));

			empleadoProperties empleadoProp = empleadoProperties.getInstance();
			tipoEmpleado = empleadoProp.getValue("TipoEmpleado");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void mostrarVentanaPorTipoEmpleado() {
		if (tipoEmpleado.equals("Administrativo")) {
			ventanaAdministrador.mostrarVentana();
		}
		if (tipoEmpleado.equals("Cajero")) {
			ventanaCajero.mostrarVentana();
		}
		if (tipoEmpleado.equals("Vendedor")) {
			ventanaVendedor.mostrarVentana();
		}
		if (tipoEmpleado.equals("Operario de Fabrica")) {
			ventanaOperarioFabrica.mostrarVentana();
		}
		if (tipoEmpleado.equals("Supervisor de Fabrica")) {
			ventanaSupervisorFabrica.mostrarVentana();
		}
		if (tipoEmpleado.equals("Supervisor")) {
			ventanaSupervisor.mostrarVentana();
		}

		if (tipoEmpleado.equals("Gerente")) {
			ventanaDashboardGerente.mostrarVentana();
		}
	}

	public void mostrarVentanaMenu() {
		mostrarVentanaPorTipoEmpleado();
	}

	public void mostrarVentanaMenuDeSistemas() {
		mostrarVentanaPorTipoEmpleado();
	}

	public void cerrarSesion(ActionEvent a) {
		cerrarTodasLasVentanas();
		controladorLogin.inicializar();
		controladorLogin.mostrarVentana();
	}

	public void cerrarTodasLasVentanas() {
		if (tipoEmpleado.equals("Administrativo")) {
			ventanaAdministrador.cerrarVentana();
		}
		if (tipoEmpleado.equals("Cajero")) {
			this.ventanaCajero.cerrarVentana();
		}
		if (tipoEmpleado.equals("Vendedor")) {
			this.ventanaVendedor.cerrarVentana();
		}
		if (tipoEmpleado.equals("Operario de Fabrica")) {
			this.ventanaOperarioFabrica.cerrarVentana();
		}
		if (tipoEmpleado.equals("Supervisor de Fabrica")) {
			ventanaSupervisorFabrica.cerrarVentana();
		}
		if (tipoEmpleado.equals("Supervisor")) {
			this.ventanaSupervisor.cerrarVentana();
		}
		if (tipoEmpleado.equals("Gerente")) {
			ventanaDashboardGerente.cerrarVentana();
		}

	}

	public void pasarAGestionarRecetasYPasos(ActionEvent a) {
		cerrarTodasLasVentanas();
		this.controladorVerPasos.inicializar();
	}

	public void pasarAConfig(ActionEvent a) {
		if (this.controladorTareasAutomatizadas.ventanaYaFueInicializada()) {
			return;
		}
		cerrarTodasLasVentanas();
		this.controladorTareasAutomatizadas.inicializar();
		this.controladorTareasAutomatizadas.mostrarVentana();
	}

	public void iniciarSistemaOperatoriaFabrica(ActionEvent a) {
		cerrarTodasLasVentanas();
		this.reControladorOperario.inicializar();
		this.reControladorOperario.mostrarVentana();

	}

	public void iniciarVerHistorialFabrica(ActionEvent a) {
		cerrarTodasLasVentanas();
		ControladorHistorialPasos con = new ControladorHistorialPasos(this, null);
		con.inicializar();
	}

	public void pasarAGestionarEmpleado(ActionEvent a) {
		cerrarTodasLasVentanas();
		this.controladorGestionarEmpleado.inicializar();
		this.controladorGestionarEmpleado.mostrarVentana();
	}

	public void pasarAArmarVenta(ActionEvent a) {
		if (!this.controladorIngresosCaja.estaCajaAbierta()) {
			JOptionPane.showMessageDialog(null, "La caja no esta abierta");
			return;
		}
		cerrarTodasLasVentanas();
		controladorBusquedaCliente.inicializar();
		this.controladorBusquedaCliente.mostrarVentana();
	}

	public void pasarACobrarVenta(ActionEvent a) {
		if (!this.controladorIngresosCaja.estaCajaAbierta()) {
			JOptionPane.showMessageDialog(null, "La caja no esta abierta");
			return;
		}
		cerrarTodasLasVentanas();
		this.controladorVisualizarCarritos.inicializar();
		this.controladorVisualizarCarritos.mostrarVentana();
	}

	// Gestionar Sucursales
	public void pasarAGestionarSucursales(ActionEvent a) {
		cerrarTodasLasVentanas();
		this.controladorGestionarSucursales.inicializar();
		this.controladorGestionarSucursales.mostrarVentana();
	}

	// Cotizacion
	public void pasarACotizaciones(ActionEvent a) {
		cerrarTodasLasVentanas();
		this.controladorModificarCotizacion.inicializar();
		this.controladorModificarCotizacion.mostrarVentana();
	}

	// Mod precio unitario
	public void pasarAModificarPrecioUnitario(ActionEvent a) {
		cerrarTodasLasVentanas();
		this.controladorModificarMProducto.inicializar();
		this.controladorModificarMProducto.mostrarVentana();
	}

	// Ingreso de caja
	public void pasarAIngresoDeCaja(ActionEvent a) {
		if (this.controladorIngresosCaja.cajaYaFueCerrada()) {
			JOptionPane.showMessageDialog(null, "La caja ya fue cerrada");
			return;
		}
		cerrarTodasLasVentanas();
		this.controladorIngresosCaja.inicializar();
		this.controladorIngresosCaja.mostrarVentana();
	}

	// Egreso
	public void pasarAEgresosCaja(ActionEvent a) {
		if (!this.controladorIngresosCaja.estaCajaAbierta()) {
			JOptionPane.showMessageDialog(null, "La caja no esta abierta");
			return;
		}
		cerrarTodasLasVentanas();
		this.controladorEgresosCaja.inicializar();
		this.controladorEgresosCaja.mostrarVentana();
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
		cerrarTodasLasVentanas();
		this.controladorCierreCaja.inicializar();
		this.controladorCierreCaja.mostrarVentana();
	}

	// Generar ord de manuf
	public void pasarAGenerarOrdenManufac(ActionEvent a) {
		cerrarTodasLasVentanas();
		this.controladorGenerarOrdenesManufactura.inicializar();
		this.controladorGenerarOrdenesManufactura.mostrarVentana();
	}

	public void pasarAGestionarClientes(ActionEvent a) {
		cerrarTodasLasVentanas();
		this.controladorGestionarClientes.inicializar();
		this.controladorGestionarClientes.mostrarVentana();

	}

	// Supervisor
	public void pasarADarDeAltaProducto(ActionEvent a) {
		cerrarTodasLasVentanas();
		this.controladorGestionarProductos.inicializar();
		this.controladorGestionarProductos.mostrarVentana();
	}

	public void pasarAConsultarProveedores(ActionEvent a) {
		cerrarTodasLasVentanas();
		this.controladorGestionarProveedores.inicializar();
		this.controladorGestionarProveedores.mostrarVentana();
	}

	// Ver pedidos a proveedor
	public void pasarAVerPedidosAProveedor(ActionEvent a) {
		cerrarTodasLasVentanas();
		this.controladorVerPedidosAProveedor.inicializar();
		this.controladorVerPedidosAProveedor.mostrarVentana();
	}

	public void pasarAVerComprasVirtuales(ActionEvent a) {
		cerrarTodasLasVentanas();
		this.controladorVisualizarComprasVirtuales.inicializar();
	}

	private void pasarAVerRanking(ActionEvent a) {
		cerrarTodasLasVentanas();
		this.controladorReporteRankingVentaXSucursal.inicializar();
	}

	public void setControladorLogin(ControladorLogin controladorLogin) {
		this.controladorLogin = controladorLogin;
	}

}
