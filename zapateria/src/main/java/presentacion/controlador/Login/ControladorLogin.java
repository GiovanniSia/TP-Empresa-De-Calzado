package presentacion.controlador.Login;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

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
	private String mail;

	private JProgressBar progressBar;
	private Simulacion simulacion = null;

	private int vecesIngresado = 0;
	private String correoElectrionicoAnterior = "";

	public ControladorLogin() {
		this.empleado = new Empleado(new DAOSQLFactory());
		this.sucursal = new Sucursal(new DAOSQLFactory());
		this.sucursales = sucursal.readAll();
	}

	public void inicializar() {
		this.ventanaLogin = new VentanaLogin();
		this.ventanaLogin.getBtnIniciarSesion().addActionListener(a -> iniciarSesion(a));
		rellenarCombobox();
		obtenerDatosPropertiesMail();
		this.ventanaLogin.getTxtFieldCorreo().setText(mail);
	}

	public void obtenerDatosPropertiesMail() {
		try {
			empleadoProperties empleadoProp = empleadoProperties.getInstance();
			mail = empleadoProp.getValue("CorreoElectronico");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void iniciarSesion(ActionEvent a) {
		if (inicioSesionValido()) {
			iniciarZapateria();
		}
	}

	private void iniciarZapateria() {
		establecerPropertiesEmpleado();
		establecerPropertiesSucursal();
		mostrarControlador();
	}

	@SuppressWarnings("unchecked")
	private void rellenarCombobox() {
		this.sucursales = this.sucursal.readAll();
		for (SucursalDTO s : sucursales) {
			if(s.getIdSucursal()!=1) {
				ventanaLogin.getCbSucursales().addItem(s.getNombre());
			}
		}
	}

	@SuppressWarnings({ "deprecation" })
	private boolean inicioSesionValido() {
		String correoElectronico = this.ventanaLogin.getTxtFieldCorreo().getText();
		String clave = this.ventanaLogin.getTxtFieldContra().getText();

		if (!correoElectronico.equals(correoElectrionicoAnterior)) {
			vecesIngresado = 0;
			correoElectrionicoAnterior = correoElectronico;
		}

		if (empleado.selectUser(correoElectronico, clave) == null) {

			if(empleado.selectUserCorreo(correoElectronico) == null) {
				JOptionPane.showMessageDialog(null, "Ingreso denegado, algunos de los datos es invalido");
				return false;
			}
			
			
			if (empleado.selectUserCorreo(correoElectronico).getTipoEmpleado().equals("Inactivo")) {
				JOptionPane.showMessageDialog(null, "Usted a sido dado de baja");
				return false;
			}
			if (empleado.selectUserCorreo(correoElectronico).getTipoEmpleado().equals("Bloqueado")) {
				JOptionPane.showMessageDialog(null, "Su cuenta se encuentra bloqueada");
				return false;
			}

			if (vecesIngresado != 5
					|| empleado.selectUserCorreo(correoElectronico).getTipoEmpleado().equals("Gerente")) {
				vecesIngresado++;
				JOptionPane.showMessageDialog(null, "Ingreso denegado, algunos de los datos es invalido");
				return false;
			}
		}

		if (empleado.selectUserCorreo(correoElectronico).getTipoEmpleado().equals("Inactivo")) {
			JOptionPane.showMessageDialog(null, "Usted a sido dado de baja");
			return false;
		}
		if (empleado.selectUserCorreo(correoElectronico).getTipoEmpleado().equals("Bloqueado")) {
			JOptionPane.showMessageDialog(null, "Su cuenta se encuentra bloqueada");
			return false;
		}

		if (vecesIngresado == 5 && !empleado.selectUserCorreo(correoElectronico).getTipoEmpleado().equals("Inactivo")
				&& !empleado.selectUserCorreo(correoElectronico).getTipoEmpleado().equals("Bloqueado")
				&& !empleado.selectUserCorreo(correoElectronico).getTipoEmpleado().equals("Gerente")) {
			JOptionPane.showMessageDialog(null, "Cuenta bloqueada, por favor notifique que su cuenta fue bloqueada");

			EmpleadoDTO empleadoBloqueado = empleado.selectUserCorreo(correoElectronico);

			EmpleadoDTO empleadoNuevo = new EmpleadoDTO(empleadoBloqueado.getIdEmpleado(), empleadoBloqueado.getCUIL(),
					empleadoBloqueado.getNombre(), empleadoBloqueado.getApellido(),
					empleadoBloqueado.getCorreoElectronico(), "Bloqueado", empleadoBloqueado.getContra());
			empleado.update(empleadoBloqueado.getIdEmpleado(), empleadoNuevo);
			vecesIngresado = 0;
			return false;
		}

		JOptionPane.showMessageDialog(null,
				"Bienvenido " + empleado.selectUser(correoElectronico, clave).getTipoEmpleado());

		sucursalSeleccionada = obtenerSucursalSleccionada();
		if(empleado.selectUserCorreo(correoElectronico).getTipoEmpleado().equals("Supervisor de Fabrica") 
				|| empleado.selectUserCorreo(correoElectronico).getTipoEmpleado().equals("Operario de Fabrica")) {
			sucursalSeleccionada = sucursales.get(0);
		}
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
		controlador.setControladorLogin(this);
		progressBar = this.ventanaLogin.getProgressBar();
		progressBar.setVisible(true);
		simulacion = new Simulacion(controlador, progressBar, this);
		simulacion.execute();
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

	public void bloquearUsuarioYClave() {
		this.ventanaLogin.getLblCargando().setVisible(true);
		this.ventanaLogin.getCbSucursales().setEnabled(false);
		this.ventanaLogin.getTxtFieldCorreo().setEditable(false);
		this.ventanaLogin.getTxtFieldContra().setEditable(false);
		this.ventanaLogin.getBtnIniciarSesion().setEnabled(false);
	}

	public void limpiarCampos() {
		this.ventanaLogin.limpiarCampos();
	}

	public void mostrarVentana() {
		this.ventanaLogin.mostrarVentana();
	}

	public void cerrarVentana() {
		this.ventanaLogin.cerrarVentana();
	}

}
