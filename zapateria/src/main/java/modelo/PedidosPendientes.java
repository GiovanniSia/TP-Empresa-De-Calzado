package modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import dto.MaestroProductoDTO;
import dto.PedidosPendientesDTO;
import dto.ProductoDeProveedorDTO;
import dto.ProveedorDTO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.PedidosPendientesDAO;
import persistencia.dao.mysql.DAOSQLFactory;

public class PedidosPendientes {
	private PedidosPendientesDAO pedido;
	
	public PedidosPendientes(DAOAbstractFactory metodo_presistencia) {
		this.pedido = metodo_presistencia.createPedidosPendientesDAO();
	}
	
	public boolean insert(PedidosPendientesDTO pedido) {
		return this.pedido.insert(pedido);
	}
	
	public boolean delete(PedidosPendientesDTO pedido) {
		return this.delete(pedido);
	}
	
	public boolean update(PedidosPendientesDTO nuevoPedido, int idPedido) {
		return this.pedido.update(nuevoPedido, idPedido);
	}
	
	public List<PedidosPendientesDTO> readAll(){
		return this.pedido.readAll();
	}
	
	
	
//	//nose si va aca :(
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static void generarPedidoAutomatico(int idSucursal, MaestroProductoDTO producto) {
		PedidosPendientes pedidosPendientes = new PedidosPendientes(new DAOSQLFactory()); 
		
		ProveedorDTO prov = getProveedorDeProducto(producto);
		ProductoDeProveedorDTO prodProv = getProductoDeProveedor(producto.getIdProveedor(),producto.getIdMaestroProducto());
		if(prov==null) {
			//VERIFICAR LUEGO QUE PASA SI NO HAY UN PROVEEDOR PREFERENCIADO PARA ESTE PRODUCTO			
		}

		int idProv = prov.getId();
		String nombreProv = prov.getNombre();
		int idMaestroProd = producto.getIdMaestroProducto();
		String nombreMaestroprod = producto.getDescripcion();
		int cantidad = producto.getCantidadAReponer();
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
		String fecha = dtf.format(LocalDateTime.now());
		
	    DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm:ss");
	    String hora = tf.format(LocalDateTime.now());
		
	    double precioUnidad = prodProv.getPrecioVenta();
	    double precioTotal = precioUnidad;
	    
	    String estado = "En espera";
	    int idSuc = idSucursal;
	    String fechaEnvio = null;
	    String fechaCompleto = null;
	    
	    PedidosPendientesDTO p = new PedidosPendientesDTO(0,idProv,nombreProv,idMaestroProd,nombreMaestroprod,cantidad,fecha,hora,precioUnidad,precioTotal,estado,idSuc,fechaEnvio,fechaCompleto);
	    
	    boolean insert = pedidosPendientes.insert(p);
	    if(!insert) {
	    	JOptionPane.showMessageDialog(null, "Ha ocurrido un error al ingresar el pedido automatico para el prod: "+producto.getDescripcion());
	    }else {
	    	JOptionPane.showMessageDialog(null, "Se ha generado un pedido automatico para el Producto: '"+producto.getDescripcion() +"' con exito");
	    }
	}
	
	
	
	private static ProductoDeProveedorDTO getProductoDeProveedor(int idProveedor,int idProducto) {
		ProductoDeProveedor productoDeProveedor = new ProductoDeProveedor(new DAOSQLFactory());
		ArrayList<ProductoDeProveedorDTO> todosLosProductoDeProveedor = (ArrayList<ProductoDeProveedorDTO>) productoDeProveedor.readAll();
		for(ProductoDeProveedorDTO p: todosLosProductoDeProveedor) {
			if(idProveedor==p.getIdProveedor() && p.getIdMaestroProducto()==idProducto) {
				return p;
			}
		}
		return null;
	}

	/*
		`Id` int(11) NOT NULL AUTO_INCREMENT,
    `IdProveedor` int(11) NOT NULL,
    `NombreProveedor` varchar(45) NOT NULL,
    `IdMaestroProducto` int(11) NOT NULL,
    `NombreMaestroProducto` varchar(45) NOT NULL,
    `Cantidad` int(11) NOT NULL,
    `Fecha` Date NOT NULL,
    `Hora` Time NOT NULL,
    `PrecioUnidad` double(45,2) NOT NULL,
    `PrecioTotal` double(45,2) NOT NULL,
    `Estado` varchar(45) NOT NULL,
    `IdSucursal` int(11) NOT NULL,
    `FechaEnvioMail` Date NOT NULL,
	`FechaCompleto` Date NOT NULL,
	
	*/
	private static ProveedorDTO getProveedorDeProducto(MaestroProductoDTO prod) {
		Proveedor proveedor = new Proveedor(new DAOSQLFactory());
		ArrayList<ProveedorDTO> todosLosProveedores = (ArrayList<ProveedorDTO>) proveedor.readAll();
		for(ProveedorDTO p: todosLosProveedores) {
			if(p.getId()== prod.getIdProveedor()) {
				return p;
			}
		}
		return null;
	}
}
