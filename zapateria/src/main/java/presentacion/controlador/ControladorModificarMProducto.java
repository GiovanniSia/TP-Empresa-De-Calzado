package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JOptionPane;

import dto.MaestroProductoDTO;
import modelo.MaestroProducto;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.vista.VentanaModificarMProducto;

public class ControladorModificarMProducto {

	private VentanaModificarMProducto ventanaModificarMProducto;
	private MaestroProducto producto;
	private List<MaestroProductoDTO> maestroProductoEnTabla;

	public ControladorModificarMProducto(MaestroProducto producto) {		
		this.ventanaModificarMProducto = new VentanaModificarMProducto();
		this.producto = producto;
	}

	public void inicializar() {
		this.producto = new MaestroProducto(new DAOSQLFactory());

		this.ventanaModificarMProducto = new VentanaModificarMProducto();
		
		//Botones
		this.ventanaModificarMProducto.getBtnAtras().addActionListener(null);
		this.ventanaModificarMProducto.getBtnActualizarProducto().addActionListener(null);
		this.ventanaModificarMProducto.getBtnVerHistorialDeCambios().addActionListener(null);
		
		//TextFiltos
		this.ventanaModificarMProducto.getTxtFiltroCodProducto().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				maestroProductoEnTabla = producto.filtrarPorCodProducto(ventanaModificarMProducto.getTxtFiltroCodProducto().getText());
				llenarTabla(maestroProductoEnTabla);
			}
		});

		this.ventanaModificarMProducto.getTxtFiltroDescripcion().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				maestroProductoEnTabla = producto.filtrarPorDescripcion(ventanaModificarMProducto.getTxtFiltroDescripcion().getText());
				llenarTabla(maestroProductoEnTabla);
			}
		});

		this.ventanaModificarMProducto.getTxtFiltroTalle().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				maestroProductoEnTabla = producto.filtrarPorTalle(ventanaModificarMProducto.getTxtFiltroTalle().getText());
				llenarTabla(maestroProductoEnTabla);
			}
		});		
		//TextActualizar
		this.mostrarVentana();
	}
	
	public void atras(ActionEvent a) {
		this.ventanaModificarMProducto.cerrar();
	}

	public void pasarAVenta(ActionEvent p) {
		maestroProductoSeleccionado();
	}

	public void maestroProductoSeleccionado() {
		int filaSeleccionada = this.ventanaModificarMProducto.getTablaProducto().getSelectedRow();
		if (filaSeleccionada == -1) {
			JOptionPane.showMessageDialog(null, "Seleccione un cliente");
			return;
		}

		List<MaestroProductoDTO> maestroProducto = producto.readAll();
	
		String codProducto = this.ventanaModificarMProducto.getTablaProducto().getValueAt(filaSeleccionada, 0).toString();
		for (MaestroProductoDTO mp : maestroProducto) {			
			//Obtengo el objeto cilente con todos sus valores
			if (codProducto.equals(""+mp.getIdMaestroProducto()+"")) {
				
				System.out.println(" Producto seleccionado");			
			}
		}
	}

	public void mostrarVentana() {
		this.ventanaModificarMProducto.show();
		this.mostrarMProductosEnTabla();
	}

	public void mostrarMProductosEnTabla() {
		this.maestroProductoEnTabla = producto.readAll();
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

			Object[] fila = { codigo, descripcion, talle, precioCosto, precioMayorista, precioMinorista, puntoRepositorio, cantidadAReponer, diasParaReponer};
			this.ventanaModificarMProducto.getModelProducto().addRow(fila);
		
		}
	}
	
	public static void main(String[] args) {
//		VentanaModificarMProducto vista = new VentanaModificarMProducto();
		MaestroProducto modelo = new MaestroProducto(new DAOSQLFactory());
		ControladorModificarMProducto controlador = new ControladorModificarMProducto(modelo);
		controlador.inicializar();
	}

}
