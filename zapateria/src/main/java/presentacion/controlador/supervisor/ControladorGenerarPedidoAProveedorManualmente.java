package presentacion.controlador.supervisor;

import java.util.List;

import dto.MaestroProductoDTO;
import dto.PedidosPendientesDTO;
import dto.ProveedorDTO;
import dto.StockDTO;
import modelo.PedidosPendientes;
import modelo.Proveedor;
import modelo.Stock;
import presentacion.vista.generarPedidoProveedor.VentanaGenerarPedidoProveedor;

public class ControladorGenerarPedidoAProveedorManualmente {
	
	
	MaestroProductoDTO productoElegido;
	
	Proveedor proveedor;
	List<ProveedorDTO> todosLosProveedores;
	
	Stock stock;
	List<StockDTO> todoElStock;
	
	PedidosPendientes pedidosPendientes;
	List<PedidosPendientesDTO> todosLosPedidosPendientes;
	
	VentanaGenerarPedidoProveedor ventanaGenerarPedidoProveedor;
	
	public ControladorGenerarPedidoAProveedorManualmente(Proveedor proveedor,Stock stock,PedidosPendientes pedidosPendientes) {
		this.proveedor=proveedor;
		this.stock = stock;
		this.pedidosPendientes = pedidosPendientes;
	}
	
	public void setProductoElegido(MaestroProductoDTO producto) {
		this.productoElegido = producto;	
	}
	
	public void inicializar() {
		this.ventanaGenerarPedidoProveedor = new VentanaGenerarPedidoProveedor();
		this.todosLosProveedores = this.proveedor.readAll();
		this.todoElStock = this.stock.readAll();
		this.todosLosPedidosPendientes = this.pedidosPendientes.readAll();
	}

	
	public void mostrarVentana() {
		this.ventanaGenerarPedidoProveedor.show();
	}

	public void cerrarVentana() {
		this.ventanaGenerarPedidoProveedor.cerrar();
	}
	
	
	
	
}
