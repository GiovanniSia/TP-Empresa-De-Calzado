/**
 * 
 */
package persistencia.dao.mysql;

import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.ProductoDAO;

public class DAOSQLFactory implements DAOAbstractFactory {

	public ProductoDAO createProductoDAO() {
		return new ProductoDAOSQL();
	}

}
