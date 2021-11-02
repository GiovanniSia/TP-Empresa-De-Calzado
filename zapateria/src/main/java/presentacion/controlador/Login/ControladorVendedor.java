package presentacion.controlador.Login;

import presentacion.vista.Login.VentanaVendedor;

public class ControladorVendedor {

	private VentanaVendedor ventanaVendedor;

	public ControladorVendedor() {
		ventanaVendedor = new VentanaVendedor();
	}

	public void inicializar() {

	}

	public void mostrarVentana() {
		ventanaVendedor.mostrarVentana();
	}

	public void cerrarVentana() {
		ventanaVendedor.cerrarVentana();
	}

}
