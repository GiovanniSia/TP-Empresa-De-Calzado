package presentacion.controlador.supervisor;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import dto.MaestroProductoDTO;
import dto.ProductoDeProveedorDTO;
import dto.ProveedorDTO;
import modelo.MaestroProducto;
import modelo.ProductoDeProveedor;
import modelo.Proveedor;
import presentacion.controlador.ValidadorTeclado;
import presentacion.vista.Supervisor.VentanaAsignarProductoAProveedor;

public class ControladorAsignarProductoAProveedor {

	VentanaAsignarProductoAProveedor ventanaAsignarProductoAProveedor;
	
	MaestroProducto maestroProducto;
	Proveedor proveedor;
	ProductoDeProveedor productoDeProveedor;
	
	ProveedorDTO proveedorElegido;
	List<MaestroProductoDTO> todosLosProductos;
	List<ProductoDeProveedorDTO> listaProductosDeProveedor;
	
	List<ProductoDeProveedorDTO> productosDeProveedorEnTabla;
	List<MaestroProductoDTO> todosLosProductoEnTabla;
	
	ControladorGestionarProveedores controladorConsultarProveedor;
	
	public ControladorAsignarProductoAProveedor(MaestroProducto maestroProducto,Proveedor proveedor,ProductoDeProveedor productoDeProveedor) {
		this.maestroProducto=maestroProducto;
		this.proveedor=proveedor;
		this.productoDeProveedor=productoDeProveedor;

		
		this.productosDeProveedorEnTabla = new ArrayList<ProductoDeProveedorDTO>();
		this.todosLosProductoEnTabla = new ArrayList<MaestroProductoDTO>();
		
	}
	
	public void setControladorConsultarProveedor(ControladorGestionarProveedores controladorConsultarProveedor) {
		this.controladorConsultarProveedor = controladorConsultarProveedor;
	}
	
	public void establecerProveedorElegido(ProveedorDTO p) {
		this.proveedorElegido =p;
	}
	
