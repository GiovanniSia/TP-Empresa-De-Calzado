package persistencia.dao.interfaz;

import java.util.List;

import dto.CajaDTO;

public interface CajaDAO {
	public boolean insert(CajaDTO caja);

	public boolean delete(CajaDTO caja_a_eliminar);
	
	public boolean update(int id_caja_a_actualizar, CajaDTO caja_nuevo);

	public List<CajaDTO> readAll();
	
	public List<CajaDTO> getCajaAproximado(String nombreColumna1, String txtAprox1,
			String nombreColumna2, String txtAprox2);
}
