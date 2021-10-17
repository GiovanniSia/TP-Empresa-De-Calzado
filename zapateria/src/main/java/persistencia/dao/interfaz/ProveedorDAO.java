package persistencia.dao.interfaz;

import java.util.List;

import dto.ProveedorDTO;

public interface ProveedorDAO {
	
	public boolean insert(ProveedorDTO proveedor);
	
	public List<ProveedorDTO> readAll();
}
