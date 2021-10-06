package persistencia.dao.interfaz;

import java.util.List;

import dto.HistorialCambioMProductoDTO;

public interface HistorialCambioMProductoDAO {
	public boolean insert(HistorialCambioMProductoDTO historialCambioMProducto);

	public boolean delete(HistorialCambioMProductoDTO historialCambioMProducto_a_eliminar);
	
	public boolean update(int id_historialCambioMProducto_a_actualizar, HistorialCambioMProductoDTO historialCambioMProducto_nuevo);

	public List<HistorialCambioMProductoDTO> readAll();

	List<HistorialCambioMProductoDTO> getHistorialCambioMProductoAproximado(String nombreColumna1, String txtAprox1,
			String nombreColumna2, String txtAprox2);
}
