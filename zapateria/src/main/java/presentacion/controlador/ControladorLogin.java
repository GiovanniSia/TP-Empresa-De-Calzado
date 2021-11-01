package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JOptionPane;

import dto.EmpleadoDTO;
import dto.SucursalDTO;
import modelo.Empleado;
import modelo.Sucursal;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.vista.VentanaLogin;

public class ControladorLogin {
	private Controlador controlador;
	private VentanaLogin ventanaLogin;
	private Empleado empleado;

	private EmpleadoDTO empleadoInicioSesion;
	private List<SucursalDTO> sucursales;
	private Sucursal sucursal;

	private SucursalDTO sucursalSeleccionada;

	public ControladorLogin() {
		this.controlador = new Controlador();
		this.empleado = new Empleado(new DAOSQLFactory());
		this.sucursal = new Sucursal(new DAOSQLFactory());

		this.ventanaLogin = new VentanaLogin();
		this.sucursales = sucursal.readAll();

	}

	public void inicializar() {
		this.ventanaLogin.getBtnIniciarSesion().addActionListener(a -> validarUsuario(a));
		rellenarCombobox();
	}

	public void rellenarCombobox() {
		for (SucursalDTO s : sucursales) {
			ventanaLogin.getCbSucursales().addItem(s.getNombre());
		}
	}

	@SuppressWarnings({ "deprecation" })
	public void validarUsuario(ActionEvent a) {
		String correo = this.ventanaLogin.getTxtFieldCorreo().getText();
		String contra = this.ventanaLogin.getTxtFieldContra().getText();

		if (empleado.selectUser(correo, contra) == null) {
			JOptionPane.showMessageDialog(null, "Ingreso denegado, datos invalidos");
			return;
		}

		JOptionPane.showMessageDialog(null,
				"Ingreso exitoso, bienvenido " + empleado.selectUser(correo, contra).getNombre());

		sucursalSeleccionada = obtenerSucursalSleccionada();
		empleadoInicioSesion = empleado.selectUser(correo, contra);

		System.out.println("Usuario Ingresado: " + empleado.selectUser(correo, contra).getCorreoElectronico() + " Sucursal: " + sucursalSeleccionada.getNombre());

		ocultarVentanaLogin();
		iniciarZapateria();
	}

	public SucursalDTO obtenerSucursalSleccionada() {
		 String nombreSucursalSeleccionado = this.ventanaLogin.getCbSucursales().getSelectedItem().toString();
		 SucursalDTO sucursalSeleccionada=null;	
		 for (SucursalDTO s : sucursales) {
				if(s.getNombre().equals(nombreSucursalSeleccionado)) {
					sucursalSeleccionada = s;
				}
			}
		return sucursalSeleccionada;
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
