package presentacion.controlador.ModificarProducto;

import java.awt.event.ActionEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JOptionPane;

import dto.HistorialCambioMProductoDTO;
import dto.MaestroProductoDTO;
import dto.ProductoDeProveedorDTO;
import modelo.HistorialCambioMProducto;
import modelo.MaestroProducto;
import modelo.ProductoDeProveedor;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.vista.ModificarProducto.VentanaDescripcionProveedorMProducto;

public class ControladorDescripcionProveedorMProducto {
	static final String idSucursal = "1";
	static final String idEmpleado = "1";

	private VentanaDescripcionProveedorMProducto ventanaDescripcionProveedorMProducto;
	private ProductoDeProveedor productoDeProveedor;
	private MaestroProducto maestroProducto;
	private List<ProductoDeProveedorDTO> ProductosDeProveedorEnTabla;
	private MaestroProductoDTO producto;
	private ControladorModificarMProducto controladorModificarMProducto;

	private HistorialCambioMProducto historialCambioMProducto;

	public ControladorDescripcionProveedorMProducto() {
		this.ventanaDescripcionProveedorMProducto = new VentanaDescripcionProveedorMProducto();
		this.productoDeProveedor = new ProductoDeProveedor(new DAOSQLFactory());
		this.maestroProducto = new MaestroProducto(new DAOSQLFactory());

		this.historialCambioMProducto = new HistorialCambioMProducto(new DAOSQLFactory());

		this.ventanaDescripcionProveedorMProducto.getBtnAtras().addActionListener(r -> atras(r));
		this.ventanaDescripcionProveedorMProducto.getBtnActualizarProducto()
				.addActionListener(a -> actualizarProducto(a));

	}

	public void inicializar() {

		rellenarTablaProductoSeleccionado();
		rellenarCampoDescripcion();
		rellenarCampoProveedorDeProducto();
	}

	public void actualizarProducto(ActionEvent a) {
		String descripcionAntigua = this.producto.getDescripcion();
		String descripcionNueva = this.ventanaDescripcionProveedorMProducto.getTxtActualizarCambioDescripcion()
				.getText();

		int idProveedorAntiguo = this.producto.getIdProveedor();
		int idProveedorNuevo = Integer.parseInt(this.ventanaDescripcionProveedorMProducto
				.getCbActualizarCambioProveedor().getSelectedItem().toString());
		if (descripcionNueva.equals("")) {
			JOptionPane.showMessageDialog(null, "No se permite una descripcion vacia");
			return;
		}

		if (descripcionAntigua.equals(descripcionNueva) && idProveedorAntiguo == idProveedorNuevo) {
			JOptionPane.showMessageDialog(null, "No se a modificado ningun dato");
			return;
		}

		if (!(descripcionAntigua.equals(descripcionNueva)) && idProveedorAntiguo != idProveedorNuevo) {
			MaestroProductoDTO productoNuevo = obtenerMaestroProductoNuevo(descripcionNueva, idProveedorNuevo);
			actualizacionMaestroProducto(productoNuevo);
			JOptionPane.showMessageDialog(null, "Modificacion de descripcion y proveedor con exito");
			controladorModificarMProducto.refrescarTablaProducto();
			this.ventanaDescripcionProveedorMProducto.cerrar();

			ingresarProductoATablaHistorialCambioMProducto(producto, productoNuevo);

			return;
		}

		if (!descripcionAntigua.equals(descripcionNueva) && idProveedorAntiguo == idProveedorNuevo) {
			MaestroProductoDTO productoNuevo = obtenerMaestroProductoNuevo(descripcionNueva, idProveedorAntiguo);
			actualizacionMaestroProducto(productoNuevo);
			JOptionPane.showMessageDialog(null, "Modificacion de descripcion con exito");
			controladorModificarMProducto.refrescarTablaProducto();
			this.ventanaDescripcionProveedorMProducto.cerrar();

			ingresarProductoATablaHistorialCambioMProducto(producto, productoNuevo);

			return;
		}

		if (descripcionAntigua.equals(descripcionNueva) && idProveedorAntiguo != idProveedorNuevo) {
			MaestroProductoDTO productoNuevo = obtenerMaestroProductoNuevo(descripcionAntigua, idProveedorNuevo);
			actualizacionMaestroProducto(productoNuevo);
			JOptionPane.showMessageDialog(null, "Modificacion de proveedor con exito");
			controladorModificarMProducto.refrescarTablaProducto();
			this.ventanaDescripcionProveedorMProducto.cerrar();
			
			ingresarProductoATablaHistorialCambioMProducto(producto, productoNuevo);

			return;
		}
	}

