package presentacion.controlador.Login;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JOptionPane;

import dto.EmpleadoDTO;
import dto.SucursalDTO;
import inicioSesion.empleadoProperties;
import inicioSesion.sucursalProperties;
import modelo.Empleado;
import modelo.Sucursal;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.controlador.Controlador;
import presentacion.vista.Login.VentanaLogin;

public class ControladorLogin {
	private Controlador controlador;
	private VentanaLogin ventanaLogin;
	private Empleado empleado;

	private EmpleadoDTO empleadoInicioSesion;
	private List<SucursalDTO> sucursales;
	private Sucursal sucursal;

	private SucursalDTO sucursalSeleccionada;

	private empleadoProperties empleadoProp;
	private sucursalProperties sucursalProp;

	public ControladorLogin() {
		this.empleado = new Empleado(new DAOSQLFactory());
		this.sucursal = new Sucursal(new DAOSQLFactory());

		this.ventanaLogin = new VentanaLogin();
		this.sucursales = sucursal.readAll();

	}

	public void inicializar() {
		this.ventanaLogin.getBtnIniciarSesion().addActionListener(a -> validarUsuario(a));
		rellenarCombobox();
	}

	@SuppressWarnings("unchecked")
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

		ocultarVentanaLogin();
		iniciarZapateria();
	}

	public SucursalDTO obtenerSucursalSleccionada() {
		String nombreSucursalSeleccionado = this.ventanaLogin.getCbSucursales().getSelectedItem().toString();
		SucursalDTO sucursalSeleccionada = null;
		for (SucursalDTO s : sucursales) {
			if (s.getNombre().equals(nombreSucursalSeleccionado)) {
				sucursalSeleccionada = s;
			}
		}
		return sucursalSeleccionada;
	}

	public void iniciarZapateria() {
		empleadoProp = empleadoProperties.getInstance();

		String idEmpleado = "" + empleadoInicioSesion.getIdEmpleado();
		String CUIL = empleadoInicioSesion.getCUIL();
		String nombre = empleadoInicioSesion.getNombre();
		String apellido = empleadoInicioSesion.getApellido();
		String correoElectronico = empleadoInicioSesion.getCorreoElectronico();
		String tipoEmpleado = empleadoInicioSesion.getTipoEmpleado();

		empleadoProp.establecerPropertiesEmpleado(idEmpleado, CUIL, nombre, apellido, correoElectronico, tipoEmpleado);

		sucursalProp = sucursalProperties.getInstance();

		String IdSucursal = "" + sucursalSeleccionada.getIdSucursal();
		String Telefono = sucursalSeleccionada.getTelefono();
		String Calle = sucursalSeleccionada.getCalle();
		String Altura = sucursalSeleccionada.getAltura();
		String Provincia = sucursalSeleccionada.getProvincia();
		String Localidad = sucursalSeleccionada.getLocalidad();
		String Pais = sucursalSeleccionada.getPais();
		String CodigoPostal = sucursalSeleccionada.getCodigoPostal();
		String Nombre = sucursalSeleccionada.getNombre();

		sucursalProp.establecerPropertiesSucursal(IdSucursal, Telefono, Calle, Altura, Provincia, Localidad, Pais,
				CodigoPostal, Nombre);
		
		this.controlador = new Controlador();
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
