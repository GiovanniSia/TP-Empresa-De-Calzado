package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.Cliente;
import modelo.MaestroProducto;
import modelo.Stock;
import modelo.Sucursal;
import modelo.Zapateria;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.vista.VentanaBusquedaCliente;
import presentacion.vista.vistaBusquedaProductos;

public class Controlador implements ActionListener {
	
	MaestroProducto maestroProducto;
	Stock stock;
	Sucursal sucursal;
	Cliente cliente;
	
	ControladorBusquedaCliente controladorBusquedaCliente;
	ControladorBusquedaProductos controladorBusquedaProducto;
	
	vistaBusquedaProductos vistaBusquedaProducto;
	VentanaBusquedaCliente ventanaBusquedaCliente;
	
	
	public Controlador(MaestroProducto maestroProducto, Stock stock, Sucursal sucursal, Cliente cliente) {
		this.maestroProducto = maestroProducto;
		this.stock = stock;
		this.sucursal = sucursal;
		this.cliente = cliente;
	}
	
	public void inicializar() {
		
		
		this.ventanaBusquedaCliente= new VentanaBusquedaCliente();
		this.vistaBusquedaProducto = new vistaBusquedaProductos();
		
		this.controladorBusquedaCliente = new ControladorBusquedaCliente(cliente);
		this.controladorBusquedaProducto = new ControladorBusquedaProductos(this.maestroProducto, this.stock, this.sucursal);
		
		
		this.controladorBusquedaCliente.setControladorBusquedaProducto(this.controladorBusquedaProducto);
		this.controladorBusquedaProducto.setControladorBusquedaCliente(this.controladorBusquedaCliente);
		
		//por ahora cuando se inicia se inicia la ventana de cliente
		controladorBusquedaCliente.inicializar();		
		this.controladorBusquedaCliente.mostrarVentana();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
	}
	
	
	public static void main(String[] args) {
		
		Cliente cliente = new Cliente(new DAOSQLFactory());
		MaestroProducto maestroProducto = new MaestroProducto(new DAOSQLFactory());
		Stock stock = new Stock(new DAOSQLFactory());
		Sucursal sucursal = new Sucursal(new DAOSQLFactory());
		
		Controlador controlador = new Controlador(maestroProducto,stock, sucursal, cliente);
		
		controlador.inicializar();
	}
	
}
