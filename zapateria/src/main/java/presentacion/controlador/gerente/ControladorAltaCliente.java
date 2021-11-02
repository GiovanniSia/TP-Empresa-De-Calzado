package presentacion.controlador.gerente;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;


import dto.ClienteDTO;
import dto.LocalidadDTO;
import dto.PaisDTO;
import dto.ProvinciaDTO;
import modelo.Cliente;
import modelo.Localidad;
import modelo.Pais;
import modelo.Provincia;
import presentacion.controlador.ValidadorTeclado;
import presentacion.vista.gerente.VentanaAltaCliente;
import presentacion.vista.gerente.VentanaEditarLocalidad;
import presentacion.vista.gerente.VentanaEditarPais;
import presentacion.vista.gerente.VentanaEditarProvincia;

public class ControladorAltaCliente {
	
	VentanaAltaCliente ventanaAltaCliente;
	
	
	ControladorEditarPais controladorEditarPais;
	VentanaEditarPais ventanaEditarPais;
	VentanaEditarProvincia ventanaEditarProvincia;
	ControladorEditarProvincia controladorEditarProvincia;
	VentanaEditarLocalidad ventanaEditarLocalidad;
	ControladorEditarLocalidad controladorEditarLocalidad;
	
	Cliente cliente;
	ArrayList<ClienteDTO> listaClientes;
	
	ClienteDTO clienteSeteado;
	
	Pais pais;
	Provincia provincia;
	Localidad localidad;
	ArrayList<PaisDTO> todosLosPaises;
	ArrayList<ProvinciaDTO> todasLasProvincias;
	ArrayList<LocalidadDTO> todasLasLocalidades;
	ArrayList<ProvinciaDTO> provEnComboBox;
	ArrayList<LocalidadDTO> localidadEnComboBox;

	ControladorGestionarClientes controladorGestionarClientes;
		
	public ControladorAltaCliente(Cliente cliente, Pais pais, Provincia provincia, Localidad localidad) {
		this.cliente=cliente;
		this.listaClientes = new ArrayList<ClienteDTO>();
		
		this.pais=pais;
		this.provincia = provincia;
		this.localidad = localidad;
		
		this.todosLosPaises = new ArrayList<PaisDTO>();
		this.todasLasProvincias = new ArrayList<ProvinciaDTO>();
		this.todasLasLocalidades = new ArrayList<LocalidadDTO>();
		this.provEnComboBox = new ArrayList<ProvinciaDTO>();
		this.localidadEnComboBox = new ArrayList<LocalidadDTO>();
	
		this.controladorEditarPais = new ControladorEditarPais(this,this.pais);
		this.controladorEditarProvincia = new ControladorEditarProvincia(this, this.pais, this.provincia);
		this.controladorEditarLocalidad = new ControladorEditarLocalidad(this, this.pais, this.provincia, this.localidad);

	}

	public void setControladorGestionarClientes(ControladorGestionarClientes controladorGestionarClientes) {
		this.controladorGestionarClientes = controladorGestionarClientes;
	}
	
	public void setCliente(ClienteDTO cliente) {
		this.clienteSeteado = cliente;
	}
	
	public void inicializar() {
		
		this.ventanaAltaCliente = new VentanaAltaCliente(); 
		
//		this.ventanaEditarPais = new VentanaEditarPais();
//		this.ventanaEditarProvincia = new VentanaEditarProvincia();
//		this.ventanaEditarLocalidad = new VentanaEditarLocalidad();
		
		this.controladorEditarPais.inicializar();
		this.controladorEditarProvincia.inicializar();
		this.controladorEditarLocalidad.inicializar();
		
		this.todosLosPaises = (ArrayList<PaisDTO>) this.pais.readAll();
		this.todasLasProvincias = (ArrayList<ProvinciaDTO>) this.provincia.readAll();
		this.todasLasLocalidades = (ArrayList<LocalidadDTO>) this.localidad.readAll();
		this.listaClientes = (ArrayList<ClienteDTO>) this.cliente.readAll();

		
		this.ventanaAltaCliente.getComboBoxPais().addActionListener(a -> actualizarCbUbicacionDadoPais());
		
		this.ventanaAltaCliente.getComboBoxProvincia().addActionListener(a -> actualizarCbUbicacionDadaProv());
		
//		this.ventanaAltaCliente.getComboBoxPais().addItemListener(new ItemListener() {
//			@Override
//			public void itemStateChanged(ItemEvent e) {
//				System.out.println("se ejecuta la escucha de cb pais");
//				actualizarCbUbicacionDadoPais(); 
//				
//			}
//		});
//		
//		this.ventanaAltaCliente.getComboBoxProvincia().addItemListener(new ItemListener() {
//			@Override
//			public void itemStateChanged(ItemEvent e) {
//				System.out.println("se ejecuta la escucha de cb localidad");
//				actualizarCbUbicacionDadaProv(); 	
//			}
//		});
		
		this.ventanaAltaCliente.getBtnRegistrar().addActionListener(a -> registrarCliente(a));
		this.ventanaAltaCliente.getBtnCancelar().addActionListener(a -> salir(a));
		this.ventanaAltaCliente.getBtnUbicacion().addActionListener(a -> pasarAEditarUbicacion(a));
		
		
		cargarComboBoxes();
		validarTeclado();
	}

