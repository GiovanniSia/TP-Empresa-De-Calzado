package modelo.compraVirtual;

import java.util.List;

import dto.CompraVirtualDTO;
import dto.RechazoCompraVirtualDTO;
import dto.RechazoCompraVirtualDetalleDTO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.RechazoCompraVirtualDAO;

public class RechazoCompraVirtual {
	
	private RechazoCompraVirtualDAO rechazo;
	
	public RechazoCompraVirtual(DAOAbstractFactory metodo_persistencia) {
		rechazo = metodo_persistencia.createRechazoCompraVirtualDAO();
	}
	
	public List<RechazoCompraVirtualDTO> readAllRechazosComprasVirtuales(){
		return rechazo.readAllRechazosComprasVirtuales();
	}
	
	public boolean insertRechazoCompraVirtualDAOSQL(CompraVirtualDTO compra, String motivo) {
		return rechazo.insertRechazoCompraVirtualDAOSQL(compra, motivo);
	}
	
	public boolean insertDetalleRechazoCompraVirtual(RechazoCompraVirtualDetalleDTO detalle) {
		return rechazo.insertDetalleRechazoCompraVirtual(detalle);
	}
	
	public List<RechazoCompraVirtualDetalleDTO> readAllDetallesDeUnRechazoCompraVirtual(int IdRechazoCompraVirtual){
		return rechazo.readAllDetallesDeUnRechazoCompraVirtual(IdRechazoCompraVirtual);
	}

}
