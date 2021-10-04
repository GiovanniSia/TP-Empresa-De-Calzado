package persistencia.dao.interfaz;

import java.util.List;

import dto.MaestroProductoDTO;

public interface MaestroProductoDAO {
	public List<MaestroProductoDTO> readAll();
	
	public List<MaestroProductoDTO> getMaestroProductoAproximado(String nombreColumna, String txtAprox);
	
}
