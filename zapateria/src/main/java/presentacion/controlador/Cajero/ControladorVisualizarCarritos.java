package presentacion.controlador.Cajero;

import java.util.ArrayList;
import java.util.List;

import dto.CarritoDTO;
import dto.ClienteDTO;
import dto.DetalleCarritoDTO;
import dto.MaestroProductoDTO;
import modelo.Carrito;
import modelo.Cliente;
import modelo.DetalleCarrito;
import modelo.MaestroProducto;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.vista.Cajero.VentanaVisualizarCarritos;

public class ControladorVisualizarCarritos {

	private final int idSucursal=1;
	
	Carrito carrito;
	DetalleCarrito detalleCarrito;
	Cliente cliente;
	MaestroProducto maestroProducto;
	
	List<CarritoDTO> listaCarritos;
	List<DetalleCarritoDTO> listaDetalleCarrito;
	List<ClienteDTO> listaClientes;
	List<CarritoDTO> carritosEnTabla;
	
	VentanaVisualizarCarritos ventanaVisualizarCarritos;
	
	public ControladorVisualizarCarritos(Carrito carrito, DetalleCarrito detalleCarrito,Cliente cliente, MaestroProducto maestroProducto) {
		this.carrito = carrito;
		this.detalleCarrito = detalleCarrito;
		this.cliente = cliente;
		this.maestroProducto = maestroProducto;
		
		this.listaCarritos = new ArrayList<CarritoDTO>();
		this.listaDetalleCarrito = new ArrayList<DetalleCarritoDTO>();
		this.listaClientes = new ArrayList<ClienteDTO>();
		this.carritosEnTabla = new ArrayList<CarritoDTO>();
	}

	
	
	public void inicializar() {
		
		this.listaCarritos = this.carrito.readAll();
		this.listaDetalleCarrito = this.detalleCarrito.readAll();
		this.listaClientes = this.cliente.readAll();
		
		
		this.ventanaVisualizarCarritos = new VentanaVisualizarCarritos();
		
		llenarTabla();
		this.ventanaVisualizarCarritos.show();
	}
	
	
	public void llenarTabla() {
		String nombreProducto="";
		int idCarrito=0;
		String hora = "";
		int idCliente=0;
		String nombreCliente = "";
		String tipoCliente = "";
		int cantidad=0;
		double precioUnitario=0.0;
		double total=0.0;
		
		//ahora hay dos tablas: una para el carrito y otra para ver el detalle
		for(CarritoDTO carrito: this.listaCarritos) {
			for(DetalleCarritoDTO detalle: this.listaDetalleCarrito) {
				if(carrito.getIdCarrito()==detalle.getIdCarrito() && carrito.getIdSucursal()==this.idSucursal) {
					ClienteDTO cliente = this.cliente.selectCliente(detalle.getIdCliente());
					MaestroProductoDTO producto = this.maestroProducto.selectMaestroProducto(detalle.getIdProducto());
					
					if(!yaFueAgregado(carrito)) {						
						idCarrito = carrito.getIdCarrito();
						hora = carrito.getHora();
						idCliente = detalle.getIdCliente();
						nombreCliente = cliente.getNombre()+" "+cliente.getApellido();
						tipoCliente = cliente.getTipoCliente();
						
						cantidad = detalle.getCantidad();
						precioUnitario = detalle.getPrecio();
						nombreProducto = producto.getDescripcion();	
						total=carrito.getTotal();
						
						System.out.println("carrito que se agrega: "+carrito.getIdCarrito());
						this.carritosEnTabla.add(carrito);
					}else {
						
						nombreProducto =nombreProducto+"-"+ producto.getDescripcion();
						System.out.println("el prod ya fue agragdo pro lo que se le concatena: "+nombreProducto);
					}
					
					
//					ID Carrito","Hora","Cod Cliente","Nombre Cliente","Tipo Cliente","Productos","Cantidad","P. Unitario","P. Total Venta"};
				}
				
			}
			
			Object[] fila = {idCarrito,hora,idCliente,nombreCliente,tipoCliente,nombreProducto,cantidad,precioUnitario, total};
			this.ventanaVisualizarCarritos.getModelTablaSucursales().addRow(fila);
		}		
	}
	
	public boolean yaFueAgregado(CarritoDTO carrito) {
		for(CarritoDTO c: this.carritosEnTabla) {
			System.out.println("carrito a ver: "+carrito.getIdCarrito()+" carritoEnTabla: "+c.getIdCarrito());
			if(carrito.getIdCarrito()==c.getIdCarrito()) {
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		Carrito carrito = new Carrito(new DAOSQLFactory());
		DetalleCarrito detalleCarrito= new DetalleCarrito(new DAOSQLFactory());
		Cliente cliente = new Cliente(new DAOSQLFactory());
		MaestroProducto maestroProducto = new MaestroProducto(new DAOSQLFactory());
		ControladorVisualizarCarritos c = new ControladorVisualizarCarritos(carrito,detalleCarrito,cliente,maestroProducto);
		c.inicializar();
	}
	
}
