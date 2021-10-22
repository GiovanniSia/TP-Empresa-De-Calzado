package main;

import presentacion.controlador.Controlador;

public class Main {

	public static void main(String[] args){
		Controlador controlador = new Controlador();
		controlador.inicializar();
		controlador.mostrarVentanaMenu();		
	}
}
