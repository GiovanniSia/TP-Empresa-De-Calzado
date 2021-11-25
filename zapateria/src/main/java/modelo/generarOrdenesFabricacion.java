package modelo;

import java.math.BigDecimal;
import java.util.List;

import dto.FabricacionesDTO;
import dto.MaestroProductoDTO;
import dto.OrdenFabricaDTO;
import dto.StockDTO;
import persistencia.dao.mysql.DAOSQLFactory;

public class generarOrdenesFabricacion {
	
	public static boolean faltaStockDeUnProductoEnUnaSucursal(int idSucursal, MaestroProductoDTO producto) {
		Double cantidadEnStock = contarStockDeUnProductoEnUnaSucursal(idSucursal,producto.getIdMaestroProducto());
		BigDecimal auxcantidadEnStock = new BigDecimal(cantidadEnStock);
		System.out.println("cantidad de stock restante: "+auxcantidadEnStock);
		if(producto.getPuntoRepositorio()>=auxcantidadEnStock.doubleValue()) {
			return true;
		}
		return false;
		/* codigo de ejemplo en caso de que solo tenga la id del producto
		MaestroProductoDTO producto;
		List<MaestroProductoDTO> todosLosProducto = a.createMaestroProductoDAO().readAll();
		for(MaestroProductoDTO p : todosLosProducto) {
			if(p.getIdMaestroProducto() == idProducto) {
				producto = p;
			}
		}*/
	}
	
	public static Double contarStockDeUnProductoEnUnaSucursal(int idSucursal, int idProducto) {
		DAOSQLFactory a = new DAOSQLFactory();
		List<StockDTO> todosLosStocks = a.createStockDAO().readAll();
		Double cantidadContador = 0.0;
		for(StockDTO stock : todosLosStocks) {
			if(stock.getIdSucursal() == idSucursal && stock.getIdProducto() == idProducto) {
				cantidadContador = cantidadContador + stock.getStockDisponible();
			}
		}
		return cantidadContador;
	}
	
	public static boolean sePuedeCrearProducto(MaestroProductoDTO producto) {
		return producto.getFabricado().equals("S");
	}
	
	public static void crearOrdenFabricacion(int idSucursal, MaestroProductoDTO producto) {
		//Codigo automatico
		
		//OrdenFabricaDTO orden = new OrdenFabricaDTO(0,producto.getIdMaestroProducto(), fechaRequerido, producto.getCantidadAReponer(), codLote, idSucursal);
		String fechaRequerido = crearFechaRequerido(producto);
		String codLote = crearCodigoLote(producto);
		Double cantReponer = producto.getPuntoRepositorio()-contarStockDeUnProductoEnUnaSucursal(idSucursal, producto.getIdMaestroProducto());
		if(cantReponer <= 0.0) {
			cantReponer = 0.0;
		}
		cantReponer = cantReponer + producto.getCantidadAReponer();
		OrdenFabricaDTO orden = fabricarOrdenFabricacion(producto.getIdMaestroProducto(), fechaRequerido, cantReponer, codLote, idSucursal);
		//DAOSQLFactory a = new DAOSQLFactory();
		//a.createOrdenFabricaDAO().insert(orden);
		insertarOrdenFabricacion(orden);
	}
	
	private static OrdenFabricaDTO fabricarOrdenFabricacion(int idProducto, String fechaRequerido, Double getCantidadAReponer, String codLote, int idSucursal) {
		OrdenFabricaDTO orden = new OrdenFabricaDTO(0,idProducto, fechaRequerido, getCantidadAReponer, codLote, idSucursal);
		return orden;
	}
	
	private static void insertarOrdenFabricacion(OrdenFabricaDTO orden) {
		DAOSQLFactory a = new DAOSQLFactory();
		a.createOrdenFabricaDAO().insert(orden);
	}
	
