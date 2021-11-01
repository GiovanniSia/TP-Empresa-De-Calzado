package presentacion.controlador.gerente;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import dto.PaisDTO;
import dto.ProvinciaDTO;
import modelo.Pais;
import modelo.Provincia;
import presentacion.vista.gerente.VentanaEditarProvincia;

public class ControladorEditarProvincia {

	Pais pais;
	Provincia provincia;
	
	
	VentanaEditarProvincia ventanaEditarProvincia;
	
	List<PaisDTO> todosLosPaises;
	List<ProvinciaDTO> todosLasProvincias;
	
	List<ProvinciaDTO> provinciasEnTabla;
	PaisDTO paisEnTabla;
	
	ControladorAltaCliente controladorAltaCliente;
	
	public ControladorEditarProvincia(ControladorAltaCliente controladorAltaCliente,Pais pais,Provincia provincia) {
		this.pais = pais;
		this.provincia = provincia;
		this.controladorAltaCliente = controladorAltaCliente;
		
		this.todosLosPaises = new ArrayList<PaisDTO>();
		this.todosLasProvincias= new ArrayList<ProvinciaDTO>();
		this.provinciasEnTabla = new ArrayList<ProvinciaDTO>();
		
		this.ventanaEditarProvincia = new VentanaEditarProvincia();
		
		this.ventanaEditarProvincia.getBtnAgregar().addActionListener(a -> agregarProvincia(a));
		this.ventanaEditarProvincia.getBtnSalir().addActionListener(a -> salir(a));
	}
	
	public void inicializar() {
		this.todosLosPaises = this.pais.readAll();
		this.todosLasProvincias = this.provincia.readAll();
		llenarComboBoxes();
	}
	
	
	public void mostrarVentana() {
		this.ventanaEditarProvincia.show();
	}
	
	public void cerrarVentana() {
		this.ventanaEditarProvincia.cerrarVentana();
	}
	
	public void salir(ActionEvent a) {
		cerrarVentana();
	}
	
