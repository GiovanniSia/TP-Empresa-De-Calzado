package presentacion.controlador.ModificarProducto;

import java.awt.event.ActionEvent;
import java.util.List;

import dto.MaestroProductoDTO;
import dto.ProductoDeProveedorDTO;
import modelo.ProductoDeProveedor;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.vista.ModificarProducto.VentanaDescripcionProveedorMProducto;

public class ControladorDescripcionProveedorMProducto {
	private VentanaDescripcionProveedorMProducto ventanaDescripcionProveedorMProducto;
	private ProductoDeProveedor productoDeProveedor;
	private List<ProductoDeProveedorDTO> ProductosDeProveedorEnTabla;
	private MaestroProductoDTO producto;

	public ControladorDescripcionProveedorMProducto(MaestroProductoDTO producto) {
		this.ventanaDescripcionProveedorMProducto = new VentanaDescripcionProveedorMProducto();
		this.productoDeProveedor = new ProductoDeProveedor(new DAOSQLFactory());
		this.producto = producto;
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

	}
	
	public void rellenarCampoProveedorDeProducto() {
		this.ventanaDescripcionProveedorMProducto.getCbActualizarCambioProveedor().addItem(producto.getIdProveedor());
	}

	public void rellenarCampoDescripcion() {
		this.ventanaDescripcionProveedorMProducto.getTxtActualizarCambioDescripcion().setText(producto.getDescripcion());
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
}
