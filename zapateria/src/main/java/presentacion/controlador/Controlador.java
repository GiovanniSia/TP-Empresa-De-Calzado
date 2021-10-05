package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.Cliente;
import modelo.MaestroProducto;
import modelo.Stock;
import modelo.Sucursal;
import presentacion.vista.VentanaBusquedaCliente;
import presentacion.vista.vistaBusquedaProductos;

public class Controlador implements ActionListener {
	
	MaestroProducto maestroProducto;
	Stock stock;
	Sucursal sucursal;
	
	ControladorBusquedaCliente controladorBusquedaCliente;
	ControladorBusquedaProductos controladorBusquedaProducto;
	
	vistaBusquedaProductos vistaBusquedaProducto;
	VentanaBusquedaCliente ventanaBusquedaCliente;
	
	
	public  Controlador() {
		this.ventanaBusquedaCliente= new VentanaBusquedaCliente();
		this.vistaBusquedaProducto = new vistaBusquedaProductos();
		
//		this.controladorBusquedaProducto = new ControladorBusquedaProductos(maestroProducto,stock,sucursal); 
	}
	
	public void inicializar() {
//		this.controladorBusquedaCliente = new controladorBusquedaCliente(ventanaBusquedaCliente, cliente) ; 
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
	}

}
