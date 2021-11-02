package presentacion.controlador.Login;

import presentacion.vista.Login.VentanaGerente;

public class ControladorGerente {

	private VentanaGerente ventanaGerente;

	public ControladorGerente() {
		ventanaGerente = new VentanaGerente();
	}

	public void inicializar() {

	}

	public void mostrarVentana() {
		ventanaGerente.mostrarVentana();
	}

	public void cerrarVentana() {
		ventanaGerente.cerrarVentana();
	}

}
