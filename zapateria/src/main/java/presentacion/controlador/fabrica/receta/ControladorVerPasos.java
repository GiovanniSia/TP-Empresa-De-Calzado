package presentacion.controlador.fabrica.receta;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dto.FabricacionesDTO;
import dto.MaestroProductoDTO;
import dto.PasoDTO;
import dto.PasoDeRecetaDTO;
import dto.RecetaDTO;
import modelo.Fabricacion;
import modelo.MaestroProducto;
import modelo.ModeloPaso;
import modelo.Receta;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.controlador.Controlador;
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
	RecetaDTO recetaSeleccionada = new RecetaDTO(0,0, "","Activo");
	List<PasoDeRecetaDTO> pasosRecetaEnLista;
	PasoDeRecetaDTO pasoDeRecetaSeleccionado;
	List<MaestroProductoDTO> ingredientesEnComboBox;
	List<MaestroProductoDTO> productosTerminados;
	
	private Controlador controlador;
	
	public ControladorVerPasos() {
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch(Exception e) {
            System.out.println("Error setting native LAF: " + e);
		}
		
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
//		ventanaPrincipal.getTextId().addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyReleased(KeyEvent e) {
//				refrescarTabla();
//			}
//		});
		ventanaPrincipal.getBtnAgregar().addActionListener(r-> agregarPaso(r));
//		ventanaPrincipal.getBtnEliminar().addActionListener(r-> botonEliminarPasos(r));
		
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
		this.ventanaPrincipal.getBtnSubirPaso().addActionListener(r->subirPaso(r));
		this.ventanaPrincipal.getBtnBajarPaso().addActionListener(r->bajarPaso(r));
		this.ventanaPrincipal.getBtnSalir().addActionListener(r->salir(r));
		
		this.ventanaPrincipal.getBtnActualizarReceta().addActionListener(r->actualizarReceta(r));
		
		this.ventanaPrincipal.getComboBoxIngredientes().addActionListener(r->seleccionIngrediente(r));
		
		this.ventanaPrincipal.getBtnInactivarPaso().addActionListener(r->inactivarPaso(r));
		
		this.ventanaPrincipal.getBtnInactivarReceta().addActionListener(r->inactivarReceta(r));
	}

	private void seleccionIngrediente(ActionEvent r) {
		int ingredienteActual = this.ventanaPrincipal.getComboBoxIngredientes().getSelectedIndex();
		if(ingredienteActual<0 || ingredientesEnComboBox.size()==0) {
			this.ventanaPrincipal.getLblUnidadMedida().setText("");
			return;
		}
		this.ventanaPrincipal.getLblUnidadMedida().setText(this.ingredientesEnComboBox.get(ingredienteActual).getUnidadMedida());
	}

	public void inicializar() {
		refrescarComboBoxReceta();
		llenarTablaPasosReceta();
		refrescarTablaIngredientes();
		llenarComboBoxIngredientes();
		refrescarComboBoxProductosTerminados();
		
		refrescarTabla();
		this.ventanaPrincipal.getComboBoxIngredientes().addActionListener(r->seleccionIngrediente(r));
		
		this.ventanaPrincipal.getLblReceta().setText("");
		ventanaPrincipal.mostrarVentana();
	}
	
	private void refrescarTabla() {
		reiniciarTablaTrabajos();
		pasosEnLista = recuperarListaPasos();
		llenarTablaConPasos(pasosEnLista);
	}
	
	private void llenarTablaConPasos(List<PasoDTO> pasosEnLista) {
		for(PasoDTO p : pasosEnLista) {
			//Object[] agregar = {p.getIdPaso(), p.getDescripcion()};
			Object[] agregar = {p.getDescripcion(), p.getEstado()};
			ventanaPrincipal.getModelPasos().addRow(agregar);
		}
	}

	private List<PasoDTO> recuperarListaPasos() {
		List<PasoDTO> ret = new ArrayList<PasoDTO>();
		List<PasoDTO> todosLosPasos = this.modeloPaso.readAll();
//		String idBuscar = this.ventanaPrincipal.getTextId().getText()+"";
		String descripBuscar = this.ventanaPrincipal.getTextDescripcion().getText();
		for(PasoDTO p: todosLosPasos) {
			boolean deboAgregar = true;
//			if(!idBuscar.equals("")) {
//				deboAgregar = deboAgregar && (p.getIdPaso()+"").matches(".*"+idBuscar.toLowerCase()+".*");
//			}
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
			mostrarMensajeEmergente("No escribio un nombre de paso valido.");
			return;
		}
		if(pasoAgregar.equals("")) {
			mostrarMensajeEmergente("No escribio un nombre de paso valido.");
			return;
		}
		if(pasoAgregar.length()>20) {
			mostrarMensajeEmergente("El nombre del paso sobrepasa los 20 caracteres.");
			return;
		}
		if(!esNombreDePasoValido(pasoAgregar)) {
			mostrarMensajeEmergente("El nombre del paso es repetido, ya esta en uso en otro paso.");
			return;
		}
		this.modeloPaso.insert(new PasoDTO(0,pasoAgregar,"Activo"));
		refrescarTabla();
	}
	
	private boolean esNombreDePasoValido(String nombre) {
		boolean ret = true;
		List<PasoDTO> todosLosPasos = this.modeloPaso.readAll();
		for(PasoDTO p: todosLosPasos) {
			ret = ret && !p.getDescripcion().toLowerCase().equals(nombre.toLowerCase());
		}
		return ret;
	}
	
	private void botonEliminarPasos(ActionEvent r) {
		int[] filasSeleccionadas = ventanaPrincipal.getTablaFabricacionesEnMarcha().getSelectedRows();
		if(filasSeleccionadas.length == 0) {
			mostrarMensajeEmergente("No se ha seleccionado ningun paso para eliminar.");
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
			}else {
				this.mostrarMensajeEmergente("No se puede eliminar "+this.pasosEnLista.get(idTabla).getDescripcion()+" porque esta en uso de una receta.");
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
	
	private void inactivarPaso(ActionEvent r) {
		int[] filasSeleccionadas = ventanaPrincipal.getTablaFabricacionesEnMarcha().getSelectedRows();
		if(filasSeleccionadas.length == 0) {
			mostrarMensajeEmergente("No se ha seleccionado ningun paso para eliminar.");
			return;
		}
		if(estaEstePasoEnUso(this.pasosEnLista.get(filasSeleccionadas[0]))) {
			mostrarMensajeEmergente("Este paso esta en uso en una receta.");
			return;
		}
		if(this.pasosEnLista.get(filasSeleccionadas[0]).getEstado().toLowerCase().equals("activo")) {
			this.pasosEnLista.get(filasSeleccionadas[0]).setEstado("Inactivo");
		}else {
			this.pasosEnLista.get(filasSeleccionadas[0]).setEstado("Activo");
		}
		this.modeloPaso.update(this.pasosEnLista.get(filasSeleccionadas[0]));
		refrescarTabla();
		refrescarTablaPasosReceta();
	}
	
	private boolean estaEstePasoEnUso(PasoDTO pasoDTO) {
		boolean ret = false;
		List<FabricacionesDTO> fabricacionesEnMarcha = this.modeloFabricacion.readAllFabricacionesEnMarcha("", "", "", "", "");
		for(FabricacionesDTO f: fabricacionesEnMarcha) {
			List<PasoDeRecetaDTO> pasos = modeloFabricacion.readAllPasosFromOneReceta(f.getIdReceta());
			ret = ret || estaPasoEnLista(pasoDTO, pasos);
		}
		return ret;
	}
	
	private boolean estaPasoEnLista(PasoDTO pasoDTO, List<PasoDeRecetaDTO> pasos) {
		boolean ret = false;
		for(PasoDeRecetaDTO p: pasos) {
			ret = ret || p.getIdPaso() == pasoDTO.getIdPaso();
		}
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
			this.ventanaPrincipal.getComboBoxReceta().addItem(r.getDescripcion() + " ("+r.getEstado()+")");
		}
	}
	
	private void seleccionReceta(ActionEvent r) {
		refrescarTablaPasosReceta();
		actualizarLblEstadoReceta(this.recetaSeleccionada.getEstado());
	}

	private void refrescarTablaPasosReceta() {
		reiniciarTablaPasosReceta();
		llenarTablaPasosReceta();
	}
	
	private void llenarTablaPasosReceta() {
		reiniciarTablaPasosReceta();
		this.ventanaPrincipal.getLblReceta().setText("");
		if(this.ventanaPrincipal.getComboBoxReceta().getSelectedIndex() == -1) {
			recetaSeleccionada = new RecetaDTO(0,0, "","Activo");
			return;
		}
		if(this.ventanaPrincipal.getComboBoxReceta().getSelectedIndex() == 0) {
			recetaSeleccionada = new RecetaDTO(0,0, "","Activo");
		}else {
			recetaSeleccionada = this.recetasEnComboBox.get(this.ventanaPrincipal.getComboBoxReceta().getSelectedIndex()-1);
		}
		pasosRecetaEnLista = this.modeloFabricacion.readAllPasosFromOneReceta(recetaSeleccionada.getIdReceta());
		llenarTablaPasosReceta(pasosRecetaEnLista);
		this.ventanaPrincipal.getTextFieldReceta().setText(recetaSeleccionada.getDescripcion());
		this.ventanaPrincipal.getComboBoxProductos().setSelectedIndex(recetaSeleccionada.getIdProducto()-1);
		
		if(this.estaEnUsoLaReceta(recetaSeleccionada)) {
			this.ventanaPrincipal.getLblReceta().setText("Esta receta esta en uso.");
		}else {
			this.ventanaPrincipal.getLblReceta().setText("Esta receta no esta en uso.");
		}
		if(this.recetaSeleccionada.getIdReceta()==0) {
			this.ventanaPrincipal.getLblReceta().setText("");
		}
		
	}
	
	private void llenarTablaPasosReceta(List<PasoDeRecetaDTO> pasos) {
		for(PasoDeRecetaDTO p: pasos) {
			//Object[] agregar = {p.getPasosDTO().getIdPaso(), p.getPasosDTO().getDescripcion(), p.getNroOrden()};
			Object[] agregar = { p.getPasosDTO().getDescripcion(), p.getNroOrden(), p.getPasosDTO().getEstado()};
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
		this.ventanaPrincipal.getComboBoxIngredientes().setSelectedIndex(0);
	}
	
	private void incluirPaso(ActionEvent e) {
		int[] filasSeleccionadas = ventanaPrincipal.getTablaFabricacionesEnMarcha().getSelectedRows();
		if(filasSeleccionadas.length == 0) {
			this.mostrarMensajeEmergente("No se puede agregar el paso a la receta porque no se a seleccionado un paso.");
			return;
		}
		this.filasSeleccionadas = ventanaPrincipal.getTablaFabricacionesEnMarcha().getSelectedRows();
		PasoDTO pasoIncluir = new PasoDTO(this.pasosEnLista.get(filasSeleccionadas[0]).getIdPaso(),this.pasosEnLista.get(filasSeleccionadas[0]).getDescripcion(),this.pasosEnLista.get(filasSeleccionadas[0]).getEstado());
		pasosRecetaEnLista.add(new PasoDeRecetaDTO(0,this.recetaSeleccionada.getIdReceta(),pasosRecetaEnLista.size()+1,this.pasosEnLista.get(filasSeleccionadas[0]).getIdPaso(), pasoIncluir));
		reiniciarTablaPasosReceta();
		llenarTablaPasosReceta(pasosRecetaEnLista);
	}
	
	private void darDeAltaReceta(ActionEvent e) {
		if(this.recetaSeleccionada == null) {
			this.mostrarMensajeEmergente("La receta no es valida.");
			return;
		}
		if(this.pasosRecetaEnLista == null) {
			this.mostrarMensajeEmergente("Los pasos de la receta no son valida.");
			return;
		}
		if(this.pasosRecetaEnLista.size() == 0){
			this.mostrarMensajeEmergente("La receta no tiene pasos.");
			return;
		}
		if(this.ventanaPrincipal.getTextFieldReceta().getText().equals("")) {
			this.mostrarMensajeEmergente("El nombre de la receta no es valida.");
			return;
		}
		if(this.ventanaPrincipal.getTextFieldReceta().getText().length()>20) {
			this.mostrarMensajeEmergente("El nombre de la receta supera los 20 letras.");
			return;
		}
		if(this.ventanaPrincipal.getComboBoxReceta().getSelectedIndex() != 0 && this.recetaSeleccionada.getIdReceta() != 0) {
			this.mostrarMensajeEmergente("La receta no es nueva, fue modificada.");
			return;
		}
		if(this.ventanaPrincipal.getComboBoxProductos().getSelectedIndex() == -1) {
			this.mostrarMensajeEmergente("La receta debe crear un producto, no se a seleccionado que producto fabrica.");
			return;
		}
		if(!esNombreDeRecetaValido(this.ventanaPrincipal.getTextFieldReceta().getText())) {
			mostrarMensajeEmergente("El nombre de la receta es repetido, ya esta en uso en otra receta.");
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
	
	private boolean esNombreDeRecetaValido(String nombre) {
		boolean ret = true;
		List<RecetaDTO> recetas = this.modeloFabricacion.readAllReceta();
		for(RecetaDTO r: recetas) {
			ret = ret && !r.getDescripcion().toLowerCase().equals(nombre.toLowerCase());
		}
		return ret;
	}
	
	private void agregarIngrediente(ActionEvent e) {
		MaestroProductoDTO ingredienteSeleccionado = 
				ingredientesEnComboBox.get(this.ventanaPrincipal.getComboBoxIngredientes().getSelectedIndex());
		if(pasoDeRecetaSeleccionado == null) {
			this.mostrarMensajeEmergente("No se a seleccionado un paso al que incluir el material.");
			return;
		}
		if(this.recetaSeleccionada == null) {
			this.mostrarMensajeEmergente("No hay receta valida.");
			return;
		}
		boolean yaEstaba = false;
		//Double cantidadUsar = (Double) this.ventanaPrincipal.getSpinnerCantidadIngrediente().getValue();
		Double cantidadUsar = Double.valueOf(this.ventanaPrincipal.getTextFieldCantidadIngrediente().getText());
		for(int x = 0; x<pasoDeRecetaSeleccionado.getPasosDTO().getMateriales().size();x++) {
			MaestroProductoDTO m = pasoDeRecetaSeleccionado.getPasosDTO().getMateriales().get(x);
			yaEstaba = yaEstaba || m.getIdMaestroProducto() == ingredienteSeleccionado.getIdMaestroProducto();
			if(m.getIdMaestroProducto() == ingredienteSeleccionado.getIdMaestroProducto()) {
				//pasoDeRecetaSeleccionado.getPasosDTO().getMateriales().remove(x);
				pasoDeRecetaSeleccionado.getPasosDTO().getCantidadUsada().set(x, pasoDeRecetaSeleccionado.getPasosDTO().getCantidadUsada().get(x)+cantidadUsar);
			}
		}
		if(!yaEstaba) {
			pasoDeRecetaSeleccionado.getPasosDTO().getMateriales().add(ingredienteSeleccionado);
			pasoDeRecetaSeleccionado.getPasosDTO().getCantidadUsada().add(cantidadUsar);
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
			this.mostrarMensajeEmergente("No hay pasos para quitar.");
			return;
		}
		int pasoSeleccionado = this.ventanaPrincipal.getTablaPasosReceta().getSelectedRow();
		if(pasoSeleccionado < 0) {
			this.mostrarMensajeEmergente("No se a seleccionado ningun paso.");
			return;
		}
		corregirNroOrden(pasosRecetaEnLista.get(pasoSeleccionado).getNroOrden());
		this.pasosRecetaEnLista.remove(pasoSeleccionado);
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
			this.mostrarMensajeEmergente("No se a seleccionado ningun material para quitar.");
			return;
		}
		this.pasoDeRecetaSeleccionado.getPasosDTO().getMateriales().remove(ingredienteSeleccionadoTabla);
		this.pasoDeRecetaSeleccionado.getPasosDTO().getCantidadUsada().remove(ingredienteSeleccionadoTabla);
		this.reiniciarTablaIngredientes();
		this.llenarTablaIngredientes(this.pasoDeRecetaSeleccionado.getPasosDTO());
	}
	
	private void subirPaso(ActionEvent e) {
		int pasoSeleccionado = this.ventanaPrincipal.getTablaPasosReceta().getSelectedRow();
		if(pasoSeleccionado < 0){
			return;
		}
		if(pasoSeleccionado == 0) {
			return;
		}
		subirPaso(pasoSeleccionado);
	}
	
	private void bajarPaso(ActionEvent e) {
		int pasoSeleccionado = this.ventanaPrincipal.getTablaPasosReceta().getSelectedRow();
		if(pasoSeleccionado < 0){
			return;
		}
		System.out.println(pasoSeleccionado);
		System.out.println(this.pasosRecetaEnLista.size());
		if(pasoSeleccionado+1 == this.pasosRecetaEnLista.size()) {
			return;
		}
		subirPaso(pasoSeleccionado+1);
	}
	
	private void subirPaso(int pasoSeleccionado) {
		
		List<PasoDeRecetaDTO> listaActualizada = new ArrayList<PasoDeRecetaDTO>();
		for(int x = 0; x<pasosRecetaEnLista.size(); x++) {
			if(pasoSeleccionado-1 == x) {
				pasosRecetaEnLista.get(x).setNroOrden(x+1+1);
				pasosRecetaEnLista.get(x+1).setNroOrden(x+1);
				listaActualizada.add(pasosRecetaEnLista.get(x+1));
				listaActualizada.add(pasosRecetaEnLista.get(x));
				x++;
			}else {
				listaActualizada.add(pasosRecetaEnLista.get(x));
			}
		}
		pasosRecetaEnLista = listaActualizada;
		this.reiniciarTablaPasosReceta();
		this.llenarTablaPasosReceta(pasosRecetaEnLista);
	}
	
	private void actualizarReceta(ActionEvent r) {
		if(this.ventanaPrincipal.getComboBoxReceta().getSelectedIndex() <= 0) {
			this.mostrarMensajeEmergente("No se a agregado la receta, es nueva, no se puede modificar.");
			return;
		}
		if(this.ventanaPrincipal.getTextFieldReceta().getText().equals("")) {
			this.mostrarMensajeEmergente("El nombre de la receta no es valido.");
			return;
		}
		if(this.pasosRecetaEnLista.size() == 0) {
			this.mostrarMensajeEmergente("La receta no tiene pasos.");
			return;
		}
		if(this.ventanaPrincipal.getTextFieldReceta().getText().equals("")) {
			this.mostrarMensajeEmergente("El nombre de la receta no es valido.");
			return;
		}
		if(this.ventanaPrincipal.getTextFieldReceta().getText().length()>20) {
			this.mostrarMensajeEmergente("El nombre de la receta sobrepasa las 20 letas.");
			return;
		}
		if(this.ventanaPrincipal.getComboBoxProductos().getSelectedIndex()<0) {
			this.mostrarMensajeEmergente("El producto a fabricar no es valido.");
			return;
		}
		if(estaEnUsoLaReceta(this.recetaSeleccionada)) {
			this.mostrarMensajeEmergente("Esta receta esta actualmente esta en uso, no se puede modificar.");
			return;
		}
		if(!nuevoNombreRecetaEsValido(this.ventanaPrincipal.getTextFieldReceta().getText())) {
			mostrarMensajeEmergente("El nombre de la receta es repetido, ya esta en uso en otra receta.");
			return;
		}
		recetaSeleccionada.setDescripcion(this.ventanaPrincipal.getTextFieldReceta().getText());
		this.modeloReceta.updateReceta(recetaSeleccionada, pasosRecetaEnLista);
		int indiceSeleccionado = this.ventanaPrincipal.getComboBoxReceta().getSelectedIndex();
		this.refrescarComboBoxReceta();
		this.ventanaPrincipal.getComboBoxReceta().setSelectedIndex(indiceSeleccionado);
	}

	private boolean nuevoNombreRecetaEsValido(String text) {
		if(this.recetaSeleccionada.getDescripcion().toLowerCase().equals(text.toLowerCase())) {
			return true;
		}
		return esNombreDeRecetaValido(text);
	}

	private boolean estaEnUsoLaReceta(RecetaDTO recetaAVerificar) {
		boolean ret = true;
		List<FabricacionesDTO> fabricacionesEnMarcha = this.modeloFabricacion.readAllFabricacionesEnMarcha("", "", "", "", "");
		for(FabricacionesDTO f: fabricacionesEnMarcha) {
			ret = ret && f.getIdReceta() != recetaAVerificar.getIdReceta();
		}
		return !ret;
	}
	
	private void inactivarReceta(ActionEvent r) {
		cambiarEstadoReceta();
	}

	private void cambiarEstadoReceta() {
		if(this.recetaSeleccionada.getEstado().toLowerCase().equals("activo")) {
			this.recetaSeleccionada.setEstado("Inactivo");
		}else {
			this.recetaSeleccionada.setEstado("Activo");
		}
		actualizarLblEstadoReceta(this.recetaSeleccionada.getEstado());
	}


	private void actualizarLblEstadoReceta(String estado) {
		this.ventanaPrincipal.getLblEstadoReceta().setText(estado);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}
	
	private void mostrarMensajeEmergente(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje);
        return;
	}
	
	public void salir(ActionEvent r) {
		this.ventanaPrincipal.cerrar();
		this.controlador.mostrarVentanaMenuDeSistemas();
	}
	
	

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public static void main(String[] args) {
		ControladorVerPasos controladorPasos = new ControladorVerPasos();
		controladorPasos.inicializar();
		
	}

}
