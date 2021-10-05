package persistencia.dao.interfaz;

import java.util.List;

import dto.MaestroProductoDTO;

public interface MaestroProductoDAO {
	public List<MaestroProductoDTO> readAll();
	
	public List<MaestroProductoDTO> getMaestroProductoAproximado(String nombreColumna1, String txtAprox1, String nombreColumna2, String txtAprox2, String nombreColumna3, String txtAprox3);
	
}
