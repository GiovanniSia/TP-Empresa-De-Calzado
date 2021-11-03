package presentacion.controlador.gerente;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dto.ClienteDTO;
import dto.PaisDTO;
import modelo.Cliente;
import modelo.Pais;
import presentacion.vista.gerente.VentanaEditarPais;

public class ControladorEditarPais {

	List<PaisDTO> todosLosPaises;
	Pais pais;
	
	Cliente cliente;
	
	VentanaEditarPais ventanaEditarPais;
	ControladorAltaCliente controladorAltaCliente;
	
	public ControladorEditarPais(ControladorAltaCliente controladorAltaCliente,Pais pais,Cliente cliente) {
		this.cliente = cliente;
		this.todosLosPaises = new ArrayList<PaisDTO>();
		this.pais = pais;
//		this.ventanaEditarPais = new VentanaEditarPais();
		
		this.controladorAltaCliente = controladorAltaCliente;
		
	}
	
	
	
	public void inicializar() {
		this.ventanaEditarPais = new VentanaEditarPais();
		
		this.todosLosPaises = this.pais.readAll();

		this.ventanaEditarPais.getBtnAgregarPais().addActionListener(a -> agregarPais(a));
		this.ventanaEditarPais.getBtnEditarPais().addActionListener(a -> editarPais(a));
		this.ventanaEditarPais.getBtnEliminarPais().addActionListener(a -> borrarPais(a));
		this.ventanaEditarPais.getBtnSalirPais().addActionListener(a -> salir(a));
		
		this.ventanaEditarPais.getTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ListSelectionModel rowSM = this.ventanaEditarPais.getTable().getSelectionModel();
		
		rowSM.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				int filaSeleccionada = ventanaEditarPais.getTable().getSelectedRow();
				if (filaSeleccionada == -1) {
					return;
				}

				String nombrePais = ventanaEditarPais.getModelTabla().getValueAt(filaSeleccionada, 0).toString();
				ventanaEditarPais.getTextPaisNuevo().setText(nombrePais);
			}
			
		});
		
		llenarTablaPaises();
	}

	public void mostrarVentana() {
		this.ventanaEditarPais.show();
		
	}

	public void salir(ActionEvent a) {
		cerrarVentana();
	}
	
	public void cerrarVentana() {
		this.ventanaEditarPais.cerrar();
	}
	
	
	private void llenarTablaPaises() {
		this.ventanaEditarPais.getModelTabla().setRowCount(0);//borrar datos de la tabla
		this.ventanaEditarPais.getModelTabla().setColumnCount(0);
		this.ventanaEditarPais.getModelTabla().setColumnIdentifiers(this.ventanaEditarPais.getNombreColumnas());
		
		for(PaisDTO p: this.todosLosPaises) {
			String nombre = p.getNombrePais();
			Object[] fila = {nombre};
			this.ventanaEditarPais.getModelTabla().addRow(fila);
		}
	}

	public void agregarPais(ActionEvent a) {
		String nombrePaisNuevo = this.ventanaEditarPais.getTextPaisNuevo().getText();
		if (nombrePaisNuevo.equals("")) {
			JOptionPane.showMessageDialog(null, "Escriba el nombre del nuevo pais");
			return;
		}		
		if (yaExisteElPais(nombrePaisNuevo)) {
			JOptionPane.showMessageDialog(null, "Este pais ya existe!");
			return;
		}

		PaisDTO nuevoPais = new PaisDTO(0, nombrePaisNuevo);
		boolean insert = this.pais.insert(nuevoPais);
		if(!insert) {
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error al insertar el nuevo pais");
			return;			
		}
		this.ventanaEditarPais.getTextPaisNuevo().setText("");
		this.todosLosPaises = this.pais.readAll();
		llenarTablaPaises();
		JOptionPane.showMessageDialog(null, "Pais añadido con exito", "Info", JOptionPane.INFORMATION_MESSAGE);
		actualizarComboBoxesDeCliente();
	}

	public boolean yaExisteElPais(String nuevoPais) {
		for (PaisDTO p : this.todosLosPaises) {
			if (p.getNombrePais().equals(nuevoPais)) {
				return true;
			}
		}
		return false;
	}
	
	
	public void borrarPais(ActionEvent a) {
		int filaSeleccionada = this.ventanaEditarPais.getTable().getSelectedRow();
		if (filaSeleccionada == -1) {
			JOptionPane.showMessageDialog(null, "Seleccione un pais para borrar");
			return;
		}

		String nombrePaisBorrar = this.ventanaEditarPais.getModelTabla().getValueAt(filaSeleccionada, 0).toString();

		PaisDTO paisElegido = getPaisDeTabla(nombrePaisBorrar);
		
		
		if(!noTieneNingunClienteAsignado(paisElegido)) {
			JOptionPane.showMessageDialog(null, "No se puede borrar el pais ya que existe un cliente que tiene asignado este pasi", "Error", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		boolean borrar = this.pais.borrarPais(paisElegido);
		
		if(!borrar) {
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error al borrar el pais");
			return;
		}
		
		this.ventanaEditarPais.getTextPaisNuevo().setText("");
		
		this.todosLosPaises = this.pais.readAll();
		llenarTablaPaises();
		JOptionPane.showMessageDialog(null, "Pais eliminado con exito", "Info", JOptionPane.INFORMATION_MESSAGE);
		actualizarComboBoxesDeCliente();
	}
	
	public PaisDTO getPaisDeTabla(String nombrePais) {
		for (PaisDTO pais : this.todosLosPaises) {
			if (pais.getNombrePais().equals(nombrePais)) {
				return pais;
			}
		}
		return null;
	}
	
	public void editarPais(ActionEvent e) {
		String nombreNuevo = this.ventanaEditarPais.getTextPaisNuevo().getText();
		
		if(nombreNuevo.equals("")) {
			JOptionPane.showMessageDialog(null, "El nombre no puede ser vacio!");
			return;	
		}
		
		if (yaExisteElPais(nombreNuevo)) {
			JOptionPane.showMessageDialog(null, "El pais ya existe!");
			return;
		}
		int filaSeleccionada = this.ventanaEditarPais.getTable().getSelectedRow();
		if (filaSeleccionada == -1) {
			JOptionPane.showMessageDialog(null, "Seleccione un pais para editar!");
			return;
		}
		
		String nombrePais = this.ventanaEditarPais.getModelTabla().getValueAt(filaSeleccionada, 0).toString();

		PaisDTO paisEditar = getPaisDeTabla(nombrePais);
				
		boolean update = this.pais.update(paisEditar, nombreNuevo);
		
		if(!update) {
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error al editar el pais");
			return;			
		}
		actualizarDatosClientes(paisEditar,nombreNuevo);
		
		this.todosLosPaises = this.pais.readAll();
		this.ventanaEditarPais.getTextPaisNuevo().setText("");
		llenarTablaPaises();
		JOptionPane.showMessageDialog(null, "Pais actualizado con exito", "Info", JOptionPane.INFORMATION_MESSAGE);
		actualizarComboBoxesDeCliente();
	}
	
	
	public void actualizarComboBoxesDeCliente() {
		this.controladorAltaCliente.actualizarComboBoxes();
	}
	
	public boolean ventanaYaEstaInicializada() {
		if(this.ventanaEditarPais == null) return false;
		return this.ventanaEditarPais.getFrame().isShowing();
	}
	
	
	public boolean noTieneNingunClienteAsignado(PaisDTO pais) {
		ArrayList<ClienteDTO> todosLosClientes = (ArrayList<ClienteDTO>) this.cliente.readAll();
		for(ClienteDTO c: todosLosClientes) {
			if(c.getPais().equals(pais.getNombrePais())) {
				return false; 
			}
		}return true;
	}
	
	public void actualizarDatosClientes(PaisDTO pais,String nombreNuevo) {
		ArrayList<ClienteDTO> todosLosClientes = (ArrayList<ClienteDTO>) this.cliente.readAll();
		for(ClienteDTO c: todosLosClientes) {
			if(c.getPais().equals(pais.getNombrePais())) {
				c.setPais(nombreNuevo);
				boolean update = this.cliente.update(c.getIdCliente(),c);
				if(!update) {
					JOptionPane.showMessageDialog(null, "Error al actualizar el pais de clientes ", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			
		}
	}
	
}
