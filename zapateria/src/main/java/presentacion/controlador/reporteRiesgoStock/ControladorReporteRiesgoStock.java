package presentacion.controlador.reporteRiesgoStock;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import presentacion.vista.reporteRiesgoStock.VentanaVerReporteRiesgoStock;

public class ControladorReporteRiesgoStock implements ActionListener {
	
	VentanaVerReporteRiesgoStock ventanaPrincipal;
	
	public ControladorReporteRiesgoStock() {
		ventanaPrincipal = new VentanaVerReporteRiesgoStock();
	}
	
	public void inicializar() {
		ventanaPrincipal.mostrarVentana();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ControladorReporteRiesgoStock control = new ControladorReporteRiesgoStock();
		control.inicializar();

	}

}
