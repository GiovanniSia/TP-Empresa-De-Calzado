package presentacion.controlador.fabrica;

import java.util.ArrayList;
import java.util.List;

import javax.swing.UIManager;

import dto.HistorialPasoDTO;
import dto.MaestroProductoDTO;
import dto.OrdenFabricaDTO;
import dto.SucursalDTO;
import modelo.Fabricacion;
import modelo.HistorialPaso;
import modelo.MaestroProducto;
import modelo.OrdenFabrica;
import modelo.Stock;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.controlador.Controlador;
import presentacion.vista.fabrica.VentanaVerHistorialPasos;

public class ControladorHistorialPasos {
	
	List<HistorialPasoDTO> historialEnLista;
	
	Controlador controlador;
	SucursalDTO fabrica;
	
	HistorialPaso modelosHistorialPaso;
	
	VentanaVerHistorialPasos ventana;
	
	OrdenFabrica modeloOrden;
	Fabricacion modeloFabricacion;
	MaestroProducto modeloProducto;
	
	//public ControladorHistorialPasos(Controlador controlador,SucursalDTO fabrica) {
	public ControladorHistorialPasos(SucursalDTO fabrica) {
		//this.controlador = controlador;
		this.fabrica = fabrica;
		this.modeloFabricacion =  new Fabricacion(new DAOSQLFactory());
		modeloProducto = new MaestroProducto(new DAOSQLFactory());
		
		modeloOrden = new OrdenFabrica(new DAOSQLFactory());
		historialEnLista = new ArrayList<HistorialPasoDTO>();
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch(Exception e) {
            System.out.println("Error setting native LAF: " + e);
		}
		/*
		modeloFabricacion = new Fabricacion(new DAOSQLFactory());
		modeloProducto = new MaestroProducto(new DAOSQLFactory());
		modeloOrden = new OrdenFabrica(new DAOSQLFactory());
		modeloStock = new Stock(new DAOSQLFactory());
		 */
		modelosHistorialPaso = new HistorialPaso(new DAOSQLFactory());
		ventana = new VentanaVerHistorialPasos();
	}
	
	public void inicializar() {
		refrescarTabla();
		mostrarVentana();
	}
	
	private void refrescarTabla() {
		vaciarTabla();
		historialEnLista = recuperarTodoElHistorial();
		llenarTablaConElHistorial();
	}

	private void vaciarTabla() {
		ventana.getModelOrdenes().setRowCount(0);
		ventana.getModelOrdenes().setColumnCount(0);
		ventana.getModelOrdenes().setColumnIdentifiers(ventana.getNombreColumnas());
		
		ventana.getTablaFabricacionesEnMarcha().getColumnModel().getColumn(0).setPreferredWidth(5);
		ventana.getTablaFabricacionesEnMarcha().getColumnModel().getColumn(1).setPreferredWidth(5);
		ventana.getTablaFabricacionesEnMarcha().getColumnModel().getColumn(3).setPreferredWidth(6);
	}
	
	private void llenarTablaConElHistorial() {
		for(HistorialPasoDTO hp: historialEnLista) {
			int nroOrden = hp.getIdOrden();
			OrdenFabricaDTO ordenTrabajandoLocal = getOrdenFabricacion(nroOrden);
			MaestroProductoDTO productoTrabajandoLocal = getMaestroProducto(ordenTrabajandoLocal.getIdProd());
			
			String nroOrdenM = ordenTrabajandoLocal.getIdOrdenFabrica()+"";
			String idSucursal = ordenTrabajandoLocal.getIdSucursal()+"";
			String producto = productoTrabajandoLocal.getIdMaestroProducto()+", "+productoTrabajandoLocal.getDescripcion()+", "+
					productoTrabajandoLocal.getTalle();
			String cantidad = ordenTrabajandoLocal.getCantidad()+"";
			String accion = hp.getDescrPasoCompletado();
			String detalle = hp.getDescr();
			String idEmpleado = hp.getIdEmpleado()+"";
			String nombreEmpleado = hp.getNombreCompleto();
			String fechaHora = hp.getFecha()+" "+hp.getHora();
			//Object[] agregar = {hp.getId(), hp.getDescrPasoCompletado()};
			//{ "Nro orden","Sucursal", "Producto", "Cantidad", "Accion", "Detalle", "Fecha y hora","Empleado"};
			Object[] agregar = {nroOrdenM, idSucursal, producto, cantidad, accion, detalle, fechaHora, idEmpleado+", "+nombreEmpleado};
			ventana.getModelOrdenes().addRow(agregar);
		}
	}
	
	private OrdenFabricaDTO getOrdenFabricacion(int idOrden) {
		OrdenFabricaDTO ret = null;
		for(OrdenFabricaDTO of: modeloOrden.readAll()) {
			if(of.getIdOrdenFabrica() == idOrden) {
				ret = of;
			}
		}
		return ret;
	}
	
	private MaestroProductoDTO getMaestroProducto(int idProducto) {
		List<MaestroProductoDTO> todosLosProductos = modeloProducto.readAll();
		for(MaestroProductoDTO mp: todosLosProductos) {
			if(mp.getIdMaestroProducto() == idProducto) {
				return mp;
			}
		}
		return null;
	}
	
	private List<HistorialPasoDTO> recuperarTodoElHistorial(){
		List<HistorialPasoDTO> historial = modelosHistorialPaso.readAll();
		return historial;
	}
	
	private void mostrarVentana() {
		ventana.mostrarVentana();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SucursalDTO suc = new SucursalDTO(1, "3123314", "Uruguay", "134", "BsAs", "Tortuguitas",
				"Argentina", "1669", "Una mas");
		ControladorHistorialPasos con = new ControladorHistorialPasos(suc);
		con.inicializar();

	}

}
