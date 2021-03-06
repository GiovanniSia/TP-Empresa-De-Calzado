package persistencia.dao.interfaz;

import java.util.List;

import dto.ProveedorDTO;

public interface ProveedorDAO {
	
	public boolean insert(ProveedorDTO proveedor);
	
	public List<ProveedorDTO> readAll();
	
	public boolean update(ProveedorDTO proveedor,int idProveedor);
	
	public List<ProveedorDTO> getProveedorAproximado(String nombreColumna,String txt);
}
