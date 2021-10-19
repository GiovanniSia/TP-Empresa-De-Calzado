package presentacion.controlador.reporteRanking;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTextPane;
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
import presentacion.reportes.ReporteRankingXSucursal;
import presentacion.vista.fabrica.VentanaVerDetalle;
import presentacion.vista.fabrica.VentanaVerHistorialPasos;
import presentacion.vista.reporteRanking.VentanaVerReporteRankingXSucursal;

public class ControladorReporteRankingVentaXSucursal {

	Controlador controlador;
	SucursalDTO fabrica;

	OrdenFabrica modeloOrden;
	Fabricacion modeloFabricacion;
	MaestroProducto modeloProducto;
	
	VentanaVerReporteRankingXSucursal ventana;

	// public ControladorHistorialPasos(Controlador controlador)
	// {
	public ControladorReporteRankingVentaXSucursal() {
		// this.controlador = controlador;
		this.fabrica = fabrica;
		this.modeloFabricacion = new Fabricacion(new DAOSQLFactory());
		modeloProducto = new MaestroProducto(new DAOSQLFactory());

		modeloOrden = new OrdenFabrica(new DAOSQLFactory());
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Error setting native LAF: " + e);
		}
		/*
		 * modeloFabricacion = new Fabricacion(new DAOSQLFactory()); modeloProducto =
		 * new MaestroProducto(new DAOSQLFactory()); modeloOrden = new OrdenFabrica(new
		 * DAOSQLFactory()); modeloStock = new Stock(new DAOSQLFactory());
		 */
		ventana = new VentanaVerReporteRankingXSucursal();
		ventana.getBtnVerDescripcion().addActionListener(r->mostrarReporte(r));
	}

	private void mostrarReporte(ActionEvent r) {
		String fechaDesde = getFechaDesde();
		String fechaHasta = fechaHasta();
		
		//DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY-mm-DD"); 
        //String fechaHoy = dtf.format(LocalDateTime.now());
		
		String fechaHoy = generarFechaActual();
		if(fechaDesde == null) {
			fechaDesde = fechaHoy;
		}
		if(fechaHasta == null) {
			fechaHasta = fechaHoy;
		}
		ReporteRankingXSucursal reporteRanking = new ReporteRankingXSucursal(fechaDesde,fechaHasta);
		reporteRanking.mostrarReporte();
	}

	public void inicializar() {
		mostrarVentana();
	}

	private void mostrarVentana() {
		ventana.mostrarVentana();
	}

	private void mostrarMensajeEmergente(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje);
		return;
	}
	
	private String getFechaDesde() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date fechaDesde = this.ventana.getFechaDesde().getDate();
		String fechaDesdeFormato = null;
		if (fechaDesde != null) {
			fechaDesdeFormato = dateFormat.format(fechaDesde);
		}
		return fechaDesdeFormato;
	}
	
	private String fechaHasta() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date fechaHasta = this.ventana.getFechaHasta().getDate();
		String fechaHastaFormato = null;
		if (fechaHasta != null) {
			fechaHastaFormato = dateFormat.format(fechaHasta);
		}
		return fechaHastaFormato;
	}
	
	private String generarFechaActual() {
		java.util.Date fecha = new java.util.Date();
		int diaCompletado = fecha.getDate();
		int mesCompletado = fecha.getMonth() + 1;
		int anioCompletado = fecha.getYear() + 1900;
		String fecha1 = anioCompletado + "-" + mesCompletado + "-" + diaCompletado;
		return fecha1;
	}
	
	public static void main(String[] args) {
		ControladorReporteRankingVentaXSucursal controladorH = new ControladorReporteRankingVentaXSucursal();
		controladorH.inicializar();
	}
}
