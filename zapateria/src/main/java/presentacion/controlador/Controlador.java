package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.List;

import javax.swing.JOptionPane;

import dto.CarritoDTO;
import dto.ClienteDTO;
import dto.DetalleCarritoDTO;
import dto.SucursalDTO;
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
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.controlador.Cajero.ControladorCierreCaja;
import presentacion.controlador.Cajero.ControladorEgresosCaja;
import presentacion.controlador.Cajero.ControladorIngresosCaja;
import presentacion.controlador.Cajero.ControladorRealizarVenta;
import presentacion.controlador.Cajero.ControladorVisualizarCarritos;
import presentacion.controlador.ModificarProducto.ControladorHistorialCambioMProducto;
import presentacion.controlador.ModificarProducto.ControladorModificarMProducto;
import presentacion.controlador.fabrica.ReControladorOperario;
import presentacion.controlador.generarOrdenesManufactura.ControladorGenerarOrdenesManufactura;
import presentacion.controlador.gerente.ControladorAltaCliente;
import presentacion.controlador.supervisor.ControladorAltaProducto;
import presentacion.controlador.supervisor.ControladorAsignarProductoAProveedor;
import presentacion.controlador.supervisor.ControladorConsultarProveedor;
import presentacion.controlador.supervisor.ControladorVerPedidosAProveedor;
import presentacion.vista.VentanaBusquedaCliente;
import presentacion.vista.VentanaBusquedaProductos;
import presentacion.vista.VentanaHistorialCambioMoneda;
import presentacion.vista.VentanaMenu;
import presentacion.vista.VentanaMenuSistemaDeVentas;
import presentacion.vista.VentanaModificarCotizacion;
import presentacion.vista.ModificarProducto.VentanaHistorialCambioMProducto;
import presentacion.vista.ModificarProducto.VentanaModificarMProducto;

public class Controlador implements ActionListener {
	
	private final int idSucursal=1;
	
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
	//Controladores
	ControladorBusquedaCliente controladorBusquedaCliente;
	ControladorBusquedaProductos controladorBusquedaProducto;
	
	ControladorHistorialCambioCotizacion controladorHistorialCambioCotizacion;
	ControladorHistorialCambioMProducto controladorHistorialCambioMProducto;
	
	ControladorModificarCotizacion controladorModificarCotizacion;
	ControladorModificarMProducto controladorModificarMProducto;
	
	
	ControladorEgresosCaja controladorEgresosCaja; 
	
	ReControladorOperario reControladorOperario;
		
	ControladorGenerarOrdenesManufactura controladorGenerarOrdenesManufactura;
	
	
	//Controlador cajero
	ControladorCierreCaja controladorCierreCaja;
	ControladorIngresosCaja controladorIngresosCaja;
	ControladorVisualizarCarritos controladorVisualizarCarritos;
	ControladorRealizarVenta controladorRealizarVenta;
	
	//Controlador gerente
	ControladorAltaCliente controladorAltaCliente;
	
	//Controlador supervisor
	ControladorAltaProducto controladorAltaProducto;
	ControladorAsignarProductoAProveedor controladorAsignarProductoAProveedor;
	ControladorConsultarProveedor controladorConsultarProveedor;
	
	
	//Ver pedidos pendientes
	ControladorVerPedidosAProveedor controladorVerPedidosAProveedor;
	
	//Ventanas
	/*
	VentanaBusquedaCliente ventanaBusquedaCliente;
	VentanaBusquedaProductos vistaBusquedaProducto;
	
	VentanaHistorialCambioMoneda ventanaHistorialCambioMoneda;
	VentanaHistorialCambioMProducto ventanaHistorialCambioMProducto;
	
	VentanaModificarCotizacion ventanaModificarCotizacion;
	VentanaModificarMProducto ventanaModificarMProducto;
	*/
	
	VentanaMenu ventanaMenu;
	VentanaMenuSistemaDeVentas ventanaMenuSistemaDeVentas;
	
	
	
//	public Controlador(MaestroProducto maestroProducto, Stock stock, Sucursal sucursal, Cliente cliente) {
//		this.maestroProducto = maestroProducto;
//		this.stock = stock;
//		this.sucursal = sucursal;
//		this.cliente = cliente;
//	}
	
