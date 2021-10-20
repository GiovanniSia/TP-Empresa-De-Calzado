package persistencia.dao.interfaz;

import java.util.List;

import dto.CajaDTO;
import dto.CompraVirtualDTO;
import dto.EmpleadoDTO;
import dto.RechazoCompraVirtualDTO;

public interface RechazoCompraVirtualDAO {
	
	public List<RechazoCompraVirtualDTO> readAllRechazosComprasVirtuales();
	
	public boolean insertRechazoCompraVirtualDAOSQL(CompraVirtualDTO compra, String motivo);
}
