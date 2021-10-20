package modelo;

import java.util.List;

import dto.PedidosPendientesDTO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.PedidosPendientesDAO;

public class PedidosPendientes {
	private PedidosPendientesDAO pedido;
	
	public PedidosPendientes(DAOAbstractFactory metodo_presistencia) {
		this.pedido = metodo_presistencia.createPedidosPendientesDAO();
	}
	
	public boolean insert(PedidosPendientesDTO pedido) {
		return this.pedido.insert(pedido);
	}
	
	public boolean delete(PedidosPendientesDTO pedido) {
		return this.delete(pedido);
	}
	
	public boolean update(PedidosPendientesDTO nuevoPedido, int idPedido) {
		return this.pedido.update(nuevoPedido, idPedido);
	}
	
	public List<PedidosPendientesDTO> readAll(){
		return this.pedido.readAll();
	}
	
}
