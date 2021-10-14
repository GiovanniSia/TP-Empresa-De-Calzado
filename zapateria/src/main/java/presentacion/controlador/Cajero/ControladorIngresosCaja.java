package presentacion.controlador.Cajero;

import java.awt.event.ActionEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

import dto.CajaDTO;
import dto.IngresosDTO;
import modelo.Caja;
import modelo.Ingresos;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.controlador.Controlador;
import presentacion.vista.Cajero.VentanaIngresosCaja;

public class ControladorIngresosCaja {
	private final int IdSucursal = 1;
	private final int apertura = 1;
	private final String nombre = "Carlos";
	
	private VentanaIngresosCaja ventanaIngresosCaja;
	private Caja caja;
	private CajaDTO cajaDeHoy;
	private Ingresos ingresos;
	
	Controlador controlador;

	public ControladorIngresosCaja(Controlador controlador, Caja caja) {
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

		if (!estaCajaAbierta()) {
			this.ventanaIngresosCaja.getBtnRealizarIngreso().addActionListener(c -> realizarPrimerIngreso(c));
			this.ventanaIngresosCaja.mostrarIngresarSaldoInicial();
		} else {
			this.ventanaIngresosCaja.getBtnRealizarIngreso().addActionListener(c -> realizarRecarga(c));
			this.ventanaIngresosCaja.mostrarIngresarRecargaSaldo();
		}
	}

	public boolean estaCajaAbierta() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fecha = dtf.format(LocalDateTime.now());
        if (caja.getCajaDeHoy("Fecha", fecha , "IdSucursal", ""+IdSucursal+"") == null) {
            return false;
        }
        this.cajaDeHoy = caja.getCajaDeHoy("Fecha", fecha , "IdSucursal", ""+IdSucursal+"");
        if (cajaDeHoy.getApertura() == 0 || cajaDeHoy.getCierre()!=0) {
            return false;
        }
        return true;
    }

	public void realizarPrimerIngreso(ActionEvent p) {
		ingresarPrimerIngreso();
		this.ventanaIngresosCaja.cerrar();
		this.controlador.inicializar();
		this.controlador.mostrarVentanaMenuDeSistemas();
	}

	public void realizarRecarga(ActionEvent p) {
		ingresarRecarga();
		this.ventanaIngresosCaja.cerrar();
		this.controlador.inicializar();
		this.controlador.mostrarVentanaMenuDeSistemas();
	}

	public void atras(ActionEvent a) {
		this.ventanaIngresosCaja.cerrar();
		this.controlador.inicializar();
		this.controlador.mostrarVentanaMenuDeSistemas();
	}

	public void ingresarPrimerIngreso() {
//		insert into caja values(0,1,"2021-10-13","10:10:00",2,0,"Juan","Carlos","10:10:00","20:21:00");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String fecha = dtf.format(LocalDateTime.now());
		DateTimeFormatter dtfhora = DateTimeFormatter.ofPattern("hh:mm");
		String hora = dtfhora.format(LocalDateTime.now());
		
		CajaDTO cajaNueva = new CajaDTO(0,IdSucursal,fecha,hora,apertura,0,nombre,"",hora,"00:00:00");
		
		int cantidadIngresada = Integer.parseInt(this.ventanaIngresosCaja.getTxtFieldIngresoSaldoInicial().getText());
		
//		insert into Ingresos values(0,1,"2021-10-12","10:21","SI",12,"A","07659485","EFE",1000,10,"02939293",10000);
		IngresosDTO primerIngreso = new IngresosDTO(0,IdSucursal,fecha,hora,"SI",0,"","00000000","EFE",cantidadIngresada,1,"00000000",cantidadIngresada);
		
		this.caja.insert(cajaNueva);
		this.ingresos.insert(primerIngreso);
		JOptionPane.showMessageDialog(null, "Primer ingreso realizado con exito, caja Abierta");
	}

	public void ingresarRecarga() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String fecha = dtf.format(LocalDateTime.now());
		DateTimeFormatter dtfhora = DateTimeFormatter.ofPattern("hh:mm");
		String hora = dtfhora.format(LocalDateTime.now());
		
		int cantidadIngresada = Integer.parseInt(this.ventanaIngresosCaja.getTxtFieldRegarcaSaldo().getText());
		
		IngresosDTO primerIngreso = new IngresosDTO(0,IdSucursal,fecha,hora,"R",0,"","00000000","EFE",cantidadIngresada,1,"00000000",cantidadIngresada);
		
		this.ingresos.insert(primerIngreso);
		JOptionPane.showMessageDialog(null, "Recarga realizada con exito");
		
	}

	public void limpiarCampos() {
		this.ventanaIngresosCaja.limpiarCampos();
	}

	public void mostrarVentana() {
		this.ventanaIngresosCaja.show();
	}

	public static void main(String[] args) {
//		Caja modelo = new Caja(new DAOSQLFactory());
//		ControladorIngresosCaja controlador = new ControladorIngresosCaja(modelo);
//		controlador.inicializar();
//		controlador.mostrarVentana();
	}
}