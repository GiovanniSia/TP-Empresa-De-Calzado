package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import dto.MaestroProductoDTO;
import dto.StockDTO;
import modelo.MaestroProducto;
import modelo.Stock;
import modelo.Sucursal;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.vista.vistaBusquedaProductos;

public class ControladorBusquedaProductos {
	
	private final int idSucursal=1;
	
	Stock stock;
	List<StockDTO> listaStock;
	Sucursal sucursal;
	
	vistaBusquedaProductos vistaBusquedaProductos;
	
	List<MaestroProductoDTO> listaMaestroProducto;
	MaestroProducto maestroProducto;
	
//	public ControladorBusquedaProductos(MaestroProducto maestroProducto, Stock stock, Sucursal sucursal) {
//		this.maestroProducto = maestroProducto;
//		this.stock = stock;
//		this.sucursal = sucursal;
//	}
	public ControladorBusquedaProductos() {
		inicializar();
	}
	
	public void inicializar() {
		this.vistaBusquedaProductos = new vistaBusquedaProductos();
		
		this.maestroProducto = new MaestroProducto(new DAOSQLFactory());
		this.listaMaestroProducto = this.maestroProducto.readAll();
		
		this.stock = new Stock(new DAOSQLFactory());
		this.listaStock = this.stock.readAll();
		
		this.vistaBusquedaProductos.getTxtNombreProducto().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				realizarBusqueda();
			}
		});
		this.vistaBusquedaProductos.getTxtTalle().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				realizarBusqueda();
			}
		});
		this.vistaBusquedaProductos.getTxtPrecio().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				realizarBusqueda();
			}
		});
//		this.vistaBusquedaProductos.getTxtIdSucursal().addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyReleased(KeyEvent e) {
//				realizarBusqueda();
//			}
//		});
		escribirTablaCompleta();
		this.vistaBusquedaProductos.show();
		}
	
	
	//Busca el producto dado el nombre y el idSucursal
