package persistencia.dao.interfaz;

import java.util.List;

import dto.ProductoDTO;

public interface ProductoDAO {

	public boolean insert(ProductoDTO persona);

	public boolean delete(ProductoDTO persona_a_eliminar);

	public List<ProductoDTO> readAll();
}