	//Coso para el properties
	private ConfiguracionBD config = ConfiguracionBD.getInstance();
	
	
	public Controlador() {
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
		this.ventanaMenu = new VentanaMenu();
		this.ventanaMenuSistemaDeVentas = new VentanaMenuSistemaDeVentas();
		
//		this.ventanaBusquedaCliente= new VentanaBusquedaCliente();
//		this.vistaBusquedaProducto = new VentanaBusquedaProductos();

		this.reControladorOperario = new ReControladorOperario(this,this.sucursalObj);
		
		//armar Venta
		this.controladorBusquedaCliente = new ControladorBusquedaCliente(this,cliente);
		this.controladorBusquedaProducto = new ControladorBusquedaProductos(this.maestroProducto, this.stock, this.sucursal,this.carrito,this.detalleCarrito);
		this.controladorBusquedaCliente.setControladorBusquedaProducto(this.controladorBusquedaProducto);
		this.controladorBusquedaProducto.setControladorBusquedaCliente(this.controladorBusquedaCliente);
		
		//Registrar Venta
		this.controladorVisualizarCarritos = new ControladorVisualizarCarritos(this,carrito, detalleCarrito, cliente,maestroProducto,stock);
		this.controladorRealizarVenta = new ControladorRealizarVenta(this.medioPago,this.cliente,this.empleado,this.carrito,this.detalleCarrito, this.maestroProducto, this.factura, this.detalleFactura, this.ingresos); 
		this.controladorVisualizarCarritos.setControladorRealizarVenta(this.controladorRealizarVenta);
		this.controladorRealizarVenta.setControladorVisualizarCarritos(this.controladorVisualizarCarritos);
		
		
		//Supervisor
		this.controladorAltaProducto = new ControladorAltaProducto(this,this.maestroProducto, this.proveedor, this.productoDeProveedor);
		this.controladorAsignarProductoAProveedor = new ControladorAsignarProductoAProveedor(this.maestroProducto, this.proveedor, this.productoDeProveedor);
		this.controladorConsultarProveedor = new ControladorConsultarProveedor(this,this.proveedor, this.productoDeProveedor);
		
		this.controladorConsultarProveedor.setControladorAsignarProductoAProveedor(this.controladorAsignarProductoAProveedor);
		this.controladorConsultarProveedor.setControladorAltaProducto(this.controladorAltaProducto);
		
		this.controladorAltaProducto.setControladorConsultarProveedor(this.controladorConsultarProveedor);
		this.controladorAsignarProductoAProveedor.setControladorConsultarProveedor(this.controladorConsultarProveedor);
		
		//cotizacion
		this.controladorModificarCotizacion = new ControladorModificarCotizacion(this,medioPago); 
		
		//mod precio unitario
		this.controladorModificarMProducto = new ControladorModificarMProducto(this,this.maestroProducto); 
		
		//Ingreso Caja
		this.controladorIngresosCaja = new ControladorIngresosCaja(this,caja); 
		
		//Egreso Caja
		this.controladorEgresosCaja = new ControladorEgresosCaja(this,egresos,pedidosPendientes);
		
		//Cierre Caja
		this.controladorCierreCaja = new ControladorCierreCaja(this,caja,ingresos,egresos,empleado); 
		
		//Generar ordenes de manufac
		this.controladorGenerarOrdenesManufactura = new ControladorGenerarOrdenesManufactura(this);
		
		
		//Alta cliente
		this.controladorAltaCliente = new ControladorAltaCliente(this,this.cliente); 
		
		//Ver pedidos a prov
		this.controladorVerPedidosAProveedor = new ControladorVerPedidosAProveedor(this,pedidosPendientes,stock);
		
		//Menu principal
		this.ventanaMenu.getBtnOperatoriaDeFabrica().addActionListener(a -> iniciarSistemaOperatoriaFabrica(a));
		this.ventanaMenu.getBtnSistemaDeVentas().addActionListener(a -> iniciarSistemaDeVentas(a));
		
		
		//VentanaMenu de sistemas
		//armar Venta
		this.ventanaMenuSistemaDeVentas.getBtnArmarVenta().addActionListener(a -> pasarAArmarVenta(a));
		this.ventanaMenuSistemaDeVentas.getBtnCobrarVenta().addActionListener(a -> pasarACobrarVenta(a));
		this.ventanaMenuSistemaDeVentas.getBtnRegresar().addActionListener(a -> regresarMenuPrincipal(a));
		
		//cotizacion
		this.ventanaMenuSistemaDeVentas.getBtnCotizaciones().addActionListener(a -> pasarACotizaciones(a));
		
		//mod precioUnitario
		this.ventanaMenuSistemaDeVentas.getBtnModPrecioUnitario().addActionListener(a -> pasarAModificarPrecioUnitario(a));
		
		
		//Ingreso caja
		this.ventanaMenuSistemaDeVentas.getBtnIngresoCaja().addActionListener(a -> pasarAIngresoDeCaja(a));
		
		//Egreso
		this.ventanaMenuSistemaDeVentas.getBtnEgresoCaja().addActionListener(a -> pasarAEgresosCaja(a));
		
		//Cierre de caja
		this.ventanaMenuSistemaDeVentas.getBtnCierreCaja().addActionListener(a -> pasarACierreDeCaja(a));

		//Generar ordenes de manufac
		this.ventanaMenuSistemaDeVentas.getBtnGenerarOrdenDe().addActionListener(a -> pasarAGenerarOrdenManufac(a));
		
		
		//Alta cliente
		this.ventanaMenuSistemaDeVentas.getBtnRegistrarUnCliente().addActionListener(a -> pasarARegistrarUnCliente(a));
		
		
		//Alta producto
		this.ventanaMenuSistemaDeVentas.getBtnIngresarProductoNuevo().addActionListener(a -> pasarADarDeAltaProducto(a));
		this.ventanaMenuSistemaDeVentas.getBtnVerProveedores().addActionListener(a -> pasarAConsultarProveedores(a));
		//Consultar proveedores y asignarle un producto
		
		//Ver pedidos a prov
		this.ventanaMenuSistemaDeVentas.getBtnVerPedidosA().addActionListener(a -> pasarAVerPedidosAProveedor(a));
		
//		try {
//			EnviarCorreosAProveedoresAutomatico.verificarEnvioDeMailsAutomatico();
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
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
		if(!this.controladorIngresosCaja.estaCajaAbierta()) {
			JOptionPane.showMessageDialog(null, "La caja no esta abierta");
			return;
		}
		this.ventanaMenuSistemaDeVentas.cerrar();
		controladorBusquedaCliente.inicializar();		
		this.controladorBusquedaCliente.mostrarVentana();
	}
	
