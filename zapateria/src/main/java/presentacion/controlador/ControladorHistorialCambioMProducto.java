package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import dto.HistorialCambioMProductoDTO;
import modelo.HistorialCambioMProducto;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.vista.VentanaHistorialCambioMProducto;

public class ControladorHistorialCambioMProducto {
	private VentanaHistorialCambioMProducto ventanaHistorialCambioProducto;
	private HistorialCambioMProducto historialCambioMProducto;
	private List<HistorialCambioMProductoDTO> historialCambioMproductoEnTabla;

	public ControladorHistorialCambioMProducto(HistorialCambioMProducto historialCambioMProducto) {
		this.ventanaHistorialCambioProducto = new VentanaHistorialCambioMProducto();
		this.historialCambioMProducto = historialCambioMProducto;
	}

	public void inicializar() {
		this.historialCambioMProducto = new HistorialCambioMProducto(new DAOSQLFactory());

		this.ventanaHistorialCambioProducto = new VentanaHistorialCambioMProducto();

		// Botones
		this.ventanaHistorialCambioProducto.getBtnVolverAModificarProducto()
				.addActionListener(v -> volverAModificarProducto(v));

		// TextFiltos
		this.ventanaHistorialCambioProducto.getTxtFiltroCodEmpleado().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				historialCambioMproductoEnTabla = historialCambioMProducto.getHistorialCambioMProductoAproximado(
						"IdEmpleado", ventanaHistorialCambioProducto.getTxtFiltroCodEmpleado().getText(), null, null);
				llenarTabla(historialCambioMproductoEnTabla);
			}
		});

		this.ventanaHistorialCambioProducto.getTxtFiltroCodProducto().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				historialCambioMproductoEnTabla = historialCambioMProducto.getHistorialCambioMProductoAproximado(
						"IdMaestroProducto", ventanaHistorialCambioProducto.getTxtFiltroCodProducto().getText(), null,
						null);
				llenarTabla(historialCambioMproductoEnTabla);
			}
		});

		this.ventanaHistorialCambioProducto.getTxtFiltroFecha().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				historialCambioMproductoEnTabla = historialCambioMProducto.getHistorialCambioMProductoAproximado(
						"Fecha", ventanaHistorialCambioProducto.getTxtFiltroFecha().getText(), null, null);
				llenarTabla(historialCambioMproductoEnTabla);
			}
		});
	}

	public void volverAModificarProducto(ActionEvent e) {
		this.ventanaHistorialCambioProducto.cerrar();
	}

	public void mostrarVentana() {
		this.ventanaHistorialCambioProducto.show();
		this.mostrarMProductosEnTabla();
	}

	public void mostrarMProductosEnTabla() {
		this.historialCambioMproductoEnTabla = historialCambioMProducto.readAll();
		this.llenarTabla(historialCambioMproductoEnTabla);
	}

	public void llenarTabla(List<HistorialCambioMProductoDTO> historialCambioMproductoEnTabla) {
		this.ventanaHistorialCambioProducto.getModelhistorialCambioMProducto().setRowCount(0);
		this.ventanaHistorialCambioProducto.getModelhistorialCambioMProducto().setColumnCount(0);
		this.ventanaHistorialCambioProducto.getModelhistorialCambioMProducto().setColumnIdentifiers(this.ventanaHistorialCambioProducto.getNombreColumnas());

		for (HistorialCambioMProductoDTO hcmp : historialCambioMproductoEnTabla) {
			int codEmpleado = hcmp.getIdEmpleado();
			int codProducto = hcmp.getIdMaestroProducto();
			String fecha = hcmp.getFecha();
			
			double precioCostoAntiguo = hcmp.getPrecioCostoAntiguo();
			double precioCostoNuevo = hcmp.getPrecioCostoNuevo();
			
			double precioMayoristaAntiguo = hcmp.getPrecioMayoristaAntiguo();
			double precioMayoristaNuevo = hcmp.getPrecioMayoristaNuevo();
			
			double precioMinoristaAntiguo = hcmp.getPrecioMinoristaAntiguo();
			double precioMinoristaNuevo = hcmp.getPrecioMinoristaNuevo();
			
			Object[] fila = { codEmpleado, codProducto, fecha, precioCostoAntiguo, precioCostoNuevo, precioMayoristaAntiguo, precioMayoristaNuevo,precioMinoristaAntiguo,precioMinoristaNuevo};
			this.ventanaHistorialCambioProducto.getModelhistorialCambioMProducto().addRow(fila);	
		}
	}

//	public static void main(String[] args) {
//		HistorialCambioMProducto modelo = new HistorialCambioMProducto(new DAOSQLFactory());
//		ControladorHistorialCambioMProducto controlador = new ControladorHistorialCambioMProducto(modelo);
//		controlador.inicializar();
//		controlador.mostrarVentana();
//	}
}