	public void mostrarVentana() {
		this.ventanaAltaCliente.show();
	}
	
	public void salir(ActionEvent a) {
		this.ventanaAltaCliente.cerrar();
		this.controladorGestionarClientes.inicializar();
		this.controladorGestionarClientes.mostrarVentana();
	}

	public void registrarCliente(ActionEvent a) {
		
		if(todosLosCamposSonValidos()) {
			
			String nombre = this.ventanaAltaCliente.getTextNombre().getText();
			String apellido = this.ventanaAltaCliente.getTextApellido().getText();
			String CUIL = this.ventanaAltaCliente.getTextCUIL().getText();
			String mail = this.ventanaAltaCliente.getTextCorreo().getText();
			double limiteCredito = Double.parseDouble(this.ventanaAltaCliente.getTextSaldoInicial().getText());
			double creditoDisp = limiteCredito;
			String tipoCliente =(String) this.ventanaAltaCliente.getComboBoxTipoCliente().getSelectedItem();
			String impuestoAFIP = getIdItemImpuestoAFIP((String) this.ventanaAltaCliente.getComboBoxImpuestoAFIP().getSelectedItem());
			String estado = "Activo";
			String calle = this.ventanaAltaCliente.getTextCalle().getText();
			String altura = this.ventanaAltaCliente.getTextAltura().getText();
			String pais = this.ventanaAltaCliente.getComboBoxPais().getSelectedItem().toString();
			String prov = this.ventanaAltaCliente.getComboBoxProvincia().getSelectedItem().toString();
			String localida = this.ventanaAltaCliente.getComboBoxLocalidad().getSelectedItem().toString();
			String codPostal = this.ventanaAltaCliente.getTextCodPostal().getText();
			
			ClienteDTO cliente = new ClienteDTO(0,nombre,apellido,CUIL,mail,limiteCredito,creditoDisp,tipoCliente,impuestoAFIP,estado,calle,altura,pais,prov,localida,codPostal);
			 if(clienteYaExiste()){
				 JOptionPane.showMessageDialog(null, "El cliente ya existe en el sistema");
				 return;
			 }
			
			this.cliente.insert(cliente);
			JOptionPane.showMessageDialog(null, "Se agrego el nuevo cliente al sistema");
			
			borrarDatosDeLosText();
			
		}
	}	
	
