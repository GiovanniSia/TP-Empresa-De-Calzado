package presentacion.controlador.gerente;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dto.ClienteDTO;
import dto.LocalidadDTO;
import dto.PaisDTO;
import dto.ProvinciaDTO;
import modelo.Cliente;
import modelo.Localidad;
import modelo.Pais;
import modelo.Provincia;
import presentacion.controlador.ValidadorTeclado;
import presentacion.vista.gerente.VentanaEditarLocalidad;

public class ControladorEditarLocalidad {
	
	Pais pais;
	Provincia provincia;
	Localidad localidad;
	
	Cliente cliente;
	
	List<PaisDTO> todosLosPaises;
	List<ProvinciaDTO> todasLasProvincias;
	List<LocalidadDTO> todasLasLocalidades;
	
	PaisDTO paisSeleccionado;
	ProvinciaDTO provinciaSeleccionada;
	List<LocalidadDTO> localidadesEnTabla;
	
	VentanaEditarLocalidad ventanaEditarLocalidad;
	
	ControladorAltaCliente controladorAltaCliente;
	
	public ControladorEditarLocalidad(ControladorAltaCliente controladorAltaCliente, Pais pais, Provincia provincia, Localidad localidad,Cliente cliente) {
		this.cliente = cliente;
		this.pais = pais;
		this.provincia = provincia;
		this.localidad = localidad;
		this.controladorAltaCliente = controladorAltaCliente;
			

		
		this.todosLosPaises = new ArrayList<PaisDTO>(); 
		this.todasLasProvincias = new ArrayList<ProvinciaDTO>();
		this.todasLasLocalidades = new ArrayList<LocalidadDTO>() ;
		this.localidadesEnTabla = new ArrayList<LocalidadDTO>();	


		
	}
	

	public void inicializar() {
		this.ventanaEditarLocalidad = new VentanaEditarLocalidad();
		this.todosLosPaises = this.pais.readAll();
		this.todasLasProvincias = this.provincia.readAll();
		this.todasLasLocalidades = this.localidad.readAll();
		
		this.ventanaEditarLocalidad.getBtnAgregarLocalidad().addActionListener(a -> agregarLocalidad());
		this.ventanaEditarLocalidad.getBtnBorrarLocalidad().addActionListener(a -> borrarLocalidad());
		this.ventanaEditarLocalidad.getBtnEditarLocalidad().addActionListener(a -> editarLocalidad());
		this.ventanaEditarLocalidad.getBtnSalirLocalidad().addActionListener(a -> cerrarVentana());
		
		this.ventanaEditarLocalidad.getTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ListSelectionModel rowSM = this.ventanaEditarLocalidad.getTable().getSelectionModel();
		
		rowSM.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				int filaSeleccionada = ventanaEditarLocalidad.getTable().getSelectedRow();
				if (filaSeleccionada == -1) {
					return;
				}

				String nombreLoc = ventanaEditarLocalidad.getModelTabla().getValueAt(filaSeleccionada, 0).toString();
				ventanaEditarLocalidad.getTxtNuevaLocalidad().setText(nombreLoc);
			}
			
		});
		
		validarTeclado();
		escribirComboBoxes();
		actualizarTabla();
		escucharComboBoxes();
	}

	
	public void escucharComboBoxes() {
		this.ventanaEditarLocalidad.getComboBoxPaises().addActionListener(a -> actualizarComboBoxesSegunPais());
		this.ventanaEditarLocalidad.getComboProvincias().addActionListener(a -> actualizarTablaSegunProvincia());		
	}
	
	public void mostrarVentana() {
		this.ventanaEditarLocalidad.show();
	}

	public void cerrarVentana() {
		this.ventanaEditarLocalidad.cerrar();
	}

	
	public void escribirComboBoxes() {
		this.ventanaEditarLocalidad.getComboBoxPaises().removeAllItems();
		this.ventanaEditarLocalidad.getComboProvincias().removeAllItems();
		this.paisSeleccionado=null;
		this.provinciaSeleccionada=null;
		
		int cant=0;
		for(PaisDTO p: this.todosLosPaises) {
			this.ventanaEditarLocalidad.getComboBoxPaises().addItem(p.getNombrePais());
			cant++;
		}
		if(cant==0) return;
		
		this.ventanaEditarLocalidad.getComboBoxPaises().setSelectedIndex(0);
		this.paisSeleccionado = this.todosLosPaises.get(0);
		
		llenarCbProvinciaDadoPais(this.paisSeleccionado.getIdPais());		
	}
	
	public void llenarCbProvinciaDadoPais(int idPais) {
		int cant=0;
		for(ProvinciaDTO prov: this.todasLasProvincias) {
			if(prov.getForeignPais()==idPais) {
				this.ventanaEditarLocalidad.getComboProvincias().addItem(prov.getNombreProvincia());
				cant++;
			}
		}
		if(cant!=0) {
			System.out.println("se setea una prov seleccionada");
			System.out.println("Pais seleccionado: "+this.paisSeleccionado.getNombrePais());
			System.out.println("Nombre prov en cb: "+this.ventanaEditarLocalidad.getComboProvincias().getSelectedItem().toString());
			this.ventanaEditarLocalidad.getComboProvincias().setSelectedIndex(0);
			this.provinciaSeleccionada = getProvincia(idPais, this.ventanaEditarLocalidad.getComboProvincias().getSelectedItem().toString());
		}
		
		
		
	}
		
