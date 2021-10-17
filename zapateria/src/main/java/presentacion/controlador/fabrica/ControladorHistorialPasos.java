package presentacion.controlador.fabrica;

import java.util.ArrayList;
import java.util.List;

import javax.swing.UIManager;

import dto.HistorialPasoDTO;
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
	//public ControladorHistorialPasos(Controlador controlador,SucursalDTO fabrica) {
	
	HistorialPaso modelosHistorialPaso;
	
	VentanaVerHistorialPasos ventana;
	
	public ControladorHistorialPasos(SucursalDTO fabrica) {
		//this.controlador = controlador;
		this.fabrica = fabrica;
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
	}
	
	private void llenarTablaConElHistorial() {
		for(HistorialPasoDTO hp: historialEnLista) {
			Object[] agregar = {hp.getId(), hp.getDescrPasoCompletado()};
			ventana.getModelOrdenes().addRow(agregar);
		}
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
