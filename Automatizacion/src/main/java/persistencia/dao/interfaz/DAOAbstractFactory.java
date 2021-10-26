package persistencia.dao.interfaz;


public interface DAOAbstractFactory {
	
	public PedidosPendientesDAO createPedidosPendientesDAO();
	
	public ProveedorDAO createProveedorDAO();
	
	public ProductoDeProveedorDAO createProductoDeProveedorDAO();
}
