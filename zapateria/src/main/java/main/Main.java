package main;

import modelo.EnviarCorreosAProveedoresAutomatico;
import presentacion.controlador.Controlador;

public class Main {

	public static void main(String[] args){
		Controlador controlador = new Controlador();
		Thread envio = new EnviarCorreosAProveedoresAutomatico();
		envio.start();
		controlador.inicializar();
		controlador.mostrarVentanaMenu();
	}
}
