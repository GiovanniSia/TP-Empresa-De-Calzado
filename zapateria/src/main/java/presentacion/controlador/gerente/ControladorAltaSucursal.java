package presentacion.controlador.gerente;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import dto.LocalidadDTO;
import dto.PaisDTO;
import dto.ProvinciaDTO;
import dto.SucursalDTO;
import modelo.Localidad;
import modelo.Pais;
import modelo.Provincia;
import modelo.Sucursal;
import presentacion.controlador.ValidadorTeclado;
import presentacion.vista.gerente.VentanaAltaSucursal;
import presentacion.vista.gerente.VentanaEditarLocalidad;
import presentacion.vista.gerente.VentanaEditarPais;
import presentacion.vista.gerente.VentanaEditarProvincia;

public class ControladorAltaSucursal {

	VentanaAltaSucursal ventanaAltaSucursal;
	
	ControladorEditarPais controladorEditarPais;
	VentanaEditarPais ventanaEditarPais;
	VentanaEditarProvincia ventanaEditarProvincia;
	ControladorEditarProvincia controladorEditarProvincia;
	VentanaEditarLocalidad ventanaEditarLocalidad;
	ControladorEditarLocalidad controladorEditarLocalidad;
	
	Pais pais;
	Provincia provincia;
	Localidad localidad;
	ArrayList<PaisDTO> todosLosPaises;
	ArrayList<ProvinciaDTO> todasLasProvincias;
	ArrayList<LocalidadDTO> todasLasLocalidades;
	ArrayList<ProvinciaDTO> provEnComboBox;
	ArrayList<LocalidadDTO> localidadEnComboBox;
	
	Sucursal sucursal;
	ArrayList<SucursalDTO> todasLasSucursales;
	
	SucursalDTO sucursalSeteada;
	
	ControladorGestionarSucursales controladorGestionarSucursales;	
	public ControladorAltaSucursal(Sucursal sucursal,Pais pais, Provincia provincia, Localidad localidad) {
		this.pais=pais;
		this.provincia = provincia;
		this.localidad = localidad;
		
		this.sucursal=sucursal;
		
		this.todasLasSucursales = new ArrayList<SucursalDTO>();
		
		this.todosLosPaises = new ArrayList<PaisDTO>();
		this.todasLasProvincias = new ArrayList<ProvinciaDTO>();
		this.todasLasLocalidades = new ArrayList<LocalidadDTO>();
		this.provEnComboBox = new ArrayList<ProvinciaDTO>();
		this.localidadEnComboBox = new ArrayList<LocalidadDTO>();
	
		this.controladorEditarPais = new ControladorEditarPais(this.pais);
		this.controladorEditarProvincia = new ControladorEditarProvincia(this.pais, this.provincia);
		this.controladorEditarLocalidad = new ControladorEditarLocalidad(this.pais, this.provincia, this.localidad);
	}
	
	public void inicializar() {
		this.ventanaAltaSucursal = new VentanaAltaSucursal();
		
		this.todasLasSucursales = (ArrayList<SucursalDTO>) this.sucursal.readAll();
		
		this.controladorEditarPais.inicializar();
		this.controladorEditarProvincia.inicializar();
		this.controladorEditarLocalidad.inicializar();
		
		this.todosLosPaises = (ArrayList<PaisDTO>) this.pais.readAll();
		this.todasLasProvincias = (ArrayList<ProvinciaDTO>) this.provincia.readAll();
		this.todasLasLocalidades = (ArrayList<LocalidadDTO>) this.localidad.readAll();


		
		this.ventanaAltaSucursal.getBtnRegresar().addActionListener(a -> salir());
		
		this.ventanaAltaSucursal.getBtnUbicacion().addActionListener(a -> pasarAEditarUbicacion());
		
		this.ventanaAltaSucursal.getComboBoxPais().addActionListener(a -> actualizarCbUbicacionDadoPais());
		
		this.ventanaAltaSucursal.getComboBoxProvincia().addActionListener(a -> actualizarCbUbicacionDadaProv());
		
		this.ventanaAltaSucursal.getBtnRegistrar().addActionListener(a -> agregarSucursal());
		
		this.ventanaAltaSucursal.getBtnEditar().addActionListener(a -> editarSucursal());
		
		cargarComboBoxes();
		
		validarTeclado();
	}
	
