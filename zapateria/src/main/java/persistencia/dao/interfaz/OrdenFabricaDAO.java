package persistencia.dao.interfaz;

import java.util.List;

import dto.OrdenFabricaDTO;

public interface OrdenFabricaDAO {

	public boolean insert(OrdenFabricaDTO sucursal);

	public List<OrdenFabricaDTO> readAll();
	
	public boolean hayOrdenPendiente(int idProducto, int idSucursal);
}
