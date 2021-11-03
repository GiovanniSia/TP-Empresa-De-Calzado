package presentacion.controlador.Login;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

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
	private VentanaLogin ventanaLogin;
	private Empleado empleado;
	private EmpleadoDTO empleadoInicioSesion;
	private List<SucursalDTO> sucursales;
	private Sucursal sucursal;
	private SucursalDTO sucursalSeleccionada;
	private empleadoProperties empleadoProp;
	private sucursalProperties sucursalProp;
	private Controlador controlador;

	public ControladorLogin() {
		this.empleado = new Empleado(new DAOSQLFactory());
		this.sucursal = new Sucursal(new DAOSQLFactory());
		this.ventanaLogin = new VentanaLogin();
		this.sucursales = sucursal.readAll();
	}

	public void inicializar() {
		if(yaSeInicioSesion()) {
			mostrarControlador();
		}else {
			mostrarMenu();
		}
		
	}

	public void mostrarMenu() {
		mostrarVentana();
		this.ventanaLogin.getBtnIniciarSesion().addActionListener(a -> iniciarSesion(a));
		rellenarCombobox();		
	}
	
	private void iniciarSesion(ActionEvent a) {
		if (inicioSesionValido()) {
			iniciarZapateria();
		}
	}

	private void iniciarZapateria() {
		cerrarVentana();
		establecerPropertiesEmpleado();
		establecerPropertiesSucursal();	
		mostrarControlador();
	}

	@SuppressWarnings("unchecked")
	private void rellenarCombobox() {
		for (SucursalDTO s : sucursales) {
			ventanaLogin.getCbSucursales().addItem(s.getNombre());
		}
	}

	@SuppressWarnings({ "deprecation" })
	private boolean inicioSesionValido() {
		String correoElectronico = this.ventanaLogin.getTxtFieldCorreo().getText();
		String clave = this.ventanaLogin.getTxtFieldContra().getText();
		if (empleado.selectUser(correoElectronico, clave) == null) {
			JOptionPane.showMessageDialog(null, "Ingreso denegado, algunos de los datos es invalido");
			return false;
		}
		JOptionPane.showMessageDialog(null,
				"Ingreso exitoso, bienvenido " + empleado.selectUser(correoElectronico, clave).getTipoEmpleado());

		sucursalSeleccionada = obtenerSucursalSleccionada();
		empleadoInicioSesion = empleado.selectUser(correoElectronico, clave);
		return true;
	}

	private SucursalDTO obtenerSucursalSleccionada() {
		String nombreSucursalSeleccionado = this.ventanaLogin.getCbSucursales().getSelectedItem().toString();
		SucursalDTO sucursalSeleccionada = null;
		for (SucursalDTO s : sucursales) {
			if (s.getNombre().equals(nombreSucursalSeleccionado)) {
				sucursalSeleccionada = s;
			}
		}
		return sucursalSeleccionada;
	}

	private void mostrarControlador() {
		controlador = new Controlador();
		controlador.inicializar();
		controlador.setControladorLogin(this);
		ventanaLogin.limpiarCampos();
		controlador.mostrarVentanaPorTipoEmpleado();
	}

	private void establecerPropertiesEmpleado() {
		empleadoProp = empleadoProperties.getInstance();

		String idEmpleado = "" + empleadoInicioSesion.getIdEmpleado();
		String CUIL = empleadoInicioSesion.getCUIL();
		String nombre = empleadoInicioSesion.getNombre();
		String apellido = empleadoInicioSesion.getApellido();
		String correoElectronico = empleadoInicioSesion.getCorreoElectronico();
		String tipoEmpleado = empleadoInicioSesion.getTipoEmpleado();

		empleadoProp.establecerPropertiesEmpleado(idEmpleado, CUIL, nombre, apellido, correoElectronico, tipoEmpleado);
	}

	private void establecerPropertiesSucursal() {
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
	}

	public void mostrarVentana() {
		this.ventanaLogin.mostrarVentana();
	}

	public void cerrarVentana() {
		this.ventanaLogin.cerrarVentana();
	}

	public boolean yaSeInicioSesion() {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("inicioSesion/empleado.properties"));
			return true;
		} catch (IOException e) {
			return false;
		}

	}
	
}
