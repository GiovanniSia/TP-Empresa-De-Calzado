package presentacion.controlador.Login;

import java.awt.event.ActionEvent;

import presentacion.controlador.Cajero.ControladorCierreCaja;
import presentacion.controlador.Cajero.ControladorEgresosCaja;
import presentacion.controlador.Cajero.ControladorIngresosCaja;
import presentacion.controlador.Cajero.ControladorVisualizarCarritos;
import presentacion.vista.Login.VentanaCajero;

public class ControladorCajero {

	private VentanaCajero ventanaCajero;
	private ControladorIngresosCaja controladorIngresosCaja;
	private ControladorEgresosCaja controladorEgresosCaja;
	private ControladorCierreCaja controladorCierreCaja;
	private ControladorVisualizarCarritos controladorVisualizarCarritos;
	
	public ControladorCajero() {
		this.ventanaCajero = new VentanaCajero();
		inicializar();
	}

	public void inicializar() {
		this.ventanaCajero.getBtnIngresoDeCaja().addActionListener(a -> ingresoDeCaja(a));
		this.ventanaCajero.getBtnEgresoDeCaja().addActionListener(a -> egresoDeCaja(a));
		this.ventanaCajero.getBtnCierreDeCaja().addActionListener(a -> cierreDeCaja(a));
		this.ventanaCajero.getBtnCobrarVenta().addActionListener(a -> cobrarVenta(a));
	}
	
	private void ingresoDeCaja(ActionEvent a) {
		controladorIngresosCaja = new ControladorIngresosCaja();
		controladorIngresosCaja.inicializar();
		controladorCierreCaja.mostrarVentana();
	}
	
	private void egresoDeCaja(ActionEvent a) {
		
	}
	
	private void cierreDeCaja(ActionEvent a) {
		
	}
	
	private void cobrarVenta(ActionEvent a) {
		
	}

	public void mostrarVentana() {
		this.ventanaCajero.mostrarVentana();
	}

	public void cerrarVentana() {
		this.ventanaCajero.cerrarVentana();
	}

}
