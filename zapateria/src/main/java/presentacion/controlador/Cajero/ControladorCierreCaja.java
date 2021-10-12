package presentacion.controlador.Cajero;

import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JOptionPane;
import dto.EgresosDTO;
import dto.IngresosDTO;
import dto.MedioPagoDTO;
import modelo.Egresos;
import modelo.HistorialCambioMoneda;
import modelo.Ingresos;
import modelo.MedioPago;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.controlador.ControladorHistorialCambioCotizacion;
import presentacion.vista.Cajero.VentanaCierreCaja;

public class ControladorCierreCaja {

	private final String[] tipoMedioPagoEgreso = { "EFE", "NC" };

	private VentanaCierreCaja ventanaCierreCaja;

	private Ingresos ingresos;
	private List<IngresosDTO> ingresosEnTabla;

	private Egresos egresos;
	private List<EgresosDTO> egresosEnTabla;

	public ControladorCierreCaja(Ingresos ingresos, Egresos egresos) {
		this.ventanaCierreCaja = new VentanaCierreCaja();
		this.ingresos = ingresos;
		this.egresos = egresos;
	}

	public void inicializar() {
		this.ingresos = new Ingresos(new DAOSQLFactory());
		this.egresos = new Egresos(new DAOSQLFactory());

		this.ventanaCierreCaja = new VentanaCierreCaja();

		// Botones
		this.ventanaCierreCaja.getBtnAtras().addActionListener(a -> atras(a));
		this.ventanaCierreCaja.getBtnCerrarCajaDeSucursal().addActionListener(c -> cerrarCajaDeSucursal(c));
		
		mostrarFechaHoy();
		llenarTablaIngresos();
		llenarTablaEgresos();

		llenarTablaMedioPagoIngresos();
		llenarTablaMedioPagoEgresos();

		mostrarTotalIngreso();
		mostrarTotalEgreso();
		mostrarBalanceCaja();

	}
	
	public void cerrarCajaDeSucursal(ActionEvent a) {
		int cantidadFilasIngresos = this.ventanaCierreCaja.getTablaMedioPagoIngresos().getRowCount();
		Boolean todosLosMediosDePagoValidos=false;
		for (int i = 0; i < cantidadFilasIngresos; i++) {
			 todosLosMediosDePagoValidos = (Boolean) this.ventanaCierreCaja.getTablaMedioPagoIngresos().getValueAt(i, 3);
			 if(todosLosMediosDePagoValidos==false) {
				 JOptionPane.showMessageDialog(null, "Validar todos los medios de pago");
				 return;
			 }
		}
		int cantidadFilasEgresos = this.ventanaCierreCaja.getTablaMedioPagoEgresos().getRowCount();
		for (int i = 0; i < cantidadFilasEgresos; i++) {
			todosLosMediosDePagoValidos = (Boolean) this.ventanaCierreCaja.getTablaMedioPagoEgresos().getValueAt(i, 3);
			if(todosLosMediosDePagoValidos==false) {
				 JOptionPane.showMessageDialog(null, "Validar todos los medios de pago");
				 return;
			 }
		}
		
		if(todosLosMediosDePagoValidos) {
			JOptionPane.showMessageDialog(null, "Cierre de caja Exitoso");
		}
	}
	
	public BigDecimal ObtenerTotalIngreso() {
		int cantidadFilas = this.ventanaCierreCaja.getTablaMedioPagoIngresos().getRowCount();
		BigDecimal suma = new BigDecimal("0.00");
		for (int i = 0; i < cantidadFilas; i++) {
			BigDecimal total = new BigDecimal(
					"" + this.ventanaCierreCaja.getTablaMedioPagoIngresos().getValueAt(i, 2) + "");
			suma = suma.add(total);
		}
		return suma;
	}

	public BigDecimal obtenerTotalEgreso() {
		int cantidadFilas = this.ventanaCierreCaja.getTablaMedioPagoEgresos().getRowCount();
		BigDecimal suma = new BigDecimal("0.00");
		for (int i = 0; i < cantidadFilas; i++) {
			BigDecimal total = new BigDecimal(
					"" + this.ventanaCierreCaja.getTablaMedioPagoEgresos().getValueAt(i, 2) + "");
			suma = suma.add(total);
		}
		return suma;
	}

	public void mostrarTotalIngreso() {
		this.ventanaCierreCaja.getLblActualizarTotalIngresos().setText("$"+ObtenerTotalIngreso()+"");
	}

	public void mostrarTotalEgreso() {
		this.ventanaCierreCaja.getLblActualizarTotalEgresos().setText("$"+obtenerTotalEgreso()+"");
	}

	public void mostrarBalanceCaja() {
		BigDecimal totalIngresos = ObtenerTotalIngreso();
		BigDecimal totalEgresos = obtenerTotalEgreso();
		BigDecimal balanceCaja= new BigDecimal(""+totalIngresos.subtract(totalEgresos)+"");
		this.ventanaCierreCaja.getLblActualizarBalanceCaja().setText("$"+balanceCaja+"");
	}

