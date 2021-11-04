package presentacion.controlador.gestionarEmpleados;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import dto.EmpleadoDTO;
import modelo.Empleado;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.vista.gestionarEmpleados.VentanaAgregarEmpleados;

public class ControladorAgregarEmpleados {
	private VentanaAgregarEmpleados ventanaAgregarEmpleados;
	private String[] estados = { "Cajero", "Vendedor", "Supervisor", "Supervisor de Fabrica", "Operario de Fabrica",
			"Administrativo", "Gerente"};
	private Empleado empleado;
	private ControladorGestionarEmpleados controladorGestionarEmpleados;

	public ControladorAgregarEmpleados(ControladorGestionarEmpleados controladorGestionarEmpleados) {
		this.ventanaAgregarEmpleados = new VentanaAgregarEmpleados();
		this.empleado = new Empleado(new DAOSQLFactory());
		this.controladorGestionarEmpleados = controladorGestionarEmpleados;
	}

	public void inicializar() {
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

			EmpleadoDTO empleadoNuevo = new EmpleadoDTO(0, CUIL, Nombre, Apellido, CorreoElectronico, TipoEmpleado,
					Contra);
			this.empleado.insert(empleadoNuevo);
			JOptionPane.showMessageDialog(null, "Se registro con exito");
			controladorGestionarEmpleados.refrescarTabla();
			this.cerrarVentana();
		}
	}

	@SuppressWarnings("deprecation")
	public boolean verificarDatos() {
		String CorreoElectronico = this.ventanaAgregarEmpleados.getTxtCorreoElectronico().getText();
		boolean m = CorreoElectronico
				.matches("^[A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		if (!m) {
			JOptionPane.showMessageDialog(null, "El formato de mail es incorrecto");
			return false;
		}
		String claveNueva = this.ventanaAgregarEmpleados.getTxtClaveNueva().getText();
		String claveRepetida = this.ventanaAgregarEmpleados.getTxtRepetirClave().getText();
		if(!claveNueva.equals(claveRepetida)) {
			JOptionPane.showMessageDialog(null, "No coinciden las claves");
			return false;
		}	
		
		if(claveNueva.equals("")) {
			JOptionPane.showMessageDialog(null, "La clave no puede estar vacia");
			return false;
		}
		
		String tipoEmpleado = this.ventanaAgregarEmpleados.getCbTipoEmpleado().getSelectedItem().toString();
		if(tipoEmpleado.equals("Sin seleccionar")) {
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
