package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
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
	}

	public void inicializar() {
		this.historialCambioMoneda = new HistorialCambioMoneda(new DAOSQLFactory());

		this.ventanaHistorialCambioMoneda = new VentanaHistorialCambioMoneda();

		// Botones
		this.ventanaHistorialCambioMoneda.getBtnVolverAModificarConversion()
				.addActionListener(v -> volverAModificarConversion(v));

		// TextFiltos
		this.ventanaHistorialCambioMoneda.getTextFiltroDescripcion().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				historialCambioMonedaEnTabla = historialCambioMoneda.getHistorialCambioMonedaAproximado("Descripcion",
						ventanaHistorialCambioMoneda.getTextFiltroDescripcion().getText(), null, null);
				llenarTabla(historialCambioMonedaEnTabla);
			}
		});

		this.ventanaHistorialCambioMoneda.getTextFiltroFecha().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				historialCambioMonedaEnTabla = historialCambioMoneda.getHistorialCambioMonedaAproximado("Fecha",
						ventanaHistorialCambioMoneda.getTextFiltroFecha().getText(), null, null);
				llenarTabla(historialCambioMonedaEnTabla);
			}
		});
	}

	public void volverAModificarConversion(ActionEvent e) {
		this.ventanaHistorialCambioMoneda.cerrar();
	}

	public void mostrarVentana() {
		this.ventanaHistorialCambioMoneda.show();
		this.mostrarMProductosEnTabla();
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
//			int idCambioMoneda = hcm.getIdCambioMoneda();
			String idMoneda = hcm.getIdMoneda();
			String descripcion = hcm.getDescripcion();
			int idEmpleado = hcm.getIdEmpleado();
			
			String fecha = hcm.getFecha();
			double tasaConversionAntigua = hcm.getTasaConversionAntigua();
			double tasaConversionNueva = hcm.getTasaConversionNueva();

			Object[] fila = { idMoneda, descripcion, idEmpleado, fecha, tasaConversionAntigua,
					tasaConversionNueva };
			this.ventanaHistorialCambioMoneda.getModelHistorialCambioMoneda().addRow(fila);
		}
	}

	public static void main(String[] args) {
		HistorialCambioMoneda modelo = new HistorialCambioMoneda(new DAOSQLFactory());
		ControladorHistorialCambioCotizacion controlador = new ControladorHistorialCambioCotizacion(modelo);
		controlador.inicializar();
		controlador.mostrarVentana();
	}
}
