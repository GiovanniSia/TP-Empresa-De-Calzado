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
	
	public boolean finalizarPedido(String nuevoEstado, String fechaCompleto, String HoraCompleto, int idPedido)  {
		return this.pedido.finalizarPedido(nuevoEstado, fechaCompleto, HoraCompleto,idPedido);
	}
	
	public boolean cambiarEstado(int id, String estado){
		return this.pedido.cambiarEstado(id,estado);
	}
	
	public List<PedidosPendientesDTO> readAll(){
		return this.pedido.readAll();
	}
	
	public boolean updateTotal(int idPedido, double nuevoPrecio) {
		return this.pedido.updateTotal(idPedido, nuevoPrecio);
	}
	
	public List<PedidosPendientesDTO> getPedidosPendientesFiltrados(String nombreColumna1, String txt1, String nombreColumna2, String txt2, String nombreColumna3,String txt3, String nombreColumna4,String txt4, String nombreColumna5,String txt5, String nombreColumna6,String txt6, String nombreColumna7,String txt7, String nombreColumna8,String txt8){
		return this.pedido.getPedidosPendientesFiltrados(nombreColumna1, txt1, nombreColumna2, txt2, nombreColumna3, txt3, nombreColumna4, txt4, nombreColumna5, txt5, nombreColumna6, txt6, nombreColumna7, txt7, nombreColumna8, txt8);
	}
	
//	//nose si va aca :(
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static void generarPedidoAutomatico(int idSucursal, MaestroProductoDTO producto) {
		
		//Un maestroProducto tiene si o si un proveedor preferenciado (si no es fabricado en nuestra fabrica
		
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
	    int idEmpleado = 0; //como es un pedido auto, nadie genera este pedido
	    String fechaEnvio = null;
	    String horaEnvio = null;
	    String fechaCompleto = null;
	    String horaCompleto = null;
	    String unidadMedida = producto.getUnidadMedida();
	    PedidosPendientesDTO p = new PedidosPendientesDTO(0,idProv,nombreProv,idMaestroProd,nombreMaestroprod,cantidadTotal,fecha,hora,precioUnidad,precioTotal,estado,idSuc,idEmpleado,fechaEnvio,horaEnvio,fechaCompleto,horaCompleto,unidadMedida);
	    
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
					
			boolean superaLaReglaDeRedondeo = conAumentoDePorciento > costoTotal;
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
			System.out.println("producto que se encuentra, idProv : "+p.getIdProveedor()+" idMP: "+p.getIdMaestroProducto());
			if(idProveedor==p.getIdProveedor() && p.getIdMaestroProducto()==idProducto) {
				return p;
			}
		}
//si el proveedor que se pide no provee este producto entonces se busca otro proveedor que si provea este producto al menos
		for(ProductoDeProveedorDTO p: todosLosProductoDeProveedor) {
			System.out.println("producto que se encuentra, idProv : "+p.getIdProveedor()+" idMP: "+p.getIdMaestroProducto());
			if(p.getIdMaestroProducto()==idProducto) {
				return p;
			}
		}
//		si retorna null es porque ningunn proveedor provee este producto
		return null;
	}

	private static ProveedorDTO getProveedorDeProducto(MaestroProductoDTO prod) {
		Proveedor proveedor = new Proveedor(new DAOSQLFactory());
		ArrayList<ProveedorDTO> todosLosProveedores = (ArrayList<ProveedorDTO>) proveedor.readAll();
		for(ProveedorDTO p: todosLosProveedores) {
			if(p.getId()== prod.getIdProveedor()) {
				//retorna el prov pref
				return p;
			}
		}
		return null;
	}
}
