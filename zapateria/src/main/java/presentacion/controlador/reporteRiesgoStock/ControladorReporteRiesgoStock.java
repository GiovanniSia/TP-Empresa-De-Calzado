package presentacion.controlador.reporteRiesgoStock;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dto.SucursalDTO;
import presentacion.controlador.Controlador;
import presentacion.reportes.ReporteListaStockFaltante;
import presentacion.vista.reporteRiesgoStock.VentanaVerReporteRiesgoStock;

public class ControladorReporteRiesgoStock implements ActionListener {
	
	VentanaVerReporteRiesgoStock ventanaPrincipal;
	SucursalDTO sucursal;
	Controlador controlador;
	
	public ControladorReporteRiesgoStock(Controlador controlador, SucursalDTO sucursal) {
		this.sucursal = sucursal;
		this.controlador = controlador;
		ventanaPrincipal = new VentanaVerReporteRiesgoStock();
		ventanaPrincipal.getLblSucursal().setText("Sucursal "+ this.sucursal.getNombre());
		asginarFuncionesABotones();
	}
	
	public void inicializar() {
		ventanaPrincipal.mostrarVentana();
	}
	
	private void salir(ActionEvent r) {
		this.ventanaPrincipal.cerrar();
		if(this.controlador == null) {
			return;
		}
		this.controlador.mostrarVentanaMenuDeSistemas();
	}
	
	private void botonVerReporte(ActionEvent r) {
		int primerFlag = obtenerNumero(this.ventanaPrincipal.getTxtPrimerFlag());
		if(primerFlag < 0) {
			mostrarMensajeEmergente("El primer porcentaje no es valido. Ingrese un numero.");
			return;
		}
		
		int segundoFlag = obtenerNumero(this.ventanaPrincipal.getTxtSegundoFlag());
		if(segundoFlag < 0) {
			mostrarMensajeEmergente("El segundo porcentaje no es valido. Ingrese un numero.");
			return;
		}
		
		if(!sonFlagsValidos(primerFlag, segundoFlag)) {
			mostrarMensajeEmergente("Asegurese que el primer porcentaje sea menor al segundo y que no sobrepasen los 100.");
			return;
		}
		mostrarReporte(primerFlag, segundoFlag);
	}

	private void mostrarReporte(int primerFlag, int segundoFlag) {
		ReporteListaStockFaltante reporteStockFaltante = new ReporteListaStockFaltante(sucursal.getIdSucursal(), primerFlag, segundoFlag);
		reporteStockFaltante.mostrar();
	}

	private boolean sonFlagsValidos(int flagChico, int flagGrande) {
		if(!(flagChico < flagGrande)) {
			return false;
		}
		if(!(flagChico<100 && flagGrande <=100)) {
			return false;
		}
		return true;
	}
	
	private int obtenerNumero(JTextField jTxt) {
		String texto = jTxt.getText();
		if(!sePuedeConvertirEnInt(texto)) {
			return -1;
		}
		int numero = Integer.valueOf(texto);
		return numero;
	}
	
	private boolean sePuedeConvertirEnInt(String texto) {
		boolean ret = isNumeric(texto);
		return ret;
	}

	private boolean isNumeric(String cadena){
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}
	
	private void asginarFuncionesABotones() {
		this.ventanaPrincipal.getBtnVerReporte().addActionListener(r->botonVerReporte(r));
		this.ventanaPrincipal.getBtnRegresar().addActionListener(r->salir(r));
	}
	
	private void mostrarMensajeEmergente(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje);
        return;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ControladorReporteRiesgoStock control = new ControladorReporteRiesgoStock(null, new SucursalDTO(2,"","","","","","","","",""));
		control.inicializar();

	}

}