	public boolean clienteYaExiste() {
		this.listaClientes = (ArrayList<ClienteDTO>) this.cliente.readAll();
		String CUIL = this.ventanaAltaCliente.getTextCUIL().getText();
		for(ClienteDTO c: this.listaClientes) {
			if(c.getCUIL().equals(CUIL)){
				return true;
			}
		}
		return false;
	}
	
	
	public boolean todosLosCamposSonValidos() {
		String nombre = this.ventanaAltaCliente.getTextNombre().getText();
		if(nombre.equals("")) {
			JOptionPane.showMessageDialog(null, "El nombre no puede ser vacio");
			return false;
		}
		String apellido = this.ventanaAltaCliente.getTextApellido().getText();
		if(apellido.equals("")) {
			JOptionPane.showMessageDialog(null, "El apellido no puede ser vacio");
			return false;
		}
		String CUIL = this.ventanaAltaCliente.getTextCUIL().getText();
		if(CUIL.equals("") || CUIL.length()!=11) {
			JOptionPane.showMessageDialog(null, "El CUIL no es correcto");
			return false;
		}
		String mail = this.ventanaAltaCliente.getTextCorreo().getText();
		boolean m = mail.matches("^[A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		if(!m) {
			JOptionPane.showMessageDialog(null, "El formato de mail es incorrecto");
			return false;
		}
		if(this.ventanaAltaCliente.getComboBoxImpuestoAFIP().getSelectedItem()=="Sin seleccionar") {
			JOptionPane.showMessageDialog(null, "Debe seleccionar un tipo de impuesto AFIP");
			return false;
		}
		if(this.ventanaAltaCliente.getComboBoxTipoCliente().getSelectedItem()=="Sin seleccionar") {
			JOptionPane.showMessageDialog(null, "Debe seleccionar un tipo de cliente");
			return false;
		}
		String saldoInicial = this.ventanaAltaCliente.getTextSaldoInicial().getText();
		if(saldoInicial.equals("")) {
			JOptionPane.showMessageDialog(null, "El saldo inicial no debe estar vacio");
			return false;
		}
		
		
		
		
		
		String pais = this.ventanaAltaCliente.getComboBoxPais().getSelectedItem().toString();
		if(pais.equals("Sin seleccionar")) {
			JOptionPane.showMessageDialog(null, "El pais no puede ser vacio");
			return false;
		}
		String prov = this.ventanaAltaCliente.getComboBoxProvincia().getSelectedItem().toString();
		if(prov.equals("")) {
			JOptionPane.showMessageDialog(null, "La provincia no puede ser vacia");
			return false;
		}
		String localida = this.ventanaAltaCliente.getComboBoxLocalidad().getSelectedItem().toString();
		if(localida.equals("")) {
			JOptionPane.showMessageDialog(null, "La localidad no puede ser vacia");
			return false;
		}
		String calle = this.ventanaAltaCliente.getTextCalle().getText();
		if(calle.equals("")) {
			JOptionPane.showMessageDialog(null, "La calle no puede ser vacia");
			return false;
		}
		String altura = this.ventanaAltaCliente.getTextAltura().getText();
		if(altura.equals("") || altura.equals("0")) {
			JOptionPane.showMessageDialog(null, "La altura es incorrecta");
			return false;
		}
		String codPostal = this.ventanaAltaCliente.getTextCodPostal().getText();
		if(codPostal.equals("")) {
			JOptionPane.showMessageDialog(null, "El cod postal no puede ser vacio");
			return false;
		}
		
		return true;
		
	}
	
	
	public void validarTeclado() {

		this.ventanaAltaCliente.getTextNombre().addKeyListener((new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarLetrasYEspacios(e);
			}
		}));
		this.ventanaAltaCliente.getTextApellido().addKeyListener((new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarLetrasYEspacios(e);
			}
		}));
		this.ventanaAltaCliente.getTextCUIL().addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarSoloNumeros(e);
			}
		});
		this.ventanaAltaCliente.getTextCorreo().addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarMinusculaDigitoPuntoArrobaYGuiones(e);
			}
		});
		
		this.ventanaAltaCliente.getTextSaldoInicial().addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarSoloNumeros(e);
			}
		});
		
		//pais,prov,localidd,calle,altura,codpostal
