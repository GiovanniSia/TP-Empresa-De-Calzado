package presentacion.controlador.generarOrdenesManufactura;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import dto.MaestroProductoDTO;
import dto.StockDTO;
import modelo.generarOrdenesFabricacion;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.controlador.Controlador;
import presentacion.vista.generarOrdenesManufactura.VentanaGenerarOrdenManufactura;

public class ControladorGenerarOrdenesManufactura implements ActionListener {
	
	private static final int idSucursal = 1;
	private static final int idEmpleado = 1;
	
	private ArrayList<MaestroProductoDTO> productosEnLista;
	
	private VentanaGenerarOrdenManufactura ventana;
	
	Controlador controlador;
	
	public ControladorGenerarOrdenesManufactura(Controlador controlador) {
		
		ventana = new VentanaGenerarOrdenManufactura();
		productosEnLista = new ArrayList<MaestroProductoDTO>();
		this.controlador = controlador;
	}
	
	
	public void inicializar() {
		ventana.getTextId().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
            	actualizarTextField();
            }
        });
		
		ventana.getTextTalle().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
            	actualizarTextField();
            }
        });
		
		ventana.getTextProducto().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
            	actualizarTextField();
            }
        });
		
		ventana.getBtnGenerarPedido().addActionListener(r->botonGenerarPedido(r));
		

		this.ventana.getBtnSalir().addActionListener(a -> salir(a));
		
		
		
		
		
		refrescarTabla();
		
		
	}
	
	public void mostrarVentana() {
		ventana.mostrarVentana();
	}
	
	public void salir(ActionEvent a) {
		this.ventana.cerrar();
		this.controlador.inicializar();
		this.controlador.mostrarVentanaMenuDeSistemas();
	}
	
	public void refrescarTabla() {
		vaciarTabla();
		recuperarDatos();
		llenarTabla();
	}
	
	private void vaciarTabla() {
		ventana.getModelOrdenes().setRowCount(0);
		ventana.getModelOrdenes().setColumnCount(0);
		ventana.getModelOrdenes().setColumnIdentifiers(ventana.getNombreColumnas());
	}
	
	private void recuperarDatos() {
		String idProducto = ventana.getTextId().getText();
		String descrProducto = ventana.getTextProducto().getText();
		String talle = ventana.getTextTalle().getText();
		productosEnLista = new ArrayList<MaestroProductoDTO>();
		DAOSQLFactory a = new DAOSQLFactory();
		List<MaestroProductoDTO> todosLosProductos = a.createOrdenFabricaDAO().leerProductos(idProducto, descrProducto, talle);
		for(MaestroProductoDTO mp: todosLosProductos) {
			if(mp.getEstado().equals("Activo") && mp.getFabricado().equals("S")) {
				productosEnLista.add(mp);
			}
		}
		
	}
	
	public void llenarTabla() {
		for(MaestroProductoDTO mp: productosEnLista) {
			DAOSQLFactory a = new DAOSQLFactory();
			List<StockDTO> stock = a.createStockDAO().readAll();
			int cantidadEnStock = 0;
			for(StockDTO s: stock) {
				if(s.getIdSucursal() == idSucursal && s.getIdProducto() == mp.getIdMaestroProducto()) {
					cantidadEnStock = cantidadEnStock + s.getStockDisponible();
				}
			}
			Object[] agregar = {mp.getIdMaestroProducto(), mp.getTalle(), mp.getDescripcion(), cantidadEnStock};
			ventana.getModelOrdenes().addRow(agregar);
		}
	}
	
	public void actualizarTextField(){
		refrescarTabla();
	}
	
	public void botonGenerarPedido(ActionEvent s) {
		try {
			String aux = ventana.getSpinnerCantidad().getValue()+"";
			int cantidadDeseada = Integer.parseInt(aux);
			if(cantidadDeseada <= 0) {
				return;
			}
			int[] filasSeleccionadas = ventana.getTablaFabricacionesEnMarcha().getSelectedRows();
			if(filasSeleccionadas.length == 0) {
				return;
			}
			//filasSeleccionadas[0]
			MaestroProductoDTO productoSeleccionado = productosEnLista.get(filasSeleccionadas[0]);
			productoSeleccionado.setCantidadAReponer(cantidadDeseada);
			generarOrdenesFabricacion.crearOrdenFabricacion(idSucursal, productoSeleccionado);
			mostrarMensajeEmergente("Orden generada exitosamente para el producto "+productoSeleccionado.getDescripcion());
		}catch(Exception e) {
			
		}
	}
	
	public void mostrarMensajeEmergente(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje);
        return;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	}

}
