package persistencia.dao.interfaz;

import java.util.List;

import dto.ProductoDeProveedorDTO;

public interface ProductoDeProveedorDAO {

	public boolean insert(ProductoDeProveedorDTO producto);
	
	public List<ProductoDeProveedorDTO> readAll();
}
