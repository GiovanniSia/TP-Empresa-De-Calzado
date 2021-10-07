package persistencia.dao.interfaz;

public interface DAOAbstractFactory {
	
	public ClienteDAO createClienteDAO();
	
	public EmpleadoDAO createEmpleadoDAO();
	
	public SucursalDAO createSucursalDAO();

	
	public HistorialCambioMProductoDAO createHistorialCambioMProductoDAO();
	
	public MaestroProductoDAO createMaestroProductoDAO();
	public StockDAO createStockDAO();
	public FacturaDAO createFacturaDAO();
	
	public OrdenFabricaDAO createOrdenFabricaDAO();
	
	public DetalleCarritoDAO createDetalleCarritoDAO();
	
	public CarritoDAO createCarritoDAO();
}