	@SuppressWarnings("deprecation")
	public static String crearCodigoLote(MaestroProductoDTO producto) {
		java.util.Date fechaActual = new java.util.Date();
		fechaActual.setDate(fechaActual.getDate());
		int dias = fechaActual.getDate();
		int mes = fechaActual.getMonth() + 1;
		int anio = fechaActual.getYear() + 1900;
		String fechaRequerido = anio+""+mes+""+dias;
		
		int hora = fechaActual.getHours();
		int minuto = fechaActual.getMinutes();
		String codLote = "L"+fechaRequerido+""+hora+""+minuto;
		return codLote;
	}
	
	@SuppressWarnings("deprecation")
	private static String crearFechaRequerido(MaestroProductoDTO producto) {
		java.util.Date fechaActual = new java.util.Date();
		fechaActual.setDate(fechaActual.getDate()+producto.getDiasParaReponer());
		int dias = fechaActual.getDate();
		int mes = fechaActual.getMonth() + 1;
		int anio = fechaActual.getYear() + 1900;
		String fechaRequerido = anio+"-"+mes+"-"+dias;
		return fechaRequerido;
	}
	
	public static void main(String[] args) {
		//insert into maestroProductos values(1,'Zapa Mc Quenn','Zapatilla','S',50,100,10,1,"talle 1",2,"activo",35,5);
		//insert into maestroProductos values(2,'Zapa Shrek','Zapatilla','S',50,100,10,1,"talle 1",2,"activo",60,35);
		DAOSQLFactory a = new DAOSQLFactory();
		List<MaestroProductoDTO> productos = a.createMaestroProductoDAO().readAll();
		for(MaestroProductoDTO mp: productos) {
			verificarYGenerarOrden(10,mp);
		}
		
		List<OrdenFabricaDTO> qwer = a.createOrdenFabricaDAO().readAll();
		for(OrdenFabricaDTO orden: qwer) {
			System.out.print(orden.getIdOrdenFabrica()+ " ");
			System.out.print(orden.getFechaRequerido()+ " ");
			System.out.print(orden.getCantidad()+ " ");
			System.out.print(orden.getIdProd()+ " ");
			System.out.println(" ");
		}
	}
	
	public static boolean hayOrdenYaGenerada(int idSucursal, MaestroProductoDTO producto) {
		DAOSQLFactory a = new DAOSQLFactory();
		return a.createOrdenFabricaDAO().hayOrdenPendiente(producto.getIdMaestroProducto(), idSucursal);
	}
	
	public static boolean verificarYGenerarOrden(int idSucursal, MaestroProductoDTO producto) {
		boolean ret = false;
		//DEVUELVE FALSE SI NO HABIA FALTA CREAR ORDEN DE MANUFACTURA O EL PRODUCTO NO ERA MANUFACTURABLE
		if(faltaStockDeUnProductoEnUnaSucursal(idSucursal, producto)) {
			if(sePuedeCrearProducto(producto)) {
				if(!hayOrdenYaGenerada(idSucursal, producto)) {
					crearOrdenFabricacion(idSucursal, producto);
					ret = true;	//DEVUELVE TRUE SI GENERA ORDEN
					System.out.println("HIZO LA CREACION");
				}
			}
		}
		return ret;
	}
	
	public static void actualizarTodosLosTrabajosListosParaLosEnvios() {
		//ESTA FUNCION ES MAS PARA EL MODELO YA PARA QUE SEA USADA AL FFINAL DE CADA DIA O AL INICIO, DARA DE ALTA EL STOCK DE 
		//LOS TRABAJOS COMPLETOS CUYAS FECHAS DE LLEGADA PASEN A LA ACTUAL
		//, LITERALMENTE RECORRE TODAS LOS PROCESOS DE TRABAJO COMPLETADOS Y 
		//VERFICA SI LA FECHA ESTA LISTA PARA EL ENVIO
		Fabricacion modeloFabricacion = new Fabricacion(new DAOSQLFactory());
		List<FabricacionesDTO> todasLasOrdenesCompletas = modeloFabricacion.readAllFabricacionesCompletas();
		for(FabricacionesDTO of: todasLasOrdenesCompletas) {
			modeloFabricacion.actualizarSiLlegoFechaDeEntrega(of);
		}
	}
	
}
