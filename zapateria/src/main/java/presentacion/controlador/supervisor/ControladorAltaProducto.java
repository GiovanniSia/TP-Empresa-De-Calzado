package presentacion.controlador.supervisor;

import java.awt.HeadlessException;
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
	ControladorGestionarProductos controladorGestionarProductos;
	

	
	
	public ControladorAltaProducto(MaestroProducto maestroProducto, Proveedor proveedor, ProductoDeProveedor productoDeProveedor) {
		this.maestroProducto=maestroProducto;
		this.proveedor = proveedor;
		this.productoDeProveedor = productoDeProveedor;


		
		
		this.todosLosProductos = new ArrayList<MaestroProductoDTO>();
		this.todosLosProveedores = new ArrayList<ProveedorDTO>();
		
	}
	
	public void setControladorConsultarProveedor(ControladorConsultarProveedor controladorConsultarProveedor) {
		this.controladorConsultarProveedor=controladorConsultarProveedor;
	}
	
	public void setControladorGestionarProductos(ControladorGestionarProductos controladorGestionarProductos) {
		this.controladorGestionarProductos = controladorGestionarProductos;
	}
	
	
	public void inicializar() {
		this.ventanaAltaProducto = new VentanaAltaProducto();
		this.todosLosProductos = this.maestroProducto.readAll();
		this.todosLosProveedores = this.proveedor.readAll();
		
		this.ventanaAltaProducto.getComboBoxTipo().addActionListener(a -> actualizarCbDadoTipo(a));
		this.ventanaAltaProducto.getComboBoxFabricado().addActionListener(a -> actualizarCbDadoFabricado(a));
//		this.ventanaAltaProducto.getComboBoxFabricado().addActionListener(a -> actualizarCbProveedor(a));
		this.ventanaAltaProducto.getBtnRegistrar().addActionListener(a -> agregarProducto(a));
		this.ventanaAltaProducto.getBtnRegresar().addActionListener(a -> salir(a));
		this.ventanaAltaProducto.getBtnElegirProveedor().addActionListener(a -> pasarAElegirProveedor(a));
		this.ventanaAltaProducto.getBtnBorrarProveedor().addActionListener(a -> borrarProveedor(a));

		this.ventanaAltaProducto.getBtnAniadirUnidadMedida().addActionListener(a -> aniadirUnidadMedida());
		this.ventanaAltaProducto.getChckbxNumerico().addActionListener(a -> filtrarTallePorNumero());
		this.ventanaAltaProducto.getLblProveedorElegido().setText("Sin seleccionar");

		
		llenarCb();
		validarTeclado();
	}

	
	public void validarTeclado() {

		this.ventanaAltaProducto.getTextDescripcion().addKeyListener((new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarLetrasNumerosYEspacios(e);
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
		this.controladorGestionarProductos.inicializar();
		this.controladorGestionarProductos.mostrarVentana();
	}
	
	public void llenarCb() {
		String[] cbTipo = {"Sin seleccionar","Producto Terminado","Materia Prima"};
		for(int i=0; i<cbTipo.length;i++) {
			this.ventanaAltaProducto.getComboBoxTipo().addItem(cbTipo[i]);
		}
		
		String[] cbFabricado = {"Sin seleccionar","Si","No"};
		for(int i=0; i<cbFabricado.length;i++) {
			this.ventanaAltaProducto.getComboBoxFabricado().addItem(cbFabricado[i]);
		}

		String[] estado = {"Sin seleccionar","Activo","Inactivo","Suspendido"};
		for(int i=0; i<estado.length;i++) {
			this.ventanaAltaProducto.getComboBoxEstado().addItem(estado[i]);
		}
		
		String[] unidadesDeMedida = {"Sin seleccionar","Unidad","Par","Pack X3","Pack X4","Pack X5","Pack X6","Pack X7"};
		for(int i=0; i<unidadesDeMedida.length; i++) {
			this.ventanaAltaProducto.getComboBoxUnidadDeMedida().addItem(unidadesDeMedida[i]);
		}
		
		
		String[] talle = {"Sin seleccionar","XS","S","M","L","XL","XXL"};
		for(int i=0; i<talle.length; i++) {
			this.ventanaAltaProducto.getComboBoxTalle().addItem(talle[i]);
		}
		
	}
	
	public void filtrarTallePorNumero() {
		this.ventanaAltaProducto.getComboBoxTalle().removeAllItems();
		this.ventanaAltaProducto.getComboBoxTalle().addItem("Sin seleccionar");
		
		if(this.ventanaAltaProducto.getChckbxNumerico().isSelected()) {
			//15 al 50
			for(int i=1; i<=50; i++) {
				this.ventanaAltaProducto.getComboBoxTalle().addItem(""+i);
			}	
		}else {
			String[] talle = {"XS","S","M","L","XL","XXL"};
			for(int i=0; i<talle.length; i++) {
				this.ventanaAltaProducto.getComboBoxTalle().addItem(talle[i]);
			}
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
		if(costo.equals("") || Double.parseDouble(costo)<=0) {
			JOptionPane.showMessageDialog(null, "El costo de produccion no es valido");
			return false;
		}
		String precioMayorista = this.ventanaAltaProducto.getTextPrecioMayorista().getText();
		if((precioMayorista.equals("") || Double.parseDouble(precioMayorista)<=0) && !tipo.equals("Materia Prima")) {
			JOptionPane.showMessageDialog(null, "El precio mayorista es invalido");
			return false;
		}
		String precioMinorista = this.ventanaAltaProducto.getTextPrecioMinorista().getText();
		if((precioMinorista.equals("") || Double.parseDouble(precioMinorista)<=0) && !tipo.equals("Materia Prima")) {
			JOptionPane.showMessageDialog(null, "El precio minorista es invalido");
			return false;
		}
		String puntoRepMinimo = this.ventanaAltaProducto.getTextPuntoRepMinimo().getText();
		if(puntoRepMinimo.equals("") || Integer.parseInt(puntoRepMinimo)<=0) {
			JOptionPane.showMessageDialog(null, "Debe establecer un punto de reposicion minimo");
			return false;
		}
		
		String talle = this.ventanaAltaProducto.getComboBoxTalle().getSelectedItem().toString();
		if(talle.equals("Sin seleccionar")) {
			JOptionPane.showMessageDialog(null, "Debe seleccionar un talle");
			return false;
		}
		
		String unidadMedida = this.ventanaAltaProducto.getComboBoxUnidadDeMedida().getSelectedItem().toString();
		if(unidadMedida.equals("Sin seleccionar")) {
			JOptionPane.showMessageDialog(null, "Debe seleccionar una unidad de medida");
			return false;
		}
		
		String estado =(String) this.ventanaAltaProducto.getComboBoxEstado().getSelectedItem();
		if(estado.equals("Sin seleccionar")) {
			JOptionPane.showMessageDialog(null, "Debe seleccionar un estado para el producto");
			return false;
		}
		
		String cantARep = this.ventanaAltaProducto.getTextCantidadAReponer().getText();
		if(cantARep.equals("") || Integer.parseInt(cantARep)<=0){
			JOptionPane.showMessageDialog(null, "Debe establecer una cantidad por defecto para reponer");
			return false;
		}
		String diasRep = this.ventanaAltaProducto.getTextDiasParaReponer().getText();
		if(diasRep.equals("") || Integer.parseInt(diasRep)<=0){
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
	
	public void agregarProducto(ActionEvent a ) {
		if(todosLosCamposSonCorrectos()) {
			String tipo = (String)this.ventanaAltaProducto.getComboBoxTipo().getSelectedItem();
			double precioMayorista = 0;
			double precioMinorista = 0;
			
			if(!tipo.equals("Materia Prima")) {
				precioMayorista = Double.parseDouble(this.ventanaAltaProducto.getTextPrecioMayorista().getText());
				precioMinorista = Double.parseDouble(this.ventanaAltaProducto.getTextPrecioMinorista().getText());
			}else {
				precioMayorista = 0;
				precioMinorista = 0;

			}
			int id = 0;
			String descr = this.ventanaAltaProducto.getTextDescripcion().getText();
			tipo = (String)this.ventanaAltaProducto.getComboBoxTipo().getSelectedItem()=="Producto Terminado" ? "PT" : "MP";
			String fabricado = ((String) this.ventanaAltaProducto.getComboBoxFabricado().getSelectedItem()).charAt(0)+"";
			double costo = Double.parseDouble(this.ventanaAltaProducto.getTextCosto().getText());
			
			int puntoRepMinimo = Integer.parseInt(this.ventanaAltaProducto.getTextPuntoRepMinimo().getText());
			int proveedor = obtenerProveedor();
			String talle = this.ventanaAltaProducto.getComboBoxTalle().getSelectedItem().toString();
			String unidadMedida = this.ventanaAltaProducto.getComboBoxUnidadDeMedida().getSelectedItem().toString();
			String estado = (String)this.ventanaAltaProducto.getComboBoxEstado().getSelectedItem();
			int cantAReponer = Integer.parseInt(this.ventanaAltaProducto.getTextCantidadAReponer().getText());
			int diasParaReponer = Integer.parseInt(this.ventanaAltaProducto.getTextDiasParaReponer().getText());
			
			MaestroProductoDTO producto = new MaestroProductoDTO(id,descr,tipo,fabricado,costo,precioMayorista,precioMinorista,puntoRepMinimo,proveedor,talle,unidadMedida,estado,cantAReponer,diasParaReponer);
			
			this.maestroProducto.insert(producto);	
			
			//se asigna al proveedor como proveedor de este material
//			
//			ProductoDeProveedorDTO prodDeProv = new ProductoDeProveedorDTO();
//			
//			this.productoDeProveedor.insert();
			
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

	
	@SuppressWarnings("deprecation")
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
//		this.ventanaAltaProducto.getTextTalle().setText("");
		this.ventanaAltaProducto.getComboBoxUnidadDeMedida().setSelectedItem(0);
		this.ventanaAltaProducto.getComboBoxFabricado().setSelectedIndex(0);
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
	
	public void aniadirUnidadMedida() {
		String resp=null;
		boolean repetir = true;
	    while (repetir) {
	    	
	    	try {
	    		//si la resp es null, se eligio cancelar
	    		resp=JOptionPane.showInputDialog("Ingrese la nueva unidad de medida");
	    		if(resp==null) {
	    			repetir=false;
	    		}else {
	    			if(resp.equals("")) {
	    				JOptionPane.showMessageDialog(null, "El valor no puede ser nulo", "Informacion", JOptionPane.OK_OPTION);	    				
	    			}else {
	    				this.ventanaAltaProducto.getComboBoxUnidadDeMedida().addItem(resp);
		    			repetir = false;        
	    			}
	    		}
	    	 }
	    	 catch(HeadlessException | NumberFormatException e) {
	    		 JOptionPane.showMessageDialog(null, "Valor ingresado incorrecto", "Informacion", JOptionPane.INFORMATION_MESSAGE);
	         }
	    }
	    
	    
	}
	
}
