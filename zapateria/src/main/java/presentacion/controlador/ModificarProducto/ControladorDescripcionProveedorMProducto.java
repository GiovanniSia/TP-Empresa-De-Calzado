package presentacion.controlador.ModificarProducto;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JOptionPane;

import dto.MaestroProductoDTO;
import dto.ProductoDeProveedorDTO;
import modelo.MaestroProducto;
import modelo.ProductoDeProveedor;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.vista.ModificarProducto.VentanaDescripcionProveedorMProducto;

public class ControladorDescripcionProveedorMProducto {
	private VentanaDescripcionProveedorMProducto ventanaDescripcionProveedorMProducto;
	private ProductoDeProveedor productoDeProveedor;
	private MaestroProducto maestroProducto;
	private List<ProductoDeProveedorDTO> ProductosDeProveedorEnTabla;
	private MaestroProductoDTO producto;
	private ControladorModificarMProducto controladorModificarMProducto;

	public ControladorDescripcionProveedorMProducto(MaestroProductoDTO producto) {
		this.ventanaDescripcionProveedorMProducto = new VentanaDescripcionProveedorMProducto();
		this.productoDeProveedor = new ProductoDeProveedor(new DAOSQLFactory());
		this.maestroProducto = new MaestroProducto(new DAOSQLFactory());
		this.producto = producto;
		this.controladorModificarMProducto = new ControladorModificarMProducto();
	}

	public void inicializar() {
		this.ventanaDescripcionProveedorMProducto.getBtnAtras().addActionListener(r -> atras(r));
		this.ventanaDescripcionProveedorMProducto.getBtnActualizarProducto()
				.addActionListener(a -> actualizarProducto(a));

		rellenarTablaProductoSeleccionado();
		rellenarCampoDescripcion();
		rellenarCampoProveedorDeProducto();
	}

	public void actualizarProducto(ActionEvent a) {
		String descripcionAntigua = this.producto.getDescripcion();
		String descripcionNueva = this.ventanaDescripcionProveedorMProducto.getTxtActualizarCambioDescripcion()
				.getText();

		int idProveedorAntiguo = this.producto.getIdProveedor();
		int idProveedorNuevo = (int) this.ventanaDescripcionProveedorMProducto.getCbActualizarCambioProveedor()
				.getSelectedItem();

		if (descripcionAntigua.equals(descripcionNueva) && idProveedorAntiguo == idProveedorNuevo) {
			JOptionPane.showMessageDialog(null, "No se a modificado ningun dato");
			return;
		}

		if (!(descripcionAntigua.equals(descripcionNueva)) && idProveedorAntiguo != idProveedorNuevo) {
			MaestroProductoDTO productoNuevo = obtenerMaestroProductoNuevo(descripcionNueva, idProveedorNuevo);
			actualizacionMaestroProducto(productoNuevo);
			JOptionPane.showMessageDialog(null, "Modificacion de descripcion y proveedor con exito");
			this.ventanaDescripcionProveedorMProducto.cerrar();
			controladorModificarMProducto.inicializar();
			controladorModificarMProducto.mostrarVentana();
			return;
		}

		if (!descripcionAntigua.equals(descripcionNueva)) {
			MaestroProductoDTO productoNuevo = obtenerMaestroProductoNuevo(descripcionNueva, idProveedorAntiguo);
			actualizacionMaestroProducto(productoNuevo);
			JOptionPane.showMessageDialog(null, "Modificacion de descripcion con exito");
			this.ventanaDescripcionProveedorMProducto.cerrar();
			controladorModificarMProducto.inicializar();
			controladorModificarMProducto.mostrarVentana();
			return;
		}

		if (idProveedorAntiguo != idProveedorNuevo) {
			MaestroProductoDTO productoNuevo = obtenerMaestroProductoNuevo(descripcionAntigua, idProveedorNuevo);
			actualizacionMaestroProducto(productoNuevo);
			JOptionPane.showMessageDialog(null, "Modificacion de proveedor con exito");
			this.ventanaDescripcionProveedorMProducto.cerrar();
			controladorModificarMProducto.inicializar();
			controladorModificarMProducto.mostrarVentana();
			return;
		}
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
		this.ventanaDescripcionProveedorMProducto.getCbActualizarCambioProveedor().addItem(""+producto.getIdProveedor());
		rellenarPosiblesProveedoresPorducto();
	}

	public void rellenarPosiblesProveedoresPorducto() {
		ProductosDeProveedorEnTabla = productoDeProveedor.readAll();
		for (ProductoDeProveedorDTO pp : ProductosDeProveedorEnTabla) {
			if (pp.getIdMaestroProducto() == producto.getIdMaestroProducto()
					&& pp.getIdProveedor() != producto.getIdProveedor()) {
				this.ventanaDescripcionProveedorMProducto.getCbActualizarCambioProveedor().addItem(""+pp.getIdProveedor());
			}
		}
	}

	public void rellenarCampoDescripcion() {
		this.ventanaDescripcionProveedorMProducto.getTxtActualizarCambioDescripcion()
				.setText(producto.getDescripcion());
	}

	public void atras(ActionEvent r) {
		this.ventanaDescripcionProveedorMProducto.cerrar();
		controladorModificarMProducto.inicializar();
		controladorModificarMProducto.mostrarVentana();
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
}
