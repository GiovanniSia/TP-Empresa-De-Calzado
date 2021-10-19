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
import presentacion.controlador.Controlador;
import presentacion.controlador.ValidadorTeclado;
import presentacion.vista.Supervisor.VentanaAltaProducto;

public class ControladorAltaProducto {

	
	MaestroProducto maestroProducto;
	Proveedor proveedor;
	ProductoDeProveedor productoDeProveedor;
	
	List<MaestroProductoDTO> todosLosProductos;
	List<ProveedorDTO> todosLosProveedores;
	
	VentanaAltaProducto ventanaAltaProducto;
	

	ProveedorDTO proveedorElegido;
	ControladorConsultarProveedor controladorConsultarProveedor;
	Controlador controlador;
	
	public ControladorAltaProducto(Controlador controlador,MaestroProducto maestroProducto, Proveedor proveedor, ProductoDeProveedor productoDeProveedor) {
		this.maestroProducto=maestroProducto;
		this.proveedor = proveedor;
		this.productoDeProveedor = productoDeProveedor;

		this.ventanaAltaProducto = new VentanaAltaProducto();
		
		
		this.todosLosProductos = new ArrayList<MaestroProductoDTO>();
		this.todosLosProveedores = new ArrayList<ProveedorDTO>();
		
		this.controlador = controlador;
	}
	
	public void setControladorConsultarProveedor(ControladorConsultarProveedor controladorConsultarProveedor) {
		this.controladorConsultarProveedor=controladorConsultarProveedor;
	}
	
	public void inicializar() {

		this.todosLosProductos = this.maestroProducto.readAll();
		this.todosLosProveedores = this.proveedor.readAll();
		
		this.ventanaAltaProducto.getComboBoxTipo().addActionListener(a -> actualizarCbDadoTipo(a));
		this.ventanaAltaProducto.getComboBoxFabricado().addActionListener(a -> actualizarCbDadoFabricado(a));
//		this.ventanaAltaProducto.getComboBoxFabricado().addActionListener(a -> actualizarCbProveedor(a));
		this.ventanaAltaProducto.getBtnRegistrar().addActionListener(a -> agregarProducto(a));
		this.ventanaAltaProducto.getBtnRegresar().addActionListener(a -> salir(a));
		this.ventanaAltaProducto.getBtnElegirProveedor().addActionListener(a -> pasarAElegirProveedor(a));
		this.ventanaAltaProducto.getBtnBorrarProveedor().addActionListener(a -> borrarProveedor(a));

		this.ventanaAltaProducto.getLblProveedorElegido().setText("Sin seleccionar");

		
		llenarCb();
		validarTeclado();
	}

	public void mostrarVentana() {
		this.ventanaAltaProducto.show();
	}
	
	
	public void establecerProveedorElegido(ProveedorDTO prov) {
		this.proveedorElegido=prov;
		this.ventanaAltaProducto.getLblProveedorElegido().setText(this.proveedorElegido.getNombre());
		this.ventanaAltaProducto.getLblProveedorElegido().repaint();
	}
	
