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

		this.ventanaHistorialCambioMoneda.getTextFiltroFecha().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				realizarBusqueda();
			}
		});
	}

	public void realizarBusqueda() {
		this.ventanaHistorialCambioMoneda.getModelHistorialCambioMoneda().setRowCount(0);// borrar datos de la tabla
		this.ventanaHistorialCambioMoneda.getModelHistorialCambioMoneda().setColumnCount(0);
		this.ventanaHistorialCambioMoneda.getModelHistorialCambioMoneda()
				.setColumnIdentifiers(this.ventanaHistorialCambioMoneda.getNombreColumnas());

		String txtcodMoneda = this.ventanaHistorialCambioMoneda.getTextFiltroCodMoneda().getText();
		String txtDescripcion = this.ventanaHistorialCambioMoneda.getTextFiltroDescripcion().getText();
		String txtFecha = this.ventanaHistorialCambioMoneda.getTextFiltroFecha().getText();

		List<HistorialCambioMonedaDTO> HistorialCambioMonedaAproximados = this.historialCambioMoneda
				.getHistorialCambioMonedaAproximado("IdMoneda", txtcodMoneda, "Descripcion", txtDescripcion, "Fecha",
						txtFecha);
		llenarTabla(HistorialCambioMonedaAproximados);
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
			String idMoneda = hcm.getIdMoneda();
			String descripcion = hcm.getDescripcion();
			int idEmpleado = hcm.getIdEmpleado();

			String fecha = hcm.getFecha();
			String hora = hcm.getHora();

			double tasaConversionAntigua = hcm.getTasaConversionAntigua();
			double tasaConversionNueva = hcm.getTasaConversionNueva();

			Object[] fila = { idMoneda, descripcion, idEmpleado, fecha, hora, tasaConversionAntigua,
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