	public void inicializar() {
		this.ventanaAsignarProductoAProveedor = new VentanaAsignarProductoAProveedor();
		
		this.todosLosProductos = this.maestroProducto.readAll();
		this.listaProductosDeProveedor = this.productoDeProveedor.readAll();
		
		this.ventanaAsignarProductoAProveedor.getBtnAgregar().addActionListener(a -> agregarProductoAProveedor(a));
		this.ventanaAsignarProductoAProveedor.getBtnQuitar().addActionListener(a -> quitarProductoAProveedor(a));
		
		this.ventanaAsignarProductoAProveedor.getBtnModificarCantidadPorLote().addActionListener(a -> modificarCantidadPorLote(a));
		this.ventanaAsignarProductoAProveedor.getBtnModificarPrecioDe().addActionListener(a -> modificarPrecioDeLote(a));
		
		this.ventanaAsignarProductoAProveedor.getBtnSalir().addActionListener(a -> salir(a));
		
		this.ventanaAsignarProductoAProveedor.getTextNombre().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				filtrarBusqueda();
			}
		});
		llenarTablas();
		validarTeclado();

	}
	
	
	public void llenarTablas() {
		llenarTablaProveedor();
		llenarTablaTodosLosProductos((ArrayList<MaestroProductoDTO>)this.todosLosProductos);
		llenarTablaProductosDelProveedor();
	}
	
	
	public void filtrarBusqueda() {
		String nombre = this.ventanaAsignarProductoAProveedor.getTextNombre().getText();
		ArrayList<MaestroProductoDTO> tablaFilatrada = (ArrayList<MaestroProductoDTO>) this.maestroProducto.getFiltroModificarMProdcto("Descripcion", nombre, null, null, null, null, null, null);
		llenarTablaTodosLosProductos(tablaFilatrada);
	}

	
	public void llenarTablaProveedor() {
		this.ventanaAsignarProductoAProveedor.getModelTablaProvElegido().setRowCount(0);//borrar datos de la tabla
		this.ventanaAsignarProductoAProveedor.getModelTablaProvElegido().setColumnCount(0);
		this.ventanaAsignarProductoAProveedor.getModelTablaProvElegido().setColumnIdentifiers(this.ventanaAsignarProductoAProveedor.getNombreColumnasProvElegido());
		
//		{"Nombre","Correo","Limite de Credito","Credito disponible"};
		String nombre = this.proveedorElegido.getNombre();
		String correo = this.proveedorElegido.getCorreo();
		String limiteCredito = ""+this.proveedorElegido.getLimiteCredito();
		String fila[] = {nombre,correo,limiteCredito};
		this.ventanaAsignarProductoAProveedor.getModelTablaProvElegido().addRow(fila);
				
	}
	
	public void llenarTablaTodosLosProductos(ArrayList<MaestroProductoDTO> listaProductos) {
		this.ventanaAsignarProductoAProveedor.getModelTablaProductos().setRowCount(0);//borrar datos de la tabla
		this.ventanaAsignarProductoAProveedor.getModelTablaProductos().setColumnCount(0);
		this.ventanaAsignarProductoAProveedor.getModelTablaProductos().setColumnIdentifiers(this.ventanaAsignarProductoAProveedor.getNombreColumnasProductos());
		this.todosLosProductoEnTabla.removeAll(this.todosLosProductoEnTabla);
		
		for(MaestroProductoDTO prod: listaProductos) {

//			boolean deboAgregar=true;
			boolean deboAgregar=productoTieneProveedorAsignado(prod);
//			boolean prodEsProvistoPorEsteProv = true;
			MaestroProductoDTO maestroProducto=prod;
			
			
			
			deboAgregar = deboAgregar && prod.getFabricado().equals("N");
			if(deboAgregar) {
				String descr = maestroProducto.getDescripcion();
				String tipo = maestroProducto.getTipo();
				BigDecimal cost = new BigDecimal(maestroProducto.getPrecioCosto()).setScale(2, RoundingMode.HALF_UP);
				String costo = ""+cost;
				
				BigDecimal precioMayo = new BigDecimal(maestroProducto.getPrecioMayorista()).setScale(2, RoundingMode.HALF_UP);
				String precioMayorista = ""+precioMayo;
				
				BigDecimal precioMino = new BigDecimal(maestroProducto.getPrecioMinorista()).setScale(2, RoundingMode.HALF_UP);
				String precioMiniorista =""+precioMino;
				String puntoRepMinimo = ""+maestroProducto.getPuntoRepositorio();
				String talle = maestroProducto.getTalle();
				String[] fila = {descr,tipo,costo,precioMayorista,precioMiniorista,puntoRepMinimo,talle};
				this.ventanaAsignarProductoAProveedor.getModelTablaProductos().addRow(fila);
				this.todosLosProductoEnTabla.add(maestroProducto);	
			}
			
		}
	
	}
	
	public boolean productoTieneProveedorAsignado(MaestroProductoDTO prod) {
		for(ProductoDeProveedorDTO p: this.listaProductosDeProveedor) {
			if(p.getIdMaestroProducto() == prod.getIdMaestroProducto() && p.getIdProveedor() == this.proveedorElegido.getId()) {
				return false;
			}
		}return true;
		
	}
	
	
	//COrregir esta re mal
	public void llenarTablaProductosDelProveedor() {
		this.ventanaAsignarProductoAProveedor.getModelTablaProdDeProv().setRowCount(0);//borrar datos de la tabla
		this.ventanaAsignarProductoAProveedor.getModelTablaProdDeProv().setColumnCount(0);
		this.ventanaAsignarProductoAProveedor.getModelTablaProdDeProv().setColumnIdentifiers(this.ventanaAsignarProductoAProveedor.getNombreColumnasProdDeProv());
		this.productosDeProveedorEnTabla.removeAll(this.productosDeProveedorEnTabla);
		
		for(ProductoDeProveedorDTO productoDeProv: this.listaProductosDeProveedor) {
			for(MaestroProductoDTO p: this.todosLosProductos) {
				if(productoDeProv.getIdMaestroProducto()==p.getIdMaestroProducto() && productoDeProv.getIdProveedor()==this.proveedorElegido.getId() && !this.productosDeProveedorEnTabla.contains(productoDeProv)) {
					String descr = p.getDescripcion();
					String tipo = p.getTipo();
					String costo = ""+p.getPrecioCosto();
					
					BigDecimal precioMayorista = new BigDecimal(p.getPrecioMayorista()).setScale(2, RoundingMode.HALF_UP);
					
					BigDecimal precioMinorista = new BigDecimal(p.getPrecioMinorista()).setScale(2, RoundingMode.HALF_UP); 
					String puntoRepMinimo = ""+p.getPuntoRepositorio();
					String talle = p.getTalle();
					
					BigDecimal precioVenta = new BigDecimal(productoDeProv.getPrecioVenta()).setScale(2, RoundingMode.HALF_UP);
					BigDecimal cantPorLote=new BigDecimal(productoDeProv.getCantidadPorLote()).setScale(2, RoundingMode.HALF_UP);
					
					
					Object[] fila = {descr,tipo,costo,precioMayorista,precioMinorista,puntoRepMinimo,talle,precioVenta,cantPorLote};
					this.ventanaAsignarProductoAProveedor.getModelTablaProdDeProv().addRow(fila);
					this.productosDeProveedorEnTabla.add(productoDeProv);
				}
			}
		}
	}
	
	
	public void mostrarVentana() {
		this.ventanaAsignarProductoAProveedor.show();
	}
	
	public void cerrarVentana() {
		this.ventanaAsignarProductoAProveedor.cerrar();
	}
	
	
	public void agregarProductoAProveedor(ActionEvent a) {
		int filaSeleccionada = this.ventanaAsignarProductoAProveedor.getTableTodosLosProd().getSelectedRow();
		if(filaSeleccionada==-1) {
			JOptionPane.showMessageDialog(null, "No ha seleccionado ningun producto");
			return;
		}
		MaestroProductoDTO productoSeleccionado = this.todosLosProductoEnTabla.get(filaSeleccionada);
		
		if(this.ventanaAsignarProductoAProveedor.getTextCantPorLote().getText().equals("") || this.ventanaAsignarProductoAProveedor.getTextPrecioVenta().getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Los datos para ingresar no son validos");
			return;
		}
		
		double precioVenta = 0;
		try {
			precioVenta = Double.parseDouble(this.ventanaAsignarProductoAProveedor.getTextPrecioVenta().getText());
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "Debe escribir un valor numerico para el campo Precio de Venta");
			return;	
		}
		
		double cantPorLote = 0;
		try {
			cantPorLote = Double.parseDouble(this.ventanaAsignarProductoAProveedor.getTextCantPorLote().getText());
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "Debe escribir un valor numerico para el campo Cantidad de Productos por Lote");
			return;	
		}
		
		if(precioVenta<=0) {
	   		 JOptionPane.showMessageDialog(null, "El precio de venta no puede ser menor a 0", "Informacion", JOptionPane.INFORMATION_MESSAGE);
	   		 return;
		}
		if(cantPorLote<=0) {
	   		 JOptionPane.showMessageDialog(null, "La cantidad de productos por lote no puede ser menor a 0", "Informacion", JOptionPane.INFORMATION_MESSAGE);
	   		 return;
		}
		
		int idProveedor = this.proveedorElegido.getId();
		int idProducto = productoSeleccionado.getIdMaestroProducto();
		
		ProductoDeProveedorDTO p = new ProductoDeProveedorDTO(0,idProveedor,idProducto,precioVenta,cantPorLote);
		
		if(yaExisteEnTabla(p)) {
			JOptionPane.showMessageDialog(null, "El producto ya ha sido asignado");
			return;
		}
		
		this.productoDeProveedor.insert(p);
		
		this.todosLosProductos = this.maestroProducto.readAll();
		this.listaProductosDeProveedor = this.productoDeProveedor.readAll();
		
		llenarTablaTodosLosProductos((ArrayList<MaestroProductoDTO>)this.todosLosProductos);
		llenarTablaProductosDelProveedor();
				
		return;
	}
	

	public void quitarProductoAProveedor(ActionEvent a) {
		int filaSeleccionada = this.ventanaAsignarProductoAProveedor.getTableProdDeProv().getSelectedRow();
		if(filaSeleccionada==-1) {
			JOptionPane.showMessageDialog(null, "No ha seleccionado ningun producto");
			return;
		}
		ProductoDeProveedorDTO productoSeleccionado = this.productosDeProveedorEnTabla.get(filaSeleccionada);
		this.productoDeProveedor.delete(productoSeleccionado);
			
		this.todosLosProductos = this.maestroProducto.readAll();
		this.listaProductosDeProveedor = this.productoDeProveedor.readAll();
			
		llenarTablaTodosLosProductos((ArrayList<MaestroProductoDTO>)this.todosLosProductos);
		llenarTablaProductosDelProveedor();
	}
	
	
	public void validarTeclado() {

		this.ventanaAsignarProductoAProveedor.getTextPrecioVenta().addKeyListener((new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarSoloNumerosYpuntos(e);
			}
		}));
		this.ventanaAsignarProductoAProveedor.getTextCantPorLote().addKeyListener((new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarSoloNumeros(e);
			}
		}));
	}
	
	public boolean yaExisteEnTabla(ProductoDeProveedorDTO p) {
		for(ProductoDeProveedorDTO productoProv: this.productosDeProveedorEnTabla) {
			if(productoProv.getIdMaestroProducto()==p.getIdMaestroProducto()) {
				return true;
			}
		}return false;
	}
	
	
	public void salir(ActionEvent a) {
		this.ventanaAsignarProductoAProveedor.cerrar();
		this.controladorConsultarProveedor.inicializar();
		this.controladorConsultarProveedor.mostrarVentana();
	}
	
	public void modificarCantidadPorLote(ActionEvent a) {
		int filaSeleccionada = this.ventanaAsignarProductoAProveedor.getTableProdDeProv().getSelectedRow();
		if(filaSeleccionada==-1) {
			JOptionPane.showMessageDialog(null, "No ha seleccionado ningun producto");
			return;
		}
		ProductoDeProveedorDTO proveedorSeleccionado = this.productosDeProveedorEnTabla.get(filaSeleccionada);
		double valorNuevo=0;
		String resp=null;
		boolean repetir = true;
	    while (repetir) {
	    	
	    	try {
	    		resp=JOptionPane.showInputDialog("Ingrese la nueva cantidad de productos por lote (cantidad maxima de caracteres 8)");
	    		if(resp==null) {
	    			repetir=false;
	    		}else {
	    			valorNuevo = Double.parseDouble(resp);
	    			if(resp.length()<=8 && valorNuevo>0)  {
	    				repetir = false;
	    			}else {
	    				JOptionPane.showMessageDialog(null, "El valor debe sere menor o igual a 8 caracteres y mayor a 0", "Informacion", JOptionPane.INFORMATION_MESSAGE);		
	    			}
		            
	    		}
	    	 }
	    	 catch(HeadlessException | NumberFormatException e) {
	    		 JOptionPane.showMessageDialog(null, "Valor ingresado incorrecto", "Informacion", JOptionPane.INFORMATION_MESSAGE);
//	                caso = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la manera quiere imprimir la bienvenida(1-scanner,2-Panel)"));
	         }
	    }
	    if(resp==null && valorNuevo==0) {
	    	return;
	    }
	    proveedorSeleccionado.setCantidadPorLote(valorNuevo);
	    boolean update = this.productoDeProveedor.update(proveedorSeleccionado, proveedorSeleccionado.getId());
	    if(!update) {
	    	JOptionPane.showMessageDialog(null, "Ha ocurrido un error al actualizar el nuevo valor");
	    }else {
//	    	JOptionPane.showMessageDialog(null, "Nuevo valor actualizado con exito");
	    }
		this.listaProductosDeProveedor = this.productoDeProveedor.readAll();
		llenarTablaProductosDelProveedor();
	}
	
	public void modificarPrecioDeLote(ActionEvent a) {
		int filaSeleccionada = this.ventanaAsignarProductoAProveedor.getTableProdDeProv().getSelectedRow();
		if(filaSeleccionada==-1) {
			JOptionPane.showMessageDialog(null, "No ha seleccionado ningun producto");
			return;
		}
		ProductoDeProveedorDTO proveedorSeleccionado = this.productosDeProveedorEnTabla.get(filaSeleccionada);
		double valorNuevo=0;
		String resp=null;
		boolean repetir = true;
	    while (repetir) {
	    	
	    	try {
	    		resp=JOptionPane.showInputDialog("Ingrese el nuevo precio del lote (cantidad maxima de caracteres 8)");
	    		if(resp==null) {
	    			repetir=false;
	    			
	    		}else {
	    			valorNuevo = Double.parseDouble(resp);
	    			if(resp.length()<=8 && valorNuevo>0)  {
	    				repetir = false;
	    			}else {
	    				JOptionPane.showMessageDialog(null, "El valor debe sere menor o igual a 8 caracteres y mayor a 0", "Informacion", JOptionPane.INFORMATION_MESSAGE);		
	    			}
		            
	    		}
	    	 }
	    	 catch(HeadlessException | NumberFormatException e) {
	    		    		 
	    		 JOptionPane.showMessageDialog(null, "Valor ingresado incorrecto", "Informacion", JOptionPane.INFORMATION_MESSAGE);
//	                caso = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la manera quiere imprimir la bienvenida(1-scanner,2-Panel)"));
	         }
	    }
	    if(resp==null && valorNuevo==0) {
	    	return;
	    }
	    proveedorSeleccionado.setPrecioVenta(valorNuevo);
	    boolean update = this.productoDeProveedor.update(proveedorSeleccionado, proveedorSeleccionado.getId());
	    if(!update) {
	    	JOptionPane.showMessageDialog(null, "Ha ocurrido un error al actualizar el nuevo valor");
	    }else {
	    	JOptionPane.showMessageDialog(null, "Nuevo valor actualizado con exito");
	    }
		this.listaProductosDeProveedor = this.productoDeProveedor.readAll();
		llenarTablaProductosDelProveedor();
	}
	
}
