package presentacion.controlador.supervisor;

import java.util.List;

import dto.MaestroProductoDTO;
import dto.ProductoDeProveedorDTO;
import dto.ProveedorDTO;
import modelo.MaestroProducto;
import modelo.ProductoDeProveedor;
import modelo.Proveedor;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.vista.Supervisor.VentanaAsignarProductoAProveedor;

public class ControladorAsignarProductoAProveedor {

	VentanaAsignarProductoAProveedor ventanaAsignarProductoAProveedor;
	
	MaestroProducto maestroProducto;
	Proveedor proveedor;
	ProductoDeProveedor productoDeProveedor;
	
	ProveedorDTO proveedorElegido;
	List<MaestroProductoDTO> productosDeProveedorEnTabla;
	List<MaestroProductoDTO> todosLosProductos;
	List<ProductoDeProveedorDTO> listaProductosDeProveedor;
	
	public ControladorAsignarProductoAProveedor(MaestroProducto maestroProducto,Proveedor proveedor,ProductoDeProveedor productoDeProveedor) {
		this.maestroProducto=maestroProducto;
		this.proveedor=proveedor;
		this.productoDeProveedor=productoDeProveedor;
		this.ventanaAsignarProductoAProveedor = new VentanaAsignarProductoAProveedor(); 
	}
	
	public void establecerProveedorElegido(ProveedorDTO p) {
		this.proveedorElegido =p;
	}
	
	public void inicializar() {
		this.todosLosProductos = this.maestroProducto.readAll();
		this.listaProductosDeProveedor = this.productoDeProveedor.readAll();
		
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
		for(MaestroProductoDTO p: this.todosLosProductos) {
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
			
		}
	}
	
	public void llenarTablaProductosDelProveedor() {
		for(MaestroProductoDTO p: this.todosLosProductos) {
			if(p.getIdProveedor()==this.proveedorElegido.getId()) {
				String descr = p.getDescripcion();
				String tipo = p.getTipo();
				String costo = ""+p.getPrecioCosto();
				String precioMayorista = ""+p.getPrecioMayorista();
				String precioMiniorista =""+p.getPrecioMinorista();
				String puntoRepMinimo = ""+p.getPuntoRepositorio();
				String talle = p.getTalle();
				String[] fila = {descr,tipo,costo,precioMayorista,precioMiniorista,puntoRepMinimo,talle};
				this.ventanaAsignarProductoAProveedor.getModelTablaProdDeProv().addRow(fila);
			}
		}
	}
	
	
	public void mostrarVentana() {
		this.ventanaAsignarProductoAProveedor.show();
	}
	
	public void cerrarVentana() {
		this.ventanaAsignarProductoAProveedor.cerrar();
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
