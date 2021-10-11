package persistencia.dao.interfaz;

import java.util.List;

import dto.CarritoDTO;

public interface CarritoDAO {
	
	public boolean insert(CarritoDTO carrito);
	public boolean delete(CarritoDTO carrito);
	public List<CarritoDTO> readAll();
	
	public List<CarritoDTO> getCarritoFiltrado(String nombreColumna1, String txt1, String nombreColumna2, String txt2, String nombreColumna3,String txt3);
}