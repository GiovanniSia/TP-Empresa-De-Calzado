package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import dto.HistorialCambioMonedaDTO;
import dto.MedioPagoDTO;
import modelo.HistorialCambioMoneda;
import modelo.MedioPago;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.vista.VentanaModificarCotizacion;

public class ControladorModificarCotizacion {

	static final int idEmpleado = 1;

	private VentanaModificarCotizacion ventanaModificarCotizacion;
	private MedioPago medioPago;
	private HistorialCambioMoneda historialCambioMoneda;
	private List<MedioPagoDTO> medioPagoEnTabla;
	
	public ControladorModificarCotizacion(MedioPago medioPago) {
		this.ventanaModificarCotizacion = new VentanaModificarCotizacion();
		this.medioPago = medioPago;
	}

	public void inicializar() {
		this.medioPago = new MedioPago(new DAOSQLFactory());
		this.historialCambioMoneda = new HistorialCambioMoneda(new DAOSQLFactory());

		this.ventanaModificarCotizacion = new VentanaModificarCotizacion();

		// Botones
		this.ventanaModificarCotizacion.getBtnAtras().addActionListener(a -> atras(a));
		this.ventanaModificarCotizacion.getBtnActualizarCotizacion().addActionListener(c -> actualizarMedioPago(c));
		this.ventanaModificarCotizacion.getBtnVerHistorialDeCambios().addActionListener(v -> verHistorialDeCambios(v));

		// Tabla
		this.ventanaModificarCotizacion.getTablaMedioPago().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int filaSeleccionada = ventanaModificarCotizacion.getTablaMedioPago().rowAtPoint(e.getPoint());
				JTable tablaMedioPago = ventanaModificarCotizacion.getTablaMedioPago();
				ventanaModificarCotizacion.getTxtActualizarTasaConvercion()
						.setText(tablaMedioPago.getValueAt(filaSeleccionada, 2).toString());
				ventanaModificarCotizacion.getLblActualizarDescripcion()
						.setText(tablaMedioPago.getValueAt(filaSeleccionada, 1).toString());
			}
		});

		// TextFiltos
		this.ventanaModificarCotizacion.getTxtFiltroDescripcion().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				medioPagoEnTabla = medioPago.getMedioPagoAproximado("Descripcion",
						ventanaModificarCotizacion.getTxtFiltroDescripcion().getText(), null, null);
				llenarTabla(medioPagoEnTabla);
			}
		});
	}

	public void atras(ActionEvent a) {
		this.ventanaModificarCotizacion.cerrar();
	}

	public void verHistorialDeCambios(ActionEvent v) {
		HistorialCambioMoneda modelo = new HistorialCambioMoneda(new DAOSQLFactory());
		ControladorHistorialCambioCotizacion controlador = new ControladorHistorialCambioCotizacion(modelo);
		controlador.inicializar();
		controlador.mostrarVentana();
	}

	public void actualizarMedioPago(ActionEvent p) {
		if (validarCampos()) {
			ingresarProductoATablaHistorialCambioMProducto();
			actualizarTablaMaestroProducto();
			limpiarCampos();
			refrescarTabla();
		}
	}

	public boolean validarCampos() {
		// Valido si selecciono un producto

		int filaSeleccionada = this.ventanaModificarCotizacion.getTablaMedioPago().getSelectedRow();
		if (filaSeleccionada == -1) {
			JOptionPane.showMessageDialog(null, "Seleccione un medio de pago");
			return false;
		}

		// Valido si ingreso letras en vez de numeros o no incluyo el .
		String tasaConversionNueva = this.ventanaModificarCotizacion.getTxtActualizarTasaConvercion().getText();

		if (!validarFormatoTasaConversion(tasaConversionNueva)) {
			JOptionPane.showMessageDialog(null, "Formato invalido. Ej: 50.99");
			return false;
		}

		// Valido si ingreso campo negativo

		double tasaConversion = Double
				.parseDouble(this.ventanaModificarCotizacion.getTxtActualizarTasaConvercion().getText());

		if (!(tasaConversion > -1)) {
			JOptionPane.showMessageDialog(null, "No se permiten negativos");
			return false;
		}

		return true;
	}

	public boolean validarFormatoTasaConversion(String precio) {
		boolean expresion = precio.matches("^-?\\d{1,11}\\.\\d{1,2}");
		if (!expresion) {
			return false;
		}
		return true;
	}

	public void actualizarTablaMaestroProducto() {
		int filaSeleccionada = this.ventanaModificarCotizacion.getTablaMedioPago().getSelectedRow();
		String idModificar = this.medioPagoEnTabla.get(filaSeleccionada).getIdMoneda();
		MedioPagoDTO datosNuevos = obtenerMedioPagoNuevo();
		medioPago.update(idModificar, datosNuevos);
	}

	public void ingresarProductoATablaHistorialCambioMProducto() {
		int filaSeleccionada = this.ventanaModificarCotizacion.getTablaMedioPago().getSelectedRow();

		int idCambioMoneda = 0;
		String idMoneda = this.medioPagoEnTabla.get(filaSeleccionada).getIdMoneda();
		String descripcion = this.medioPagoEnTabla.get(filaSeleccionada).getDescripcion();

		// Fecha de hoy
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		String fecha = dtf.format(LocalDateTime.now());

		double tasaConversionAntigua = this.medioPagoEnTabla.get(filaSeleccionada).getTasaConversion();
		double tasaConversionNueva = Double
				.parseDouble(this.ventanaModificarCotizacion.getTxtActualizarTasaConvercion().getText());

		HistorialCambioMonedaDTO nuevoHistorial = new HistorialCambioMonedaDTO(idCambioMoneda, idMoneda, descripcion,
				idEmpleado, fecha, tasaConversionAntigua, tasaConversionNueva);

		this.historialCambioMoneda.insert(nuevoHistorial);
	}

	public MedioPagoDTO obtenerMedioPagoNuevo() {
		int filaSeleccionada = this.ventanaModificarCotizacion.getTablaMedioPago().getSelectedRow();

		String idMoneda = this.medioPagoEnTabla.get(filaSeleccionada).getIdMoneda();
		String descripcion = this.medioPagoEnTabla.get(filaSeleccionada).getDescripcion();
		double tasaConversion = Double
				.parseDouble(this.ventanaModificarCotizacion.getTxtActualizarTasaConvercion().getText());
		return new MedioPagoDTO(idMoneda, descripcion, tasaConversion);
	}

	public void limpiarCampos() {
		this.ventanaModificarCotizacion.limpiarCampos();
	}

	public MedioPagoDTO obtenerMedioPagoSeleccionado() {
		int filaSeleccionada = this.ventanaModificarCotizacion.getTablaMedioPago().getSelectedRow();

		List<MedioPagoDTO> medioPago = this.medioPago.readAll();

		String codProducto = this.ventanaModificarCotizacion.getTablaMedioPago().getValueAt(filaSeleccionada, 0)
				.toString();
		for (MedioPagoDTO mp : medioPago) {
			if (codProducto.equals("" + mp.getIdMoneda() + "")) {
				return mp;
			}
		}
		return null;
	}

	public void mostrarVentana() {
		this.ventanaModificarCotizacion.show();
		this.refrescarTabla();
	}

	public void refrescarTabla() {
		this.medioPagoEnTabla = medioPago.readAll();
		this.llenarTabla(medioPagoEnTabla);
	}

	public void llenarTabla(List<MedioPagoDTO> monedaEnTabla) {
		this.ventanaModificarCotizacion.getModelProducto().setRowCount(0);
		this.ventanaModificarCotizacion.getModelProducto().setColumnCount(0);
		this.ventanaModificarCotizacion.getModelProducto()
				.setColumnIdentifiers(this.ventanaModificarCotizacion.getNombreColumnas());
		for (MedioPagoDTO m : monedaEnTabla) {
			String idMoneda = m.getIdMoneda();
			String descripcion = m.getDescripcion();
			double tasaConversion = m.getTasaConversion();

			Object[] fila = { idMoneda, descripcion, tasaConversion };
			this.ventanaModificarCotizacion.getModelProducto().addRow(fila);
		}
	}

	public static void main(String[] args) {
		MedioPago modelo = new MedioPago(new DAOSQLFactory());
		ControladorModificarCotizacion controlador = new ControladorModificarCotizacion(modelo);
		controlador.inicializar();
		controlador.mostrarVentana();
	}

}
