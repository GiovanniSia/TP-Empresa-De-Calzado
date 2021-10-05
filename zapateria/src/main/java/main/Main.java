package main;

import presentacion.controlador.fabrica.ControladorOperario;

public class Main {

	public static void main(String[] args) {
		/*
		Vista vista = new Vista();
		Zapateria modelo = new Zapateria(new DAOSQLFactory());
		Controlador controlador = new Controlador(vista, modelo);
		controlador.inicializar();
		*/
		ControladorOperario co = new ControladorOperario();
		co.inicializar();
		
	}
}
