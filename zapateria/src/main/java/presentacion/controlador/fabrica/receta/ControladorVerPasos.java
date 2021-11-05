package presentacion.controlador.fabrica.receta;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dto.MaestroProductoDTO;
import dto.PasoDTO;
import dto.PasoDeRecetaDTO;
import dto.RecetaDTO;
import modelo.Fabricacion;
import modelo.MaestroProducto;
import modelo.ModeloPaso;
import modelo.Receta;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.vista.fabrica.receta.VerPasos;

public class ControladorVerPasos implements ActionListener {
	
	VerPasos ventanaPrincipal;
	ModeloPaso modeloPaso;
	Fabricacion modeloFabricacion;
	MaestroProducto modeloMaestroProducto;
	Receta modeloReceta;
	
	List<PasoDTO> pasosEnLista;
	int[]filasSeleccionadas;
	
	List<RecetaDTO> recetasEnComboBox;
	RecetaDTO recetaSeleccionada = new RecetaDTO(0,0, "");
	List<PasoDeRecetaDTO> pasosRecetaEnLista;
	PasoDeRecetaDTO pasoDeRecetaSeleccionado;
	List<MaestroProductoDTO> ingredientesEnComboBox;
	List<MaestroProductoDTO> productosTerminados;
	
	public ControladorVerPasos() {
		ventanaPrincipal = new VerPasos();
		modeloPaso = new ModeloPaso(new DAOSQLFactory());
		modeloFabricacion = new Fabricacion(new DAOSQLFactory());
		modeloMaestroProducto = new MaestroProducto(new DAOSQLFactory());
		pasosEnLista = new ArrayList<PasoDTO>();
		modeloReceta = new Receta(new DAOSQLFactory());
		
		ventanaPrincipal.getTextDescripcion().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				refrescarTabla();
			}
		});
		ventanaPrincipal.getTextId().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				refrescarTabla();
			}
		});
		ventanaPrincipal.getBtnAgregar().addActionListener(r-> agregarPaso(r));
		ventanaPrincipal.getBtnEliminar().addActionListener(r-> botonEliminarPasos(r));
		
		ventanaPrincipal.getComboBoxReceta().addActionListener(r-> seleccionReceta(r));
		ventanaPrincipal.getTablaPasosReceta().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ListSelectionModel rowSM = ventanaPrincipal.getTablaPasosReceta().getSelectionModel();
		rowSM.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				refrescarTablaIngredientes();
			}
		});
		
		this.ventanaPrincipal.getBtnAgregarPasoAReceta().addActionListener(r->incluirPaso(r));
		this.ventanaPrincipal.getBtnReceta().addActionListener(r->darDeAltaReceta(r));
		this.ventanaPrincipal.getBtnAgregarIngrediente().addActionListener(r->agregarIngrediente(r));
		this.ventanaPrincipal.getBtnQuitarPasoReceta().addActionListener(r->quitarPasoReceta(r));
		this.ventanaPrincipal.getBtnQuitarIngrediente().addActionListener(r->quitarIngrediente(r));
	}

	public void inicializar() {
		refrescarComboBoxReceta();
		llenarTablaPasosReceta();
		refrescarTablaIngredientes();
		llenarComboBoxIngredientes();
		refrescarComboBoxProductosTerminados();
		
		refrescarTabla();
		ventanaPrincipal.mostrarVentana();
	}
	
	private void refrescarTabla() {
		reiniciarTablaTrabajos();
		pasosEnLista = recuperarListaPasos();
		llenarTablaConPasos(pasosEnLista);
	}
	
	private void llenarTablaConPasos(List<PasoDTO> pasosEnLista) {
		for(PasoDTO p : pasosEnLista) {
			Object[] agregar = {p.getIdPaso(), p.getDescripcion()};
			ventanaPrincipal.getModelPasos().addRow(agregar);
		}
	}

	private List<PasoDTO> recuperarListaPasos() {
		List<PasoDTO> ret = new ArrayList<PasoDTO>();
		List<PasoDTO> todosLosPasos = this.modeloPaso.readAll();
		String idBuscar = this.ventanaPrincipal.getTextId().getText()+"";
		String descripBuscar = this.ventanaPrincipal.getTextDescripcion().getText();
		for(PasoDTO p: todosLosPasos) {
			boolean deboAgregar = true;
			if(!idBuscar.equals("")) {
				deboAgregar = deboAgregar && (p.getIdPaso()+"").matches(".*"+idBuscar.toLowerCase()+".*");
			}
			if(!descripBuscar.equals("")) {
				deboAgregar = deboAgregar && p.getDescripcion().toLowerCase().matches(".*"+descripBuscar.toLowerCase()+".*");
			}
			if(deboAgregar) {
				ret.add(p);
			}
		}
		return ret;
	}

	private void reiniciarTablaTrabajos() {
		ventanaPrincipal.getModelPasos().setRowCount(0);
		ventanaPrincipal.getModelPasos().setColumnCount(0);
		ventanaPrincipal.getModelPasos().setColumnIdentifiers(ventanaPrincipal.getNombreColumnas());
	}
	
	private void agregarPaso(ActionEvent r) {
		String pasoAgregar = this.ventanaPrincipal.getTextDescripcionAgregar().getText();
		if(pasoAgregar == null) {
			return;
		}
		if(pasoAgregar.equals("")) {
			return;
		}
		if(pasoAgregar.length()>20) {
			return;
		}
		this.modeloPaso.insert(new PasoDTO(0,pasoAgregar));
		refrescarTabla();
	}
	
	private void botonEliminarPasos(ActionEvent r) {
		int[] filasSeleccionadas = ventanaPrincipal.getTablaFabricacionesEnMarcha().getSelectedRows();
		if(filasSeleccionadas.length == 0) {
			return;
		}
		this.filasSeleccionadas = ventanaPrincipal.getTablaFabricacionesEnMarcha().getSelectedRows();
		if(filasSeleccionadas.length > 1) {
			abrirVentanaParaPreguntarEliminarPasos();
			return;
		}
		eliminarPasosSeleccionados();
		refrescarTabla();
	}
	
	private void abrirVentanaParaPreguntarEliminarPasos() {
		int res = JOptionPane.showConfirmDialog(null, "¿estas seguro que quiere eliminar "+filasSeleccionadas.length+" pasos?", "", JOptionPane.YES_NO_OPTION);
        switch (res) {
            case JOptionPane.YES_OPTION:
            	eliminarPasosSeleccionados();
        		refrescarTabla();
        		break;
            case JOptionPane.NO_OPTION:
            	//JOptionPane.showMessageDialog(null, "No se eliminan");
            	break;
        }
	}
	
	private void eliminarPasosSeleccionados() {
		for(int idTabla: filasSeleccionadas) {
			if(sePuedeEliminarPaso(this.pasosEnLista.get(idTabla))) {
				this.modeloPaso.delete(this.pasosEnLista.get(idTabla));
			}
		}
	}
	
	private boolean sePuedeEliminarPaso(PasoDTO paso) {
		boolean ret = true;
		List<RecetaDTO> todasLasRecetas = this.modeloFabricacion.readAllReceta();
		for(RecetaDTO r: todasLasRecetas) {
			List<PasoDeRecetaDTO> todosLosPasos = this.modeloFabricacion.readAllPasosFromOneReceta(r.getIdReceta());
			for(PasoDeRecetaDTO p: todosLosPasos) {
				ret = ret && p.getPasosDTO().getIdPaso() != paso.getIdPaso();
				if(p.getPasosDTO().getIdPaso() == paso.getIdPaso()) {
					System.out.println("No se puede eliminar poque el paso: "+paso.getDescripcion()+" es usado en "+r.getDescripcion()+" en el paso "+p.getNroOrden());
					//return false;
				}
			}
		}
		//return true;
		return ret;
	}
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
	//	RECETAS
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
	
	private void refrescarComboBoxReceta() {
		recetasEnComboBox = new ArrayList<RecetaDTO>();
		recetasEnComboBox = recuperarRecetas();
		llenarComboBoxReceta(recetasEnComboBox);
	}
	
	private List<RecetaDTO> recuperarRecetas(){
		return this.modeloFabricacion.readAllReceta();
	}
	
	@SuppressWarnings("unchecked")
	private void llenarComboBoxReceta(List<RecetaDTO> recetas) {
		this.ventanaPrincipal.getComboBoxReceta().removeAllItems();
		this.ventanaPrincipal.getComboBoxReceta().addItem("Nueva receta");
		for(RecetaDTO r: recetas) {
			this.ventanaPrincipal.getComboBoxReceta().addItem(r.getDescripcion());
		}
	}
	
	private void seleccionReceta(ActionEvent r) {
		reiniciarTablaPasosReceta();
		llenarTablaPasosReceta();
	}
	
	private void llenarTablaPasosReceta() {
		reiniciarTablaPasosReceta();
		if(this.ventanaPrincipal.getComboBoxReceta().getSelectedIndex() == -1) {
			recetaSeleccionada = new RecetaDTO(0,0, "");
			return;
		}
		if(this.ventanaPrincipal.getComboBoxReceta().getSelectedIndex() == 0) {
			recetaSeleccionada = new RecetaDTO(0,0, "");
		}else {
			recetaSeleccionada = this.recetasEnComboBox.get(this.ventanaPrincipal.getComboBoxReceta().getSelectedIndex()-1);
		}
		pasosRecetaEnLista = this.modeloFabricacion.readAllPasosFromOneReceta(recetaSeleccionada.getIdReceta());
		llenarTablaPasosReceta(pasosRecetaEnLista);
		this.ventanaPrincipal.getTextFieldReceta().setText(recetaSeleccionada.getDescripcion());
		this.ventanaPrincipal.getComboBoxProductos().setSelectedIndex(recetaSeleccionada.getIdProducto()-1);
	}
	
	private void llenarTablaPasosReceta(List<PasoDeRecetaDTO> pasos) {
		for(PasoDeRecetaDTO p: pasos) {
			Object[] agregar = {p.getPasosDTO().getIdPaso(), p.getPasosDTO().getDescripcion(), p.getNroOrden()};
			this.ventanaPrincipal.getModelPasosReceta().addRow(agregar);
		}
	}

	private void reiniciarTablaPasosReceta() {
		ventanaPrincipal.getModelPasosReceta().setRowCount(0);
		ventanaPrincipal.getModelPasosReceta().setColumnCount(0);
		ventanaPrincipal.getModelPasosReceta().setColumnIdentifiers(ventanaPrincipal.getNombreColumnasPasosReceta());
	}
	
	private void refrescarTablaIngredientes() {
		reiniciarTablaIngredientes();
		if(this.recetaSeleccionada == null) {
			return;
		}
		if(this.recetaSeleccionada.getIdReceta() == 0) {
			//return;	//ES NECESARIO TENERLO COMENTADO O SINO NO AGREGA INGREDIENTES NUEVO EN LA TABLA, NO SE VE
		}
		int filasSeleccionadas = ventanaPrincipal.getTablaPasosReceta().getSelectedRow();
		if(filasSeleccionadas == -1) {
			return;
		}
		pasoDeRecetaSeleccionado = this.pasosRecetaEnLista.get(filasSeleccionadas);
		llenarTablaIngredientes(this.pasosRecetaEnLista.get(filasSeleccionadas).getPasosDTO());
	}
	
	private void llenarTablaIngredientes(PasoDTO pasosDTO) {
		for(int x = 0; x < pasosDTO.getMateriales().size(); x++) {
			Object[] agregar = {pasosDTO.getMateriales().get(x).getDescripcion(), pasosDTO.getCantidadUsada().get(x)};
			ventanaPrincipal.getModelIngredientes().addRow(agregar);
		}
	}

	private void reiniciarTablaIngredientes() {
		ventanaPrincipal.getModelIngredientes().setRowCount(0);
		ventanaPrincipal.getModelIngredientes().setColumnCount(0);
		ventanaPrincipal.getModelIngredientes().setColumnIdentifiers(ventanaPrincipal.getNombreColumnasIngredientes());
	}
	
	@SuppressWarnings("unchecked")
	private void llenarComboBoxIngredientes() {
		this.ventanaPrincipal.getComboBoxIngredientes().removeAllItems();
		List<MaestroProductoDTO> todosProductos = this.modeloMaestroProducto.readAll();
		ingredientesEnComboBox = new ArrayList<MaestroProductoDTO>();
		for(MaestroProductoDTO mp: todosProductos) {
			if(mp.getTipo().toLowerCase().equals("mp")) {
				this.ventanaPrincipal.getComboBoxIngredientes().addItem(mp.getDescripcion());
				ingredientesEnComboBox.add(mp);
			}
		}
	}
	
	private void incluirPaso(ActionEvent e) {
		int[] filasSeleccionadas = ventanaPrincipal.getTablaFabricacionesEnMarcha().getSelectedRows();
		if(filasSeleccionadas.length == 0) {
			return;
		}
		this.filasSeleccionadas = ventanaPrincipal.getTablaFabricacionesEnMarcha().getSelectedRows();
		PasoDTO pasoIncluir = new PasoDTO(this.pasosEnLista.get(filasSeleccionadas[0]).getIdPaso(),this.pasosEnLista.get(filasSeleccionadas[0]).getDescripcion());
		pasosRecetaEnLista.add(new PasoDeRecetaDTO(0,this.recetaSeleccionada.getIdReceta(),pasosRecetaEnLista.size()+1,this.pasosEnLista.get(filasSeleccionadas[0]).getIdPaso(), pasoIncluir));
		reiniciarTablaPasosReceta();
		llenarTablaPasosReceta(pasosRecetaEnLista);
	}
	
	private void darDeAltaReceta(ActionEvent e) {
		if(this.recetaSeleccionada == null) {
			return;
		}
		if(this.pasosRecetaEnLista == null) {
			return;
		}
		if(this.pasosRecetaEnLista.size() == 0){
			return;
		}
		if(this.ventanaPrincipal.getTextFieldReceta().getText().equals("")) {
			return;
		}
		if(this.ventanaPrincipal.getComboBoxReceta().getSelectedIndex() != 0 && this.recetaSeleccionada.getIdReceta() != 0) {
			return;
		}
		if(this.ventanaPrincipal.getComboBoxProductos().getSelectedIndex() == -1) {
			return;
		}
		recetaSeleccionada.setDescripcion(this.ventanaPrincipal.getTextFieldReceta().getText());
		recetaSeleccionada.setIdProducto(this.productosTerminados.get(this.ventanaPrincipal.getComboBoxProductos().getSelectedIndex()).getIdMaestroProducto());
		this.modeloReceta.insertReceta(recetaSeleccionada);
		int idReceta = this.modeloFabricacion.readAllReceta().get(this.modeloFabricacion.readAllReceta().size()-1).getIdReceta();
		for(PasoDeRecetaDTO p : pasosRecetaEnLista) {
			p.setIdReceta(idReceta);
		}
		this.modeloReceta.insertPasosReceta(pasosRecetaEnLista);
		
		this.refrescarComboBoxReceta();
		this.ventanaPrincipal.getComboBoxReceta().setSelectedIndex(this.ventanaPrincipal.getComboBoxReceta().getItemCount()-1);
	}
	
	private void agregarIngrediente(ActionEvent e) {
		MaestroProductoDTO ingredienteSeleccionado = 
				ingredientesEnComboBox.get(this.ventanaPrincipal.getComboBoxIngredientes().getSelectedIndex());
		if(pasoDeRecetaSeleccionado == null) {
			return;
		}
		if(this.recetaSeleccionada == null) {
			return;
		}
		boolean yaEstaba = false;
		for(int x = 0; x<pasoDeRecetaSeleccionado.getPasosDTO().getMateriales().size();x++) {
			MaestroProductoDTO m = pasoDeRecetaSeleccionado.getPasosDTO().getMateriales().get(x);
			yaEstaba = yaEstaba || m.getIdMaestroProducto() == ingredienteSeleccionado.getIdMaestroProducto();
			if(m.getIdMaestroProducto() == ingredienteSeleccionado.getIdMaestroProducto()) {
				//pasoDeRecetaSeleccionado.getPasosDTO().getMateriales().remove(x);
				pasoDeRecetaSeleccionado.getPasosDTO().getCantidadUsada().set(x, pasoDeRecetaSeleccionado.getPasosDTO().getCantidadUsada().get(x)+(Integer) this.ventanaPrincipal.getSpinnerCantidadIngrediente().getValue());
			}
		}
		if(!yaEstaba) {
			pasoDeRecetaSeleccionado.getPasosDTO().getMateriales().add(ingredienteSeleccionado);
			pasoDeRecetaSeleccionado.getPasosDTO().getCantidadUsada().add((Integer) this.ventanaPrincipal.getSpinnerCantidadIngrediente().getValue());
		}
		/*
		reiniciarTablaIngredientes();
		this.llenarTablaIngredientes(pasoDeRecetaSeleccionado.getPasosDTO());
		*/
		this.refrescarTablaIngredientes();
	}
	
	@SuppressWarnings("unchecked")
	private void refrescarComboBoxProductosTerminados() {
		this.ventanaPrincipal.getComboBoxProductos().removeAllItems();
		productosTerminados = new ArrayList<MaestroProductoDTO>();
		for(MaestroProductoDTO mp: this.modeloMaestroProducto.readAll()) {
			if(mp.getFabricado().equals("S") && mp.getTipo().equals("PT")) {
				productosTerminados.add(mp);
				this.ventanaPrincipal.getComboBoxProductos().addItem(mp.getDescripcion()+", "+mp.getTalle());
			}
		}
	}
	
	private void quitarPasoReceta(ActionEvent e) {
		if(this.pasosRecetaEnLista.size()==0) {
			return;
		}
		int pasoSeleccionado = this.ventanaPrincipal.getTablaPasosReceta().getSelectedRow();
		corregirNroOrden(pasosRecetaEnLista.get(pasoSeleccionado-1).getNroOrden());
		this.pasosRecetaEnLista.remove(pasoSeleccionado-1);
		this.reiniciarTablaPasosReceta();
		this.llenarTablaPasosReceta(pasosRecetaEnLista);
	}
	
	private void corregirNroOrden(int nroOrdenEliminado) {
		for(PasoDeRecetaDTO p: pasosRecetaEnLista) {
			if(p.getNroOrden() > nroOrdenEliminado) {
				p.setNroOrden(p.getNroOrden()-1);
			}
		}
	}
	
	private void quitarIngrediente(ActionEvent e) {
		int ingredienteSeleccionadoTabla = this.ventanaPrincipal.getTablaIngredientes().getSelectedRow();
		if(ingredienteSeleccionadoTabla<0) {
			return;
		}
		this.pasoDeRecetaSeleccionado.getPasosDTO().getMateriales().remove(ingredienteSeleccionadoTabla);
		this.pasoDeRecetaSeleccionado.getPasosDTO().getCantidadUsada().remove(ingredienteSeleccionadoTabla);
		this.reiniciarTablaIngredientes();
		this.llenarTablaIngredientes(this.pasoDeRecetaSeleccionado.getPasosDTO());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

	public static void main(String[] args) {
		ControladorVerPasos controladorPasos = new ControladorVerPasos();
		controladorPasos.inicializar();
		
	}

}
