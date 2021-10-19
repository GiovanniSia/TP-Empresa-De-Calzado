package presentacion.controlador.supervisor;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import dto.MaestroProductoDTO;
import dto.ProductoDeProveedorDTO;
import dto.ProveedorDTO;
import modelo.MaestroProducto;
import modelo.ProductoDeProveedor;
import modelo.Proveedor;
import persistencia.dao.mysql.DAOSQLFactory;
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
	
	public ControladorAsignarProductoAProveedor(MaestroProducto maestroProducto,Proveedor proveedor,ProductoDeProveedor productoDeProveedor) {
		this.maestroProducto=maestroProducto;
		this.proveedor=proveedor;
		this.productoDeProveedor=productoDeProveedor;
		this.ventanaAsignarProductoAProveedor = new VentanaAsignarProductoAProveedor();
		
		this.productosDeProveedorEnTabla = new ArrayList<ProductoDeProveedorDTO>();
		this.todosLosProductoEnTabla = new ArrayList<MaestroProductoDTO>();
		
	}
	
	public void establecerProveedorElegido(ProveedorDTO p) {
		this.proveedorElegido =p;
	}
	
	public void inicializar() {
		this.todosLosProductos = this.maestroProducto.readAll();
		this.listaProductosDeProveedor = this.productoDeProveedor.readAll();
		
		this.ventanaAsignarProductoAProveedor.getBtnAgregar().addActionListener(a -> agregarProductoAProveedor(a));
		this.ventanaAsignarProductoAProveedor.getBtnQuitar().addActionListener(a -> quitarProductoAProveedor(a));
		this.ventanaAsignarProductoAProveedor.getTextNombre().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				filtrarBusqueda();
			}
		});
		validarTeclado();
		llenarTablas();
	}
	
	
	public void llenarTablas() {
		llenarTablaProveedor();
		llenarTablaTodosLosProductos();
		llenarTablaProductosDelProveedor();
	}
	
	
	public void filtrarBusqueda() {
		String nombre = this.ventanaAsignarProductoAProveedor.getTextNombre().getText();
		ArrayList<MaestroProductoDTO> tablaFilatrada = (ArrayList<MaestroProductoDTO>) this.maestroProducto.getFiltroModificarMProdcto("Descripcion", nombre, null, null, null, null, null, null);
		llenarTablaFiltrada(tablaFilatrada);
	}
	
	
	public void llenarTablaFiltrada(ArrayList<MaestroProductoDTO> tablaFiltrada) {
		this.ventanaAsignarProductoAProveedor.getModelTablaProductos().setRowCount(0);//borrar datos de la tabla
		this.ventanaAsignarProductoAProveedor.getModelTablaProductos().setColumnCount(0);
		this.ventanaAsignarProductoAProveedor.getModelTablaProductos().setColumnIdentifiers(this.ventanaAsignarProductoAProveedor.getNombreColumnasProductos());
		this.todosLosProductoEnTabla.removeAll(this.todosLosProductoEnTabla);
		
		for(MaestroProductoDTO prod: tablaFiltrada) {
			
			boolean deboAgregar=true;
			MaestroProductoDTO maestroProducto=prod;
			for(ProductoDeProveedorDTO p: this.listaProductosDeProveedor) {
				deboAgregar = deboAgregar && prod.getIdMaestroProducto() != p.getIdMaestroProducto();

			}
//			"Descripcion","Tipo","Costo de produccion","Precio Mayorista","Precio Minorista","Punto de Rep minimo","Talle"

			if(deboAgregar) {
				String descr = maestroProducto.getDescripcion();
				String tipo = maestroProducto.getTipo();
				 
				BigDecimal cost = new BigDecimal(maestroProducto.getPrecioCosto());
				String costo = ""+cost;
				
				BigDecimal precioMayo = new BigDecimal(maestroProducto.getPrecioMayorista());
				String precioMayorista = ""+precioMayo;
				
				BigDecimal precioMino = new BigDecimal(maestroProducto.getPrecioMinorista());
				String precioMiniorista =""+precioMino;
				String puntoRepMinimo = ""+maestroProducto.getPuntoRepositorio();
				String talle = maestroProducto.getTalle();
				String[] fila = {descr,tipo,costo,precioMayorista,precioMiniorista,puntoRepMinimo,talle};
				this.ventanaAsignarProductoAProveedor.getModelTablaProductos().addRow(fila);
				this.todosLosProductoEnTabla.add(maestroProducto);	
			}
			
		}
	}
	
	
	
	public void llenarTablaProveedor() {
//		{"Nombre","Correo","Limite de Credito","Credito disponible"};
		String nombre = this.proveedorElegido.getNombre();
		String correo = this.proveedorElegido.getCorreo();
		String limiteCredito = ""+this.proveedorElegido.getLimiteCredito();
		String crediDisp = ""+this.proveedorElegido.getCreditoDisponible();
		String fila[] = {nombre,correo,limiteCredito,crediDisp};
		this.ventanaAsignarProductoAProveedor.getModelTablaProvElegido().addRow(fila);
				
	}
	
	public void llenarTablaTodosLosProductos() {
		this.ventanaAsignarProductoAProveedor.getModelTablaProductos().setRowCount(0);//borrar datos de la tabla
		this.ventanaAsignarProductoAProveedor.getModelTablaProductos().setColumnCount(0);
		this.ventanaAsignarProductoAProveedor.getModelTablaProductos().setColumnIdentifiers(this.ventanaAsignarProductoAProveedor.getNombreColumnasProductos());
		this.todosLosProductoEnTabla.removeAll(this.todosLosProductoEnTabla);
		
		for(MaestroProductoDTO prod: this.todosLosProductos) {
		
			boolean deboAgregar=true;
			MaestroProductoDTO maestroProducto=prod;
			for(ProductoDeProveedorDTO p: this.listaProductosDeProveedor) {
				deboAgregar = deboAgregar && prod.getIdMaestroProducto() != p.getIdMaestroProducto();

			}
//			"Descripcion","Tipo","Costo de produccion","Precio Mayorista","Precio Minorista","Punto de Rep minimo","Talle"

			if(deboAgregar) {
				String descr = maestroProducto.getDescripcion();
				String tipo = maestroProducto.getTipo();
				BigDecimal cost = new BigDecimal(maestroProducto.getPrecioCosto());
				String costo = ""+cost;
				
				BigDecimal precioMayo = new BigDecimal(maestroProducto.getPrecioMayorista());
				String precioMayorista = ""+precioMayo;
				
				BigDecimal precioMino = new BigDecimal(maestroProducto.getPrecioMinorista());
				String precioMiniorista =""+precioMino;
				String puntoRepMinimo = ""+maestroProducto.getPuntoRepositorio();
				String talle = maestroProducto.getTalle();
				String[] fila = {descr,tipo,costo,precioMayorista,precioMiniorista,puntoRepMinimo,talle};
				this.ventanaAsignarProductoAProveedor.getModelTablaProductos().addRow(fila);
				this.todosLosProductoEnTabla.add(maestroProducto);	
			}
			
		}
	
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
					String precioMayorista = ""+p.getPrecioMayorista();
					String precioMiniorista =""+p.getPrecioMinorista();
					String puntoRepMinimo = ""+p.getPuntoRepositorio();
					String talle = p.getTalle();
					
					String precioVenta =""+productoDeProv.getPrecioVenta(); ;
					String cantPorLote=""+productoDeProv.getCantidadPorLote();
					
					
					String[] fila = {descr,tipo,costo,precioMayorista,precioMiniorista,puntoRepMinimo,talle,precioVenta,cantPorLote};
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
		
		double precioVenta = Double.parseDouble(this.ventanaAsignarProductoAProveedor.getTextPrecioVenta().getText());
		int cantPorLote = Integer.parseInt(this.ventanaAsignarProductoAProveedor.getTextCantPorLote().getText());
		
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
		
		llenarTablaTodosLosProductos();
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
			
		llenarTablaTodosLosProductos();
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
		for(ProductoDeProveedorDTO productoProv: this.listaProductosDeProveedor) {
			if(productoProv.getIdMaestroProducto()==p.getIdMaestroProducto()) {
				return true;
			}
		}return false;
	}
	
	public static void main(String[] args) {
		MaestroProducto maestroProducto = new MaestroProducto(new DAOSQLFactory());
		Proveedor proveedor = new Proveedor(new DAOSQLFactory());
		ProductoDeProveedor productoDeProveedor = new ProductoDeProveedor(new DAOSQLFactory());
		ControladorAsignarProductoAProveedor c = new ControladorAsignarProductoAProveedor(maestroProducto,proveedor,productoDeProveedor);
		c.establecerProveedorElegido(new ProveedorDTO(1,"Naik","naik@gmail.com",20000,10000));
		c.inicializar();
		c.mostrarVentana();
	}
	
}
