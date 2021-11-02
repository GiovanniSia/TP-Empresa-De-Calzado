package presentacion.controlador.fabrica.receta;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import dto.PasoDTO;
import dto.PasoDeRecetaDTO;
import dto.RecetaDTO;
import modelo.Fabricacion;
import modelo.ModeloPaso;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.vista.fabrica.receta.VerPasos;

public class ControladorVerPasos implements ActionListener {
	
	VerPasos ventanaPrincipal;
	ModeloPaso modeloPaso;
	Fabricacion modeloFabricacion;
	List<PasoDTO> pasosEnLista;
	int[]filasSeleccionadas;
	
	public ControladorVerPasos() {
		ventanaPrincipal = new VerPasos();
		modeloPaso = new ModeloPaso(new DAOSQLFactory());
		modeloFabricacion = new Fabricacion(new DAOSQLFactory());
		pasosEnLista = new ArrayList<PasoDTO>();
		
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
	}

	public void inicializar() {
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

	@Override
	public void actionPerformed(ActionEvent e) {
	}

	public static void main(String[] args) {
		ControladorVerPasos controladorPasos = new ControladorVerPasos();
		controladorPasos.inicializar();

	}

}
