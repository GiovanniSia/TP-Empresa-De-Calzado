package persistencia.dao.interfaz;

import java.util.List;

import dto.MaestroProductoDTO;
import dto.OrdenFabricaDTO;

public interface OrdenFabricaDAO {

	public boolean insert(OrdenFabricaDTO sucursal);

	public List<OrdenFabricaDTO> readAll();
	
	public boolean hayOrdenPendiente(int idProducto, int idSucursal);
	
	public List<MaestroProductoDTO> leerProductos(String id, String descr, String talle);
}
