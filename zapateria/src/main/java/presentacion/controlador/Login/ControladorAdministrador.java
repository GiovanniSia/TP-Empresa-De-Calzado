package presentacion.controlador.Login;

import presentacion.vista.Login.VentanaAdministrador;

public class ControladorAdministrador {
	
	private VentanaAdministrador ventanaAdministrador;
	
	public ControladorAdministrador() {
		ventanaAdministrador = new VentanaAdministrador();
	}

	public void inicializar() {
		
	}
	
	public void mostrarVentana() {
		ventanaAdministrador.mostrarVentana();
	}
	
	public void cerrarVentana() {
		ventanaAdministrador.cerrarVentana();
	}

}
