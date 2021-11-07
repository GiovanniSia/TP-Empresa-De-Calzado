package presentacion.controlador.gestionarEmpleados;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JOptionPane;

import dto.EmpleadoDTO;
import modelo.Empleado;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.controlador.Controlador;
import presentacion.vista.gestionarEmpleados.VentanaGestionarEmpleados;

public class ControladorGestionarEmpleados {

	private VentanaGestionarEmpleados ventanaGestionarEmpleados;
	private Empleado empleado;
	private List<EmpleadoDTO> empleadosEnTabla;
	private String[] estados = { "Cajero", "Vendedor", "Supervisor", "Supervisor de Fabrica", "Operario de Fabrica",
			"Administrativo", "Gerente" };
	private ControladorAgregarEmpleados controladorAgregarEmpleados;

	private ControladorModificarEmpleados controladorModificarEmpelados;
	
	private ControladorHistorialCambiosEmpleados controladorHistorialCambiosEmpleados;

	private Controlador controlador;
	
	public static void main(String[] args) {
		ControladorGestionarEmpleados a = new ControladorGestionarEmpleados();
		a.inicializar();
		a.mostrarVentana();
	}

	public ControladorGestionarEmpleados() {
		this.ventanaGestionarEmpleados = new VentanaGestionarEmpleados();
		this.empleado = new Empleado(new DAOSQLFactory());
		this.controladorAgregarEmpleados = new ControladorAgregarEmpleados(this);
		this.controladorModificarEmpelados = new ControladorModificarEmpleados(this);
		this.controladorHistorialCambiosEmpleados = new ControladorHistorialCambiosEmpleados();
	}

	public ControladorGestionarEmpleados(Controlador controlador) {
		this.ventanaGestionarEmpleados = new VentanaGestionarEmpleados();
		this.empleado = new Empleado(new DAOSQLFactory());
		this.controladorAgregarEmpleados = new ControladorAgregarEmpleados(this);
		this.controladorModificarEmpelados = new ControladorModificarEmpleados(this);
		this.controladorHistorialCambiosEmpleados = new ControladorHistorialCambiosEmpleados();
		this.controlador = controlador;
	}

	public void inicializar() {
		this.controladorAgregarEmpleados.inicializar();
		this.controladorModificarEmpelados.inicializar();
		this.controladorHistorialCambiosEmpleados.inicializar();
		
		this.ventanaGestionarEmpleados.getBtnAtras().addActionListener(a -> atras(a));
		this.ventanaGestionarEmpleados.getBtnAgregar().addActionListener(v -> agregar(v));
		this.ventanaGestionarEmpleados.getBtnModificar().addActionListener(c -> actualizar(c));
		this.ventanaGestionarEmpleados.getBtnHistorialCambio().addActionListener(v -> verHistorial(v));

		rellenarCbTipoEmpleado();

		// TextFiltos
		this.ventanaGestionarEmpleados.getTxtFiltroIdEmpleado().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				realizarBusqueda();
			}
		});
		// TextFiltos
		this.ventanaGestionarEmpleados.getTxtFiltroCUIL().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				realizarBusqueda();
			}
		});

		this.ventanaGestionarEmpleados.getCbTipoEmpleado().addActionListener(a -> realizarBusqueda());

	}

	public void realizarBusqueda() {
		this.ventanaGestionarEmpleados.getModel().setRowCount(0);// borrar datos de la tabla
		this.ventanaGestionarEmpleados.getModel().setColumnCount(0);
		this.ventanaGestionarEmpleados.getModel()
				.setColumnIdentifiers(this.ventanaGestionarEmpleados.getNombreColumnas());

		String txtIdEmpleado = this.ventanaGestionarEmpleados.getTxtFiltroIdEmpleado().getText();
		String txtCUIL = this.ventanaGestionarEmpleados.getTxtFiltroCUIL().getText();
		String tipoEmpleado = this.ventanaGestionarEmpleados.getCbTipoEmpleado().getSelectedItem().toString();
		if (tipoEmpleado.equals("Sin seleccionar")) {
			tipoEmpleado = "";
		}

		List<EmpleadoDTO> empleadosFiltro = this.empleado.getFiltrarPor("IdEmpleado", txtIdEmpleado, "CUIL", txtCUIL,
				"TipoEmpleado", tipoEmpleado);
		llenarTabla(empleadosFiltro);
	}

	public void agregar(ActionEvent v) {
		this.controladorAgregarEmpleados.mostrarVentana();
	}

	public void verHistorial(ActionEvent v) {
		this.controladorHistorialCambiosEmpleados.mostrarVentana();	
	}

	public void actualizar(ActionEvent p) {
		int filaSeleccionada = filaSeleccionadaTablaProducto();
		if (filaSeleccionada == -1) {
			JOptionPane.showMessageDialog(null, "Seleccione un empleado");
			return;
		}
		EmpleadoDTO empleadoSeleccionado = this.empleadosEnTabla.get(filaSeleccionada);
		controladorModificarEmpelados.setEmpleadoSeleccionado(empleadoSeleccionado);

		this.controladorModificarEmpelados.mostrarVentana();
	}

	public int filaSeleccionadaTablaProducto() {
		return this.ventanaGestionarEmpleados.getTablaEmpleados().getSelectedRow();
	}

	public void cerrarTodasLasVentanas() {
		this.controladorAgregarEmpleados.cerrarVentana();
		this.controladorHistorialCambiosEmpleados.cerrarVentana();
		this.controladorModificarEmpelados.cerrarVentana();
	}

	public void mostrarVentana() {
		this.ventanaGestionarEmpleados.show();
		this.refrescarTabla();
	}

	public void atras(ActionEvent a) {
		cerrarTodasLasVentanas();
	}

	@SuppressWarnings("unchecked")
	public void rellenarCbTipoEmpleado() {
		for (int i = 0; i < estados.length; i++) {
			this.ventanaGestionarEmpleados.getCbTipoEmpleado().addItem(estados[i]);
		}
	}

	public void refrescarTabla() {
		this.empleadosEnTabla = empleado.readAll();
		this.llenarTabla(empleadosEnTabla);
	}

	public void llenarTabla(List<EmpleadoDTO> empleadosEnTabla) {
		this.ventanaGestionarEmpleados.getModel().setRowCount(0);
		this.ventanaGestionarEmpleados.getModel().setColumnCount(0);
		this.ventanaGestionarEmpleados.getModel()
				.setColumnIdentifiers(this.ventanaGestionarEmpleados.getNombreColumnas());
		for (EmpleadoDTO m : empleadosEnTabla) {
			String IdEmpleado = "" + m.getIdEmpleado();
			String CUIL = m.getCUIL();
			String Nombre = m.getNombre();
			String Apellido = m.getApellido();
			String CorreoElectronico = m.getCorreoElectronico();
			String TipoEmpleado = m.getTipoEmpleado();
			Object[] fila = { IdEmpleado, CUIL, Nombre, Apellido, CorreoElectronico, TipoEmpleado };
			this.ventanaGestionarEmpleados.getModel().addRow(fila);
		}
	}
}
