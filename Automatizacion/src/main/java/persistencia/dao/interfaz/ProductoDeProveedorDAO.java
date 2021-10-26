package persistencia.dao.interfaz;

import java.util.List;

import dto.ProductoDeProveedorDTO;

public interface ProductoDeProveedorDAO {

	public boolean insert(ProductoDeProveedorDTO producto);
	
	public boolean delete(ProductoDeProveedorDTO producto);
	
	public List<ProductoDeProveedorDTO> readAll();
	
	public boolean updateCantidadPorLote(double cantidad,int id); 
}