	public void salir(ActionEvent a ) {
		this.ventanaAltaProducto.cerrar();
		this.controlador.inicializar();
		this.controlador.mostrarVentanaMenuDeSistemas();
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
		
		
//		for(ProveedorDTO p: this.todosLosProveedores) {
//			this.ventanaAltaProducto.getComboBoxProveedorPreferenciado().addItem(p.getNombre());
//		}
		
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
		
//		String prov = (String)this.ventanaAltaProducto.getComboBoxProveedorPreferenciado().getSelectedItem();
//		if(productoPropio.equals("No") && prov.equals("Sin seleccionar")) {
//			JOptionPane.showMessageDialog(null, "El producto no es propio, por lo que debe tener un proveedor asignado");
//			return false;
//		}
		String prov = this.ventanaAltaProducto.getLblProveedorElegido().getText();
		if(productoPropio.equals("No") && prov.equals("Sin seleccionar")) {
			JOptionPane.showMessageDialog(null, "El producto no es propio, por lo que debe tener un proveedor asignado");
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
//		this.ventanaAltaProducto.getTextTalle().addKeyListener((new KeyAdapter() {
//			public void keyTyped(KeyEvent e) {
//				ValidadorTeclado.ac(e);
//			}
//		}));
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
		String resp = (String) this.ventanaAltaProducto.getComboBoxFabricado().getSelectedItem();
		if(resp.equals("Si")) {
			return 0;
		}
		if(this.proveedorElegido==null) {
			JOptionPane.showMessageDialog(null, "No ha seleccionado ningun proveedor");
		}
		
		return this.proveedorElegido.getId();
		
	}

	
//	public void actualizarCbProveedor(ActionEvent a) {
//		String fabricado = (String) this.ventanaAltaProducto.getComboBoxFabricado().getSelectedItem();
//		String tipoProducto = (String) this.ventanaAltaProducto.getComboBoxTipo().getSelectedItem();
//		if(fabricado.equals("Si") && tipoProducto.equals("Producto Terminado")) {
//			this.ventanaAltaProducto.getComboBoxProveedorPreferenciado().setSelectedIndex(0);
//			this.ventanaAltaProducto.getComboBoxProveedorPreferenciado().disable();
//			
//			return;
//		}
//		this.ventanaAltaProducto.getComboBoxProveedorPreferenciado().enable();
//	}
	
	public void actualizarCbDadoTipo(ActionEvent a) {
		String tipo =(String) this.ventanaAltaProducto.getComboBoxTipo().getSelectedItem();
		if(tipo.equals("Materia Prima")) {
			this.ventanaAltaProducto.getComboBoxFabricado().setSelectedIndex(2);
			this.ventanaAltaProducto.getComboBoxFabricado().disable();
			
			this.ventanaAltaProducto.getTextPrecioMayorista().disable();
			this.ventanaAltaProducto.getTextPrecioMinorista().disable();
			
			return;
		}
		this.ventanaAltaProducto.getTextPrecioMayorista().enable();
		this.ventanaAltaProducto.getTextPrecioMinorista().enable();
		this.ventanaAltaProducto.getComboBoxFabricado().enable();
	}
	
	public void actualizarCbDadoFabricado(ActionEvent a) {
		String fabricado =(String) this.ventanaAltaProducto.getComboBoxFabricado().getSelectedItem();
		if(fabricado.equals("Si")) {
			System.out.println("se desactiva el btn elegir proveedor");
			this.proveedorElegido=null;
			this.ventanaAltaProducto.getLblProveedorElegido().setText("Sin seleccionar");
			this.ventanaAltaProducto.getBtnElegirProveedor().setEnabled(false);
//			this.ventanaAltaProducto.getBtnElegirProveedor().repaint();
			return;
		}
		this.ventanaAltaProducto.getBtnElegirProveedor().setEnabled(true);
		
	}
	
	
	public void borrarDatosEscritos() {
		this.ventanaAltaProducto.getTextDescripcion().setText("");
		this.ventanaAltaProducto.getComboBoxTipo().setSelectedIndex(0);
		this.ventanaAltaProducto.getComboBoxFabricado().setSelectedIndex(0);
		this.ventanaAltaProducto.getTextCosto().setText("");
		this.ventanaAltaProducto.getTextPrecioMayorista().setText("");
		this.ventanaAltaProducto.getTextPrecioMinorista().setText("");
		this.ventanaAltaProducto.getTextPuntoRepMinimo().setText("");
//		this.ventanaAltaProducto.getComboBoxProveedorPreferenciado().setSelectedIndex(0);
		this.ventanaAltaProducto.getTextTalle().setText("");
		this.ventanaAltaProducto.getTextUnidadMedida().setText("");
		this.ventanaAltaProducto.getComboBoxEstado().setSelectedIndex(0);
		this.ventanaAltaProducto.getTextCantidadAReponer().setText("");
		this.ventanaAltaProducto.getTextDiasParaReponer().setText("");
		
	}
	
	
	public void pasarAElegirProveedor(ActionEvent a) {
//		this.ventanaAltaProducto.cerrar();
		this.controladorConsultarProveedor.inicializar();
		this.controladorConsultarProveedor.mostrarVentanaParaAltaProducto();
	}
	
	public void borrarProveedor(ActionEvent a) {
		this.proveedorElegido=null;
		this.ventanaAltaProducto.getLblProveedorElegido().setText("Sin seleccionar");
	}
	
	
//	public static void main(String[] args) {
//		
//		MaestroProducto m = new MaestroProducto(new DAOSQLFactory());
//		Proveedor p = new Proveedor(new DAOSQLFactory());
//		ProductoDeProveedor pp = new ProductoDeProveedor(new DAOSQLFactory());
//		ControladorConsultarProveedor cp = new ControladorConsultarProveedor(p,pp);
//		ControladorAltaProducto c = new ControladorAltaProducto(m,p,pp);
//		c.setControladorConsultarProveedor(cp);
//		cp.setControladorAltaProducto(c);
//		c.inicializar();
//		c.mostrarVentana();
//	}
	
}
