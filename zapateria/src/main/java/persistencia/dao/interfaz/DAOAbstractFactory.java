package persistencia.dao.interfaz;

public interface DAOAbstractFactory {
	
	public ClienteDAO createClienteDAO();
	
	public EmpleadoDAO createEmpleadoDAO();
	
	public SucursalDAO createSucursalDAO();

	
	public MaestroProductoDAO createMaestroProductoDAO();
	public StockDAO createStockDAO();
	public FacturaDAO createFacturaDAO();
}
