package persistencia.dao.interfaz;

import java.util.List;

import dto.PasoDeRecetaDTO;
import dto.RecetaDTO;

public interface RecetaDAO {
	public boolean insertReceta(RecetaDTO receta);
	
	public boolean insertPasosReceta(List<PasoDeRecetaDTO> pasos);
	
	public boolean updateReceta(RecetaDTO receta, List<PasoDeRecetaDTO> pasos);
}