	public void pasarACobrarVenta(ActionEvent a) {
		if(!this.controladorIngresosCaja.estaCajaAbierta()) {
			JOptionPane.showMessageDialog(null, "La caja no esta abierta");
			return;
		}
		
		this.ventanaMenuSistemaDeVentas.cerrar();
		this.controladorVisualizarCarritos.inicializar();
		this.controladorVisualizarCarritos.mostrarVentana();
	}

	
	//Cotizacion
	public void pasarACotizaciones(ActionEvent a) {
		this.ventanaMenuSistemaDeVentas.cerrar();
		this.controladorModificarCotizacion.inicializar();
		this.controladorModificarCotizacion.mostrarVentana();
	}
	
	//Mod precio unitario
	public void pasarAModificarPrecioUnitario(ActionEvent a) {
		this.ventanaMenuSistemaDeVentas.cerrar();
		this.controladorModificarMProducto.inicializar();
		this.controladorModificarMProducto.mostrarVentana();
	}
	
	//Ingreso de caja
	public void pasarAIngresoDeCaja(ActionEvent a) {
		if(this.controladorIngresosCaja.cajaYaFueCerrada()) {
			JOptionPane.showMessageDialog(null, "La caja ya fue cerrada");
			return;
		}
		
		this.ventanaMenuSistemaDeVentas.cerrar();
		this.controladorIngresosCaja.inicializar();
		this.controladorIngresosCaja.mostrarVentana();
	}
	
	
	//Egreso 
	public void pasarAEgresosCaja(ActionEvent a ) {
		if(!this.controladorIngresosCaja.estaCajaAbierta()) {
			JOptionPane.showMessageDialog(null, "La caja no esta abierta");
			return;
		}
		
		this.ventanaMenuSistemaDeVentas.cerrar();
		this.controladorEgresosCaja.inicializar();
		this.controladorEgresosCaja.mostrarVentana();
	}
	
	
	//Cierre de caja
	public void pasarACierreDeCaja(ActionEvent a ) {
		if(!this.controladorIngresosCaja.estaCajaAbierta()) {
			JOptionPane.showMessageDialog(null, "La caja no esta abierta");
			return;
		}
		
		if(this.controladorIngresosCaja.cajaYaFueCerrada()) {
			JOptionPane.showMessageDialog(null, "La caja ya fue cerrada");
			return;
		}
		
		this.ventanaMenuSistemaDeVentas.cerrar();
		this.controladorCierreCaja.inicializar();
		this.controladorCierreCaja.mostrarVentana();
	}
	
	//Generar ord de manuf
	public void pasarAGenerarOrdenManufac(ActionEvent a) {
		this.ventanaMenuSistemaDeVentas.cerrar();
		this.controladorGenerarOrdenesManufactura.inicializar();
		this.controladorGenerarOrdenesManufactura.mostrarVentana();
	}
	
	
	public void pasarARegistrarUnCliente(ActionEvent a ) {
		this.ventanaMenuSistemaDeVentas.cerrar();
		this.controladorAltaCliente.inicializar();
		this.controladorAltaCliente.mostrarVentana();
	}
	
	//Supervisor
	public void pasarADarDeAltaProducto(ActionEvent a) {
		this.ventanaMenuSistemaDeVentas.cerrar();
		this.controladorAltaProducto.inicializar();
		this.controladorAltaProducto.mostrarVentana();
	}
	
	public void pasarAConsultarProveedores(ActionEvent a) {
		this.ventanaMenuSistemaDeVentas.cerrar();
		this.controladorConsultarProveedor.inicializar();
		this.controladorConsultarProveedor.mostrarVentana();
	}

	//Ver pedidos a proveedor
	public void pasarAVerPedidosAProveedor(ActionEvent a) {
		this.ventanaMenuSistemaDeVentas.cerrar();
		this.controladorVerPedidosAProveedor.inicializar();
		this.controladorVerPedidosAProveedor.mostrarVentana();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
