package main;

import dto.SucursalDTO;
import presentacion.controlador.fabrica.ReControladorOperario;
import presentacion.reportes.ReporteCajaDiaria;
import presentacion.reportes.ReporteFactura;

public class Main {

	public static void main(String[] args) {
		/*
		Vista vista = new Vista();
		Zapateria modelo = new Zapateria(new DAOSQLFactory());
		Controlador controlador = new Controlador(vista, modelo);
		controlador.inicializar();
		*/
		//Para ejecutar la operativa del operario
		
		/*
		ReControladorOperario co = new ReControladorOperario(new SucursalDTO(1, "FABRICA", "JOSE HERNANDEZ","123","BSAS","TORTGUITAS","ARG","1234","PAPU"));
		co.inicializar();
		*/
		
		ReporteFactura reporte = new ReporteFactura("A000011", 1);
		reporte.mostrar();
		
		/*
		ReporteCajaDiaria ca = new ReporteCajaDiaria(1);
		ca.mostrarUltimoReporte();
		*/
	}
}