//	public void realizarBusqueda(ActionEvent a) {
	public void realizarBusqueda() {
		this.vistaBusquedaProductos.getModelTabla().setRowCount(0);//borrar datos de la tabla
		this.vistaBusquedaProductos.getModelTabla().setColumnCount(0);
		this.vistaBusquedaProductos.getModelTabla().setColumnIdentifiers(this.vistaBusquedaProductos.getNombreColumnas());
		
		String txtNombre = this.vistaBusquedaProductos.getTxtNombreProducto().getText();
		String cbCategoria = (String) this.vistaBusquedaProductos.getComboBoxCategoria().getSelectedItem();
		String txtTalle = this.vistaBusquedaProductos.getTxtTalle().getText(); 
		String txtPrecio = this.vistaBusquedaProductos.getTxtPrecio().getText();
		
		if(cbCategoria.equals("Sin seleccionar") &&	txtNombre.equals("") && txtTalle.equals("") && txtPrecio.equals("")) { //todo vacio
			escribirTablaCompleta();
			return;
		}
		
		if(cbCategoria.equals("Sin seleccionar") && txtNombre!=null && txtTalle.equals("") && txtPrecio.equals("")) {//nombre solo
			escribirTablaConUnFiltro("Descripcion",txtNombre);
			return;
		}
		if(cbCategoria.equals("Sin seleccionar") && txtNombre.equals("") && txtTalle != null && txtPrecio.equals("")) {//talle solo
			escribirTablaConUnFiltro("Talle",txtTalle);
			return;
		}
		if(cbCategoria.equals("Sin seleccionar") &&	txtNombre.equals("") && txtTalle.equals("") && txtPrecio!=null) { //precio vacio
			escribirTablaConUnFiltro("PrecioVenta",txtPrecio);
			return;
		}
		if(cbCategoria.equals("Sin seleccionar") && txtNombre != null && txtTalle != null && txtPrecio.equals("")) {//nombre y talle
			escribirTablaConDosFiltros("Descripcion", txtNombre, "Talle", txtTalle);
			return;
		}
		//verificar
		if(cbCategoria.equals("Sin seleccionar") && txtNombre != null && txtTalle.equals("") && txtPrecio!=null) {//nombre y precio
			escribirTablaConDosFiltros("Descripcion", txtNombre, "PrecioVenta", txtPrecio);
			return;
		}
		if(cbCategoria.equals("Sin seleccionar") && txtNombre.equals("") && txtTalle != null && txtPrecio!=null) {//talle y precio
			escribirTablaConDosFiltros("Talle", txtTalle, "PrecioVenta", txtPrecio);
			return;
		}
//		if(cbCategoria.equals("Sin seleccionar") && txtNombre.equals("") && txtTalle != null && txtPrecio!=null) {//TodoCompleto (falta categoria)
//			escribirTablaConTodosLosDatos("Descripcion", txtNombre, "Talle", txtPrecio,"");
//			return;
//		}
	}	
	
	public void escribirTablaCompleta() {
		for(StockDTO s: this.listaStock) {
			for(MaestroProductoDTO m: this.listaMaestroProducto) {
				if(m.getIdMaestroProducto()==s.getIdProducto() && idSucursal == s.getIdSucursal() && esAptoParaVender(s,m)) {
					agregarATabla(s,m);
				}
			}
		}
	}

	public void escribirTablaConUnFiltro(String nombreColumna, String aprox){
		List<MaestroProductoDTO> productosAproximados = this.maestroProducto.getMaestroProductoAproximado(nombreColumna,aprox);

		for(StockDTO s: this.listaStock) {
			for(MaestroProductoDTO m: productosAproximados) {
				if(m.getIdMaestroProducto()==s.getIdProducto() && idSucursal == s.getIdSucursal() && esAptoParaVender(s,m)) {
					agregarATabla(s,m);
				}
			}
		}
	}
	
	public void escribirTablaConDosFiltros(String nombreColumna, String aprox, String nombreColumna2, String aprox2) {
		List<MaestroProductoDTO> productosAproximados = this.maestroProducto.getMaestroProductoAproximado(nombreColumna,aprox);
		List<MaestroProductoDTO> productosAproximados2 = this.maestroProducto.getMaestroProductoAproximado(nombreColumna2,aprox2);
		List<MaestroProductoDTO> productos = interseccionProducto(productosAproximados,productosAproximados2);
		
		for(StockDTO s: this.listaStock) {
			for(MaestroProductoDTO p: productos) {
				if(p.getIdMaestroProducto()==s.getIdProducto() && idSucursal == s.getIdSucursal() && esAptoParaVender(s,p)) {
					agregarATabla(s,p);
				}
			}
		}
	}

	
	public List<MaestroProductoDTO> interseccionProducto(List<MaestroProductoDTO> n, List<MaestroProductoDTO> m){
		List<MaestroProductoDTO> inter = new ArrayList<MaestroProductoDTO>();
		for(MaestroProductoDTO nn: n) {
			for(MaestroProductoDTO mm: m) {
				if(nn.getIdMaestroProducto()==mm.getIdMaestroProducto()) {
					inter.add(nn);
				}
			}
		}
		return inter;
	}
	
	public void agregarATabla(StockDTO s, MaestroProductoDTO m) {
		
//private String[] nombreColumnasProductosFiltrados = { "Nombre", "Talle", "Precio Venta"};
		String nombre=m.getDescripcion();
		String talle = m.getTalle();
		int precioVenta = m.getPrecioVenta();
		
		Object[] fila = { nombre,talle,precioVenta};
		this.vistaBusquedaProductos.getModelTabla().addRow(fila);
	}

	
	public boolean esAptoParaVender(StockDTO s, MaestroProductoDTO m) {
		return m.getEstado().equals("Activo") && m.getTipo().equals("PT") && m.getFabricado().equals("S");
	}
	
	public static void main(String[] args) {
		new ControladorBusquedaProductos();
	}
}
