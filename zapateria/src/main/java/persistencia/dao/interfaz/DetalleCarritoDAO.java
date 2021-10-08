package persistencia.dao.interfaz;

import java.util.List;

import dto.DetalleCarritoDTO;

public interface DetalleCarritoDAO {
	
	public boolean insert(DetalleCarritoDTO detalle);
	
	public boolean delete(DetalleCarritoDTO detalle);
	
	public List<DetalleCarritoDTO> readAll();
}
