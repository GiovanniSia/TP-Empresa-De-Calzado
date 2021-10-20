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
		
		if(prodProv==null) {
			 JOptionPane.showMessageDialog(null, "No existe ningun proveedor que venda este producto, no se pudo crear el pedido automático", "Error", JOptionPane.ERROR_MESSAGE);
			 return;
		}
		
		int idProv = prov.getId();
		String nombreProv = prov.getNombre();
		int idMaestroProd = producto.getIdMaestroProducto();
		String nombreMaestroprod = producto.getDescripcion();
		
		int cantPorLote = prodProv.getCantidadPorLote();
		int cantidadTotal = determinarCantidad(producto,prodProv);;
				
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
		String fecha = dtf.format(LocalDateTime.now());
		
	    DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm:ss");
	    String hora = tf.format(LocalDateTime.now());
		
	    double precioUnidad = prodProv.getPrecioVenta();
	    int auxCantDeLotes = cantidadTotal/cantPorLote;
	    double precioTotal = precioUnidad * auxCantDeLotes;
	    
	    String estado = "En espera";
	    int idSuc = idSucursal;
	    String fechaEnvio = null;
	    String fechaCompleto = null;
	    
	    PedidosPendientesDTO p = new PedidosPendientesDTO(0,idProv,nombreProv,idMaestroProd,nombreMaestroprod,cantidadTotal,fecha,hora,precioUnidad,precioTotal,estado,idSuc,fechaEnvio,fechaCompleto);
	    
	    boolean insert = pedidosPendientes.insert(p);
	    if(!insert) {
	    	JOptionPane.showMessageDialog(null, "Ha ocurrido un error al ingresar el pedido automatico para el prod: "+producto.getDescripcion());
	    }else {
	    	JOptionPane.showMessageDialog(null, "Se ha generado un pedido automatico para el Producto: '"+producto.getDescripcion() +"' con exito");
	    }
	}
	
	
	
	private static int determinarCantidad(MaestroProductoDTO producto, ProductoDeProveedorDTO prodProv) {
		int cantProdDeseada = producto.getCantidadAReponer();
		int cantProd=0;
		int cantLotes=0;
		double costo = prodProv.getPrecioVenta();
		while(cantProd<cantProdDeseada) {
			cantProd +=prodProv.getCantidadPorLote();
			cantLotes++;
		}
		double costoTotal = cantLotes * costo;
		System.out.println("cantidad total de prod luego del while: "+cantProd+" - cantidad de lotes: "+cantLotes);
		if(cantProd==cantProdDeseada) {
			System.out.println("Se compra stock con cantidad justa");
			return cantProd;
		}else {
			//si la cant que se compro supera a lo que se pidio se verifica que esa cantidad no supere el +20%
			int auxCantLotes = cantLotes-1;
			double precioConUnStockMenos = auxCantLotes * costo; 
			double conAumentoDePorciento = ( (20 *precioConUnStockMenos)/100 ) + precioConUnStockMenos;//+20% al precio con -1 stock
					
			boolean superaLaReglaDeRedondeo = conAumentoDePorciento < costoTotal;
			if(superaLaReglaDeRedondeo) {
				System.out.println("Se compra un stock menos ya que este supera el +20%, por lo que se usara un stock menos");				
				//le quitamos un stock porque supera el 20%
				cantProd -=prodProv.getCantidadPorLote();
				System.out.println("Cantidad total de prod que se compran: "+cantProd+"\ncant de lotes: "+auxCantLotes);
				return cantProd;
			}else {
				System.out.println("Se compra un stock de mas y no supera el 20%");
				System.out.println("Cantidad total de prod que se compran: "+cantProd+"\ncant de lotes: "+cantLotes);
				return cantProd;
			}
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
