package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import dto.CarritoDTO;
import dto.ClienteDTO;
import dto.DetalleCarritoDTO;
import dto.MaestroProductoDTO;
import dto.ProductoEnCarritoDTO;
import dto.StockDTO;
import modelo.Carrito;
import modelo.Cliente;
import modelo.DetalleCarrito;
import modelo.MaestroProducto;
import modelo.Stock;
import modelo.Sucursal;
import persistencia.conexion.Conexion;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.vista.vistaBusquedaProductos;
import java.time.LocalDateTime;
public class ControladorBusquedaProductos {
	
	private final int idSucursal=1;//esto esta hardcodeado
//	private final List<MaestroProductoDTO> carrito;
	int Preciototal;
	
	ControladorBusquedaCliente controladorBusquedaCliente;
	
	Sucursal sucursal;
	MaestroProducto maestroProducto;
	Stock stock;
	Carrito carrito;
	DetalleCarrito detalleCarrito;
	
	List<StockDTO> listaStock;
	List<MaestroProductoDTO> listaMaestroProducto;
	List<MaestroProductoDTO> productosEnTabla;
	List<ProductoEnCarritoDTO> productosEnCarrito;
	List<CarritoDTO> listaCarrito;
	
	vistaBusquedaProductos vistaBusquedaProductos;
	
	ClienteDTO clienteSeleccionado;
		
	public ControladorBusquedaProductos(MaestroProducto maestroProducto, Stock stock, Sucursal sucursal) {
		this.sucursal = sucursal;
		this.maestroProducto = maestroProducto;
		this.stock = stock;
		this.carrito = new Carrito(new DAOSQLFactory());
		this.detalleCarrito = new DetalleCarrito(new DAOSQLFactory());
	}
	
	public void setControladorBusquedaCliente(ControladorBusquedaCliente c) {
		this.controladorBusquedaCliente = c;
	}
	
//	public ControladorBusquedaProductos() {
//		carrito=new ArrayList<ProductoEnCarritoDTO>();
//		productosEnTabla = new ArrayList<MaestroProductoDTO>();
//		this.vistaBusquedaProductos = new vistaBusquedaProductos();
//		this.maestroProducto = new MaestroProducto(new DAOSQLFactory());
//		this.stock = new Stock(new DAOSQLFactory());
//		inicializar();
//	}
	
