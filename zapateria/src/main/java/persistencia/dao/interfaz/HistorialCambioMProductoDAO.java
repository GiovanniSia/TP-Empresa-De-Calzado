package persistencia.dao.interfaz;

import java.util.List;

import dto.HistorialCambioMProductoDTO;

public interface HistorialCambioMProductoDAO {
	public boolean insert(HistorialCambioMProductoDTO historialCambioMProducto);

	public boolean delete(HistorialCambioMProductoDTO historialCambioMProducto_a_eliminar);
	
	public boolean update(int id_historialCambioMProducto_a_actualizar, HistorialCambioMProductoDTO historialCambioMProducto_nuevo);

	public List<HistorialCambioMProductoDTO> readAll();

	public List<HistorialCambioMProductoDTO> getFiltroHistorialCambioMProducto(String nombreColumna1, String txtAprox1,
			String nombreColumna2, String txtAprox2, String nombreColumna3, String txtAprox3,String nombreColumna4, String txtAprox4);
}
