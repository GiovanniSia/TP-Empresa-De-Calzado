package presentacion.controlador.gerente;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import dto.LocalidadDTO;
import dto.PaisDTO;
import dto.ProvinciaDTO;
import modelo.Localidad;
import modelo.Pais;
import modelo.Provincia;
import presentacion.vista.gerente.VentanaEditarLocalidad;

public class ControladorEditarLocalidad {
	
	Pais pais;
	Provincia provincia;
	Localidad localidad;
	
	List<PaisDTO> todosLosPaises;
	List<ProvinciaDTO> todasLasProvincias;
	List<LocalidadDTO> todasLasLocalidades;
	
	PaisDTO paisSeleccionado;
	ProvinciaDTO provinciaSeleccionada;
	List<LocalidadDTO> localidadesEnTabla;
	
	VentanaEditarLocalidad ventanaEditarLocalidad;
	
	ControladorAltaCliente controladorAltaCliente;
	
	public ControladorEditarLocalidad(ControladorAltaCliente controladorAltaCliente, Pais pais, Provincia provincia, Localidad localidad) {
		this.pais = pais;
		this.provincia = provincia;
		this.localidad = localidad;
		this.controladorAltaCliente = controladorAltaCliente;
			
		this.ventanaEditarLocalidad = new VentanaEditarLocalidad();
		
		this.todosLosPaises = new ArrayList<PaisDTO>(); 
		this.todasLasProvincias = new ArrayList<ProvinciaDTO>();
		this.todasLasLocalidades = new ArrayList<LocalidadDTO>() ;
		this.localidadesEnTabla = new ArrayList<LocalidadDTO>();	
		

		
	}
	

	public void inicializar() {
		this.todosLosPaises = this.pais.readAll();
		this.todasLasProvincias = this.provincia.readAll();
		this.todasLasLocalidades = this.localidad.readAll();
		this.ventanaEditarLocalidad.getBtnAgregarLocalidad().addActionListener(a -> agregarLocalidad());
		
		
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
	
		
	public boolean ventanaEstaInicializada() {
		return this.ventanaEditarLocalidad.getFrame().isShowing();
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

		boolean insert = this.localidad.agregarLocalidad(nuevaLocalidad);
		if(!insert) {
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error al agregar una nueva localidad");
			return;		
		}
		this.todasLasLocalidades = this.localidad.readAll();
		this.ventanaEditarLocalidad.getTxtNuevaLocalidad().setText("");

		actualizarComboBoxProv();
		actualizarTabla();
		actualizarComboBoxesDeCliente();
	}

	
	
	
	public void actualizarComboBoxesDeCliente() {
		System.out.println("se actualiza los cb");
		this.controladorAltaCliente.actualizarComboBoxes();
	}
}
