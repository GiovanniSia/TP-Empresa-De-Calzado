/**
 * 
 */
package persistencia.dao.mysql;

import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.PedidosPendientesDAO;
import persistencia.dao.interfaz.ProductoDeProveedorDAO;
import persistencia.dao.interfaz.ProveedorDAO;

public class DAOSQLFactory implements DAOAbstractFactory 
{

	@Override
	public PedidosPendientesDAO createPedidosPendientesDAO() {
		return new PedidosPendientesDAOSQL();
	}
	/* (non-Javadoc)
	 * @see persistencia.dao.interfaz.DAOAbstractFactory#createPersonaDAO()
	 */

	@Override
	public ProveedorDAO createProveedorDAO() {
		return new ProveedorDAOSQL();
	}
	@Override
	public ProductoDeProveedorDAO createProductoDeProveedorDAO() {
		return new ProductoDeProveedorDAOSQL();
	}

	
}
