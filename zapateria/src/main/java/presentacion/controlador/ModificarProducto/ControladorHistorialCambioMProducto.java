package presentacion.controlador.ModificarProducto;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import dto.HistorialCambioMProductoDTO;
import modelo.HistorialCambioMProducto;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.vista.ModificarProducto.VentanaHistorialCambioMProducto;

public class ControladorHistorialCambioMProducto {
	private VentanaHistorialCambioMProducto ventanaHistorialCambioProducto;
	private HistorialCambioMProducto historialCambioMProducto;
	private List<HistorialCambioMProductoDTO> historialCambioMproductoEnTabla;

	public ControladorHistorialCambioMProducto(HistorialCambioMProducto historialCambioMProducto) {
		this.ventanaHistorialCambioProducto = new VentanaHistorialCambioMProducto();
		this.historialCambioMProducto = historialCambioMProducto;
		this.historialCambioMProducto = new HistorialCambioMProducto(new DAOSQLFactory());		
		this.ventanaHistorialCambioProducto = new VentanaHistorialCambioMProducto();
	}

	public void inicializar() {

		// Botones
		this.ventanaHistorialCambioProducto.getBtnVolverAModificarProducto().addActionListener(v -> volverAModificarProducto(v));

		// TextFiltos
		this.ventanaHistorialCambioProducto.getTxtFiltroCodEmpleado().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				realizarBusqueda();
			}
		});

		this.ventanaHistorialCambioProducto.getTxtFiltroCodSucursal().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				realizarBusqueda();
			}
		});

		this.ventanaHistorialCambioProducto.getTxtFiltroCodProducto().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				realizarBusqueda();
			}
		});

		this.ventanaHistorialCambioProducto.getTxtFiltroFecha().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				realizarBusqueda();
			}
		});
	}

	public void realizarBusqueda() {
		this.ventanaHistorialCambioProducto.getModelhistorialCambioMProducto().setRowCount(0);
		this.ventanaHistorialCambioProducto.getModelhistorialCambioMProducto().setColumnCount(0);
		this.ventanaHistorialCambioProducto.getModelhistorialCambioMProducto()
				.setColumnIdentifiers(this.ventanaHistorialCambioProducto.getNombreColumnas());

		String txtCodEmpleado = this.ventanaHistorialCambioProducto.getTxtFiltroCodEmpleado().getText();
		String txtCodSucursal = this.ventanaHistorialCambioProducto.getTxtFiltroCodSucursal().getText();
		String txtCodProducto = this.ventanaHistorialCambioProducto.getTxtFiltroCodProducto().getText();
		String txtFecha = this.ventanaHistorialCambioProducto.getTxtFiltroFecha().getText();
		List<HistorialCambioMProductoDTO> ProductosAproximados = this.historialCambioMProducto
				.getFiltroHistorialCambioMProducto("IdEmpleado", txtCodEmpleado, "IdSucursal", txtCodSucursal,
						"IdMaestroProducto", txtCodProducto, "Fecha", txtFecha);
		llenarTabla(ProductosAproximados);
		this.historialCambioMproductoEnTabla = ProductosAproximados;
	}

	public void volverAModificarProducto(ActionEvent e) {
		this.ventanaHistorialCambioProducto.cerrar();
	}

	public void mostrarVentana() {
		this.ventanaHistorialCambioProducto.show();
		this.mostrarMProductosEnTabla();
	}
	
	public void ocultarVentana() {
		this.ventanaHistorialCambioProducto.cerrar();
	}

	public void mostrarMProductosEnTabla() {
		this.historialCambioMproductoEnTabla = historialCambioMProducto.readAll();
		this.llenarTabla(historialCambioMproductoEnTabla);
	}

	public void llenarTabla(List<HistorialCambioMProductoDTO> historialCambioMproductoEnTabla) {
		this.ventanaHistorialCambioProducto.getModelhistorialCambioMProducto().setRowCount(0);
		this.ventanaHistorialCambioProducto.getModelhistorialCambioMProducto().setColumnCount(0);
		this.ventanaHistorialCambioProducto.getModelhistorialCambioMProducto()
				.setColumnIdentifiers(this.ventanaHistorialCambioProducto.getNombreColumnas());

		for (HistorialCambioMProductoDTO hcmp : historialCambioMproductoEnTabla) {
			String codSucursal = hcmp.getIdSucursal();
			String codEmpleado = hcmp.getIdEmpleado();
			String codProducto = hcmp.getIdMaestroProducto();
			String fecha = hcmp.getFecha();

			String precioCostoAntiguo = hcmp.getPrecioCostoAntiguo();
			String precioCostoNuevo = hcmp.getPrecioCostoNuevo();

			String precioMayoristaAntiguo = hcmp.getPrecioMayoristaAntiguo();
			String precioMayoristaNuevo = hcmp.getPrecioMayoristaNuevo();

			String precioMinoristaAntiguo = hcmp.getPrecioMinoristaAntiguo();
			String precioMinoristaNuevo = hcmp.getPrecioMinoristaNuevo();

			String PuntoRepositorioAntiguo = hcmp.getPuntoRepositorioAntiguo();
			String PuntoRepositorioNuevo = hcmp.getPuntoRepositorioNuevo();

			String CantidadAReponerAntiguo = hcmp.getCantidadAReponerAntiguo();
			String CantidadAReponerNuevo = hcmp.getCantidadAReponerNuevo();

			String DiasParaReponerAntiguo = hcmp.getDiasParaReponerAntiguo();
			String DiasParaReponerNuevo = hcmp.getDiasParaReponerNuevo();

			Object[] fila = { codEmpleado, codSucursal, codProducto, fecha, precioCostoAntiguo, precioCostoNuevo,
					precioMayoristaAntiguo, precioMayoristaNuevo, precioMinoristaAntiguo, precioMinoristaNuevo,
					PuntoRepositorioAntiguo, PuntoRepositorioNuevo, CantidadAReponerAntiguo, CantidadAReponerNuevo,
					DiasParaReponerAntiguo, DiasParaReponerNuevo };
			this.ventanaHistorialCambioProducto.getModelhistorialCambioMProducto().addRow(fila);
		}
	}
}
