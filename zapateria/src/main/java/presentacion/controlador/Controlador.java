package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import modelo.Cliente;
import modelo.MaestroProducto;
import modelo.Stock;
import modelo.Sucursal;
import modelo.Zapateria;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.controlador.Cajero.ControladorCierreCaja;
import presentacion.controlador.Cajero.ControladorIngresosCaja;
import presentacion.controlador.Cajero.ControladorRealizarVenta;
import presentacion.controlador.Cajero.ControladorVisualizarCarritos;
import presentacion.vista.VentanaBusquedaCliente;
import presentacion.vista.VentanaBusquedaProductos;
import presentacion.vista.VentanaHistorialCambioMProducto;
import presentacion.vista.VentanaHistorialCambioMoneda;
import presentacion.vista.VentanaMenu;
import presentacion.vista.VentanaMenuSistemaDeVentas;
import presentacion.vista.VentanaModificarCotizacion;
import presentacion.vista.VentanaModificarMProducto;

public class Controlador implements ActionListener {
	
	MaestroProducto maestroProducto;
	Stock stock;
	Sucursal sucursal;
	Cliente cliente;
	
	//Controladores
	ControladorBusquedaCliente controladorBusquedaCliente;
	ControladorBusquedaProductos controladorBusquedaProducto;
	
	ControladorHistorialCambioCotizacion controladorHistorialCambioCotizacion;
	ControladorHistorialCambioMProducto controladorHistorialCambioMProducto;
	
	ControladorModificarCotizacion controladorModificarCotizacion;
	ControladorModificarMProducto controladorModificarMProducto;
	
	//Controlador cajero
	ControladorCierreCaja controladorCierreCaja;
	ControladorIngresosCaja controladorIngresosCaja;
//	ControladorRealizarVenta controladorRealizarVenta;
	ControladorVisualizarCarritos controladorVisualizarCarritos;
	
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
	
	public Controlador() {
		this.cliente = new Cliente(new DAOSQLFactory());
		this.maestroProducto = new MaestroProducto(new DAOSQLFactory());
		this.stock = new Stock(new DAOSQLFactory());
		this.sucursal = new Sucursal(new DAOSQLFactory());
	}
	
	
	public void inicializar() {		
		this.ventanaMenu = new VentanaMenu();
		this.ventanaMenuSistemaDeVentas = new VentanaMenuSistemaDeVentas();
		
//		this.ventanaBusquedaCliente= new VentanaBusquedaCliente();
//		this.vistaBusquedaProducto = new VentanaBusquedaProductos();
		
		//armar Venta
		this.controladorBusquedaCliente = new ControladorBusquedaCliente(this,cliente);
		this.controladorBusquedaProducto = new ControladorBusquedaProductos(this.maestroProducto, this.stock, this.sucursal);
		this.controladorBusquedaCliente.setControladorBusquedaProducto(this.controladorBusquedaProducto);
		this.controladorBusquedaProducto.setControladorBusquedaCliente(this.controladorBusquedaCliente);
		
		this.controladorVisualizarCarritos = new ControladorVisualizarCarritos(this);
		
		//Menu principal
		this.ventanaMenu.getBtnOperatoriaDeFabrica().addActionListener(a -> iniciarSistemaOperatoriaFabrica(a));
		this.ventanaMenu.getBtnSistemaDeVentas().addActionListener(a -> iniciarSistemaDeVentas(a));
		
		
		//VentanaMenu de sistemas
		//armar Venta
		this.ventanaMenuSistemaDeVentas.getBtnArmarVenta().addActionListener(a -> pasarAArmarVenta(a));
		this.ventanaMenuSistemaDeVentas.getBtnCobrarVenta().addActionListener(a -> pasarACobrarVenta(a));
		this.ventanaMenuSistemaDeVentas.getBtnRegresar().addActionListener(a -> regresarMenuPrincipal(a));
		
		
		
//		this.ventanaMenu.show();
		
		//por ahora cuando se inicia se inicia la ventana de cliente
//		controladorBusquedaCliente.inicializar();		
//		this.controladorBusquedaCliente.mostrarVentana();
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
		JOptionPane.showMessageDialog(null, "Aun no se implemento xd");
	}
	
	
	
	
	
	public void iniciarSistemaDeVentas(ActionEvent a) {
		this.ventanaMenu.cerrar();
		this.ventanaMenuSistemaDeVentas.show();
	}
	
	
	public void pasarAArmarVenta(ActionEvent a) {
		this.ventanaMenuSistemaDeVentas.cerrar();
		controladorBusquedaCliente.inicializar();		
		this.controladorBusquedaCliente.mostrarVentana();
	}
	
	public void pasarACobrarVenta(ActionEvent a) {
		this.ventanaMenuSistemaDeVentas.cerrar();
		this.controladorVisualizarCarritos.inicializar();
		this.controladorVisualizarCarritos.mostrarVentana();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
	}
	
	
	public static void main(String[] args) {
		
//		Cliente cliente = new Cliente(new DAOSQLFactory());
//		MaestroProducto maestroProducto = new MaestroProducto(new DAOSQLFactory());
//		Stock stock = new Stock(new DAOSQLFactory());
//		Sucursal sucursal = new Sucursal(new DAOSQLFactory());
//		
//		Controlador controlador = new Controlador(maestroProducto,stock, sucursal, cliente);
		Controlador controlador = new Controlador();
		controlador.inicializar();
		controlador.mostrarVentanaMenu();
	}
	
}
