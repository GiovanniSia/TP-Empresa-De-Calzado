package persistencia.dao.interfaz;

public interface DAOAbstractFactory {
	public MaestroProductoDAO createMaestroProductoDAO();
	public StockDAO createStockDAO();
	public FacturaDAO createFacturaDAO();
}
