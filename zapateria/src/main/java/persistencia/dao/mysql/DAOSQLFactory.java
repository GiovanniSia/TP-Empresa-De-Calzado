/**
 * 
 */
package persistencia.dao.mysql;

import persistencia.dao.interfaz.CarritoDAO;
import persistencia.dao.interfaz.ClienteDAO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.EgresosDAO;
import persistencia.dao.interfaz.EmpleadoDAO;
import persistencia.dao.interfaz.FabricacionDAO;
import persistencia.dao.interfaz.SucursalDAO;
import persistencia.dao.interfaz.MaestroProductoDAO;
import persistencia.dao.interfaz.MedioPagoDAO;
import persistencia.dao.interfaz.MedioPagoEgresoDAO;
import persistencia.dao.interfaz.OrdenFabricaDAO;
import persistencia.dao.interfaz.DetalleCarritoDAO;
import persistencia.dao.interfaz.StockDAO;
import persistencia.dao.interfaz.FacturaDAO;
import persistencia.dao.interfaz.HistorialCambioMProductoDAO;
import persistencia.dao.interfaz.HistorialCambioMonedaDAO;
import persistencia.dao.interfaz.IngresosDAO;

public class DAOSQLFactory implements DAOAbstractFactory {

	@Override
	public HistorialCambioMProductoDAO createHistorialCambioMProductoDAO() {
		return new HistorialCambioMProductoDAOSQL();
	}

	public MaestroProductoDAO createMaestroProductoDAO() {
		return new MaestroProductoDAOSQL();
	}

	public StockDAO createStockDAO() {
		return new StockDAOSQL();
	}

	public FacturaDAO createFacturaDAO() {
		return new FacturaDAOSQL();
	}

	@Override
	public ClienteDAO createClienteDAO() {
		return new ClienteDAOSQL();
	}

	@Override
	public EmpleadoDAO createEmpleadoDAO() {
		return new EmpleadoDAOSQL();
	}

	@Override
	public SucursalDAO createSucursalDAO() {
		return new SucursalDAOSQL();
	}

	@Override
	public OrdenFabricaDAO createOrdenFabricaDAO() {
		return new OrdenFabricaDAOSQL();
	}

	public MedioPagoDAO createMedioPagoDAO() {
		return new MedioPagoDAOSQL();
	}

	public MedioPagoEgresoDAO createMedioPagoEgresoDAO() {
		return new MedioPagoEgresoDAOSQL();
	}

	public HistorialCambioMonedaDAO createHistorialCambioMonedaDAO() {
		return new HistorialCambioMonedaDAOSQL();
	}

	public FabricacionDAO createFabricacionDAO() {
		return new FabricacionDAOSQL();
	}

	public IngresosDAO createIngresosDAO() {
		return new IngresosDAOSQL();
	}

	public EgresosDAO createEgresosDAO() {
		return new EgresosDAOSQL();
	}

	@Override
	public DetalleCarritoDAO createDetalleCarritoDAO() {
		return new DetalleCarritoDAOSQL();
	}

	@Override
	public CarritoDAO createCarritoDAO() {
		return new CarritoDAOSQL();
	}

}
