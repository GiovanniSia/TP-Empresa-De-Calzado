package main;

import modelo.Zapateria;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.controlador.Controlador;
import presentacion.vista.Vista;

public class Main {

	public static void main(String[] args) {
		Vista vista = new Vista();
		Zapateria modelo = new Zapateria(new DAOSQLFactory());
		Controlador controlador = new Controlador(vista, modelo);
		controlador.inicializar();
		
		
	}
}
