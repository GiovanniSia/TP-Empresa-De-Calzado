package presentacion.controlador.Cajero;

import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.TableColumn;

import dto.EgresosDTO;
import dto.IngresosDTO;
import modelo.Egresos;
import modelo.HistorialCambioMoneda;
import modelo.Ingresos;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.controlador.ControladorHistorialCambioCotizacion;
import presentacion.vista.Cajero.VentanaCierreCajaEclipse;

public class ControladorCierreCajaEclipse {

	private VentanaCierreCajaEclipse ventanaCierreCaja;
	
	private Ingresos ingresos;
	private List<IngresosDTO> ingresosEnTabla;
	
	private Egresos egresos;
	private List<EgresosDTO> egresosEnTabla;
	

	public ControladorCierreCajaEclipse(Ingresos ingresos, Egresos egresos) {
		this.ventanaCierreCaja = new VentanaCierreCajaEclipse();
		this.ingresos = ingresos;
		this.egresos = egresos;
	}

	public void inicializar() {
		this.ingresos = new Ingresos(new DAOSQLFactory());
		this.egresos= new Egresos(new DAOSQLFactory());
		
		this.ventanaCierreCaja = new VentanaCierreCajaEclipse();

		// Botones
		this.ventanaCierreCaja.getBtnAtras().addActionListener(a -> atras(a));
		this.ventanaCierreCaja.getBtnCerrarCajaDeSucursal().addActionListener(c -> cerrarCaja(c));

		
		llenarTablaIngresos();
		llenarTablaEgresos();
		
		llenarTablaMedioPagoIngresos();
		
	}

	public void atras(ActionEvent a) {
		this.ventanaCierreCaja.cerrar();
	}
	
	public void cerrarCaja(ActionEvent a) {
		this.ventanaCierreCaja.cerrar();
	}

	public void verHistorialDeCambios(ActionEvent v) {
		HistorialCambioMoneda modelo = new HistorialCambioMoneda(new DAOSQLFactory());
		ControladorHistorialCambioCotizacion controlador = new ControladorHistorialCambioCotizacion(modelo);
		controlador.inicializar();
		controlador.mostrarVentana();
	}

	public void mostrarVentana() {
		this.ventanaCierreCaja.show();
	}
	
	//RELLENAR POR FECHA DE HOY
	public List<IngresosDTO> obtenerIngresosDeHoy() {
		return ingresos.readAll();
	}

	public void llenarTablaIngresos() {
		this.ingresosEnTabla = obtenerIngresosDeHoy();
		this.ventanaCierreCaja.getModelIngresos().setRowCount(0);
		this.ventanaCierreCaja.getModelIngresos().setColumnCount(0);
		this.ventanaCierreCaja.getModelIngresos()
				.setColumnIdentifiers(this.ventanaCierreCaja.getNombreColumnasIngresos());
		for (IngresosDTO i : ingresosEnTabla) {
			String hora = i.getHora();
			String tipo = i.getTipo();
			int idCliente = i.getIdCliente();
			String tipoFactura = i.getTipoFactura();
			String medioPago = i.getMedioPago();
			double mont = i.getCantidad();
			BigDecimal monto = new BigDecimal(mont);
			double cotizacio = i.getCotizacion();
			BigDecimal cotizacion = new BigDecimal(cotizacio); 
			String nroOperacio = i.getOperacion();
			BigDecimal nroOperacion = new BigDecimal(nroOperacio);
			double tota = i.getTotal();
			BigDecimal total = new BigDecimal(tota);
			
			Object[] fila = { hora, tipo, idCliente, tipoFactura, medioPago, monto,cotizacion,nroOperacion,total};
			this.ventanaCierreCaja.getModelIngresos().addRow(fila);
		}
	}
	
	public void llenarTablaMedioPagoIngresos() {

		
		Object[] fila = {"a","s",123};
		
		this.ventanaCierreCaja.getModelMedioPagoIngresos().addRow(fila);
	}
	
	
	public void agregarCheckBox(int columna, JTable table) {
		TableColumn tc = table.getColumnModel().getColumn(columna);
		tc.setCellEditor(table.getDefaultEditor(Boolean.class));
		tc.setCellRenderer(table.getDefaultRenderer(Boolean.class));
	}
	
	
	//RELLENAR POR FECHA DE HOY
	public List<EgresosDTO> obtenerEgresosDeHoy(){
		return egresos.readAll();
	}
	
	public void llenarTablaEgresos() {
		this.egresosEnTabla = obtenerEgresosDeHoy();
		this.ventanaCierreCaja.getModelEgresos().setRowCount(0);
		this.ventanaCierreCaja.getModelEgresos().setColumnCount(0);
		this.ventanaCierreCaja.getModelEgresos()
				.setColumnIdentifiers(this.ventanaCierreCaja.getNombreColumnasEgresos());

		for (EgresosDTO e : egresosEnTabla) {
			String hora = e.getHora();
			String tipo = e.getTipo();
			String detalle = e.getDetalle();
			String medioPago = e.getMedioPago();
			double tota = e.getTotal();
			BigDecimal total = new BigDecimal(tota);
			
			Object[] fila = { hora, tipo, detalle, medioPago, total};
			this.ventanaCierreCaja.getModelEgresos().addRow(fila);
		}
	}

	public static void main(String[] args) {
		
		Ingresos ingresos = new Ingresos(new DAOSQLFactory());
		Egresos egresos = new Egresos(new DAOSQLFactory());
		ControladorCierreCajaEclipse controlador = new ControladorCierreCajaEclipse(ingresos,egresos);
		controlador.inicializar();
		controlador.mostrarVentana();
	}
	
}