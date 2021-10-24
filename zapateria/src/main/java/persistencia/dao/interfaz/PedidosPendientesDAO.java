package persistencia.dao.interfaz;

import java.util.List;

import dto.PedidosPendientesDTO;


public interface PedidosPendientesDAO {

	public boolean insert(PedidosPendientesDTO pedido);
	public boolean delete(PedidosPendientesDTO pedido);
	public boolean update(PedidosPendientesDTO nuevopedido,int idPedido);
	
	public boolean finalizarPedido(String nuevoEstado, String fechaCompleto, String HoraCompleto, int idPedido);
	
	public boolean cambiarEstado(int id, String estado);
	
	public boolean updateTotal(int idPedido, double nuevoPrecio);
	
	public List<PedidosPendientesDTO> readAll();
	
	public List<PedidosPendientesDTO> getPedidosPendientesFiltrados(String nombreColumna1, String txt1, String nombreColumna2, String txt2, String nombreColumna3,String txt3, String nombreColumna4,String txt4, String nombreColumna5,String txt5, String nombreColumna6,String txt6, String nombreColumna7,String txt7, String nombreColumna8,String txt8);
}