//	public void llenarTabla() {
//		this.ventanaEditarLocalidad.getModelTabla().setRowCount(0);//borrar datos de la tabla
//		this.ventanaEditarLocalidad.getModelTabla().setColumnCount(0);
//		this.ventanaEditarLocalidad.getModelTabla().setColumnIdentifiers(this.ventanaEditarLocalidad.getNombreColumnas());
//		
//		this.localidadesEnTabla.removeAll(this.localidadesEnTabla);
//		for(LocalidadDTO l: this.todasLasLocalidades) {
//			if(l.getIdForeignProvincia() == this.provinciaSeleccionada.getIdProvincia()) {
//				String nombreLoc = l.getNombreLocalidad();
//				String[] fila = {nombreLoc};
//				this.ventanaEditarLocalidad.getModelTabla().addRow(fila);
//				this.localidadesEnTabla.add(l);
//				
//			}
//		}
//	}
	public boolean yaExisteLocalidad(ProvinciaDTO provincia, String nombre) {
		for (LocalidadDTO localidad : this.todasLasLocalidades) {
			if (localidad.getIdForeignProvincia() == provincia.getIdProvincia()
					&& localidad.getNombreLocalidad().equals(nombre)) {
				return true;
			}
		}
		return false;
	}
	
	
	public void actualizarTabla() {
		this.ventanaEditarLocalidad.getModelTabla().setRowCount(0);
		this.ventanaEditarLocalidad.getModelTabla().setColumnCount(0);
		this.ventanaEditarLocalidad.getModelTabla().setColumnIdentifiers(this.ventanaEditarLocalidad.getNombreColumnas());
		this.localidadesEnTabla.removeAll(this.localidadesEnTabla);
		// Si no hay paises
		if (this.paisSeleccionado == null) {
			Object[] fila = {""};
			this.ventanaEditarLocalidad.getModelTabla().addRow(fila);
			return;
		}

		// Si el pais no tiene provincias
		if (this.provinciaSeleccionada == null) {
			Object[] fila = {""};
			this.ventanaEditarLocalidad.getModelTabla().addRow(fila);
			System.out.println("este pais no tiene prov, se setea '' en la tabla");
			return;
		}
		
		
		
		int cantLoc = 0;
		for (LocalidadDTO localidad : this.todasLasLocalidades) {
			if (localidad.getIdForeignProvincia() == this.provinciaSeleccionada.getIdProvincia()) {
				Object[] fila = {localidad.getNombreLocalidad() };
				this.ventanaEditarLocalidad.getModelTabla().addRow(fila);
				cantLoc++;
				this.localidadesEnTabla.add(localidad);
			}

		}
//			Si una prov no tiene localidades le agregamos una vacia
		if (cantLoc == 0) {
			Object[] fila = {""};
			this.ventanaEditarLocalidad.getModelTabla().addRow(fila);
		}
	}

	
	private void actualizarTablaSegunProvincia() {
		this.ventanaEditarLocalidad.getModelTabla().setRowCount(0);
		this.ventanaEditarLocalidad.getModelTabla().setColumnCount(0);
		this.ventanaEditarLocalidad.getModelTabla().setColumnIdentifiers(this.ventanaEditarLocalidad.getNombreColumnas());
		
		if(this.ventanaEditarLocalidad.getComboProvincias().getSelectedItem() == null) {
			return;
		}
		System.out.println("Se ejecuta la escucha de actualizar tabla segun prov");
		String provinciaEnCb = this.ventanaEditarLocalidad.getComboProvincias().getSelectedItem().toString();
		this.provinciaSeleccionada = getProvincia(this.paisSeleccionado.getIdPais(),provinciaEnCb);
		actualizarTabla();
	}

	private void actualizarComboBoxesSegunPais() {
		this.ventanaEditarLocalidad.getModelTabla().setRowCount(0);
		this.ventanaEditarLocalidad.getModelTabla().setColumnCount(0);
		this.ventanaEditarLocalidad.getModelTabla().setColumnIdentifiers(this.ventanaEditarLocalidad.getNombreColumnas());
		this.paisSeleccionado=null;
		this.provinciaSeleccionada=null;
//		Obtenemos el pais seleccionado
		String nombrePaisSeleccionado = (String) this.ventanaEditarLocalidad.getComboBoxPaises().getSelectedItem();

//		por alguna razón se ejecuta la escucha de cb de ventanaLocalidad cuando se inicia la ventana 
		if (nombrePaisSeleccionado == null) {
			return;
		}
		System.out.println("Se ejecuta la escucha de cambiarCb segun pais");
		this.paisSeleccionado = getPais(nombrePaisSeleccionado);
		// Obtenemos las provincias de ese pais y las escribimos
		this.ventanaEditarLocalidad.getComboProvincias().removeAllItems();
		for (ProvinciaDTO provincia : this.todasLasProvincias) {
			if (paisSeleccionado.getIdPais() == provincia.getForeignPais()) {
				this.ventanaEditarLocalidad.getComboProvincias().addItem(provincia.getNombreProvincia());
			}
		}		
		actualizarTabla();
	}
	
	public void actualizarComboBoxProv() {
		this.todasLasProvincias = this.provincia.readAll();
		this.ventanaEditarLocalidad.getComboProvincias().removeAllItems();
		for(ProvinciaDTO prov: this.todasLasProvincias) {
			if(prov.getForeignPais() == this.paisSeleccionado.getIdPais()) {
				this.ventanaEditarLocalidad.getComboProvincias().addItem(prov.getNombreProvincia());
			}
		}
		this.ventanaEditarLocalidad.getComboProvincias().setSelectedIndex(0);
	}
	
	
	public PaisDTO getPais(String nombrePais) {
		for(PaisDTO p: this.todosLosPaises) {
			if(p.getNombrePais().equals(nombrePais)) {
				return p;
			}
		}return null;
	}
	
	public ProvinciaDTO getProvincia(int idPais, String nombreProv) {
		System.out.println("se busca la prov en cb");
		for(ProvinciaDTO prov: this.todasLasProvincias) {
			
			if(prov.getForeignPais() == idPais && prov.getNombreProvincia().equals(nombreProv)) {
				return prov;
			}
		}return null;
	}

	
	public void agregarLocalidad() {
		String nombreLocalidadNueva = this.ventanaEditarLocalidad.getTxtNuevaLocalidad().getText();
		if (this.ventanaEditarLocalidad.getComboProvincias().getSelectedItem() == null) {
			JOptionPane.showMessageDialog(null, "El pais no tiene provincias!");
			return;
		}

		if (nombreLocalidadNueva.equals("")) {
			JOptionPane.showMessageDialog(null, "Por favor escriba una localidad nueva para agregar");
			return;
		}
//			Una vez que verificamos que la localidad tenga provincias, vemos si esa localidad ya existe para esa prov
		if (yaExisteLocalidad(this.provinciaSeleccionada, nombreLocalidadNueva)) {
			JOptionPane.showMessageDialog(null, "Esa localidad ya existe para esta provincia");
			return;
		}
		LocalidadDTO nuevaLocalidad = new LocalidadDTO(0, nombreLocalidadNueva, this.provinciaSeleccionada.getIdProvincia());

		boolean insert = this.localidad.insert(nuevaLocalidad);
		if(!insert) {
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error al agregar una nueva localidad", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		this.todasLasLocalidades = this.localidad.readAll();
		this.ventanaEditarLocalidad.getTxtNuevaLocalidad().setText("");

		actualizarComboBoxProv();
		actualizarTabla();
		JOptionPane.showMessageDialog(null, "Localidad insertada con exito", "Info", JOptionPane.INFORMATION_MESSAGE);
		actualizarComboBoxesDeCliente();
	}

	
	public void borrarLocalidad() {
		int filaSeleccionada = this.ventanaEditarLocalidad.getTable().getSelectedRow();

		if (filaSeleccionada == -1) {
			JOptionPane.showMessageDialog(null, "Seleccione una localidad de la tabla para borrar");
			return;
		}

		// Obtenemos toda la tabla con sus datos

	
		String nombreLocalidadBorrar = this.ventanaEditarLocalidad.getModelTabla().getValueAt(filaSeleccionada, 0).toString();

		if (this.provinciaSeleccionada == null) {
			JOptionPane.showMessageDialog(null, "El pais no tiene provincias ni localidad para borrar");
			return;
		}
		if (nombreLocalidadBorrar.equals("")) {
			JOptionPane.showMessageDialog(null, "La provincia seleccionada no tiene localidades para borrar");
			return;
		}
		// Obtenemos los obj

		LocalidadDTO loca = this.localidadesEnTabla.get(filaSeleccionada);

		if(!noTieneClienteAsignado(loca)) {
			JOptionPane.showMessageDialog(null, "La localidad tiene al menos un cliente asignado!, no se puede borrar.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		boolean delete = this.localidad.delete(loca);
		if(!delete) {
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error al borrar la localidad", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		this.todasLasLocalidades = this.localidad.readAll();
		this.ventanaEditarLocalidad.getTxtNuevaLocalidad().setText("");
		actualizarTabla();
		JOptionPane.showMessageDialog(null, "Localidad eliminada con exito", "Info", JOptionPane.INFORMATION_MESSAGE);
		actualizarComboBoxesDeCliente();
	}
	
	
	public void editarLocalidad() {
		int filaSeleccionada = this.ventanaEditarLocalidad.getTable().getSelectedRow();

		if (filaSeleccionada == -1) {
			JOptionPane.showMessageDialog(null, "Por favor seleccione una localidad de la fila para editar");
			return;
		}
		String nombreLocalidadEditar = this.ventanaEditarLocalidad.getTable().getModel().getValueAt(filaSeleccionada, 0).toString();

		if (this.provinciaSeleccionada==null) {
			JOptionPane.showMessageDialog(null, "El pais no tiene provincias!");
			return;
		}
		if (nombreLocalidadEditar.equals("")) {
			JOptionPane.showMessageDialog(null, "La provincia no tiene localidades!");
			return;
		}

		String nuevoNombre = this.ventanaEditarLocalidad.getTxtNuevaLocalidad().getText();
		
		//Verificamos si ya existe esa provincia con ese nuevo nombre
				
		if(yaExisteLocalidad(nuevoNombre)) {
			JOptionPane.showMessageDialog(null, "Ya existe una localidad con ese nombre!");
			return;
		}
		
		LocalidadDTO loc = this.localidadesEnTabla.get(filaSeleccionada);
		
		boolean update = this.localidad.update(loc, nuevoNombre);
		if(!update) {
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error al agregar editar la localidad", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		actualizarDatosClientes(loc,nuevoNombre);
		
//		for (LocalidadDTO loc : this.localidadesEnTabla) {
//			if (loc.getNombreLocalidad().equals(nombreLocalidadEditar)
//					&& loc.getIdForeignProvincia() == provinciaSeleccionada.getIdProvincia()) {
//				boolean update = this.localidad.update(loc, nuevoNombre);
//				if(!update) {
//					JOptionPane.showMessageDialog(null, "Ha ocurrido un error al agregar editar la localidad", "Error", JOptionPane.ERROR_MESSAGE);
//					return;
//				}
//			}
//		}

		this.todasLasLocalidades = this.localidad.readAll();
		this.ventanaEditarLocalidad.getTxtNuevaLocalidad().setText("");
		actualizarTabla();
		JOptionPane.showMessageDialog(null, "Localidad eliminada con exito", "Info", JOptionPane.INFORMATION_MESSAGE);
		actualizarComboBoxesDeCliente();

	}
	
	public boolean yaExisteLocalidad(String nuevoNombre) {
		for(LocalidadDTO l: this.todasLasLocalidades) {
			if(l.getIdForeignProvincia() == this.provinciaSeleccionada.getIdProvincia() && l.getNombreLocalidad().equals(nuevoNombre)) {
				return true;
			}
		}return false;
	}
	
	public void actualizarComboBoxesDeCliente() {
		System.out.println("se actualiza los cb");
		this.controladorAltaCliente.actualizarComboBoxes();
	}
	
	public boolean ventanaEstaInicializada() {
		if(this.ventanaEditarLocalidad == null) return false;
		return this.ventanaEditarLocalidad.getFrame().isShowing();
	}
	
	public void actualizarDatosClientes(LocalidadDTO localidad, String nombreNuevo) {
		ArrayList<ClienteDTO> todosLosClientes = (ArrayList<ClienteDTO>) this.cliente.readAll();
		for(ClienteDTO c: todosLosClientes) {
			if(c.getLocalidad().equals(localidad.getNombreLocalidad()) && c.getProvincia().equals(this.provinciaSeleccionada.getNombreProvincia()) && c.getPais().equals(this.paisSeleccionado.getNombrePais())) {
				c.setLocalidad(nombreNuevo);
				boolean update = this.cliente.update(c.getIdCliente(), c);
				if(!update) {
					JOptionPane.showMessageDialog(null, "Error al actualizar la localidad de clientes ", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
			}
		}
	}
	
	
	public boolean noTieneClienteAsignado(LocalidadDTO localidad) {
		ArrayList<ClienteDTO> todosLosClientes = (ArrayList<ClienteDTO>) this.cliente.readAll();
		for(ClienteDTO c: todosLosClientes) {
			if(c.getLocalidad().equals(localidad.getNombreLocalidad()) && c.getProvincia().equals(this.provinciaSeleccionada.getNombreProvincia()) && c.getPais().equals(this.paisSeleccionado.getNombrePais())) {
				return false;
			}
		}return true;
	}
	
	public void validarTeclado() {
		this.ventanaEditarLocalidad.getTxtNuevaLocalidad().addKeyListener((new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarLetrasYEspacios(e);
			}
		}));
	}
}