	public void setControladorGestionarSucursales(ControladorGestionarSucursales controladorGestionarSucursales) {
		this.controladorGestionarSucursales=controladorGestionarSucursales;
	}
	
	public void setearSucursalAEditar(SucursalDTO sucursal) {
		this.sucursalSeteada = sucursal;
	}
	
	public void mostrarVentanaAgregar() {
		this.ventanaAltaSucursal.getBtnRegistrar().setVisible(true);
		this.ventanaAltaSucursal.getLblRegistrar().setVisible(true);
		this.ventanaAltaSucursal.show();
	}
	
	public void mostrarVentanaEditar() {
		this.ventanaAltaSucursal.getBtnEditar().setVisible(true);
		this.ventanaAltaSucursal.getLblEditar().setVisible(true);
		escribirValores();
		this.ventanaAltaSucursal.show();
	}
	
	public void cerrarVentana() {
		this.ventanaAltaSucursal.cerrar();
	}
	
	public void salir() {
		this.sucursalSeteada = null;
		this.ventanaAltaSucursal.cerrar();
		this.controladorGestionarSucursales.inicializar();
		this.controladorGestionarSucursales.mostrarVentana();
	}
	
	public void escribirValores() {
		this.ventanaAltaSucursal.getTextNombre().setText(this.sucursalSeteada.getNombre());
		this.ventanaAltaSucursal.getTextTelefono().setText(this.sucursalSeteada.getTelefono());
		this.ventanaAltaSucursal.getTextCalle().setText(this.sucursalSeteada.getCalle());
		this.ventanaAltaSucursal.getTextAltura().setText(this.sucursalSeteada.getAltura());
		this.ventanaAltaSucursal.getTextCodPostal().setText(this.sucursalSeteada.getCodigoPostal());
		this.ventanaAltaSucursal.getComboBoxPais().setSelectedItem(this.sucursalSeteada.getPais());
		this.ventanaAltaSucursal.getComboBoxProvincia().setSelectedItem(this.sucursalSeteada.getProvincia());
		this.ventanaAltaSucursal.getComboBoxLocalidad().setSelectedItem(this.sucursalSeteada.getLocalidad());
	}
	
	public void actualizarCbUbicacionDadoPais() {
		if(this.ventanaAltaSucursal.getComboBoxPais().getSelectedItem() == null || this.ventanaAltaSucursal.getComboBoxPais().getSelectedIndex()==-1) {
			return;
		}
//		System.out.println("se ejecuta la escucha de cb pais");
		this.ventanaAltaSucursal.getComboBoxProvincia().removeAllItems();
		this.provEnComboBox.removeAll(this.provEnComboBox);
		
		this.ventanaAltaSucursal.getComboBoxLocalidad().removeAllItems();
		this.localidadEnComboBox.removeAll(this.localidadEnComboBox);

		PaisDTO paisSeleccionado =this.todosLosPaises.get(this.ventanaAltaSucursal.getComboBoxPais().getSelectedIndex());

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
		if(this.ventanaAltaSucursal.getComboBoxProvincia().getSelectedItem() == null || this.provEnComboBox.size()==0) {
			return;
		}
//		System.out.println("se ejecuta la escucha de cb localidad");
		this.ventanaAltaSucursal.getComboBoxLocalidad().removeAllItems();
		this.localidadEnComboBox.removeAll(this.localidadEnComboBox);
				
		ProvinciaDTO provincia = this.provEnComboBox.get(this.ventanaAltaSucursal.getComboBoxProvincia().getSelectedIndex());
		if (provincia == null) {
			return;		
		}

		llenarCbLocalidad(provincia.getIdProvincia());
	}
	
	
	public void llenarCbProv(int idPais) {
		ArrayList<ProvinciaDTO> provincias = (ArrayList<ProvinciaDTO>) getProvinciasDePais(idPais);
		for (ProvinciaDTO p : provincias) {
			if(idPais == p.getForeignPais()) {
				this.ventanaAltaSucursal.getComboBoxProvincia().addItem(p.getNombreProvincia());
				this.provEnComboBox.add(p);
			}
			
		}
	}
	
