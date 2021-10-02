/**
 * 
 */
package persistencia.dao.mysql;

import persistencia.dao.interfaz.ClienteDAO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.EmpleadoDAO;
import persistencia.dao.interfaz.ProductoDAO;

public class DAOSQLFactory implements DAOAbstractFactory {

	public ProductoDAO createProductoDAO() {
		return new ProductoDAOSQL();
	}

	@Override
	public ClienteDAO createClienteDAO() {
		return new ClienteDAOSQL();
	}

	@Override
	public EmpleadoDAO createEmpleadoDAO() {
		return new EmpleadoDAOSQL();
	}

}
