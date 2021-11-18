package presentacion.controlador.supervisor;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dto.MaestroProductoDTO;
import dto.ProductoDeProveedorDTO;
import dto.ProveedorDTO;
import inicioSesion.empleadoProperties;
import inicioSesion.sucursalProperties;
import modelo.MaestroProducto;
import modelo.ProductoDeProveedor;
import modelo.Proveedor;
import presentacion.controlador.Controlador;
import presentacion.controlador.ValidadorTeclado;
import presentacion.vista.Supervisor.VentanaAltaProducto;

public class ControladorAltaProducto {

	int idSucursal = 0;
	String tipoEmpleado = "";
	
	MaestroProducto maestroProducto;
	Proveedor proveedor;
	ProductoDeProveedor productoDeProveedor;
	
	List<MaestroProductoDTO> todosLosProductos;
	List<ProveedorDTO> todosLosProveedores;
	
	VentanaAltaProducto ventanaAltaProducto;
	
	ControladorGestionarProveedores controladorConsultarProveedor;
	ControladorGestionarProductos controladorGestionarProductos;
	
	List<ProveedorDTO> proveedoresEnTabla;
	List<ProductoDeProveedorDTO> productoDeProveedorEnTabla;
	
	MaestroProductoDTO productoAEditar;
	List<ProductoDeProveedorDTO> todosLosProductosDeProveedores;	

	public ControladorAltaProducto(MaestroProducto maestroProducto, Proveedor proveedor, ProductoDeProveedor productoDeProveedor) {
		this.maestroProducto=maestroProducto;
		this.proveedor = proveedor;
		this.productoDeProveedor = productoDeProveedor;
		this.todosLosProductos = new ArrayList<MaestroProductoDTO>();
		this.todosLosProveedores = new ArrayList<ProveedorDTO>();

		this.proveedoresEnTabla = new ArrayList<ProveedorDTO>();
		this.productoDeProveedorEnTabla = new ArrayList<ProductoDeProveedorDTO>();
		
		this.controladorConsultarProveedor = new ControladorGestionarProveedores(null,proveedor, productoDeProveedor);
		this.controladorConsultarProveedor.setControladorAltaProducto(this);
	}
	
//	public void setControladorConsultarProveedor(ControladorGestionarProveedores controladorConsultarProveedor) {
//		this.controladorConsultarProveedor=controladorConsultarProveedor;
//	}
	
	public void setControladorGestionarProductos(ControladorGestionarProductos controladorGestionarProductos) {
		this.controladorGestionarProductos = controladorGestionarProductos;
	}
	
