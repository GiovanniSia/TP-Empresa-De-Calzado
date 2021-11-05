package presentacion.controlador.supervisor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import dto.MaestroProductoDTO;
import dto.PedidosPendientesDTO;
import dto.ProductoDeProveedorDTO;
import dto.ProveedorDTO;
import dto.StockDTO;
import modelo.PedidosPendientes;
import modelo.ProductoDeProveedor;
import modelo.Proveedor;
import modelo.Stock;
import presentacion.vista.generarPedidoProveedor.VentanaGenerarPedidoProveedor;

public class ControladorGenerarPedidoAProveedorManualmente {
	
	
	MaestroProductoDTO productoElegido;
	StockDTO stockDeProd;
	
	Proveedor proveedor;
	List<ProveedorDTO> todosLosProveedores;
	
	List<ProveedorDTO> proveedorEnTabla;
	
	Stock stock;
	List<StockDTO> todoElStock;
	
	ProductoDeProveedor productoDeProveedor; 
	List<ProductoDeProveedorDTO> todosLosProductosDeProveedor;
	
	//estos dos quizas quitarlos
	PedidosPendientes pedidosPendientes;
	List<PedidosPendientesDTO> todosLosPedidosPendientes;
	
	VentanaGenerarPedidoProveedor ventanaGenerarPedidoProveedor;
	
	
	
	public ControladorGenerarPedidoAProveedorManualmente(Proveedor proveedor,Stock stock,PedidosPendientes pedidosPendientes,ProductoDeProveedor productoDeProveedor) {
		this.proveedor=proveedor;
		this.stock = stock;
		this.pedidosPendientes = pedidosPendientes;
		this.productoDeProveedor = productoDeProveedor;
		this.todosLosProveedores = new ArrayList<ProveedorDTO>();
		this.todoElStock = new ArrayList<StockDTO>();
		this.todosLosPedidosPendientes = new ArrayList<PedidosPendientesDTO>();
		this.todosLosProductosDeProveedor =new ArrayList<ProductoDeProveedorDTO>(); 
		this.proveedorEnTabla = new ArrayList<ProveedorDTO>(); 
	}
	
	public void setProductoElegido(MaestroProductoDTO producto,StockDTO stock) {
		this.productoElegido = producto;	
		this.stockDeProd=stock;

		
	}
	
	public void inicializar() {
		this.ventanaGenerarPedidoProveedor = new VentanaGenerarPedidoProveedor();
		this.todosLosProveedores = this.proveedor.readAll();
		this.todoElStock = this.stock.readAll();
		this.todosLosPedidosPendientes = this.pedidosPendientes.readAll();
		
		llenarTablaProductoElegido();
		llenarTablaProveedores();
	}

	
	public void mostrarVentana() {
		this.ventanaGenerarPedidoProveedor.show();
	}

	public void cerrarVentana() {
		this.ventanaGenerarPedidoProveedor.cerrar();
	}
	
	public void llenarTablaProductoElegido() {
		
		int idProd = this.productoElegido.getIdMaestroProducto();
		String descr = this.productoElegido.getDescripcion();
		String tipo = this.productoElegido.getTipo();
		String propio = this.productoElegido.getFabricado();
		
		double costoPro = this.productoElegido.getPrecioCosto();
		BigDecimal costoProduccion = new BigDecimal(costoPro);
		
		double precioMayo = this.productoElegido.getPrecioMayorista();
		BigDecimal precioMayorista = new BigDecimal(precioMayo);
		
		double precioMino = this.productoElegido.getPrecioMayorista();
		BigDecimal precioMinorista = new BigDecimal(precioMino);
		
		int puntoRep = this.productoElegido.getPuntoRepositorio();
		int idProveedor = this.productoElegido.getIdProveedor();
		String talle = this.productoElegido.getTalle();
		String medida = this.productoElegido.getUnidadMedida();
		String estado = this.productoElegido.getEstado();
		int cantRep = this.productoElegido.getCantidadAReponer();
		int diasARep = this.productoElegido.getDiasParaReponer();
		
		
		int stockDisp;
		String codLote=null;
		if(this.stockDeProd != null) {
			stockDisp = this.stockDeProd.getStockDisponible();
			codLote = this.stockDeProd.getCodigoLote();
		}else {
			stockDisp = 0;
			codLote = "Sin asignar";
		}
	
		Object[] fila = {idProd,descr,tipo,propio,costoProduccion,precioMayorista,precioMinorista,puntoRep,idProveedor,talle,medida,estado,cantRep,diasARep,stockDisp,codLote};
		this.ventanaGenerarPedidoProveedor.getModelProducto().addRow(fila);
	}
	
	
	public void llenarTablaProveedores() {
		this.ventanaGenerarPedidoProveedor.getModelProveedores().setRowCount(0);// borrar datos de la tabla
		this.ventanaGenerarPedidoProveedor.getModelProveedores().setColumnCount(0);
		this.ventanaGenerarPedidoProveedor.getModelProveedores().setColumnIdentifiers(this.ventanaGenerarPedidoProveedor.getNombreColumnasProveedores());
		this.proveedorEnTabla.removeAll(this.proveedorEnTabla);
		
		for(ProveedorDTO p: this.todosLosProveedores) {
			for(ProductoDeProveedorDTO prodProv: this.todosLosProductosDeProveedor) {
				if(prodProv.getIdProveedor() == p.getId() && prodProv.getIdMaestroProducto() == this.productoElegido.getIdMaestroProducto()) {
					int id = p.getId();
					String nombre = p.getNombre();
					String correo = p.getCorreo();
					
					double credDisp = p.getCreditoDisponible();
					BigDecimal creditoDisponible = new BigDecimal(credDisp);
					
					double precio = prodProv.getPrecioVenta();
					BigDecimal precioVenta = new BigDecimal(precio);
					
					int cantProd = prodProv.getCantidadPorLote();
					Object[] fila = {id,nombre,correo,creditoDisponible,precioVenta,cantProd};
					this.ventanaGenerarPedidoProveedor.getModelProveedores().addRow(fila);
					this.proveedorEnTabla.add(p);
				}
			}
		}
		
		
	}
	
}
