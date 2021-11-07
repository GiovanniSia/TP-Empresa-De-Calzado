package persistencia.dao.interfaz;

import java.util.List;

import dto.StockDTO;

public interface StockDAO {
	public List<StockDTO> readAll();
	
	public boolean insert(StockDTO stock) ;
	
	public boolean actualizarStock(int idStock,int cant);
}