	public void mostrarFechaHoy() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String fecha = dtf.format(LocalDateTime.now());
		this.ventanaCierreCaja.getLblFechaHoy().setText(fecha);
	}

	public void atras(ActionEvent a) {
		this.ventanaCierreCaja.cerrar();
	}

	public void cerrarCaja(ActionEvent a) {
		this.ventanaCierreCaja.cerrar();
	}

	public void verHistorialDeCambios(ActionEvent v) {
		HistorialCambioMoneda modelo = new HistorialCambioMoneda(new DAOSQLFactory());
		ControladorHistorialCambioCotizacion controlador = new ControladorHistorialCambioCotizacion(modelo);
		controlador.inicializar();
		controlador.mostrarVentana();
	}

	public void mostrarVentana() {
		this.ventanaCierreCaja.show();
	}

	private List<IngresosDTO> obtenerIngresosDeHoy() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String fecha = dtf.format(LocalDateTime.now());
		return ingresos.getIngresosAproximado("Fecha", fecha, null, null);
	}

	public void llenarTablaIngresos() {
		this.ingresosEnTabla = obtenerIngresosDeHoy();
		this.ventanaCierreCaja.getModelIngresos().setRowCount(0);
		this.ventanaCierreCaja.getModelIngresos().setColumnCount(0);
		this.ventanaCierreCaja.getModelIngresos()
				.setColumnIdentifiers(this.ventanaCierreCaja.getNombreColumnasIngresos());
		for (IngresosDTO i : ingresosEnTabla) {
			String hora = i.getHora();
			String tipo = i.getTipo();
			int idCliente = i.getIdCliente();
			String tipoFactura = i.getTipoFactura();
			String medioPago = i.getMedioPago();
			BigDecimal monto = new BigDecimal("" + i.getCantidad() + "");

			BigDecimal cotizacion = new BigDecimal("" + i.getCotizacion() + "");

			BigDecimal nroOperacion = new BigDecimal(i.getOperacion());
			BigDecimal total = new BigDecimal("" + i.getTotal() + "");

			Object[] fila = { hora, tipo, idCliente, tipoFactura, medioPago, monto, cotizacion, nroOperacion, total };
			this.ventanaCierreCaja.getModelIngresos().addRow(fila);
		}
	}

	public List<EgresosDTO> obtenerEgresosDeHoy() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String fecha = dtf.format(LocalDateTime.now());
		return egresos.getEgresosAproximado("Fecha", fecha, null, null);
	}

	public void llenarTablaEgresos() {
		this.egresosEnTabla = obtenerEgresosDeHoy();
		this.ventanaCierreCaja.getModelEgresos().setRowCount(0);
		this.ventanaCierreCaja.getModelEgresos().setColumnCount(0);
		this.ventanaCierreCaja.getModelEgresos()
				.setColumnIdentifiers(this.ventanaCierreCaja.getNombreColumnasEgresos());

		for (EgresosDTO e : egresosEnTabla) {
			String hora = e.getHora();
			String tipo = e.getTipo();
			String detalle = e.getDetalle();
			String medioPago = e.getMedioPago();
			BigDecimal total = new BigDecimal("" + e.getTotal() + "");

			Object[] fila = { hora, tipo, detalle, medioPago, total };
			this.ventanaCierreCaja.getModelEgresos().addRow(fila);
		}
	}

	public BigDecimal obtenerTotalIngresoMedioPago(String medioPago) {
		BigDecimal totalMedioPago = new BigDecimal(0);
		for (IngresosDTO ingreso : this.ingresosEnTabla) {
			if (ingreso.getMedioPago().equals(medioPago)) {
				BigDecimal total = new BigDecimal("" + ingreso.getTotal() + "");
				totalMedioPago = totalMedioPago.add(total);
			}
		}
		return totalMedioPago;
	}

	public void llenarTablaMedioPagoIngresos() {
		MedioPago medioPago = new MedioPago(new DAOSQLFactory());
		List<MedioPagoDTO> medioPagoLista = medioPago.readAll();

		for (MedioPagoDTO mp : medioPagoLista) {
			String medioPagoID = mp.getIdMoneda();
			String descripcion = mp.getDescripcion();
			BigDecimal total = obtenerTotalIngresoMedioPago(medioPagoID);
			if (!(total.compareTo(new BigDecimal("0.00")) == 0)) {
				Object[] fila = { medioPagoID, descripcion, total, false };
				this.ventanaCierreCaja.getModelMedioPagoIngresos().addRow(fila);
			}
		}
	}

	public String obtenerDescripcionMedioPago(String medioPagoID) {
		String descripcion = "";
		if (medioPagoID == "EFE") {
			descripcion = "Efectivo";
		}
		if (medioPagoID == "NC") {
			descripcion = "Nota Credito";
		}
		return descripcion;
	}

	public BigDecimal obtenerTotalEgresosMedioPago(String medioPago) {
		BigDecimal totalMedioPago = new BigDecimal(0);
		for (EgresosDTO e : this.egresosEnTabla) {
			if (e.getMedioPago().equals(medioPago)) {
				BigDecimal total = new BigDecimal("" + e.getTotal() + "");
				totalMedioPago = totalMedioPago.add(total);
			}
		}
		return totalMedioPago;
	}

	public void llenarTablaMedioPagoEgresos() {
		for (String mpe : tipoMedioPagoEgreso) {
			String medioPago = mpe;
			String descripcion = obtenerDescripcionMedioPago(mpe);
			BigDecimal total = obtenerTotalEgresosMedioPago(mpe);
			if (!(total.compareTo(new BigDecimal("0.00")) == 0)) {
				Object[] fila = { medioPago, descripcion, total, false };
				this.ventanaCierreCaja.getModelMedioPagoEgresos().addRow(fila);
			}
		}
	}

	public static void main(String[] args) {
		Ingresos ingresos = new Ingresos(new DAOSQLFactory());
		Egresos egresos = new Egresos(new DAOSQLFactory());
		ControladorCierreCaja controlador = new ControladorCierreCaja(ingresos, egresos);
		controlador.inicializar();
		controlador.mostrarVentana();
	}

}