	public String obtenerFechaDeHoy() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		String fecha = dtf.format(LocalDateTime.now());
		return fecha;
	}

	public void ingresarProductoATablaHistorialCambioMProducto(MaestroProductoDTO productoAntiguo,
			MaestroProductoDTO productoNuevo) {

		String idMaestroProducto = "" + productoAntiguo.getIdMaestroProducto();
		String fecha = obtenerFechaDeHoy();

		String descripcionAntiguo = "" + productoAntiguo.getDescripcion();
		String descripcionNuevo = "" + productoNuevo.getDescripcion();
		String proveedorAntiguo = "" + productoAntiguo.getIdProveedor();
		String prpobeedorNuevo = "" + productoNuevo.getIdProveedor();

		String precioCostoAntiguo = "" + productoAntiguo.getPrecioCosto();
		String precioCostoNuevo = "" + productoNuevo.getPrecioCosto();

		String precioMayoristaAntiguo = "" + productoAntiguo.getPrecioMayorista();
		String precioMayoristaNuevo = "" + productoNuevo.getPrecioMayorista();

		String precioMinoristaAntiguo = "" + productoAntiguo.getPrecioMinorista();
		String precioMinoristaNuevo = "" + productoNuevo.getPrecioMinorista();

		String PuntoRepositorioAntiguo = "" + productoAntiguo.getPuntoRepositorio();
		String CantidadAReponerAntiguo = "" + productoAntiguo.getCantidadAReponer();
		String DiasParaReponerAntiguo = "" + productoAntiguo.getDiasParaReponer();

		HistorialCambioMProductoDTO nuevoHistorial = new HistorialCambioMProductoDTO(0, idSucursal, idEmpleado,
				idMaestroProducto, fecha, descripcionAntiguo, descripcionNuevo, proveedorAntiguo, prpobeedorNuevo,
				precioCostoAntiguo, precioCostoNuevo, precioMayoristaAntiguo, precioMayoristaNuevo,
				precioMinoristaAntiguo, precioMinoristaNuevo, PuntoRepositorioAntiguo, PuntoRepositorioAntiguo,
				CantidadAReponerAntiguo, CantidadAReponerAntiguo, DiasParaReponerAntiguo, DiasParaReponerAntiguo);

		this.historialCambioMProducto.insert(nuevoHistorial);

	}

	public void ocultarVentana() {
		this.ventanaDescripcionProveedorMProducto.cerrar();
	}

	public void actualizacionMaestroProducto(MaestroProductoDTO productoNuevo) {
		int idModificar = producto.getIdMaestroProducto();
		maestroProducto.update(idModificar, productoNuevo);
	}

	public MaestroProductoDTO obtenerMaestroProductoNuevo(String descripcio, int idProveedo) {

		int idMaestroProducto = producto.getIdMaestroProducto();
		String descripcion = descripcio;
		int idProveedor = idProveedo;
		String talle = producto.getTalle();
		String tipo = producto.getTipo();
		String fabricado = producto.getFabricado();
		double precioCosto = producto.getPrecioCosto();
		double precioMayorista = producto.getPrecioMayorista();
		double precioMinorista = producto.getPrecioMinorista();
		int puntoRepositorio = producto.getPuntoRepositorio();
		int cantidadAReponer = producto.getCantidadAReponer();
		int diasParaReponer = producto.getDiasParaReponer();
		String unidadMedida = producto.getUnidadMedida();
		String estado = producto.getEstado();

		return new MaestroProductoDTO(idMaestroProducto, descripcion, tipo, fabricado, precioCosto, precioMayorista,
				precioMinorista, puntoRepositorio, idProveedor, talle, unidadMedida, estado, cantidadAReponer,
				diasParaReponer);
	}

	public void rellenarCampoProveedorDeProducto() {
		this.ventanaDescripcionProveedorMProducto.getCbActualizarCambioProveedor().removeAllItems();
		this.ventanaDescripcionProveedorMProducto.getCbActualizarCambioProveedor()
				.addItem("" + producto.getIdProveedor());
		rellenarPosiblesProveedoresPorducto();
	}

	public void rellenarPosiblesProveedoresPorducto() {
		ProductosDeProveedorEnTabla = productoDeProveedor.readAll();
		for (ProductoDeProveedorDTO pp : ProductosDeProveedorEnTabla) {
			if (pp.getIdMaestroProducto() == producto.getIdMaestroProducto()
					&& pp.getIdProveedor() != producto.getIdProveedor()) {
				this.ventanaDescripcionProveedorMProducto.getCbActualizarCambioProveedor()
						.addItem("" + pp.getIdProveedor());
			}
		}
	}

	public void rellenarCampoDescripcion() {
		this.ventanaDescripcionProveedorMProducto.getTxtActualizarCambioDescripcion()
				.setText(producto.getDescripcion());
	}

	public void atras(ActionEvent r) {
		this.ventanaDescripcionProveedorMProducto.cerrar();
	}

	public void mostrarVentana() {
		this.ventanaDescripcionProveedorMProducto.show();
	}

	public void rellenarTablaProductoSeleccionado() {
		this.ventanaDescripcionProveedorMProducto.getModelModelProducto().setRowCount(0);
		this.ventanaDescripcionProveedorMProducto.getModelModelProducto().setColumnCount(0);
		this.ventanaDescripcionProveedorMProducto.getModelModelProducto()
				.setColumnIdentifiers(this.ventanaDescripcionProveedorMProducto.getNombreColumnas());

		int idProducto = producto.getIdMaestroProducto();
		String descripcion = producto.getDescripcion();
		int idProveedor = producto.getIdProveedor();
		String talle = producto.getTalle();
		Object[] fila = { idProducto, descripcion, idProveedor, talle };
		this.ventanaDescripcionProveedorMProducto.getModelModelProducto().addRow(fila);

	}

	public void refrescarDatosTabla() {
		this.ProductosDeProveedorEnTabla = productoDeProveedor.readAll();
	}

	public void setMaestroProductoDTO(MaestroProductoDTO p) {
		this.producto = p;
	}

	public void setControladorModificarMProducto(ControladorModificarMProducto c) {
		this.controladorModificarMProducto = c;
	}
}
