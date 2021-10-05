package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import dto.MaestroProductoDTO;
import dto.ProductoEnCarritoDTO;
import dto.StockDTO;
import modelo.MaestroProducto;
import modelo.Stock;
import modelo.Sucursal;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.vista.vistaBusquedaProductos;

public class ControladorBusquedaProductos {
	
	private final int idSucursal=1;
//	private final List<MaestroProductoDTO> carrito;
	
	Stock stock;
	List<StockDTO> listaStock;
	Sucursal sucursal;
	
	vistaBusquedaProductos vistaBusquedaProductos;
	
	List<MaestroProductoDTO> listaMaestroProducto;
	MaestroProducto maestroProducto;
	
	List<MaestroProductoDTO> productosEnTabla;
	
	List<ProductoEnCarritoDTO> carrito; 
	
//	public ControladorBusquedaProductos(MaestroProducto maestroProducto, Stock stock, Sucursal sucursal) {
//		this.maestroProducto = maestroProducto;
//		this.stock = stock;
//		this.sucursal = sucursal;
//		carrito=new ArrayList<MaestroProductoDTO>();
//		productosEnTabla = new ArrayList<MaestroProductoDTO>();
//		this.vistaBusquedaProductos = new vistaBusquedaProductos();
//		this.maestroProducto = new MaestroProducto(new DAOSQLFactory());
//		this.stock = new Stock(new DAOSQLFactory());
//		inicializar();
//	}
	public ControladorBusquedaProductos() {
		carrito=new ArrayList<ProductoEnCarritoDTO>();
		productosEnTabla = new ArrayList<MaestroProductoDTO>();
		this.vistaBusquedaProductos = new vistaBusquedaProductos();
		this.maestroProducto = new MaestroProducto(new DAOSQLFactory());
		this.stock = new Stock(new DAOSQLFactory());
		inicializar();
	}
	
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
		this.vistaBusquedaProductos.getBtnQuitarProducto().addActionListener(a -> quitarProductoDelCarrito(a));
		this.vistaBusquedaProductos.getBtnAtras().addActionListener(a -> volverAtras(a));
		this.vistaBusquedaProductos.getBtnPasarAElegir().addActionListener(a -> avanzarPantalla(a));
		this.vistaBusquedaProductos.getTableCarrito().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int filaSeleccionada = vistaBusquedaProductos.getTableCarrito().rowAtPoint(e.getPoint());
				vistaBusquedaProductos.getSpinner().setValue(carrito.get(filaSeleccionada).getCantidad());
			}
		});
		
		this.vistaBusquedaProductos.getSpinner().addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				//actualizar cantidad
				
			}
			
		});
		
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
	
	
	//Carrito
	
	public void aniadirProductoAlCarrito(ActionEvent a) {
		int filaSeleccionada = this.vistaBusquedaProductos.getTable().getSelectedRow();
		if(this.vistaBusquedaProductos.getTable().getSelectedRow()==-1) {
			JOptionPane.showMessageDialog(null, "No ha seleccionado ningun producto para agregar");
			return;
		}
		MaestroProductoDTO productoSeleccionado = productosEnTabla.get(filaSeleccionada);
		for(ProductoEnCarritoDTO p: this.carrito) {
			if(productoSeleccionado.getIdMaestroProducto() == p.getProducto().getIdMaestroProducto()) {
				p.sumarUno();
				actualzarTablaCarrito();
				return;
			}
		}
		this.carrito.add(new ProductoEnCarritoDTO(productoSeleccionado,1));
		actualzarTablaCarrito();
//		System.out.println(productoSeleccionado.toString());
		
	}
	
	
	public void actualzarTablaCarrito() {
		this.vistaBusquedaProductos.getModelTablaCarrito().setRowCount(0);//borrar datos de la tabla
		this.vistaBusquedaProductos.getModelTablaCarrito().setColumnCount(0);
		this.vistaBusquedaProductos.getModelTablaCarrito().setColumnIdentifiers(this.vistaBusquedaProductos.getNombreColumnasCarrito());
		
		for(ProductoEnCarritoDTO m: this.carrito) {
			String nombre=m.getProducto().getDescripcion();
			int precioVenta = m.getProducto().getPrecioVenta();
			int cantObj = m.getCantidad();
			Object[] fila = { nombre,cantObj,precioVenta};
			this.vistaBusquedaProductos.getModelTablaCarrito().addRow(fila);
			
		}
		calcularPrecioTotal();
	}
	
	public void calcularPrecioTotal() {
		int total=0;
		for(ProductoEnCarritoDTO m: this.carrito) {
			for(int i=0; i<m.getCantidad(); i++) {
				total += m.getProducto().getPrecioVenta();
			}
		}
		this.vistaBusquedaProductos.getLblValorTotal().setText("$"+total);
	}
	
	public void quitarProductoDelCarrito(ActionEvent a) {
		int filaSeleccionada = this.vistaBusquedaProductos.getTableCarrito().getSelectedRow();
		System.out.println("fila seleccionada "+ filaSeleccionada);
		if(this.vistaBusquedaProductos.getTable().getSelectedRow()==-1) {
			JOptionPane.showMessageDialog(null, "No ha seleccionado ningun producto para quitar");
			return;
		}
		ProductoEnCarritoDTO productoSeleccionado = this.carrito.get(filaSeleccionada);
		carrito.remove(productoSeleccionado);
		actualzarTablaCarrito();
	}
	
	
	public void volverAtras(ActionEvent a) {
		this.vistaBusquedaProductos.cerrar();
		//se deberia abrir la pantalla anterior
	}
	
	public void avanzarPantalla(ActionEvent a) {
		this.vistaBusquedaProductos.cerrar();
		//se deberia abrir la ventana de elegir cliente
	}
	
	public static void main(String[] args) {
		new ControladorBusquedaProductos();
	}
}
