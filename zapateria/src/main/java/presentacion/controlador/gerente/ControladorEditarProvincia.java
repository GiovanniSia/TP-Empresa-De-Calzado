package presentacion.controlador.gerente;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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
		this.ventanaEditarProvincia.getBtnEditar().addActionListener(a -> editarProvincia(a));
		this.ventanaEditarProvincia.getBtnBorrar().addActionListener(a -> borrarProvincia(a));
		this.ventanaEditarProvincia.getBtnSalir().addActionListener(a -> salir(a));
		
		this.ventanaEditarProvincia.getComboBox().addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				actualizarTabla(); 	
			}
		});
		
		this.ventanaEditarProvincia.getTablaProvincia().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ListSelectionModel rowSM = this.ventanaEditarProvincia.getTablaProvincia().getSelectionModel();
		
		rowSM.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				int filaSeleccionada = ventanaEditarProvincia.getTablaProvincia().getSelectedRow();
				if (filaSeleccionada == -1) {
					return;
				}

				String nombreProv = ventanaEditarProvincia.getModelProvincia().getValueAt(filaSeleccionada, 0).toString();
				ventanaEditarProvincia.getTxtLocalidad().setText(nombreProv);
			}
			
		});
		
	}
	
	public void inicializar() {
		this.todosLosPaises = this.pais.readAll();
		this.todosLasProvincias = this.provincia.readAll();
		llenarComboBoxes();
		llenarTablaPorDefecto();
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
			if(prov.getForeignPais()== paisEnCb.getIdPais()) {
				String nombreProv = prov.getNombreProvincia();
				String[] fila = {nombreProv};
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
		actualizarTabla();
		actualizarComboBoxesDeCliente();
	}
	

	private void editarProvincia(ActionEvent w) {
		int filaSeleccionada = this.ventanaEditarProvincia.tablaProvinciaSeleccionada();

		if(filaSeleccionada==-1) {
			JOptionPane.showMessageDialog(null, "Debe seleccionar una provincia");
			return;
		}
		
		String nombreNuevo = this.ventanaEditarProvincia.getTxtLocalidad().getText().toString();

		if (nombreNuevo.contentEquals("")) {
			JOptionPane.showMessageDialog(null, "Campo nombre no debe estar vacio");
			return;
		}
				
		ProvinciaDTO provincia = this.provinciasEnTabla.get(filaSeleccionada);
				
		boolean update = this.provincia.update(nombreNuevo, provincia);
		if(!update) {
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error al actualizar una nueva provincia", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		actualizarTabla();
		actualizarComboBoxesDeCliente();
	}
	
	
	private void borrarProvincia(ActionEvent r) {
		int filaSeleccionada = this.ventanaEditarProvincia.getTablaProvincia().getSelectedRow();
		if (filaSeleccionada == -1) {
			JOptionPane.showMessageDialog(null, "Debes seleccionar al menos una fila");
			return;
		}
		
		ProvinciaDTO prov = this.provinciasEnTabla.get(filaSeleccionada);
		boolean delete = this.provincia.delete(prov);
		if(!delete) {
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error al borrar la provincia", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		actualizarTabla();
		actualizarComboBoxesDeCliente();
	}

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
	
	public void actualizarTabla() {
		
		if(this.ventanaEditarProvincia.getComboBox().getSelectedIndex()==-1) {
			System.out.println("item seleccionado -1");
			return;
		}
		
		this.ventanaEditarProvincia.getModelProvincia().setRowCount(0);//borrar datos de la tabla
		this.ventanaEditarProvincia.getModelProvincia().setColumnCount(0);
		this.ventanaEditarProvincia.getModelProvincia().setColumnIdentifiers(this.ventanaEditarProvincia.getNombreColumnasProvincia());
		this.provinciasEnTabla.removeAll(this.provinciasEnTabla);
		
		String paisEnCb = this.ventanaEditarProvincia.getComboBox().getSelectedItem().toString();
		PaisDTO pais = getPais(paisEnCb);
		this.paisEnTabla = pais;
		
		this.todosLasProvincias = this.provincia.readAll();
		
		for(ProvinciaDTO prov: this.todosLasProvincias) {
			if(prov.getForeignPais() == this.paisEnTabla.getIdPais()) {
				String provincia = prov.getNombreProvincia();
				String[] fila = {provincia};
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
