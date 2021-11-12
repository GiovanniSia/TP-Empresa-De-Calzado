package presentacion.controlador.supervisor;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import dto.MaestroProductoDTO;
import dto.PedidosPendientesDTO;
import dto.ProductoDeProveedorDTO;
import dto.ProveedorDTO;
import dto.StockDTO;

import modelo.PedidosPendientes;
import modelo.ProductoDeProveedor;
import modelo.Proveedor;
import modelo.Stock;
import presentacion.controlador.ValidadorTeclado;
import presentacion.vista.generarPedidoProveedor.VentanaGenerarPedidoProveedor;

public class ControladorGenerarPedidoAProveedorManualmente {

	int idSucursal;
	int idEmpleado;
	
	ProveedorDTO proveedorElegido;
	MaestroProductoDTO productoElegido;
	StockDTO stockDeProd;
	
	Proveedor proveedor;
	List<ProveedorDTO> todosLosProveedores;
	
	List<ProveedorDTO> proveedorEnTabla;
	
	Stock stock;
	List<StockDTO> todoElStock;
	
	ProductoDeProveedor productoDeProveedor; 
	List<ProductoDeProveedorDTO> todosLosProductosDeProveedor;
	
	//estos dos quizas quitarlos
	PedidosPendientes pedidosPendientes;
	List<PedidosPendientesDTO> todosLosPedidosPendientes;
	
	VentanaGenerarPedidoProveedor ventanaGenerarPedidoProveedor;
	
	ControladorGestionarProductos controladorGestionarProductos;
	
	public ControladorGenerarPedidoAProveedorManualmente(Proveedor proveedor,Stock stock,PedidosPendientes pedidosPendientes,ProductoDeProveedor productoDeProveedor) {
		this.proveedor=proveedor;
		this.stock = stock;
		this.pedidosPendientes = pedidosPendientes;
		this.productoDeProveedor = productoDeProveedor;
		this.todosLosProveedores = new ArrayList<ProveedorDTO>();
		this.todoElStock = new ArrayList<StockDTO>();
		this.todosLosPedidosPendientes = new ArrayList<PedidosPendientesDTO>();
		this.todosLosProductosDeProveedor =new ArrayList<ProductoDeProveedorDTO>(); 
		this.proveedorEnTabla = new ArrayList<ProveedorDTO>(); 
	}
	
	public void setProductoElegido(MaestroProductoDTO producto,StockDTO stock) {
		this.productoElegido = producto;	
		this.stockDeProd=stock;

		
	}

	public void setControladorGestionarProductos(ControladorGestionarProductos controladorGestionarProductos) {
		this.controladorGestionarProductos = controladorGestionarProductos;
	}
	
