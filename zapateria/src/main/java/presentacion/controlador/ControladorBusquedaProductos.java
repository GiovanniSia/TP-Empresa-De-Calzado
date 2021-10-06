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

import dto.ClienteDTO;
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
	
	
	List<StockDTO> listaStock;
	
	Sucursal sucursal;
	MaestroProducto maestroProducto;
	Stock stock;
	
	vistaBusquedaProductos vistaBusquedaProductos;
	
	List<MaestroProductoDTO> listaMaestroProducto;
	
	ClienteDTO clienteSeleccionado;
	
	List<MaestroProductoDTO> productosEnTabla;
	
	List<ProductoEnCarritoDTO> carrito; 
	
//	public ControladorBusquedaProductos(MaestroProducto maestroProducto, Stock stock, Sucursal sucursal) {
//		this.maestroProducto = maestroProducto;
//		this.stock = stock;
//		this.sucursal = sucursal;
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
		System.out.println("se ejecuta el controlador de productos");
		this.carrito=new ArrayList<ProductoEnCarritoDTO>();
		productosEnTabla = new ArrayList<MaestroProductoDTO>();
		this.vistaBusquedaProductos = new vistaBusquedaProductos();
		this.maestroProducto = new MaestroProducto(new DAOSQLFactory());
		this.stock = new Stock(new DAOSQLFactory());
				
		this.listaMaestroProducto = this.maestroProducto.readAll();
		this.listaStock = this.stock.readAll();
