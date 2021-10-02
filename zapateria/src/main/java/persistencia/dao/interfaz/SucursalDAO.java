package persistencia.dao.interfaz;

import java.util.List;

import dto.SucursalDTO;


public interface SucursalDAO {

	public boolean insert(SucursalDTO sucursal);

	public boolean delete(SucursalDTO sucursal_a_eliminar);
	
	public boolean update(int id_sucursal_a_actualizar, SucursalDTO sucursal_nueva);

	public List<SucursalDTO> readAll();
}
