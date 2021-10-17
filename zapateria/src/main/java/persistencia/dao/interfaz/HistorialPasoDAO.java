package persistencia.dao.interfaz;

import java.util.List;

import dto.HistorialPasoDTO;

public interface HistorialPasoDAO {
	public List<HistorialPasoDTO> readAll();

	public boolean insert(HistorialPasoDTO pasoHistorial);

}