//		this.vistaBusquedaProductos.getTxtNombreProducto().setText(this.clienteSeleccionado.getNombre());
		
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

		this.vistaBusquedaProductos.getBtnAniadirProd().addActionListener(a -> aniadirProductoAlCarrito(a));
		this.vistaBusquedaProductos.getBtnQuitarProducto().addActionListener(a -> quitarProductoDelCarrito(a));
		this.vistaBusquedaProductos.getBtnAtras().addActionListener(a -> volverAtras(a));
		this.vistaBusquedaProductos.getBtnPasarAElegir().addActionListener(a -> avanzarPantalla(a));
		this.vistaBusquedaProductos.getTableCarrito().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int filaSeleccionada = vistaBusquedaProductos.getTableCarrito().rowAtPoint(e.getPoint());
				//cuando se clickea en la tabla, se actualiza el spinner
				vistaBusquedaProductos.getSpinner().setValue(carrito.get(filaSeleccionada).getCantidad()); 
			}
		});
		
		this.vistaBusquedaProductos.getSpinner().addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				//actualizar cantidad
				modificarCantDeCarritoDadoSpinner();
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
		String txtTalle = this.vistaBusquedaProductos.getTxtTalle().getText(); 
		
		List<MaestroProductoDTO> productosAproximados = this.maestroProducto.getMaestroProductoAproximado("Descripcion",txtNombre,"Talle",txtTalle);
		System.out.println("productos aprox: "+productosAproximados.size());
		escribirTabla(productosAproximados);
	}
	
	
	public void escribirTablaCompleta() {
		productosEnTabla.removeAll(productosEnTabla);
		System.out.println("deberia escribir tabla completa");
		for(StockDTO s: this.listaStock) {
			for(MaestroProductoDTO m: this.listaMaestroProducto) {
				if(m.getIdMaestroProducto()==s.getIdProducto() && idSucursal == s.getIdSucursal() && esAptoParaVender(s,m)) {
					agregarATabla(s,m);
					productosEnTabla.add(m);
					System.out.println("se deberia agregar prod");
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
		double precioMinorista = m.getPrecioMinorista();
		int stockDisp = s.getStockDisponible();
		String codLote = s.getCodigoLote();
		Object[] fila = { nombre,talle,precioMinorista,stockDisp,codLote};
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
		//verificamos si existe este prod en el carrito
		for(ProductoEnCarritoDTO c: this.carrito) {
			if(productoSeleccionado.getIdMaestroProducto() == c.getProducto().getIdMaestroProducto()) {
				modificarCantStock(productoSeleccionado,c.getStock().getIdStock(),-1);
				c.aniadirProducto();
				actualzarTablaCarrito();
				realizarBusqueda();
				return;
			}
		}
		//si no existe se crea la entidad ProductoEnCarritoDTO
		for(StockDTO s: this.listaStock) {
			if(s.getIdProducto()==productoSeleccionado.getIdMaestroProducto() && s.getIdSucursal()==idSucursal) {
				modificarCantStock(productoSeleccionado,s.getIdStock(),-1);
				this.carrito.add(new ProductoEnCarritoDTO(productoSeleccionado,s,1));
			}
		}
		actualzarTablaCarrito();
		realizarBusqueda();
//		System.out.println(productoSeleccionado.toString());
		
	}
	
	public void modificarCantStock(MaestroProductoDTO producto, int IdStock, int cant) {
		for(StockDTO s: this.listaStock) {
			if(s.getIdProducto()==producto.getIdMaestroProducto() && IdStock == s.getIdStock()) {
				int valor = s.getStockDisponible()+cant;
				s.setStockDisponible(valor);
				return;
			}
		}
	}
	
	public void actualzarTablaCarrito() {
		this.vistaBusquedaProductos.getModelTablaCarrito().setRowCount(0);//borrar datos de la tabla
		this.vistaBusquedaProductos.getModelTablaCarrito().setColumnCount(0);
		this.vistaBusquedaProductos.getModelTablaCarrito().setColumnIdentifiers(this.vistaBusquedaProductos.getNombreColumnasCarrito());
		
		for(ProductoEnCarritoDTO m: this.carrito) {
			String nombre=m.getProducto().getDescripcion();
			double precioVenta = m.getProducto().getPrecioMinorista();
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
				total += m.getProducto().getPrecioMinorista();
			}
		}
		this.vistaBusquedaProductos.getLblValorTotal().setText("$"+total);
	}
	
	public void quitarProductoDelCarrito(ActionEvent a) {
		int filaSeleccionada = this.vistaBusquedaProductos.getTableCarrito().getSelectedRow();
		if(this.vistaBusquedaProductos.getTableCarrito().getSelectedRow()==-1) {
			JOptionPane.showMessageDialog(null, "No ha seleccionado ningun producto para quitar");
			return;
		}
		ProductoEnCarritoDTO productoSeleccionado = this.carrito.get(filaSeleccionada);
		carrito.remove(productoSeleccionado);
		modificarCantStock(productoSeleccionado.getProducto(),productoSeleccionado.getStock().getIdStock(),productoSeleccionado.getCantidad());
		actualzarTablaCarrito();
		realizarBusqueda();
	}
	
	public void modificarCantDeCarritoDadoSpinner() {
		int filaSeleccionada = this.vistaBusquedaProductos.getTableCarrito().getSelectedRow();
		if(this.vistaBusquedaProductos.getTableCarrito().getSelectedRow()==-1) {
			JOptionPane.showMessageDialog(null, "No ha seleccionado ningun producto para descontar");
			return;
		}
		//si el valor que se acaba de actualizar es el mismo al de la cantidad original significa que no se hizo click sobre la tabla, no se desconto valor desde el spinner
		if((int)this.vistaBusquedaProductos.getSpinner().getValue()==this.carrito.get(filaSeleccionada).getCantidad()) {
			return;
		}		
		if((int)this.vistaBusquedaProductos.getSpinner().getValue()==0) {
			quitarProductoDelCarrito(null);
			return;
		}
		ProductoEnCarritoDTO productoEnCarrito = this.carrito.get(filaSeleccionada);
		
		int cantSpinner = (int)this.vistaBusquedaProductos.getSpinner().getValue();
		
		//dado el spinner si el nuevo valor es menor que la cant del carrito significa que se le resta al carrito (se le suma al stock original), si es mayor se le suma uno al carrito (se le descuenta al stock original)
		int diferencia = productoEnCarrito.getCantidad() - cantSpinner;
		
		modificarCantStock(productoEnCarrito.getProducto(),productoEnCarrito.getStock().getIdStock(),diferencia);

		productoEnCarrito.cambiarCantidad(cantSpinner);

		realizarBusqueda();
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
	

	public void establecerClienteElegido(ClienteDTO cliente) {
		this.clienteSeleccionado = cliente;
	}
	
	
	public static void main(String[] args) {
		new ControladorBusquedaProductos();
	}
	
}
