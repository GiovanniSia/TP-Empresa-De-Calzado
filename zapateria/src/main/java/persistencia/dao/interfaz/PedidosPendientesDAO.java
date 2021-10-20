package persistencia.dao.interfaz;

import java.util.List;

import dto.PedidosPendientesDTO;


public interface PedidosPendientesDAO {

	public boolean insert(PedidosPendientesDTO pedido);
	public boolean delete(PedidosPendientesDTO pedido);
	public boolean update(PedidosPendientesDTO nuevopedido,int idPedido);
	public List<PedidosPendientesDTO> readAll();
}
