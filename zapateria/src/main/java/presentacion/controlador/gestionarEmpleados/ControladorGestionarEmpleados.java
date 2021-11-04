package presentacion.controlador.gestionarEmpleados;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import dto.EmpleadoDTO;
import modelo.Empleado;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.controlador.Controlador;
import presentacion.vista.gestionarEmpleados.VentanaAgregarEmpleados;
import presentacion.vista.gestionarEmpleados.VentanaGestionarEmpleados;

public class ControladorGestionarEmpleados {

	private VentanaGestionarEmpleados ventanaGestionarEmpleados;
	private Empleado empleado;
	private List<EmpleadoDTO> empleadosEnTabla;
	private String[] estados = {"Cajero","Vendedor","Supervisor","Supervisor de Fabrica","Operario de Fabrica","Administrativo","Gerente"};
	
	Controlador controlador;

	public ControladorGestionarEmpleados() {
		this.ventanaGestionarEmpleados = new VentanaGestionarEmpleados();
		this.empleado = new Empleado(new DAOSQLFactory());
	}

	public ControladorGestionarEmpleados(Controlador controlador) {
		this.ventanaGestionarEmpleados = new VentanaGestionarEmpleados();
		this.empleado = new Empleado(new DAOSQLFactory());
		this.controlador = controlador;
	}

	public void inicializar() {
		// Botones
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
		if(tipoEmpleado.equals("Sin seleccionar")) {
			tipoEmpleado="";
		}
		
		List<EmpleadoDTO> empleadosFiltro = this.empleado.getFiltrarPor("IdEmpleado", txtIdEmpleado,
				"CUIL", txtCUIL,"TipoEmpleado", tipoEmpleado);
		llenarTabla(empleadosFiltro);
	}

	public void agregar(ActionEvent v) {
	
	}

	public void verHistorial(ActionEvent v) {

	}

	public void actualizar(ActionEvent p) {

	}

	public void mostrarVentana() {
		this.ventanaGestionarEmpleados.show();
		this.refrescarTabla();
	}

	public void atras(ActionEvent a) {
//		this.controladorHistorialCambioCotizacion.ocultarVentana();
//		this.ventanaModificarCotizacion.cerrar();
//		this.controlador.mostrarVentanaMenuDeSistemas();

	}
	
	@SuppressWarnings("unchecked")
	public void rellenarCbTipoEmpleado() {
		for(int i=0; i<estados.length; i++) {
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
			String Contra = m.getContra();
			Object[] fila = { IdEmpleado, CUIL, Nombre, Apellido, CorreoElectronico, TipoEmpleado, Contra };
			this.ventanaGestionarEmpleados.getModel().addRow(fila);
		}
	}

	public static void main(String[] args) {
		ControladorGestionarEmpleados controlador = new ControladorGestionarEmpleados();
		controlador.inicializar();
		controlador.mostrarVentana();
	}

}
