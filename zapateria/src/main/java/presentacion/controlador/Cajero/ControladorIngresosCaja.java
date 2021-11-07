package presentacion.controlador.Cajero;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import dto.CajaDTO;
import dto.IngresosDTO;
import inicioSesion.empleadoProperties;
import inicioSesion.sucursalProperties;
import modelo.Caja;
import modelo.Ingresos;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.controlador.Controlador;
import presentacion.vista.Cajero.VentanaIngresosCaja;

public class ControladorIngresosCaja {
	private int IdSucursal = 0;
	private int apertura = 0;
	private String nombre = "";
	public void obtenerDatosPropertiesSucursalEmpleado() {
		try {
			sucursalProperties sucursalProp = sucursalProperties.getInstance();
			IdSucursal = Integer.parseInt(sucursalProp.getValue("IdSucursal"));

			empleadoProperties empleadoProp = empleadoProperties.getInstance();
			apertura = Integer.parseInt(empleadoProp.getValue("IdEmpleado"));
			nombre = empleadoProp.getValue("Nombre");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private VentanaIngresosCaja ventanaIngresosCaja;
	private Caja caja;
	private CajaDTO cajaDeHoy;
	private Ingresos ingresos;

	Controlador controlador;

	public ControladorIngresosCaja() {
		obtenerDatosPropertiesSucursalEmpleado();
		this.caja = new Caja(new DAOSQLFactory());
		this.ventanaIngresosCaja = new VentanaIngresosCaja();
	}

	public ControladorIngresosCaja(Controlador controlador, Caja caja) {
		obtenerDatosPropertiesSucursalEmpleado();
		this.ventanaIngresosCaja = new VentanaIngresosCaja();
		this.caja = caja;
		this.controlador = controlador;
	}

	public void inicializar() {
		
		this.caja = new Caja(new DAOSQLFactory());
		this.ingresos = new Ingresos(new DAOSQLFactory());

		this.ventanaIngresosCaja = new VentanaIngresosCaja();

		// Botones
		this.ventanaIngresosCaja.getBtnAtras().addActionListener(a -> atras(a));

		estadoDeLaCaja();
		actualizarFechaHoy();

		if (!estaCajaAbierta()) {
			this.ventanaIngresosCaja.getBtnRealizarIngreso().addActionListener(c -> realizarPrimerIngreso(c));
			this.ventanaIngresosCaja.mostrarIngresarSaldoInicial();
		} else {
			this.ventanaIngresosCaja.getBtnRealizarIngreso().addActionListener(c -> realizarRecarga(c));
			this.ventanaIngresosCaja.mostrarIngresarRecargaSaldo();
			actualizarSaldoActual(obtenerSaldoActual());
		}

	}

	public void actualizarFechaHoy() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String fecha = dtf.format(LocalDateTime.now());
		this.ventanaIngresosCaja.getLblActualizarFechaHoy().setText(fecha);
	}

	public String obtenerSaldoActual() {
		ControladorEgresosCaja a = new ControladorEgresosCaja();
		return "" + a.obtenerValorBalance() + "";
	}

	public void actualizarSaldoActual(String Saldo) {
		this.ventanaIngresosCaja.getLblActualizarSaldoActual().setText(Saldo);
	}

	public void estadoDeLaCaja() {

		if (estaCajaAbierta()) {
			this.ventanaIngresosCaja.getLblActualizarEstadoCaja().setText("Abierta");
			this.ventanaIngresosCaja.mostrarSaldoActual();
			this.ventanaIngresosCaja.mostrarHoraHoy();
		} else {
			this.ventanaIngresosCaja.getLblActualizarEstadoCaja().setText("Cerrada");
			this.ventanaIngresosCaja.ocultarSaldoActual();
			this.ventanaIngresosCaja.ocultarHoraHoy();
		}
//		this.ventanaIngresosCaja.getLblActualizarEstadoCaja().setText("No hay caja");
	}

	public boolean estaCajaAbierta() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String fecha = dtf.format(LocalDateTime.now());

//      ArrayList<CajaDTO> cajas = (ArrayList<CajaDTO>) this.caja.readAll();

		if (caja.getCajaDeHoy("Fecha", fecha, "IdSucursal", "" + IdSucursal + "") == null) {
			return false;
		}

		this.cajaDeHoy = caja.getCajaDeHoy("Fecha", fecha, "IdSucursal", "" + IdSucursal + "");
		if (cajaDeHoy.getApertura() == 0 || cajaDeHoy.getCierre() != 0) {
			return false;
		}
		return true;
	}

