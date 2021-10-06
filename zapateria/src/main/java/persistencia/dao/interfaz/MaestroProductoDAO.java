package persistencia.dao.interfaz;

import java.util.List;

import dto.MaestroProductoDTO;

public interface MaestroProductoDAO {
	public List<MaestroProductoDTO> readAll();
	
	public List<MaestroProductoDTO> getMaestroProductoAproximado(String nombreColumna1, String txtAprox1, String nombreColumna2, String txtAprox2);
	
	public List<MaestroProductoDTO> filtrarPorCodProducto(String valor);
	
	public List<MaestroProductoDTO> filtrarPorDescripcion(String valor);
	
	public List<MaestroProductoDTO> filtrarPorTalle(String valor);
	
}