	public void inicializar() {
		this.listaStock = this.stock.readAll();
		this.listaMaestroProducto = this.maestroProducto.readAll();
		//estos dos se guardan en la aplicacion no lo obtenemos de la bd
		this.productosEnTabla = new ArrayList<MaestroProductoDTO>();
		this.productosEnCarrito=new ArrayList<ProductoEnCarritoDTO>();
		this.listaCarrito = carrito.readAll();
		
		this.vistaBusquedaProductos = new vistaBusquedaProductos();
		this.maestroProducto = new MaestroProducto(new DAOSQLFactory());


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
		this.vistaBusquedaProductos.getBtnPasarAElegir().addActionListener(a -> armarVenta(a));
		this.vistaBusquedaProductos.getTableCarrito().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int filaSeleccionada = vistaBusquedaProductos.getTableCarrito().rowAtPoint(e.getPoint());
				//cuando se clickea en la tabla, se actualiza el spinner
				vistaBusquedaProductos.getSpinner().setValue(productosEnCarrito.get(filaSeleccionada).getCantidad()); 
			}
		});
		
		this.vistaBusquedaProductos.getSpinner().addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				//actualizar cantidad
				modificarCantDeCarritoDadoSpinner();
			}
			
		});
		escribirTablaCliente();
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
		String nombre=m.getDescripcion();
		String talle = m.getTalle();
		double precio;
		if(this.clienteSeleccionado.getTipoCliente().equals("Mayorista")) {
			precio = m.getPrecioMayorista();
		}else {
			precio = m.getPrecioMinorista();
		}
		int stockDisp = s.getStockDisponible();
		String codLote = s.getCodigoLote();
		Object[] fila = { nombre,talle,precio,stockDisp,codLote};
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
		int cantSeleccionada = (int) this.vistaBusquedaProductos.getSpinnerProductos().getValue();
		if(!laCantidadEsValida(cantSeleccionada,productoSeleccionado.getIdMaestroProducto(),null)) {
			JOptionPane.showMessageDialog(null, "La cantidad elegida no es válida");
			return;
		}
		//verificamos si existe este prod en el carrito
		for(ProductoEnCarritoDTO c: this.productosEnCarrito) {
			if(productoSeleccionado.getIdMaestroProducto() == c.getProducto().getIdMaestroProducto()) {
				modificarCantStock(productoSeleccionado,c.getStock().getIdStock(),-cantSeleccionada);
				c.aniadirProducto(cantSeleccionada);
				actualzarTablaCarrito();
				realizarBusqueda();
				return;
			}
		}
		//si no existe se crea la entidad ProductoEnCarritoDTO
		for(StockDTO s: this.listaStock) {
			if(s.getIdProducto()==productoSeleccionado.getIdMaestroProducto() && s.getIdSucursal()==idSucursal) {
				modificarCantStock(productoSeleccionado,s.getIdStock(),-cantSeleccionada);
				this.productosEnCarrito.add(new ProductoEnCarritoDTO(productoSeleccionado,s,cantSeleccionada));
			}
		}
		actualzarTablaCarrito();
		realizarBusqueda();
		
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
		
		for(ProductoEnCarritoDTO m: this.productosEnCarrito) {
			String nombre=m.getProducto().getDescripcion();
			double total;
			if(this.clienteSeleccionado.getTipoCliente().equals("Mayorista")) {
				total = m.getProducto().getPrecioMayorista() * m.getCantidad();
			}else {
				total = m.getProducto().getPrecioMinorista() * m.getCantidad();
			}
			BigDecimal b = new BigDecimal(total);
			int cantObj = m.getCantidad();
			
			String codLote = m.getStock().getCodigoLote();
			String talle = m.getProducto().getTalle();
			Object[] fila = { nombre,cantObj,b,codLote,talle};
			this.vistaBusquedaProductos.getModelTablaCarrito().addRow(fila);			
		}
		calcularPrecioTotal();
	}
	
	public void calcularPrecioTotal() {
		Preciototal=0;
		for(ProductoEnCarritoDTO m: this.productosEnCarrito) {
			if(this.clienteSeleccionado.getTipoCliente().equals("Mayorista")) {
				Preciototal += m.getProducto().getPrecioMayorista() * m.getCantidad();
			}else {
				Preciototal += m.getProducto().getPrecioMinorista() * m.getCantidad();
			}
		}
		this.vistaBusquedaProductos.getLblValorTotal().setText("$"+Preciototal);
	}
	
	public void quitarProductoDelCarrito(ActionEvent a) {
		int filaSeleccionada = this.vistaBusquedaProductos.getTableCarrito().getSelectedRow();
		if(this.vistaBusquedaProductos.getTableCarrito().getSelectedRow()==-1) {
			JOptionPane.showMessageDialog(null, "No ha seleccionado ningun producto para quitar");
			return;
		}
		ProductoEnCarritoDTO productoSeleccionado = this.productosEnCarrito.get(filaSeleccionada);
		productosEnCarrito.remove(productoSeleccionado);
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
		
		ProductoEnCarritoDTO productoEnCarrito = this.productosEnCarrito.get(filaSeleccionada);
		
		//si el valor que se acaba de actualizar es el mismo al de la cantidad original significa que no se hizo click sobre la tabla, no se desconto valor desde el spinner
		int valorDelSpinner = (int)this.vistaBusquedaProductos.getSpinner().getValue(); 
		if(valorDelSpinner == this.productosEnCarrito.get(filaSeleccionada).getCantidad()) {
			return;
		}		
		if(valorDelSpinner == 0) {
			quitarProductoDelCarrito(null);
			return;
		}
		if(!laCantidadEsValida(valorDelSpinner,productoEnCarrito.getProducto().getIdMaestroProducto(),productoEnCarrito)) {
			JOptionPane.showMessageDialog(null, "La cantidad elegida no es válida");
			return;
		}
		
		//dado el spinner si el nuevo valor es menor que la cant del carrito significa que se le resta al carrito (se le suma al stock original), si es mayor se le suma uno al carrito (se le descuenta al stock original)
		int diferencia = productoEnCarrito.getCantidad() - valorDelSpinner;
		
		modificarCantStock(productoEnCarrito.getProducto(),productoEnCarrito.getStock().getIdStock(),diferencia);

		productoEnCarrito.cambiarCantidad(valorDelSpinner);

		realizarBusqueda();
		actualzarTablaCarrito();
	}
	
	public boolean laCantidadEsValida(int valorDelSpinner,int idMaestroProducto, ProductoEnCarritoDTO prod) {
		for(StockDTO s: this.listaStock) {
			if(idMaestroProducto == s.getIdProducto() && s.getIdSucursal()==this.idSucursal) {
				if(prod!=null) {//ESTAMOS VALIDANDO UNA AGREGACION/DESCUENTO EN CARRITO
					int total = s.getStockDisponible()+ prod.getCantidad();
					return (s.getStockDisponible()==0 && valorDelSpinner <= prod.getCantidad() ) || 
						   (s.getStockDisponible()!=0 && (valorDelSpinner > 0 && valorDelSpinner < total )  );

				}else {
					boolean a=s.getStockDisponible() >= valorDelSpinner && valorDelSpinner > 0 ;

					return s.getStockDisponible() >= valorDelSpinner && valorDelSpinner > 0 ;
				}
			}
		}
		System.out.println("no se encontro el prod del carrito :o");
		return false;
	}
	
	
	public void volverAtras(ActionEvent a) {
		this.vistaBusquedaProductos.cerrar();
//		Cliente cliente = new Cliente(new DAOSQLFactory());
//		ControladorBusquedaCliente c = new ControladorBusquedaCliente(cliente);
		
		this.controladorBusquedaCliente.inicializar();
		this.controladorBusquedaCliente.mostrarVentana();
		//se deberia abrir la pantalla anterior
	}
	
	public void armarVenta(ActionEvent a) {
		int resp = JOptionPane.showConfirmDialog(null, "Está segura que desea armar la venta?", "Armar venta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		
		//si selecciona que si devuelve un 0, no un 1, y la x un -1
		if(resp==0) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			JOptionPane.showConfirmDialog(null, "Venta armada con éxito a las "+dtf.format(LocalDateTime.now())+".\n "
					+ "En espera de ser efectuada por un cajero", "Armar venta", JOptionPane.CLOSED_OPTION, JOptionPane.QUESTION_MESSAGE);
			//se deberia guardar en la bd el carrito con el productoACobrar
			guardarVentaArmada();
//			descontarDelStock();
			this.vistaBusquedaProductos.cerrar();
		}
	}
	
	public void guardarVentaArmada() {
		
		int idCliente = this.clienteSeleccionado.getIdCliente();
		int idSucursal = this.idSucursal;
		CarritoDTO carrito = new CarritoDTO(0,idCliente,idSucursal,this.Preciototal);
		this.carrito.insert(carrito);
		//actualizamos la lista de carrito para ver el id del ultimo que se agrego
		this.listaCarrito = this.carrito.readAll();
		
		int ultId = this.listaCarrito.get(this.listaCarrito.size()-1).getIdCarrito();
		
		for(ProductoEnCarritoDTO compra: this.productosEnCarrito) {
			int idProducto = compra.getProducto().getIdMaestroProducto();
			int idStock = compra.getStock().getIdStock();
			int cant = compra.getCantidad();
			double precio;
			if(this.clienteSeleccionado.getTipoCliente().equals("Mayorista")) {
				precio = compra.getProducto().getPrecioMayorista() * compra.getCantidad();
			}else {
				precio = compra.getProducto().getPrecioMinorista() * compra.getCantidad();
			}
			
			DetalleCarritoDTO detalleCarrito = new DetalleCarritoDTO(0,ultId,idProducto,idStock,cant,precio);
			this.detalleCarrito.insert(detalleCarrito);
		}
		
	}

//	public void descontarDelStock() {
//		for(StockDTO stock: this.listaStock) {
//			for(ProductoEnCarritoDTO compra: this.productosEnCarrito) {
//				if(stock.getIdProducto() == compra.getProducto().getIdMaestroProducto() && stock.getIdSucursal() == this.idSucursal) {
//					
//				}
//			}
//		}
//	}
	
	public void establecerClienteElegido(ClienteDTO cliente) {
		this.clienteSeleccionado = cliente;
	}
	
	public void escribirTablaCliente() {
		String nombre= this.clienteSeleccionado.getNombre();
		String apellido = this.clienteSeleccionado.getApellido();
		String DNI = this.clienteSeleccionado.getDNI();
		String tipoCliente = this.clienteSeleccionado.getTipoCliente();
		Object[] fila = { nombre,apellido,DNI, tipoCliente};
		this.vistaBusquedaProductos.getModelTablaCliente().addRow(fila);			
	}
	
//	public static void main(String[] args) {
//		new ControladorBusquedaProductos();
//	}
	
}
