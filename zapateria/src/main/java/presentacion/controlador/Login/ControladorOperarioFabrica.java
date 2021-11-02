package presentacion.controlador.Login;

import presentacion.vista.Login.VentanaOperarioFabrica;

public class ControladorOperarioFabrica {

	private VentanaOperarioFabrica ventanaOperarioFabrica;

	public ControladorOperarioFabrica() {
		ventanaOperarioFabrica = new VentanaOperarioFabrica();
	}

	public void inicializar() {

	}

	public void mostrarVentana() {
		ventanaOperarioFabrica.mostrarVentana();
	}

	public void cerrarVentana() {
		ventanaOperarioFabrica.cerrarVentana();
	}
	
}
