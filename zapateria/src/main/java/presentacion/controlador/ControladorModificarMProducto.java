package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;

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
		this.ventanaModificarMProducto.getBtnAtras().addActionListener(a->atras(a));
		this.ventanaModificarMProducto.getBtnActualizarProducto().addActionListener(c->actualizarProducto(c));
		this.ventanaModificarMProducto.getBtnVerHistorialDeCambios().addActionListener(v->verHistorialDeCambios(v));
		
		//Tabla
		this.ventanaModificarMProducto.getTablaProducto().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int filaSeleccionada = ventanaModificarMProducto.getTablaProducto().rowAtPoint(e.getPoint());
				JTable tablaProducto =ventanaModificarMProducto.getTablaProducto();
				ventanaModificarMProducto.getLblActualizarDescripcion().setText(tablaProducto.getValueAt(filaSeleccionada, 1).toString());
				ventanaModificarMProducto.getTxtActualizarPrecioCosto().setText(tablaProducto.getValueAt(filaSeleccionada, 3).toString());
				ventanaModificarMProducto.getTxtActualizarPrecioMayorista().setText(tablaProducto.getValueAt(filaSeleccionada, 4).toString());
				ventanaModificarMProducto.getTxtActualizarPrecioMinorista().setText(tablaProducto.getValueAt(filaSeleccionada, 5).toString());
				ventanaModificarMProducto.getSpinnerPuntoRepositorio().setValue(tablaProducto.getValueAt(filaSeleccionada, 6));
				ventanaModificarMProducto.getSpinnerCantidadAReponer().setValue(tablaProducto.getValueAt(filaSeleccionada, 7));
				ventanaModificarMProducto.getSpinnerDiasParaReponer().setValue(tablaProducto.getValueAt(filaSeleccionada, 8));		
				
			}
		});
		
		//TextFiltos
		this.ventanaModificarMProducto.getTxtFiltroCodProducto().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				maestroProductoEnTabla = producto.getMaestroProductoAproximado("IdMaestroProducto",ventanaModificarMProducto.getTxtFiltroCodProducto().getText(),null,null);
				llenarTabla(maestroProductoEnTabla);
			}
		});

		this.ventanaModificarMProducto.getTxtFiltroDescripcion().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				maestroProductoEnTabla = producto.getMaestroProductoAproximado("Descripcion",ventanaModificarMProducto.getTxtFiltroDescripcion().getText(),null,null);
				llenarTabla(maestroProductoEnTabla);
			}
		});

		this.ventanaModificarMProducto.getTxtFiltroTalle().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				maestroProductoEnTabla = producto.getMaestroProductoAproximado("Talle",ventanaModificarMProducto.getTxtFiltroTalle().getText(),null,null);
				llenarTabla(maestroProductoEnTabla);
			}
		});		
		

//		this.mostrarVentana();
	}
	
	public void atras(ActionEvent a) {
		this.ventanaModificarMProducto.cerrar();
	}

	//Se actualizar la tabla maestroProducto y historialCambioMProducto
	public void actualizarProducto(ActionEvent p) {
		
		
		
		
		
	}
	
	//Abrir ventana para ver la tabla historialCambioMProducto
	public void verHistorialDeCambios(ActionEvent v) {
		
		
		
		
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
				
//				System.out.println(" Producto seleccionado");			
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
		MaestroProducto modelo = new MaestroProducto(new DAOSQLFactory());
		ControladorModificarMProducto controlador = new ControladorModificarMProducto(modelo);
		controlador.inicializar();
		controlador.mostrarVentana();
	}

}
