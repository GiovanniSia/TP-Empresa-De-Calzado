package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import modelo.Zapateria;
import presentacion.reportes.ReporteProducto;
import presentacion.vista.VentanaProducto;
import presentacion.vista.Vista;
import dto.ProductoDTO;

public class Controlador implements ActionListener {
	private Vista vista;
	private List<ProductoDTO> productoEnTabla;
	private VentanaProducto ventanaAgregarProducto;
	private Zapateria zapateria;

	public Controlador(Vista vista, Zapateria agenda) {
		this.vista = vista;
		this.vista.getBtnAgregar().addActionListener(a -> ventanaAgregarProducto(a));
		this.vista.getBtnBorrar().addActionListener(s -> borrarProducto(s));
		this.ventanaAgregarProducto = VentanaProducto.getInstance();
		this.ventanaAgregarProducto.getBtnAgregarProducto().addActionListener(p -> agregarProductoNuevo(p));
		this.zapateria = agenda;
	}

	private void ventanaAgregarProducto(ActionEvent a) {
		this.ventanaAgregarProducto.mostrarVentana();
	}

	private void agregarProductoNuevo(ActionEvent p) {
		String nombre = this.ventanaAgregarProducto.getTxtNombre().getText();
		String idProveedor = ventanaAgregarProducto.getTxtIdProveedor().getText();
		int idIntProveedor = Integer.parseInt(idProveedor);
		ProductoDTO nuevoProducto = new ProductoDTO(0, nombre, idIntProveedor);
		this.zapateria.agregarProducto(nuevoProducto);
		this.refrescarTabla();
		this.ventanaAgregarProducto.cerrar();
	}

	public void borrarProducto(ActionEvent s) {
		int[] filasSeleccionadas = this.vista.getTablaProductos().getSelectedRows();
		for (int fila : filasSeleccionadas) {
			this.zapateria.borrarProducto(this.productoEnTabla.get(fila));
		}
		this.refrescarTabla();
	}

	public void inicializar() {
		this.refrescarTabla();
		this.vista.show();
	}

	private void refrescarTabla() {
		this.productoEnTabla = zapateria.obtenerProducto();
		this.vista.llenarTabla(this.productoEnTabla);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

}