//		this.ventanaAltaCliente.getTextPais().addKeyListener(new KeyAdapter() {
//			public void keyTyped(KeyEvent e) {
//				ValidadorTeclado.aceptarLetrasYEspacios(e);
//			}
//		});
//		this.ventanaAltaCliente.getTextProvincia().addKeyListener(new KeyAdapter() {
//			public void keyTyped(KeyEvent e) {
//				ValidadorTeclado.aceptarLetrasYEspacios(e);
//			}
//		});
//		this.ventanaAltaCliente.getTextLocalidad().addKeyListener(new KeyAdapter() {
//			public void keyTyped(KeyEvent e) {
//				ValidadorTeclado.aceptarLetrasYEspacios(e);
//			}
//		});
//		this.ventanaAltaCliente.getTextCalle().addKeyListener(new KeyAdapter() {
//			public void keyTyped(KeyEvent e) {
//				ValidadorTeclado.aceptarLetrasNumerosYEspacios(e);
//			}
//		});
		this.ventanaAltaCliente.getTextAltura().addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarSoloNumeros(e);
			}
		});
		this.ventanaAltaCliente.getTextCodPostal().addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarSoloNumeros(e);
			}
		});
	}
	
	
	public void cargarComboBoxes() {
		
		String[] tipoCliente = {"Mayorista","Minorista"};
		String[] impuestoAFIP = {"Responsable Inscripto","Monotributista","Exento","Consumidor Final"};
		
		for(int i=0; i<tipoCliente.length;i++) {
			this.ventanaAltaCliente.getComboBoxTipoCliente().addItem(tipoCliente[i]);
		}
		for(int i=0; i<impuestoAFIP.length; i++) {
			this.ventanaAltaCliente.getComboBoxImpuestoAFIP().addItem(impuestoAFIP[i]);
		}	
		
		
		
		//COMBO BOXES PARA UBICACION
		
//		this.ventanaAltaCliente.getComboBoxPais().addItem("Sin seleccionar");
		if(this.todosLosPaises.size()==0) {
			return;
		}
		for(PaisDTO p: this.todosLosPaises) {
			this.ventanaAltaCliente.getComboBoxPais().addItem(p.getNombrePais());
		}
		
		this.ventanaAltaCliente.getComboBoxPais().setSelectedIndex(0);
		
		
		
//		if(this.todasLasProvincias.size()==0) {
//			return;
//		}
//		for(ProvinciaDTO prov: this.todasLasProvincias) {
//			if(paisEnCb.getIdPais()==prov.getForeignPais()) {
//				this.ventanaAltaCliente.getComboBoxProvincia().addItem(prov.getNombreProvincia());
//				this.provEnComboBox.add(prov);
//			}		
//		}
//		ProvinciaDTO provAux = this.provEnComboBox.get(0);
//		this.ventanaAltaCliente.getComboBoxProvincia().setSelectedIndex(0);
//		
//		
//		
//		if(this.todasLasLocalidades.size()==0) {
//			return;
//		}
//		for(LocalidadDTO l: this.todasLasLocalidades) {
//			if(provAux.getIdProvincia() == l.getIdForeignProvincia()) {
//				this.ventanaAltaCliente.getComboBoxLocalidad().addItem(l.getNombreLocalidad());
//				this.localidadEnComboBox.add(l);
//			}			
//		}
//		this.ventanaAltaCliente.getComboBoxLocalidad().setSelectedIndex(0);
		
		
	}

	
	
	public String getIdItemImpuestoAFIP(String descr) {
		if(descr.equals("Responsable Inscripto")) {
			return "RI";
		}
		if(descr.equals("Monotributista")) {
			return "M";
		}
		if(descr.equals("Exento")) {
			return "E";
		}
		return "";
	}
	
	
	public void borrarDatosDeLosText() {
		this.ventanaAltaCliente.getTextNombre().setText("");
		this.ventanaAltaCliente.getTextApellido().setText("");
		this.ventanaAltaCliente.getTextCUIL().setText("");
		this.ventanaAltaCliente.getTextCorreo().setText("");
		
		
		this.ventanaAltaCliente.getComboBoxTipoCliente().setSelectedIndex(0);
		this.ventanaAltaCliente.getComboBoxImpuestoAFIP().setSelectedIndex(0);
		
		this.ventanaAltaCliente.getTextSaldoInicial().setText("");
		this.ventanaAltaCliente.getTextCalle().setText("");
		this.ventanaAltaCliente.getTextAltura().setText("");
//		this.ventanaAltaCliente.getTextPais().setText("");
//		this.ventanaAltaCliente.getTextProvincia().setText("");
//		this.ventanaAltaCliente.getTextLocalidad().setText("");
		this.ventanaAltaCliente.getTextCodPostal().setText("");
	}
	
	public void actualizarCbUbicacionDadoPais() {
		
		if(this.ventanaAltaCliente.getComboBoxPais().getSelectedItem() == null || this.ventanaAltaCliente.getComboBoxPais().getSelectedIndex()==-1) {
			return;
		}
		System.out.println("se ejecuta la escucha de cb pais");
		this.ventanaAltaCliente.getComboBoxProvincia().removeAllItems();
		this.provEnComboBox.removeAll(this.provEnComboBox);
		
		this.ventanaAltaCliente.getComboBoxLocalidad().removeAllItems();
		this.localidadEnComboBox.removeAll(this.localidadEnComboBox);
		
		
		
		PaisDTO paisSeleccionado =this.todosLosPaises.get(this.ventanaAltaCliente.getComboBoxPais().getSelectedIndex());

		if (paisSeleccionado == null) {
			return;
		}
		
		llenarCbProv(paisSeleccionado.getIdPais());
		
		if(this.provEnComboBox.size()==0) {
			return;
		}
		
		ProvinciaDTO provEnCb = this.provEnComboBox.get(0);
		
		llenarCbLocalidad(provEnCb.getIdProvincia());
		
	}

	public void actualizarCbUbicacionDadaProv() {
		if(this.ventanaAltaCliente.getComboBoxProvincia().getSelectedItem() == null || this.provEnComboBox.size()==0) {
			return;
		}
		System.out.println("se ejecuta la escucha de cb localidad");
		this.ventanaAltaCliente.getComboBoxLocalidad().removeAllItems();
		this.localidadEnComboBox.removeAll(this.localidadEnComboBox);
				
		ProvinciaDTO provincia = this.provEnComboBox.get(this.ventanaAltaCliente.getComboBoxProvincia().getSelectedIndex());
		if (provincia == null) {
			return;		
		}

		llenarCbLocalidad(provincia.getIdProvincia());
		
	}
	
	public void llenarCbProv(int idPais) {
		ArrayList<ProvinciaDTO> provincias = (ArrayList<ProvinciaDTO>) getProvinciasDePais(idPais);
		for (ProvinciaDTO p : provincias) {
			if(idPais == p.getForeignPais()) {
				this.ventanaAltaCliente.getComboBoxProvincia().addItem(p.getNombreProvincia());
				this.provEnComboBox.add(p);
			}
			
		}
	}
	
	public void llenarCbLocalidad(int idProv) {
		ArrayList<LocalidadDTO> localidades = (ArrayList<LocalidadDTO>) getLocalidadesDeProvincia(idProv);
		for(LocalidadDTO l: localidades) {
			if(idProv == l.getIdForeignProvincia()) {
				this.ventanaAltaCliente.getComboBoxLocalidad().addItem(l.getNombreLocalidad());
				this.localidadEnComboBox.add(l);
			}
			
		}
	}
	

	public List<ProvinciaDTO> getProvinciasDePais(int idPais){
		ArrayList<ProvinciaDTO> provincias = new ArrayList<ProvinciaDTO>();
		for(ProvinciaDTO p: this.todasLasProvincias) {
			if(idPais == p.getForeignPais()){
				provincias.add(p);
			}
		}
		return provincias;
	}
	
	public List<LocalidadDTO> getLocalidadesDeProvincia(int idProv){
		ArrayList<LocalidadDTO> localidades = new ArrayList<LocalidadDTO>();		
		for(LocalidadDTO l: this.todasLasLocalidades) {
			if(idProv == l.getIdForeignProvincia()) {
				localidades.add(l);
			}
		}
		return localidades;
	}

	public void pasarAEditarUbicacion(ActionEvent a) {
		int seleccion = JOptionPane.showOptionDialog(
				   null,
				   "Que desea editar?", 
				   "Editar Ubicacion",
				   JOptionPane.YES_NO_CANCEL_OPTION,
				   JOptionPane.QUESTION_MESSAGE,
				   null,    // null para icono por defecto.
				   new Object[] { "Paises", "Provincias", "Localidades" },   // null para YES, NO y CANCEL
				   "opcion 1");
		
		if(seleccion==0) {
			if(this.controladorEditarPais.ventanaYaEstaInicializada()) {
				System.out.println("la ventana edit pais ya esta inicializada");
				return;
			}
			this.controladorEditarProvincia.cerrarVentana();
			this.controladorEditarLocalidad.cerrarVentana();	
			this.controladorEditarPais.inicializar();
			this.controladorEditarPais.mostrarVentana();
		}
		
		if (seleccion == 1) {
			if(this.controladorEditarProvincia.ventanaYaFueInicializada()) {
				System.out.println("la ventana edit prov ya esta inicializada");
				return;
			}
			this.controladorEditarPais.cerrarVentana();
			this.controladorEditarLocalidad.cerrarVentana();
			this.controladorEditarProvincia.inicializar();
			this.controladorEditarProvincia.mostrarVentana();
		}
		if (seleccion == 2) {
			if(this.controladorEditarLocalidad.ventanaEstaInicializada()) {
				return;
			}
			this.controladorEditarProvincia.cerrarVentana();
			this.controladorEditarPais.cerrarVentana();
			
			this.controladorEditarLocalidad.inicializar();
			this.controladorEditarLocalidad.mostrarVentana();
		}
		
	}
	
	
	public void actualizarComboBoxes() {	
		this.todosLosPaises = (ArrayList<PaisDTO>) this.pais.readAll();
		this.todasLasProvincias = (ArrayList<ProvinciaDTO>) this.provincia.readAll();
		this.todasLasLocalidades = (ArrayList<LocalidadDTO>) this.localidad.readAll();
		this.ventanaAltaCliente.getComboBoxPais().removeAllItems();
		this.ventanaAltaCliente.getComboBoxProvincia().removeAllItems();
		this.ventanaAltaCliente.getComboBoxLocalidad().removeAllItems();
		
		cargarComboBoxes();
		
		//los cb tienen una escucha que funciona cada vez que se modifica un cb. Por lo que si se modifica el cb pais, se
		//ejecutará la escucha de llenarCbDadoPais >:(. 
		
		
	}
	
}
