package presentacion.controlador.supervisor;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import dto.MaestroProductoDTO;
import dto.ProveedorDTO;
import modelo.MaestroProducto;
import modelo.ProductoDeProveedor;
import modelo.Proveedor;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.controlador.ValidadorTeclado;
import presentacion.vista.Supervisor.VentanaAltaProducto;

public class ControladorAltaProducto {

	
	MaestroProducto maestroProducto;
	Proveedor proveedor;
	ProductoDeProveedor productoDeProveedor;
	
	List<MaestroProductoDTO> todosLosProductos;
	List<ProveedorDTO> todosLosProveedores;
	
	VentanaAltaProducto ventanaAltaProducto;
	

	
	
	
	public ControladorAltaProducto(MaestroProducto maestroProducto, Proveedor proveedor, ProductoDeProveedor productoDeProveedor) {
		this.maestroProducto=maestroProducto;
		this.proveedor = proveedor;
		this.productoDeProveedor = productoDeProveedor;
		
		this.todosLosProductos = new ArrayList<MaestroProductoDTO>();
		this.todosLosProveedores = new ArrayList<ProveedorDTO>();
	}
	
	public void inicializar() {
		this.ventanaAltaProducto = new VentanaAltaProducto();
		
		this.todosLosProductos = this.maestroProducto.readAll();
		this.todosLosProveedores = this.proveedor.readAll();
		
		this.ventanaAltaProducto.getComboBoxFabricado().addActionListener(a -> actualizarCbProveedor(a));
		this.ventanaAltaProducto.getBtnRegistrar().addActionListener(a -> agregarProducto(a));
		this.ventanaAltaProducto.getBtnRegresar().addActionListener(a -> salir(a));
		
		llenarCb();
		validarTeclado();
	}

	public void mostrarVentana() {
		this.ventanaAltaProducto.show();
	}
	
	
	
	public void llenarCb() {
		String[] cbTipo = {"Producto Terminado","Materia Prima"};
		for(int i=0; i<cbTipo.length;i++) {
			this.ventanaAltaProducto.getComboBoxTipo().addItem(cbTipo[i]);
		}
		
		String[] cbFabricado = {"Si","No"};
		for(int i=0; i<cbFabricado.length;i++) {
			this.ventanaAltaProducto.getComboBoxFabricado().addItem(cbFabricado[i]);
		}
		
		
		for(ProveedorDTO p: this.todosLosProveedores) {
			this.ventanaAltaProducto.getComboBoxProveedorPreferenciado().addItem(p.getNombre());
		}
		
		String[] estado = {"Activo","Inactivo","Suspendido"};
		for(int i=0; i<estado.length;i++) {
			this.ventanaAltaProducto.getComboBoxEstado().addItem(estado[i]);
		}
		
	}
	
	
	
