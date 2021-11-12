package presentacion.controlador;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;

import dto.CarritoDTO;
import dto.ClienteDTO;
import dto.DetalleCarritoDTO;
import dto.MaestroProductoDTO;
import dto.ProductoEnCarritoDTO;
import dto.StockDTO;
import inicioSesion.empleadoProperties;
import inicioSesion.sucursalProperties;
import modelo.Carrito;
import modelo.DetalleCarrito;
import modelo.MaestroProducto;
import modelo.Stock;
import modelo.Sucursal;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.vista.VentanaBusquedaProductos;
import java.time.LocalDateTime;

public class ControladorBusquedaProductos {
	
	private int idSucursal = 0;// esto esta hardcodeado
	private int idVendedor = 0;

	public void obtenerDatosPropertiesSucursalEmpleado() {
		try {
			sucursalProperties sucursalProp = sucursalProperties.getInstance();
			idSucursal = Integer.parseInt(sucursalProp.getValue("IdSucursal"));

			empleadoProperties empleadoProp = empleadoProperties.getInstance();
			idVendedor = Integer.parseInt(empleadoProp.getValue("IdEmpleado"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
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
	List<StockDTO> stockEnTabla;
	List<ProductoEnCarritoDTO> productosEnCarrito;
	List<CarritoDTO> listaCarrito;
	
	VentanaBusquedaProductos vistaBusquedaProductos;
	
	ClienteDTO clienteSeleccionado;
		
	public ControladorBusquedaProductos(MaestroProducto maestroProducto, Stock stock, Sucursal sucursal,Carrito carrito,DetalleCarrito detalleCarrito) {
		this.sucursal = sucursal;
		this.maestroProducto = maestroProducto;
		this.stock = stock;
		this.carrito = carrito;
		this.detalleCarrito = detalleCarrito;
	}
	
	public void setControladorBusquedaCliente(ControladorBusquedaCliente c) {
		this.controladorBusquedaCliente = c;
	}

	public void inicializar() {
		obtenerDatosPropertiesSucursalEmpleado();
		this.listaStock = recuperarListaDeStock();
		this.listaMaestroProducto = this.maestroProducto.readAll();
		//estos dos se guardan en la aplicacion no lo obtenemos de la bd
		this.productosEnTabla = new ArrayList<MaestroProductoDTO>();
		this.stockEnTabla = new ArrayList<StockDTO>();
		this.productosEnCarrito=new ArrayList<ProductoEnCarritoDTO>();
		this.listaCarrito = carrito.readAll();
		
		this.vistaBusquedaProductos = new VentanaBusquedaProductos();
		this.maestroProducto = new MaestroProducto(new DAOSQLFactory());
		
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
		
		
		this.vistaBusquedaProductos.getTableCarrito().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ListSelectionModel rowSMCarrito = this.vistaBusquedaProductos.getTableCarrito().getSelectionModel();
		
		rowSMCarrito.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				int filaSeleccionada = vistaBusquedaProductos.getTableCarrito().getSelectedRow();
				if(filaSeleccionada==-1) return;
//				//cuando se clickea en la tabla, se actualiza el spinner
				vistaBusquedaProductos.getSpinnerCarrito().setValue((int) productosEnCarrito.get(filaSeleccionada).getCantidad()); 
			}
			
		});
		
//		this.vistaBusquedaProductos.getTableCarrito().addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				int filaSeleccionada = vistaBusquedaProductos.getTableCarrito().rowAtPoint(e.getPoint());
//				//cuando se clickea en la tabla, se actualiza el spinner
//				vistaBusquedaProductos.getSpinnerCarrito().setValue(productosEnCarrito.get(filaSeleccionada).getCantidad()); 
//			}
//		});
		
		this.vistaBusquedaProductos.getSpinnerCarrito().addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				//actualizar cantidad
				modificarCantDeCarritoDadoSpinner();
			}
			
		});
		
		this.vistaBusquedaProductos.getSpinnerPrecioHasta().addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
//				modificarValorMaximoDeSpinnerDesde();
				realizarBusqueda();
			}
		});
		
		this.vistaBusquedaProductos.getSpinnerPrecioDesde().addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				realizarBusqueda();
			}
		});
		
		this.vistaBusquedaProductos.getTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ListSelectionModel rowSMProducto = this.vistaBusquedaProductos.getTable().getSelectionModel();

		rowSMProducto.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				int filaSeleccionada = vistaBusquedaProductos.getTable().getSelectedRow();
				if (filaSeleccionada == -1)
					return;
				actualizarColorSeleccionTabla();
			}

		});
		
		validarTeclado();
		
		escribirTablaCliente();
		escribirTablaCompleta();
		this.vistaBusquedaProductos.show();
		}
	
	
	
	
	public void validarTeclado() {
		this.vistaBusquedaProductos.getTxtNombreProducto().addKeyListener((new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarLetrasYEspacios(e);
			}
		}));
		this.vistaBusquedaProductos.getTxtTalle().addKeyListener((new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarLetrasYEspacios(e);
			}
		}));
	}
	
	
	//Busca el producto dado el nombre y el idSucursal
