package persistencia.dao.interfaz;

import java.util.List;

import dto.FacturaDTO;

public interface FacturaDAO {
	public boolean insert(FacturaDTO factura);
	
	public List<FacturaDTO> readAll();
}
