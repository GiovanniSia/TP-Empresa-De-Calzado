package persistencia.dao.interfaz;

import java.util.List;

import dto.StockDTO;

public interface StockDAO {
	public List<StockDTO> readAll();
}
