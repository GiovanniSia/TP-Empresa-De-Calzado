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

import dto.HistorialCambioMProductoDTO;
import dto.MaestroProductoDTO;
import modelo.HistorialCambioMProducto;
import modelo.MaestroProducto;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.vista.VentanaModificarMProducto;

public class ControladorModificarMProducto {
	static final int idEmpleado = 1;
	
	private VentanaModificarMProducto ventanaModificarMProducto;
	private MaestroProducto maestroProducto;
	private HistorialCambioMProducto historialCambioMProducto;
	private List<MaestroProductoDTO> maestroProductoEnTabla;

	public ControladorModificarMProducto(MaestroProducto producto) {
		this.ventanaModificarMProducto = new VentanaModificarMProducto();
		this.maestroProducto = producto;
	}

	public void inicializar() {
		this.maestroProducto = new MaestroProducto(new DAOSQLFactory());
		this.historialCambioMProducto = new HistorialCambioMProducto(new DAOSQLFactory());

		this.ventanaModificarMProducto = new VentanaModificarMProducto();

		// Botones
		this.ventanaModificarMProducto.getBtnAtras().addActionListener(a -> atras(a));
		this.ventanaModificarMProducto.getBtnActualizarProducto().addActionListener(c -> actualizarProducto(c));
		this.ventanaModificarMProducto.getBtnVerHistorialDeCambios().addActionListener(v -> verHistorialDeCambios(v));

		// Tabla
		this.ventanaModificarMProducto.getTablaProducto().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int filaSeleccionada = ventanaModificarMProducto.getTablaProducto().rowAtPoint(e.getPoint());
				JTable tablaProducto = ventanaModificarMProducto.getTablaProducto();
				ventanaModificarMProducto.getLblActualizarDescripcion()
						.setText(tablaProducto.getValueAt(filaSeleccionada, 1).toString());
				ventanaModificarMProducto.getTxtActualizarPrecioCosto()
						.setText(tablaProducto.getValueAt(filaSeleccionada, 3).toString());
				ventanaModificarMProducto.getTxtActualizarPrecioMayorista()
						.setText(tablaProducto.getValueAt(filaSeleccionada, 4).toString());
				ventanaModificarMProducto.getTxtActualizarPrecioMinorista()
						.setText(tablaProducto.getValueAt(filaSeleccionada, 5).toString());
				ventanaModificarMProducto.getSpinnerPuntoRepositorio()
						.setValue(tablaProducto.getValueAt(filaSeleccionada, 6));
				ventanaModificarMProducto.getSpinnerCantidadAReponer()
						.setValue(tablaProducto.getValueAt(filaSeleccionada, 7));
				ventanaModificarMProducto.getSpinnerDiasParaReponer()
						.setValue(tablaProducto.getValueAt(filaSeleccionada, 8));

			}
		});

		// TextFiltos
		this.ventanaModificarMProducto.getTxtFiltroCodProducto().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				maestroProductoEnTabla = maestroProducto.getMaestroProductoAproximado("IdMaestroProducto",
						ventanaModificarMProducto.getTxtFiltroCodProducto().getText(), null, null,null,0,0);
				llenarTabla(maestroProductoEnTabla);
			}
		});

		this.ventanaModificarMProducto.getTxtFiltroDescripcion().addKeyListener(new KeyAdapter() {
			@Override
//			String nombreColumna1, String txtAprox1,String nombreColumna2, String txtAprox2,String nombreColumna3,int precioDesde, int precioHasta
			public void keyReleased(KeyEvent e) {
				maestroProductoEnTabla = maestroProducto.getMaestroProductoAproximado("Descripcion",
						ventanaModificarMProducto.getTxtFiltroDescripcion().getText(), null, null,null,0,0);
				llenarTabla(maestroProductoEnTabla);
			}
		});

		this.ventanaModificarMProducto.getTxtFiltroTalle().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				maestroProductoEnTabla = maestroProducto.getMaestroProductoAproximado("Talle",
						ventanaModificarMProducto.getTxtFiltroTalle().getText(), null, null,null,0,0);
				llenarTabla(maestroProductoEnTabla);
			}
		});
	}

	public void atras(ActionEvent a) {
		this.ventanaModificarMProducto.cerrar();
	}

	// Abrir ventana para ver la tabla historialCambioMProducto
	public void verHistorialDeCambios(ActionEvent v) {
		HistorialCambioMProducto modelo = new HistorialCambioMProducto(new DAOSQLFactory());
		ControladorHistorialCambioMProducto controlador = new ControladorHistorialCambioMProducto(modelo);
		controlador.inicializar();
		controlador.mostrarVentana();
	}

	// Se actualizar la tabla maestroProducto y historialCambioMProducto
	public void actualizarProducto(ActionEvent p) {
		if (validarCampos()) {
			ingresarProductoATablaHistorialCambioMProducto();
			actualizarTablaMaestroProducto();
			limpiarCampos();
			refrescarTabla();
		}
	}

	public boolean validarCampos() {
		//Valido si selecciono un producto
		
		int filaSeleccionada = this.ventanaModificarMProducto.getTablaProducto().getSelectedRow();
		if (filaSeleccionada == -1) {
			JOptionPane.showMessageDialog(null, "Seleccione un producto");
			return false;
		}			
		
		// Valido si ingreso letras en vez de numeros o no incluyo el .
		
		String precioCostoNuevo = this.ventanaModificarMProducto.getTxtActualizarPrecioCosto().getText();
		String precioMayoristaNuevo = this.ventanaModificarMProducto.getTxtActualizarPrecioMayorista().getText();
		String precioMinoristaNuevo = this.ventanaModificarMProducto.getTxtActualizarPrecioMinorista().getText();
		
		if (!validarFormatoPrecio(precioCostoNuevo)) {
			JOptionPane.showMessageDialog(null, "Precio Costo tiene formato invalido. Ej: 50.99");
			return false;
		}
		if (!validarFormatoPrecio(precioMayoristaNuevo)) {
			JOptionPane.showMessageDialog(null, "Precio Mayorista tiene formato invalido. Ej: 50.99");
			return false;
		}
		if (!validarFormatoPrecio(precioMinoristaNuevo)) {
			JOptionPane.showMessageDialog(null, "Precio Minorista tiene formato invalido. Ej: 50.99");
			return false;
		}
		
		//Valido si ingreso campos negativos
		
		double precioCosto = Double.parseDouble(this.ventanaModificarMProducto.getTxtActualizarPrecioCosto().getText());
		double precioMayorista = Double
				.parseDouble(this.ventanaModificarMProducto.getTxtActualizarPrecioMayorista().getText());
		double precioMinorista = Double
				.parseDouble(this.ventanaModificarMProducto.getTxtActualizarPrecioMinorista().getText());
		int puntoRepositorio = (int) this.ventanaModificarMProducto.getSpinnerPuntoRepositorio().getValue();
		int cantidadAReponer = (int) this.ventanaModificarMProducto.getSpinnerCantidadAReponer().getValue();
		int diasParaReponer = (int) this.ventanaModificarMProducto.getSpinnerDiasParaReponer().getValue();
		
		if (!(precioCosto > -1 && precioMayorista > -1 && precioMinorista > -1 && puntoRepositorio > -1
				&& cantidadAReponer > -1 && diasParaReponer > -1)) {
			JOptionPane.showMessageDialog(null, "Los valores negativos no estan permitidos");
			return false;
		}
		return true;
	}

	public boolean validarFormatoPrecio(String precio) {
		boolean expresion = precio.matches("^-?\\d{1,11}\\.\\d{1,2}");
		if (!expresion) {
			return false;
		}
		return true;
	}

	public void actualizarTablaMaestroProducto() {
		int filaSeleccionada = this.ventanaModificarMProducto.getTablaProducto().getSelectedRow();
		int idModificar = this.maestroProductoEnTabla.get(filaSeleccionada).getIdMaestroProducto();
		MaestroProductoDTO datosNuevos = obtenerMaestroProductoNuevo();
		maestroProducto.update(idModificar, datosNuevos);
	}

	public void ingresarProductoATablaHistorialCambioMProducto() {
		int filaSeleccionada = this.ventanaModificarMProducto.getTablaProducto().getSelectedRow();

		// CodProducto
		int idMaestroProducto = this.maestroProductoEnTabla.get(filaSeleccionada).getIdMaestroProducto();

		// Fecha de hoy
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");

		String fecha = dtf.format(LocalDateTime.now());

		// Datos antiguos
		double precioCostoAntiguo = this.maestroProductoEnTabla.get(filaSeleccionada).getPrecioCosto();
		double precioMayoristaAntiguo = this.maestroProductoEnTabla.get(filaSeleccionada).getPrecioMayorista();
		double precioMinoristaAntiguo = this.maestroProductoEnTabla.get(filaSeleccionada).getPrecioMinorista();

		// Datos nuevos
		double precioCostoNuevo = Double
				.parseDouble(this.ventanaModificarMProducto.getTxtActualizarPrecioCosto().getText());
		double precioMayoristaNuevo = Double
				.parseDouble(this.ventanaModificarMProducto.getTxtActualizarPrecioMayorista().getText());
		double precioMinoristaNuevo = Double
				.parseDouble(this.ventanaModificarMProducto.getTxtActualizarPrecioMinorista().getText());

		HistorialCambioMProductoDTO nuevoHistorial = new HistorialCambioMProductoDTO(0, idEmpleado, idMaestroProducto,
				fecha, precioCostoAntiguo, precioCostoNuevo, precioMayoristaAntiguo, precioMayoristaNuevo,
				precioMinoristaAntiguo, precioMinoristaNuevo);

		this.historialCambioMProducto.insert(nuevoHistorial);

	}

	public MaestroProductoDTO obtenerMaestroProductoNuevo() {

		int filaSeleccionada = this.ventanaModificarMProducto.getTablaProducto().getSelectedRow();

		int idMaestroProducto = this.maestroProductoEnTabla.get(filaSeleccionada).getIdMaestroProducto();
		String descripcion = this.maestroProductoEnTabla.get(filaSeleccionada).getDescripcion();
		String tipo = this.maestroProductoEnTabla.get(filaSeleccionada).getTipo();
		String fabricado = this.maestroProductoEnTabla.get(filaSeleccionada).getFabricado();

		double precioCosto = Double.parseDouble(this.ventanaModificarMProducto.getTxtActualizarPrecioCosto().getText());
		double precioMayorista = Double
				.parseDouble(this.ventanaModificarMProducto.getTxtActualizarPrecioMayorista().getText());
		double precioMinorista = Double
				.parseDouble(this.ventanaModificarMProducto.getTxtActualizarPrecioMinorista().getText());
		int puntoRepositorio = (int) this.ventanaModificarMProducto.getSpinnerPuntoRepositorio().getValue();
		int cantidadAReponer = (int) this.ventanaModificarMProducto.getSpinnerCantidadAReponer().getValue();
		int diasParaReponer = (int) this.ventanaModificarMProducto.getSpinnerDiasParaReponer().getValue();

		int idProveedor = this.maestroProductoEnTabla.get(filaSeleccionada).getIdProveedor();
		String talle = this.maestroProductoEnTabla.get(filaSeleccionada).getTalle();
		int unidadMedida = this.maestroProductoEnTabla.get(filaSeleccionada).getUnidadMedida();
		String estado = this.maestroProductoEnTabla.get(filaSeleccionada).getEstado();

		return new MaestroProductoDTO(idMaestroProducto, descripcion, tipo, fabricado, precioCosto, precioMayorista,
				precioMinorista, puntoRepositorio, idProveedor, talle, unidadMedida, estado, cantidadAReponer,
				diasParaReponer);
	}

	public void limpiarCampos() {
		this.ventanaModificarMProducto.limpiarCampos();
	}

	public MaestroProductoDTO obtenerMaestroProductoSeleccionado() {
		int filaSeleccionada = this.ventanaModificarMProducto.getTablaProducto().getSelectedRow();

		List<MaestroProductoDTO> maestroProducto = this.maestroProducto.readAll();

		String codProducto = this.ventanaModificarMProducto.getTablaProducto().getValueAt(filaSeleccionada, 0)
				.toString();
		for (MaestroProductoDTO mp : maestroProducto) {
			if (codProducto.equals("" + mp.getIdMaestroProducto() + "")) {
				return mp;
			}
		}
		return null;
	}

	public void mostrarVentana() {
		this.ventanaModificarMProducto.show();
		this.refrescarTabla();
	}

	public void refrescarTabla() {
		this.maestroProductoEnTabla = maestroProducto.readAll();
		this.llenarTabla(maestroProductoEnTabla);
	}

	public void llenarTabla(List<MaestroProductoDTO> mProductoEnTabla) {
		this.ventanaModificarMProducto.getModelProducto().setRowCount(0);
		this.ventanaModificarMProducto.getModelProducto().setColumnCount(0);
		this.ventanaModificarMProducto.getModelProducto()
				.setColumnIdentifiers(this.ventanaModificarMProducto.getNombreColumnas());
		for (MaestroProductoDTO mp : mProductoEnTabla) {

			int codigo = mp.getIdMaestroProducto();
			String descripcion = mp.getDescripcion();
			String talle = mp.getTalle();
			double precioCosto = mp.getPrecioCosto();
			double precioMayorista = mp.getPrecioMayorista();
			double precioMinorista = mp.getPrecioMinorista();
			int puntoRepositorio = mp.getPuntoRepositorio();
			int cantidadAReponer = mp.getCantidadAReponer();
			int diasParaReponer = mp.getDiasParaReponer();

			Object[] fila = { codigo, descripcion, talle, precioCosto, precioMayorista, precioMinorista,
					puntoRepositorio, cantidadAReponer, diasParaReponer };
			this.ventanaModificarMProducto.getModelProducto().addRow(fila);

		}
	}

	public static void main(String[] args) {
		MaestroProducto modelo = new MaestroProducto(new DAOSQLFactory());
		ControladorModificarMProducto controlador = new ControladorModificarMProducto(modelo);
		controlador.inicializar();
		controlador.mostrarVentana();
	}

}
