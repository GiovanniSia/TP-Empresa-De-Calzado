package persistencia.dao.interfaz;

import java.util.List;

import dto.ProvinciaDTO;

public interface ProvinciaDAO {
public boolean insert(ProvinciaDTO Provincia);
	
	public boolean delete(ProvinciaDTO Provincia_a_eliminar);
	
	public boolean edit(String nombreProvincia,ProvinciaDTO Provincia_a_editar);
	
	public List<ProvinciaDTO> readAll();
}
