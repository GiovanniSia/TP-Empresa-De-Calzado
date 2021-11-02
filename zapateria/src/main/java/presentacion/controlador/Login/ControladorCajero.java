package presentacion.controlador.Login;

import presentacion.vista.Login.VentanaCajero;

public class ControladorCajero {

	private VentanaCajero ventanaCajero;

	public ControladorCajero() {
		ventanaCajero = new VentanaCajero();
	}

	public void inicializar() {

	}

	public void mostrarVentana() {
		ventanaCajero.mostrarVentana();
	}

	public void cerrarVentana() {
		ventanaCajero.cerrarVentana();
	}

}
