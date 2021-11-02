package presentacion.controlador.Login;

import presentacion.vista.Login.VentanaSupervisor;

public class ControladorSupervisor {

	private VentanaSupervisor ventanaSupervisor;

	public ControladorSupervisor() {
		ventanaSupervisor = new VentanaSupervisor();
	}

	public void inicializar() {

	}

	public void mostrarVentana() {
		ventanaSupervisor.mostrarVentana();
	}

	public void cerrarVentana() {
		ventanaSupervisor.cerrarVentana();
	}
	
}
