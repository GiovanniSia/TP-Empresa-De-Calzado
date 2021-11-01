package main;

import modelo.EnviarCorreosAProveedoresAutomatico;
import presentacion.controlador.ControladorLogin;

public class Main {

	public static void main(String[] args){
		Thread envio = new EnviarCorreosAProveedoresAutomatico();
		envio.start();

//		Controlador controlador = new Controlador();
//		controlador.inicializar();
//		controlador.mostrarVentanaMenu();
		
		ControladorLogin login = new ControladorLogin();
		login.inicializar();
		login.mostrarVentanaLogin();
	}
	
}
