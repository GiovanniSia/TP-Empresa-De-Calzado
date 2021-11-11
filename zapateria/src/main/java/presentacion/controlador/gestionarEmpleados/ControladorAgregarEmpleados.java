package presentacion.controlador.gestionarEmpleados;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JOptionPane;

import dto.EmpleadoDTO;
import dto.HistorialCambioEmpleadoDTO;
import inicioSesion.empleadoProperties;
import inicioSesion.sucursalProperties;
import modelo.Empleado;
import modelo.HistorialCambioEmpleado;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.vista.gestionarEmpleados.VentanaAgregarEmpleados;

public class ControladorAgregarEmpleados {

	private static int idEmpleado = 0;
	private static int idSucursal = 0;

	public void obtenerDatosPropertiesSucursalEmpleado() {
		try {
			sucursalProperties sucursalProp = sucursalProperties.getInstance();
			idSucursal = Integer.parseInt(sucursalProp.getValue("IdSucursal"));

			empleadoProperties empleadoProp = empleadoProperties.getInstance();
			idEmpleado = Integer.parseInt(empleadoProp.getValue("IdEmpleado"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private VentanaAgregarEmpleados ventanaAgregarEmpleados;
	private String[] estados = { "Cajero", "Vendedor", "Supervisor", "Supervisor de Fabrica", "Operario de Fabrica",
			"Administrativo", "Gerente" };
	private Empleado empleado;
	private ControladorGestionarEmpleados controladorGestionarEmpleados;
	private HistorialCambioEmpleado historialCambioEmpleado;

	private List<EmpleadoDTO> empleadosEnTabla;

	public ControladorAgregarEmpleados(ControladorGestionarEmpleados controladorGestionarEmpleados) {
		obtenerDatosPropertiesSucursalEmpleado();
		this.empleado = new Empleado(new DAOSQLFactory());
		this.historialCambioEmpleado = new HistorialCambioEmpleado(new DAOSQLFactory());
		this.controladorGestionarEmpleados = controladorGestionarEmpleados;
	}

	public void inicializar() {
		this.ventanaAgregarEmpleados = new VentanaAgregarEmpleados();
		this.ventanaAgregarEmpleados.getBtnAtras().addActionListener(a -> atras(a));
		this.ventanaAgregarEmpleados.getBtnAgregar().addActionListener(v -> agregar(v));

		rellenarCbTipoEmpleado();
	}

	@SuppressWarnings("deprecation")
	public void agregar(ActionEvent v) {
		if (verificarDatos()) {
			String CUIL = this.ventanaAgregarEmpleados.getTxtCUIL().getText();
			String Nombre = this.ventanaAgregarEmpleados.getTxtNombre().getText();
			String Apellido = this.ventanaAgregarEmpleados.getTxtApellido().getText();
			String CorreoElectronico = this.ventanaAgregarEmpleados.getTxtCorreoElectronico().getText();
			String TipoEmpleado = this.ventanaAgregarEmpleados.getCbTipoEmpleado().getSelectedItem().toString();
			String Contra = this.ventanaAgregarEmpleados.getTxtClaveNueva().getText();

			empleadosEnTabla = empleado.readAll();
			EmpleadoDTO empleadoNuevo = new EmpleadoDTO(empleadosEnTabla.size() + 1, CUIL, Nombre, Apellido,
					CorreoElectronico, TipoEmpleado, Contra);
			this.empleado.insert(empleadoNuevo);
			JOptionPane.showMessageDialog(null, "Se registro con exito");
			controladorGestionarEmpleados.refrescarTabla();
			insertarEmpleadoAgregadoATablaHistorialCambiosEmpleados(empleadoNuevo);
			this.cerrarVentana();
			this.ventanaAgregarEmpleados.limpiarCampos();
		}
	}

	public void insertarEmpleadoAgregadoATablaHistorialCambiosEmpleados(EmpleadoDTO empleadoNuevo) {
		int IdHistorialCambioEmpleados = 0;
		String IdEmpleadoResponsable = "" + idEmpleado;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String fecha = dtf.format(LocalDateTime.now());
		String IdSucursal = "" + idSucursal;
		String IdEmpleado = "" + empleadoNuevo.getIdEmpleado();
		String CUILAntiguo = "";
		String CUILNuevo = "" + empleadoNuevo.getCUIL();
		String NombreAntiguo = "";
		String NombreNuevo = "" + empleadoNuevo.getNombre();
		String ApellidoAntiguo = "";
		String ApellidoNuevo = "" + empleadoNuevo.getApellido();
		String CorreoElectronicoAntiguo = "";
		String CorreoElectronicoNuevo = "" + empleadoNuevo.getCorreoElectronico();
		String TipoEmpleadoAntiguo = "";
		String TipoEmpleadoNuevo = "" + empleadoNuevo.getTipoEmpleado();

		HistorialCambioEmpleadoDTO nuevoHistorial = new HistorialCambioEmpleadoDTO(IdHistorialCambioEmpleados,
				IdEmpleadoResponsable, fecha, IdSucursal, IdEmpleado, CUILAntiguo, CUILNuevo, NombreAntiguo,
				NombreNuevo, ApellidoAntiguo, ApellidoNuevo, CorreoElectronicoAntiguo, CorreoElectronicoNuevo,
				TipoEmpleadoAntiguo, TipoEmpleadoNuevo);

		this.historialCambioEmpleado.insert(nuevoHistorial);
	}

	@SuppressWarnings("deprecation")
	public boolean verificarDatos() {
		String nombre = this.ventanaAgregarEmpleados.getTxtNombre().getText();
		String apellido = this.ventanaAgregarEmpleados.getTxtApellido().getText();
		String cuil = this.ventanaAgregarEmpleados.getTxtCUIL().getText();
		if (nombre.equals("")) {
			JOptionPane.showMessageDialog(null, "El nombre no puede estar vacio");
			return false;
		}
		if (apellido.equals("")) {
			JOptionPane.showMessageDialog(null, "El apellido no puede estar vacio");
			return false;
		}
		if (cuil.equals("")) {
			JOptionPane.showMessageDialog(null, "El CUIL no puede estar vacio");
			return false;
		}

		String CorreoElectronico = this.ventanaAgregarEmpleados.getTxtCorreoElectronico().getText();
		boolean m = CorreoElectronico
				.matches("^[A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		if (!m) {
			JOptionPane.showMessageDialog(null, "El formato de mail es incorrecto");
			return false;
		}

		String claveNueva = this.ventanaAgregarEmpleados.getTxtClaveNueva().getText();
		String claveRepetida = this.ventanaAgregarEmpleados.getTxtRepetirClave().getText();
		if (!claveNueva.equals(claveRepetida)) {
			JOptionPane.showMessageDialog(null, "No coinciden las claves");
			return false;
		}
		
		if(cuil.length()!=11) {
			JOptionPane.showMessageDialog(null, "El CUIL tiene que tener 11 digitos");
			return false;
		}

		if (claveNueva.equals("")) {
			JOptionPane.showMessageDialog(null, "La clave no puede estar vacia");
			return false;
		}

		String tipoEmpleado = this.ventanaAgregarEmpleados.getCbTipoEmpleado().getSelectedItem().toString();
		if (tipoEmpleado.equals("Sin seleccionar")) {
			JOptionPane.showMessageDialog(null, "No selecciono el tipo de empleado");
			return false;
		}

		return true;
	}

	public void atras(ActionEvent a) {
		cerrarVentana();
	}

	public void mostrarVentana() {
		this.ventanaAgregarEmpleados.show();
	}

	public void cerrarVentana() {
		this.ventanaAgregarEmpleados.cerrarVentana();
	}

	@SuppressWarnings("unchecked")
	public void rellenarCbTipoEmpleado() {
		for (int i = 0; i < estados.length; i++) {
			this.ventanaAgregarEmpleados.getCbTipoEmpleado().addItem(estados[i]);
		}
	}
}
