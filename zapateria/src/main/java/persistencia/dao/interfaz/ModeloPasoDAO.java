package persistencia.dao.interfaz;

import java.util.List;

import dto.PasoDTO;

public interface ModeloPasoDAO {
	public boolean insert(PasoDTO paso);

	public boolean delete(PasoDTO paso);
	
	public List<PasoDTO> readAll();
	
	public boolean updatePaso(PasoDTO paso);
}
