package presentacion.controlador;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import dto.EmpleadoDTO;
import modelo.Empleado;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.vista.VentanaLogin;

public class ControladorLogin {
	private Controlador controlador;
	private VentanaLogin ventanaLogin;
	private Empleado empleado;
	private EmpleadoDTO empleadoInicioSesion;

	public ControladorLogin() {
		this.controlador = new Controlador();
		this.empleado = new Empleado(new DAOSQLFactory());
		this.ventanaLogin = new VentanaLogin();
	}

	public void inicializar() {

		this.ventanaLogin.getBtnIniciarSesion().addActionListener(a -> validarUsuario(a));
	}

	@SuppressWarnings({ "deprecation" })
	public void validarUsuario(ActionEvent a) {
		String correo = this.ventanaLogin.getTxtFieldCorreo().getText();
		String contra = this.ventanaLogin.getTxtFieldContra().getText();

		if(empleado.selectUser(correo, contra)==null) {
			JOptionPane.showMessageDialog(null, "Ingreso denegado, datos invalidos");
			return;
		}
		
		JOptionPane.showMessageDialog(null, "Ingreso exitoso, bienvenido "+ empleado.selectUser(correo, contra).getNombre());
		empleadoInicioSesion = empleado.selectUser(correo, contra);
		ocultarVentanaLogin();
		iniciarZapateria();
	}

	public void iniciarZapateria() {
		controlador.inicializar();
		controlador.mostrarVentanaMenu();
	}

	public void mostrarVentanaLogin() {
		this.ventanaLogin.show();
	}

	public void ocultarVentanaLogin() {
		this.ventanaLogin.cerrar();
	}

}
