package main;

import dto.SucursalDTO;
import presentacion.controlador.Controlador;
import presentacion.controlador.fabrica.ReControladorOperario;
import presentacion.reportes.ReporteCajaDiaria;
import presentacion.reportes.ReporteFactura;

public class Main {

	public static void main(String[] args) {
		Controlador controlador = new Controlador();
		controlador.inicializar();
		controlador.mostrarVentanaMenu();
		
	}
}
