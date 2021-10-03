package modelo;

import java.util.List;

import dto.MaestroProductoDTO;
import dto.StockDTO;
import persistencia.dao.mysql.DAOSQLFactory;

public class generarOrdenesFabricacion {
	
	//public static 
	public boolean faltaStockDeUnProductoEnUnaSucursal(int idSucursal, MaestroProductoDTO producto) {
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
	
	public int contarStockDeUnProductoEnUnaSucursal(int idSucursal, int idProducto) {
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
}
