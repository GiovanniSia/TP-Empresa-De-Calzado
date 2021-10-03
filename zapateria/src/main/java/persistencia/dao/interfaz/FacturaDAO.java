package persistencia.dao.interfaz;

import java.util.List;

import dto.FacturaDTO;

public interface FacturaDAO {
	public List<FacturaDTO> readAll();
}