	public void obtenerDatosPropertiesSucursalEmpleado() {
		try {
			sucursalProperties sucursalProp = sucursalProperties.getInstance();
			idSucursal = Integer.parseInt(sucursalProp.getValue("IdSucursal"));

			empleadoProperties empleadoProp = empleadoProperties.getInstance();
			tipoEmpleado = empleadoProp.getValue("TipoEmpleado");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void inicializar() {
		obtenerDatosPropertiesSucursalEmpleado();
		this.controladorConsultarProveedor.inicializar();
		this.ventanaAltaProducto = new VentanaAltaProducto();
		this.todosLosProductos = this.maestroProducto.readAll();
		this.todosLosProveedores = this.proveedor.readAll();
		this.todosLosProductosDeProveedores = (ArrayList<ProductoDeProveedorDTO>) this.productoDeProveedor.readAll();
		
		this.ventanaAltaProducto.getComboBoxTipo().addActionListener(a -> actualizarCbDadoTipo(a));
		this.ventanaAltaProducto.getComboBoxFabricado().addActionListener(a -> actualizarCbDadoFabricado(a));
		this.ventanaAltaProducto.getBtnRegistrar().addActionListener(a -> agregarProducto(a));
		this.ventanaAltaProducto.getBtnRegresar().addActionListener(a -> salir());

		this.ventanaAltaProducto.getBtnEditar().addActionListener(a -> editar());
		
		this.ventanaAltaProducto.getBtnAniadirUnidadMedida().addActionListener(a -> aniadirUnidadMedida());
		this.ventanaAltaProducto.getChckbxNumerico().addActionListener(a -> filtrarTallePorNumero());

		this.ventanaAltaProducto.getBtnAgregarProv().addActionListener(a -> mostrarVentanaGestionarProveedores());

		this.ventanaAltaProducto.getBtnAgregarProv().addActionListener(a -> pasarAAGregarProv());
		this.ventanaAltaProducto.getBtnBorrarProv().addActionListener(a -> borrarProveedor());
		
		this.ventanaAltaProducto.getTableProveedores().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	
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
	
	public void cerrarVentana() {
		this.ventanaAltaProducto.cerrar();
	}
	
	public void mostrarVentanaEditar() {
		llenarDatos();
		this.ventanaAltaProducto.getLblRegistrarProducto().setVisible(false);
		this.ventanaAltaProducto.getBtnRegistrar().setVisible(false);
		this.ventanaAltaProducto.getBtnEditar().setVisible(true);
		this.ventanaAltaProducto.getLblEditarProducto().setVisible(true);
		this.ventanaAltaProducto.show();
	}
	
	public void salir() {		
		this.controladorConsultarProveedor.cerrarVentana();
		this.ventanaAltaProducto.cerrar();
		this.productoAEditar = null;
		this.proveedoresEnTabla.removeAll(this.proveedoresEnTabla);
		this.productoDeProveedorEnTabla.removeAll(this.productoDeProveedorEnTabla);
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
		if((costo.equals("") || Double.parseDouble(costo)<=0) && this.ventanaAltaProducto.getComboBoxFabricado().getSelectedItem().toString().equals("Si")) {
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
		
		if(productoPropio.equals("No") && this.proveedoresEnTabla.size()==0) {
			JOptionPane.showMessageDialog(null, "Debe elegir al menos un proveedor");
			return false;	
		}
		if(productoPropio.equals("No") && cantProvPreferenciadoElegidos() != 1) {
			JOptionPane.showMessageDialog(null, "Debe elegir solo un proveedor preferenciado");
			return false;		
		}
				
		return true;
	}
	
	public void agregarProducto(ActionEvent a ) {
		if(todosLosCamposSonCorrectos()) {

			
			MaestroProductoDTO producto = obtenerProductoDeVista();
			
			boolean insert = this.maestroProducto.insert(producto);
			if(!insert) {
				JOptionPane.showMessageDialog(null, "Ha ocurrido un error al insertar el nuevo producto");
				return;
			}
			
			//se asigna al proveedor como proveedor de este material
//			
//			ProductoDeProveedorDTO prodDeProv = new ProductoDeProveedorDTO();
//			
//			this.productoDeProveedor.insert();
			
			asignarProveedoresAProducto();
			
			JOptionPane.showMessageDialog(null, "Produto agregado con éxito");
			this.productoAEditar = null;
			this.proveedoresEnTabla.removeAll(this.proveedoresEnTabla);
			this.productoDeProveedorEnTabla.removeAll(this.productoDeProveedorEnTabla);
			borrarDatosEscritos();
			salir();
		}
	}
	
	public MaestroProductoDTO obtenerProductoDeVista() {
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
		double costo;
		if(fabricado.equals("S")) {
			costo = Double.parseDouble(this.ventanaAltaProducto.getTextCosto().getText());	
		}else {
			costo=0;
		}
		
		int puntoRepMinimo = Integer.parseInt(this.ventanaAltaProducto.getTextPuntoRepMinimo().getText());
		int proveedor = obtenerProveedorPreferenciado();
		String talle = this.ventanaAltaProducto.getComboBoxTalle().getSelectedItem().toString();
		String unidadMedida = this.ventanaAltaProducto.getComboBoxUnidadDeMedida().getSelectedItem().toString();
		String estado = (String)this.ventanaAltaProducto.getComboBoxEstado().getSelectedItem();
		int cantAReponer = Integer.parseInt(this.ventanaAltaProducto.getTextCantidadAReponer().getText());
		int diasParaReponer = Integer.parseInt(this.ventanaAltaProducto.getTextDiasParaReponer().getText());
		
		return new MaestroProductoDTO(id,descr,tipo,fabricado,costo,precioMayorista,precioMinorista,puntoRepMinimo,proveedor,talle,unidadMedida,estado,cantAReponer,diasParaReponer);
	}
	
	public int obtenerProveedorPreferenciado() {
		/*
		String resp = (String) this.ventanaAltaProducto.getComboBoxFabricado().getSelectedItem();
		if(resp.equals("Si")) {
			return 0;
		}
		if(this.proveedorElegido==null) {
			JOptionPane.showMessageDialog(null, "No ha seleccionado ningun proveedor");
		}
		
		return this.proveedorElegido.getId();
		*/
		int id=0;
		for(int i=0; i<this.productoDeProveedorEnTabla.size(); i++) {
			if((Boolean)this.ventanaAltaProducto.getTableProveedores().getValueAt(i, 5)==true) {
				id=this.proveedoresEnTabla.get(i).getId();
			}
		}return id;
	}

	
	@SuppressWarnings("deprecation")
	public void actualizarCbDadoTipo(ActionEvent a) {
		String tipo =(String) this.ventanaAltaProducto.getComboBoxTipo().getSelectedItem();
		if(tipo.equals("Materia Prima")) {
			this.ventanaAltaProducto.getComboBoxFabricado().setSelectedIndex(2);
			this.ventanaAltaProducto.getComboBoxFabricado().disable();
			this.ventanaAltaProducto.getTextCosto().setText("");
			this.ventanaAltaProducto.getTextPrecioMayorista().setText("");
			this.ventanaAltaProducto.getTextPrecioMinorista().setText("");
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
//			this.proveedorElegido=null;
			this.ventanaAltaProducto.getBtnAgregarProv().setEnabled(false);
			this.ventanaAltaProducto.getTextCosto().setEnabled(true);
			
			this.proveedoresEnTabla.removeAll(this.proveedoresEnTabla);
			this.productoDeProveedorEnTabla.removeAll(productoDeProveedorEnTabla);
			refrescarTabla();
			return;
		}
		if(fabricado.equals("No")) {
			this.ventanaAltaProducto.getTextCosto().setText("");
			this.ventanaAltaProducto.getBtnAgregarProv().setEnabled(true);
			this.ventanaAltaProducto.getTextCosto().setEnabled(false);	
			llenarTablaProveedores();
			return;
		}
		this.ventanaAltaProducto.getTextCosto().setEnabled(true);
		this.ventanaAltaProducto.getBtnAgregarProv().setEnabled(true);
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
		
		
		this.ventanaAltaProducto.getModelTablaProveedores().setRowCount(0);//borrar datos de la tabla
		this.ventanaAltaProducto.getModelTablaProveedores().setColumnCount(0);
		this.ventanaAltaProducto.getModelTablaProveedores().setColumnIdentifiers(this.ventanaAltaProducto.getNombreColumnas());
		this.proveedoresEnTabla.removeAll(this.proveedoresEnTabla);
		this.productoDeProveedorEnTabla.removeAll(productoDeProveedorEnTabla);
		
	}
	
	
	public void pasarAElegirProveedor(ActionEvent a) {
//		this.ventanaAltaProducto.cerrar();
		this.controladorConsultarProveedor.inicializar();
		this.controladorConsultarProveedor.mostrarVentanaParaAltaProducto();
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
	    				if(resp.length()>20) {
	    					JOptionPane.showMessageDialog(null, "El texto es demasiado largo", "Informacion", JOptionPane.OK_OPTION);
	    				}else {
	    					this.ventanaAltaProducto.getComboBoxUnidadDeMedida().addItem(resp);
			    			repetir = false;        	
	    				}
	    				
	    				
	    			}
	    		}
	    	 }
	    	 catch(HeadlessException | NumberFormatException e) {
	    		 JOptionPane.showMessageDialog(null, "Valor ingresado incorrecto", "Informacion", JOptionPane.INFORMATION_MESSAGE);
	         }
	    }
	    
	    
	}

