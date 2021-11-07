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

	public MedioPagoEgresoDAO createMedioPagoEgresoDAO();

	public HistorialCambioMonedaDAO createHistorialCambioMonedaDAO();

	public FabricacionDAO createFabricacionDAO();

	public IngresosDAO createIngresosDAO();

	public EgresosDAO createEgresosDAO();

	public DetalleCarritoDAO createDetalleCarritoDAO();

	public CarritoDAO createCarritoDAO();

	public CajaDAO createCajaDAO();
	
	public TipoEgresosDAO createTipoEgresosDAO();
	
	public ProveedorDAO createProveedorDAO();
	
	public ProductoDeProveedorDAO createProductoDeProveedorDAO();
	
	public HistorialPasoDAO createHistorialPasoDAO();
	
	public PedidosPendientesDAO createPedidosPendientesDAO();
	
	public RechazoCompraVirtualDAO createRechazoCompraVirtualDAO();
	
	public PaisDAO createPaisDAO();
	public ProvinciaDAO createProvinciaDAO();
	public LocalidadDAO createLocalidadDAO();
	
	public MotivoEgresoDAO createMotivoEgresoDAO();
	
	public ModeloPasoDAO createModeloPasoDAO();
	
	public RecetaDAO createModeloRecetaDAO();
	
	public HistorialCambioClienteDAO createHistorialCambioClienteDAO();

	public HistorialCambioEmpleadoDAO createHistorialCambioEmpleadoDAO();
	
}
