package persistencia.dao.interfaz;

import java.util.List;

import dto.CajaDTO;
import dto.EmpleadoDTO;
import dto.MotivoEgresoDTO;

public interface MotivoEgresoDAO {
	public boolean insert(MotivoEgresoDTO m);
	
	public List<MotivoEgresoDTO> readAll();
}
