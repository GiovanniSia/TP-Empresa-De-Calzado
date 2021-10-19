package presentacion.controlador.supervisor;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
	
	List<MaestroProductoDTO> productosDeProveedorEnTabla;
	List<MaestroProductoDTO> todosLosProductoEnTabla;
	
	public ControladorAsignarProductoAProveedor(MaestroProducto maestroProducto,Proveedor proveedor,ProductoDeProveedor productoDeProveedor) {
		this.maestroProducto=maestroProducto;
		this.proveedor=proveedor;
		this.productoDeProveedor=productoDeProveedor;
		this.ventanaAsignarProductoAProveedor = new VentanaAsignarProductoAProveedor();
		
		this.productosDeProveedorEnTabla = new ArrayList<MaestroProductoDTO>();
		this.todosLosProductoEnTabla = new ArrayList<MaestroProductoDTO>();
		
	}
	
	public void establecerProveedorElegido(ProveedorDTO p) {
		this.proveedorElegido =p;
	}
	
	public void inicializar() {
		this.todosLosProductos = this.maestroProducto.readAll();
		this.listaProductosDeProveedor = this.productoDeProveedor.readAll();
		
		this.ventanaAsignarProductoAProveedor.getBtnAgregar().addActionListener(a -> agregarProductoAProveedor(a));
		validarTeclado();
		llenarTablas();
	}
	
	
	public void llenarTablas() {
		llenarTablaProveedor();
		llenarTablaTodosLosProductos();
		llenarTablaProductosDelProveedor();
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
		
		for(MaestroProductoDTO p: this.todosLosProductos) {
			if(p.getIdProveedor()!=this.proveedorElegido.getId()) {
//			"Descripcion","Tipo","Costo de produccion","Precio Mayorista","Precio Minorista","Punto de Rep minimo","Talle"
				String descr = p.getDescripcion();
				String tipo = p.getTipo();
				String costo = ""+p.getPrecioCosto();
				String precioMayorista = ""+p.getPrecioMayorista();
				String precioMiniorista =""+p.getPrecioMinorista();
				String puntoRepMinimo = ""+p.getPuntoRepositorio();
				String talle = p.getTalle();
				String[] fila = {descr,tipo,costo,precioMayorista,precioMiniorista,puntoRepMinimo,talle};
				this.ventanaAsignarProductoAProveedor.getModelTablaProductos().addRow(fila);
				this.todosLosProductoEnTabla.add(p);
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
				if(p.getIdProveedor()==this.proveedorElegido.getId() && productoDeProv.getIdProveedor()==this.proveedorElegido.getId() && !this.productosDeProveedorEnTabla.contains(p) && productoDeProv.getIdMaestroProducto()==p.getIdMaestroProducto()) {
					String descr = p.getDescripcion();
					String tipo = p.getTipo();
					String costo = ""+p.getPrecioCosto();
					String precioMayorista = ""+p.getPrecioMayorista();
					String precioMiniorista =""+p.getPrecioMinorista();
					String puntoRepMinimo = ""+p.getPuntoRepositorio();
					String talle = p.getTalle();
					
					String precioVenta = ""+productoDeProv.getPrecioVenta();
					String cantPorLote = ""+productoDeProv.getCantidadPorLote();
					
					String[] fila = {descr,tipo,costo,precioMayorista,precioMiniorista,puntoRepMinimo,talle,precioVenta,cantPorLote};
					this.ventanaAsignarProductoAProveedor.getModelTablaProdDeProv().addRow(fila);
					this.productosDeProveedorEnTabla.add(p);
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
		int cantPorLote =Integer.parseInt(this.ventanaAsignarProductoAProveedor.getTextCantPorLote().getText());
		
		int idProveedor = this.proveedorElegido.getId();
		int idProducto = productoSeleccionado.getIdMaestroProducto();
		
		ProductoDeProveedorDTO p = new ProductoDeProveedorDTO(0,idProveedor,idProducto,precioVenta,cantPorLote);
		this.productoDeProveedor.insert(p);
		
		this.todosLosProductos = this.maestroProducto.readAll();
		this.listaProductosDeProveedor = this.productoDeProveedor.readAll();
		
		llenarTablaProductosDelProveedor();
				
		return;
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
