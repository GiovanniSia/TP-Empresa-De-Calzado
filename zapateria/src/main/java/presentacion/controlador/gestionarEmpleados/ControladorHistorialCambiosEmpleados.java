package presentacion.controlador.gestionarEmpleados;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import dto.HistorialCambioEmpleadoDTO;
import modelo.HistorialCambioEmpleado;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.vista.gestionarEmpleados.VentanaHistorialCambiosEmpleados;

public class ControladorHistorialCambiosEmpleados {
	private VentanaHistorialCambiosEmpleados ventanaHistorialCambioEmpleados;
	private HistorialCambioEmpleado historialCambioEmpleado;
	private List<HistorialCambioEmpleadoDTO> historialCambioEmpleadosEnTabla;
	private ControladorGestionarEmpleados controladorGestionarEmpleados;

	public ControladorHistorialCambiosEmpleados() {
		this.ventanaHistorialCambioEmpleados = new VentanaHistorialCambiosEmpleados();
		this.historialCambioEmpleado = new HistorialCambioEmpleado(new DAOSQLFactory());
	}

	public void inicializar() {

		this.ventanaHistorialCambioEmpleados.getBtnAtras().addActionListener(a -> atras(a));

		// TextFiltos
		this.ventanaHistorialCambioEmpleados.getTxtFiltroCodEmpleadoResponsable().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				realizarBusqueda();
			}
		});
		// DateFiltos
		this.ventanaHistorialCambioEmpleados.getDateFiltroFecha()
				.addPropertyChangeListener(new PropertyChangeListener() {
					@Override
					public void propertyChange(PropertyChangeEvent e) {
						realizarBusqueda();
					}
				});

		// TextFiltos
		this.ventanaHistorialCambioEmpleados.getTxtFiltroCodSucursal().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				realizarBusqueda();
			}
		});

		// TextFiltos
		this.ventanaHistorialCambioEmpleados.getTxtFiltroCodEmpleado().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				realizarBusqueda();
			}
		});

		this.ventanaHistorialCambioEmpleados.getCheckboxEmpleadosAgregados()
				.addActionListener(a -> llenarTablaConEmpleadosAgregados());

		this.ventanaHistorialCambioEmpleados.getCheckboxEmpleadosModificados()
				.addActionListener(a -> llenarTablaConEmpleadosModificados());

	}

	public void llenarTablaConEmpleadosAgregados() {
		if (this.ventanaHistorialCambioEmpleados.getCheckboxEmpleadosAgregados().isSelected()) {
			this.historialCambioEmpleadosEnTabla = historialCambioEmpleado.readAll();
			this.llenarTablaAgregados(historialCambioEmpleadosEnTabla);
		} else {
			refrescarTabla();
		}
	}

	public void llenarTablaConEmpleadosModificados() {
		if (this.ventanaHistorialCambioEmpleados.getCheckboxEmpleadosModificados().isSelected()) {
			this.historialCambioEmpleadosEnTabla = historialCambioEmpleado.readAll();
			this.llenarTablaModificados(historialCambioEmpleadosEnTabla);
		} else {
			refrescarTabla();
		}
	}

	public void realizarBusqueda() {
		this.ventanaHistorialCambioEmpleados.getModel().setRowCount(0);// borrar datos de la tabla
		this.ventanaHistorialCambioEmpleados.getModel().setColumnCount(0);
		this.ventanaHistorialCambioEmpleados.getModel()
				.setColumnIdentifiers(this.ventanaHistorialCambioEmpleados.getNombreColumnas());

		String txtIdEmpleadoResponsable = this.ventanaHistorialCambioEmpleados.getTxtFiltroCodEmpleadoResponsable()
				.getText();
		String dateFecha = getFecha();
		if (dateFecha == null) {
			dateFecha = "";
		}

		String txtCodSucursal = this.ventanaHistorialCambioEmpleados.getTxtFiltroCodSucursal().getText();
		String txtCodEmpleado = this.ventanaHistorialCambioEmpleados.getTxtFiltroCodEmpleado().getText();

		List<HistorialCambioEmpleadoDTO> historialCambioEmpleadoFiltro = this.historialCambioEmpleado.getFiltrarPor(
				"IdEmpleadoResponsable", txtIdEmpleadoResponsable, "Fecha", dateFecha, "IdSucursal", txtCodSucursal,
				"IdEmpleado", txtCodEmpleado);
		llenarTabla(historialCambioEmpleadoFiltro);
	}

	private String getFecha() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date fechaDesde = this.ventanaHistorialCambioEmpleados.getDateFiltroFecha().getDate();
		String fechaDesdeFormato = null;
		if (fechaDesde != null) {
			fechaDesdeFormato = dateFormat.format(fechaDesde);
		}
		return fechaDesdeFormato;
	}

	public void mostrarVentana() {
		this.ventanaHistorialCambioEmpleados.mostrarVentana();
		this.refrescarTabla();
	}

	public void cerrarVentana() {
		this.ventanaHistorialCambioEmpleados.cerrarVentana();
	}

	public void atras(ActionEvent a) {
		this.ventanaHistorialCambioEmpleados.cerrarVentana();
		this.controladorGestionarEmpleados.mostrarVentana();
	}

	public void refrescarTabla() {
		this.historialCambioEmpleadosEnTabla = historialCambioEmpleado.readAll();
		this.llenarTabla(historialCambioEmpleadosEnTabla);
	}

	public void llenarTabla(List<HistorialCambioEmpleadoDTO> empleadosEnTabla) {
		this.ventanaHistorialCambioEmpleados.getModel().setRowCount(0);
		this.ventanaHistorialCambioEmpleados.getModel().setColumnCount(0);
		this.ventanaHistorialCambioEmpleados.getModel()
				.setColumnIdentifiers(this.ventanaHistorialCambioEmpleados.getNombreColumnas());
		for (HistorialCambioEmpleadoDTO m : empleadosEnTabla) {

			String IdEmpleadoResponsable = m.getIdEmpleadoResponsable();
			String Fecha = m.getFecha();
			String IdSucursal = m.getIdSucursal();
			String IdEmpleado = m.getIdEmpleado();
			String CUILAntiguo = m.getCUILAntiguo();
			String CUILNuevo = m.getCUILNuevo();
			String NombreAntiguo = m.getNombreAntiguo();
			String NombreNuevo = m.getNombreNuevo();
			String ApellidoAntiguo = m.getApellidoAntiguo();
			String ApellidoNuevo = m.getApellidoNuevo();
			String CorreoElectronicoAntiguo = m.getCorreoElectronicoAntiguo();
			String CorreoElectronicoNuevo = m.getCorreoElectronicoNuevo();
			String TipoEmpleadoAntiguo = m.getTipoEmpleadoAntiguo();
			String TipoEmpleadoNuevo = m.getTipoEmpleadoNuevo();

			Object[] fila = { IdEmpleadoResponsable, Fecha, IdSucursal, IdEmpleado, CUILAntiguo, CUILNuevo,
					NombreAntiguo, NombreNuevo, ApellidoAntiguo, ApellidoNuevo, CorreoElectronicoAntiguo,
					CorreoElectronicoNuevo, TipoEmpleadoAntiguo, TipoEmpleadoNuevo };
			this.ventanaHistorialCambioEmpleados.getModel().addRow(fila);
		}
	}

	public void llenarTablaAgregados(List<HistorialCambioEmpleadoDTO> empleadosEnTabla) {
		this.ventanaHistorialCambioEmpleados.getModel().setRowCount(0);
		this.ventanaHistorialCambioEmpleados.getModel().setColumnCount(0);
		this.ventanaHistorialCambioEmpleados.getModel()
				.setColumnIdentifiers(this.ventanaHistorialCambioEmpleados.getNombreColumnas());
		for (HistorialCambioEmpleadoDTO m : empleadosEnTabla) {

			String IdEmpleadoResponsable = m.getIdEmpleadoResponsable();
			String Fecha = m.getFecha();
			String IdSucursal = m.getIdSucursal();
			String IdEmpleado = m.getIdEmpleado();
			String CUILAntiguo = m.getCUILAntiguo();
			String CUILNuevo = m.getCUILNuevo();
			String NombreAntiguo = m.getNombreAntiguo();
			String NombreNuevo = m.getNombreNuevo();
			String ApellidoAntiguo = m.getApellidoAntiguo();
			String ApellidoNuevo = m.getApellidoNuevo();
			String CorreoElectronicoAntiguo = m.getCorreoElectronicoAntiguo();
			String CorreoElectronicoNuevo = m.getCorreoElectronicoNuevo();
			String TipoEmpleadoAntiguo = m.getTipoEmpleadoAntiguo();
			String TipoEmpleadoNuevo = m.getTipoEmpleadoNuevo();
			if (NombreAntiguo.equals("")) {
				Object[] fila = { IdEmpleadoResponsable, Fecha, IdSucursal, IdEmpleado, CUILAntiguo, CUILNuevo,
						NombreAntiguo, NombreNuevo, ApellidoAntiguo, ApellidoNuevo, CorreoElectronicoAntiguo,
						CorreoElectronicoNuevo, TipoEmpleadoAntiguo, TipoEmpleadoNuevo };
				this.ventanaHistorialCambioEmpleados.getModel().addRow(fila);
			}
		}
	}

	public void llenarTablaModificados(List<HistorialCambioEmpleadoDTO> empleadosEnTabla) {
		this.ventanaHistorialCambioEmpleados.getModel().setRowCount(0);
		this.ventanaHistorialCambioEmpleados.getModel().setColumnCount(0);
		this.ventanaHistorialCambioEmpleados.getModel()
				.setColumnIdentifiers(this.ventanaHistorialCambioEmpleados.getNombreColumnas());
		for (HistorialCambioEmpleadoDTO m : empleadosEnTabla) {

			String IdEmpleadoResponsable = m.getIdEmpleadoResponsable();
			String Fecha = m.getFecha();
			String IdSucursal = m.getIdSucursal();
			String IdEmpleado = m.getIdEmpleado();
			String CUILAntiguo = m.getCUILAntiguo();
			String CUILNuevo = m.getCUILNuevo();
			String NombreAntiguo = m.getNombreAntiguo();
			String NombreNuevo = m.getNombreNuevo();
			String ApellidoAntiguo = m.getApellidoAntiguo();
			String ApellidoNuevo = m.getApellidoNuevo();
			String CorreoElectronicoAntiguo = m.getCorreoElectronicoAntiguo();
			String CorreoElectronicoNuevo = m.getCorreoElectronicoNuevo();
			String TipoEmpleadoAntiguo = m.getTipoEmpleadoAntiguo();
			String TipoEmpleadoNuevo = m.getTipoEmpleadoNuevo();
			if (!NombreAntiguo.equals("")) {
				Object[] fila = { IdEmpleadoResponsable, Fecha, IdSucursal, IdEmpleado, CUILAntiguo, CUILNuevo,
						NombreAntiguo, NombreNuevo, ApellidoAntiguo, ApellidoNuevo, CorreoElectronicoAntiguo,
						CorreoElectronicoNuevo, TipoEmpleadoAntiguo, TipoEmpleadoNuevo };
				this.ventanaHistorialCambioEmpleados.getModel().addRow(fila);
			}
		}
	}

	public void setControladorGestionarEmpleados(ControladorGestionarEmpleados controladorGestionarEmpleados) {
		this.controladorGestionarEmpleados = controladorGestionarEmpleados;
	}
	
	
}