	public void llenarComboBoxes() {
		this.ventanaEditarProvincia.getComboBox().removeAllItems();
		
		for(PaisDTO p: this.todosLosPaises) {
			this.ventanaEditarProvincia.getComboBox().addItem(p.getNombrePais());
		}
		this.ventanaEditarProvincia.getComboBox().setSelectedIndex(0);
	}
	
	
	public void llenarTablaPorDefecto() {
		this.ventanaEditarProvincia.getModelProvincia().setRowCount(0);//borrar datos de la tabla
		this.ventanaEditarProvincia.getModelProvincia().setColumnCount(0);
		this.ventanaEditarProvincia.getModelProvincia().setColumnIdentifiers(this.ventanaEditarProvincia.getNombreColumnasProvincia());
		
		String nombrePaisEnCb = this.ventanaEditarProvincia.getComboBox().getSelectedItem().toString();
		PaisDTO paisEnCb = getPais(nombrePaisEnCb);
		this.paisEnTabla = paisEnCb;
		
		for(ProvinciaDTO prov: this.todosLasProvincias) {
			if(prov.getIdProvincia()== paisEnCb.getIdPais()) {
				String nombrePais = paisEnCb.getNombrePais();
				String nombreProv = prov.getNombreProvincia();
				String[] fila = {nombrePais,nombreProv};
				this.ventanaEditarProvincia.getModelProvincia().addRow(fila);
				this.provinciasEnTabla.add(prov);
			}
		}
	}

	
	public PaisDTO getPais(String nombrePais) {
		this.todosLosPaises = this.pais.readAll();
		for(PaisDTO p: this.todosLosPaises) {
			if(p.getNombrePais().equals(nombrePais)) {
				return p;
			}
		}
		return null;
	}
	
	
	private void agregarProvincia(ActionEvent a) {
		String nombreProvincia = this.ventanaEditarProvincia.getTxtLocalidad().getText();

		if (nombreProvincia.contentEquals("")) {
			JOptionPane.showMessageDialog(null, "Campo nombre no debe estar vacio");
			return;
		}
		String paisElegido = this.ventanaEditarProvincia.getComboBox().getSelectedItem().toString();
		PaisDTO paisSeleccionado = getPais(paisElegido);

		if (yaExisteProvincia(paisSeleccionado, nombreProvincia)) {
			JOptionPane.showMessageDialog(null, "Esta provincia ya existe para este pais");
			return;
		}
		
		ProvinciaDTO nuevaLocalidad = new ProvinciaDTO(0, nombreProvincia, paisSeleccionado.getIdPais());

		boolean insert = this.provincia.insert(nuevaLocalidad);
		if(!insert) {
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error al insertar una nueva provincia", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		this.ventanaEditarProvincia.getTxtLocalidad().setText("");
		this.todosLosPaises = this.pais.readAll();
		actualizarVentana(paisSeleccionado);
		actualizarComboBoxesDeCliente();
	}
	
/*
	private void editarProvincia(ActionEvent w) {
		int filaSeleccionada = this.ventanaProvincia.tablaProvinciaSeleccionada();

		String nombreNuevo = (String) this.ventanaProvincia.getTxtFieldId().getText();

		if (nombreNuevo.contentEquals("") || filaSeleccionada == -1) {
			JOptionPane.showMessageDialog(null, "Campo nombre no debe estar vacio y se debe seleccionar una fila");
			return;
		}

		String paisElegido = (String) this.ventanaProvincia.getComboBox().getSelectedItem();
		if (paisElegido == null) {
			return;
		}

		PaisDTO PaisSeleccionado = getPaisDeTabla(paisElegido);

		DefaultTableModel modelo = (DefaultTableModel) this.ventanaProvincia.getTablaProvincia().getModel();
		String nombreProvincia = (String) modelo.getValueAt(filaSeleccionada, 0);

		ProvinciaDTO provinciaEditar = getProvinciaDeTabla(nombreProvincia, PaisSeleccionado.getIdPais());
		this.provincia.editarProvincia(nombreNuevo, provinciaEditar);
		this.provinciaEnTabla = this.provincia.obtenerProvincia();

		this.ventanaProvincia.limpiarTodosTxt();
		this.llenarTablaProvincia(paisElegido);
		this.refrescarComboBoxes();
		return;
	}

	private void borrarProvincia(ActionEvent r) {
		int filaSeleccionada = this.ventanaProvincia.getTablaProvincia().getSelectedRow();
		if (filaSeleccionada == -1) {
			JOptionPane.showMessageDialog(null, "Debes seleccionar al menos una fila");
			return;
		}

		String paisElegido = (String) this.ventanaProvincia.getComboBox().getSelectedItem();
		if (paisElegido == null) {
			return;
		}

		DefaultTableModel modelo = (DefaultTableModel) this.ventanaProvincia.getTablaProvincia().getModel();
		PaisDTO PaisSeleccionado = getPaisDeTabla(paisElegido);
		String nombreProvinciaBorrar = (String) modelo.getValueAt(filaSeleccionada, 0);

		ProvinciaDTO provinciaElegida = getProvinciaDeTabla(nombreProvinciaBorrar, PaisSeleccionado.getIdPais());

		for (int i = 0; i < this.provinciaEnTabla.size(); i++) {
			if (this.provinciaEnTabla.get(i).equals(provinciaElegida)) {
				this.provincia.borrarProvincia(this.provinciaEnTabla.get(i));
			}
		}

		this.ventanaProvincia.limpiarTodosTxt();
		this.provinciaEnTabla = this.provincia.obtenerProvincia();
		this.llenarTablaProvincia(paisElegido);
		this.refrescarComboBoxes();
		return;
	}

	private void salirProvincia(ActionEvent t) {
		this.refrescarComboBoxes();
		this.ventanaProvincia.cerrarVentana();
	}

	public void actualizarTablaProvincia(ActionEvent t) {
		String paisElegido = (String) this.ventanaProvincia.getComboBox().getSelectedItem();
		if (paisElegido == null) {
			return;
		}
		this.llenarTablaProvincia(paisElegido);
	}

	public void llenarTablaProvincia(String paisElegido) {
		this.ventanaProvincia.getModelProvincia().setRowCount(0); // Para vaciar la tabla
		this.ventanaProvincia.getModelProvincia().setColumnCount(0);
		this.ventanaProvincia.getModelProvincia()
				.setColumnIdentifiers(this.ventanaProvincia.getNombreColumnasProvincia());

		PaisDTO paisReferenciado = getPaisDeTabla(paisElegido);

		// Si el no hay paises
		if (paisReferenciado == null) {
			Object[] fila = { "", "", "" };
			this.ventanaEditarLocalidad.getModelTabla().addRow(fila);
			return;
		}

		for (ProvinciaDTO provincia : this.provinciaEnTabla) {
			if (provincia.getForeignPais() == paisReferenciado.getIdPais()) {
				Object[] fila = { provincia.getNombreProvincia(), paisElegido };
				this.ventanaProvincia.getModelProvincia().addRow(fila);
			}
		}
		return;
	}
	
*/
	public boolean yaExisteProvincia(PaisDTO pais,String nombreProvincia) {
		for(PaisDTO p: this.todosLosPaises) {
			for(ProvinciaDTO prov: this.todosLasProvincias) {
				if(pais.getIdPais()==p.getIdPais() && p.getIdPais() == prov.getForeignPais()) {
					if(prov.getNombreProvincia().equals(nombreProvincia)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public void actualizarVentana(PaisDTO paisSeleccionado) {
		this.ventanaEditarProvincia.getModelProvincia().setRowCount(0);//borrar datos de la tabla
		this.ventanaEditarProvincia.getModelProvincia().setColumnCount(0);
		this.ventanaEditarProvincia.getModelProvincia().setColumnIdentifiers(this.ventanaEditarProvincia.getNombreColumnasProvincia());
		this.provinciasEnTabla.removeAll(this.provinciasEnTabla);
		
		this.todosLasProvincias = this.provincia.readAll();
		
		for(ProvinciaDTO prov: this.todosLasProvincias) {
			if(prov.getForeignPais() == this.paisEnTabla.getIdPais()) {
				String pais = this.paisEnTabla.getNombrePais();
				String provincia = prov.getNombreProvincia();
				String[] fila = {pais,provincia};
				this.ventanaEditarProvincia.getModelProvincia().addRow(fila);
				this.provinciasEnTabla.add(prov);
			}
		}
		
	}
	
	
	public void actualizarComboBoxesDeCliente() {
		this.controladorAltaCliente.actualizarComboBoxes();
	}

	public boolean ventanaYaFueInicializada() {
		return this.ventanaEditarProvincia.getFrame().isShowing();
	}
}