	public void mostrarVentanaGestionarProveedores() {
		this.controladorConsultarProveedor.mostrarVentanaParaAltaProducto();
	}
	
	public boolean yaFueAsignado(ProveedorDTO prov) {
		for(ProveedorDTO p: this.proveedoresEnTabla) {
			if(p.getId() == prov.getId()) {
				return true;
			}
		}return false;
	}
	
	public void aniadirProveedor(ProveedorDTO prov) {
		
		if(yaFueAsignado(prov)) {
			JOptionPane.showMessageDialog(null, "Este proveedor ya se ha agregado", "Informacion", JOptionPane.INFORMATION_MESSAGE);			
			return;
		}
		
		boolean repetir = true;
		String cantProdLotes = null;
		int cantidadDeProductosPorLote=0;
		while (repetir) {

			try {
				// si la resp es null, se eligio cancelar
				cantProdLotes = JOptionPane.showInputDialog("Ingrese la cantidad de productos por lote (limite 8 caracteres)");

				
				if (cantProdLotes == null) {
					repetir = false;
					return;
				} else {
					if (cantProdLotes.equals("")) {
						JOptionPane.showMessageDialog(null, "El valor no puede ser nulo", "Informacion",JOptionPane.OK_OPTION);
					} else {
						System.out.println("logitud de string: "+(""+cantProdLotes).length());
						cantidadDeProductosPorLote = Integer.parseInt(cantProdLotes);
						if (cantProdLotes.length() >8) {
							JOptionPane.showMessageDialog(null, "La cantidad de caracteres no puede ser mayor a 8", "Informacion",	JOptionPane.OK_OPTION);
						}
						if (cantidadDeProductosPorLote <= 0) {
							JOptionPane.showMessageDialog(null, "El valor no puede ser menor a 0", "Informacion",JOptionPane.OK_OPTION);
						}
						if (cantidadDeProductosPorLote > 0 && cantProdLotes.length() <= 8) {
							repetir = false;
						}
					}
				}
			} catch (HeadlessException | NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Valor ingresado incorrecto", "Informacion",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
		repetir=true;
		String precioLot = null;
		int precioLote=0;
		while (repetir) {

			try {
				// si la resp es null, se eligio cancelar
				precioLot = JOptionPane.showInputDialog("Ingrese el precio del lote (limite 8 caracteres)");
				if (precioLot == null) {
					repetir = false;
					return;
				} else {
					if (precioLot.equals("")) {
						JOptionPane.showMessageDialog(null, "El valor no puede ser nulo", "Informacion",JOptionPane.OK_OPTION);
					} else {
						precioLote = Integer.parseInt(precioLot);
						if (precioLot.length() > 8) {
							JOptionPane.showMessageDialog(null, "La cantidad de caracteres no puede ser mayor a 8", "Informacion",	JOptionPane.OK_OPTION);
						}
						if (precioLote <= 0) {
							JOptionPane.showMessageDialog(null, "El valor no puede ser menor a 0", "Informacion",JOptionPane.OK_OPTION);
						}
						if (precioLote > 0 && precioLot.length() <= 8) {
							repetir = false;
						}
					}
				}
			} catch (HeadlessException | NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Valor ingresado incorrecto", "Informacion",JOptionPane.INFORMATION_MESSAGE);
			}
		}

		int Id=0;
		int IdProveedor = prov.getId();
		int IdMaestroProducto = 0;
		double precioVenta = precioLote;
		int cantidadPorLote = cantidadDeProductosPorLote;
		
		ProductoDeProveedorDTO pp = new ProductoDeProveedorDTO(Id,IdProveedor,IdMaestroProducto,precioVenta,cantidadPorLote);

		this.proveedoresEnTabla.add(prov);
		this.productoDeProveedorEnTabla.add(pp);
		refrescarTabla();
	}
	
	public void refrescarTabla() {
		this.ventanaAltaProducto.getModelTablaProveedores().setRowCount(0);//borrar datos de la tabla
		this.ventanaAltaProducto.getModelTablaProveedores().setColumnCount(0);
		this.ventanaAltaProducto.getModelTablaProveedores().setColumnIdentifiers(this.ventanaAltaProducto.getNombreColumnas());
		
		for(int i=0; i<this.proveedoresEnTabla.size();i++) {
			aniadirProveedorATabla(this.proveedoresEnTabla.get(i),this.productoDeProveedorEnTabla.get(i));	
		}
		
			
	}
	
	public void aniadirProveedorATabla(ProveedorDTO prov,ProductoDeProveedorDTO pp) {
//		{"Nombre Proveedor","Correo","Limite de credito","Precio Venta","Cant Prod por lote"};
		String nombre = prov.getNombre();
		String correo = prov.getCorreo();
		
		double limiteCredit = prov.getLimiteCredito();
		BigDecimal limiteCredito = new BigDecimal(limiteCredit).setScale(2, RoundingMode.HALF_UP);
		
		double precioVent = pp.getPrecioVenta();
		BigDecimal precioVenta = new BigDecimal(precioVent).setScale(2, RoundingMode.HALF_UP);
		
		double cantPro = pp.getCantidadPorLote();
		BigDecimal cantProd = new BigDecimal(cantPro).setScale(2, RoundingMode.HALF_UP);
		
		boolean aux=false;
		if(this.productoAEditar!=null) {
			if(this.productoAEditar.getIdProveedor() == prov.getId()) {
				aux=true;
			}
		}
		
		
		Object[] fila = {nombre,correo,limiteCredito,precioVenta,cantProd,aux};
		this.ventanaAltaProducto.getModelTablaProveedores().addRow(fila);
	}
	
	public void pasarAAGregarProv() {
		this.controladorConsultarProveedor.mostrarVentanaParaAltaProducto();
	}
	
	public void borrarProveedor() {
		int filaSeleccionada = this.ventanaAltaProducto.getTableProveedores().getSelectedRow();
		if(filaSeleccionada==-1) {
			JOptionPane.showMessageDialog(null, "Debe seleccionar un proveedor", "Informacion",JOptionPane.INFORMATION_MESSAGE);
			return;
		}
//		ProveedorDTO prov = this.proveedoresEnTabla.get(filaSeleccionada);
//		ProductoDeProveedorDTO pp = this.productoDeProveedorEnTabla.get(filaSeleccionada);
		this.proveedoresEnTabla.remove(filaSeleccionada);
		this.productoDeProveedorEnTabla.remove(filaSeleccionada);
		refrescarTabla();
	}

	public int cantProvPreferenciadoElegidos() {
		int cant=0;	
		for(int i=0; i<this.proveedoresEnTabla.size();i++) {
			if((Boolean)this.ventanaAltaProducto.getTableProveedores().getValueAt(i, 5)==true) {
				cant++;
			}
		}return cant;
	}
	
	public void asignarProveedoresAProducto() {
		MaestroProductoDTO m = this.maestroProducto.selectUltimoMPInsetado(); 
		for(ProductoDeProveedorDTO pp: this.productoDeProveedorEnTabla) {
			
			if(!yaEixsteAsignacionConProducto(pp)) {
				pp.setIdMaestroProducto(m.getIdMaestroProducto());
				boolean insert = productoDeProveedor.insert(pp);
				if(!insert) {
					JOptionPane.showMessageDialog(null, "El producto: "+pp.getIdMaestroProducto()+" no se ha podido añadir correctamente");
				}	
			}
		}
	}

	public boolean yaEixsteAsignacionConProducto(ProductoDeProveedorDTO pp) {
		for(ProductoDeProveedorDTO prodProv: this.todosLosProductosDeProveedores) {
			if(pp.equals(prodProv)) {
				return true;
			}
		}return false;
	}
	
	
	
	public void setProductoAEditar(MaestroProductoDTO producto) {
		this.productoAEditar=producto;
	}

	public void llenarDatos() {
		this.ventanaAltaProducto.getTextDescripcion().setText(this.productoAEditar.getDescripcion());
		
		String tipo = this.productoAEditar.getTipo().equals("PT") ? "Producto Terminado" : "Materia Prima";
		this.ventanaAltaProducto.getComboBoxTipo().setSelectedItem(tipo);
		
		String fabricado = this.productoAEditar.getFabricado().equals("S") ? "Si" : "No";
		this.ventanaAltaProducto.getComboBoxFabricado().setSelectedItem(fabricado);
		
		this.ventanaAltaProducto.getTextCosto().setText(""+new BigDecimal(this.productoAEditar.getPrecioCosto()).setScale(2, RoundingMode.HALF_UP));
		this.ventanaAltaProducto.getTextPrecioMayorista().setText(""+new BigDecimal(this.productoAEditar.getPrecioMayorista()).setScale(2, RoundingMode.HALF_UP));
		this.ventanaAltaProducto.getTextPrecioMinorista().setText(""+new BigDecimal(this.productoAEditar.getPrecioMinorista()).setScale(2, RoundingMode.HALF_UP));
		this.ventanaAltaProducto.getTextPuntoRepMinimo().setText(""+this.productoAEditar.getPuntoRepositorio());
		
		String talle = this.productoAEditar.getTalle();
		this.ventanaAltaProducto.getComboBoxUnidadDeMedida().setSelectedItem(talle);
		boolean seEncontroTalle=false;
		for(int i=0; i< this.ventanaAltaProducto.getComboBoxTalle().getItemCount();i++) {
			System.out.println("cb: "+this.ventanaAltaProducto.getComboBoxTalle().getItemAt(i).toString()+", talle: "+talle);
			if(this.ventanaAltaProducto.getComboBoxTalle().getItemAt(i).equals(talle)) {
				this.ventanaAltaProducto.getComboBoxTalle().setSelectedIndex(i);
				seEncontroTalle=true;
			}		
		}
		if(!seEncontroTalle) {
			System.out.println("no se encontro, se crea uno nuevo");
			this.ventanaAltaProducto.getComboBoxTalle().addItem(talle);	
			this.ventanaAltaProducto.getComboBoxTalle().setSelectedItem(talle);	
		}
		
		String unidadMedida = this.productoAEditar.getUnidadMedida();
		boolean valorSeEncuentraEnCb = false;
		for(int i=0; i<this.ventanaAltaProducto.getComboBoxUnidadDeMedida().getItemCount(); i++) {
			if(this.ventanaAltaProducto.getComboBoxUnidadDeMedida().getItemAt(i).toString().equals(unidadMedida)) {
				this.ventanaAltaProducto.getComboBoxUnidadDeMedida().setSelectedIndex(i);
				valorSeEncuentraEnCb = true;
				
			}
		}
		if(!valorSeEncuentraEnCb) {
			this.ventanaAltaProducto.getComboBoxUnidadDeMedida().addItem(unidadMedida);	
			this.ventanaAltaProducto.getComboBoxUnidadDeMedida().setSelectedItem(unidadMedida);
		}
		
		
		this.ventanaAltaProducto.getTextCantidadAReponer().setText(""+this.productoAEditar.getCantidadAReponer());
		this.ventanaAltaProducto.getTextDiasParaReponer().setText(""+this.productoAEditar.getDiasParaReponer());
		
		String estado = this.productoAEditar.getEstado();
		this.ventanaAltaProducto.getComboBoxEstado().setSelectedItem(estado);

		llenarTablaProveedores();
		
	}

	public void llenarTablaProveedores() {
		if(this.productoAEditar==null) {
			return;
		}
		
		this.ventanaAltaProducto.getModelTablaProveedores().setRowCount(0);//borrar datos de la tabla
		this.ventanaAltaProducto.getModelTablaProveedores().setColumnCount(0);
		this.ventanaAltaProducto.getModelTablaProveedores().setColumnIdentifiers(this.ventanaAltaProducto.getNombreColumnas());
		this.proveedoresEnTabla.removeAll(this.proveedoresEnTabla);
		this.productoDeProveedorEnTabla.removeAll(productoDeProveedorEnTabla);		
		
		
		for(ProductoDeProveedorDTO pp: this.todosLosProductosDeProveedores) {
			if(pp.getIdMaestroProducto() == this.productoAEditar.getIdMaestroProducto()) {
				for(ProveedorDTO p: this.todosLosProveedores) {
					if(p.getId() == pp.getIdProveedor()) {
						this.proveedoresEnTabla.add(p);
						System.out.println("se añade prov: "+p.getNombre()+" a la lista");
						this.productoDeProveedorEnTabla.add(pp);
						refrescarTabla();
					}
				}
			}
		}
	}

	public void editar() {
		if(todosLosCamposSonCorrectos()) {
			MaestroProductoDTO prod = obtenerProductoDeVista();
			boolean update = this.maestroProducto.update(this.productoAEditar.getIdMaestroProducto(), prod);
			if(!update) {
				JOptionPane.showMessageDialog(null, "Ha ocurrido un error al actualizar el producto");
				return;
			}else {
				JOptionPane.showMessageDialog(null, "Producto editado con exito");
			}
			//borramos los que estan de mas
			actualizarProveedoresDeProducto();
			//agregamos los nuevos si no existen, si ya existian no se agregan
//			asignarProveedoresAProducto();
			this.todosLosProductosDeProveedores = this.productoDeProveedor.readAll();
			this.productoAEditar = null;
			this.proveedoresEnTabla.removeAll(this.proveedoresEnTabla);
			this.productoDeProveedorEnTabla.removeAll(this.productoDeProveedorEnTabla);
//			borrarDatosEscritos();		
			salir();	
		}
	}

	public void actualizarProveedoresDeProducto() {
		ArrayList<ProductoDeProveedorDTO> proveedoresDeProductoPrevio = new ArrayList<ProductoDeProveedorDTO>();
		for(ProductoDeProveedorDTO pp: this.todosLosProductosDeProveedores) {
			//no se si faltará un if mas :(
			if(pp.getIdMaestroProducto()==this.productoAEditar.getIdMaestroProducto()) {
				proveedoresDeProductoPrevio.add(pp);
			}
		}
		//this.productoDeProveedorEnTabla son los prodDeProv que quedarian en la tabla luego del editar
		for(ProductoDeProveedorDTO prv: proveedoresDeProductoPrevio) {
			boolean existe = false;
			for(ProductoDeProveedorDTO pp: this.productoDeProveedorEnTabla) {	
				existe = existe || pp.getId() == prv.getId();
			}
			if(!existe) {
				this.productoDeProveedor.delete(prv);
			}
			existe = false;
		}
		
		for(ProductoDeProveedorDTO pp: this.productoDeProveedorEnTabla) {
			
			if(!yaEixsteAsignacionConProducto(pp)) {
				pp.setIdMaestroProducto(this.productoAEditar.getIdMaestroProducto());
				boolean insert = productoDeProveedor.insert(pp);
				if(!insert) {
					JOptionPane.showMessageDialog(null, "El producto: "+pp.getIdMaestroProducto()+" no se ha podido añadir correctamente");
				}	
			}
		}
	}
	
}
