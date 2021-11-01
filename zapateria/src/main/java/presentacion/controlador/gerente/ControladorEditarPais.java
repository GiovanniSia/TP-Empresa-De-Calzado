package presentacion.controlador.gerente;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import dto.PaisDTO;
import modelo.Pais;
import presentacion.vista.gerente.VentanaEditarPais;

public class ControladorEditarPais {

	List<PaisDTO> todosLosPaises;
	Pais pais;
	
	VentanaEditarPais ventanaEditarPais;
	
	public ControladorEditarPais(Pais pais,VentanaEditarPais ventanaEditarPais) {
		this.ventanaEditarPais = ventanaEditarPais;
		this.todosLosPaises = new ArrayList<PaisDTO>();
		this.pais = pais;
//		this.ventanaEditarPais = new VentanaEditarPais();
		
		this.ventanaEditarPais.getBtnSalirPais().addActionListener(a -> salir(a));
	}
	
	
	
	public void inicializar() {
		this.todosLosPaises = this.pais.readAll();
		
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

	public boolean ventanaYaEstaInicializada() {
		return this.ventanaEditarPais.getFrame().isShowing();
	}
	
}
