package persistencia.dao.interfaz;

public interface DAOAbstractFactory {

	public ClienteDAO createClienteDAO();

	public EmpleadoDAO createEmpleadoDAO();

	public SucursalDAO createSucursalDAO();

	public HistorialCambioMProductoDAO createHistorialCambioMProductoDAO();

	public MaestroProductoDAO createMaestroProductoDAO();

	public StockDAO createStockDAO();

	public FacturaDAO createFacturaDAO();

	public DetalleFacturaDAO createDetalleFacturaDAO();
	
	public OrdenFabricaDAO createOrdenFabricaDAO();
	
	public MedioPagoDAO createMedioPagoDAO();
	
	public HistorialCambioMonedaDAO createHistorialCambioMonedaDAO();
	public FabricacionDAO createFabricacionDAO();
	
	public IngresosDAO createIngresosDAO();
	
	public EgresosDAO createEgresosDAO();
	public DetalleCarritoDAO createDetalleCarritoDAO();
	
	public CarritoDAO createCarritoDAO();
}
