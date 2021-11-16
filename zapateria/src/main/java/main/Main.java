package main;

import modelo.CambiarEstadoMorosoClienteAutomaticamente;
import modelo.EnviarCorreosAProveedoresAutomatico;
import presentacion.controlador.Login.ControladorLogin;

public class Main {

	public static void main(String[] args){
		Thread envio = new EnviarCorreosAProveedoresAutomatico();
		envio.start();
		
		Thread pasarMoroso = new CambiarEstadoMorosoClienteAutomaticamente();
		pasarMoroso.start();
		
		ControladorLogin login = new ControladorLogin();
		login.inicializar();
		login.mostrarVentana();
	}
}