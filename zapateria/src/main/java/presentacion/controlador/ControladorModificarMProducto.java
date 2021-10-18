package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
	private List<MaestroProductoDTO> maestroProductoEnTablaProducto;

	private List<MaestroProductoDTO> maestroProductoEnTablaProductosModificar;

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
		maestroProductoEnTablaProductosModificar = new ArrayList<MaestroProductoDTO>();

		// Botones
		this.ventanaModificarMProducto.getBtnAtras().addActionListener(a -> atras(a));
		this.ventanaModificarMProducto.getBtnActualizarProducto().addActionListener(c -> actualizarProducto(c));
		this.ventanaModificarMProducto.getBtnVerHistorialDeCambios().addActionListener(v -> verHistorialDeCambios(v));

		this.ventanaModificarMProducto.getBtnAgregarProductoSeleccionado()
				.addActionListener(a -> agregarProductoSeleccionado(a));
		this.ventanaModificarMProducto.getBtnAgregarProductosEnTabla()
				.addActionListener(a -> agregarProductosEnTablaModificar(a));

		this.ventanaModificarMProducto.getBtnQuitarProductoSeleccionado()
				.addActionListener(a -> quitarProductoSeleccionado(a));
		this.ventanaModificarMProducto.getBtnLimpiarTabla().addActionListener(a -> limpiarTabla(a));

		this.ventanaModificarMProducto.getBtnActualizarMasivamente().addActionListener(a -> actualizarMasivamente(a));

		// Tabla Productos Modificar
		this.ventanaModificarMProducto.getTablaProductosModificar().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rellenarCamposDeModificacionUnitaria();
			}
		});

		// Filtro CodProducto
		this.ventanaModificarMProducto.getTxtFiltroCodProducto().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				realizarBusqueda();
			}
		});
		// Filtro Descripcion
		this.ventanaModificarMProducto.getTxtFiltroDescripcion().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				realizarBusqueda();
			}
		});
		// Filtro Talle
		this.ventanaModificarMProducto.getTxtFiltroTalle().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				realizarBusqueda();
			}
		});
		// Filtro Proveedor
		this.ventanaModificarMProducto.getTxtFiltroProveedor().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				realizarBusqueda();
			}
		});
	}

	public void actualizarMasivamente(ActionEvent a) {
		int aumentar = Integer.parseInt(this.ventanaModificarMProducto.getTxtActualizarAumentar().getText());
		int disminuir = Integer.parseInt(this.ventanaModificarMProducto.getTxtActualizarDisminuir().getText());
		if (aumentar == 0 && disminuir == 0) {
			JOptionPane.showMessageDialog(null, "Sin cambios");
			return;
		}
		System.out.println(maestroProductoEnTablaProductosModificar);
		for (MaestroProductoDTO productoTablaModificar : maestroProductoEnTablaProductosModificar) {
			System.out.println("Producto: " + productoTablaModificar.getIdMaestroProducto());
			Double precioCosto = productoTablaModificar.getPrecioCosto();
			Double precioMayorista = productoTablaModificar.getPrecioMayorista();
			Double precioMinorista = productoTablaModificar.getPrecioMinorista();

			Double precioCostoAumentado = aumentar * precioCosto / 100;
			Double precioMayoristaAumentado = aumentar * precioMayorista / 100;
			Double precioMinoristaAumentado = aumentar * precioMinorista / 100;

			Double precioCostoDisminuido = disminuir * precioCosto / 100;
			Double precioMayoristaDisminuido = disminuir * precioMayorista / 100;
			Double precioMinoristaDiminuido = disminuir * precioMinorista / 100;

			Double precioCostoFinal = precioCosto + precioCostoAumentado - precioCostoDisminuido;
			Double precioMayoristaFinal = precioMayorista + precioMayoristaAumentado - precioMayoristaDisminuido;
			Double precioMinoristaFinal = precioMinorista + precioMinoristaAumentado - precioMinoristaDiminuido;

			if (precioCostoFinal <= 0 || precioMayoristaFinal <= 0 || precioMinoristaFinal <= 0) {
				JOptionPane.showMessageDialog(null, "Actualizacion fallida, algunos precios dan negativos");
				return;
			}
			MaestroProductoDTO productoNuevo = obtenerMaestroProductoNuevo(productoTablaModificar, precioCostoFinal,
					precioMayoristaFinal, precioMinoristaFinal);
			
			
			
			this.ingresarProductosMasivosATablaHistorialCambioMProducto(productoTablaModificar, productoNuevo);			
			this.actualizacionMasivoTablaMaestroProducto(productoNuevo);	
		}
		this.refrescarTablaProducto();
		this.limpiarTablaMaestroProductosModificar();
	}

	public void actualizacionMasivoTablaMaestroProducto(MaestroProductoDTO productoNuevo) {
		int idModificar = productoNuevo.getIdMaestroProducto();
		maestroProducto.update(idModificar, productoNuevo);
	}

	public void limpiarTablaMaestroProductosModificar() {
		this.limpiarTabla(null);
	}

	public void ingresarProductosMasivosATablaHistorialCambioMProducto(MaestroProductoDTO productoAntiguo,
			MaestroProductoDTO productoNuevo) {

		String idMaestroProducto = "" + productoAntiguo.getIdMaestroProducto();
		String fecha = obtenerFechaDeHoy();

		String precioCostoAntiguo = "" + productoAntiguo.getPrecioCosto();
		String precioCostoNuevo = "" + productoNuevo.getPrecioCosto();

		String precioMayoristaAntiguo = "" + productoAntiguo.getPrecioMayorista();
		String precioMayoristaNuevo = "" + productoNuevo.getPrecioMayorista();

		String precioMinoristaAntiguo = "" + productoAntiguo.getPrecioMinorista();
		String precioMinoristaNuevo = "" + productoNuevo.getPrecioMinorista();

		String PuntoRepositorioAntiguo = "" + productoAntiguo.getPuntoRepositorio();
		String CantidadAReponerAntiguo = "" + productoAntiguo.getCantidadAReponer();
		String DiasParaReponerAntiguo = "" + productoAntiguo.getDiasParaReponer();

		HistorialCambioMProductoDTO nuevoHistorial = new HistorialCambioMProductoDTO(0, idEmpleado, idMaestroProducto,
				fecha, precioCostoAntiguo, precioCostoNuevo, precioMayoristaAntiguo, precioMayoristaNuevo,
				precioMinoristaAntiguo, precioMinoristaNuevo, PuntoRepositorioAntiguo, PuntoRepositorioAntiguo,
				CantidadAReponerAntiguo, CantidadAReponerAntiguo, DiasParaReponerAntiguo, DiasParaReponerAntiguo);

		this.historialCambioMProducto.insert(nuevoHistorial);

	}

	public MaestroProductoDTO obtenerMaestroProductoNuevo(MaestroProductoDTO productoAntiguo, Double precioCostoNuevo,
			Double precioMayoristaNuevo, Double precioMinoristaNuevo) {

		int idMaestroProducto = productoAntiguo.getIdMaestroProducto();
		String descripcion = productoAntiguo.getDescripcion();
		String tipo = productoAntiguo.getTipo();
		String fabricado = productoAntiguo.getFabricado();
		double precioCosto = precioCostoNuevo;
		double precioMayorista = precioMayoristaNuevo;
		double precioMinorista = precioMinoristaNuevo;
		int puntoRepositorio = productoAntiguo.getPuntoRepositorio();
		int cantidadAReponer = productoAntiguo.getCantidadAReponer();
		int diasParaReponer = productoAntiguo.getDiasParaReponer();
		int idProveedor = productoAntiguo.getIdProveedor();
		String talle = productoAntiguo.getTalle();
		String unidadMedida = productoAntiguo.getUnidadMedida();
		String estado = productoAntiguo.getEstado();

		return new MaestroProductoDTO(idMaestroProducto, descripcion, tipo, fabricado, precioCosto, precioMayorista,
				precioMinorista, puntoRepositorio, idProveedor, talle, unidadMedida, estado, cantidadAReponer,
				diasParaReponer);
	}

	public void agregarProductosEnTablaModificar(ActionEvent a) {
		for (MaestroProductoDTO m : maestroProductoEnTablaProducto) {
			if (!productoSeleccionadoEstaEnTablaProductosModificar(m)) {
				this.maestroProductoEnTablaProductosModificar.add(m);
			}
		}
		refrescarTablaProductosModificar();
		JOptionPane.showMessageDialog(null, "Se agregaron todos los productos de la tabla");
	}

	public void quitarProductoSeleccionado(ActionEvent a) {
		int filaSeleccionada = this.filaSeleccionadaTablaProductosModificar();
		if (filaSeleccionada == -1) {
			JOptionPane.showMessageDialog(null, "Seleccione un producto");
			return;
		}
		MaestroProductoDTO productoSeleccionado = this.maestroProductoEnTablaProductosModificar.get(filaSeleccionada);
		this.maestroProductoEnTablaProductosModificar.remove(productoSeleccionado);
		this.refrescarTablaProductosModificar();
		limpiarCampos();
	}

	public void limpiarTabla(ActionEvent a) {
		this.ventanaModificarMProducto.getModelProducto2().setRowCount(0);
		this.ventanaModificarMProducto.getModelProducto2().setColumnCount(0);
		this.ventanaModificarMProducto.getModelProducto2()
				.setColumnIdentifiers(this.ventanaModificarMProducto.getNombreColumnas2());
		this.maestroProductoEnTablaProductosModificar.clear();
		limpiarCampos();
	}

	public void agregarProductoSeleccionado(ActionEvent a) {
		int filaSeleccionada = this.filaSeleccionadaTablaProducto();
		if (filaSeleccionada == -1) {
			JOptionPane.showMessageDialog(null, "Seleccione un producto");
			return;
		}
		
		MaestroProductoDTO productoSeleccionado = this.maestroProductoEnTablaProducto.get(filaSeleccionada);
		if (!productoSeleccionadoEstaEnTablaProductosModificar(productoSeleccionado)) {
			this.maestroProductoEnTablaProductosModificar.add(productoSeleccionado);
			refrescarTablaProductosModificar();
		} else {
			JOptionPane.showMessageDialog(null, "Este producto ya fue seleccionado");
		}
	}

	public boolean productoSeleccionadoEstaEnTablaProductosModificar(MaestroProductoDTO productoSeleccionado) {
		for (MaestroProductoDTO m : maestroProductoEnTablaProductosModificar) {
			if (productoSeleccionado.getIdMaestroProducto() == m.getIdMaestroProducto()) {
				return true;
			}
		}
		return false;
	}

	public int filaSeleccionadaTablaProducto() {
		return this.ventanaModificarMProducto.getTablaProducto().getSelectedRow();
	}

	public int filaSeleccionadaTablaProductosModificar() {
		return this.ventanaModificarMProducto.getTablaProductosModificar().getSelectedRow();
	}

	public void rellenarCamposDeModificacionUnitaria() {
		this.limpiarCampos();
		int filaSeleccionada = filaSeleccionadaTablaProductosModificar();
		JTable tablaProductosModificar = ventanaModificarMProducto.getTablaProductosModificar();
		ventanaModificarMProducto.getLblActualizarDescripcion()
				.setText(tablaProductosModificar.getValueAt(filaSeleccionada, 1).toString());
		ventanaModificarMProducto.getTxtActualizarPrecioCosto()
				.setText(tablaProductosModificar.getValueAt(filaSeleccionada, 4).toString());
		ventanaModificarMProducto.getTxtActualizarPrecioMayorista()
				.setText(tablaProductosModificar.getValueAt(filaSeleccionada, 5).toString());
		ventanaModificarMProducto.getTxtActualizarPrecioMinorista()
				.setText(tablaProductosModificar.getValueAt(filaSeleccionada, 6).toString());
		ventanaModificarMProducto.getTxtActualizarPuntoRepositorio()
				.setText(tablaProductosModificar.getValueAt(filaSeleccionada, 7).toString());
		ventanaModificarMProducto.getTxtActualizarCantidadAReponer()
				.setText(tablaProductosModificar.getValueAt(filaSeleccionada, 8).toString());
		ventanaModificarMProducto.getTxtActualizarDiasParaResponder()
				.setText(tablaProductosModificar.getValueAt(filaSeleccionada, 9).toString());
	}

	public void realizarBusqueda() {
		this.ventanaModificarMProducto.getModelProducto().setRowCount(0);
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
		llenarTablaProducto(ProductosAproximados);
		this.maestroProductoEnTablaProducto = ProductosAproximados;
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

	public void actualizarProducto(ActionEvent p) {
		if (validarCamposModificacionUnitaria()) {
			this.ingresarProductoATablaHistorialCambioMProducto();
			this.actualizarTablaMaestroProducto();
			this.actualizarTablaMaestroProductosModificar();
			this.refrescarTablaProducto();
			this.refrescarTablaProductosModificar();
			this.limpiarCampos();
		}
	}

	public boolean validarCamposModificacionUnitaria() {
		// Valido si selecciono un producto

		int filaSeleccionada = this.filaSeleccionadaTablaProductosModificar();
		if (filaSeleccionada == -1) {
			JOptionPane.showMessageDialog(null, "Seleccione un producto");
			return false;
		}

		// Valido si ingreso letras en vez de numeros

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

		double precioCosto = Double.parseDouble(precioCostoNuevo);
		double precioMayorista = Double.parseDouble(precioMayoristaNuevo);
		double precioMinorista = Double.parseDouble(precioMinoristaNuevo);

		if ((precioCosto == 0 || precioMayorista == 0 || precioMinorista == 0)) {
			JOptionPane.showMessageDialog(null, "Los precios no pueden tener valor cero");
			return false;
		}

		if (noSeModificaronDatos()) {
			JOptionPane.showMessageDialog(null, "No se a modificado ningun dato");
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
		int filaSeleccionada = this.filaSeleccionadaTablaProductosModificar();
		int idModificar = this.maestroProductoEnTablaProductosModificar.get(filaSeleccionada).getIdMaestroProducto();
		MaestroProductoDTO productoNuevo = obtenerMaestroProductoNuevo();
		maestroProducto.update(idModificar, productoNuevo);
	}

	public void actualizarTablaMaestroProductosModificar() {
		int filaSeleccionada = this.filaSeleccionadaTablaProductosModificar();
		MaestroProductoDTO productoNuevo = obtenerMaestroProductoNuevo();
		for (MaestroProductoDTO m : maestroProductoEnTablaProductosModificar) {
			if (m.getIdMaestroProducto() == productoNuevo.getIdMaestroProducto()) {
				maestroProductoEnTablaProductosModificar.set(filaSeleccionada, productoNuevo);
			}
		}
	}

	public boolean noSeModificaronDatos() {

		int filaSeleccionada = this.filaSeleccionadaTablaProductosModificar();

		String precioCostoAntiguo = ""
				+ this.maestroProductoEnTablaProductosModificar.get(filaSeleccionada).getPrecioCosto();
		String precioCostoNuevo = this.ventanaModificarMProducto.getTxtActualizarPrecioCosto().getText();

		String precioMayoristaAntiguo = ""
				+ this.maestroProductoEnTablaProductosModificar.get(filaSeleccionada).getPrecioMayorista();
		String precioMayoristaNuevo = this.ventanaModificarMProducto.getTxtActualizarPrecioMayorista().getText();

		String precioMinoristaAntiguo = ""
				+ this.maestroProductoEnTablaProductosModificar.get(filaSeleccionada).getPrecioMinorista();
		String precioMinoristaNuevo = this.ventanaModificarMProducto.getTxtActualizarPrecioMinorista().getText();

		String PuntoRepositorioAntiguo = ""
				+ this.maestroProductoEnTablaProductosModificar.get(filaSeleccionada).getPuntoRepositorio();
		String PuntoRepositorioNuevo = "" + this.ventanaModificarMProducto.getTxtActualizarPuntoRepositorio().getText();

		String CantidadAReponerAntiguo = ""
				+ this.maestroProductoEnTablaProductosModificar.get(filaSeleccionada).getCantidadAReponer();
		String CantidadAReponerNuevo = "" + this.ventanaModificarMProducto.getTxtActualizarCantidadAReponer().getText();

		String DiasParaReponerAntiguo = ""
				+ this.maestroProductoEnTablaProductosModificar.get(filaSeleccionada).getDiasParaReponer();
		String DiasParaReponerNuevo = "" + this.ventanaModificarMProducto.getTxtActualizarDiasParaResponder().getText();

		if (precioCostoAntiguo.equals(precioCostoNuevo) && precioMayoristaAntiguo.equals(precioMayoristaNuevo)
				&& precioMinoristaAntiguo.equals(precioMinoristaNuevo)
				&& PuntoRepositorioAntiguo.equals(PuntoRepositorioNuevo)
				&& CantidadAReponerAntiguo.equals(CantidadAReponerNuevo)
				&& DiasParaReponerAntiguo.equals(DiasParaReponerNuevo)) {
			return true;
		}
		return false;
	}

	public String obtenerFechaDeHoy() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		String fecha = dtf.format(LocalDateTime.now());
		return fecha;
	}

	public void ingresarProductoATablaHistorialCambioMProducto() {
		int filaSeleccionada = this.filaSeleccionadaTablaProductosModificar();

		String idMaestroProducto = ""
				+ this.maestroProductoEnTablaProducto.get(filaSeleccionada).getIdMaestroProducto();
		String fecha = obtenerFechaDeHoy();

		String precioCostoAntiguo = ""
				+ this.maestroProductoEnTablaProductosModificar.get(filaSeleccionada).getPrecioCosto();
		String precioCostoNuevo = this.ventanaModificarMProducto.getTxtActualizarPrecioCosto().getText();

		String precioMayoristaAntiguo = ""
				+ this.maestroProductoEnTablaProductosModificar.get(filaSeleccionada).getPrecioMayorista();
		String precioMayoristaNuevo = this.ventanaModificarMProducto.getTxtActualizarPrecioMayorista().getText();

		String precioMinoristaAntiguo = ""
				+ this.maestroProductoEnTablaProductosModificar.get(filaSeleccionada).getPrecioMinorista();
		String precioMinoristaNuevo = this.ventanaModificarMProducto.getTxtActualizarPrecioMinorista().getText();

		String PuntoRepositorioAntiguo = ""
				+ this.maestroProductoEnTablaProductosModificar.get(filaSeleccionada).getPuntoRepositorio();
		String PuntoRepositorioNuevo = "" + this.ventanaModificarMProducto.getTxtActualizarPuntoRepositorio().getText();

		String CantidadAReponerAntiguo = ""
				+ this.maestroProductoEnTablaProductosModificar.get(filaSeleccionada).getCantidadAReponer();
		String CantidadAReponerNuevo = "" + this.ventanaModificarMProducto.getTxtActualizarCantidadAReponer().getText();

		String DiasParaReponerAntiguo = ""
				+ this.maestroProductoEnTablaProductosModificar.get(filaSeleccionada).getDiasParaReponer();
		String DiasParaReponerNuevo = "" + this.ventanaModificarMProducto.getTxtActualizarDiasParaResponder().getText();

		HistorialCambioMProductoDTO nuevoHistorial = new HistorialCambioMProductoDTO(0, idEmpleado, idMaestroProducto,
				fecha, precioCostoAntiguo, precioCostoNuevo, precioMayoristaAntiguo, precioMayoristaNuevo,
				precioMinoristaAntiguo, precioMinoristaNuevo, PuntoRepositorioAntiguo, PuntoRepositorioNuevo,
				CantidadAReponerAntiguo, CantidadAReponerNuevo, DiasParaReponerAntiguo, DiasParaReponerNuevo);

		this.historialCambioMProducto.insert(nuevoHistorial);

	}

	public MaestroProductoDTO obtenerMaestroProductoNuevo() {

		int filaSeleccionada = filaSeleccionadaTablaProductosModificar();

		int idMaestroProducto = this.maestroProductoEnTablaProductosModificar.get(filaSeleccionada)
				.getIdMaestroProducto();
		String descripcion = this.maestroProductoEnTablaProductosModificar.get(filaSeleccionada).getDescripcion();
		String tipo = this.maestroProductoEnTablaProductosModificar.get(filaSeleccionada).getTipo();
		String fabricado = this.maestroProductoEnTablaProductosModificar.get(filaSeleccionada).getFabricado();

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

		int idProveedor = this.maestroProductoEnTablaProductosModificar.get(filaSeleccionada).getIdProveedor();
		String talle = this.maestroProductoEnTablaProductosModificar.get(filaSeleccionada).getTalle();
		String unidadMedida = this.maestroProductoEnTablaProductosModificar.get(filaSeleccionada).getUnidadMedida();
		String estado = this.maestroProductoEnTablaProductosModificar.get(filaSeleccionada).getEstado();

		return new MaestroProductoDTO(idMaestroProducto, descripcion, tipo, fabricado, precioCosto, precioMayorista,
				precioMinorista, puntoRepositorio, idProveedor, talle, unidadMedida, estado, cantidadAReponer,
				diasParaReponer);
	}

	public MaestroProductoDTO obtenerMaestroProductoSeleccionado() {
		int filaSeleccionada = filaSeleccionadaTablaProductosModificar();
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
		this.refrescarTablaProducto();
	}

	public void refrescarTablaProducto() {
		this.maestroProductoEnTablaProducto = maestroProducto.readAll();
		this.llenarTablaProducto(maestroProductoEnTablaProducto);
	}

	public void llenarTablaProducto(List<MaestroProductoDTO> mProductoEnTabla) {
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

	public void refrescarTablaProductosModificar() {
		this.llenarTablaProductosModificar(maestroProductoEnTablaProductosModificar);
	}

	public void llenarTablaProductosModificar(List<MaestroProductoDTO> mProductoEnTablaProductosModificar) {
		this.ventanaModificarMProducto.getModelProducto2().setRowCount(0);
		this.ventanaModificarMProducto.getModelProducto2().setColumnCount(0);
		this.ventanaModificarMProducto.getModelProducto2()
				.setColumnIdentifiers(this.ventanaModificarMProducto.getNombreColumnas2());
		for (MaestroProductoDTO mp : mProductoEnTablaProductosModificar) {
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
			this.ventanaModificarMProducto.getModelProducto2().addRow(fila);

		}
	}
}
