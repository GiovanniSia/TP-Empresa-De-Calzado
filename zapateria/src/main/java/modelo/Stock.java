package modelo;

import java.util.List;

import dto.StockDTO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.StockDAO;

public class Stock {
	
	private StockDAO stock;
	
	public Stock(DAOAbstractFactory metodo_persistencia) {
		this.stock = metodo_persistencia.createStockDAO();
	}

	public List<StockDTO> readAll(){
		return this.stock.readAll();
	}
}
