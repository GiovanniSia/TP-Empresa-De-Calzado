package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import dto.HistorialCambioMonedaDTO;
import modelo.HistorialCambioMoneda;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.vista.VentanaHistorialCambioMoneda;

public class ControladorHistorialCambioCotizacion {
	private VentanaHistorialCambioMoneda ventanaHistorialCambioMoneda;
	private HistorialCambioMoneda historialCambioMoneda;
	private List<HistorialCambioMonedaDTO> historialCambioMonedaEnTabla;

	public ControladorHistorialCambioCotizacion(HistorialCambioMoneda historialCambioMoneda) {
		this.ventanaHistorialCambioMoneda = new VentanaHistorialCambioMoneda();
		this.historialCambioMoneda = historialCambioMoneda;
		this.historialCambioMoneda = new HistorialCambioMoneda(new DAOSQLFactory());
	}

	public void inicializar() {

		// Botones
		this.ventanaHistorialCambioMoneda.getBtnVolverAModificarConversion()
				.addActionListener(v -> volverAModificarConversion(v));

		this.ventanaHistorialCambioMoneda.getBtnReiniciarTabla().addActionListener(r -> reiniciarTabla(r));
		// TextFiltos
		this.ventanaHistorialCambioMoneda.getTextFiltroCodMoneda().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				realizarBusqueda();
			}
		});

		// TextFiltos
		this.ventanaHistorialCambioMoneda.getTextFiltroDescripcion().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				realizarBusqueda();
			}
		});
		this.ventanaHistorialCambioMoneda.getBtnFiltrarFechas().addActionListener(a -> btnFiltrarFecha(a));

	}

	public void btnFiltrarFecha(ActionEvent f) {
		realizarBusqueda();
		this.ventanaHistorialCambioMoneda.limpiarCampos();
	}
	
	private void reiniciarTabla(ActionEvent r) {
		this.ventanaHistorialCambioMoneda.limpiarCampos();
		refrescarDatosTabla();
	}

	public void realizarBusqueda() {
		this.ventanaHistorialCambioMoneda.getModelHistorialCambioMoneda().setRowCount(0);// borrar datos de la tabla
		this.ventanaHistorialCambioMoneda.getModelHistorialCambioMoneda().setColumnCount(0);
		this.ventanaHistorialCambioMoneda.getModelHistorialCambioMoneda()
				.setColumnIdentifiers(this.ventanaHistorialCambioMoneda.getNombreColumnas());

		String txtcodMoneda = this.ventanaHistorialCambioMoneda.getTextFiltroCodMoneda().getText();
		String txtDescripcion = this.ventanaHistorialCambioMoneda.getTextFiltroDescripcion().getText();
		String fechaDesde = fechaDesde();
		String fechaHasta = fechaHasta();
		
		if(verificarFechas()) {
			JOptionPane.showMessageDialog(null, "Fecha Hasta no puede ser inferior a Fecha Desde");
			refrescarDatosTabla();
			return;
		}

		List<HistorialCambioMonedaDTO> HistorialCambioMonedaAproximados = this.historialCambioMoneda
				.getHistorialCambioMonedaAproximado("IdMoneda", txtcodMoneda, "Descripcion", txtDescripcion, "Fecha",
						fechaDesde, fechaHasta);

		llenarTabla(HistorialCambioMonedaAproximados);
	}

	public String fechaDesde() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date fechaDesde = this.ventanaHistorialCambioMoneda.getFechaDesde().getDate();

		String fechaDesdeFormato = null;
		if (fechaDesde != null) {
			fechaDesdeFormato = dateFormat.format(fechaDesde);
		}
		return fechaDesdeFormato;
	}

	public String fechaHasta() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date fechaHasta = this.ventanaHistorialCambioMoneda.getFechaHasta().getDate();
		String fechaHastaFormato = null;
		if (fechaHasta != null) {
			fechaHastaFormato = dateFormat.format(fechaHasta);
		}
		return fechaHastaFormato;
	}

	public boolean verificarFechas() {
		Date fechaDesde = this.ventanaHistorialCambioMoneda.getFechaDesde().getDate();
		Date fechaHasta = this.ventanaHistorialCambioMoneda.getFechaHasta().getDate();
		if(fechaDesde != null && fechaHasta !=null) {
			if (fechaDesde.after(fechaHasta)) {
				return true;
			}			
		}
		return false;
	}

	public void volverAModificarConversion(ActionEvent e) {
		this.ventanaHistorialCambioMoneda.cerrar();
	}

	public void mostrarVentana() {
		this.ventanaHistorialCambioMoneda.show();
		this.mostrarMProductosEnTabla();
	}

	public void ocultarVentana() {
		this.ventanaHistorialCambioMoneda.cerrar();
	}
	
	public void mostrarMProductosEnTabla() {
		this.historialCambioMonedaEnTabla = historialCambioMoneda.readAll();
		this.llenarTabla(historialCambioMonedaEnTabla);
	}

	public void llenarTabla(List<HistorialCambioMonedaDTO> historialCambioMonedaEnTabla) {
		this.ventanaHistorialCambioMoneda.getModelHistorialCambioMoneda().setRowCount(0);
		this.ventanaHistorialCambioMoneda.getModelHistorialCambioMoneda().setColumnCount(0);
		this.ventanaHistorialCambioMoneda.getModelHistorialCambioMoneda()
				.setColumnIdentifiers(this.ventanaHistorialCambioMoneda.getNombreColumnas());

		for (HistorialCambioMonedaDTO hcm : historialCambioMonedaEnTabla) {
			String idMoneda = hcm.getIdMoneda();
			String descripcion = hcm.getDescripcion();
			int idEmpleado = hcm.getIdEmpleado();
			String fecha = hcm.getFecha();
			String hora = hcm.getHora();
			double tasaConversionAntigu = hcm.getTasaConversionAntigua();
			BigDecimal tasaConversionAntigua = new BigDecimal(tasaConversionAntigu);
			double tasaConversionNuev = hcm.getTasaConversionNueva();
			BigDecimal tasaConversionNueva = new BigDecimal(tasaConversionNuev);

			Object[] fila = { idMoneda, descripcion, idEmpleado, fecha, hora, tasaConversionAntigua,
					tasaConversionNueva };
			this.ventanaHistorialCambioMoneda.getModelHistorialCambioMoneda().addRow(fila);
		}
	}
	
	public void refrescarDatosTabla() {
		this.historialCambioMonedaEnTabla = historialCambioMoneda.readAll();
		this.llenarTabla(historialCambioMonedaEnTabla);
	}

}
