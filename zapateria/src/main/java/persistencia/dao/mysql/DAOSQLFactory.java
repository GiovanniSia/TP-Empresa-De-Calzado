/**
 * 
 */
package persistencia.dao.mysql;

import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.MaestroProductoDAO;
import persistencia.dao.interfaz.StockDAO;
import persistencia.dao.interfaz.FacturaDAO;

public class DAOSQLFactory implements DAOAbstractFactory {

	public MaestroProductoDAO createMaestroProductoDAO() {
		return new MaestroProductoDAOSQL();
	}
	
	public StockDAO createStockDAO() {
		return new StockDAOSQL();
	}
	
	public FacturaDAO createFacturaDAO() {
		return new FacturaDAOSQL();
	}

}
