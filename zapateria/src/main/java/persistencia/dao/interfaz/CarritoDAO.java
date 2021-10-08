package persistencia.dao.interfaz;

import java.util.List;

import dto.CarritoDTO;

public interface CarritoDAO {
	
	public boolean insert(CarritoDTO carrito);
	public boolean delete(CarritoDTO carrito);
	public List<CarritoDTO> readAll();
}