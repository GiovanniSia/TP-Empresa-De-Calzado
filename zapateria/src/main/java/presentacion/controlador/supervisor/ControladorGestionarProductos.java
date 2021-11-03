package presentacion.controlador.supervisor;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import dto.MaestroProductoDTO;
import dto.StockDTO;
import modelo.MaestroProducto;
import modelo.Stock;
import presentacion.controlador.Controlador;
import presentacion.controlador.ValidadorTeclado;
import presentacion.vista.Supervisor.VentanaGestionarProductos;

public class ControladorGestionarProductos {
	
	public final int idSucursal=1;
	
	MaestroProducto maestroProducto;
	Stock stock;
	
	List<MaestroProductoDTO> todosLosProductos;
	List<StockDTO> todoElStock;
	
	List<MaestroProductoDTO> productosEnTabla;
	
	
	VentanaGestionarProductos ventanaGestionarProductos;
	
	Controlador controlador;
	
	public ControladorGestionarProductos(Controlador controlador,MaestroProducto maestroProducto,Stock stock) {
		this.maestroProducto = maestroProducto;
		this.stock = stock;
		
		this.controlador=controlador;
		
		this.todoElStock = new ArrayList<StockDTO>();
		this.todosLosProductos = new ArrayList<MaestroProductoDTO>();
		this.productosEnTabla = new ArrayList<MaestroProductoDTO>();
		this.ventanaGestionarProductos = new VentanaGestionarProductos();
		
		
		this.ventanaGestionarProductos.getBtnAtras().addActionListener(a -> volverAtras(a));
		
		this.ventanaGestionarProductos.getTxtFieldNombre().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				realizarBusqueda();
			}
		});
		this.ventanaGestionarProductos.getTextTalle().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				realizarBusqueda();
			}
		});
	}
	
	public void inicializar() {
		escribirTablaCompleta();
		validarTeclado();
	}
	
	public void mostrarVentana() {
		this.ventanaGestionarProductos.show();	
	}
	
	public void volverAtras(ActionEvent a) {
		this.ventanaGestionarProductos.cerrar();
		this.controlador.inicializar();
		this.controlador.mostrarVentanaMenuDeSistemas();

	}
	
	public void realizarBusqueda() {				
		String txtNombre = this.ventanaGestionarProductos.getTxtFieldNombre().getText();
		String txtTalle = this.ventanaGestionarProductos.getTextTalle().getText(); 
		List<MaestroProductoDTO> productosAproximados = this.maestroProducto.getMaestroProductoAproximado("Descripcion",txtNombre,"Talle",txtTalle, null, null, null, null);

		escribirTabla(productosAproximados);
	}
	
	
	public void escribirTablaCompleta() {
		this.ventanaGestionarProductos.getModelProductos().setRowCount(0);//borrar datos de la tabla
		this.ventanaGestionarProductos.getModelProductos().setColumnCount(0);
		this.ventanaGestionarProductos.getModelProductos().setColumnIdentifiers(this.ventanaGestionarProductos.getNombreColumnas());
		productosEnTabla.removeAll(productosEnTabla);
		for(StockDTO s: this.todoElStock) {
			for(MaestroProductoDTO m: this.todosLosProductos) {
				if(m.getIdMaestroProducto()==s.getIdProducto() && idSucursal == s.getIdSucursal()) {
					agregarATabla(s,m);
					productosEnTabla.add(m);
				}
			}
		}
	}
	
	public void escribirTabla(List<MaestroProductoDTO> productosAproximados) {
		productosEnTabla.removeAll(productosEnTabla);
		for(StockDTO s: this.todoElStock) {
			for(MaestroProductoDTO m: productosAproximados) {
				if(m.getIdMaestroProducto()==s.getIdProducto() && idSucursal == s.getIdSucursal()) {
					agregarATabla(s,m);
					productosEnTabla.add(m);
				}
			}
		}
	}
	public void agregarATabla(StockDTO s, MaestroProductoDTO m) {
		int id = m.getIdMaestroProducto();
		String nombre=m.getDescripcion();
		String tipo = m.getTipo();
		String propio = m.getFabricado();
		
		double costoProd = m.getPrecioCosto();
		BigDecimal costoProduccion = new BigDecimal(costoProd);
		
		double precioM = m.getPrecioMayorista();
		BigDecimal precioMayo = new BigDecimal(precioM);
		
		double precioMi = m.getPrecioMinorista();
		BigDecimal precioMino = new BigDecimal(precioMi);
		
		int puntoRepMin = m.getPuntoRepositorio();
		
		int idProv = m.getIdProveedor();
		
		String talle = m.getTalle();
		String medida = m.getUnidadMedida();
		String estado = m.getEstado();
		
		int cantARep = m.getCantidadAReponer();
		int diasARep = m.getDiasParaReponer();

		int stockDisp = s.getStockDisponible();
//		String codLote = s.getCodigoLote();
		Object[] fila = { id,nombre,tipo,propio,costoProduccion,precioMayo,precioMino,puntoRepMin,idProv,talle,medida,estado,cantARep,diasARep,stockDisp};
		this.ventanaGestionarProductos.getModelProductos().addRow(fila);
	}
	
	
	
	
	public void validarTeclado() {
		this.ventanaGestionarProductos.getTxtFieldNombre().addKeyListener((new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarLetrasYEspacios(e);
			}
		}));
		this.ventanaGestionarProductos.getTextTalle().addKeyListener((new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarLetrasYEspacios(e);
			}
		}));
	}
	
}
