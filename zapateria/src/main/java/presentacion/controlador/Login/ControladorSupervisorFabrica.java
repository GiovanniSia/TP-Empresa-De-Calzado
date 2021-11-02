package presentacion.controlador.Login;

import presentacion.vista.Login.VentanaSupervisorFabrica;

public class ControladorSupervisorFabrica {

	private VentanaSupervisorFabrica ventanaSupervisorFabrica;

	public ControladorSupervisorFabrica() {
		ventanaSupervisorFabrica = new VentanaSupervisorFabrica();
	}

	public void inicializar() {

	}

	public void mostrarVentana() {
		ventanaSupervisorFabrica.mostrarVentana();
	}

	public void cerrarVentana() {
		ventanaSupervisorFabrica.cerrarVentana();
	}
	
}