//	public void realizarBusqueda(ActionEvent a) {
	public void realizarBusqueda() {
		this.vistaBusquedaProductos.getModelTabla().setRowCount(0);//borrar datos de la tabla
		this.vistaBusquedaProductos.getModelTabla().setColumnCount(0);
		this.vistaBusquedaProductos.getModelTabla().setColumnIdentifiers(this.vistaBusquedaProductos.getNombreColumnas());
				
		String txtNombre = this.vistaBusquedaProductos.getTxtNombreProducto().getText();
		String txtTalle = this.vistaBusquedaProductos.getTxtTalle().getText(); 
		String precioDesde =  ""+this.vistaBusquedaProductos.getSpinnerPrecioDesde().getValue();
		String precioHasta = ""+ this.vistaBusquedaProductos.getSpinnerPrecioHasta().getValue();
		
		List<MaestroProductoDTO> productosAproximados;

		if(this.clienteSeleccionado.getTipoCliente().equals("Mayorista")) {
			productosAproximados = this.maestroProducto.getMaestroProductoAproximado("Descripcion",txtNombre,"Talle",txtTalle,"PrecioMayorista",precioDesde,"PrecioMayorista",precioHasta);
		}else {
			productosAproximados = this.maestroProducto.getMaestroProductoAproximado("Descripcion",txtNombre,"Talle",txtTalle,"PrecioMinorista",precioDesde,"PrecioMinorista",precioHasta);
			}

		escribirTabla(productosAproximados);
	}
	
	
	public void escribirTablaCompleta() {
		productosEnTabla.removeAll(productosEnTabla);
		this.stockEnTabla.removeAll(stockEnTabla);
		for(StockDTO s: this.listaStock) {
			for(MaestroProductoDTO m: this.listaMaestroProducto) {
				if(m.getIdMaestroProducto()==s.getIdProducto() && idSucursal == s.getIdSucursal() && esAptoParaVender(s,m) && m.getTipo().equals("PT")) {
					agregarATabla(s,m);
					productosEnTabla.add(m);
					stockEnTabla.add(s);
				}
			}
		}
	}
	
	private ArrayList<StockDTO> recuperarListaDeStock(){
		ArrayList<StockDTO> todoElStock = (ArrayList<StockDTO>) this.stock.readAll();
		
		ArrayList<StockDTO> ret = new ArrayList<StockDTO>();
		
		for(StockDTO s: todoElStock) {
			if(s.getStockDisponible()>0) {
				ret.add(s);
			}
		}
		return ret;
	}
	
	public void escribirTabla(List<MaestroProductoDTO> productosAproximados) {
		productosEnTabla.removeAll(productosEnTabla);
		this.stockEnTabla.removeAll(stockEnTabla);
		for(StockDTO s: this.listaStock) {
			for(MaestroProductoDTO m: productosAproximados) {
				if(m.getIdMaestroProducto()==s.getIdProducto() && idSucursal == s.getIdSucursal() && esAptoParaVender(s,m) && m.getTipo().equals("PT")) {
					agregarATabla(s,m);
					productosEnTabla.add(m);
					stockEnTabla.add(s);
				}
			}
		}
	}
	public void agregarATabla(StockDTO s, MaestroProductoDTO m) {
		String nombre=m.getDescripcion();
		String talle = m.getTalle();
		double preci;
		if(this.clienteSeleccionado.getTipoCliente().equals("Mayorista")) {
			preci = m.getPrecioMayorista();
		}else {
			preci = m.getPrecioMinorista();
		}
		BigDecimal precio = new BigDecimal(preci);
		Double stockDisp = s.getStockDisponible();
		String codLote = s.getCodigoLote();
		Object[] fila = { nombre,talle,precio,stockDisp,codLote};
		this.vistaBusquedaProductos.getModelTabla().addRow(fila);
	}

	
	public boolean esAptoParaVender(StockDTO s, MaestroProductoDTO m) {
		return m.getEstado().equals("Activo") && m.getTipo().equals("PT");
	}
	
	
	//Carrito
	
	public void aniadirProductoAlCarrito(ActionEvent a) {
		int filaSeleccionada = this.vistaBusquedaProductos.getTable().getSelectedRow();
		if(this.vistaBusquedaProductos.getTable().getSelectedRow()==-1) {
			JOptionPane.showMessageDialog(null, "No ha seleccionado ningun producto para agregar");
			return;
		}
		MaestroProductoDTO productoSeleccionado = productosEnTabla.get(filaSeleccionada);
		StockDTO stockDeProductoSeleccionado = this.stockEnTabla.get(filaSeleccionada);
		int cantSeleccionada = (int) this.vistaBusquedaProductos.getSpinnerProductos().getValue();
		if(!laCantidadEsValida(cantSeleccionada,productoSeleccionado.getIdMaestroProducto(),null)) {
			JOptionPane.showMessageDialog(null, "La cantidad elegida no es valida");
			return;
		}
		//verificamos si existe este prod en el carrito
		for(ProductoEnCarritoDTO c: this.productosEnCarrito) {
			if(productoSeleccionado.getIdMaestroProducto() == c.getProducto().getIdMaestroProducto()
					&& c.getStock().getCodigoLote()==stockDeProductoSeleccionado.getCodigoLote() && c.getStock().getIdStock()==stockDeProductoSeleccionado.getIdStock()) {
				modificarCantStock(productoSeleccionado,c.getStock().getIdStock(),-cantSeleccionada);
				c.aniadirProducto(cantSeleccionada);
				actualzarTablaCarrito();
				realizarBusqueda();
				return;
			}
		}
		//si no existe se crea la entidad ProductoEnCarritoDTO
		for(StockDTO s: this.listaStock) {
			if(s.getIdProducto()==productoSeleccionado.getIdMaestroProducto() && s.getIdSucursal()==idSucursal && s.getIdStock()==stockDeProductoSeleccionado.getIdStock() && s.getCodigoLote()==stockDeProductoSeleccionado.getCodigoLote()) {
				modificarCantStock(productoSeleccionado,s.getIdStock(),-cantSeleccionada);
				this.productosEnCarrito.add(new ProductoEnCarritoDTO(productoSeleccionado,s,cantSeleccionada));
			}
		}
		
		actualzarTablaCarrito();
		realizarBusqueda();
		
	}
	
	public void modificarCantStock(MaestroProductoDTO producto, int IdStock, double cant) {
		for(StockDTO s: this.listaStock) {
			if(s.getIdProducto()==producto.getIdMaestroProducto() && IdStock == s.getIdStock()) {
				Double valor = s.getStockDisponible()+cant;
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
			BigDecimal cantObj = new BigDecimal(m.getCantidad());
			
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
		int valorDelSpinner = (int)this.vistaBusquedaProductos.getSpinnerCarrito().getValue(); 
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
		double diferencia = productoEnCarrito.getCantidad() - valorDelSpinner;
		
		modificarCantStock(productoEnCarrito.getProducto(),productoEnCarrito.getStock().getIdStock(),diferencia);

		productoEnCarrito.cambiarCantidad(valorDelSpinner);

		realizarBusqueda();
		actualzarTablaCarrito();
	}
	
	public boolean laCantidadEsValida(int valorDelSpinner,int idMaestroProducto, ProductoEnCarritoDTO prod) {
		for(StockDTO s: this.listaStock) {
			if(idMaestroProducto == s.getIdProducto() && s.getIdSucursal()==this.idSucursal) {
				if(prod!=null) {//ESTAMOS VALIDANDO UNA AGREGACION/DESCUENTO EN CARRITO
					Double total = s.getStockDisponible()+ prod.getCantidad();
					return (s.getStockDisponible()==0 && valorDelSpinner <= prod.getCantidad() ) || 
						   (s.getStockDisponible()!=0 && (valorDelSpinner > 0 && valorDelSpinner <= total )  );

				}else {
//					boolean a=s.getStockDisponible() >= valorDelSpinner && valorDelSpinner > 0 ;

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
		int resp = JOptionPane.showConfirmDialog(null, "Estas seguro que desea armar la venta?", "Armar venta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if(this.productosEnCarrito.size()==0) {
			JOptionPane.showMessageDialog(null, "No ha agregado ningun producto en el carrito!");
			return;
		
		}
		//si selecciona que si devuelve un 0, no un 1, y la x un -1
		if(resp==0) {

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
			JOptionPane.showConfirmDialog(null, "Venta armada con exito a las "+dtf.format(LocalDateTime.now())+".\n "
					+ "En espera de ser efectuada por un cajero", "Armar venta", JOptionPane.CLOSED_OPTION, JOptionPane.QUESTION_MESSAGE);
			//se deberia guardar en la bd el carrito con el productoACobrar
			if(hayStockDisponible()) {
				guardarVentaArmada();
				descontarDelStock();
				this.vistaBusquedaProductos.cerrar();
				this.controladorBusquedaCliente.inicializar();
				this.controladorBusquedaCliente.mostrarVentana();
			}
			
		}
	}
	
	public void guardarVentaArmada() {
		guardarCarrito();//el carrito solo va a ser uno
		guardarDetalleCarrito();		
	}

	public void guardarCarrito() {
		int idSucursal = this.idSucursal;
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss"); 
		String hora = dtf.format(LocalDateTime.now());
		
		CarritoDTO carrito = new CarritoDTO(0,idSucursal,this.clienteSeleccionado.getIdCliente(),this.idVendedor,this.Preciototal,hora);
		this.carrito.insert(carrito);
		
		this.listaCarrito = this.carrito.readAll();
	}
	
	
	public void guardarDetalleCarrito() {
//		El id ser� el mismo para todos los sig prod
		int ultIdCarrito = this.listaCarrito.get(this.listaCarrito.size()-1).getIdCarrito();
		
		for(ProductoEnCarritoDTO compra: this.productosEnCarrito) {
			int idProducto = compra.getProducto().getIdMaestroProducto();
			int idStock = compra.getStock().getIdStock();
			int cant = (int)compra.getCantidad();
			double precio;
			if(this.clienteSeleccionado.getTipoCliente().equals("Mayorista")) {
				precio = compra.getProducto().getPrecioMayorista() * compra.getCantidad();
			}else {
				precio = compra.getProducto().getPrecioMinorista() * compra.getCantidad();
			}
			
			DetalleCarritoDTO detalleCarrito = new DetalleCarritoDTO(0,ultIdCarrito,idProducto,idStock,cant,precio);
			this.detalleCarrito.insert(detalleCarrito);
		}
	}
		
	public boolean hayStockDisponible() {
		boolean ret = true;
		//actualizamos la lista de stockdisponible
		this.listaStock = recuperarListaDeStock();
		for(StockDTO s: this.listaStock) {
			for(ProductoEnCarritoDTO compra: this.productosEnCarrito) {
				if(s.getIdProducto()==compra.getProducto().getIdMaestroProducto() && s.getIdSucursal()== this.idSucursal) {
					ret = ret && s.getStockDisponible()>0; 
				}
			}
		}
		return ret;
	}	
		
		
	public void descontarDelStock() {
		for(StockDTO stock: this.listaStock) {
			for(ProductoEnCarritoDTO compra: this.productosEnCarrito) {
				if(stock.getIdProducto() == compra.getProducto().getIdMaestroProducto() && stock.getIdSucursal() == this.idSucursal) {
					Double nuevoValor = stock.getStockDisponible() - compra.getCantidad(); 
					boolean a = this.stock.actualizarStock(stock.getIdStock(), nuevoValor);
					if(!a) {
						JOptionPane.showMessageDialog(null, "Ha ocurrido un error al descontar del stock");
						return;
					}
				}
			}
		}
	}
	
	public void establecerClienteElegido(ClienteDTO cliente) {
		this.clienteSeleccionado = cliente;
	}
	
	public void escribirTablaCliente() {
		String nombre= this.clienteSeleccionado.getNombre();
		String apellido = this.clienteSeleccionado.getApellido();
		String DNI = this.clienteSeleccionado.getCUIL();
		String tipoCliente = this.clienteSeleccionado.getTipoCliente();
		Object[] fila = { nombre,apellido,DNI, tipoCliente};
		this.vistaBusquedaProductos.getModelTablaCliente().addRow(fila);			
	}
	
	public void modificarValorMaximoDeSpinnerDesde() {
		int valorMaximo = (int)this.vistaBusquedaProductos.getSpinnerPrecioHasta().getValue();
		System.out.println("se deberia actualizar el valor maximo del desde, nuevo maxiom: "+valorMaximo);
		this.vistaBusquedaProductos.setSpinnerModelDesde(new SpinnerNumberModel(0, 0, valorMaximo, 100));
	}
	

	
	public void actualizarColorSeleccionTabla() {
		this.vistaBusquedaProductos.getTable().setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
		    public Component getTableCellRendererComponent(JTable table,
		            Object value, boolean isSelected, boolean hasFocus, int row, int col) {

		        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
		        
		        if(isSelected) {    
//		        	if((int)table.getValueAt(row, 14) > 0) {
//		        		setBackground(Color.lightGray);
//			        	setForeground(Color.WHITE);	
//		        	}
		        	
		        }else {
		        	if((Double)table.getValueAt(row, 3) == 0) {
			        	setBackground(Color.red);
			        	setForeground(Color.WHITE);	
		        	}else {
		        		setBackground(table.getBackground());
			        	setForeground(Color.BLACK);
		        	}
		        }
		        
		        return this;
		    }   
		});
	}
}
