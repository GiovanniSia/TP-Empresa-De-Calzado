package modelo;

import java.util.ArrayList;
import java.util.List;

import dto.MaestroProductoDTO;
import dto.StockDTO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.StockDAO;
import persistencia.dao.mysql.DAOSQLFactory;

public class Stock {
	
	private StockDAO stock;
	
	public Stock(DAOAbstractFactory metodo_persistencia) {
		this.stock = metodo_persistencia.createStockDAO();
	}

	public boolean insert(StockDTO stock) {
		return this.stock.insert(stock);
	}
	
	
	public List<StockDTO> readAll(){
		return this.stock.readAll();
	}
	
	public boolean actualizarStock(int idStock,Double double1) {
		return this.stock.actualizarStock(idStock,double1);
	}
	
	
	public static double cantidadTotalDeStock(MaestroProductoDTO producto) {
		Stock stock = new Stock(new DAOSQLFactory());
		ArrayList<StockDTO> todoElStock = (ArrayList<StockDTO>) stock.readAll();
		double cant=0;
		for(StockDTO s: todoElStock) {
			if(s.getIdProducto() == producto.getIdMaestroProducto()) {
				cant = cant + s.getStockDisponible();
			}
		}return cant;
	}
	
}
