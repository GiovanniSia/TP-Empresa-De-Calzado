package main;

import dto.SucursalDTO;
import presentacion.controlador.fabrica.ReControladorOperario;

public class Main {

	public static void main(String[] args) {
		/*
		Vista vista = new Vista();
		Zapateria modelo = new Zapateria(new DAOSQLFactory());
		Controlador controlador = new Controlador(vista, modelo);
		controlador.inicializar();
		*/
		//Para ejecutar la operativa del operario
		ReControladorOperario co = new ReControladorOperario(new SucursalDTO(1, "FABRICA", "JOSE HERNANDEZ","123","BSAS","TORTGUITAS","ARG","1234","PAPU"));
		co.inicializar();
		
	}
}
