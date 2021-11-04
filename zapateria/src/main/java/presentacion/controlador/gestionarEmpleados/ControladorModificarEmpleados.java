package presentacion.controlador.gestionarEmpleados;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import dto.EmpleadoDTO;
import modelo.Empleado;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.vista.gestionarEmpleados.VentanaModificarEmpleados;

public class ControladorModificarEmpleados {
	private VentanaModificarEmpleados ventanaModificarEmpleados;
	
	private String[] estados = { "Cajero", "Vendedor", "Supervisor", "Supervisor de Fabrica", "Operario de Fabrica",
			"Administrativo", "Gerente", "Despedido"};
	private Empleado empleado;
	private ControladorGestionarEmpleados controladorGestionarEmpleados;

	public ControladorModificarEmpleados(ControladorGestionarEmpleados controladorGestionarEmpleados) {
		this.ventanaModificarEmpleados = new VentanaModificarEmpleados();
		this.empleado = new Empleado(new DAOSQLFactory());
		this.controladorGestionarEmpleados = controladorGestionarEmpleados;
	}

	public void inicializar() {
		this.ventanaModificarEmpleados.getBtnAtras().addActionListener(a -> atras(a));
		this.ventanaModificarEmpleados.getBtnAgregar().addActionListener(v -> agregar(v));

		rellenarCbTipoEmpleado();
	}

	@SuppressWarnings("deprecation")
	public void agregar(ActionEvent v) {
		if (verificarDatos()) {
			String CUIL = this.ventanaModificarEmpleados.getTxtCUIL().getText();
			String Nombre = this.ventanaModificarEmpleados.getTxtNombre().getText();
			String Apellido = this.ventanaModificarEmpleados.getTxtApellido().getText();
			String CorreoElectronico = this.ventanaModificarEmpleados.getTxtCorreoElectronico().getText();
			String TipoEmpleado = this.ventanaModificarEmpleados.getCbTipoEmpleado().getSelectedItem().toString();
			String Contra = this.ventanaModificarEmpleados.getTxtClaveNueva().getText();

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
		String CorreoElectronico = this.ventanaModificarEmpleados.getTxtCorreoElectronico().getText();
		boolean m = CorreoElectronico
				.matches("^[A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		if (!m) {
			JOptionPane.showMessageDialog(null, "El formato de mail es incorrecto");
			return false;
		}
		String claveNueva = this.ventanaModificarEmpleados.getTxtClaveNueva().getText();
		String claveRepetida = this.ventanaModificarEmpleados.getTxtClaveNueva().getText();
		if(!claveNueva.equals(claveRepetida)) {
			JOptionPane.showMessageDialog(null, "No coinciden las claves");
			return false;
		}	
		
		String tipoEmpleado = this.ventanaModificarEmpleados.getCbTipoEmpleado().getSelectedItem().toString();
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
		this.ventanaModificarEmpleados.show();
	}

	public void cerrarVentana() {
		this.ventanaModificarEmpleados.cerrarVentana();
	}

	@SuppressWarnings("unchecked")
	public void rellenarCbTipoEmpleado() {
		for (int i = 0; i < estados.length; i++) {
			this.ventanaModificarEmpleados.getCbTipoEmpleado().addItem(estados[i]);
		}
	}
}
