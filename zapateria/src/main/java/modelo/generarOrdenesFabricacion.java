package modelo;

import java.util.List;

import dto.MaestroProductoDTO;
import dto.OrdenFabricaDTO;
import dto.StockDTO;
import persistencia.dao.mysql.DAOSQLFactory;

public class generarOrdenesFabricacion {
	
	public static boolean faltaStockDeUnProductoEnUnaSucursal(int idSucursal, MaestroProductoDTO producto) {
		int cantidadEnStock = contarStockDeUnProductoEnUnaSucursal(idSucursal,producto.getIdMaestroProducto());
		if(producto.getPuntoRepositorio()>=cantidadEnStock) {
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
	
	public static int contarStockDeUnProductoEnUnaSucursal(int idSucursal, int idProducto) {
		DAOSQLFactory a = new DAOSQLFactory();
		List<StockDTO> todosLosStocks = a.createStockDAO().readAll();
		int cantidadContador = 0;
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
		java.util.Date fechaActual = new java.util.Date();
		fechaActual.setDate(fechaActual.getDate()+producto.getDiasParaReponer());
		int dias = fechaActual.getDate();
		int mes = fechaActual.getMonth() + 1;
		int anio = fechaActual.getYear() + 1900;
		String fechaRequerido = anio+"-"+mes+"-"+dias;
		
		int hora = fechaActual.getHours()+1;
		int minuto = fechaActual.getMinutes()+1;
		String codLote = "L"+fechaRequerido+""+hora+""+minuto;
		
		OrdenFabricaDTO orden = new OrdenFabricaDTO(0,producto.getIdMaestroProducto(), fechaRequerido, producto.getCantidadAReponer(), codLote, idSucursal);
		DAOSQLFactory a = new DAOSQLFactory();
		a.createOrdenFabricaDAO().insert(orden);
	}
	
	//CODIGO PARA VERLO ANDAR
/*
	  	insert into maestroProductos values(1,'Zapa Mc Quenn','Zapatilla','S',50,100,10,1,"talle 1",2,"activo",35,5);
		insert into maestroProductos values(2,'Zapa Shrek','Zapatilla','S',50,100,10,1,"talle 1",2,"activo",60,35);
	DAOSQLFactory a = new DAOSQLFactory();
	List<MaestroProductoDTO> productos = a.createMaestroProductoDAO().readAll();
	System.out.print("Momento de la verdad");
	if(modelo.generarOrdenesFabricacion.faltaStockDeUnProductoEnUnaSucursal(1,productos.get(0)) && modelo.generarOrdenesFabricacion.sePuedeCrearProducto(productos.get(0))) {
		modelo.generarOrdenesFabricacion.crearOrdenFabricacion(1, productos.get(0));
	}
	
	if(modelo.generarOrdenesFabricacion.faltaStockDeUnProductoEnUnaSucursal(2,productos.get(1)) && modelo.generarOrdenesFabricacion.sePuedeCrearProducto(productos.get(1))) {
		modelo.generarOrdenesFabricacion.crearOrdenFabricacion(2, productos.get(1));
	}
	
	List<OrdenFabricaDTO> qwer = a.createOrdenFabricaDAO().readAll();
	for(OrdenFabricaDTO orden: qwer) {
		System.out.print(orden.getIdOrdenFabrica()+ " ");
		System.out.print(orden.getFechaRequerido()+ " ");
		System.out.print(orden.getCantidad()+ " ");
		System.out.print(orden.getIdProd()+ " ");
		System.out.println(" ");
	}
	*/
}
