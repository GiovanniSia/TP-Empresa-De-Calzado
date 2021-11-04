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
			"Administrativo", "Gerente", "Desempleado" };
	private Empleado empleado;
	private EmpleadoDTO empleadoSeleccionado;
	private ControladorGestionarEmpleados controladorGestionarEmpleados;

	public ControladorModificarEmpleados(ControladorGestionarEmpleados controladorGestionarEmpleados) {
		this.ventanaModificarEmpleados = new VentanaModificarEmpleados();
		this.empleado = new Empleado(new DAOSQLFactory());
		this.controladorGestionarEmpleados = controladorGestionarEmpleados;
	}

	public void inicializar() {
		this.ventanaModificarEmpleados.getBtnAtras().addActionListener(a -> atras(a));
		this.ventanaModificarEmpleados.getBtnActualizar().addActionListener(v -> actualizar(v));
	}

	public void rellenarCampos() {
		this.ventanaModificarEmpleados.getTxtNombre().setText(empleadoSeleccionado.getNombre());
		this.ventanaModificarEmpleados.getTxtApellido().setText(empleadoSeleccionado.getApellido());
		this.ventanaModificarEmpleados.getTxtCUIL().setText(empleadoSeleccionado.getCUIL());
		this.ventanaModificarEmpleados.getTxtCorreoElectronico().setText(empleadoSeleccionado.getCorreoElectronico());
		this.ventanaModificarEmpleados.getCbTipoEmpleado().setSelectedItem(empleadoSeleccionado.getTipoEmpleado());
	}

	public void actualizar(ActionEvent v) {

		if (verificarDatos()) {
			String CUIL = this.ventanaModificarEmpleados.getTxtCUIL().getText();
			String Nombre = this.ventanaModificarEmpleados.getTxtNombre().getText();
			String Apellido = this.ventanaModificarEmpleados.getTxtApellido().getText();
			String CorreoElectronico = this.ventanaModificarEmpleados.getTxtCorreoElectronico().getText();
			String TipoEmpleado = this.ventanaModificarEmpleados.getCbTipoEmpleado().getSelectedItem().toString();
			String ContraNueva = "";
			String ContraVieja = "";
			
			if (this.ventanaModificarEmpleados.getCheckboxCambiarClave().isSelected()) {
				ContraNueva = this.ventanaModificarEmpleados.getTxtClaveNueva().getText();
				EmpleadoDTO empleadoNuevo = new EmpleadoDTO(0, CUIL, Nombre, Apellido, CorreoElectronico, TipoEmpleado,
						ContraNueva);
				this.empleado.update(empleadoSeleccionado.getIdEmpleado(),empleadoNuevo);
			} else {
				ContraVieja = this.empleadoSeleccionado.getContra();
				EmpleadoDTO empleadoNuevo = new EmpleadoDTO(0, CUIL, Nombre, Apellido, CorreoElectronico, TipoEmpleado,
						ContraVieja);
				this.empleado.update(empleadoSeleccionado.getIdEmpleado(),empleadoNuevo);
			}

			JOptionPane.showMessageDialog(null, "Se registro con exito");
			controladorGestionarEmpleados.refrescarTabla();
			this.cerrarVentana();
		}

	}

	public boolean verificarDatos() {
		String CUILAntiguo = this.empleadoSeleccionado.getCUIL();
		String NombreAntiguo = this.empleadoSeleccionado.getNombre();
		String ApellidoAntiguo = this.empleadoSeleccionado.getApellido();
		String CorreoElectronicoAntiguo = this.empleadoSeleccionado.getCorreoElectronico();
		String TipoEmpleadoAntiguo = this.empleadoSeleccionado.getTipoEmpleado();
		String ClaveAntiguo = this.empleadoSeleccionado.getContra();
		
		
		String CUILNuevo = this.ventanaModificarEmpleados.getTxtCUIL().getText();
		String NombreNuevo = this.ventanaModificarEmpleados.getTxtNombre().getText();
		String ApellidoNuevo  = this.ventanaModificarEmpleados.getTxtApellido().getText();
		String CorreoElectronicoNuevo  = this.ventanaModificarEmpleados.getTxtCorreoElectronico().getText();
		String TipoEmpleadoNuevo = this.ventanaModificarEmpleados.getCbTipoEmpleado().getSelectedItem().toString();
		String ClaveNuevo = this.ventanaModificarEmpleados.getTxtClaveNueva().getText();
		
		if(CUILAntiguo.equals(CUILNuevo) && NombreAntiguo.equals(NombreNuevo) && ApellidoAntiguo.equals(ApellidoNuevo) && CorreoElectronicoAntiguo.equals(CorreoElectronicoNuevo)
				&& TipoEmpleadoAntiguo.equals(TipoEmpleadoNuevo)) {
			JOptionPane.showMessageDialog(null, "es lo msiom");
			return false;
		}
		
		boolean m = CorreoElectronicoNuevo
				.matches("^[A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		if (!m) {
			JOptionPane.showMessageDialog(null, "El formato de mail es incorrecto");
			return false;
		}
		Boolean checkboxActivado = this.ventanaModificarEmpleados.getCheckboxCambiarClave().isSelected();
		if (checkboxActivado && ClaveNuevo.equals("")) {
			JOptionPane.showMessageDialog(null, "La clave no puede ser vacia");
			return false;
		}

		if (TipoEmpleadoNuevo.equals("Sin seleccionar")) {
			JOptionPane.showMessageDialog(null, "No selecciono el tipo de empleado");
			return false;
		}
		
		
		return true;
	}

	public void atras(ActionEvent a) {
		cerrarVentana();
	}

	public void mostrarVentana() {
		rellenarCbTipoEmpleado();
		
		rellenarCampos();
		this.ventanaModificarEmpleados.show();
	}

	public void cerrarVentana() {
		this.ventanaModificarEmpleados.cerrarVentana();
	}

	@SuppressWarnings("unchecked")
	public void rellenarCbTipoEmpleado() {
		this.ventanaModificarEmpleados.getCbTipoEmpleado().removeAllItems();
		for (int i = 0; i < estados.length; i++) {
			this.ventanaModificarEmpleados.getCbTipoEmpleado().addItem(estados[i]);
		}
	}

	public void setEmpleadoSeleccionado(EmpleadoDTO empleadoSeleccionado) {
		this.empleadoSeleccionado = empleadoSeleccionado;
	}

}
