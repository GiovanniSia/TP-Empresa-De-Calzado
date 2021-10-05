package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import dto.MaestroProductoDTO;
import dto.StockDTO;
import modelo.MaestroProducto;
import modelo.Stock;
import modelo.Sucursal;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.vista.vistaBusquedaProductos;

public class ControladorBusquedaProductos {
	
	private final int idSucursal=1;
	private final List<MaestroProductoDTO> carrito;
	
	Stock stock;
	List<StockDTO> listaStock;
	Sucursal sucursal;
	
	vistaBusquedaProductos vistaBusquedaProductos;
	
	List<MaestroProductoDTO> listaMaestroProducto;
	MaestroProducto maestroProducto;
	
	List<MaestroProductoDTO> productosEnTabla;
	
	public ControladorBusquedaProductos(MaestroProducto maestroProducto, Stock stock, Sucursal sucursal) {
		this.maestroProducto = maestroProducto;
		this.stock = stock;
		this.sucursal = sucursal;
		carrito=new ArrayList<MaestroProductoDTO>();
		productosEnTabla = new ArrayList<MaestroProductoDTO>();
		this.vistaBusquedaProductos = new vistaBusquedaProductos();
		this.maestroProducto = new MaestroProducto(new DAOSQLFactory());
		this.stock = new Stock(new DAOSQLFactory());
		inicializar();
	}
//	public ControladorBusquedaProductos() {
//		carrito=new ArrayList<MaestroProductoDTO>();
//		productosEnTabla = new ArrayList<MaestroProductoDTO>();
//		this.vistaBusquedaProductos = new vistaBusquedaProductos();
//		this.maestroProducto = new MaestroProducto(new DAOSQLFactory());
//		this.stock = new Stock(new DAOSQLFactory());
//		inicializar();
//	}
	
	public void inicializar() {
		this.listaMaestroProducto = this.maestroProducto.readAll();
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

		this.vistaBusquedaProductos.getBtnAniadirProd().addActionListener(a -> aniadirProductoAlCarrito(a));
		
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
		
		List<MaestroProductoDTO> productosAproximados = this.maestroProducto.getMaestroProductoAproximado("Descripcion",txtNombre,"Talle",txtTalle,"PrecioVenta",txtPrecio);
		
		escribirTabla(productosAproximados);
	}
	public void escribirTablaCompleta() {
		productosEnTabla.removeAll(productosEnTabla);
		for(StockDTO s: this.listaStock) {
			for(MaestroProductoDTO m: this.listaMaestroProducto) {
				if(m.getIdMaestroProducto()==s.getIdProducto() && idSucursal == s.getIdSucursal() && esAptoParaVender(s,m)) {
					agregarATabla(s,m);
					productosEnTabla.add(m);
				}
			}
		}
	}
	
	public void escribirTabla(List<MaestroProductoDTO> productosAproximados) {
		productosEnTabla.removeAll(productosEnTabla);
		for(StockDTO s: this.listaStock) {
			for(MaestroProductoDTO m: productosAproximados) {
				if(m.getIdMaestroProducto()==s.getIdProducto() && idSucursal == s.getIdSucursal() && esAptoParaVender(s,m)) {
					agregarATabla(s,m);
					productosEnTabla.add(m);
				}
			}
		}
	}
	public void agregarATabla(StockDTO s, MaestroProductoDTO m) {
		
		
//private String[] nombreColumnasProductosFiltrados = { "Nombre", "Talle", "Precio Venta"};
		String nombre=m.getDescripcion();
		String talle = m.getTalle();
		int precioVenta = m.getPrecioVenta();
		int stockDisp = s.getStockDisponible();
		String codLote = s.getCodigoLote();
		Object[] fila = { nombre,talle,precioVenta,stockDisp,codLote};
		this.vistaBusquedaProductos.getModelTabla().addRow(fila);
	}

	
	public boolean esAptoParaVender(StockDTO s, MaestroProductoDTO m) {
		return m.getEstado().equals("Activo") && m.getTipo().equals("PT") && m.getFabricado().equals("S");
	}
	
	
	
	public void aniadirProductoAlCarrito(ActionEvent a) {
		int filaSeleccionada = this.vistaBusquedaProductos.getTable().getSelectedRow();
		if(this.vistaBusquedaProductos.getTable().getSelectedRow()==-1) {
			JOptionPane.showMessageDialog(null, "No ha seleccionado ningun producto para agregar");
			return;
		}
		MaestroProductoDTO productoSeleccionado = productosEnTabla.get(filaSeleccionada);
		carrito.add(productoSeleccionado);
		llenarTablaCarrito(productoSeleccionado);
		System.out.println(productoSeleccionado.toString());
		
	}
	
	
	public void llenarTablaCarrito(MaestroProductoDTO productoSeleccionado) {
		String nombre=productoSeleccionado.getDescripcion();
		int precioVenta = productoSeleccionado.getPrecioVenta();
		
		Object[] fila = { nombre,1,precioVenta};
		this.vistaBusquedaProductos.getModelTablaCarrito().addRow(fila);
	}
	
//	public static void main(String[] args) {
//		new ControladorBusquedaProductos();
//	}
}