	public boolean todosLosCamposSonCorrectos() {
		String descripcion = this.ventanaAltaProducto.getTextDescripcion().getText();
		if(descripcion.equals("")) {
			JOptionPane.showMessageDialog(null, "La descripcion no puede ser vacia");
			return false;
		}
		String tipo =(String) this.ventanaAltaProducto.getComboBoxTipo().getSelectedItem();
		if(tipo.equals("Sin seleccionar")) {
			JOptionPane.showMessageDialog(null, "Debe seleccionar un tipo de producto");
			return false;
		}
		String productoPropio =(String) this.ventanaAltaProducto.getComboBoxFabricado().getSelectedItem();
		if(productoPropio.equals("Sin seleccionar")) {
			JOptionPane.showMessageDialog(null, "Debe seleccionar si es fabricado por la propia empresa o no");
			return false;
		}
		String costo = this.ventanaAltaProducto.getTextCosto().getText();
		if(costo.equals("")) {
			JOptionPane.showMessageDialog(null, "El costo de produccion no puede ser vacio");
			return false;
		}
		String precioMayorista = this.ventanaAltaProducto.getTextPrecioMayorista().getText();
		if(precioMayorista.equals("")) {
			JOptionPane.showMessageDialog(null, "El precio mayorista no puede ser vacio");
			return false;
		}
		String precioMinorista = this.ventanaAltaProducto.getTextPrecioMinorista().getText();
		if(precioMinorista.equals("")) {
			JOptionPane.showMessageDialog(null, "El precio minorista no puede ser vacio");
			return false;
		}
		String puntoRepMinimo = this.ventanaAltaProducto.getTextPuntoRepMinimo().getText();
		if(puntoRepMinimo.equals("")) {
			JOptionPane.showMessageDialog(null, "Debe establecer un punto de reposicion minimo");
			return false;
		}
		//EL PRODUCTO PUEDE NO TENER UN PROVEEDOR PREFERENCIADO
		
		String talle = this.ventanaAltaProducto.getTextTalle().getText();
		if(talle.equals("")) {
			JOptionPane.showMessageDialog(null, "Debe establecer un talle para el producto");
			return false;
		}
		String unidadMedida = this.ventanaAltaProducto.getTextUnidadMedida().getText();
		if(unidadMedida.equals("")) {
			JOptionPane.showMessageDialog(null, "Debe establecer una unidad de medida");
			return false;
		}
		String estado =(String) this.ventanaAltaProducto.getComboBoxEstado().getSelectedItem();
		if(estado.equals("Sin seleccionar")) {
			JOptionPane.showMessageDialog(null, "Debe seleccionar un estado para el producto");
			return false;
		}
		
		String cantARep = this.ventanaAltaProducto.getTextCantidadAReponer().getText();
		if(cantARep.equals("")){
			JOptionPane.showMessageDialog(null, "Debe establecer una cantidad por defecto para reponer");
			return false;
		}
		String diasRep = this.ventanaAltaProducto.getTextDiasParaReponer().getText();
		if(diasRep.equals("")){
			JOptionPane.showMessageDialog(null, "Debe establecer una cantidad de dias para reponer");
			return false;
		}
		
		return true;
	}
	
	
	public void validarTeclado() {

		this.ventanaAltaProducto.getTextDescripcion().addKeyListener((new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarLetrasYEspacios(e);
			}
		}));
		this.ventanaAltaProducto.getTextCosto().addKeyListener((new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarSoloNumerosYpuntos(e);
			}
		}));
		this.ventanaAltaProducto.getTextPrecioMinorista().addKeyListener((new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarSoloNumerosYpuntos(e);
			}
		}));
		this.ventanaAltaProducto.getTextPrecioMayorista().addKeyListener((new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarSoloNumerosYpuntos(e);
			}
		}));
		this.ventanaAltaProducto.getTextPuntoRepMinimo().addKeyListener((new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarSoloNumeros(e);
			}
		}));
		this.ventanaAltaProducto.getTextTalle().addKeyListener((new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarSoloLetras(e);
			}
		}));
		this.ventanaAltaProducto.getTextUnidadMedida().addKeyListener((new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarLetrasNumerosYEspacios(e);
			}
		}));
		
		this.ventanaAltaProducto.getTextCantidadAReponer().addKeyListener((new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarSoloNumeros(e);
			}
		}));
		this.ventanaAltaProducto.getTextDiasParaReponer().addKeyListener((new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarSoloNumeros(e);
			}
		}));
	}
	
	
	
	public void agregarProducto(ActionEvent a ) {
		if(todosLosCamposSonCorrectos()) {
			int id = 0;
			String descr = this.ventanaAltaProducto.getTextDescripcion().getText();
			String tipo = (String)this.ventanaAltaProducto.getComboBoxTipo().getSelectedItem()=="Producto Terminado" ? "PT" : "MP";
			String fabricado = ((String) this.ventanaAltaProducto.getComboBoxFabricado().getSelectedItem()).charAt(0)+"";
			double costo = Double.parseDouble(this.ventanaAltaProducto.getTextCosto().getText());
			double precioMayorista = Double.parseDouble(this.ventanaAltaProducto.getTextPrecioMayorista().getText());
			double precioMinorista = Double.parseDouble(this.ventanaAltaProducto.getTextPrecioMinorista().getText());
			int puntoRepMinimo = Integer.parseInt(this.ventanaAltaProducto.getTextPuntoRepMinimo().getText());
			int proveedor = obtenerProveedor();
			String talle = this.ventanaAltaProducto.getTextTalle().getText();
			String unidadMedida = this.ventanaAltaProducto.getTextUnidadMedida().getText();
			String estado = (String)this.ventanaAltaProducto.getComboBoxEstado().getSelectedItem();
			int cantAReponer = Integer.parseInt(this.ventanaAltaProducto.getTextCantidadAReponer().getText());
			int diasParaReponer = Integer.parseInt(this.ventanaAltaProducto.getTextDiasParaReponer().getText());
			
			MaestroProductoDTO producto = new MaestroProductoDTO(id,descr,tipo,fabricado,costo,precioMayorista,precioMinorista,puntoRepMinimo,proveedor,talle,unidadMedida,estado,cantAReponer,diasParaReponer);
			
			this.maestroProducto.insert(producto);	
			JOptionPane.showMessageDialog(null, "Produto agregado con éxito");
			borrarDatosEscritos();
		}
	}
	
	public int obtenerProveedor() {
		String prov = (String)this.ventanaAltaProducto.getComboBoxProveedorPreferenciado().getSelectedItem();
		String resp = (String) this.ventanaAltaProducto.getComboBoxFabricado().getSelectedItem();
		if(resp.equals("Si") || prov.equals("Sin seleccionar")) {
			return 0;
		}
		for(ProveedorDTO p: this.todosLosProveedores) {
			if(p.getNombre().equals(prov)) {
				return p.getId();
			}
		}
		return 0;
		
	}
	
	public void salir(ActionEvent a ) {
		this.ventanaAltaProducto.cerrar();
	}
	
	public void actualizarCbProveedor(ActionEvent a) {
		String resp = (String) this.ventanaAltaProducto.getComboBoxFabricado().getSelectedItem();
		if(resp.equals("Si")) {
			this.ventanaAltaProducto.getComboBoxProveedorPreferenciado().setSelectedIndex(0);
			this.ventanaAltaProducto.getComboBoxProveedorPreferenciado().disable();
			
			return;
		}
		this.ventanaAltaProducto.getComboBoxProveedorPreferenciado().enable();
	}
	
	
	public void borrarDatosEscritos() {
		this.ventanaAltaProducto.getTextDescripcion().setText("");
		this.ventanaAltaProducto.getComboBoxTipo().setSelectedIndex(0);
		this.ventanaAltaProducto.getComboBoxFabricado().setSelectedIndex(0);
		this.ventanaAltaProducto.getTextCosto().setText("");
		this.ventanaAltaProducto.getTextPrecioMayorista().setText("");
		this.ventanaAltaProducto.getTextPrecioMinorista().setText("");
		this.ventanaAltaProducto.getTextPuntoRepMinimo().setText("");
		this.ventanaAltaProducto.getComboBoxProveedorPreferenciado().setSelectedIndex(0);
		this.ventanaAltaProducto.getTextTalle().setText("");
		this.ventanaAltaProducto.getTextUnidadMedida().setText("");
		this.ventanaAltaProducto.getComboBoxEstado().setSelectedIndex(0);
		this.ventanaAltaProducto.getTextCantidadAReponer().setText("");
		this.ventanaAltaProducto.getTextDiasParaReponer().setText("");
		
	}
	
	public static void main(String[] args) {
		MaestroProducto m = new MaestroProducto(new DAOSQLFactory());
		Proveedor p = new Proveedor(new DAOSQLFactory());
		ProductoDeProveedor pp = new ProductoDeProveedor(new DAOSQLFactory());
		ControladorAltaProducto c = new ControladorAltaProducto(m,p,pp);
		c.inicializar();
		c.mostrarVentana();
	}
	
}