	public void llenarCbLocalidad(int idProv) {
		ArrayList<LocalidadDTO> localidades = (ArrayList<LocalidadDTO>) getLocalidadesDeProvincia(idProv);
		for(LocalidadDTO l: localidades) {
			if(idProv == l.getIdForeignProvincia()) {
				this.ventanaAltaSucursal.getComboBoxLocalidad().addItem(l.getNombreLocalidad());
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

	public void pasarAEditarUbicacion() {
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
			this.controladorEditarPais.setControladorAltaSucursal(this);
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
			this.controladorEditarProvincia.setControladorAltaSucursal(this);
			this.controladorEditarProvincia.inicializar();
			this.controladorEditarProvincia.mostrarVentana();
		}
		if (seleccion == 2) {
			if(this.controladorEditarLocalidad.ventanaEstaInicializada()) {
				return;
			}
			this.controladorEditarProvincia.cerrarVentana();
			this.controladorEditarPais.cerrarVentana();
			this.controladorEditarLocalidad.setControladorAltaSucursal(this);
			this.controladorEditarLocalidad.inicializar();
			this.controladorEditarLocalidad.mostrarVentana();
		}
	}
	
	public void actualizarComboBoxes() {
		this.todosLosPaises = (ArrayList<PaisDTO>) this.pais.readAll();
		this.todasLasProvincias = (ArrayList<ProvinciaDTO>) this.provincia.readAll();
		this.todasLasLocalidades = (ArrayList<LocalidadDTO>) this.localidad.readAll();
		
		this.ventanaAltaSucursal.getComboBoxPais().removeAllItems();
		this.ventanaAltaSucursal.getComboBoxProvincia().removeAllItems();
		this.ventanaAltaSucursal.getComboBoxLocalidad().removeAllItems();
		cargarComboBoxes();
	}
	
	public void cargarComboBoxes() {
		for(PaisDTO p: this.todosLosPaises) {
			this.ventanaAltaSucursal.getComboBoxPais().addItem(p.getNombrePais());
		}
		this.ventanaAltaSucursal.getComboBoxPais().setSelectedIndex(0);
		
	}

	public void agregarSucursal() {
		if(todosLosCamposSonValidos()) {

			SucursalDTO sucursalNueva = getSucursalDeVista();
			
			if(yaExisteEstaSucursal(sucursalNueva)) {
				JOptionPane.showMessageDialog(null, "Ya existe una sucursal con este nombre", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			boolean insert = this.sucursal.insert(sucursalNueva);
			if(!insert) {
				JOptionPane.showMessageDialog(null, "Ha ocurrido un error al insertar la nueva sucursal", "Error", JOptionPane.ERROR_MESSAGE);
				return;	
			}else {
				JOptionPane.showMessageDialog(null, "Sucursal agregada con exito", "Info", JOptionPane.INFORMATION_MESSAGE);
			}
			this.todasLasSucursales = (ArrayList<SucursalDTO>) this.sucursal.readAll();
			limpiarDatos();
		}
	}
	
	public SucursalDTO getSucursalDeVista() {
		String nombre = this.ventanaAltaSucursal.getTextNombre().getText();
		String telefono = this.ventanaAltaSucursal.getTextTelefono().getText();
		String calle = this.ventanaAltaSucursal.getTextCalle().getText();
		String altura = this.ventanaAltaSucursal.getTextAltura().getText();
		String codPostal = this.ventanaAltaSucursal.getTextCodPostal().getText();
		
		String pais = this.ventanaAltaSucursal.getComboBoxPais().getSelectedItem().toString();
		String provincia = this.ventanaAltaSucursal.getComboBoxProvincia().getSelectedItem().toString();
		String localidad = this.ventanaAltaSucursal.getComboBoxLocalidad().getSelectedItem().toString();
		String nroSucursal = this.ventanaAltaSucursal.getTextNroSucursal().getText();
		return new SucursalDTO(0,telefono,calle,altura,provincia,localidad,pais,codPostal,nombre,nroSucursal);
	}
	
	public void limpiarDatos() {
		this.ventanaAltaSucursal.getTextNombre().setText("");
		this.ventanaAltaSucursal.getTextTelefono().setText("");
		this.ventanaAltaSucursal.getTextCalle().setText("");
		this.ventanaAltaSucursal.getTextAltura().setText("");
		this.ventanaAltaSucursal.getTextCodPostal().setText("");	
	}
	
	public boolean yaExisteEstaSucursal(SucursalDTO nuevaSucursal) {
		for(SucursalDTO s: this.todasLasSucursales) {
			if(s.getNombre().equals(nuevaSucursal.getNombre()) && this.sucursalSeteada!=null) {
				if(this.sucursalSeteada.getIdSucursal() != s.getIdSucursal()) {
					return true;
				}
			}else {
				if(s.getNombre().equals(nuevaSucursal.getNombre())) {
					return true;	
				}	
			}
			
		}
		return false;
	}
	
	public boolean todosLosCamposSonValidos() {
		String nombre = this.ventanaAltaSucursal.getTextNombre().getText();
		if(nombre.equals("")) {
			JOptionPane.showMessageDialog(ventanaEditarProvincia, "El nombre no puede ser vacio");
			return false;
		}
		
		String telefono = this.ventanaAltaSucursal.getTextTelefono().getText();
		if(telefono.equals("")) {
			JOptionPane.showMessageDialog(ventanaEditarProvincia, "El telefono no puede ser vacio");
			return false;	
		}
		
		String calle = this.ventanaAltaSucursal.getTextCalle().getText();
		if(calle.equals("")) {
			JOptionPane.showMessageDialog(ventanaEditarProvincia, "La calle no puede ser vacia");
			return false;		
		}
		
		String altura = this.ventanaAltaSucursal.getTextAltura().getText();
		if(altura.equals("")) {
			JOptionPane.showMessageDialog(ventanaEditarProvincia, "La altura no puede ser vacia");
			return false;	
		}
		
		String codPostal = this.ventanaAltaSucursal.getTextCodPostal().getText();
		if(codPostal.equals("")) {
			JOptionPane.showMessageDialog(ventanaEditarProvincia, "El codigo postal no puede ser vacio");
			return false;	
		}
		
		if(this.ventanaAltaSucursal.getComboBoxPais().getSelectedItem()==null) {
			JOptionPane.showMessageDialog(ventanaEditarProvincia, "El pais no puede ser vacio");
			return false;		
		}
		if(this.ventanaAltaSucursal.getComboBoxProvincia().getSelectedItem()==null) {
			JOptionPane.showMessageDialog(ventanaEditarProvincia, "La provincia no puede ser vacia");
			return false;		
		}
		if(this.ventanaAltaSucursal.getComboBoxLocalidad().getSelectedItem()==null) {
			JOptionPane.showMessageDialog(ventanaEditarProvincia, "La localidad no puede ser vacia");
			return false;		
		}
		String nroSucursal = this.ventanaAltaSucursal.getTextNroSucursal().getText();
		if(nroSucursal.equals("")) {
			JOptionPane.showMessageDialog(ventanaEditarProvincia, "El numero de sucursal no puede ser vacio");
			return false;			
		}
		
		return true;
	}
	
	public void validarTeclado() {
		this.ventanaAltaSucursal.getTextNombre().addKeyListener((new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarLetrasNumerosYEspacios(e);
			}
		}));
		
		this.ventanaAltaSucursal.getTextTelefono().addKeyListener((new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarSoloNumeros(e);
			}
		}));
		
		this.ventanaAltaSucursal.getTextCalle().addKeyListener((new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarLetrasNumerosYEspacios(e);
			}
		}));
		
		this.ventanaAltaSucursal.getTextAltura().addKeyListener((new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarSoloNumeros(e);
			}
		}));
		
		this.ventanaAltaSucursal.getTextCodPostal().addKeyListener((new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarSoloNumeros(e);
			}
		}));
		
		this.ventanaAltaSucursal.getTextNroSucursal().addKeyListener((new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarSoloNumeros(e);
			}
		}));
	}

	public void editarSucursal() {
		if(todosLosCamposSonValidos()) {
			SucursalDTO sucursalNueva = getSucursalDeVista();
			
			if(yaExisteEstaSucursal(sucursalNueva)) {
				JOptionPane.showMessageDialog(null, "Ya existe una sucursal con este nombre", "Error", JOptionPane.ERROR_MESSAGE);
				return;	
			}
			boolean update = this.sucursal.update(this.sucursalSeteada.getIdSucursal(), sucursalNueva);
			if(!update) {
				JOptionPane.showMessageDialog(null, "Ha ocurrido un error al actualizar esta sucursal", "Error", JOptionPane.ERROR_MESSAGE);
				return;		
			}else {
				JOptionPane.showMessageDialog(null, "Sucursal actualizada con exio", "Info", JOptionPane.INFORMATION_MESSAGE);			
			}
			this.todasLasSucursales = (ArrayList<SucursalDTO>) this.sucursal.readAll();
			limpiarDatos();
		}
	}

	
}