	public boolean cajaYaFueCerrada() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String fecha = dtf.format(LocalDateTime.now());

//		Verificamos que exista una caja con la fecha de hoy
		ArrayList<CajaDTO> cajas = (ArrayList<CajaDTO>) this.caja.readAll();
		for (CajaDTO c : cajas) {
			if (c.getFecha().equals(fecha) && c.getCierre() != 0) {
				return true;
			}
		}
		return false;
	}

	public void realizarPrimerIngreso(ActionEvent p) {
		if (this.ventanaIngresosCaja.getTxtFieldIngresoSaldoInicial().getText().equals("")) {
			JOptionPane.showMessageDialog(null, "El campo no puede ser vacio");
			return;
		}
		if (Integer.parseInt(this.ventanaIngresosCaja.getTxtFieldIngresoSaldoInicial().getText()) == 0) {
			JOptionPane.showMessageDialog(null, "El campo no puede ser cero");
			return;
		}

		ingresarPrimerIngreso();
		this.ventanaIngresosCaja.cerrar();
		this.controlador.mostrarVentanaMenuDeSistemas();
	}

	public void realizarRecarga(ActionEvent p) {
		if (this.ventanaIngresosCaja.getTxtFieldRecargaSaldo().getText().equals("")) {
			JOptionPane.showMessageDialog(null, "El campo no puede ser vacio");
			return;
		}
		if (Integer.parseInt(this.ventanaIngresosCaja.getTxtFieldRecargaSaldo().getText()) == 0) {
			JOptionPane.showMessageDialog(null, "El campo no puede ser cero");
			return;
		}
		if (Integer.parseInt(obtenerSaldoActual()) >= 1000000000) {
			JOptionPane.showMessageDialog(null, "Recarga invalida, se supero el limite. Limite: 1.000.000.000");
			return;
		}
		ingresarRecarga();
		this.ventanaIngresosCaja.cerrar();
		this.controlador.mostrarVentanaMenuDeSistemas();
	}

	public void atras(ActionEvent a) {
		this.ventanaIngresosCaja.cerrar();
		this.controlador.mostrarVentanaMenuDeSistemas();
	}

	public void ingresarPrimerIngreso() {

//		insert into caja values(0,1,"2021-10-13","10:10:00",2,0,"Juan","Carlos","10:10:00","20:21:00");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String fecha = dtf.format(LocalDateTime.now());
		DateTimeFormatter dtfhora = DateTimeFormatter.ofPattern("hh:mm");
		String hora = dtfhora.format(LocalDateTime.now());

		CajaDTO cajaNueva = new CajaDTO(0, IdSucursal, fecha, hora, apertura, 0, nombre, "", hora, "00:00:00");

		int cantidadIngresada = Integer.parseInt(this.ventanaIngresosCaja.getTxtFieldIngresoSaldoInicial().getText());

//		insert into Ingresos values(0,1,"2021-10-12","10:21","SI",12,"A","07659485","EFE",1000,10,"02939293",10000);
		IngresosDTO primerIngreso = new IngresosDTO(0, IdSucursal, fecha, hora, "SI", 0, "", "00000000", "EFE",
				cantidadIngresada, 1, "00000000", cantidadIngresada);

		this.caja.insert(cajaNueva);
		this.ingresos.insert(primerIngreso);
		JOptionPane.showMessageDialog(null, "Primer ingreso realizado con exito, caja Abierta");
	}

	public void ingresarRecarga() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String fecha = dtf.format(LocalDateTime.now());
		DateTimeFormatter dtfhora = DateTimeFormatter.ofPattern("hh:mm");
		String hora = dtfhora.format(LocalDateTime.now());

		int cantidadIngresada = Integer.parseInt(this.ventanaIngresosCaja.getTxtFieldRecargaSaldo().getText());

		IngresosDTO primerIngreso = new IngresosDTO(0, IdSucursal, fecha, hora, "R", 0, "", "00000000", "EFE",
				cantidadIngresada, 1, "00000000", cantidadIngresada);

		this.ingresos.insert(primerIngreso);
		JOptionPane.showMessageDialog(null, "Recarga realizada con exito");

	}

	public void limpiarCampos() {
		this.ventanaIngresosCaja.limpiarCampos();
	}

	public void mostrarVentana() {
		this.ventanaIngresosCaja.show();
	}
}