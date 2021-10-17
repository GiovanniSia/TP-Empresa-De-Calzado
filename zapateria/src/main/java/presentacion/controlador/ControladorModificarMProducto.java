package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
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
	static final String idEmpleado = "1";

	private VentanaModificarMProducto ventanaModificarMProducto;
	private MaestroProducto maestroProducto;
	private HistorialCambioMProducto historialCambioMProducto;
	private List<MaestroProductoDTO> maestroProductoEnTabla;

	Controlador controlador;

	public static void main(String[] args) {
		ControladorModificarMProducto a = new ControladorModificarMProducto();
		a.inicializar();
		a.mostrarVentana();
	}

	public ControladorModificarMProducto() {
		this.ventanaModificarMProducto = new VentanaModificarMProducto();
		this.maestroProducto = new MaestroProducto(new DAOSQLFactory());
	}

	public ControladorModificarMProducto(Controlador controlador, MaestroProducto producto) {
		this.ventanaModificarMProducto = new VentanaModificarMProducto();
		this.maestroProducto = producto;
		this.controlador = controlador;
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
				rellenarCamposDeModificacionUnitaria();
			}
		});

		// TextFiltos
		this.ventanaModificarMProducto.getTxtFiltroCodProducto().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				realizarBusqueda();
			}
		});

		this.ventanaModificarMProducto.getTxtFiltroDescripcion().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				realizarBusqueda();
			}
		});

		this.ventanaModificarMProducto.getTxtFiltroTalle().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				realizarBusqueda();
			}
		});

		this.ventanaModificarMProducto.getTxtFiltroProveedor().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				realizarBusqueda();
			}
		});
	}
	
	public void rellenarCamposDeModificacionUnitaria() {
		int filaSeleccionada = this.ventanaModificarMProducto.getTablaProducto().getSelectedRow();
		JTable tablaProducto = ventanaModificarMProducto.getTablaProducto();
		ventanaModificarMProducto.getLblActualizarDescripcion()
				.setText(tablaProducto.getValueAt(filaSeleccionada, 1).toString());
		ventanaModificarMProducto.getTxtActualizarPrecioCosto()
				.setText(tablaProducto.getValueAt(filaSeleccionada, 4).toString());
		ventanaModificarMProducto.getTxtActualizarPrecioMayorista()
				.setText(tablaProducto.getValueAt(filaSeleccionada, 5).toString());
		ventanaModificarMProducto.getTxtActualizarPrecioMinorista()
				.setText(tablaProducto.getValueAt(filaSeleccionada, 6).toString());
		ventanaModificarMProducto.getTxtActualizarPuntoRepositorio()
				.setText(tablaProducto.getValueAt(filaSeleccionada, 7).toString());
		ventanaModificarMProducto.getTxtActualizarCantidadAReponer()
				.setText(tablaProducto.getValueAt(filaSeleccionada, 8).toString());
		ventanaModificarMProducto.getTxtActualizarDiasParaResponder()
				.setText(tablaProducto.getValueAt(filaSeleccionada, 9).toString());
	}

	public void realizarBusqueda() {
		this.ventanaModificarMProducto.getModelProducto().setRowCount(0);// borrar datos de la tabla
		this.ventanaModificarMProducto.getModelProducto().setColumnCount(0);
		this.ventanaModificarMProducto.getModelProducto()
				.setColumnIdentifiers(this.ventanaModificarMProducto.getNombreColumnas());

		String txtCodProducto = this.ventanaModificarMProducto.getTxtFiltroCodProducto().getText();
		String txtDescripcion = this.ventanaModificarMProducto.getTxtFiltroDescripcion().getText();
		String txtTalle = this.ventanaModificarMProducto.getTxtFiltroTalle().getText();
		String txtProveedor = this.ventanaModificarMProducto.getTxtFiltroProveedor().getText();

		List<MaestroProductoDTO> ProductosAproximados = this.maestroProducto.getFiltroModificarMProdcto(
				"IdMaestroProducto", txtCodProducto, "Descripcion", txtDescripcion, "Talle", txtTalle, "IdProveedor",
				txtProveedor);
		llenarTabla(ProductosAproximados);
	}

	public void atras(ActionEvent a) {
		this.ventanaModificarMProducto.cerrar();
		this.controlador.inicializar();
		this.controlador.mostrarVentanaMenuDeSistemas();
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
		// Valido si selecciono un producto

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
			JOptionPane.showMessageDialog(null, "Precio Costo tiene formato invalido. Ej: 50 o 50.99");
			return false;
		}

		if (!validarFormatoPrecio(precioMayoristaNuevo)) {
			JOptionPane.showMessageDialog(null, "Precio Mayorista tiene formato invalido. Ej: 50 o 50.99");
			return false;
		}

		if (!validarFormatoPrecio(precioMinoristaNuevo)) {
			JOptionPane.showMessageDialog(null, "Precio Minorista tiene formato invalido. Ej: 50 o 50.99");
			return false;
		}

		// Valido si ingreso campos negativos

		double precioCosto = Double.parseDouble(this.ventanaModificarMProducto.getTxtActualizarPrecioCosto().getText());
		double precioMayorista = Double
				.parseDouble(this.ventanaModificarMProducto.getTxtActualizarPrecioMayorista().getText());
		double precioMinorista = Double
				.parseDouble(this.ventanaModificarMProducto.getTxtActualizarPrecioMinorista().getText());
		int puntoRepositorio = Integer
				.parseInt(this.ventanaModificarMProducto.getTxtActualizarPuntoRepositorio().getText());
		int cantidadAReponer = Integer
				.parseInt(this.ventanaModificarMProducto.getTxtActualizarCantidadAReponer().getText());
		int diasParaReponer = Integer
				.parseInt(this.ventanaModificarMProducto.getTxtActualizarDiasParaResponder().getText());

		if (!(precioCosto > -1 && precioMayorista > -1 && precioMinorista > -1 && puntoRepositorio > -1
				&& cantidadAReponer > -1 && diasParaReponer > -1)) {
			JOptionPane.showMessageDialog(null, "Los valores negativos no estan permitidos");
			return false;
		}

		if ((precioCosto == 0 || precioMayorista == 0 || precioMinorista == 0)) {
			JOptionPane.showMessageDialog(null, "Los precios no pueden tener valor cero");
			return false;
		}

		return true;
	}

	public boolean validarFormatoPrecio(String precio) {
		boolean expresion = precio.matches("^[0-9]+(\\.[0-9]{1,2})?$");
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

	public boolean seModificaronPrecios() {

		// Verificar si hay precios iguales
		int filaSeleccionada = this.ventanaModificarMProducto.getTablaProducto().getSelectedRow();
		double precioCostoAntiguo = this.maestroProductoEnTabla.get(filaSeleccionada).getPrecioCosto();
		double precioMayoristaAntiguo = this.maestroProductoEnTabla.get(filaSeleccionada).getPrecioMayorista();
		double precioMinoristaAntiguo = this.maestroProductoEnTabla.get(filaSeleccionada).getPrecioMinorista();
		double precioCosto = Double.parseDouble(this.ventanaModificarMProducto.getTxtActualizarPrecioCosto().getText());
		double precioMayorista = Double
				.parseDouble(this.ventanaModificarMProducto.getTxtActualizarPrecioMayorista().getText());
		double precioMinorista = Double
				.parseDouble(this.ventanaModificarMProducto.getTxtActualizarPrecioMinorista().getText());

		if (precioCosto == precioCostoAntiguo && precioMayorista == precioMayoristaAntiguo
				&& precioMinorista == precioMinoristaAntiguo) {
			JOptionPane.showMessageDialog(null, "No se guardara el cambio en el historial al no cambiar precios");
			return false;
		}
		return true;

	}

	public String obtenerFechaDeHoy() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		String fecha = dtf.format(LocalDateTime.now());
		return fecha;
	}

	public void ingresarProductoATablaHistorialCambioMProducto() {
		if (seModificaronPrecios()) {
			int filaSeleccionada = this.ventanaModificarMProducto.getTablaProducto().getSelectedRow();

			String idMaestroProducto = "" + this.maestroProductoEnTabla.get(filaSeleccionada).getIdMaestroProducto();
			String fecha = obtenerFechaDeHoy();

			String precioCostoAntiguo = "" + this.maestroProductoEnTabla.get(filaSeleccionada).getPrecioCosto();
			String precioCostoNuevo = this.ventanaModificarMProducto.getTxtActualizarPrecioCosto().getText();

			String precioMayoristaAntiguo = "" + this.maestroProductoEnTabla.get(filaSeleccionada).getPrecioMayorista();
			String precioMayoristaNuevo = this.ventanaModificarMProducto.getTxtActualizarPrecioMayorista().getText();

			String precioMinoristaAntiguo = "" + this.maestroProductoEnTabla.get(filaSeleccionada).getPrecioMinorista();
			String precioMinoristaNuevo = this.ventanaModificarMProducto.getTxtActualizarPrecioMinorista().getText();

			String PuntoRepositorioAntiguo = ""
					+ this.maestroProductoEnTabla.get(filaSeleccionada).getPuntoRepositorio();
			String PuntoRepositorioNuevo = ""
					+ this.ventanaModificarMProducto.getTxtActualizarPuntoRepositorio().getText();

			String CantidadAReponerAntiguo = ""
					+ this.maestroProductoEnTabla.get(filaSeleccionada).getCantidadAReponer();
			String CantidadAReponerNuevo = ""
					+ this.ventanaModificarMProducto.getTxtActualizarCantidadAReponer().getText();

			String DiasParaReponerAntiguo = "" + this.maestroProductoEnTabla.get(filaSeleccionada).getDiasParaReponer();
			String DiasParaReponerNuevo = ""
					+ this.ventanaModificarMProducto.getTxtActualizarDiasParaResponder().getText();

			HistorialCambioMProductoDTO nuevoHistorial = new HistorialCambioMProductoDTO(0, idEmpleado,
					idMaestroProducto, fecha, precioCostoAntiguo, precioCostoNuevo, precioMayoristaAntiguo,
					precioMayoristaNuevo, precioMinoristaAntiguo, precioMinoristaNuevo, PuntoRepositorioAntiguo,
					PuntoRepositorioNuevo, CantidadAReponerAntiguo, CantidadAReponerNuevo, DiasParaReponerAntiguo,
					DiasParaReponerNuevo);

			this.historialCambioMProducto.insert(nuevoHistorial);
		}

	}

	public MaestroProductoDTO obtenerMaestroProductoNuevo() {

		int filaSeleccionada = this.ventanaModificarMProducto.getTablaProducto().getSelectedRow();

		int idMaestroProducto = this.maestroProductoEnTabla.get(filaSeleccionada).getIdMaestroProducto();
		String descripcion = this.maestroProductoEnTabla.get(filaSeleccionada).getDescripcion();
		String tipo = this.maestroProductoEnTabla.get(filaSeleccionada).getTipo();
		String fabricado = this.maestroProductoEnTabla.get(filaSeleccionada).getFabricado();

		double precioCosto = Double.parseDouble(
				"" + new BigDecimal(this.ventanaModificarMProducto.getTxtActualizarPrecioCosto().getText()));

		double precioMayorista = Double.parseDouble(
				"" + new BigDecimal(this.ventanaModificarMProducto.getTxtActualizarPrecioMayorista().getText()));

		double precioMinorista = Double.parseDouble(
				"" + new BigDecimal(this.ventanaModificarMProducto.getTxtActualizarPrecioMinorista().getText()));

		int puntoRepositorio = Integer
				.parseInt(this.ventanaModificarMProducto.getTxtActualizarPuntoRepositorio().getText());
		int cantidadAReponer = Integer
				.parseInt(this.ventanaModificarMProducto.getTxtActualizarCantidadAReponer().getText());
		int diasParaReponer = Integer
				.parseInt(this.ventanaModificarMProducto.getTxtActualizarDiasParaResponder().getText());

		int idProveedor = this.maestroProductoEnTabla.get(filaSeleccionada).getIdProveedor();
		String talle = this.maestroProductoEnTabla.get(filaSeleccionada).getTalle();
		String unidadMedida = this.maestroProductoEnTabla.get(filaSeleccionada).getUnidadMedida();
		String estado = this.maestroProductoEnTabla.get(filaSeleccionada).getEstado();

		return new MaestroProductoDTO(idMaestroProducto, descripcion, tipo, fabricado, precioCosto, precioMayorista,
				precioMinorista, puntoRepositorio, idProveedor, talle, unidadMedida, estado, cantidadAReponer,
				diasParaReponer);
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

	public void limpiarCampos() {
		this.ventanaModificarMProducto.limpiarCampos();
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
			int idProveedor = mp.getIdProveedor();
			String talle = mp.getTalle();
			double precioCost = mp.getPrecioCosto();
			BigDecimal precioCosto = new BigDecimal("" + precioCost + "");

			double precioMayorist = mp.getPrecioMayorista();
			BigDecimal precioMayorista = new BigDecimal("" + precioMayorist + "");

			double precioMinorist = mp.getPrecioMinorista();
			BigDecimal precioMinorista = new BigDecimal("" + precioMinorist + "");

			int puntoRepositorio = mp.getPuntoRepositorio();
			int cantidadAReponer = mp.getCantidadAReponer();
			int diasParaReponer = mp.getDiasParaReponer();

			Object[] fila = { codigo, descripcion, idProveedor, talle, precioCosto, precioMayorista, precioMinorista,
					puntoRepositorio, cantidadAReponer, diasParaReponer };
			this.ventanaModificarMProducto.getModelProducto().addRow(fila);

		}
	}
}