	public void inicializar() {
		this.ventanaGenerarPedidoProveedor = new VentanaGenerarPedidoProveedor();
		this.todosLosProveedores = this.proveedor.readAll();
		this.todoElStock = this.stock.readAll();
		this.todosLosPedidosPendientes = this.pedidosPendientes.readAll();
		this.todosLosProductosDeProveedor = this.productoDeProveedor.readAll();
		
		
		this.ventanaGenerarPedidoProveedor.getBtnAtras().addActionListener(a -> atras());
		
		llenarTablaProductoElegido();
		llenarTablaProveedores();
//		llenarLabels();
		this.ventanaGenerarPedidoProveedor.getRdbtnTodosLosProveedores().setSelected(true);
		
		this.ventanaGenerarPedidoProveedor.getRdbtnProveedoresPref().addActionListener(a -> radioButtonProveedorPreferenciadoSeleccionado());
		this.ventanaGenerarPedidoProveedor.getRdbtnTodosLosProveedores().addActionListener(a -> radioButtonTodosLosProveedoresSeleccionado());
		this.ventanaGenerarPedidoProveedor.getBtnSeleccionarProveedor().addActionListener(a -> agregarProveedor());
		this.ventanaGenerarPedidoProveedor.getBtnBorrarTablaPedido().addActionListener(a -> quitarDatosDeTablaPedido());
		
//		this.ventanaGenerarPedidoProveedor.getSpinnerCantARep().addChangeListener(new ChangeListener() {
//			@Override
//			public void stateChanged(ChangeEvent e) {
//				actualizarDatosTablaPedido();
//			}
//
//		});
		this.ventanaGenerarPedidoProveedor.getTextCantidad().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				actualizarDatosTablaPedido();
			}
		});
		
		
		this.ventanaGenerarPedidoProveedor.getBtnGenerarPedido().addActionListener(a -> generarPedido());
		validarTeclado();
	}
	
	public void mostrarVentana() {
		this.ventanaGenerarPedidoProveedor.show();
	}

	public void cerrarVentana() {
		this.ventanaGenerarPedidoProveedor.cerrar();
	}
	
	public void atras() {
		this.ventanaGenerarPedidoProveedor.cerrar();
		this.controladorGestionarProductos.inicializar();
		this.controladorGestionarProductos.mostrarVentana();
	}
	
	public void llenarTablaProductoElegido() {
		
		int idProd = this.productoElegido.getIdMaestroProducto();
		String descr = this.productoElegido.getDescripcion();
		String tipo = this.productoElegido.getTipo();
		String propio = this.productoElegido.getFabricado();
		
		double costoPro = this.productoElegido.getPrecioCosto();
		BigDecimal costoProduccion = new BigDecimal(costoPro);
		
		double precioMayo = this.productoElegido.getPrecioMayorista();
		BigDecimal precioMayorista = new BigDecimal(precioMayo);
		
		double precioMino = this.productoElegido.getPrecioMayorista();
		BigDecimal precioMinorista = new BigDecimal(precioMino);
		
		int puntoRep = this.productoElegido.getPuntoRepositorio();
		int idProveedor = this.productoElegido.getIdProveedor();
		String talle = this.productoElegido.getTalle();
		String medida = this.productoElegido.getUnidadMedida();
		String estado = this.productoElegido.getEstado();
		int cantRep = this.productoElegido.getCantidadAReponer();
		int diasARep = this.productoElegido.getDiasParaReponer();
		
		
		Double stockDisp;
		String codLote=null;
		if(this.stockDeProd != null) {
			stockDisp = this.stockDeProd.getStockDisponible();
			codLote = this.stockDeProd.getCodigoLote();
		}else {
			stockDisp = 0.0;
			codLote = "Sin asignar";
		}
	
		Object[] fila = {idProd,descr,tipo,propio,costoProduccion,precioMayorista,precioMinorista,puntoRep,idProveedor,talle,medida,estado,cantRep,diasARep,stockDisp,codLote};
		this.ventanaGenerarPedidoProveedor.getModelProducto().addRow(fila);
	}
	
	
	public void llenarTablaProveedores() {
		limpiarTablaProveedores();
		for(ProductoDeProveedorDTO prodProv: this.todosLosProductosDeProveedor) {
			for(ProveedorDTO p: this.todosLosProveedores) {
				if(prodProv.getIdProveedor() == p.getId() && prodProv.getIdMaestroProducto() == this.productoElegido.getIdMaestroProducto()) {
					agregarProveedorATabla(p,prodProv);
				}
			}
		}
	}

	
	public void agregarProveedorATabla(ProveedorDTO p,ProductoDeProveedorDTO prodProv) {
		int id = p.getId();
		String nombre = p.getNombre();
		String correo = p.getCorreo();
		
		double credDisp = p.getLimiteCredito();
		BigDecimal limiteCredito = new BigDecimal(credDisp);
		
		double precio = prodProv.getPrecioVenta();
		BigDecimal precioVenta = new BigDecimal(precio);
		
		int cantProd = prodProv.getCantidadPorLote();
		Object[] fila = {id,nombre,correo,limiteCredito,precioVenta,cantProd};
		this.ventanaGenerarPedidoProveedor.getModelProveedores().addRow(fila);
		this.proveedorEnTabla.add(p);		
	}
	
	public void limpiarTablaProveedores() {
		this.ventanaGenerarPedidoProveedor.getModelProveedores().setRowCount(0);// borrar datos de la tabla
		this.ventanaGenerarPedidoProveedor.getModelProveedores().setColumnCount(0);
		this.ventanaGenerarPedidoProveedor.getModelProveedores().setColumnIdentifiers(this.ventanaGenerarPedidoProveedor.getNombreColumnasProveedores());
		this.proveedorEnTabla.removeAll(this.proveedorEnTabla);	
	}
	
	public void radioButtonTodosLosProveedoresSeleccionado() {
		if(!this.ventanaGenerarPedidoProveedor.getRdbtnProveedoresPref().isSelected() && !this.ventanaGenerarPedidoProveedor.getRdbtnTodosLosProveedores().isSelected()) {
			//si ambos estan deseleccionados entonces se vuelve a seleccionar el previo porque no pueden quedar sin seleccionar
			this.ventanaGenerarPedidoProveedor.getRdbtnTodosLosProveedores().setSelected(true);
			return;
		}
		
		this.ventanaGenerarPedidoProveedor.getRdbtnProveedoresPref().setSelected(false);
		llenarTablaProveedores();
		return;
	}
	
	public void radioButtonProveedorPreferenciadoSeleccionado() {
		if(!this.ventanaGenerarPedidoProveedor.getRdbtnProveedoresPref().isSelected() && !this.ventanaGenerarPedidoProveedor.getRdbtnTodosLosProveedores().isSelected()) {
			//si ambos estan deseleccionados entonces se vuelve a seleccionar el previo porque no pueden quedar sin seleccionar
			this.ventanaGenerarPedidoProveedor.getRdbtnProveedoresPref().setSelected(true);
			return;
		}
		this.ventanaGenerarPedidoProveedor.getRdbtnTodosLosProveedores().setSelected(false);
		mostrarSoloProveedoresPreferenciados();
		return;
		
	}
	
	public void mostrarSoloProveedoresPreferenciados() {
		limpiarTablaProveedores();
		this.ventanaGenerarPedidoProveedor.getRdbtnTodosLosProveedores().setSelected(false);
		
		for(ProveedorDTO p: this.todosLosProveedores) {
			
			if(p.getId() == this.productoElegido.getIdProveedor()) {
				for(ProductoDeProveedorDTO prodProv: this.todosLosProductosDeProveedor) {
					if(prodProv.getIdMaestroProducto() == this.productoElegido.getIdMaestroProducto() && prodProv.getIdProveedor() == p.getId()) {
						agregarProveedorATabla(p,prodProv);
						return;
					}
				}
			}
		}
	}

	public void agregarProveedor() {
		int filaSel = this.ventanaGenerarPedidoProveedor.getTablaProveedores().getSelectedRow();
		if(filaSel == -1) {
   		 JOptionPane.showMessageDialog(null, "Debe seleccionar un proveedor", "Informacion", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		quitarDatosDeTablaPedido();

		this.proveedorElegido = this.proveedorEnTabla.get(filaSel);
		actualizarDatosTablaPedido();
	}

	public void actualizarDatosTablaPedido() {
		if(proveedorElegido==null) {
			return;
		}
		this.ventanaGenerarPedidoProveedor.getModelPedido().setRowCount(0);// borrar datos de la tabla
		this.ventanaGenerarPedidoProveedor.getModelPedido().setColumnCount(0);
		this.ventanaGenerarPedidoProveedor.getModelPedido().setColumnIdentifiers(this.ventanaGenerarPedidoProveedor.getNombreColumnasPedido());
		
//		int cantidad = (int) this.ventanaGenerarPedidoProveedor.getSpinnerCantARep().getValue();
		
		ProductoDeProveedorDTO productoDeProveedor = getProductoDeProveedor(this.proveedorElegido.getId(),this.productoElegido.getIdMaestroProducto());

		String cant = this.ventanaGenerarPedidoProveedor.getTextCantidad().getText();
		double canti;
		if(cant.equals("")) {
			canti = 0;
		}else {
			canti = Double.parseDouble(cant);
		}
		
		BigDecimal cantidad = new BigDecimal(canti);
		BigDecimal precio = new BigDecimal(productoDeProveedor.getPrecioVenta());
		
		BigDecimal precioTotal = cantidad.multiply(precio);
		
		BigDecimal cantidadPorLote = new BigDecimal(productoDeProveedor.getCantidadPorLote());
		
		BigDecimal cantTotal = cantidad.multiply(cantidadPorLote) ;
		
		String unidadMedida = this.productoElegido.getUnidadMedida();
		Object[] fila = {unidadMedida,cantidad,cantTotal,precioTotal};
		this.ventanaGenerarPedidoProveedor.getModelPedido().addRow(fila);
		
	}

	private ProductoDeProveedorDTO getProductoDeProveedor(int idProveedor, int idMaestroProducto) {
		for(ProductoDeProveedorDTO prodProv: this.todosLosProductosDeProveedor) {
			if(prodProv.getIdMaestroProducto()==idMaestroProducto && prodProv.getIdProveedor()==idProveedor) {
				return prodProv;
			}
		}return null;
	}
		
	public void quitarDatosDeTablaPedido() {
		this.ventanaGenerarPedidoProveedor.getModelPedido().setRowCount(0);// borrar datos de la tabla
		this.ventanaGenerarPedidoProveedor.getModelPedido().setColumnCount(0);
		this.ventanaGenerarPedidoProveedor.getModelPedido().setColumnIdentifiers(this.ventanaGenerarPedidoProveedor.getNombreColumnasPedido());
		this.proveedorElegido = null;
	}

	public void generarPedido() {
		if(this.proveedorElegido==null) {
	   		 JOptionPane.showMessageDialog(null, "Debe seleccionar un proveedor", "Informacion", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		if(this.productoElegido==null) {
	   		JOptionPane.showMessageDialog(null, "Debe seleccionar un producto (tremendo error)", "Informacion", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		if(this.ventanaGenerarPedidoProveedor.getTextCantidad().equals("")) {
			JOptionPane.showMessageDialog(null, "La cantidad seleccionada no es valida", "Informacion", JOptionPane.INFORMATION_MESSAGE);
			return;		
		}
		
	    if(yaExisteUnPedidoParaEsteProducto(this.productoElegido,this.proveedorElegido)) {
	    	// si selecciona que si devuelve un 0, no un 1, y la x un -1
	    	int resp = JOptionPane.showConfirmDialog(null, "Ya existe un pedido de este producto para este proveedor!. \nGenerar igualmente?",
	   		"Advertencia", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	    	if(resp==1 || resp==-1) {
	    		return;
	    	}
	    }
		
		ProductoDeProveedorDTO prodProv = getProductoDeProveedor(this.proveedorElegido.getId(), this.productoElegido.getIdMaestroProducto());
		
		//Datos de pedido
		int idProv = this.proveedorElegido.getId();
		String nombreProv = this.proveedorElegido.getNombre();
		int idMaestroProd = this.productoElegido.getIdMaestroProducto();
		String nombreMaestroprod = this.productoElegido.getDescripcion();
		
		
		BigDecimal cantidad = new BigDecimal(Double.parseDouble(this.ventanaGenerarPedidoProveedor.getTextCantidad().getText()));
		
		BigDecimal cantPorLote = new BigDecimal(prodProv.getCantidadPorLote());
		
		BigDecimal cantidadTotal = cantidad.multiply(cantPorLote);
				
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
		String fecha = dtf.format(LocalDateTime.now());
		
	    DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm:ss");
	    String hora = tf.format(LocalDateTime.now());
		
	    BigDecimal precioUnidad =new BigDecimal(prodProv.getPrecioVenta());
	    BigDecimal precioTotal = cantidad.multiply(precioUnidad);
	    	
	    double cantTotalStock = Stock.cantidadTotalDeStock(productoElegido);
//	    System.out.println("cantidad total de stock: "+cantTotalStock);
	    BigDecimal auxCantTotalStock = new BigDecimal(cantTotalStock);
	    
	    BigDecimal cantidadDeProdDeOtrosPedidosIguales = obtenerSumaDeProductosDeOtrosPedidosDeMismoProd(productoElegido);
//	    System.out.println("cantidad de pedidos de otros prod: "+cantidadDeProdDeOtrosPedidosIguales);

	    BigDecimal auxSuma = auxCantTotalStock.add(cantidadTotal);
//	    System.out.println("se suma la cant total de sotck + la cantidad de pedidos del mismo prod: "+auxSuma);
	    
	    auxSuma = auxSuma.add(cantidadDeProdDeOtrosPedidosIguales);
//	    System.out.println("cantidad total total: "+auxSuma);
	    String aux = ""+auxSuma;
//	    System.out.println("stock total seria: "+aux);
	    if(aux.length()>20) {
	    	JOptionPane.showMessageDialog(null, "El cantidad de productos supera la cantidad total de bytes almacenables en la Base de Datos(45)", "Informacion", JOptionPane.ERROR_MESSAGE);
			return;		
	    }
	    
	    
	    String auxPrecio = precioTotal+"";
	    if(auxPrecio.length()>20) {
			JOptionPane.showMessageDialog(null, "El precio total supera la cantidad de bytes almacenables en la Base de Datos(45)", "Informacion", JOptionPane.ERROR_MESSAGE);
			return;	
	    }
	    
	    String auxPrecioUnidad = precioUnidad+"";
	    if(auxPrecioUnidad.length()>20) {
	    	JOptionPane.showMessageDialog(null, "El cantidad de productos supera la cantidad total de bytes almacenables en la Base de Datos(45)", "Informacion", JOptionPane.ERROR_MESSAGE);
			return;			
	    }
	    String estado = "En espera";
	    int idSuc = this.idSucursal;
	    String fechaEnvio = null;
	    String horaEnvio = null;
	    String fechaCompleto = null;
	    String horaCompleto = null;
	    String unidadMedida = this.productoElegido.getUnidadMedida();
	    PedidosPendientesDTO p = new PedidosPendientesDTO(0,idProv,nombreProv,idMaestroProd,nombreMaestroprod,cantidadTotal.doubleValue(),fecha,hora,precioUnidad.doubleValue(),precioTotal.doubleValue(),estado,idSuc,this.idEmpleado,fechaEnvio,horaEnvio,fechaCompleto,horaCompleto,unidadMedida);
		    
	    // si selecciona que si devuelve un 0, no un 1, y la x un -1
	    int resp = JOptionPane.showConfirmDialog(null, "Seguro que desea realizar el pedido?",
	   		"Advertencia", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	    if(resp==1 || resp==-1) {
	    	return;
	    }
	    
	    
	    boolean insert = pedidosPendientes.insert(p);
	    if(!insert) {
	    	JOptionPane.showMessageDialog(null, "Ha ocurrido un error al ingresar el pedido automatico para el prod: "+this.productoElegido.getDescripcion());
	    }else {
	    	JOptionPane.showMessageDialog(null, "Se ha generado un pedido para el Producto: '"+this.productoElegido.getDescripcion() +"' con exito");
	    }
	    this.todosLosPedidosPendientes = this.pedidosPendientes.readAll();
		
	}
	
	public BigDecimal obtenerSumaDeProductosDeOtrosPedidosDeMismoProd(MaestroProductoDTO p) {
		this.todosLosPedidosPendientes = pedidosPendientes.readAll();
		BigDecimal cant=new BigDecimal(0);
		for(PedidosPendientesDTO pedido: this.todosLosPedidosPendientes) {
			if(pedido.getIdMaestroProducto() == p.getIdMaestroProducto() && (pedido.getEstado().equals("En espera") || pedido.getEstado().equals("Enviado"))) {
				BigDecimal sumar = new BigDecimal(pedido.getCantidad());
				cant = cant.add(sumar);
			}
		}return cant;
	}
	
	public boolean yaExisteUnPedidoParaEsteProducto(MaestroProductoDTO producto,ProveedorDTO prov) {
		for(PedidosPendientesDTO pedido: this.todosLosPedidosPendientes) {
			if(pedido.getIdMaestroProducto() == producto.getIdMaestroProducto() && prov.getId() == pedido.getIdProveedor()
					&& (pedido.getEstado().equals("En espera") || pedido.getEstado().equals("Enviado")) ) {
				return true;
			}
		}return false;
	}
	
	public void validarTeclado() {

		this.ventanaGenerarPedidoProveedor.getTextCantidad().addKeyListener((new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarSoloNumeros(e);
			}
		}));
		
	}
}
