package persistencia.dao.interfaz;

import java.util.List;

import dto.MaestroProductoDTO;

public interface MaestroProductoDAO {
	public List<MaestroProductoDTO> readAll();
	
	public List<MaestroProductoDTO> getMaestroProducto(String nombre);
	
	public List<MaestroProductoDTO> filtrarPorCodProducto(String valor);
	
	public List<MaestroProductoDTO> filtrarPorDescripcion(String valor);
	
	public List<MaestroProductoDTO> filtrarPorTalle(String valor);
	
}
